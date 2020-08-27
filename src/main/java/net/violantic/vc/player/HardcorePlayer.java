package net.violantic.vc.player;

import net.violantic.vc.HardcorePlugin;
import net.violantic.vc.player.cosmetic.Cosmetic;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Ethan Borawski
 */
public class HardcorePlayer {

    public static Map<UUID, HardcorePlayer> playerCollection = new ConcurrentHashMap<>();

    private int id_;
    private UUID uuid;
    private boolean skulled;
    private int kills;
    private Set<Integer> activeCosmetics;

    public HardcorePlayer(int id_, UUID uuid) {
        this.id_ = id_;
        this.uuid = uuid;
        this.activeCosmetics = new HashSet<>();
    }

    public int getId_() {
        return id_;
    }

    public UUID getUuid() {
        return uuid;
    }

    public boolean isSkulled() {
        return skulled;
    }

    public void setSkulled(boolean skulled) {
        this.skulled = skulled;
    }

    public int getKills() {
        return kills;
    }

    public Set<Integer> getActiveCosmetics() {
        return activeCosmetics;
    }

    public void playCosmetic(Cosmetic cosmetic){
        BukkitScheduler scheduler = HardcorePlugin.getPlugin().getServer().getScheduler();

        int taskId = scheduler.runTaskTimer(
                HardcorePlugin.getPlugin(), cosmetic.getTask(), 0, cosmetic.tickFrequency()
        ).getTaskId();

        cosmetic.getTask().setTaskId(taskId);
        activeCosmetics.add(taskId);
    }

    public static HardcorePlayer getPlayer(UUID uuid) {
        if(playerCollection.containsKey(uuid)) {
            return playerCollection.get(uuid);

        } else {
            HardcorePlayer player = new HardcorePlayer(-1, uuid);
            playerCollection.put(uuid, player);

            return player;
        }
    }

}
