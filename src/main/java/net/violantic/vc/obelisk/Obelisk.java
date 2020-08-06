package net.violantic.vc.obelisk;

import org.bukkit.Location;
import org.bukkit.Material;

public class Obelisk {

    private Location location;
    private Material material;
    private int height;
    private double payoutPerSoul;
    private boolean enabled;

    public Obelisk(Location location, Material material, double payoutPerSoul, int height) {
        this.location = location;
        this.material = material;
        this.payoutPerSoul = payoutPerSoul;
        this.height = height;
        this.enabled = false;
    }

    public Location getLocation() {
        return location;
    }

    public Material getMaterial() {
        return material;
    }

    public int getHeight() {
        return height;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
