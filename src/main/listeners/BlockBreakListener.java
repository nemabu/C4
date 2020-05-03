package main.listeners;

import net.minecraft.server.v1_15_R1.NBTTagCompound;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_15_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

public class BlockBreakListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();

        if (event.getBlock().getType().equals(Material.TNT)) { //Check if block broken is TNT

            for (Map.Entry<UUID, ArrayList<String>> entry : main.C4.mapOfC4.entrySet()) { //Loop through hashmap

                for (int i = 0; i < entry.getValue().size(); i++) { //Loop through arraylist of each value in hashmap
                    Location checkLocation = main.C4.getLocationFromString(entry.getValue().get(i)); //Iterated location
                    if (event.getBlock().getLocation().equals(checkLocation)) { //Check if block broken is in the iterated location

                        event.setCancelled(true);
                        event.getBlock().setType(Material.AIR);

                        ItemStack brokenItem = new ItemStack(Material.TNT);
                        ItemMeta meta = brokenItem.getItemMeta();
                        meta.setDisplayName("C4");
                        brokenItem.setItemMeta(meta);

                        net.minecraft.server.v1_15_R1.ItemStack nmsItem = CraftItemStack.asNMSCopy(brokenItem);
                        NBTTagCompound tag = nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound();

                        tag.setBoolean("isC4", true);
                        tag.setString("owner", player.getName());
                        nmsItem.setTag(tag);
                        brokenItem = CraftItemStack.asBukkitCopy(nmsItem);

                        player.getInventory().addItem(brokenItem);

                        main.C4.removeTNTLocation(checkLocation, player); //Remove block from hashmap
                    }

                }

            }

        }

    }

}
