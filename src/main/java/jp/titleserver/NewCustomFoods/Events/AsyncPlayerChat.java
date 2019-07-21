package jp.titleserver.NewCustomFoods.Events;

import jp.titleserver.NewCustomFoods.Events.ClickProcess.PotionEdit;
import jp.titleserver.NewCustomFoods.Gui.ChatFlagPlayer;
import jp.titleserver.NewCustomFoods.Gui.EditInventory;
import jp.titleserver.NewCustomFoods.Gui.GuiFlagPlayer;
import jp.titleserver.NewCustomFoods.Main;
import jp.titleserver.NewCustomFoods.Utils.FoodsLib;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;

public class AsyncPlayerChat implements Listener {

    @EventHandler
    public void AsyncPlayerChatEvent(AsyncPlayerChatEvent event) {
        if (ChatFlagPlayer.ChatFlagPlayers.containsKey(event.getPlayer().getUniqueId())) {
            event.setCancelled(true);
            ChatFlagPlayer.CHAT_TYPE type = ChatFlagPlayer.getEditInventoryType(event.getPlayer().getUniqueId());

            if (event.getMessage().equalsIgnoreCase("cancel")) {
                ChatFlagPlayer.remove(event.getPlayer().getUniqueId());
                GuiFlagPlayer.OpenPlayers.remove(event.getPlayer().getUniqueId());
                ChatFlagPlayer.ChatGetID.remove(event.getPlayer().getUniqueId());
                GuiFlagPlayer.GuiGetID.remove(event.getPlayer().getUniqueId());
                InventoryClose.OneTime.remove(event.getPlayer().getUniqueId());
                event.getPlayer().sendMessage(ChatColor.RED + "キャンセルしました!");
                return;
            }

            if (type == ChatFlagPlayer.CHAT_TYPE.ITEM) {
                if (event.getMessage().equalsIgnoreCase("set")) {

                    String ID = ChatFlagPlayer.ChatGetID.get(event.getPlayer().getUniqueId());
                    ItemStack is = new ItemStack(event.getPlayer().getInventory().getItemInMainHand());

                    if (is.getAmount() == 0) {
                        event.getPlayer().sendMessage(ChatColor.RED + "アイテムを手に持ってください!");
                        return;
                    }

                    try {
                        Main.plugin.getConfig().set(ID + ".item", is);
                        if (!(FoodsLib.changeItem(ID, is))) {
                            event.getPlayer().sendMessage(ChatColor.RED + "アイテム変更中にエラーが発生しました。");
                            return;
                        }
                        event.getPlayer().sendMessage(ChatColor.GREEN + "アイテムを変更しました。");
                        ChatFlagPlayer.remove(event.getPlayer().getUniqueId());
                        afterrun(event.getPlayer(), ID);
                    } catch (Exception error) {
                        event.getPlayer().sendMessage(ChatColor.RED + "アイテム変更中にエラーが発生しました。");
                        return;
                    }

                } else {
                    event.getPlayer().sendMessage(ChatColor.RED + "「set」もしくは「cancel」と入力してください!");
                }

            } else if (type == ChatFlagPlayer.CHAT_TYPE.FOODPOINT) {
                int FP;
                try {
                    FP = Integer.valueOf(event.getMessage());
                } catch (Exception error) {
                    event.getPlayer().sendMessage(ChatColor.RED + "整数もしくは「cancel」と入力してください!");
                    return;
                }

                String ID = ChatFlagPlayer.ChatGetID.get(event.getPlayer().getUniqueId());

                try {
                    Main.plugin.getConfig().set(ID + ".food", FP);
                    event.getPlayer().sendMessage(ChatColor.GREEN + "食料回復値を変更しました。");
                    ChatFlagPlayer.remove(event.getPlayer().getUniqueId());
                    afterrun(event.getPlayer(), ID);

                } catch (Exception error) {
                    event.getPlayer().sendMessage(ChatColor.RED + "食料回復値変更中にエラーが発生しました。");
                }

            } else if (type == ChatFlagPlayer.CHAT_TYPE.COOLDOWN) {
                int CD;
                try {
                    if (Integer.valueOf(event.getMessage()) < 0) {
                        event.getPlayer().sendMessage(ChatColor.RED + "正の値を入力してください!");
                        return;
                    } else {
                        CD = Integer.valueOf(event.getMessage());
                    }
                } catch (Exception error) {
                    event.getPlayer().sendMessage(ChatColor.RED + "整数もしくは「cancel」と入力してください!");
                    return;
                }

                String ID = ChatFlagPlayer.ChatGetID.get(event.getPlayer().getUniqueId());

                try {
                    Main.plugin.getConfig().set(ID + ".cooldown", CD);
                    event.getPlayer().sendMessage(ChatColor.GREEN + "クールダウンを変更しました。");
                    ChatFlagPlayer.remove(event.getPlayer().getUniqueId());
                    afterrun(event.getPlayer(), ID);

                } catch (Exception error) {
                    event.getPlayer().sendMessage(ChatColor.RED + "クールダウン変更中にエラーが発生しました。");
                }

            } else if (type == ChatFlagPlayer.CHAT_TYPE.PARTICLE) {
                String[] pt = event.getMessage().toUpperCase().split(" ");
                String ID = ChatFlagPlayer.ChatGetID.get(event.getPlayer().getUniqueId());

                String effectName;
                int amount = 50;
                double x = 1.5;
                double y = 1.5;
                double z = 1.5;

                if (pt.length >= 1) {
                    try {
                        Particle.valueOf(pt[0].toUpperCase());
                        effectName = pt[0].toUpperCase();
                    } catch (Exception error) {
                        event.getPlayer().sendMessage(ChatColor.RED + "そのパーティクルは存在しません!");
                        return;
                    }

                } else {
                    event.getPlayer().sendMessage(ChatColor.RED + "フォーマットが異なります!");
                    return;
                }

                if (pt.length >= 2) {
                    try {
                        if (Integer.valueOf(pt[1]) < 0) {
                            event.getPlayer().sendMessage(ChatColor.RED + "パーティクル量は正の値である必要があります!");
                            return;
                        }
                        amount = Integer.valueOf(pt[1]);
                    } catch (Exception error) {
                        event.getPlayer().sendMessage(ChatColor.RED + "パーティクル量は数値で指定してください!");
                        return;
                    }
                }

                if (pt.length >= 3 && !(pt.length == 5)) {
                    event.getPlayer().sendMessage(ChatColor.RED + "散開距離はX,Y,Z 3つをそろえる必要があります!");
                    return;
                }

                if (pt.length == 5) {
                    try {
                        if (Double.valueOf(pt[2]) < 0 || Double.valueOf(pt[3]) < 0 || Double.valueOf(pt[4]) < 0) {
                            event.getPlayer().sendMessage(ChatColor.RED + "散開距離はそれぞれ正の値である必要があります!");
                            return;
                        }
                        x = Double.valueOf(pt[2]);
                        y = Double.valueOf(pt[3]);
                        z = Double.valueOf(pt[4]);
                    } catch (Exception error) {
                        event.getPlayer().sendMessage(ChatColor.RED + "散開距離は少数数値で指定してください!");
                        return;
                    }
                }

                try {
                    Main.plugin.getConfig().set(ID + ".particle", effectName + "," + amount + "," + x + "," + y + "," + z);
                    event.getPlayer().sendMessage(ChatColor.GREEN + "パーティクルを変更しました。");
                    ChatFlagPlayer.remove(event.getPlayer().getUniqueId());
                    afterrun(event.getPlayer(), ID);

                } catch (Exception error) {
                    event.getPlayer().sendMessage(ChatColor.RED + "パーティクル変更中にエラーが発生しました。");
                }

            } else if (type == ChatFlagPlayer.CHAT_TYPE.COMMAND) {
                if (event.getMessage().contains("stop") || event.getMessage().contains("reload") || event.getMessage().contains("op")) {
                    event.getPlayer().sendMessage(ChatColor.RED + "使用できない単語が含まれています!");
                    return;
                }
                String ID = ChatFlagPlayer.ChatGetID.get(event.getPlayer().getUniqueId());

                try {
                    Main.plugin.getConfig().set(ID + ".command", event.getMessage());
                    event.getPlayer().sendMessage(ChatColor.GREEN + "コマンドを変更しました。");
                    ChatFlagPlayer.remove(event.getPlayer().getUniqueId());
                    afterrun(event.getPlayer(), ID);

                } catch (Exception error) {
                    event.getPlayer().sendMessage(ChatColor.RED + "コマンド変更中にエラーが発生しました。");
                }

            } else if (type == ChatFlagPlayer.CHAT_TYPE.HEALTHPOINT) {
                double HP;
                try {
                    HP = Double.valueOf(event.getMessage());
                } catch (Exception error) {
                    event.getPlayer().sendMessage(ChatColor.RED + "数値もしくは「cancel」と入力してください!");
                    return;
                }

                String ID = ChatFlagPlayer.ChatGetID.get(event.getPlayer().getUniqueId());

                try {
                    Main.plugin.getConfig().set(ID + ".health", HP);
                    event.getPlayer().sendMessage(ChatColor.GREEN + "体力回復値を変更しました。");
                    ChatFlagPlayer.remove(event.getPlayer().getUniqueId());
                    afterrun(event.getPlayer(), ID);

                } catch (Exception error) {
                    event.getPlayer().sendMessage(ChatColor.RED + "体力回復値変更中にエラーが発生しました。");
                }

            } else if (type == ChatFlagPlayer.CHAT_TYPE.SOUND) {
                String[] se = event.getMessage().toUpperCase().split(" ");
                String ID = ChatFlagPlayer.ChatGetID.get(event.getPlayer().getUniqueId());

                String SE_ID = "";
                float pitch = 1.0f;
                float volume = 1.0f;

                if (se.length > 3) {
                    event.getPlayer().sendMessage(ChatColor.RED + "区切りは3つまでです!");
                    return;
                }

                if (se.length >= 1) {
                    try {
                        Sound.valueOf(se[0]);
                    } catch (Exception error) {
                        event.getPlayer().sendMessage(ChatColor.RED + "そのサウンドは存在しません!");
                        return;
                    }

                    SE_ID = se[0];
                }

                if (se.length >= 2) {
                    try {
                        pitch = Float.valueOf(se[1]);
                    } catch (Exception error) {
                        event.getPlayer().sendMessage(ChatColor.RED + "ピッチは数値で指定してください!");
                        return;
                    }
                }

                if (se.length == 3) {
                    try {
                        volume = Float.valueOf(se[2]);
                    } catch (Exception error) {
                        event.getPlayer().sendMessage(ChatColor.RED + "ボリュームは数値で指定してください!");
                        return;
                    }
                }

                try {
                    Main.plugin.getConfig().set(ID + ".sound", SE_ID + "," + pitch + "," + volume);
                    event.getPlayer().sendMessage(ChatColor.GREEN + "サウンドを変更しました。");
                    ChatFlagPlayer.remove(event.getPlayer().getUniqueId());
                    afterrun(event.getPlayer(), ID);
                } catch (Exception error) {
                    event.getPlayer().sendMessage(ChatColor.RED + "サウンド変更中にエラーが発生しました。");
                }

            }

        }

        if (PotionEdit.PotionEditFlagPlayers.containsKey(event.getPlayer().getUniqueId())) {

            event.setCancelled(true);
            String ID = GuiFlagPlayer.GuiGetID.get(event.getPlayer().getUniqueId());
            String effectName = PotionEdit.PotionEditFlagPlayers.get(event.getPlayer().getUniqueId()).name();

            if (event.getMessage().equalsIgnoreCase("cancel")) {
                PotionEdit.PotionEditFlagPlayers.remove(event.getPlayer().getUniqueId());
                GuiFlagPlayer.OpenPlayers.remove(event.getPlayer().getUniqueId());
                ChatFlagPlayer.ChatGetID.remove(event.getPlayer().getUniqueId());
                GuiFlagPlayer.GuiGetID.remove(event.getPlayer().getUniqueId());
                InventoryClose.OneTime.remove(event.getPlayer().getUniqueId());
                event.getPlayer().sendMessage(ChatColor.RED + "キャンセルしました!");
                return;
            }

            if (event.getMessage().equalsIgnoreCase("delete") || event.getMessage().equalsIgnoreCase("remove")) {
                try {
                    Main.plugin.getConfig().set(ID + ".potion." + effectName, null);
                    Main.plugin.saveConfig();
                    PotionEdit.PotionEditFlagPlayers.remove(event.getPlayer().getUniqueId());
                    GuiFlagPlayer.OpenPlayers.remove(event.getPlayer().getUniqueId());
                    ChatFlagPlayer.ChatGetID.remove(event.getPlayer().getUniqueId());
                    GuiFlagPlayer.GuiGetID.remove(event.getPlayer().getUniqueId());
                    InventoryClose.OneTime.remove(event.getPlayer().getUniqueId());
                    event.getPlayer().sendMessage(ChatColor.RED + "ポーション効果" + effectName + "を削除しました!");
                } catch (Exception error) {
                    event.getPlayer().sendMessage(ChatColor.RED + "ポーション効果の削除に失敗しました!");
                }
                return;
            }

            String[] po = event.getMessage().toUpperCase().split(" ");

            int level = 1;
            int sec = 1;
            boolean effect = false;

            if (po.length > 3) {
                event.getPlayer().sendMessage(ChatColor.RED + "区切りは3つまでです!");
                return;
            }

            if (po.length == 1) {
                event.getPlayer().sendMessage(ChatColor.RED + "区切りは1つ以上必要です!");
                return;
            }

            if (po.length >= 2) {
                try {
                    level = Integer.valueOf(po[0]);
                    sec = Integer.valueOf(po[1]);
                } catch (Exception error) {
                    event.getPlayer().sendMessage(ChatColor.RED + "レベル, 秒数は数値で指定してください!");
                    return;
                }
            }

            if (po.length == 3) {
                try {
                    effect = Boolean.valueOf(po[2]);
                } catch (Exception error) {
                    event.getPlayer().sendMessage(ChatColor.RED + "パーティクルの有無はtrue/falseで指定してください!");
                    return;
                }
            }

            try {
                boolean isExist = false;

                if (Main.plugin.getConfig().contains(ID + ".potion." + effectName)) isExist = true;

                Main.plugin.getConfig().set(ID + ".potion." + effectName + ".level", level);
                Main.plugin.getConfig().set(ID + ".potion." + effectName + ".duration", sec);
                Main.plugin.getConfig().set(ID + ".potion." + effectName + ".particle", effect);

                if (isExist) {
                    event.getPlayer().sendMessage(ChatColor.GREEN + "ポーションを上書きしました。");
                } else {
                    event.getPlayer().sendMessage(ChatColor.GREEN + "ポーションを追加しました。");
                }
                PotionEdit.PotionEditFlagPlayers.remove(event.getPlayer().getUniqueId());
                afterrun(event.getPlayer(), ID);
            } catch (Exception error) {
                event.getPlayer().sendMessage(ChatColor.RED + "ポーションを追加中にエラーが発生しました。");
            }
        }
    }

    private static void afterrun(Player player, String ID) {
        Main.plugin.saveConfig();
        GuiFlagPlayer.OpenPlayers.put(player.getUniqueId(), GuiFlagPlayer.INVENTORY_TYPE.ITEM_EDIT);
        GuiFlagPlayer.GuiGetID.put(player.getUniqueId(), ID);
        player.openInventory(EditInventory.getEditGui(ID));
    }

}
