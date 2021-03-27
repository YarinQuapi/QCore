package me.yarinlevi.qcore.handlers.nms.version;

import org.bukkit.command.Command;

/**
 * @author Quapi
 */
public interface VersionWrapper {
    void register(String cmdString, Command command);

    String getVersion();
}
