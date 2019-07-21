package jp.titleserver.NewCustomFoods.Events;

import jp.titleserver.NewCustomFoods.Gui.ChatFlagPlayer;
import jp.titleserver.NewCustomFoods.Gui.GuiFlagPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InventoryClose implements Listener {

    public static List<UUID> OneTime = new ArrayList<>();

    @EventHandler
    public void InventoryCloseEvent(InventoryCloseEvent event) {
        if (GuiFlagPlayer.OpenPlayers.containsKey(event.getPlayer().getUniqueId())) {
            if (OneTime.contains(event.getPlayer().getUniqueId())) {
                OneTime.remove(event.getPlayer().getUniqueId());
            } else {
                GuiFlagPlayer.OpenPlayers.remove(event.getPlayer().getUniqueId());
                ChatFlagPlayer.ChatGetID.remove(event.getPlayer().getUniqueId());
                GuiFlagPlayer.GuiGetID.remove(event.getPlayer().getUniqueId());
            }
        }
    }

}
