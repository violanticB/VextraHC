package net.violantic.vc.player.cosmetic;

/**
 * @author Ethan Borawski
 */
public interface Cosmetic {
    long tickFrequency();
    boolean showsWhileRunning();
    CosmeticTask getTask();
}
