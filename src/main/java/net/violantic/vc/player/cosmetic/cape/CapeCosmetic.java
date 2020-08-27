package net.violantic.vc.player.cosmetic.cape;

import net.violantic.vc.player.cosmetic.Cosmetic;
import net.violantic.vc.player.cosmetic.CosmeticTask;

import java.util.UUID;

/**
 * @author Ethan Borawski
 */
public class CapeCosmetic implements Cosmetic {

    private UUID uuid;
    private CapeTask task;

    public CapeCosmetic(UUID uuid, CapeDesign design) {
        this.uuid = uuid;
        this.task = new CapeTask(
                uuid,
                presetCloakDesign(design)
        );
    }

    @Override
    public long tickFrequency() {
        return 2;
    }

    @Override
    public boolean showsWhileRunning() {
        return false;
    }

    @Override
    public CosmeticTask getTask() {
        return task;
    }

    public enum CapeDesign {
        OBSIDIAN_CAPE,
        VIOLANTIC_CAPE,

        WOOD_SKILLCAPE
    }

    public static int[][] presetCloakDesign(CapeDesign design) {
        switch (design) {
            case OBSIDIAN_CAPE:
                return new int[][]{
                        {13, -1, -1, -1, 13},
                        {13, 13, 13, 13, 13},
                        {13, 13, 13, 13, 13},
                        {13, 13, 13, 13, 13},
                        {13, 13, 13, 13, 13},
                        {12, 12, 12, 12, 12},
                        {12, 12, 12, 12, 12}
                };
            case VIOLANTIC_CAPE:
                return new int[][]{
                        {7 , -1, -1, -1, 7 },
                        {7 , 7 , 7 , 7 , 7 },
                        {7 , 12, 7 , 12, 7 },
                        {7 , 12, 12, 12, 7 },
                        {7 , 7 , 12, 7 , 7 },
                        {7 , 7 , 7 , 7 , 7 },
                        {7 , 7 , 7 , 7 , 7 }
                };
            case WOOD_SKILLCAPE:
                return new int[][] {
                        {14 , -1, -1, -1, 14},
                        {14 , 14, 14, 14, 14},
                        {14 , 11, 11, 11, 14},
                        {14 , 11, 11, 11, 14},
                        {14 , 14, 15, 14, 14},
                        {14 , 14, 15, 14, 14},
                        {14 , 14, 14, 14, 14}
                };
        }

        return null;
    }
}
