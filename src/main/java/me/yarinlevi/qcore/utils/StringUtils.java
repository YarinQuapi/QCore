package me.yarinlevi.qcore.utils;

import net.md_5.bungee.api.ChatColor;

/**
 * @author Quapi
 */
public class StringUtils {
    public static String format(String text, Object... objs) {
        return String.format(ChatColor.translateAlternateColorCodes('&', text), objs);
    }
}
