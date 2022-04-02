package me.fhoz.notenoughaddons.utils;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.items.groups.NestedItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.SubItemGroup;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.fhoz.notenoughaddons.machines.BudgetDustFabricator;
import me.fhoz.notenoughaddons.items.AngelBlock;
import me.fhoz.notenoughaddons.items.electric.FlyingBubble;
import me.fhoz.notenoughaddons.NEAItemSetup;
import me.fhoz.notenoughaddons.NotEnoughAddons;
import me.fhoz.notenoughaddons.boosts.BoostJump;
import me.fhoz.notenoughaddons.utils.NEAItems;

import javax.annotation.Nonnull;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

public class CompressedItemSetup {
    private final ItemStack copperDust = new SlimefunItemStack(SlimefunItems.COPPER_DUST.getItemId(),
        SlimefunItems.COPPER_DUST);

    private final ItemStack goldDust = new SlimefunItemStack(SlimefunItems.GOLD_DUST.getItemId(),
        SlimefunItems.GOLD_DUST);

    private final static ItemStack ironDust = new SlimefunItemStack(SlimefunItems.IRON_DUST.getItemId(),
        SlimefunItems.IRON_DUST);

    private final ItemStack leadDust = new SlimefunItemStack(SlimefunItems.LEAD_DUST.getItemId(),
        SlimefunItems.LEAD_DUST);

    private final ItemStack aluminumDust = new SlimefunItemStack(SlimefunItems.ALUMINUM_DUST.getItemId(),
        SlimefunItems.ALUMINUM_DUST);

    private final ItemStack zincDust = new SlimefunItemStack(SlimefunItems.ZINC_DUST.getItemId(),
        SlimefunItems.ZINC_DUST);

    private final ItemStack tinDust = new SlimefunItemStack(SlimefunItems.TIN_DUST.getItemId(),
        SlimefunItems.TIN_DUST);

    private final ItemStack magnesiumDust = new SlimefunItemStack(SlimefunItems.MAGNESIUM_DUST.getItemId(),
        SlimefunItems.MAGNESIUM_DUST);

    private final ItemStack silverDust = new SlimefunItemStack(SlimefunItems.SILVER_DUST.getItemId(),
        SlimefunItems.SILVER_DUST);

    


    public static void setup(ItemGroup itemGroup, SlimefunItemStack item, SlimefunItemStack recipeItem, RecipeType recipeType, @Nonnull NotEnoughAddons plugin) {
        new SlimefunItem(itemGroup, item, recipeType, 
        new ItemStack[] {
            new SlimefunItemStack(recipeItem, 8), null, null,
            null, null, null,
            null, null, null
        }).register(plugin);
        
        // new SlimefunItem(NEAItemSetup.decompressing_recipes, new SlimefunItemStack(SlimefunItem.getById(recipeItem).getId(), SlimefunItem.getById(recipeItem).getItem()), recipeType, 
        // new ItemStack[] {
        //     item, null, null,
        //     null, null, null,
        //     null, null, null
        // }, SlimefunItem.getById(recipeItem).getItem()).register(plugin);
    }
    public static void setupDecompressing(ItemGroup itemGroup, RecipeType recipeType, @Nonnull NotEnoughAddons plugin) {
        new SlimefunItem(itemGroup, SlimefunItems.IRON_DUST, recipeType, 
        new ItemStack[] {
            NEACompressedItems.COMPRESSED_IRON_DUST, null, null,
            null, null, null,
            null, null, null
        }, new SlimefunItemStack(SlimefunItems.IRON_DUST, 8)).register(plugin);
    }
}
