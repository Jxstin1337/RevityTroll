package de.jxstin.revityhub.troll.commands;

// created by Justin Fiedler on 21.02.2021 with IntelliJ

import de.jxstin.revityhub.troll.helpers.InventoryHelper;
import de.jxstin.revityhub.troll.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RevityTroll_CMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (command.getName().equalsIgnoreCase("troll")) {
                if (p.hasPermission(Main.getPlugin().getConfig().getString("RevityTroll.permission"))) {
                    if (args.length == 1) {
                        if (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl")) {
                            Main.getPlugin().reloadConfig();
                            Main.getPlugin().renewPrefix();
                            p.sendMessage(Main.getPlugin().getPrefix() + "Die Config wurde §aerfolgreich §7reloaded!");
                            return true;
                        }
                        if (!(p.getName().equalsIgnoreCase(args[0]))) {
                            if (Bukkit.getPlayer(args[0]) != null) {
                                Player t = Bukkit.getPlayer(args[0]);
                                p.setGameMode(GameMode.CREATIVE);
                                p.openInventory(InventoryHelper.getTrollInventory(p, t));
                            } else {
                                p.sendMessage(Main.getPlugin().getPrefix() + "§cDer angegebene Spieler ist nicht online!");
                            }
                        } else {
                            p.sendMessage(Main.getPlugin().getPrefix() + "§cDu kannst dich nicht selbst trollen!");
                        }
                    } else {
                        p.sendMessage(Main.getPlugin().getPrefix() + "Benutze: /troll <name> §eoder §7/troll <rl/reload>");
                    }
                } else {
                    if (Main.getPlugin().getConfig().getBoolean("RevityTroll.no_permission.enabled")) {
                        p.sendMessage(Main.getPlugin().getPrefix() + Main.getPlugin().getConfig()
                                .getString("RevityTroll.no_permission.message")
                                .replaceAll("%target%", p.getName())
                                .replaceAll("&", "§"));
                    }
                }
            }
        } else {
            sender.sendMessage(Main.getPlugin().getPrefix() + "§cDiesen Command können nur Spieler ausführen!");
        }
        return false;
    }
}
