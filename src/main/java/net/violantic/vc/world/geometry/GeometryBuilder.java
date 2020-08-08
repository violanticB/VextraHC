package net.violantic.vc.world.geometry;

import org.bukkit.Material;
import org.bukkit.block.BlockState;

import java.util.HashSet;
import java.util.Set;

public class GeometryBuilder {

    private static final int TASK_TICKS = 20 * 20;

    private final WorldGeometry geometry;
    private final Material buildMaterial;
    private final byte data;
    private final Set<BlockState> currentBlocks;

    public GeometryBuilder(WorldGeometry g, Material buildMaterial, byte data) {
        this.geometry = g;
        this.buildMaterial = buildMaterial;
        this.data = data;
        this.currentBlocks = new HashSet<>();
    }

    public WorldGeometry getGeometry() {
        return geometry;
    }

    public Material getBuildMaterial() {
        return buildMaterial;
    }

    public byte getData() {
        return data;
    }

}
