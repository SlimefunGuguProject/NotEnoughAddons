package me.fhoz.notenoughaddons.items.electric;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemHandler;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class FlyingBubble extends AContainer implements RecipeDisplayItem {

    public static final int ENERGY_CONSUMPTION = 15;

    private final Set<UUID> enabledPlayers = new HashSet<>();
    public static final int CAPACITY = ENERGY_CONSUMPTION * 3;

    public FlyingBubble(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    public void preRegister() {
        super.preRegister();
    }

    @Override
    public void tick(Block b) {
        Collection<Entity> bubbledEntities = b.getWorld().getNearbyEntities(b.getLocation(), 25, 25, 25);

        for (Entity entity : bubbledEntities) {
            if (entity instanceof Player) {
                Player p = (Player) entity;

                if (!p.getAllowFlight()) {
                    enabledPlayers.add(p.getUniqueId());
                    // p.setAllowFlight(true);
                    removeCharge(b.getLocation(), getEnergyConsumption());
                }
            }
        }

        final Iterator<UUID> playerIterator = enabledPlayers.iterator();
        while (playerIterator.hasNext()) {
            final UUID uuid = playerIterator.next();
            Player p = Bukkit.getPlayer(uuid);

            if (p != null && !bubbledEntities.contains(p)) {
                p.setAllowFlight(false);
                // p.setFlying(false);
                // p.setFallDistance(0.0f);
                playerIterator.remove();
            }
        }
    }

    public void onPlayerBreak(BlockBreakEvent e, ItemStack tool, List<ItemStack> drops) {
        final Iterator<UUID> playerIterator = enabledPlayers.iterator();
                while (playerIterator.hasNext()) {
                    final UUID uuid = playerIterator.next();
                    Player p = Bukkit.getPlayer(uuid);
                    if (p != null) {
                        // p.setAllowFlight(false);
                        // p.setFlying(false);
                        // p.setFallDistance(0.0F);
                        playerIterator.remove();
                    }
                }
    }
    
    @Override
    public String getMachineIdentifier() {
        return "FLYING_BUBBLE";
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.CAULDRON);
    }

    @Override
    public int getCapacity() {
        return CAPACITY;
    }

    @Override
    public int getSpeed() {
        return 1;
    }

    @Override
    public int getEnergyConsumption() {
        return ENERGY_CONSUMPTION;
    }

}