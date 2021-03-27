package me.yarinlevi.qcore.filemanagement;

import me.clip.placeholderapi.PlaceholderAPI;
import me.yarinlevi.qcore.QCore;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Quapi
 */
public class Messages {
    private final QCore instance;

    private final FileConfiguration messagesData;

    private final Map<String, String> messages = new HashMap<>();

    public Messages(QCore instance) {
        this.instance = instance;

        this.instance.saveResource("messages.yml", false);
        messagesData = YamlConfiguration.loadConfiguration(new InputStreamReader(this.instance.getResource("messages.yml")));

        messagesData.getKeys(false).forEach(key -> {
            messages.put(key, messagesData.getString(key));
        });
    }

    public String getMessage(String key, Object... args) {
        return String.format(ChatColor.translateAlternateColorCodes('&', messages.get(key)), args);
    }

    public String setPlaceholders(Player player, String message) {
        return PlaceholderAPI.setPlaceholders(player, message);
    }
}
