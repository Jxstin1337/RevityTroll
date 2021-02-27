package de.jxstin.revityhub.troll.helpers;

// created by Justin Fiedler on 21.02.2021 with IntelliJ

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemHelper {

    public static ItemStack createItem(String name, Material material, int count) {
        ItemStack is = new ItemStack(material, count);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(name);
        is.setItemMeta(im);
        return is;
    }

    public static ItemStack createTrollInvItem(String name, Material material, int count) {
        ItemStack is = new ItemStack(material, count);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(name);
        im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        is.setItemMeta(im);
        return is;
    }

    public static ItemStack createTrollInvHead(String name, String playerName, int count) {
        ItemStack is = new ItemStack(Material.PLAYER_HEAD, count);
        SkullMeta im = (SkullMeta) is.getItemMeta();
        im.setDisplayName(name);
        im.setOwningPlayer(Bukkit.getOfflinePlayer(playerName));
        im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        is.setItemMeta(im);
        return is;
    }

}
