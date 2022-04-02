package me.fhoz.notenoughaddons.items.backpacks;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.backpacks.SlimefunBackpack;
import me.fhoz.notenoughaddons.listeners.MinerBackpackListener;

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
        defaultWhitelist.add(Material.NETHERITE_SCRAP);
        defaultWhitelist.add(Material.ANCIENT_DEBRIS);
        defaultWhitelist.add(Material.EMERALD);
        defaultWhitelist.add(Material.EMERALD_ORE);
        defaultWhitelist.add(Material.QUARTZ);
        defaultWhitelist.add(Material.NETHER_QUARTZ_ORE);
        defaultWhitelist.add(Material.NETHER_GOLD_ORE);
        defaultWhitelist.add(Material.GOLD_NUGGET);

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
