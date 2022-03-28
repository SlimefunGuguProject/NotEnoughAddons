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
import java.util.function.Supplier;
import java.util.logging.Level;

public class FlyingBubbleListener {
    public static Map<Location, Set<UUID>> toGiveFlight = new HashMap<Location, Set<UUID>>();
    // public static Map<Location, Collection<Entity>> toCheckFlight = new HashMap<Location, Collection<Entity>>();
    Set<UUID> bubbledPlayers = new HashSet<>();
    Set<UUID> allBubbleUuids = new HashSet<>();
    // Set<UUID> allTracedUuids = new HashSet<>();
    ArrayList<Player> noFallDamage = new ArrayList<Player>();
    public void run() {

        for (Map.Entry<Location, Set<UUID>> flightEntry : toGiveFlight.entrySet()) {
            Set<UUID> bubbleFlightUuids = flightEntry.getValue();

            for (UUID uuid : bubbleFlightUuids) {
                if (!allBubbleUuids.contains(uuid)) {
                    allBubbleUuids.add(uuid);
                    if (!bubbledPlayers.contains(uuid)) {
                        bubbledPlayers.add(uuid);
                    }
                    Player p = Bukkit.getPlayer(uuid);
                    p.setAllowFlight(true);
                    
                    NotEnoughAddons.getInstance().getLogger().log(Level.SEVERE, "Give flight:" + uuid.toString());

                }
            }
        }
        // for (Map.Entry<Location, Collection<Entity>> checkEntry : toCheckFlight.entrySet()) {
        //     Collection<Entity> tracedEntity = checkEntry.getValue();

        //     for (Entity e : tracedEntity) {
        //         if (e instanceof Player) {
        //             Player p = (Player) e;
        //             UUID uuid = p.getUniqueId();
        //             if (!allTracedUuids.contains(uuid)) {
        //                 allTracedUuids.add(uuid);
        //                 NotEnoughAddons.getInstance().getLogger().log(Level.SEVERE, "all entities:" + uuid.toString());
        //             }
        //         }
        //     }
        // }

        // for (UUID uuid : allTracedUuids) {
        //     Player p = Bukkit.getPlayer(uuid);
        //     NotEnoughAddons.getInstance().getLogger().log(Level.SEVERE, "for traced:" + p.getName());
        //     if (allBubbleUuids.contains(uuid)) {
        //         p.setAllowFlight(true);
        //         if (!bubbledPlayers.contains(uuid)) {
        //             bubbledPlayers.add(uuid);
        //         }
            // } else {
            //     if (!bubbledPlayers.contains(uuid)) {
            //         noFallDamage.add(p);
            //         p.setAllowFlight(false);
            //         p.setFlying(false);
            //         p.setFallDistance(0.0f);
            //         bubbledPlayers.remove(uuid);
            //     }
                
            // }
        //     }
        // }
        NotEnoughAddons.getInstance().getLogger().log(Level.WARNING, allBubbleUuids.toString());
        for (UUID uuid : bubbledPlayers) {
            if (!allBubbleUuids.contains(uuid)) {
                Player p = Bukkit.getPlayer(uuid);
                p.setAllowFlight(false);
                p.setFlying(false);
                p.setFallDistance(0.0f);
                
                bubbledPlayers.remove(uuid);
            }
        }
        // toGiveFlight.clear();
        // toCheckFlight.clear();
        allBubbleUuids.clear();
        // allTracedUuids.clear();
    }
 
    public void updateSetFlying(Location blockLocation, Set<UUID> enabledPlayers) {
        toGiveFlight.remove(blockLocation);
        toGiveFlight.put(blockLocation, enabledPlayers);
    }


    public void allEntities(Location blockLocation, Collection<Entity> tracedEntities) {
        // toCheckFlight.remove(blockLocation);
        // toCheckFlight.put(blockLocation, tracedEntities);
    }
}