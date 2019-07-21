package jp.titleserver.NewCustomFoods.Events;

import jp.titleserver.NewCustomFoods.Events.ClickProcess.PotionEdit;
import jp.titleserver.NewCustomFoods.Gui.ChatFlagPlayer;
import jp.titleserver.NewCustomFoods.Gui.GuiFlagPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {

    @EventHandler
    public void PlayerQuitEvent(PlayerQuitEvent event) {
        PotionEdit.PotionEditFlagPlayers.remove(event.getPlayer().getUniqueId());
        GuiFlagPlayer.OpenPlayers.remove(event.getPlayer().getUniqueId());
        ChatFlagPlayer.ChatGetID.remove(event.getPlayer().getUniqueId());
        GuiFlagPlayer.GuiGetID.remove(event.getPlayer().getUniqueId());
        InventoryClose.OneTime.remove(event.getPlayer().getUniqueId());
    }

}
