package net.violantic.vc.obelisk.animation;

import net.violantic.vc.obelisk.Obelisk;
import org.bukkit.World;

public class ObeliskBuildTask implements Runnable {

    private final int TICK_INTERVAL = 5;

    private Obelisk obelisk;
    private World world;
    private int yIndex = 0;

    public ObeliskBuildTask(Obelisk obelisk) {
        this.obelisk = obelisk;
        this.world = obelisk.getLocation().getWorld();
    }

    public int getInterval() {
        return TICK_INTERVAL;
    }

    public void run() {
        if(yIndex >= obelisk.getHeight())
            return;

        world.getBlockAt(obelisk.getLocation().clone().add(0, yIndex++, 0))
                .setType(obelisk.getMaterial());
    }
}
