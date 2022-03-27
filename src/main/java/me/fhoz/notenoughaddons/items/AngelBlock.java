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
            blockLocation.setY(blockLocation.getY() - 1);
            Block targetBlock = p.getWorld().getBlockAt(blockLocation);
            if (targetBlock.getType() != Material.AIR) {
                Utils.send(p, "You can only place Angel Blocks in the air");
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
                    targetBlock.setType(Material.AIR);
                    angelBlockBufferList.remove(targetBlock);
                    p.playSound(playerLocation, Sound.BLOCK_BAMBOO_BREAK, 100, 1);
                    p.playEffect(playerLocation, Effect.ENDER_SIGNAL, null);
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
