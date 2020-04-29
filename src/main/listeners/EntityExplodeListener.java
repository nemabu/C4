package main.listeners;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

public class EntityExplodeListener implements Listener {

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        if (event.getEntity().getType().equals(EntityType.PRIMED_TNT)) {


            for (Map.Entry<UUID, ArrayList<String>> entry : main.C4.mapOfC4.entrySet()) { //Loop through hashmap

                for (int i = 0; i < entry.getValue().size(); i++) { //Loop through arraylist of each value in hashmap
                    if (!(event.getEntity().getWorld().getBlockAt(main.C4.getLocationFromString(entry.getValue().get(i))).getType().equals(Material.TNT))) {
                        main.C4.removeTNTLocation(main.C4.getLocationFromString(entry.getValue().get(i)));
                    }


                }

            }

        }

    }


}
