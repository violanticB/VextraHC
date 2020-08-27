package net.violantic.vc.command;

import net.violantic.vc.player.HardcorePlayer;
import net.violantic.vc.player.cosmetic.cape.CapeCosmetic;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author Ethan Borawski
 */
public class CapeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player player = (Player) commandSender;
        HardcorePlayer hPlayer = HardcorePlayer.getPlayer(player.getUniqueId());

        if(!hPlayer.getActiveCosmetics().isEmpty()) {
            for (Integer activeCosmetic : hPlayer.getActiveCosmetics()) {
                Bukkit.getServer().getScheduler().cancelTask(activeCosmetic);
            }
        }

        hPlayer.playCosmetic(new CapeCosmetic(player.getUniqueId(), CapeCosmetic.CapeDesign.valueOf(
                args[0]
        )));

        return false;
    }
}
