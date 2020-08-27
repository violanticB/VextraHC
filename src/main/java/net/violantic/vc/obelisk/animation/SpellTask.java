package net.violantic.vc.obelisk.animation;

import net.violantic.vc.HardcorePlugin;
import net.violantic.vc.util.ParticleUtil;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class SpellTask implements Runnable {

    private Player mage;
    private HardcorePlugin plugin;
    private double length = 50;

    public SpellTask(HardcorePlugin plugin, Player mage) {
        this.plugin = plugin;
        this.mage = mage;
    }

    public void run() {
        Vector direction = mage.getLocation().getDirection().normalize().clone();
        Location start = mage.getLocation().add(0,1.5,0).clone();
        Location oppositeStrand = start.clone();

        /* Velocity is distance / time, in our case time is in ticks.
         * So we can define velocity as 1.2 meters per tick*/

        int id = new BukkitRunnable() {

            double velocity = 0.15;
            double gravityAcceleration = 0.0;
            double particleDensity = 0.00375;
            double netDistance = 0.0;
            double oscillationAngle = 0;

            @Override
            public void run() {
                if(netDistance >= length) {
                    cancel();
                    return;
                }

                netDistance += velocity;
                Vector gravity = new Vector(0, gravityAcceleration, 0);
                Vector projectileMotion = direction.clone().multiply(netDistance).subtract(gravity);
                mage.playSound(start.clone().add(projectileMotion), Sound.ITEM_FIRECHARGE_USE, 1, 1);

                gravityAcceleration += 0.035;

                for(double x = 0; x < velocity; x += particleDensity) {
                    for (Entity entity : start.getWorld().getNearbyEntities(start, particleDensity, particleDensity, particleDensity)) {
                        if(entity == mage)
                            continue;

                        entity.sendMessage("You've been hit");

                        if(entity instanceof LivingEntity) {
                            ((LivingEntity) entity).damage(5D);
                        }
                    }

                    start.add(projectileMotion.clone().normalize().add(new Vector(0, 0.05 * Math.sin(oscillationAngle), 0)).multiply(x));
                    ParticleUtil.displayParticle(start, Color.FUCHSIA);

                    oppositeStrand.add(projectileMotion.clone().normalize().add(new Vector(0, -0.05 * Math.sin(oscillationAngle), 0)).multiply(x));
                    ParticleUtil.displayParticle(start, Color.TEAL);
                }

                oscillationAngle += Math.PI / 2;
//                velocity += acceleration;
            }
        }.runTaskTimerAsynchronously(plugin, 0, 1).getTaskId();
    }

}
