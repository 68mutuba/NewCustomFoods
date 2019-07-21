package jp.titleserver.NewCustomFoods.Events;

import jp.titleserver.NewCustomFoods.Main;
import jp.titleserver.NewCustomFoods.Utils.Cooldown;
import jp.titleserver.NewCustomFoods.Utils.EatProcess;
import jp.titleserver.NewCustomFoods.Utils.FoodsLib;
import org.bukkit.GameMode;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class PlayerInteract implements Listener {

    @EventHandler
    public void PlayerInteractEvent(PlayerInteractEvent event) {

        Main.debug("EVENT RUN");

        if (event.getPlayer().getGameMode() == GameMode.SPECTATOR) return;

        if (!(event.hasItem())) return;
        if (!(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) return;

        ItemStack is = new ItemStack(event.getPlayer().getInventory().getItemInMainHand());
        is.setAmount(1);
        Map<String, ItemStack> map = new HashMap<>(FoodsLib.getItemList());
        String ID = null;
        if (map.containsValue(is)) {
            for (String st : map.keySet()) {
                ItemStack f = map.get(st);
                f.setAmount(1);
                if (f.equals(is)) {
                    ID = st;
                    Main.debug(st);
                    break;
                }
            }
            if (ID == null) {
                return;
            }
        } else {
            return;
        }

        Main.debug("EVENT #101");

        event.setCancelled(true);

        if (Cooldown.inCooldown(event.getPlayer(), is.getType())) return;

        //INFINITY
        if (Main.plugin.getConfig().contains(ID + ".infinity")) {
            if (!(Main.plugin.getConfig().getBoolean(ID + ".infinity"))) {
                int amount = event.getPlayer().getInventory().getItemInMainHand().getAmount();
                event.getPlayer().getInventory().getItemInMainHand().setAmount(amount - 1);
            }
        } else {
            int amount = event.getPlayer().getInventory().getItemInMainHand().getAmount();
            event.getPlayer().getInventory().getItemInMainHand().setAmount(amount - 1);
        }

        EatProcess.eat(event.getPlayer(), ID, is.getType());

    }

    @EventHandler
    public void PlayerInteractOffEvent(PlayerInteractEvent event) {

        Main.debug("EVENT RUN");

        if (!(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) return;

        ItemStack is = new ItemStack(event.getPlayer().getInventory().getItemInOffHand());
        is.setAmount(1);
        Map<String, ItemStack> map = new HashMap<>(FoodsLib.getItemList());
        String ID = null;
        if (map.containsValue(is)) {
            for (String st : map.keySet()) {
                ItemStack f = map.get(st);
                f.setAmount(1);
                if (f.equals(is)) {
                    ID = st;
                    Main.debug(st);
                    break;
                }
            }
            if (ID == null) {
                return;
            }
        } else {
            return;
        }

        Main.debug("EVENT #101");

        event.setCancelled(true);

    }

}
