package net.violantic.vc.command;

import net.violantic.vc.HardcorePlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SetObeliskCommand implements CommandExecutor {

    private HardcorePlugin plugin;

    public SetObeliskCommand(HardcorePlugin plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender commandSender, Command command,
                             String label, String[] args) {

        if(!commandSender.isOp())
            commandSender.sendMessage(ChatColor.RED + "You don't have permission!");

        if(args.length == 3) {

            double x = Double.parseDouble(args[0]);
            double y = Double.parseDouble(args[1]);
            double z = Double.parseDouble(args[2]);

            plugin.updateObelisk(x, y, z);
        }

        return false;
    }
}
