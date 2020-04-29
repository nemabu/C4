package main.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

public class PlayerLoginListener implements Listener {

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        Player player = event.getPlayer();

        boolean playerAlreadyOnHashMap = false;
        for (Map.Entry<UUID, ArrayList<String>> entry : main.C4.mapOfC4.entrySet()) {
            try {
                if (player.getUniqueId().equals(entry.getKey())) {
                    playerAlreadyOnHashMap = true;

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        if (playerAlreadyOnHashMap == false) {
            main.C4.addPlayer(player.getUniqueId());
        }

    }

}
