package net.violantic.vc.obelisk.animation;

import net.violantic.vc.HardcorePlugin;
import net.violantic.vc.util.ParticleUtil;
import net.violantic.vc.world.geometry.WorldGeometry;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.concurrent.ThreadLocalRandom;

public class MagicRainTask implements Runnable {

    private static final int PROJECTILE_HEIGHT_A = 10;
    private static final int PROJECTILE_HEIGHT_B = 6;
    private static final int PROJECTILE_WAVE_AMT = 10;
    private static final float PROJECTILE_OFFSET = 1.5f;
    private static final double PROJECTILE_DENSITY = 0.15;

    private int projectileWavesPerCycle;
    private WorldGeometry geometry;

    public MagicRainTask(int projectileWavesPerCycle, WorldGeometry geometry) {
        this.projectileWavesPerCycle = projectileWavesPerCycle;
        this.geometry = geometry;
    }

    public double[] getRandom() {
        return new double[] {
                ThreadLocalRandom.current().nextDouble(geometry.minimumX(), geometry.maximumX()),
                ThreadLocalRandom.current().nextDouble(geometry.minimumZ(), geometry.maximumZ())
        };
    }

    private boolean checkCollision(Block block) {
        switch(block.getType()) {
            case GRASS:
                block.setType(Material.MYCELIUM);
                break;
            case SAND:
                block.setType(Material.GRAVEL);
                break;
            case DIRT:
                block.setType(Material.SOUL_SAND);
                break;
        }

        return block.getType() != Material.AIR;
    }

    public void run() {
        for (int i = 0; i < projectileWavesPerCycle; i++) {

            for (int j = 0; j < 3; j++) {

                long delayOffset = ThreadLocalRandom.current().nextLong(30);
                new BukkitRunnable() {

                    final double height = ThreadLocalRandom.current().nextDouble(PROJECTILE_HEIGHT_B, PROJECTILE_HEIGHT_A);
                    final double y0 = geometry.getCenter().getY();
                    final double[] a = getRandom();
                    final Location from =  new Location(geometry.getCenter().getWorld(), a[0], y0 + height, a[1]);
                    // Shoot particles in line using vector calculation
                    // d = v1 - v0
                    Vector direction = null;
                    final double velocity = 0.60;
                    double distance = 0;

                    public void run() {
                        direction = new Vector(0, -distance, 0);
                        Location current = from.clone().add(direction);

                        if(distance >= height || checkCollision(current.getBlock())) {
                            cancel();
//                            from.getWorld().playEffect(from.clone().add(direction).add(0, 0.5, 0), Effect.POTION_BREAK, 1);
                            return;
                        }

                        int random = ThreadLocalRandom.current().nextInt(0, 2);

                        Color color = random % 2 == 0 ? Color.FUCHSIA : Color.TEAL;
                        for(double x = 0; x < velocity; x += PROJECTILE_DENSITY) {
                            ParticleUtil.displayParticle(from.clone().add(direction.subtract(new Vector(0, x, 0))), color);
                        }

                        distance += velocity;
                    }

                }.runTaskTimer(HardcorePlugin.getPlugin(), delayOffset, 1);
            }
        }
    }

}
