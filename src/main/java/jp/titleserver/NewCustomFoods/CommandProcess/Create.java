package jp.titleserver.NewCustomFoods.CommandProcess;

import jp.titleserver.NewCustomFoods.Gui.EditInventory;
import jp.titleserver.NewCustomFoods.Gui.GuiFlagPlayer;
import jp.titleserver.NewCustomFoods.Main;
import jp.titleserver.NewCustomFoods.Utils.FoodsLib;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Create {

    public static void create(Player player, String Id, ItemStack is) {
        if (is.getAmount() == 0) {
            player.sendMessage(ChatColor.RED + "アイテムを手に持ってください!");
            return;
        }

        if (FoodsLib.addItem(Id, is)) {
            player.sendMessage(ChatColor.GREEN + "作成に成功しました。");
            GuiFlagPlayer.OpenPlayers.put(player.getUniqueId(), GuiFlagPlayer.INVENTORY_TYPE.ITEM_EDIT);
            GuiFlagPlayer.GuiGetID.put(player.getUniqueId(), Id);
            player.openInventory(EditInventory.getEditGui(Id));
            Main.plugin.saveConfig();

        } else {
            player.sendMessage(ChatColor.RED + "同一のIDが存在します!");
        }

    }

}
