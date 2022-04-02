package me.fhoz.notenoughaddons.utils;

import dev.j3fftw.extrautils.utils.LoreBuilderDynamic;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineTier;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.utils.LoreBuilder;
import me.fhoz.notenoughaddons.items.electric.FlyingBubble;
import me.fhoz.notenoughaddons.machines.BudgetDustFabricator;
import me.fhoz.notenoughaddons.utils.Constants;
import io.github.thebusybiscuit.slimefun4.implementation.items.multiblocks.Compressor;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

public class NEACompressedItems {
    private NEACompressedItems() {
    }

    // Dust 
    public static final SlimefunItemStack COMPRESSED_IRON_DUST = new SlimefunItemStack("COMPRESSED_IRON_DUST",
    Material.GUNPOWDER,
    "&cCompressed Iron Dust",
    "&7This thing holds 8 iron dust!"
    );

    public static final SlimefunItemStack COMPRESSED_COPPER_DUST = new SlimefunItemStack("COMPRESSED_COPPER_DUST",
    Material.GLOWSTONE_DUST,
    "&cCompressed Copper Dust",
    "&7This thing holds 8 copper dust!"
    );

    public static final SlimefunItemStack COMPRESSED_GOLD_DUST = new SlimefunItemStack("COMPRESSED_GOLD_DUST",
    Material.GLOWSTONE_DUST,
    "&cCompressed Gold Dust",
    "&7This thing holds 8 gold dust!"
    );

    public static final SlimefunItemStack COMPRESSED_LEAD_DUST = new SlimefunItemStack("COMPRESSED_LEAD_DUST",
    Material.GUNPOWDER,
    "&cCompressed Lead Dust",
    "&7This thing holds 8 lead dust!"
    );

    // public static final SlimefunItemStack COMPRESSED_
    
}