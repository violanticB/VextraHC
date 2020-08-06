package net.violantic.vc.obelisk;

import net.violantic.vc.obelisk.animation.ObeliskBuildTask;
import net.violantic.vc.obelisk.animation.ObeliskParticleTask;
import org.bukkit.Color;
import org.bukkit.block.Block;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.HashSet;
import java.util.Set;

public class ObeliskManager {

    private JavaPlugin javaPlugin;
    private Set<Obelisk> obeliskSet;
    private int particleTaskId;

    public ObeliskManager(JavaPlugin javaPlugin) {
        this.javaPlugin = javaPlugin;
        this.obeliskSet = new HashSet<Obelisk>();
    }

    public JavaPlugin getJavaPlugin() {
        return javaPlugin;
    }

    public Set<Obelisk> getObeliskSet() {
        return obeliskSet;
    }

    public boolean register(Obelisk obelisk) {
        constructObelisk(obelisk);
        return obeliskSet.add(obelisk);
    }

    public boolean isObelisk(Block block) {

        double x0 = block.getLocation().getBlockX();
        double z0 = block.getLocation().getBlockZ();
        double y0 = block.getLocation().getBlockY();

        for (Obelisk obelisk : obeliskSet) {
            double x1 = obelisk.getLocation().getBlockX();
            double z1 = obelisk.getLocation().getBlockZ();
            double y1 = obelisk.getLocation().getBlockY();

            return ((x0 == x1 && z0 == z1) && (y0 >= y1 && y0 <= (y1 + obelisk.getHeight())));
        }

        return false;

    }

    public void constructObelisk(Obelisk obelisk) {
        BukkitScheduler scheduler = javaPlugin.getServer().getScheduler();

        ObeliskBuildTask buildTask = new ObeliskBuildTask(obelisk);
        ObeliskParticleTask particleTask = new ObeliskParticleTask(
                obelisk,
                Color.TEAL,
                2,
                0.67,
                50
        );

        scheduler.runTaskTimer(javaPlugin, buildTask, 0, buildTask.getInterval());
        particleTaskId = scheduler.runTaskTimerAsynchronously(
                javaPlugin,
                particleTask,
                20, 1
        ).getTaskId();
    }
}
