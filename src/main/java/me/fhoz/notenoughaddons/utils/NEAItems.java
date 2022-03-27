package me.fhoz.notenoughaddons.utils;

import dev.j3fftw.extrautils.utils.LoreBuilderDynamic;
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
    // Resources
    public static final SlimefunItemStack RIGHT_GLOOB_ESSENCE = new SlimefunItemStack("RIGHT_GLOOB_ESSENCE",
        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2I4YjY0N2UxNzJkNjUwNTg1MTJmYTgxYTFlNTUwNDgyNmM3OGJlNDZhMGFmNGJhYmJiMjhiODIxMzQ1ODIxZCJ9fX0=",
        "&9Right Gloob Essence",
        "&7Not to be confused with &aLeft Gloob Essence"
    );

    public static final SlimefunItemStack RIGHT_GLOOB = new SlimefunItemStack("RIGHT_GLOOB",
        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzM2YmExZGFiYmNhYmJkNjI3NzBjZWNlMDIwMmViZDliYjA2NDllY2E1MzExZWUyMmMzZDBmMjk4MWU5MDg5MCJ9fX0=",
        "&9Right Gloob",
        "&7Not to be confused with &aLeft Gloob"
    );

    public static final SlimefunItemStack POWERED_RIGHT_GLOOB = new SlimefunItemStack("POWERED_RIGHT_GLOOB",
        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzc2N2EwY2FjZGQ3MzE2ZDQwZTY5ZjZiYjkyM2NiNmI4YmYyZDgyNDk5MThkOGQxYjk0ZjJiZjQ2OWQ1Y2VmYyJ9fX0=",
        "&9Powered Right Gloob",
        "&7Not to be confused with &aPowered Left Gloob"
    );

    public static final SlimefunItemStack ENERGIZED_RIGHT_GLOOB = new SlimefunItemStack("ENERGIZED_RIGHT_GLOOB",
        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzc0MDBlYTE5ZGJkODRmNzVjMzlhZDY4MjNhYzRlZjc4NmYzOWY0OGZjNmY4NDYwMjM2NmFjMjliODM3NDIyIn19fQ==",
        "&9Energized Right Gloob",
        "&7Not to be confused with &aEnergized Left Gloob",
        "&7When gloobs get combined.."
    );

    public static final SlimefunItemStack LEFT_GLOOB_ESSENCE = new SlimefunItemStack("LEFT_GLOOB_ESSENCE",
        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWViODhhMzZlMWIxMGI1MWY2ZDdmYzhiYzlkM2IyMTQ3MWQ2NGQ2ZTc1YWZmYjgwMjRjZjE1ODY0MmZjYjlkIn19fQ==",
        "&aLeft Gloob Essence",
        "&7Not to be confused with &9Right Gloob Essence"
    );

    public static final SlimefunItemStack LEFT_GLOOB = new SlimefunItemStack("LEFT_GLOOB",
        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmUyYWFlYmFhMWE5ZWFkNTM2ZWRjNzlkZGZhZGU0NmNmNTBiNGM0MGM4M2MxMDJmYjYzZDg0ZDUzYzc2ZDY4ZiJ9fX0=",
        "&aLeft Gloob",
        "&7Not to be confused with &9Right Gloob"
    );

    public static final SlimefunItemStack POWERED_LEFT_GLOOB = new SlimefunItemStack("POWERED_LEFT_GLOOB",
        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTI4MjRkNDY5YzkzZTQ1NGEwYjIzZDJmMGM0MmZkNmNhNTRhOGI1NGU2OTBlYzkwNTljYzllNWEzZTZkOWY5MCJ9fX0=",
        "&aPowered Left Gloob",
        "&7Not to be confused with &9Powered Right Gloob"
    );

    public static final SlimefunItemStack ENERGIZED_LEFT_GLOOB = new SlimefunItemStack("ENERGIZED_LEFT_GLOOB",
        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTI4MjRkNDY5YzkzZTQ1NGEwYjIzZDJmMGM0MmZkNmNhNTRhOGI1NGU2OTBlYzkwNTljYzllNWEzZTZkOWY5MCJ9fX0=",
        "&aEnergized Left Gloob",
        "&7Not to be confused with &9Energized Right Gloob",
        "&7When gloobs get combined.."
    );

    public static final SlimefunItemStack DARK_GLOOB = new SlimefunItemStack("DARK_GLOOB",
        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjg4MTMwOTI1NmEwNjQxMzVjMDlkNDhiNzM4ODgxYzcwMmU5Y2RjMTMwNjJkYzk5MjdjZWM0ZWM0ZmU1ZWQ3YiJ9fX0=",
        "&5&lDark Gloob",
        "&7One of the final forms..",
        "",
        "&0&oYOU ARE ON THE DARK SIDE"
    );

    public static final SlimefunItemStack LIGHT_GLOOB = new SlimefunItemStack("LIGHT_GLOOB",
        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjg1NGNmNThmM2JhNmE1Mzg5MDZlMGM0NGZiZDUwYjU0ZjU2MTUyYzc3MzU0ZDk0N2E4Zjg5NWM3MjQzNjgyMCJ9fX0=",
        "&b&lLight Gloob",
        "&7One of the final forms..",
        "",
        "&f&oYOU ARE ON THE LIGHT SIDE"
    );

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
        "&6Antigravity Bubble",
        "",
        "&f Creative Flight within an 45 block area",
        "",
        LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE),
        LoreBuilderDynamic.powerBuffer(FlyingBubble.CAPACITY),
        LoreBuilderDynamic.powerPerTick(FlyingBubble.ENERGY_CONSUMPTION)
    );

    public static final SlimefunItemStack ANGEL_BLOCK = new SlimefunItemStack("ANGEL_BLOCK",
        Material.FEATHER,
        "&6Angel Block",
        "&7Places a block below you",
        "&7Very useful for building something in the sky",
        LoreBuilder.RIGHT_CLICK_TO_USE
    );

    public static final SlimefunItemStack BOOST_JUMP = new SlimefunItemStack("BOOST_JUMP", 
        Material.RABBIT_FOOT,
        "&6Jump Boost",
        "&7Launches you in the air, then gets consumed",
        LoreBuilder.RIGHT_CLICK_TO_USE
    );

    static {
        BOOST_JUMP.addUnsafeEnchantment(Enchantment.QUICK_CHARGE, 1);
    }
}
