package me.yarinlevi.qcore.api;

import me.yarinlevi.qcore.internalapi.QApi;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.InputStreamReader;

/**
 * @author Quapi
 */
public class BaseModule {
    private QApi api;
    private final YamlConfiguration configuration = YamlConfiguration.loadConfiguration(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("extension.yml")));

    public void onEnable(QApi api) {
        this.api = api;
    }

    public void onDisable() {

    }

    public String getExtensionName() {
        return configuration.getString("name");
    }

    public String getExtensionVersion() {
        return configuration.getString("version");
    }
}
