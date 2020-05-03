package main;

import main.commands.C4Command;
import main.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class C4 extends JavaPlugin {

    FileConfiguration config = this.getConfig();
    //Test
    public static Map<UUID, ArrayList<String>> mapOfC4 = new HashMap<UUID, ArrayList<String>>(); //Tying TNT locations to owner

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //Test
    }

    @Override
    public void onEnable() {


        this.saveDefaultConfig();
        getConfig().addDefault("c4PluginInfo", "C4 is a plugin that allows you to place C4 and detonate from a distance."); // /C4
        getConfig().addDefault("radius", 5); //maximum radius to use a c4
        getConfig().addDefault("c4Placed", "C4 Placed!");
        getConfig().addDefault("maxC4Placed", "Maximum C4 already placed!");
        getConfig().addDefault("detonateAllMaterial", "TNT"); //material to detonate all tnt (in inventory)
        getConfig().addDefault("outOfRangeSingleDetonation", "You are out of range of the C4."); //message sent when player is out of range of a C4
        getConfig().addDefault("outOfRangeMultipleDetonation", "C4 is out of range at location: ");
        getConfig().addDefault("detonateAllC4InventoryDisplayName", "Detonate all TNT");

        getConfig().options().copyDefaults(true);
        saveConfig();

        try {
            FileInputStream fis = new FileInputStream("plugins/C4/locations.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            mapOfC4 = (HashMap) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        this.getCommand("c4").setExecutor(new C4Command(this));
        getServer().getPluginManager().registerEvents(new PlayerLoginListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractListener(this), this);
        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
        getServer().getPluginManager().registerEvents(new BlockPlaceListener(this), this);
        getServer().getPluginManager().registerEvents(new InventoryClickListener(this), this);
        getServer().getPluginManager().registerEvents(new EntityExplodeListener(), this);
    }

    @Override
    public void onDisable() {

        try {
            FileOutputStream fos = new FileOutputStream("plugins/C4/locations.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(mapOfC4);
            oos.close();
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void addTNTLocation(Location location, Player player) {

        for (Map.Entry<UUID, ArrayList<String>> entry : mapOfC4.entrySet()) {

            if (player.getName().equals(Bukkit.getPlayer(entry.getKey()).getName())) { //Find player in hashmap
                entry.getValue().add(getStringFromLocation(location)); //Add location from parameter to the arraylist corresponding to the found player
            }

        }

    }

    public static void removeTNTLocation(Location location, Player player) {

        for (Map.Entry<UUID, ArrayList<String>> entry : mapOfC4.entrySet()) {
            if (Bukkit.getPlayer(entry.getKey()).getName().equals(player.getName())) { //Find player in hashmap

                for (int i = 0; i < entry.getValue().size(); i++) { //Loop through the TNT locations corresponding to their name

                    if (entry.getValue().get(i).equals(getStringFromLocation(location))) { //Find matching location

                        entry.getValue().remove(i);

                    }

                }

            }

        }

    }

    public static void removeTNTLocation(Location location) {

        for (Map.Entry<UUID, ArrayList<String>> entry : mapOfC4.entrySet()) {

            for (int i = 0; i < entry.getValue().size(); i++) {

                if (getLocationFromString(entry.getValue().get(i)).equals(location)) {
                    entry.getValue().remove(i);

                }

            }

        }

    }

    public Map<UUID, ArrayList<String>> getHashMap() {
        return mapOfC4;
    }

    public static void addPlayer(UUID uuid) {
        mapOfC4.put(uuid, new ArrayList<>());
    }

    public static UUID getUUIDFromPlayer(Player player) {

        return player.getUniqueId();

    }

    public static String getStringFromLocation(Location location) {
        if (location == null) {
            return "";
        }
        return location.getWorld().getName() + ":" + location.getX() + ":" + location.getY() + ":" + location.getZ() + ":" + location.getYaw() + ":" + location.getPitch();
    }

    public static Location getLocationFromString(String s) {
        if (s == null || s.trim() == "") {
            return null;
        }
        final String[] parts = s.split(":");
        if (parts.length == 6) {
            World w = Bukkit.getServer().getWorld(parts[0]);
            double x = Double.parseDouble(parts[1]);
            double y = Double.parseDouble(parts[2]);
            double z = Double.parseDouble(parts[3]);
            float yaw = Float.parseFloat(parts[4]);
            float pitch = Float.parseFloat(parts[5]);
            return new Location(w, x, y, z, yaw, pitch);
        }
        return null;
    }

}
