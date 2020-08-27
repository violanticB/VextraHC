package net.violantic.vc.player.cosmetic.cape;

import net.violantic.vc.player.cosmetic.CosmeticTask;
import net.violantic.vc.util.ParticleUtil;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.util.Vector;

import java.util.UUID;

/**
 * @author Ethan Borawski
 */
public class CapeTask implements CosmeticTask {

    private UUID uuid;
    private int taskId;
    private int[][] capeMatrix;

    private Location baseLocation;

    public CapeTask(UUID uuid, int[][] capeMatrix) {
        this.uuid = uuid;
        this.capeMatrix = capeMatrix;
        baseLocation = Bukkit.getPlayer(uuid).getLocation();
    }

    @Override
    public int getTaskId() {
        return taskId;
    }

    @Override
    public void setTaskId(int id) {
        this.taskId = id;
    }

    @Override
    public void run() {
        baseLocation = Bukkit.getPlayer(uuid).getLocation();
        baseLocation.setPitch(0);

        double angle = angleFromDirection(baseLocation.getDirection());
        double right = angle + (Math.PI / 2);
        right = right > Math.PI ? right - 2 * Math.PI : right;

        Vector vright = new Vector(Math.cos(right), 0, Math.sin(right));
        Location topLoc = baseLocation.add(0, 1.4D, 0).add(vright.clone().multiply(0.3)).add(baseLocation.getDirection().multiply(-0.25));
        int t = 0;

        Location ploc = null;
        for (int j = 0; j < 6; j++) {
            for (int i = 0; i < 5; i++) {
                if(capeMatrix[j][i] == -1)
                    continue;

                ploc = topLoc.clone().add(baseLocation.getDirection().multiply(-0.1 * t));
                ploc = ploc.subtract(vright.clone().multiply(0.17 * i));
                ploc = ploc.subtract(0, 0.17 * j, 0);

                ParticleUtil.displayParticle(ploc, getColor(capeMatrix[j][i]));
            }

            t++;
        }
    }

    public static double angleFromDirection(Vector v1) {
        Vector v1n = new Vector(v1.getX(), 0, v1.getZ()).normalize();
        double angle = 0;
        angle = Math.acos(Math.abs(v1n.getX()));
        if (v1n.getX() >= 0) {
            if (v1n.getZ() >= 0) {

            } else {
                angle = Math.PI * 2 - angle;
            }
        } else {
            if (v1n.getZ() >= 0) {
                angle = Math.PI - angle;
            } else {
                angle = Math.PI + angle;
            }
        }

        return angle;
    }

    private Color getColor(int i) {
        switch(i) {
            case 0: return Color.WHITE;
            case 1: return Color.ORANGE;
            case 2: return Color.FUCHSIA;
            case 3: return Color.AQUA;
            case 4: return Color.YELLOW;
            case 5: return Color.LIME;
            case 6: return Color.fromRGB(255, 105, 180);
            case 7: return Color.GRAY;
            case 8: return Color.TEAL;
            case 9: return Color.PURPLE;
            case 10: return Color.BLUE;
            case 11: return Color.GREEN;
            case 12: return Color.RED;
            case 13: return Color.BLACK;
            case 14: return Color.fromRGB(210,180,140);
            case 15: return Color.fromRGB(139,69,19);
        }

        return null;
    }

}
