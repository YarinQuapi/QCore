package me.yarinlevi.qcore.commands;

import me.yarinlevi.qcore.utils.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

/**
 * @author Quapi
 */
public class ModuleManagementCommands extends Command {
    public ModuleManagementCommands(String name, String description, String usageMessage, List<String> aliases) {
        super(name, description, usageMessage, aliases);
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {

        if (args.length == 0) {
            sender.sendMessage(StringUtils.format(""));
        }

        return false;
    }
}
