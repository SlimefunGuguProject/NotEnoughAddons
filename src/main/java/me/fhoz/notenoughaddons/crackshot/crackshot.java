package me.fhoz.notenoughaddons.crackshot;

import com.shampaggon.crackshot.CSUtility;
import com.shampaggon.crackshot.events.WeaponReloadEvent;
import me.fhoz.notenoughaddons.NotEnoughAddons;
import me.fhoz.notenoughaddons.utils.Utils;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

public class crackshot implements Listener {
    CSUtility csu = new CSUtility();
    private static final NamespacedKey key = new NamespacedKey(NotEnoughAddons.getInstance(), "doerability");
    private static final String[] ammoNameArray = new String[] {"&6Filled Flammenwerfer Tank",
        "§c40mm HE Grenade",
        "§eTaser Cartridge",
        "§67.62 NATO",
        "§68mm Mauser",
        "§65.56 NATO",
        "§67.62 Soviet",
        "§67.92 Kurz",
        "§69mm Parabellum",
        "§6.50 AE",
        "§c12 Gauge Buckshot"
    };
    private static final List<String> ammoNames = new ArrayList<>(Arrays.asList(ammoNameArray));

    @EventHandler
    private void onWeaponReload(WeaponReloadEvent w) {
        Player p = w.getPlayer();
        String weaponTitle = w.getWeaponTitle();
        String regex = "\\s+.?\\s?«\\d+».*";

        ItemStack dummy = csu.generateWeapon(weaponTitle);
        String dummyDispName = dummy.getItemMeta().getDisplayName().replaceAll(regex, "");

        ItemStack weapon = p.getInventory().getItemInMainHand();
        String heldDispName = weapon.getItemMeta().getDisplayName().replaceAll(regex, "");

        if (!dummyDispName.equals(heldDispName)) {
            ItemStack itemHeldOffhand = p.getInventory().getItemInOffHand();
            heldDispName = itemHeldOffhand.getItemMeta().getDisplayName().replaceAll(regex, "");
            weapon = itemHeldOffhand;
            if (!dummyDispName.equals(heldDispName)) {
                return;
            }
        }
        Damageable weaponMeta = (Damageable) weapon.getItemMeta();

        PersistentDataContainer itemData = weaponMeta.getPersistentDataContainer();
        if (itemData.get(key, PersistentDataType.INTEGER) == null) {
            weaponMeta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, 1);
        } else if (itemData.get(key, PersistentDataType.INTEGER) % 5 == 0) {
            weaponMeta.setDamage(weaponMeta.getDamage() + 1);
            itemData.set(key, PersistentDataType.INTEGER, itemData.get(key, PersistentDataType.INTEGER) + 1);
        } else {
            itemData.set(key, PersistentDataType.INTEGER, itemData.get(key, PersistentDataType.INTEGER) + 1);
        }
        weapon.setItemMeta(weaponMeta);
    }

    @EventHandler
    private void prepareAnvil(PrepareAnvilEvent anvilEvent) {
        ItemStack result = anvilEvent.getResult();
        if (result != null) {
            ItemMeta resultMeta = result.getItemMeta();
            String resultName = resultMeta.getDisplayName();
            NotEnoughAddons.getInstance().getLogger().log(Level.SEVERE, resultName);
            if (ammoNames.contains(resultName)) {
                resultMeta.setDisplayName("");
                result.setItemMeta(resultMeta);
                anvilEvent.setResult(result);
            }
        }
    }

    @Nonnull
    public static void repairWeapon(Economy econ, Player p) {
        ItemStack item = p.getInventory().getItemInMainHand();

        if (!item.hasItemMeta()) {
            Utils.send(p, "Hold your weapon in your main hand and then run the command to repair it.");
            return;
        }

        Damageable itemMeta = (Damageable) item.getItemMeta();
        PersistentDataContainer itemData = itemMeta.getPersistentDataContainer();

        if (itemData.get(key, PersistentDataType.INTEGER) == null) {
            Utils.send(p, "Invalid item");
            return;
        }

        int damage = itemData.get(key, PersistentDataType.INTEGER);

        if (damage < 5) {
            Utils.send(p, "Weapon has full durability");
            return;
        }

        int price = damage * 250;
        double bal = econ.getBalance(p);
        if (bal < price) {
            Utils.send(p, String.format("It costs %s to repair your weapon. You only have %s.", econ.format(price), econ.format(bal)));
            return;
        }

        EconomyResponse r = econ.withdrawPlayer(p, price);
        if (r.transactionSuccess()) {
            Utils.send(p, String.format("You have repaired your weapon for %s, you now have %s.", econ.format(r.amount), econ.format(r.balance)));
        } else {
            Utils.send(p, String.format("An error occured: %s", r.errorMessage));
        }
        itemData.set(key, PersistentDataType.INTEGER, 1);
        itemMeta.setDamage(0);
        item.setItemMeta(itemMeta);
    }
}
