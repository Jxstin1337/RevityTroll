package de.jxstin.revityhub.troll.main;

// created by Justin Fiedler on 21.02.2021 with IntelliJ

import de.jxstin.revityhub.troll.commands.RevityTroll_CMD;
import de.jxstin.revityhub.troll.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class Main extends JavaPlugin {

    static Main plugin;
    String prefix = "§5§lRevity§2§lTroll §7» ";
    ArrayList<Player> inFreeze = new ArrayList<>();
    InventoryClick_Listener inventoryClick_Listener;
    String usedVersion;

    public static Main getPlugin() {
        return plugin;
    }

    public ArrayList<Player> getInFreeze() {
        return inFreeze;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public void onEnable() {
        plugin = this;
        if (!(isVersionSupported())) {
            getLogger().severe("Die Version deines Servers wird nicht unterstützt!");
            getLogger().severe("Unterstützte Versionen: 1.16.X");
            Bukkit.getPluginManager().disablePlugin(getPlugin());
        }
        registerListeners();
        registerCommands();
        saveDefaultConfig();
        renewPrefix();
        System.out.println(getPrefix() + "§7-------------------");
        System.out.println(getPrefix() + "§7Status: §agestartet");
        System.out.println(getPrefix() + "§7Autor: " + String.valueOf(getDescription().getAuthors())
                .replace("[", "").replace("]", ""));
        System.out.println(getPrefix() + "§7Version: " + getDescription().getVersion());
        System.out.println(getPrefix() + "§7-------------------");
    }

    @Override
    public void onDisable() {
        System.out.println(getPrefix() + "§7-------------------");
        System.out.println(getPrefix() + "§7Status: §cgestoppt");
        System.out.println(getPrefix() + "§7Autor: " + String.valueOf(getDescription().getAuthors())
                .replace("[", "").replace("]", ""));
        System.out.println(getPrefix() + "§7Version: " + getDescription().getVersion());
        System.out.println(getPrefix() + "§7-------------------");
    }

    public void registerListeners() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerInteract_Listener(), getPlugin());
        pm.registerEvents(new PlayerJump_Listener(), getPlugin());
        pm.registerEvents(new PlayerQuitLeave_Listener(), getPlugin());
    }

    public void registerCommands() {
        getCommand("troll").setExecutor(new RevityTroll_CMD());
    }

    public void renewPrefix() {
        prefix = (getConfig().getString("RevityTroll.prefix") +
                getConfig().getString("RevityTroll.prefixUnicode")).replaceAll("&", "§");
    }

    public String getColorCode() {
        return getConfig().getString("RevityTroll.inv_items.colorCode").replaceAll("&", "§");
    }

    public Boolean isVersionSupported() {
        PluginManager pm = Bukkit.getPluginManager();

        try {
            usedVersion = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        } catch (ArrayIndexOutOfBoundsException ex) {
            ex.printStackTrace();
            return false;
        }

        if (usedVersion.equals("v1_16_R1")) {
            inventoryClick_Listener = new InventoryClick_Listener_v1_16_R1();
            pm.registerEvents(new InventoryClick_Listener_v1_16_R1(), getPlugin());
        } else if (usedVersion.equals("v1_16_R2")) {
            inventoryClick_Listener = new InventoryClick_Listener_v1_16_R2();
            pm.registerEvents(new InventoryClick_Listener_v1_16_R2(), getPlugin());
        } else if (usedVersion.equals("v1_16_R3") || usedVersion.equals("v1_16_R4") || usedVersion.equals("v1_16_R5")) {
            inventoryClick_Listener = new InventoryClick_Listener_v1_16_R3to5();
            pm.registerEvents(new InventoryClick_Listener_v1_16_R3to5(), getPlugin());
        }

        return inventoryClick_Listener != null;
    }
}
