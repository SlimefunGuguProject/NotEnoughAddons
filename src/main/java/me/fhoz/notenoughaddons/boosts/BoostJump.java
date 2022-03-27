package me.fhoz.notenoughaddons.boosts;

import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import me.fhoz.notenoughaddons.NotEnoughAddons;
import me.fhoz.notenoughaddons.utils.NEAItems;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import javax.annotation.ParametersAreNonnullByDefault;

import java.util.ArrayList;

public class BoostJump extends SimpleSlimefunItem<ItemUseHandler> implements Listener  {
    private static ArrayList<Player> noFallDMGList = new ArrayList<Player>();
    private static double boost_power = 2.5;

    @ParametersAreNonnullByDefault
    public BoostJump(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            Player p = e.getPlayer();
            Vector pVel = p.getVelocity();

            if(p.getInventory().containsAtLeast(NEAItems.BOOST_JUMP, 1)){
                p.getInventory().removeItem(NEAItems.BOOST_JUMP);
                p.updateInventory();
            }

            final Location playerLocation = p.getLocation(); 
            final Vector boostVector = new Vector(pVel.getX(), boost_power, pVel.getZ());
            noFallDMGList.add(p);
            Bukkit.getScheduler().scheduleSyncDelayedTask(NotEnoughAddons.getInstance(), new Runnable() {
                public void run() {
                    noFallDMGList.remove(p);
                }
            }, 200);
            p.setVelocity(boostVector);
            p.playSound(playerLocation, Sound.ENTITY_HORSE_JUMP, 100, 2);
            p.playEffect(playerLocation, Effect.DRAGON_BREATH, null);
            
        };
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onFall(EntityDamageEvent event) {
        Entity e = event.getEntity();
        if(e instanceof Player && event.getCause() == EntityDamageEvent.DamageCause.FALL) {
            Player p = (Player)e;
            if(noFallDMGList.contains(p)) {
                event.setCancelled(true);
            }
        
        }
    
    }

    public static void onDisable() {
        BoostJump.noFallDMGList.clear();
    }
    public static void onEnable() {
        BoostJump.noFallDMGList.clear();
    }
}
