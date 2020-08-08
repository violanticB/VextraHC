package net.violantic.vc.listener;

import net.violantic.vc.HardcorePlugin;
import net.violantic.vc.obelisk.animation.SpellTask;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class ObeliskListener implements Listener {

    private HardcorePlugin plugin;

    public ObeliskListener(HardcorePlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {

        if(event.getPlayer().getItemInHand().getType().equals(Material.BLAZE_POWDER)) {
            new SpellTask(plugin, event.getPlayer()).run();
        }

//        if(plugin.getObeliskManager().isObelisk(event.getClickedBlock())) {
//
//            // TODO handle blood tokens
//
//        }

    }
}
