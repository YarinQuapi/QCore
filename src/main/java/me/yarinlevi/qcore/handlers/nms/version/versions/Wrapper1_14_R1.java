package me.yarinlevi.qcore.handlers.nms.version.versions;

import me.yarinlevi.qcore.QCore;
import me.yarinlevi.qcore.handlers.nms.version.VersionWrapper;
import org.bukkit.command.Command;
import org.bukkit.craftbukkit.v1_14_R1.CraftServer;

/**
 * @author Quapi
 */
public class Wrapper1_14_R1 implements VersionWrapper {
    private final CraftServer server = (CraftServer) QCore.getInstance().getServer();

    @Override
    public void register(String cmdString, Command command) {
        server.getCommandMap().register(cmdString, command);
    }

    @Override
    public String getVersion() {
        return "1.14.4";
    }
}
