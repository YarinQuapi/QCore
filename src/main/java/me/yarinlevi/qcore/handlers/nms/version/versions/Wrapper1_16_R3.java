package me.yarinlevi.qcore.handlers.nms.version.versions;

import me.yarinlevi.qcore.QCore;
import me.yarinlevi.qcore.handlers.nms.version.VersionWrapper;
import org.bukkit.command.Command;
import org.bukkit.craftbukkit.v1_16_R3.CraftServer;

/**
 * @author Quapi
 */
public class Wrapper1_16_R3 implements VersionWrapper {
    private final CraftServer server = (CraftServer) QCore.getInstance().getServer();


    @Override
    public void register(String cmdString, Command command) {
        server.getCommandMap().register(cmdString, command);
    }

    @Override
    public String getVersion() {
        return "1.16.4 / 1.16.5";
    }
}
