package main.listeners;

import main.C4;
import net.minecraft.server.v1_15_R1.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_15_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class BlockPlaceListener implements Listener {

    C4 mainClass;

    public BlockPlaceListener(C4 mainClass) {
        this.mainClass = mainClass;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        String c4Placed = mainClass.getConfig().getString("c4Placed");
        String maxC4Placed = mainClass.getConfig().getString("maxC4Placed");

        Player player = event.getPlayer();
        if (event.getBlockPlaced().getType().equals(Material.TNT)) {
            ItemStack placedTNT = event.getItemInHand();
            net.minecraft.server.v1_15_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(placedTNT);
            NBTTagCompound compound = nmsStack.hasTag() ? nmsStack.getTag() : new NBTTagCompound();

            if (compound.getBoolean("isC4") == true) {

                for (Map.Entry<UUID, ArrayList<String>> entry : main.C4.mapOfC4.entrySet()) {

                    if (Bukkit.getPlayer(entry.getKey()) != null) {
                        if (Bukkit.getPlayer(entry.getKey()).getName().equals(player.getName())) { //Find player who placed TNT in hashmap
                            if (entry.getValue().size() < 8) { //If they have less than 8 stored locations to their name
                                Location tntLocation = event.getBlock().getLocation();
                                main.C4.addTNTLocation(tntLocation, player); //Add TNT location to their arraylist in the hashmap
                                player.sendMessage(ChatColor.RED + c4Placed);

                            } else if (entry.getValue().size() >= 8) {
                                event.setCancelled(true);
                                player.sendMessage(ChatColor.RED + maxC4Placed);
                            }
                        }

                    }

                }
            }

        }

    }


}
