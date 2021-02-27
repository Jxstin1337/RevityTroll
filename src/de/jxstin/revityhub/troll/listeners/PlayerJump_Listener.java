package de.jxstin.revityhub.troll.listeners;

// created by Justin Fiedler on 21.02.2021 with IntelliJ

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import de.jxstin.revityhub.troll.main.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerJump_Listener implements Listener {

    @EventHandler
    public void onJump(PlayerJumpEvent event) {
        Player p = event.getPlayer();
        if (Main.getPlugin().getInFreeze().contains(p)) {
            event.setCancelled(true);
        }
    }

}
