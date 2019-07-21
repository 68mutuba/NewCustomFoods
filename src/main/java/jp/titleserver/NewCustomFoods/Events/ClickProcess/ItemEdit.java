package jp.titleserver.NewCustomFoods.Events.ClickProcess;

import jp.titleserver.NewCustomFoods.Events.InventoryClick;
import jp.titleserver.NewCustomFoods.Events.InventoryClose;
import jp.titleserver.NewCustomFoods.Gui.ChatFlagPlayer;
import jp.titleserver.NewCustomFoods.Gui.EditInventory;
import jp.titleserver.NewCustomFoods.Gui.GuiFlagPlayer;
import jp.titleserver.NewCustomFoods.Main;
import jp.titleserver.NewCustomFoods.Utils.FoodsLib;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class ItemEdit {

    public static void ClickItemEditInventory(Player player, int slot) {

        String ID = GuiFlagPlayer.GuiGetID.get(player.getUniqueId());

        player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 1.0f);

        //ITEM_CHANGE
        if (slot == 27) {
            ChatFlagPlayer.ChatFlagPlayers.put(player.getUniqueId(), ChatFlagPlayer.CHAT_TYPE.ITEM);
            ChatFlagPlayer.ChatGetID.put(player.getUniqueId(), ID);
            player.sendMessage(ChatColor.GOLD + "変更後のアイテムを手に持ち、「set」とチャットに入力してください。");
            player.sendMessage(ChatColor.GOLD + "キャンセルするには「cancel」と入力してください。");
            InventoryClose.OneTime.add(player.getUniqueId());
            player.closeInventory();

            return;
        }

        //CHANGE_FOODPOINT
        if (slot == 29) {
            ChatFlagPlayer.ChatFlagPlayers.put(player.getUniqueId(), ChatFlagPlayer.CHAT_TYPE.FOODPOINT);
            ChatFlagPlayer.ChatGetID.put(player.getUniqueId(), ID);
            player.sendMessage(ChatColor.GOLD + "整数をチャットに入力してください。");
            player.sendMessage(ChatColor.GOLD + "キャンセルするには「cancel」と入力してください。");
            InventoryClose.OneTime.add(player.getUniqueId());
            player.closeInventory();

            return;
        }

        //CHANGE_COOLDOWN
        if (slot == 31) {
            ChatFlagPlayer.ChatFlagPlayers.put(player.getUniqueId(), ChatFlagPlayer.CHAT_TYPE.COOLDOWN);
            ChatFlagPlayer.ChatGetID.put(player.getUniqueId(), ID);
            player.sendMessage(ChatColor.GOLD + "整数をチャットに入力してください。");
            player.sendMessage(ChatColor.GOLD + "キャンセルするには「cancel」と入力してください。");
            InventoryClose.OneTime.add(player.getUniqueId());
            player.closeInventory();

            return;
        }

        //CHANGE_PARTICLE
        if (slot == 33) {
            ChatFlagPlayer.ChatFlagPlayers.put(player.getUniqueId(), ChatFlagPlayer.CHAT_TYPE.PARTICLE);
            ChatFlagPlayer.ChatGetID.put(player.getUniqueId(), ID);
            player.sendMessage(ChatColor.GOLD + "パーティクルのIDをチャットに入力してください。");
            player.sendMessage(ChatColor.GOLD + "※任意でパーティクル量, 散開距離X, 散開距離Y, 散開距離Zをそれぞれスペースを空けて入力してください。");
            player.sendMessage(ChatColor.GOLD + "キャンセルするには「cancel」と入力してください。");
            InventoryClose.OneTime.add(player.getUniqueId());
            player.closeInventory();

            return;
        }

        //CHANGE_COMMAND
        if (slot == 35) {
            ChatFlagPlayer.ChatFlagPlayers.put(player.getUniqueId(), ChatFlagPlayer.CHAT_TYPE.COMMAND);
            ChatFlagPlayer.ChatGetID.put(player.getUniqueId(), ID);
            player.sendMessage(ChatColor.GOLD + "コマンドをチャットに入力してください。");
            player.sendMessage(ChatColor.GOLD + "※先頭の/は不要です。");
            player.sendMessage(ChatColor.GOLD + "キャンセルするには「cancel」と入力してください。");
            InventoryClose.OneTime.add(player.getUniqueId());
            player.closeInventory();

            return;
        }

        //CHANGE_INFINITY
        if (slot == 45) {
            if (Main.plugin.getConfig().getBoolean(ID + ".infinity")) {
                Main.plugin.getConfig().set(ID + ".infinity", false);
                player.getOpenInventory().setItem(45, EditInventory.off);
                player.updateInventory();
                Main.plugin.saveConfig();
            } else {
                Main.plugin.getConfig().set(ID + ".infinity", true);
                player.getOpenInventory().setItem(45, EditInventory.on);
                player.updateInventory();
                Main.plugin.saveConfig();

            }

            return;
        }

        //CHANGE_HEALTHPOINT
        if (slot == 47) {
            ChatFlagPlayer.ChatFlagPlayers.put(player.getUniqueId(), ChatFlagPlayer.CHAT_TYPE.HEALTHPOINT);
            ChatFlagPlayer.ChatGetID.put(player.getUniqueId(), ID);
            player.sendMessage(ChatColor.GOLD + "整数をチャットに入力してください。");
            player.sendMessage(ChatColor.GOLD + "キャンセルするには「cancel」と入力してください。");
            InventoryClose.OneTime.add(player.getUniqueId());
            player.closeInventory();

            return;
        }

        //CHANGE_POTION
        if (slot == 49) {
            InventoryClose.OneTime.add(player.getUniqueId());
            player.closeInventory();
            GuiFlagPlayer.OpenPlayers.put(player.getUniqueId(), GuiFlagPlayer.INVENTORY_TYPE.POTION_EDIT);
            ChatFlagPlayer.ChatGetID.put(player.getUniqueId(), ID);
            player.openInventory(EditInventory.getPotionEditGui());

            return;
        }

        //CHANGE_SOUND
        if (slot == 51) {
            ChatFlagPlayer.ChatFlagPlayers.put(player.getUniqueId(), ChatFlagPlayer.CHAT_TYPE.SOUND);
            ChatFlagPlayer.ChatGetID.put(player.getUniqueId(), ID);
            player.sendMessage(ChatColor.GOLD + "サウンドIDをチャットに入力してください。");
            player.sendMessage(ChatColor.GOLD + "※任意でピッチとボリュームをそれぞれスペースを空けて入力してください。");
            player.sendMessage(ChatColor.GOLD + "キャンセルするには「cancel」と入力してください。");
            InventoryClose.OneTime.add(player.getUniqueId());
            player.closeInventory();

            return;
        }

        //REMOVE
        if (slot == 53) {
            FoodsLib.remove(GuiFlagPlayer.GuiGetID.get(player.getUniqueId()));
            player.sendMessage(ChatColor.RED + "削除しました。");
            player.closeInventory();

            return;
        }

    }

}
