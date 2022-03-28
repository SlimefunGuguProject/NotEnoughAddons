package me.fhoz.notenoughaddons.items.electric;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemHandler;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.fhoz.notenoughaddons.NotEnoughAddons;
import me.fhoz.notenoughaddons.listeners.FlyingBubbleListener;
import me.fhoz.notenoughaddons.abstractitems.AMachine;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Flying;
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
import java.util.logging.Level;

public class FlyingBubble extends AMachine {
    FlyingBubbleListener flyingBubbleListener = new FlyingBubbleListener();
    public static final int ENERGY_CONSUMPTION = 15;
    
    public final Set<UUID> enabledPlayers = new HashSet<>();
    public org.bukkit.Location bubbleLocation;
    private static final int[] BORDER = new int[] { 1, 2, 6, 7, 9, 10, 11, 15, 16, 17, 19, 20, 24, 25 };
    private static final int[] BORDER_IN = new int[] { 3, 4, 5, 12, 14, 21, 22, 23 };
    private static final int[] BORDER_OUT = new int[] { 0, 8, 18, 26 };

    public static final int CAPACITY = ENERGY_CONSUMPTION * 3;

    public FlyingBubble(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);

        addItemHandler(onBlockBreak());
    }

    @Override
    public void preRegister() {
        addItemHandler(new BlockTicker() {
            @Override
            public void tick(Block b, SlimefunItem sfItem, Config data) {
                
                FlyingBubble.this.tick(b);
            }

            @Override
            public boolean isSynchronized() {
                return true;
            }
        });
    }

    @Override
    public void tick(Block b) {
        bubbleLocation = b.getLocation();
        // Collection<Entity> bubbledEntities = b.getWorld().getNearbyEntities(b.getLocation(), 25, 25, 25);
        

        // flyingBubbleListener.allEntities(bubbleLocation, bubbledEntities);
        // for (Entity entity : bubbledEntities) {
        //     if (entity instanceof Player) {
        //         Player p = (Player) entity;
        //         if (!p.getAllowFlight()) {
        //             enabledPlayers.add(p.getUniqueId());
        //             // flyingBubbleListener.updateSetFlying(bubbleLocation, enabledPlayers);
        //             removeCharge(b.getLocation(), getEnergyConsumption());
        //         }
        //     }
        // }
        // flyingBubbleListener.updateSetFlying(bubbleLocation, enabledPlayers);

        // for (UUID uuid : enabledPlayers) {
        //     Player p = Bukkit.getPlayer(uuid);
        //     if (p != null && !bubbledEntities.contains(p)) {
        //         enabledPlayers.remove(uuid);
        //     }
        // }
        // final Iterator<UUID> playerIterator = enabledPlayers.iterator();
        // while (playerIterator.hasNext()) {
        //     final UUID uuid = playerIterator.next();
        //     Player p = Bukkit.getPlayer(uuid);
        //     if (p != null && !bubbledEntities.contains(p)) {
        //         playerIterator.remove();
        //         // flyingBubbleListener.updateSetFlying(bubbleLocation, enabledPlayers);
        //     }
        // }
    }

    public void run() {
        Collection<Entity> bubbledEntities = bubbleLocation.getWorld().getNearbyEntities(bubbleLocation, 25, 25, 25);
        

        flyingBubbleListener.allEntities(bubbleLocation, bubbledEntities);
        for (Entity entity : bubbledEntities) {
            if (entity instanceof Player) {
                Player p = (Player) entity;
                if (!p.getAllowFlight()) {
                    enabledPlayers.add(p.getUniqueId());
                    // flyingBubbleListener.updateSetFlying(bubbleLocation, enabledPlayers);
                    removeCharge(bubbleLocation, getEnergyConsumption());
                }
            }
        }
        flyingBubbleListener.updateSetFlying(bubbleLocation, enabledPlayers);

        for (UUID uuid : enabledPlayers) {
            Player p = Bukkit.getPlayer(uuid);
            if (p != null && !bubbledEntities.contains(p)) {
                enabledPlayers.remove(uuid);
            }
        }
    }
    

    private ItemHandler onBlockBreak() {
        return new BlockBreakHandler(false, false) {
        
            @Override
            public void onPlayerBreak(BlockBreakEvent e, ItemStack tool, List<ItemStack> drops) {
                final Iterator<UUID> playerIterator = enabledPlayers.iterator();
                while (playerIterator.hasNext()) {
                    final UUID uuid = playerIterator.next();
                    Player p = Bukkit.getPlayer(uuid);
                    if (p != null) {
                        playerIterator.remove();
                        // flyingBubbleListener.updateSetFlying(bubbleLocation, enabledPlayers);
                    }
                }
            }
        };
    }

    @Override
    public boolean isGraphical() {
        return false;
    }
    
    @Override
    public String getMachineIdentifier() {
        return "FLYING_BUBBLE";
    }

    @Override
    public List<int[]> getBorders() {
        List<int[]> borders = new ArrayList<>();
        borders.add(BORDER);
        borders.add(BORDER_IN);
        borders.add(BORDER_OUT);
        
        return borders;
    }

    @Override
    public int[] getInputSlots() {
        return new int[] {13};
    }

    @Override
    public int[] getOutputSlots() {
        return new int[] {13};
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

    @Override
    public int getProgressBarSlot() {
        return 4;
    }

}