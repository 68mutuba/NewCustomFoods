package jp.titleserver.NewCustomFoods.Gui;

import jp.titleserver.NewCustomFoods.Main;
import jp.titleserver.NewCustomFoods.Utils.FoodsLib;
import jp.titleserver.NewCustomFoods.Utils.SetInitialItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class EditInventory {

    private static Inventory OriginalInventory = Bukkit.createInventory(null, 6 * 9, "");
    private static Inventory PotionInventory = Bukkit.createInventory(null, 6 * 9, ChatColor.GOLD + "" + ChatColor.BOLD + "Potion付与");

    public static ItemStack off = new ItemStack(Material.STONE);
    public static ItemStack on = new ItemStack(Material.STONE);


    public static void setInitialItem() {
        OriginalInventory = SetInitialItem.setEditGuiInitialItem(OriginalInventory);
        PotionInventory = SetInitialItem.setPotionEditGuiInitialItem(PotionInventory);

        List<String> lore = new ArrayList<>();
        ItemMeta im = on.getItemMeta();
        on.setType(Material.REDSTONE_TORCH_ON);
        on.setDurability((short)0);
        im.setDisplayName(ChatColor.GOLD + "インフィニティ");
        lore.clear();
        lore.add(ChatColor.GREEN + "クリックでインフィニティをトグル");
        lore.add(ChatColor.GOLD + "現在: 有効");
        im.setLore(lore);
        on.setItemMeta(im);

        im = off.getItemMeta();
        off.setType(Material.LEVER);
        off.setDurability((short)0);
        im.setDisplayName(ChatColor.GOLD + "インフィニティ");
        lore.clear();
        lore.add(ChatColor.GREEN + "クリックでインフィニティをトグル");
        lore.add(ChatColor.GOLD + "現在: " + ChatColor.RED + "無効");
        im.setLore(lore);
        off.setItemMeta(im);
    }

    public static Inventory getEditGui(String ID) {
        Inventory ReturnEditInv = Bukkit.createInventory(null, 6 * 9, ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "EDIT: " + ChatColor.DARK_GRAY + ID);
        ReturnEditInv = SetInitialItem.setEditGuiInitialItem(ReturnEditInv);


        return setValues(ReturnEditInv, ID);
    }

    public static Inventory setValues(Inventory inv, String ID) {

        inv.setItem(13, FoodsLib.getItem(ID));

        //FOODPOINT
        ItemStack is = new ItemStack(Material.STAINED_GLASS);
        ItemMeta im = is.getItemMeta();

        List<String> lore = new ArrayList<>();

        is.setType(Material.BREAD);
        is.setDurability((short)0);
        im.setDisplayName(ChatColor.GOLD + "食料ゲージ回復量");
        lore.clear();
        lore.add(ChatColor.GREEN + "クリックで食料ゲージ回復量を変更");

        if (Main.plugin.getConfig().contains(ID + ".food")) {

            int value = Main.plugin.getConfig().getInt(ID + ".food");

            if (value == 0) {
                lore.add(ChatColor.GOLD + "設定済み: " + ChatColor.GRAY + value);
            } else if (value > 0) {
                lore.add(ChatColor.GOLD + "設定済み: " + ChatColor.GREEN + value);
            } else {
                lore.add(ChatColor.GOLD + "設定済み: " + ChatColor.RED + value);
            }
        } else {
            lore.add(ChatColor.GRAY + "未設定: " + ChatColor.GRAY + 0);
        }
        im.setLore(lore);
        is.setItemMeta(im);
        inv.setItem(29, is);


        //COOLDOWN
        is.setType(Material.WATCH);
        is.setDurability((short)0);
        im.setDisplayName(ChatColor.GOLD + "クールダウン");
        lore.clear();
        lore.add(ChatColor.GREEN + "クリックでクールダウンを変更");

        if (Main.plugin.getConfig().contains(ID + ".cooldown")) {

            int value = Main.plugin.getConfig().getInt(ID + ".cooldown");

            if (value == 0) {
                lore.add(ChatColor.GOLD + "設定済み: " + ChatColor.GRAY + value);
            } else if (value > 0) {
                lore.add(ChatColor.GOLD + "設定済み: " + ChatColor.GREEN + value);
            } else {
                lore.add(ChatColor.GOLD + "設定済み: " + ChatColor.RED + value);
            }
        } else {
            lore.add(ChatColor.GRAY + "未設定: " + ChatColor.GRAY + 0);
        }
        im.setLore(lore);
        is.setItemMeta(im);
        inv.setItem(31, is);


        //PARTICLE
        is.setType(Material.SNOW_BALL);
        is.setDurability((short)0);
        im.setDisplayName(ChatColor.GOLD + "パーティクル");
        lore.clear();
        lore.add(ChatColor.GREEN + "クリックでパーティクルを変更");

        if (Main.plugin.getConfig().contains(ID + ".particle")) {

            String[] value = Main.plugin.getConfig().getString(ID + ".particle").split(",");

            lore.add(ChatColor.GOLD + "設定済み(N): " + ChatColor.GREEN + value[0]);
            lore.add(ChatColor.GOLD + "設定済み(A): " + ChatColor.GREEN + value[1]);
            lore.add(ChatColor.GOLD + "設定済み(X): " + ChatColor.GREEN + value[2]);
            lore.add(ChatColor.GOLD + "設定済み(Y): " + ChatColor.GREEN + value[3]);
            lore.add(ChatColor.GOLD + "設定済み(Z): " + ChatColor.GREEN + value[4]);
        } else {
            lore.add(ChatColor.GRAY + "未設定(N): NONE");
            lore.add(ChatColor.GRAY + "未設定(A): NONE");
            lore.add(ChatColor.GRAY + "未設定(X): NONE");
            lore.add(ChatColor.GRAY + "未設定(Y): NONE");
            lore.add(ChatColor.GRAY + "未設定(Z): NONE");
        }
        im.setLore(lore);
        is.setItemMeta(im);
        inv.setItem(33, is);


        //COMMAND
        is.setType(Material.SIGN);
        is.setDurability((short)0);
        im.setDisplayName(ChatColor.GOLD + "コマンド");
        lore.clear();
        lore.add(ChatColor.GREEN + "クリックでコマンドを変更");

        if (Main.plugin.getConfig().contains(ID + ".command")) {
            lore.add(ChatColor.GOLD + "設定済み: [" + ChatColor.GREEN + Main.plugin.getConfig().get(ID + ".command") + "]");
        } else {
            lore.add(ChatColor.GRAY + "未設定: NONE");
        }
        im.setLore(lore);
        is.setItemMeta(im);
        inv.setItem(35, is);


        if (Main.plugin.getConfig().contains(ID + ".infinity")) {
            if (Main.plugin.getConfig().getBoolean(ID + ".infinity")) {
                inv.setItem(45, on);
            } else {
                inv.setItem(45, off);
            }
        } else {
            Main.plugin.getConfig().set(ID + ".infinity", false);
            inv.setItem(45, off);
        }

        //HEALTH
        is.setType(Material.INK_SACK);
        is.setDurability((short)1);
        im.setDisplayName(ChatColor.GOLD + "体力ゲージ回復量");
        lore.clear();
        lore.add(ChatColor.GREEN + "クリックで体力ゲージ回復量を変更");

        if (Main.plugin.getConfig().contains(ID + ".health")) {

            float value = Main.plugin.getConfig().getLong(ID + ".health");

            if (value == 0) {
                lore.add(ChatColor.GOLD + "設定済み: " + ChatColor.GRAY + value);
            } else if (value > 0) {
                lore.add(ChatColor.GOLD + "設定済み: " + ChatColor.GREEN + value);
            } else {
                lore.add(ChatColor.GOLD + "設定済み: " + ChatColor.RED + value);
            }
        } else {
            lore.add(ChatColor.GRAY + "未設定: " + ChatColor.GRAY + 0);
        }
        im.setLore(lore);
        is.setItemMeta(im);
        inv.setItem(47, is);


        if (Main.plugin.getConfig().contains(ID + ".potion")) {

        }

        //SOUND
        is.setType(Material.NOTE_BLOCK);
        is.setDurability((short)0);
        im.setDisplayName(ChatColor.GOLD + "サウンド");
        lore.clear();
        lore.add(ChatColor.GREEN + "クリックでサウンドを編集");

        if (Main.plugin.getConfig().contains(ID + ".sound")) {

            String[] value = Main.plugin.getConfig().getString(ID + ".sound").split(",");

            lore.add(ChatColor.GOLD + "設定済み(N): " + ChatColor.GREEN + value[0]);
            lore.add(ChatColor.GOLD + "設定済み(P): " + ChatColor.GREEN + value[1]);
            lore.add(ChatColor.GOLD + "設定済み(V): " + ChatColor.GREEN + value[2]);
        } else {
            lore.add(ChatColor.GRAY + "未設定(N): NONE");
            lore.add(ChatColor.GRAY + "未設定(P): NONE");
            lore.add(ChatColor.GRAY + "未設定(V): NONE");
        }
        im.setLore(lore);
        is.setItemMeta(im);
        inv.setItem(51, is);


        return inv;

    }

    public static Inventory getPotionEditGui() {
        return PotionInventory;
    }

}
