package me.fhoz.notenoughaddons.items.backpacks;

import io.github.thebusybiscuit.slimefun4.api.MinecraftVersion;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.implementation.items.backpacks.SlimefunBackpack;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class MinerBackpack extends SlimefunBackpack {

    private final List<Material> defaultWhitelist = new ArrayList<>();

    private final ItemSetting<List<String>> whitelistedMaterials = new ItemSetting<>(this, "whitelisted-materials", ToStringList(getDefaultWhitelist()));

    public MinerBackpack(int size, ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType,
                         ItemStack[] recipe) {
        super(size, itemGroup, item, recipeType, recipe);
        //TODO Auto-generated constructor stub

        addItemSetting(whitelistedMaterials);
    }

    @Override
    public boolean isItemAllowed(@Nonnull ItemStack item, @Nullable SlimefunItem itemAsSlimefunItem) {
        return whitelistedMaterials.getValue().contains(item.getType().toString());
    }

    private List<Material> getDefaultWhitelist() {
        defaultWhitelist.add(Material.COAL);
        defaultWhitelist.add(Material.COAL_ORE);
        defaultWhitelist.add(Material.IRON_ORE);
        defaultWhitelist.add(Material.REDSTONE);
        defaultWhitelist.add(Material.REDSTONE_ORE);
        defaultWhitelist.add(Material.GOLD_ORE);
        defaultWhitelist.add(Material.DIAMOND);
        defaultWhitelist.add(Material.DIAMOND_ORE);
        defaultWhitelist.add(Material.LAPIS_LAZULI);
        defaultWhitelist.add(Material.LAPIS_ORE);
        defaultWhitelist.add(Material.EMERALD);
        defaultWhitelist.add(Material.EMERALD_ORE);
        defaultWhitelist.add(Material.QUARTZ);
        defaultWhitelist.add(Material.NETHER_QUARTZ_ORE);
        defaultWhitelist.add(Material.GOLD_NUGGET);

        if (Slimefun.getMinecraftVersion().isAtLeast(MinecraftVersion.MINECRAFT_1_16)) {
            defaultWhitelist.add(Material.NETHERITE_SCRAP);
            defaultWhitelist.add(Material.ANCIENT_DEBRIS);
            defaultWhitelist.add(Material.NETHER_GOLD_ORE);
        }

        if (Slimefun.getMinecraftVersion().isAtLeast(MinecraftVersion.MINECRAFT_1_17)) {
            defaultWhitelist.add(Material.COPPER_ORE);
            defaultWhitelist.add(Material.DEEPSLATE_COPPER_ORE);
            defaultWhitelist.add(Material.DEEPSLATE_COAL_ORE);
            defaultWhitelist.add(Material.DEEPSLATE_IRON_ORE);
            defaultWhitelist.add(Material.DEEPSLATE_DIAMOND_ORE);
            defaultWhitelist.add(Material.DEEPSLATE_EMERALD_ORE);
            defaultWhitelist.add(Material.DEEPSLATE_GOLD_ORE);
            defaultWhitelist.add(Material.DEEPSLATE_LAPIS_ORE);
            defaultWhitelist.add(Material.DEEPSLATE_REDSTONE_ORE);
        }

        return defaultWhitelist;
    }

    private List<String> ToStringList(List<Material> mats) {
        List<String> materials = new ArrayList<>();

        for (Material mat : mats) {
            materials.add(mat.toString());
        }

        return materials;
    }
}
