package me.fhoz.notenoughaddons;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.items.groups.NestedItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.SubItemGroup;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.items.multiblocks.PressureChamber;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.fhoz.notenoughaddons.machines.BudgetDustFabricator;
import me.fhoz.notenoughaddons.items.AngelBlock;
import me.fhoz.notenoughaddons.items.backpacks.MinerBackpack;
import me.fhoz.notenoughaddons.items.electric.FlyingBubble;
import me.fhoz.notenoughaddons.boosts.BoostJump;
import me.fhoz.notenoughaddons.utils.NEAItems;
import me.fhoz.notenoughaddons.utils.CompressedItemSetup;
import me.fhoz.notenoughaddons.utils.NEACompressedItems;

import javax.annotation.Nonnull;

import com.google.gson.internal.bind.SqlDateTypeAdapter;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

public final class NEAItemSetup {
        private NEAItemSetup() {}
        // ItemGroup
        private static final NestedItemGroup notenoughaddons = new NestedItemGroup(
                new NamespacedKey(NotEnoughAddons.getInstance(), "notenoughaddons"),
                new CustomItemStack(Material.COMMAND_BLOCK, "&2&n&lN&r&aot&2&l&nE&r&anough&2&l&nA&r&addons")
        );

        // public static final ItemGroup decompressing_recipes = new SubItemGroup(
        //         new NamespacedKey(NotEnoughAddons.getInstance(), "decompressing_recipes"), notenoughaddons,
        //         new CustomItemStack(Material.TNT, "&aDecompressing Recipes"), 36
        // );

        // public static final SubItemGroup compressed_dust = new SubItemGroup(
        //         new NamespacedKey(NotEnoughAddons.getInstance(), "compressed_dust"), notenoughaddons, 
        //         new CustomItemStack(Material.PISTON, "&bCompressed Dust"), 35
        // );

        private static final ItemGroup machines = new SubItemGroup(
                new NamespacedKey(NotEnoughAddons.getInstance(), "machines"), notenoughaddons,
                new CustomItemStack(Material.DEAD_BUSH, "&bMachines"), 1
        );

        private static final ItemGroup items = new SubItemGroup(
                new NamespacedKey(NotEnoughAddons.getInstance(), "items"), notenoughaddons,
                new CustomItemStack(Material.LEAD, "&bItems"), 2
        );

        // public static final ItemGroup boosts = new SubItemGroup(
        //         new NamespacedKey(NotEnoughAddons.getInstance(), "boosts"), notenoughaddons,
        //         new CustomItemStack(Material.LINGERING_POTION, "&bBoosts"), 3
        // );

        // public static final ItemGroup gloobs = new SubItemGroup(
        //         new NamespacedKey(NotEnoughAddons.getInstance(), "gloobs"), notenoughaddons,
        //         new CustomItemStack(Material.IRON_INGOT, "&2Glo&9obs"), 4
        // );

        public static void setup(@Nonnull NotEnoughAddons plugin) {
                // CompressedItemSetup.setup(compressed_dust, NEACompressedItems.COMPRESSED_COPPER_DUST, SlimefunItems.COPPER_DUST, RecipeType.COMPRESSOR, plugin);
                // CompressedItemSetup.setup(compressed_dust, NEACompressedItems.COMPRESSED_GOLD_DUST, SlimefunItems.GOLD_DUST, RecipeType.COMPRESSOR, plugin);
                // CompressedItemSetup.setup(compressed_dust, NEACompressedItems.COMPRESSED_IRON_DUST, SlimefunItems.IRON_DUST, RecipeType.COMPRESSOR, plugin);
                // CompressedItemSetup.setup(compressed_dust, NEACompressedItems.COMPRESSED_LEAD_DUST, SlimefunItems.LEAD_DUST, RecipeType.COMPRESSOR, plugin);
                // CompressedItemSetup.setupDecompressing(decompressing_recipes, RecipeType.COMPRESSOR, plugin);
                // Resources
                // new SlimefunItem(gloobs, NEAItems.RIGHT_GLOOB_ESSENCE, RecipeType.SMELTERY,
                // new ItemStack[] {
                // new ItemStack(Material.LAPIS_LAZULI), null, null,
                //         null, null, null,
                //         null, null, null,
                // }, new SlimefunItemStack(NEAItems.RIGHT_GLOOB_ESSENCE, 2)).register(plugin);

                // new SlimefunItem(gloobs, NEAItems.LEFT_GLOOB_ESSENCE, RecipeType.SMELTERY,
                // new ItemStack[] {
                // new ItemStack(Material.EMERALD), null, null,
                //         null, null, null,
                //         null, null, null,
                // }, new SlimefunItemStack(NEAItems.LEFT_GLOOB_ESSENCE, 2)).register(plugin);

                // new SlimefunItem(gloobs, NEAItems.RIGHT_GLOOB, RecipeType.SMELTERY,
                // new ItemStack[] {
                //         NEAItems.RIGHT_GLOOB_ESSENCE, new ItemStack(Material.LAPIS_LAZULI), new ItemStack(Material.BLUE_STAINED_GLASS_PANE),
                //         null, null, null,
                //         null, null, null,
                // }).register(plugin);

                // new SlimefunItem(gloobs, NEAItems.LEFT_GLOOB, RecipeType.SMELTERY,
                // new ItemStack[] {
                //         NEAItems.LEFT_GLOOB_ESSENCE, new ItemStack(Material.EMERALD), new ItemStack(Material.GREEN_STAINED_GLASS_PANE),
                //         null, null, null,
                //         null, null, null,
                // }).register(plugin);

                // new SlimefunItem(gloobs, NEAItems.POWERED_RIGHT_GLOOB, RecipeType.ENHANCED_CRAFTING_TABLE,
                // new ItemStack[] {
                //         NEAItems.RIGHT_GLOOB_ESSENCE, NEAItems.RIGHT_GLOOB, NEAItems.RIGHT_GLOOB_ESSENCE,
                //         NEAItems.RIGHT_GLOOB, SlimefunItems.CARBON, NEAItems.RIGHT_GLOOB,
                //         NEAItems.RIGHT_GLOOB_ESSENCE, NEAItems.RIGHT_GLOOB, NEAItems.RIGHT_GLOOB_ESSENCE
                // }).register(plugin);

                // new SlimefunItem(gloobs, NEAItems.POWERED_LEFT_GLOOB, RecipeType.ENHANCED_CRAFTING_TABLE,
                // new ItemStack[] {
                //         NEAItems.LEFT_GLOOB_ESSENCE, NEAItems.LEFT_GLOOB, NEAItems.LEFT_GLOOB_ESSENCE,
                //         NEAItems.LEFT_GLOOB, SlimefunItems.CARBON, NEAItems.LEFT_GLOOB,
                //         NEAItems.LEFT_GLOOB_ESSENCE, NEAItems.LEFT_GLOOB, NEAItems.LEFT_GLOOB_ESSENCE
                // }).register(plugin);

                // new SlimefunItem(gloobs, NEAItems.ENERGIZED_RIGHT_GLOOB, RecipeType.ENHANCED_CRAFTING_TABLE,
                // new ItemStack[] {
                //         SlimefunItems.COMPRESSED_CARBON, NEAItems.RIGHT_GLOOB_ESSENCE, SlimefunItems.COMPRESSED_CARBON,
                //         NEAItems.LEFT_GLOOB, NEAItems.POWERED_RIGHT_GLOOB, NEAItems.RIGHT_GLOOB,
                //         SlimefunItems.COMPRESSED_CARBON, NEAItems.RIGHT_GLOOB_ESSENCE, SlimefunItems.COMPRESSED_CARBON
                // }).register(plugin);

                // new SlimefunItem(gloobs, NEAItems.ENERGIZED_LEFT_GLOOB, RecipeType.ENHANCED_CRAFTING_TABLE,
                // new ItemStack[] {
                //         SlimefunItems.COMPRESSED_CARBON, NEAItems.LEFT_GLOOB_ESSENCE, SlimefunItems.COMPRESSED_CARBON,
                //         NEAItems.LEFT_GLOOB, NEAItems.POWERED_LEFT_GLOOB, NEAItems.RIGHT_GLOOB,
                //         SlimefunItems.COMPRESSED_CARBON, NEAItems.LEFT_GLOOB_ESSENCE, SlimefunItems.COMPRESSED_CARBON
                // }).register(plugin);

                // new SlimefunItem(gloobs, NEAItems.DARK_GLOOB, RecipeType.ANCIENT_ALTAR,
                // new ItemStack[] {
                //         SlimefunItems.CARBONADO, SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.CARBONADO,
                //         NEAItems.ENERGIZED_LEFT_GLOOB, new ItemStack(Material.WITHER_SKELETON_SKULL), NEAItems.ENERGIZED_RIGHT_GLOOB,
                //         SlimefunItems.CARBONADO, SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.CARBONADO
                // }).register(plugin);

                // new SlimefunItem(gloobs, NEAItems.LIGHT_GLOOB, RecipeType.ANCIENT_ALTAR,
                // new ItemStack[] {
                //         SlimefunItems.SYNTHETIC_SAPPHIRE, SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.SYNTHETIC_SAPPHIRE,
                //         NEAItems.ENERGIZED_LEFT_GLOOB, new ItemStack(Material.NETHER_STAR), NEAItems.ENERGIZED_RIGHT_GLOOB,
                //         SlimefunItems.SYNTHETIC_SAPPHIRE, SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.SYNTHETIC_SAPPHIRE
                // }).register(plugin);
                
                // Machines
                new BudgetDustFabricator(machines, NEAItems.BUDGET_DUST_FABRICATOR,
                        RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                        new ItemStack(Material.GRINDSTONE), new ItemStack(Material.GRINDSTONE), new ItemStack(Material.GRINDSTONE),
                        SlimefunItems.GOLD_PAN, new ItemStack(Material.DIAMOND), SlimefunItems.GOLD_PAN,
                        SlimefunItems.MAGNET, new ItemStack(Material.WATER_BUCKET), SlimefunItems.MAGNET
                }).register(plugin);

                // new FlyingBubble(machines, NEAItems.FLYING_BUBBLE, RecipeType.ENHANCED_CRAFTING_TABLE, 
                // new ItemStack[] {
                //         SlimefunItems.BLISTERING_INGOT_3, new ItemStack(Material.DIAMOND_BLOCK), SlimefunItems.BLISTERING_INGOT_3,
                //         NEAItems.LEFT_GLOOB, SlimefunItems.BIG_CAPACITOR , NEAItems.RIGHT_GLOOB,
                //         SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.REINFORCED_ALLOY_INGOT
                // }).register(plugin);
                

                // Items
                new AngelBlock(items, NEAItems.ANGEL_BLOCK,
                        RecipeType.MAGIC_WORKBENCH, new ItemStack[]{
                        new ItemStack(Material.FEATHER), SlimefunItems.MAGIC_LUMP_2,  new ItemStack(Material.FEATHER),
                        SlimefunItems.MAGIC_LUMP_2, new ItemStack(Material.COBBLESTONE), SlimefunItems.MAGIC_LUMP_2,
                        new ItemStack(Material.FEATHER), SlimefunItems.MAGIC_LUMP_2, new ItemStack(Material.FEATHER)
                }).register(plugin);

                new MinerBackpack(27, items, NEAItems.MINER_BACKPACK, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {
                        new ItemStack(Material.DIRT), null, null, 
                        null, null, null, 
                        null, null, null
                }).register(plugin);


                // Boosts
                // new BoostJump(boosts, NEAItems.BOOST_JUMP,
                //     RecipeType.MAGIC_WORKBENCH, new ItemStack[]{
                //         new ItemStack(Material.LILY_PAD), new ItemStack(Material.FEATHER), new ItemStack(Material.RABBIT),
                //         null, null, null,
                //         null, null, null
                // }).register(plugin);
    }
}
