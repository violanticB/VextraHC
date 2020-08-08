package net.violantic.vc.world.geometry.shapes;

import net.violantic.vc.world.geometry.WorldGeometry;
import org.bukkit.Location;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class SquareWorldGeometry implements WorldGeometry {

    private double size;
    private Location center;
    private final double x0, z0, x1, z1;
    private List<Vector> points;

    public SquareWorldGeometry(Location center, double size) {
        this.size = size;
        this.center = center.add(0.5, 0, 0.5);
        this.points = getSquareVectors(size);

        double r = size / 2;
        this.x0 = center.getX() - r;
        this.z0 = center.getZ() + r;
        this.x1 = center.getX() + r;
        this.z1 = center.getZ() - r;
    }

    public SquareWorldGeometry(double x0, double z0, double x1, double z1) {
        this.x0 = x0;
        this.z0 = z0;
        this.x1 = x1;
        this.z1 = z1;
    }

    public double getSize() {
        return size;
    }

    @Override
    public Location getCenter() {
        return center;
    }

    @Override
    public List<Vector> getPoints() {
        return points;
    }

    @Override
    public boolean isInside(Location location) {
        double x = location.getX();
        double z = location.getZ();

        return ((x0 <= x && x < x1) && (z0 <= z && z < z1));
    }

    @Override
    public double minimumX() {
        return x0;
    }

    @Override
    public double minimumZ() {
        return z1;
    }

    @Override
    public double maximumX() {
        return x1;
    }

    @Override
    public double maximumZ() {
        return z0;
    }

    public List<Vector> getSquareVectors(double size) {
        double r = size / 2;

        Vector v1 = new Vector(-r, 0, r);
        Vector v2 = new Vector(r, 0, -r);

        List<Vector> result = new ArrayList<Vector>();
        for (int x = v1.getBlockX(); x <= v2.getBlockX(); x++) {
            result.add(new Vector(x, v1.getBlockY(), v1.getBlockZ()));
            result.add(new Vector(x, v1.getBlockY(), v2.getBlockZ()));
            result.add(new Vector(x, v2.getBlockY(), v1.getBlockZ()));
            result.add(new Vector(x, v2.getBlockY(), v2.getBlockZ()));
        }

        for (int z = v1.getBlockZ(); z < v2.getBlockZ(); z++) {
            result.add(new Vector(v1.getBlockX(), v1.getBlockY(), z));
            result.add(new Vector(v1.getBlockX(), v2.getBlockY(), z));
            result.add(new Vector(v2.getBlockX(), v1.getBlockY(), z));
            result.add(new Vector(v2.getBlockX(), v2.getBlockY(), z));
        }

        return result;
    }
}
