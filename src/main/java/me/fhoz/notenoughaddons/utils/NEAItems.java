package me.fhoz.notenoughaddons.utils;

import dev.j3fftw.extrautils.utils.LoreBuilderDynamic;
import io.github.bakedlibs.dough.items.CustomItemStack;
import io.github.bakedlibs.dough.skins.PlayerHead;
import io.github.bakedlibs.dough.skins.PlayerSkin;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineTier;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineType;
import io.github.thebusybiscuit.slimefun4.utils.LoreBuilder;
import me.fhoz.notenoughaddons.items.electric.FlyingBubble;
import me.fhoz.notenoughaddons.machines.BudgetDustFabricator;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

/**
 * Specifies all plugin items
 */
public class NEAItems {

    private NEAItems() {
    }

    // Machines
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

    public static final SlimefunItemStack FLYING_BUBBLE = new SlimefunItemStack("FLYING_BUBBLE",
        Material.CRYING_OBSIDIAN,
        "&6Flying Bubble",
        "",
        "&f Creative Flight within an 45 block area",
        "",
        LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE),
        LoreBuilder.powerPerSecond(128)
    );

    public static final SlimefunItemStack ANGEL_BLOCK = new SlimefunItemStack("ANGEL_BLOCK",
        Material.FEATHER,
        "&6Angel Block",
        "&7Places a block below you",
        "&7Very useful for building something in the sky",
        LoreBuilder.RIGHT_CLICK_TO_USE
    );


    public static final SlimefunItemStack MINER_BACKPACK = new SlimefunItemStack("MINER_BACKPACK",
        new CustomItemStack(PlayerHead.getItemStack(PlayerSkin.fromHashCode("8dcc6eb40f3bada41e4339888d6d207437598bdbd175c2e731191d5a9a42d3c8"))),
        "&6Miner Backpack",
        "",
        "&fAllows you to store ores",
        "&fAutomatically stores them when you pick them up",
        "&fMust be in your inventory",
        "",
        "&7Size: &e54 (Double chest)",
        "",
        "&7ID: <ID>",
        "",
        LoreBuilder.RIGHT_CLICK_TO_OPEN
    );
}
