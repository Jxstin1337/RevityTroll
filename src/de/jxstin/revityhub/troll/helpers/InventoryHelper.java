package de.jxstin.revityhub.troll.helpers;

// created by Justin Fiedler on 21.02.2021 with IntelliJ

import de.jxstin.revityhub.troll.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class InventoryHelper {

    public static Inventory getTrollInventory(Player p, Player t) {
        Inventory inv = Bukkit.createInventory(null, 27, Main.getPlugin().getPrefix() + "§9" + t.getName());
        String colorCode = Main.getPlugin().getColorCode();

        for (int i = 0; i < 27; i++) {
            if (i <= 9 || i >= 17) {
                inv.setItem(i, ItemHelper.createTrollInvItem(" ", Material.GRAY_STAINED_GLASS_PANE, 1));
            }
            if (i == 10) {
                inv.setItem(i, ItemHelper.createTrollInvItem(colorCode + "Granate", Material.SUGAR, 1));
            }
            if (i == 11) {
                inv.setItem(i, ItemHelper.createTrollInvItem(colorCode + ((Main.getPlugin().getInFreeze().contains(t))
                        ? "Unfreeze" : "Freeze"), Material.ICE, 1));
            }
            if (i == 12) {
                inv.setItem(i, ItemHelper.createTrollInvItem(colorCode + "Crash Game", Material.TNT, 1));
            }
            if (i == 13) {
                inv.setItem(i, ItemHelper.createTrollInvItem(colorCode + "Strike", Material.TRIDENT, 1));
            }
            if (i == 14) {
                inv.setItem(i, ItemHelper.createTrollInvItem(colorCode + "Boost", Material.RABBIT_FOOT, 1));
            }
            if (i == 15) {
                inv.setItem(i, ItemHelper.createTrollInvItem(colorCode + "Item Switch", Material.DIAMOND_SWORD, 1));
            }
            if (i == 16) {
                inv.setItem(i, ItemHelper.createTrollInvHead(colorCode + "Head Spin", t.getName(), 1));
            }
        }

        return inv;
    }

}
