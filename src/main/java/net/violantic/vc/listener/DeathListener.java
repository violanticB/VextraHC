package net.violantic.vc.listener;

import net.violantic.vc.HardcorePlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class DeathListener implements Listener {

    private HardcorePlugin plugin;

    public DeathListener(HardcorePlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        // Send title
        // Play sound
        // reward killer with money
        // reward global payment
        // take money from player that was killed


        Player killer = event.getEntity().getKiller();

        plugin.getEconomy().depositPlayer(
                killer,
                plugin.getKillerPayment()
        );

        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {

            // send title
            onlinePlayer.playSound(onlinePlayer.getLocation(), Sound.WITHER_DEATH, 1, 1);

            if(onlinePlayer.getUniqueId() == killer.getUniqueId())
                continue;

            plugin.getEconomy().depositPlayer(
                    onlinePlayer,
                    plugin.getGlobalPayment()
            );
        }

        Player player = event.getEntity();
        plugin.getEconomy().withdrawPlayer(
                player,
                plugin.getDeathPrice()
        );

        killer.setDisplayName(ChatColor.GRAY + "[" + ChatColor.RED + "X" + ChatColor.GRAY + "] " + ChatColor.RESET + killer.getName());
        plugin.getServer().getScheduler().runTaskLater(plugin, () -> {
            killer.setDisplayName(killer.getName());
        }, 20 * 60 * 10);

        // TODO give blood token

    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        // TODO temp ban player
    }
}
