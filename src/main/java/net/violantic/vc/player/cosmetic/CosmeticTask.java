package net.violantic.vc.player.cosmetic;

/**
 * @author Ethan Borawski
 */
public interface CosmeticTask extends Runnable {
    int getTaskId();
    void setTaskId(int id);
    void run();
}
