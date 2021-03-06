package main.listeners;

import main.C4;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

public class PlayerInteractListener implements Listener {

    C4 mainClass;

    public PlayerInteractListener(C4 mainClass) {
        this.mainClass = mainClass;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {

        Player player = event.getPlayer();

        if (player.getItemInHand().getType().equals(Material.LEVER) && player.isSneaking() && (event.getAction().equals(Action.LEFT_CLICK_AIR)
                || event.getAction().equals(Action.LEFT_CLICK_BLOCK))) {

            openInventory(player);

        }


    }
    
    public void openInventory(Player player) {
        String detonateAllC4InventoryDisplayName = mainClass.getConfig().getString("detonateAllC4InventoryDisplayName");

        for (Map.Entry<UUID, ArrayList<String>> entry : main.C4.mapOfC4.entrySet()) {
            if (player.getName().equals(Bukkit.getPlayer(entry.getKey()).getName())) {

                int amountOfTNT = entry.getValue().size();

                Inventory inventory = Bukkit.createInventory(null, 9, "C4 Detonator");

                ItemStack firstTNT = new ItemStack(Material.TNT);
                ItemMeta firstTNTMeta = firstTNT.getItemMeta();
                try {
                    firstTNTMeta.setDisplayName("TNT Location: " + String.valueOf(main.C4.getLocationFromString(entry.getValue().get(0)).getX()) + ", " + String.valueOf(main.C4.getLocationFromString(entry.getValue().get(0)).getY()) + ", " + String.valueOf(main.C4.getLocationFromString(entry.getValue().get(0)).getZ()));
                    firstTNT.setItemMeta(firstTNTMeta);

                } catch (Exception e) {
                    firstTNTMeta.setDisplayName("null");

                }

                ItemStack secondTNT = new ItemStack(Material.TNT);
                ItemMeta secondTNTMeta = secondTNT.getItemMeta();
                try {
                    secondTNTMeta.setDisplayName("TNT Location: " + String.valueOf(main.C4.getLocationFromString(entry.getValue().get(1)).getX()) + ", " + String.valueOf(main.C4.getLocationFromString(entry.getValue().get(1)).getY()) + ", " + String.valueOf(main.C4.getLocationFromString(entry.getValue().get(1)).getZ()));
                    secondTNT.setItemMeta(secondTNTMeta);

                } catch (Exception e) {
                    secondTNTMeta.setDisplayName("null");
                }

                ItemStack thirdTNT = new ItemStack(Material.TNT);
                ItemMeta thirdTNTMeta = thirdTNT.getItemMeta();
                try {
                    thirdTNTMeta.setDisplayName("TNT Location: " + String.valueOf(main.C4.getLocationFromString(entry.getValue().get(2)).getX()) + ", " + String.valueOf(main.C4.getLocationFromString(entry.getValue().get(2)).getY()) + ", " + String.valueOf(main.C4.getLocationFromString(entry.getValue().get(2)).getZ()));
                    thirdTNT.setItemMeta(thirdTNTMeta);

                } catch (Exception e) {
                    thirdTNTMeta.setDisplayName("null");
                }

                ItemStack fourthTNT = new ItemStack(Material.TNT);
                ItemMeta fourthTNTMeta = fourthTNT.getItemMeta();
                try {
                    fourthTNTMeta.setDisplayName("TNT Location: " + String.valueOf(main.C4.getLocationFromString(entry.getValue().get(3)).getX()) + ", " + String.valueOf(main.C4.getLocationFromString(entry.getValue().get(3)).getY()) + ", " + String.valueOf(main.C4.getLocationFromString(entry.getValue().get(3)).getZ()));
                    fourthTNT.setItemMeta(fourthTNTMeta);

                } catch (Exception e) {
                    fourthTNTMeta.setDisplayName("null");
                }

                ItemStack fifthTNT = new ItemStack(Material.TNT);
                ItemMeta fifthTNTMeta = fifthTNT.getItemMeta();

                try {
                    fifthTNTMeta.setDisplayName("TNT Location: " + String.valueOf(main.C4.getLocationFromString(entry.getValue().get(4)).getX()) + ", " + String.valueOf(main.C4.getLocationFromString(entry.getValue().get(4)).getY()) + ", " + String.valueOf(main.C4.getLocationFromString(entry.getValue().get(4)).getZ()));
                    fifthTNT.setItemMeta(fifthTNTMeta);

                } catch (Exception e) {
                    fifthTNTMeta.setDisplayName("null");
                }

                ItemStack sixthTNT = new ItemStack(Material.TNT);
                ItemMeta sixthTNTMeta = sixthTNT.getItemMeta();
                try {
                    sixthTNTMeta.setDisplayName("TNT Location: " + String.valueOf(main.C4.getLocationFromString(entry.getValue().get(5)).getX()) + ", " + String.valueOf(main.C4.getLocationFromString(entry.getValue().get(5)).getY()) + ", " + String.valueOf(main.C4.getLocationFromString(entry.getValue().get(5)).getZ()));
                    sixthTNT.setItemMeta(sixthTNTMeta);

                } catch (Exception e) {
                    sixthTNTMeta.setDisplayName("null");

                }

                ItemStack seventhTNT = new ItemStack(Material.TNT);
                ItemMeta seventhTNTMeta = seventhTNT.getItemMeta();
                try {
                    seventhTNTMeta.setDisplayName("TNT Location: " + String.valueOf(main.C4.getLocationFromString(entry.getValue().get(6)).getX()) + ", " + String.valueOf(main.C4.getLocationFromString(entry.getValue().get(6)).getY()) + ", " + String.valueOf(main.C4.getLocationFromString(entry.getValue().get(6)).getZ()));
                    seventhTNT.setItemMeta(seventhTNTMeta);

                } catch (Exception e) {
                    seventhTNTMeta.setDisplayName("null");

                }

                ItemStack eigthTNT = new ItemStack(Material.TNT);
                ItemMeta eigthTNTMeta = eigthTNT.getItemMeta();
                try {
                    eigthTNTMeta.setDisplayName("TNT Location: " + String.valueOf(main.C4.getLocationFromString(entry.getValue().get(7)).getX()) + ", " + String.valueOf(main.C4.getLocationFromString(entry.getValue().get(7)).getY()) + ", " + String.valueOf(main.C4.getLocationFromString(entry.getValue().get(7)).getZ()));
                    eigthTNT.setItemMeta(eigthTNTMeta);

                } catch (Exception e) {
                    eigthTNTMeta.setDisplayName("null");

                }
                
                Material detonateAllMaterial = Material.matchMaterial(mainClass.getConfig().getString("detonateAllMaterial"));
                ItemStack allTNT = new ItemStack(detonateAllMaterial);
                ItemMeta allTNTMeta = firstTNT.getItemMeta();
                allTNTMeta.setDisplayName(detonateAllC4InventoryDisplayName);
                allTNT.setItemMeta(allTNTMeta);

                ArrayList<ItemStack> inventoryTNTs = new ArrayList<ItemStack>();
                inventoryTNTs.add(firstTNT);
                inventoryTNTs.add(secondTNT);
                inventoryTNTs.add(thirdTNT);
                inventoryTNTs.add(fourthTNT);
                inventoryTNTs.add(fifthTNT);
                inventoryTNTs.add(sixthTNT);
                inventoryTNTs.add(seventhTNT);
                inventoryTNTs.add(eigthTNT);

                for (int i = 0; i < amountOfTNT; i++) {
                    inventory.setItem(i, inventoryTNTs.get(i));
                }

                if (amountOfTNT > 0) {
                    inventory.setItem(8, allTNT);
                }

                player.updateInventory();
                player.openInventory(inventory);

            }

        }

        // }
        // }
    }

}
