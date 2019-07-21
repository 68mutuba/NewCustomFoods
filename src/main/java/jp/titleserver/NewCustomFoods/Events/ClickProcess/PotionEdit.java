package jp.titleserver.NewCustomFoods.Events.ClickProcess;

import jp.titleserver.NewCustomFoods.Events.InventoryClose;
import jp.titleserver.NewCustomFoods.Gui.ChatFlagPlayer;
import jp.titleserver.NewCustomFoods.Gui.GuiFlagPlayer;
import jp.titleserver.NewCustomFoods.Main;
import jp.titleserver.NewCustomFoods.Utils.HelpMessage;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class PotionEdit {

    public static Map<UUID, POTIONS> PotionEditFlagPlayers = new HashMap<>();

    public static void ClickPotionEditInventory(Player player, int slot) {

//        new BukkitRunnable() {
//            @Override
//            public void run() {
                String ID = GuiFlagPlayer.GuiGetID.get(player.getUniqueId());

                player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 1.0f);

                if (slot == 0) {
                    PotionEditFlagPlayers.put(player.getUniqueId(), POTIONS.SPEED);
                } else if (slot == 9) {
                    PotionEditFlagPlayers.put(player.getUniqueId(), POTIONS.SLOW);
                } else if (slot == 18) {
                    PotionEditFlagPlayers.put(player.getUniqueId(), POTIONS.INCREASE_DAMAGE);
                } else if (slot == 27) {
                    PotionEditFlagPlayers.put(player.getUniqueId(), POTIONS.WEAKNESS);
                } else if (slot == 36) {
                    PotionEditFlagPlayers.put(player.getUniqueId(), POTIONS.FAST_DIGGING);
                } else if (slot == 45) {
                    PotionEditFlagPlayers.put(player.getUniqueId(), POTIONS.SLOW_DIGGING);
                } else if (slot == 2) {
                    PotionEditFlagPlayers.put(player.getUniqueId(), POTIONS.LUCK);
                } else if (slot == 11) {
                    PotionEditFlagPlayers.put(player.getUniqueId(), POTIONS.UNLUCK);
                } else if (slot == 20) {
                    PotionEditFlagPlayers.put(player.getUniqueId(), POTIONS.HEAL);
                } else if (slot == 29) {
                    PotionEditFlagPlayers.put(player.getUniqueId(), POTIONS.HARM);
                } else if (slot == 38) {
                    PotionEditFlagPlayers.put(player.getUniqueId(), POTIONS.REGENERATION);
                } else if (slot == 47) {
                    PotionEditFlagPlayers.put(player.getUniqueId(), POTIONS.WITHER);
                } else if (slot == 4) {
                    PotionEditFlagPlayers.put(player.getUniqueId(), POTIONS.NIGHT_VISION);
                } else if (slot == 13) {
                    PotionEditFlagPlayers.put(player.getUniqueId(), POTIONS.BLINDNESS);
                } else if (slot == 22) {
                    PotionEditFlagPlayers.put(player.getUniqueId(), POTIONS.SATURATION);
                } else if (slot == 31) {
                    PotionEditFlagPlayers.put(player.getUniqueId(), POTIONS.HUNGER);
                } else if (slot == 40) {
                    PotionEditFlagPlayers.put(player.getUniqueId(), POTIONS.POISON);
                } else if (slot == 49) {
                    PotionEditFlagPlayers.put(player.getUniqueId(), POTIONS.WATER_BREATHING);
                } else if (slot == 6) {
                    PotionEditFlagPlayers.put(player.getUniqueId(), POTIONS.ABSORPTION);
                } else if (slot == 15) {
                    PotionEditFlagPlayers.put(player.getUniqueId(), POTIONS.LEVITATION);
                } else if (slot == 24) {
                    PotionEditFlagPlayers.put(player.getUniqueId(), POTIONS.GLOWING);
                } else if (slot == 33) {
                    PotionEditFlagPlayers.put(player.getUniqueId(), POTIONS.FIRE_RESISTANCE);
                } else if (slot == 42) {
                    PotionEditFlagPlayers.put(player.getUniqueId(), POTIONS.INVISIBILITY);
                } else if (slot == 51) {
                    PotionEditFlagPlayers.put(player.getUniqueId(), POTIONS.CONFUSION);
                } else if (slot == 8) {
                    PotionEditFlagPlayers.put(player.getUniqueId(), POTIONS.DAMAGE_RESISTANCE);
                } else if (slot == 17) {
                    PotionEditFlagPlayers.put(player.getUniqueId(), POTIONS.JUMP);
                } else if (slot == 26) {
                    PotionEditFlagPlayers.put(player.getUniqueId(), POTIONS.HEALTH_BOOST);
                } else if (slot == 44) {

                    HelpMessage.checkEffects(player, ID);

                    ChatFlagPlayer.remove(player.getUniqueId());
                    PotionEdit.PotionEditFlagPlayers.remove(player.getUniqueId());

                    player.closeInventory();

                    Main.debug("RETUN");
                    return;

                } else if (slot == 53) {
                    //DETELE_ALL
                    try {
                        Main.plugin.getConfig().set(ID + ".potion", null);
                        Main.plugin.saveConfig();
                        player.sendMessage(ChatColor.GREEN + "設定済みの全効果を削除しました。");
                    } catch (Exception error) {
                        player.sendMessage(ChatColor.RED + "全効果削除中にエラーが発生しました。");
                    }
                    Main.debug("RETUN");
                    return;
                }

                if (Main.plugin.getConfig().contains(ID + ".potion." + PotionEditFlagPlayers.get(player.getUniqueId()))) {
                    player.sendMessage(ChatColor.RED + "その効果は既に設定されているので、上書きします。");
                }
                player.sendMessage(ChatColor.GOLD + "レベル 秒数 をスペースを空けて入力してください。");
                player.sendMessage(ChatColor.GOLD + "※必要であれば、パーティクルの有無もその後ろに記入してください。");
                player.sendMessage(ChatColor.GOLD + "効果を削除する場合は「delete」もしくは「remove」と入力してください。");
                player.sendMessage(ChatColor.GOLD + "キャンセルするには「cancel」と入力してください。");

                InventoryClose.OneTime.add(player.getUniqueId());
                player.closeInventory();
//            }
//        }.runTaskAsynchronously(Main.plugin);
    }

    public enum POTIONS {
        SPEED,
        SLOW,
        INCREASE_DAMAGE,
        WEAKNESS,
        FAST_DIGGING,
        SLOW_DIGGING,
        LUCK,
        UNLUCK,
        HEAL,
        HARM,
        REGENERATION,
        WITHER,
        NIGHT_VISION,
        BLINDNESS,
        SATURATION,
        HUNGER,
        POISON,
        WATER_BREATHING,
        ABSORPTION,
        LEVITATION,
        GLOWING,
        FIRE_RESISTANCE,
        INVISIBILITY,
        CONFUSION,
        DAMAGE_RESISTANCE,
        JUMP,
        HEALTH_BOOST
    }

}
