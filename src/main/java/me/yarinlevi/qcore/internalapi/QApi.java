package me.yarinlevi.qcore.internalapi;

import lombok.Getter;
import me.yarinlevi.qcore.QCore;
import org.bukkit.command.Command;
import org.bukkit.event.Listener;

/**
 * @author Quapi
 */
public class QApi {
    @Getter private final QCore plugin;

    public QApi(QCore instance) {
        this.plugin = instance;
    }

    /**
     * Register a command
     * @param cmd The command's name
     * @param command The command's class
     * @param aliases Aliases of the command
     */
    public void registerCommand(String cmd, Command command, String... aliases) {
        QCore.getInstance().getCommandHandler().register(cmd, command, aliases);
    }

    /**
     * Register a command
     * @param cmd The command's name
     * @param command The command's class
     * @param permission The permission required to execute the command
     * @param aliases Aliases of the command
     */
    public void registerCommand(String cmd, Command command, String permission, String... aliases) {
        QCore.getInstance().getCommandHandler().register(cmd, command, permission, aliases);
    }

    /**
     * Register a command
     * @param cmd The command's name
     * @param command The command's class
     * @param description The command's description
     * @param permission The permission required to execute the command
     * @param aliases Aliases of the command
     */
    public void registerCommand(String cmd, Command command, String description, String permission, String... aliases) {
        QCore.getInstance().getCommandHandler().register(cmd, command, permission, description, aliases);
    }

    /**
     * Register a listener as QUtils
     * @param listener The listener class you want to register
     */
    public void registerListener(Listener listener) {
        QCore.getInstance().getServer().getPluginManager().registerEvents(listener, QCore.getInstance());
    }
}
