package me.yarinlevi.qcore.handlers.nms.commands;

import lombok.Getter;
import me.yarinlevi.qcore.handlers.nms.version.VersionMatcher;
import me.yarinlevi.qcore.handlers.nms.version.VersionWrapper;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;

import java.util.Arrays;

/**
 * @author Quapi
 */
public class CommandHandler {
    @Getter private final VersionWrapper versionWrapper;


    public CommandHandler() {
        versionWrapper = new VersionMatcher().match();

        Bukkit.getLogger().info("This server is running supported version: " + versionWrapper.getVersion() + "!");

        String fallbackPrefix = "qutils"; // eg: "/qutils:msg", "/qutils:r" ("qutils" is fallbackPrefix, "msg" or "r" is the command)

        /*

        this.register(fallbackPrefix, new ToggleFlyCommand("fly"), "qutils.cmd.fly", new String[] {"togglefly"});
        this.register(fallbackPrefix, new TeleportCommand("tp"), "qutils.cmd.tp", new String[] {"teleport"});


        this.register(fallbackPrefix, new MessageCommand("msg"), "qutils.cmd.message", new String[]{"message", "dm", "pm", "privatemessage"});
        this.register(fallbackPrefix, new ReplyCommand("r"), "qutils.cmd.reply", new String[] {});
        this.register(fallbackPrefix, new ToggleMessageCommand("togglemsg"),"qutils.cmd.togglemsg", new String[]{});
        */
    }

    public void register(String cmdString, Command command, String... aliases) {
        if (aliases != null) {
            command.setAliases(Arrays.asList(aliases));
        }

        versionWrapper.register(cmdString, command);
    }

    public void register(String cmdString, Command command, String permission, String... aliases) {
        command.setPermission(permission);

        if (aliases != null) {
            command.setAliases(Arrays.asList(aliases));
        }

        versionWrapper.register(cmdString, command);
    }

    public void register(String cmdString, Command command, String permission, String description, String... aliases) {
        command.setPermission(permission);
        command.setDescription(description);

        if (aliases != null) {
            command.setAliases(Arrays.asList(aliases));
        }

        versionWrapper.register(cmdString, command);
    }
}
