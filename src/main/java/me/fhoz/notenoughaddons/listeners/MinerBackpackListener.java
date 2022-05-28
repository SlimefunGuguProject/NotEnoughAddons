package me.fhoz.notenoughaddons.listeners;

import io.github.thebusybiscuit.slimefun4.api.player.PlayerBackpack;
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.fhoz.notenoughaddons.NotEnoughAddons;
import me.fhoz.notenoughaddons.items.backpacks.MinerBackpack;
import me.fhoz.notenoughaddons.utils.Utils;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.Map;

public class MinerBackpackListener implements Listener {
    private final NotEnoughAddons plugin;
    private final MinerBackpack minerBackpack;


    public MinerBackpackListener(@Nonnull NotEnoughAddons plugin, @Nonnull MinerBackpack minerBackpack) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);

        this.plugin = plugin;
        this.minerBackpack = minerBackpack;
    }

    @EventHandler
    public void onItemPickup(EntityPickupItemEvent e) {
        if (minerBackpack == null || minerBackpack.isDisabled()) {
            return;
        }

        if (!(e.getEntity() instanceof Player)) {
            return;
        }

        Player p = (Player) e.getEntity();
        Item pickedItem = e.getItem();
        ItemStack pickedItemStack = pickedItem.getItemStack();

        for (ItemStack item : p.getInventory().getContents()) {
            if (item != null && item.getType() == minerBackpack.getItem().getType() && item.hasItemMeta() && minerBackpack.isItem(item)) {
                if (SlimefunUtils.canPlayerUseItem(p, minerBackpack.getItem(), true)) {
                    PlayerProfile.getBackpack(item, backpack -> {
                        if (backpack != null && minerBackpack.isItemAllowed(pickedItemStack, null)) {
                            NotEnoughAddons.runSync(() -> addOre(p, item, backpack, pickedItemStack));
                        }
                    });
                } else {
                    return;
                }
            }
        }
    }

    private void addOre(Player p, ItemStack minerBackpackItem, PlayerBackpack backpack, ItemStack pickedItemStack) {
        Inventory inv = backpack.getInventory();
        if (inv.firstEmpty() == -1) {
            Utils.send(p, "&6Miner Backpack &r is full");
            return;
        }
        Map<Integer, ItemStack> notRemoved = p.getInventory().removeItem(pickedItemStack);
        if (notRemoved.isEmpty()) {
            inv.addItem(pickedItemStack);
        } else {
            int toRemove = 0;
            for (ItemStack itemStack : notRemoved.values()) {
                toRemove += itemStack.getAmount();
            }
            pickedItemStack.setAmount(pickedItemStack.getAmount() - toRemove);
            inv.addItem(pickedItemStack);
        }

        backpack.markDirty();
    }
}
