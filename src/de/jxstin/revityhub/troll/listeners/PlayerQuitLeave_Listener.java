package de.jxstin.revityhub.troll.listeners;

// created by Justin Fiedler on 24.02.2021 with IntelliJ

import de.jxstin.revityhub.troll.main.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitLeave_Listener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player p = event.getPlayer();
        if (Main.getPlugin().getInFreeze().contains(p)) {
            Main.getPlugin().getInFreeze().remove(p);
        }
    }

    @EventHandler
    public void onLeave(PlayerKickEvent event) {
        Player p = event.getPlayer();
        if (Main.getPlugin().getInFreeze().contains(p)) {
            Main.getPlugin().getInFreeze().remove(p);
        }
    }

}
