package de.jxstin.revityhub.troll.main;

// created by Justin Fiedler on 21.02.2021 with IntelliJ

import de.jxstin.revityhub.troll.commands.RevityTroll_CMD;
import de.jxstin.revityhub.troll.listeners.InventoryClick_Listener;
import de.jxstin.revityhub.troll.listeners.PlayerInteract_Listener;
import de.jxstin.revityhub.troll.listeners.PlayerJump_Listener;
import de.jxstin.revityhub.troll.listeners.PlayerQuitLeave_Listener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class Main extends JavaPlugin {

    static Main plugin;
    String prefix;
    ArrayList<Player> inFreeze = new ArrayList<>();

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
        pm.registerEvents(new InventoryClick_Listener(), getPlugin());
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
}
