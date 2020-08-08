package net.violantic.vc.obelisk.animation;

import net.violantic.vc.obelisk.Obelisk;
import net.violantic.vc.util.VectorUtil;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.util.Vector;
import org.inventivetalent.particle.ParticleEffect;

import java.util.List;
import java.util.Set;

public class ObeliskParticleTask implements Runnable {

    private Obelisk obelisk;
    private Color ringColor;

    // Epicycloid variables
    private double a;
    private double b;
    private double alpha;
    private double phi;
    private double spacing;

    // Sphere variables
    private double radius;
    private List<Vector> sphereVectors;

    public ObeliskParticleTask(Obelisk obelisk, Color ringColor,
                               double a, double b, double spacing) {
        this.obelisk = obelisk;
        this.ringColor = ringColor;
        this.a = a;
        this.b = b;
        this.spacing = spacing;
        this.sphereVectors = VectorUtil.getSphericalVectors(0);
    }

    public void run() {
        for(double theta = 0; theta < 360; theta += 2) {
            double x = ((a + b) * Math.cos(Math.toRadians(theta))
                    - b * Math.cos((a / b + 1.0) * Math.toRadians(theta)) + .5);
            double z = ((a + b) * Math.sin(Math.toRadians(theta))
                    - b * Math.sin((a / b + 1.0) * Math.toRadians(theta)) + .5);

            Vector direction = new Vector(x, Math.sin(phi), z);

            ParticleEffect.REDSTONE.sendColor(
                    Bukkit.getOnlinePlayers(),
                    obelisk.getLocation().clone()
                            .add(0, 0.25, 0)
                            .add(VectorUtil.rotateAroundAxisY(direction, alpha)),
                    ringColor
            );

            phi += Math.PI / 6;
        }

        phi += Math.PI / 96;
        alpha += Math.PI / 96;

        int gain = 255;
        for (Vector sphereVector : sphereVectors) {
            ParticleEffect.REDSTONE.sendColor(
                    Bukkit.getOnlinePlayers(),
                    obelisk.getLocation().clone()
                            .add(0, 8, 0)
                            .add(sphereVector)
                            .add(0, Math.sin(alpha), 0),
                    Color.fromRGB(255, gain--, 255)
            );
        }
    }
}
