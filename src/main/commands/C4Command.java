package main.commands;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

public class C4Command implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        Player player = (Player) s;

        if (args.length == 3 && player.isOp()) {
            if (args[0].equals("give")) {

                boolean foundPlayer = false;
                for (Player targetPlayer : Bukkit.getOnlinePlayers()) {
                    if (args[1].equals(targetPlayer.getName())) {
                        foundPlayer = true;

                        try {
                            ItemStack addItems = new ItemStack(Material.TNT, Integer.valueOf(args[2]));

                            ItemMeta meta = addItems.getItemMeta();
                            meta.setDisplayName("C4");
                            addItems.setItemMeta(meta);

                            net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(addItems);
                            NBTTagCompound tag = nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound();

                            tag.setBoolean("isC4", true);
                            tag.setString("owner", player.getName());
                            nmsItem.setTag(tag);
                            addItems = CraftItemStack.asBukkitCopy(nmsItem);

                            targetPlayer.getInventory().addItem(addItems);

                        } catch (Exception e) {
                            player.sendMessage(e.toString()); //Send error to player

                        }

                    }

                }
                if (foundPlayer == false) {
                    player.sendMessage("Could not find target player.");
                }

            }

        }
        if (args.length == 1) {
            if (args[0].equals("check")) {

                for (Map.Entry<UUID, ArrayList<String>> entry : main.C4.mapOfC4.entrySet()) {
                    if (entry.getKey().equals(main.C4.getUUIDFromPlayer(player))) {
                        player.sendMessage("You currently have " + entry.getValue().size() + " active TNT locations.");
                        return true;
                    }
                }

            }

        }

        return false;
    }
}