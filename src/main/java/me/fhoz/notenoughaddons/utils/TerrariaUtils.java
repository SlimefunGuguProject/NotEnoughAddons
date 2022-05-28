package me.fhoz.notenoughaddons.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public final class TerrariaUtils {
    private TerrariaUtils() {
    }

    public static Vector fastNormalize(Vector v) {
        float length = fastLength(v);
        v.multiply(length);

        return v;
    }

    public static float fastLength(Vector v) {
        double x = v.getX();
        double y = v.getY();
        double z = v.getZ();

        return fastSqrt(x * x + y * y + z * z);
    }

    public static float fastSqrt(double double_num) {
        int i;
        float x2, y;
        float threehalfs = 1.5F;
        float num = (float) double_num;

        x2 = num * 0.5F;
        y = num;
        i = Float.floatToIntBits(y);
        i = 0x5f3759df - (i >> 1);
        y = Float.intBitsToFloat(i);
        y = y * (threehalfs - (x2 * y * y));

        return y;
    }


    public static Vector knockback(Location l1, Location l2, double k) {
        Vector ray = new Vector(l1.getX() - l2.getX(), l1.getY() - l2.getY(), l1.getZ() - l2.getZ());
        Vector kbVector = fastNormalize(ray).multiply(k / 6);
        return kbVector;
    }

    public static void castDamage(EntityDamageByEntityEvent e, Player p, ItemStack item, double critChance, double dmg, double knockback, int useTime) {
        Material mat = item.getType();
        if (p.hasCooldown(mat)) {
            e.setDamage(0);
            e.setCancelled(true);
            return;
        }
        int TICK_DELAY = Math.floorDiv(useTime, 60) * 20;
        Boolean isCrit = false;
        double d = Math.random();
        Location eLoc = e.getEntity().getLocation();
        Location pLoc = p.getLocation();
        if (d < critChance) {
            isCrit = true;
        }
        dmg = (ThreadLocalRandom.current().nextDouble(1.15 - 0.85) + 0.85) * dmg;
        e.setDamage(dmg * (isCrit ? 2 : 1));
        e.getEntity().setVelocity(knockback(eLoc, pLoc, knockback).multiply(isCrit ? 1.4 : 1));
        p.setCooldown(mat, TICK_DELAY);
    }

    public static String useTimeConv(int t) {
        if (t <= 8) {
            return "&f超快";
        } else if (9 <= t && t <= 20) {
            return "&f很快";
        } else if (21 <= t && t <= 25) {
            return "&f快";
        } else if (26 <= t && t <= 30) {
            return "&f普通";
        } else if (31 <= t && t <= 35) {
            return "&f慢";
        } else if (36 <= t && t <= 45) {
            return "&f很慢";
        } else if (46 <= t && t <= 55) {
            return "&f极慢";
        } else {
            return "&f蜗牛";
        }
    }

    public static String kbConv(double k) {
        if (k == 0) {
            return "&f无击退力";
        } else if (k <= 1.5) {
            return "&f极弱击退力";
        } else if (k <= 3) {
            return "&f很弱击退力";
        } else if (k <= 4) {
            return "&f较弱击退力";
        } else if (k <= 6) {
            return "&f普通击退力";
        } else if (k <= 7) {
            return "&f较强击退力";
        } else if (k <= 9) {
            return "&f很强击退力";
        } else if (k <= 11) {
            return "&f极强击退力";
        } else if (k > 11) {
            return "&f疯狂击退力";
        }
        return "&f未知击退力";
    }

    public static String getDMG(double d) {

        return "&f" + (int) d + " 近战伤害";
    }

    public static String getCC(double c) {
        return "&f" + (int) c * 100 + "% 暴击率";
    }
}

