package main.listeners;

import main.C4;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

public class InventoryClickListener implements Listener {

    C4 mainClass;

    public InventoryClickListener(C4 mainClass) {
        this.mainClass = mainClass;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {

        int radius = mainClass.getConfig().getInt("radius");
        String outOfRangeSingleDetonation = mainClass.getConfig().getString("outOfRangeSingleDetonation");
        String outOfRangeMultipleDetonation = mainClass.getConfig().getString("outOfRangeMultipleDetonation");

        Player player = (Player) event.getWhoClicked();
        if (event.getInventory().getTitle().equals("C4 Detonator")) {
            event.setCancelled(true);
            int whichTNT = event.getRawSlot();

            for (Map.Entry<UUID, ArrayList<String>> entry : main.C4.mapOfC4.entrySet()) {
                if (Bukkit.getPlayer(entry.getKey()).getName().equals(player.getName())) { //Test

                    if (whichTNT != 8) {
                        try {
                            Location detonationLocation = main.C4.getLocationFromString(entry.getValue().get(whichTNT));

                            if (player.getLocation().distance(detonationLocation) < radius) {

                                detonationLocation.getBlock().setType(Material.AIR);
                                detonationLocation.getBlock().breakNaturally();
                                player.getWorld().spawnEntity(detonationLocation, EntityType.PRIMED_TNT);

                                main.C4.removeTNTLocation(detonationLocation, player);

                            } else {
                                player.sendMessage(ChatColor.RED + outOfRangeSingleDetonation + " (" + (whichTNT + 1) + ").");
                            }

                        } catch (Exception e) {
                            //Do nothing
                        }

                    }

                    if (whichTNT == 8) {
                        boolean allAreWithinRange = true;

                        for (int i = 0; i < entry.getValue().size(); i++) {
                            Location detonationLocation = main.C4.getLocationFromString(entry.getValue().get(i));


                            if (player.getLocation().distance(detonationLocation) > radius) {
                                allAreWithinRange = false;
                                player.sendMessage(ChatColor.RED + outOfRangeMultipleDetonation + " (" + (i+1) + ").");

                             /* detonationLocation.getBlock().setType(Material.AIR);
                                player.getWorld().spawnEntity(detonationLocation, EntityType.PRIMED_TNT);

                                main.C4.removeTNTLocation(detonationLocation, player);
                               i--;  */

                            } else {
                                //Do nothing
                            }

                        }

                        if (allAreWithinRange == true) {
                            for (int i = 0; i < entry.getValue().size(); i++) {
                                Location detonationLocation = main.C4.getLocationFromString(entry.getValue().get(i));
                                detonationLocation.getBlock().setType(Material.AIR);
                                player.getWorld().spawnEntity(detonationLocation, EntityType.PRIMED_TNT);
                                main.C4.removeTNTLocation(detonationLocation, player);
                                i--;
                            }

                        }

                    }

                }

            }

        }

    }

}
