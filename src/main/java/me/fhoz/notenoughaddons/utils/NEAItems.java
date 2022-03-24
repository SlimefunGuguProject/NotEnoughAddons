package me.fhoz.notenoughaddons.utils;

import dev.j3fftw.extrautils.utils.LoreBuilderDynamic;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineTier;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineType;
import io.github.thebusybiscuit.slimefun4.utils.LoreBuilder;
import me.fhoz.notenoughaddons.machines.BudgetDustFabricator;

import org.bukkit.Material;

/**
 * Specifies all plugin items
 */
public class NEAItems {

    private NEAItems() {
    }

    public static final SlimefunItemStack BUDGET_DUST_FABRICATOR = new SlimefunItemStack("BUDGET_DUST_FABRICATOR",
            Material.CRACKED_STONE_BRICKS,
            "&6Budget Dust Fabricator",
            "&7An all-in-one machine that grinds, pans, and washes but in a budget way",
            "&7&oFor people who are too lazy to do slimefun...",
            LoreBuilder.machine(MachineTier.GOOD, MachineType.MACHINE),
            LoreBuilder.speed(1),
            LoreBuilderDynamic.powerBuffer(BudgetDustFabricator.CAPACITY),
            LoreBuilderDynamic.powerPerTick(BudgetDustFabricator.ENERGY_CONSUMPTION)
    );
}
