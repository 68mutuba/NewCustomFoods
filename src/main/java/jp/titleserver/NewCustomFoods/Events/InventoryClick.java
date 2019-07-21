package jp.titleserver.NewCustomFoods.Events;

import jp.titleserver.NewCustomFoods.Events.ClickProcess.ItemEdit;
import jp.titleserver.NewCustomFoods.Events.ClickProcess.PotionEdit;
import jp.titleserver.NewCustomFoods.Gui.GuiFlagPlayer;
import jp.titleserver.NewCustomFoods.Main;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClick implements Listener {

    @EventHandler
    public void InventoryClickEvent(InventoryClickEvent event) {

        if (GuiFlagPlayer.OpenPlayers.containsKey(event.getWhoClicked().getUniqueId())) {
            event.setCancelled(true);
            Main.debug("EVENTHANDLER: RUN");
            GuiFlagPlayer.INVENTORY_TYPE InvType = GuiFlagPlayer.OpenPlayers.get(event.getWhoClicked().getUniqueId());

            if (event.getClickedInventory() == null) {
                Main.debug("EVENTHANDLER: INVENTORY NULL");
                return;
            }

            if (event.getCurrentItem().getAmount() == 0) {
                Main.debug("EVENTHANDLER: AMOUNT ZERO");
                return;
            }

            if (event.getClickedInventory().getName().contains("container.inventory")) {
                Main.debug("EVENTHANDLER: CLICK PLAYER INVENTORY");
                return;
            }

            if (InvType == GuiFlagPlayer.INVENTORY_TYPE.ITEM_EDIT) {
                Main.debug("EVENTHANDLER: MATCH ITEM_EDIT");
                ((Player)event.getWhoClicked()).playSound(event.getWhoClicked().getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 1.0f);
                ItemEdit.ClickItemEditInventory((Player)event.getWhoClicked(), event.getSlot());
                return;

            }

            if (InvType == GuiFlagPlayer.INVENTORY_TYPE.POTION_EDIT) {
                Main.debug("EVENTHANDLER: MATCH POTION_EDIT");
                ((Player)event.getWhoClicked()).playSound(event.getWhoClicked().getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 1.0f);
                PotionEdit.ClickPotionEditInventory((Player)event.getWhoClicked(), event.getSlot());
                return;

            } else {
                Main.debug("ERROR: DOES NOT MATCH INVENTORY_TYPE");
                return;
            }
        }

    }

}
