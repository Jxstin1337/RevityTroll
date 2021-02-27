package de.jxstin.revityhub.troll.listeners;

// created by Justin Fiedler on 27.02.2021 with IntelliJ

import de.jxstin.revityhub.troll.helpers.ItemHelper;
import de.jxstin.revityhub.troll.main.Main;
import net.minecraft.server.v1_16_R3.PacketPlayOutExplosion;
import net.minecraft.server.v1_16_R3.Vec3D;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collections;
import java.util.Random;

public class InventoryClick_Listener_v1_16_R3to5 implements Listener, InventoryClick_Listener {

    int spins = 0;

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getWhoClicked() == null) return;
        if (event.getCurrentItem() == null) return;
        Player p = (Player) event.getWhoClicked();
        if (event.getView().getTitle().startsWith(Main.getPlugin().getPrefix())) {
            if (event.getClickedInventory() instanceof PlayerInventory) return;
            String[] tName = event.getView().getTitle().split(Main.getPlugin().getConfig()
                    .getString("RevityTroll.prefixUnicode").replaceAll("&", "§"));
            Player t = Bukkit.getPlayer(tName[1].replace("§9", ""));
            String colorCode = Main.getPlugin().getColorCode();
            event.setCancelled(true);
            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(colorCode + "Granate")) {
                p.closeInventory();
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 3, 1);
                p.sendMessage(Main.getPlugin().getPrefix() + Main.getPlugin().getConfig().getString
                        ("RevityTroll.messages.granate").replaceAll("&", "§"));
                p.getInventory().addItem(ItemHelper.createItem(colorCode + "Granate", Material.SUGAR, 1));
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(colorCode + "Freeze")) {
                p.closeInventory();
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 3, 1);
                p.sendMessage(Main.getPlugin().getPrefix() + Main.getPlugin().getConfig().getString
                        ("RevityTroll.messages.freeze").replaceAll("&", "§").replaceAll("%target%", t.getName()));
                Main.getPlugin().getInFreeze().add(t);
                t.setWalkSpeed(0);
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(colorCode + "Unfreeze")) {
                p.closeInventory();
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 3, 1);
                p.sendMessage(Main.getPlugin().getPrefix() + Main.getPlugin().getConfig().getString
                        ("RevityTroll.messages.unfreeze").replaceAll("&", "§").replaceAll("%target%", t.getName()));
                Main.getPlugin().getInFreeze().remove(t);
                t.setWalkSpeed(0.2F);
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(colorCode + "Crash Game")) {
                p.closeInventory();
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 3, 1);
                p.sendMessage(Main.getPlugin().getPrefix() + Main.getPlugin().getConfig().getString
                        ("RevityTroll.messages.crash_game").replaceAll("&", "§").replaceAll("%target%", t.getName()));
                ((CraftPlayer) t).getHandle().playerConnection.sendPacket(new PacketPlayOutExplosion(
                        Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE, Float.MAX_VALUE,
                        Collections.EMPTY_LIST, new Vec3D(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE)));
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(colorCode + "Strike")) {
                p.closeInventory();
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 3, 1);
                p.sendMessage(Main.getPlugin().getPrefix() + Main.getPlugin().getConfig().getString
                        ("RevityTroll.messages.strike").replaceAll("&", "§").replaceAll("%target%", t.getName()));
                t.getWorld().strikeLightning(t.getLocation());
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(colorCode + "Boost")) {
                p.closeInventory();
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 3, 1);
                p.sendMessage(Main.getPlugin().getPrefix() + Main.getPlugin().getConfig().getString
                        ("RevityTroll.messages.boost").replaceAll("&", "§").replaceAll("%target%", t.getName()));
                t.setVelocity(t.getLocation().getDirection().setY(Main.getPlugin().getConfig().getDouble("RevityTroll.boost.power")));
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(colorCode + "Item Switch")) {
                p.closeInventory();
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 3, 1);
                p.sendMessage(Main.getPlugin().getPrefix() + Main.getPlugin().getConfig().getString
                        ("RevityTroll.messages.item_switch").replaceAll("&", "§").replaceAll("%target%", t.getName()));
                switchInventoryItems(t);
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(colorCode + "Head Spin")) {
                p.closeInventory();
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 3, 1);
                p.sendMessage(Main.getPlugin().getPrefix() + Main.getPlugin().getConfig().getString
                        ("RevityTroll.messages.head_spin").replaceAll("&", "§").replaceAll("%target%", t.getName()));
                switchInventoryItems(t);
                Random r = new Random();
                spins = 0;
                new BukkitRunnable() {

                    @Override
                    public void run() {
                        t.teleport(new Location(t.getWorld(), t.getLocation().getX(), t.getLocation().getY(),
                                t.getLocation().getZ(), r.nextInt(360), r.nextInt(60)));
                        spins++;
                        if (spins >= 60) this.cancel();
                    }
                }.runTaskTimer(Main.getPlugin(), 2L, 1L);
            }
        }
    }

    public void switchInventoryItems(Player p) {
        Random r1 = new Random();
        Random r2 = new Random();
        int item1, item2;
        for (int i = 0; i < 10; i++) {
            item1 = r1.nextInt(p.getInventory().getSize());
            item2 = r2.nextInt(p.getInventory().getSize());
            ItemStack itemStack1 = p.getInventory().getItem(item1);
            ItemStack itemStack2 = p.getInventory().getItem(item2);
            p.getInventory().setItem(item1, itemStack2);
            p.getInventory().setItem(item2, itemStack1);
        }
    }

}
