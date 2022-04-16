package me.fhoz.notenoughaddons.terraria.weapons.shortsword;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.core.handlers.WeaponUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import me.fhoz.notenoughaddons.utils.TerrariaUtils;

public class LeadShortsword extends SimpleSlimefunItem<WeaponUseHandler> {
    private static final double DAMAGE = 9;
    private static final double KNOCKBACK = 4;
    private static final double CRIT_CHANCE = 0.04;
    private static final int USE_TIME = 12;

    @ParametersAreNonnullByDefault
    public LeadShortsword(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    public @Nonnull WeaponUseHandler getItemHandler() {
        return (e, p, item) -> {
            TerrariaUtils.castDamage(e, p, item, CRIT_CHANCE, DAMAGE, KNOCKBACK, USE_TIME);
        };
    }

    public static int getUseTime() {
        return USE_TIME;
    }

    public static double getKB() {
        return KNOCKBACK;
    }

    public static double getDMG() {
        return DAMAGE;
    }

    public static double getCC() {
        return CRIT_CHANCE;
    }
}
