package me.yarinlevi.qcore;

import lombok.Getter;
import me.yarinlevi.qcore.api.BaseModule;
import me.yarinlevi.qcore.filemanagement.Messages;
import me.yarinlevi.qcore.handlers.nms.commands.CommandHandler;
import me.yarinlevi.qcore.internalapi.QApi;
import me.yarinlevi.qcore.modules.ModuleRegistration;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Quapi
 */
public final class QCore extends JavaPlugin {
    @Getter private static QCore instance;

    @Getter private Messages messages;
    @Getter private CommandHandler commandHandler;

    @Getter private QApi api;
    @Getter private ModuleRegistration quantumRegistration;

    @Override
    public void onEnable() {
        instance = this;

        this.messages = new Messages(instance);
        this.commandHandler = new CommandHandler();


        // LOAD LAST
        this.api = new QApi(instance);
        this.quantumRegistration = new ModuleRegistration();

        this.quantumRegistration.loadAll(instance); // Load all modules

        this.quantumRegistration.enableAll(); // Enable all loaded modules
    }

    @Override
    public void onDisable() {
        // Disabling all loaded & enabled modules
        this.quantumRegistration.getModuleList().forEach(BaseModule::onDisable);
    }
}
