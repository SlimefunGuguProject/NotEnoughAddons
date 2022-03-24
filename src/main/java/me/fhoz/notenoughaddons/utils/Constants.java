package me.fhoz.notenoughaddons.utils;

import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Constants {
    public static final List<SlimefunItemStack> dusts = new ArrayList<>(Arrays.asList(
        SlimefunItems.COPPER_DUST, SlimefunItems.GOLD_DUST, SlimefunItems.IRON_DUST,
        SlimefunItems.LEAD_DUST, SlimefunItems.ALUMINUM_DUST, SlimefunItems.ZINC_DUST,
        SlimefunItems.TIN_DUST, SlimefunItems.MAGNESIUM_DUST, SlimefunItems.SILVER_DUST
    ));

    public static final int MAX_STACK_SIZE = 64;

    private Constants() {}

}
