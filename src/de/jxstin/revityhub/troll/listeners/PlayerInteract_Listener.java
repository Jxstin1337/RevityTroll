package de.jxstin.revityhub.troll.listeners;

// created by Justin Fiedler on 21.02.2021 with IntelliJ

import de.jxstin.revityhub.troll.helpers.ItemHelper;
import de.jxstin.revityhub.troll.main.Main;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerInteract_Listener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getItem() == null) return;
        Player p = event.getPlayer();
        String colorCode = Main.getPlugin().getColorCode();
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_AIR) {
            if (event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(colorCode + "Granate")) {
                p.getInventory().removeItem(ItemHelper.createItem(colorCode + "Granate", Material.SUGAR, 1));
                Item granate = p.getWorld().dropItem(p.getEyeLocation(), ItemHelper.createItem(colorCode + "Granate", Material.SUGAR, 1));
                granate.setVelocity(p.getLocation().getDirection().multiply(1.5D));
                new BukkitRunnable() {

                    @Override
                    public void run() {
                        p.getWorld().createExplosion(p, granate.getLocation(), Main.getPlugin().getConfig().getInt
                                ("RevityTroll.granate.explosion_power"), Main.getPlugin().getConfig().getBoolean
                                ("RevityTroll.granate.setFire"), Main.getPlugin().getConfig().getBoolean
                                ("RevityTroll.granate.destroyBlocks"));
                        this.cancel();
                    }
                }.runTaskLater(Main.getPlugin(), 2 * 20L);
            }
        }
    }
}
