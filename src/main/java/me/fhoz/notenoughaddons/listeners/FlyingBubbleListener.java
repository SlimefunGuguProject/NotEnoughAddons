package me.fhoz.notenoughaddons.listeners;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemHandler;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.fhoz.notenoughaddons.NotEnoughAddons;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class FlyingBubbleListener {
    public static Map<Location, Set<UUID>> uuidLogger = new HashMap<Location, Set<UUID>>();
    Set<UUID> allBubbleUuids = new HashSet<>();
    public void run() {
        
        for (Map.Entry<Location, Set<UUID>> entry : uuidLogger.entrySet()) {
            Set<UUID> bubbleUuids = entry.getValue();
            final Iterator<UUID> playerIterator = bubbleUuids.iterator();
            while (playerIterator.hasNext()) {
                final UUID uuid = playerIterator.next();
                NotEnoughAddons.getInstance().getLogger().log(Level.SEVERE, uuid + "");
                if (!allBubbleUuids.contains(uuid)) {
                    allBubbleUuids.add(uuid);
                }
            }
        }   
        for (Player p : Bukkit.getOnlinePlayers()) {
            // if (p.getAllowFlight() && !allBubbleUuids.contains(p.getUniqueId())) {
            //     p.setAllowFlight(false);
            //     p.setFlying(false);
            //     p.setFallDistance(0.0F);
            // } else if (allBubbleUuids.contains(p.getUniqueId())) {
            //     p.setAllowFlight(true);
            // }
            if (allBubbleUuids.contains(p.getUniqueId())) {
                p.setAllowFlight(true);
            }
        }
    }
 
    public void updateValue(Location blockLocation, Set<UUID> enabledPlayers) {
        uuidLogger.put(blockLocation, enabledPlayers);
        NotEnoughAddons.getInstance().getLogger().log(Level.SEVERE, blockLocation.toString() + enabledPlayers);
        NotEnoughAddons.getInstance().getLogger().log(Level.SEVERE, blockLocation.toString() + enabledPlayers);
    }
}