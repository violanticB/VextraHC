package net.violantic.vc;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public enum Messages {

    ENGLISH_PLAYERKILL(HardcorePlugin.PREFIX + "&7You have killed &e%player%"),
    ENGLISH_OBELISK_USE(HardcorePlugin.PREFIX + "&7You have traded &d%tokens% &cBlood Tokens &7for &a$%money%");

    String message;
    Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String replacePlayer(String key, Player player) {
        return message.replace(key, player.getName());
    }

    public String replaceString(String key, String value) {
        return message.replace(key, value);
    }

    public void send(Player player) {
        player.sendMessage(message);
    }

    public void send(Player player, String... values) {
        String temp = message;
        for (String value : values) {
            String[] valToken = value.split("_");
            temp.replace(valToken[0], valToken[1]);
        }

        player.sendMessage(message);
    }

    public void sendAll(String... values) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            send(player, values);
        }
    }


}
