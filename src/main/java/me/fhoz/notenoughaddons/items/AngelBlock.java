package me.fhoz.notenoughaddons.items;

import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import me.fhoz.notenoughaddons.NotEnoughAddons;
import me.fhoz.notenoughaddons.utils.Utils;
import me.fhoz.notenoughaddons.utils.NEAItems;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.association.RegionAssociable;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;

import java.util.ArrayList;


public class AngelBlock extends SimpleSlimefunItem<ItemUseHandler> {
    private static ArrayList<Block> angelBlockBufferList = new ArrayList<Block>();
    @ParametersAreNonnullByDefault
    public AngelBlock(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

   

    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            Player p = e.getPlayer();
            final Location playerLocation = p.getLocation(); 
            final Location blockLocation = playerLocation;

            LocalPlayer localPlayer = WorldGuardPlugin.inst().wrapPlayer(p);
            RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
            RegionQuery query = container.createQuery();
            ApplicableRegionSet set = query.getApplicableRegions(BukkitAdapter.adapt(playerLocation));


            if (!set.testState(localPlayer, Flags.BUILD)) {
                Utils.send(p, "Missing build permissions");
                return;
            }

            blockLocation.setY(blockLocation.getY() - 1);
            Block targetBlock = p.getWorld().getBlockAt(blockLocation);
            if (targetBlock.getType() != Material.AIR) {
                return;
            }
            targetBlock.setType(Material.COBBLESTONE);
            angelBlockBufferList.add(targetBlock);
            if(p.getInventory().containsAtLeast(NEAItems.ANGEL_BLOCK, 1)){
                p.getInventory().removeItem(NEAItems.ANGEL_BLOCK);
                p.updateInventory();
            }
            Bukkit.getScheduler().scheduleSyncDelayedTask(NotEnoughAddons.getInstance(), new Runnable() {
                public void run() {
                    if (targetBlock.getType() == Material.COBBLESTONE) {
                        targetBlock.setType(Material.AIR);
                        p.playSound(playerLocation, Sound.BLOCK_BAMBOO_BREAK, 100, 1);
                        p.playEffect(playerLocation, Effect.ENDER_SIGNAL, null);
                    }
                    angelBlockBufferList.remove(targetBlock);
                }
            }, 200);

        };
    }

    public static void onDisable() {
        for (Block angelBlock : AngelBlock.angelBlockBufferList) {
            angelBlock.setType(Material.AIR);
        }
        AngelBlock.angelBlockBufferList.clear();
    }

    

}
