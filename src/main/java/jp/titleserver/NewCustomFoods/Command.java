package jp.titleserver.NewCustomFoods;

import jp.titleserver.NewCustomFoods.CommandProcess.Create;
import jp.titleserver.NewCustomFoods.Gui.ChatFlagPlayer;
import jp.titleserver.NewCustomFoods.Gui.EditInventory;
import jp.titleserver.NewCustomFoods.Gui.GuiFlagPlayer;
import jp.titleserver.NewCustomFoods.Utils.FoodsLib;
import jp.titleserver.NewCustomFoods.Utils.HelpMessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Command implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "プレイヤーのみ実行可能なコマンドです!");
            return true;
        }

        if (!(sender.hasPermission("CustomFoods.op"))) return true;

        Player player = (Player)sender;

        if(args.length == 0) {
            HelpMessage.helpMessage(sender);
        } else {

            switch(args[0]) {

                case "help":
                    HelpMessage.helpMessage(sender);
                    break;

                case "create":
                    if (args.length == 2) {
                        Create.create(player, args[1], new ItemStack(player.getInventory().getItemInMainHand()));
                    } else {
                        sender.sendMessage(ChatColor.RED + "IDを指定してください!");
                    }
                    break;

                case "edit":
                    if (args.length == 2) {

                        if (ChatFlagPlayer.ChatFlagPlayers.containsKey(player.getUniqueId())) {
                            sender.sendMessage(ChatColor.RED + "並行編集はできません!");
                            return true;
                        }

                        if (FoodsLib.getItem(args[1]) == null) {
                            sender.sendMessage(ChatColor.RED + "そのIDは存在しません!");
                            return true;
                        }

                        if (GuiFlagPlayer.GuiGetID.containsValue(args[1]) || ChatFlagPlayer.ChatGetID.containsValue(args[1])) {
                            sender.sendMessage(ChatColor.RED + "ほかの人との同時編集はできません!");
                            return true;
                        }

                        GuiFlagPlayer.OpenPlayers.put(player.getUniqueId(), GuiFlagPlayer.INVENTORY_TYPE.ITEM_EDIT);
                        GuiFlagPlayer.GuiGetID.put(player.getUniqueId(), args[1]);
                        player.openInventory(EditInventory.getEditGui(args[1]));
                    } else {
                        sender.sendMessage(ChatColor.RED + "IDを指定してください!");
                    }
                    break;

                case "give":
                    if (args.length == 2) {
                        ItemStack is = FoodsLib.getItem(args[1]);
                        if (is == null) {
                            sender.sendMessage(ChatColor.RED + "そのIDは存在しません!");
                            return true;
                        }
                        is = new ItemStack(FoodsLib.getItem(args[1]));

                        is.setAmount(1);
                        player.getInventory().addItem(is);
                        sender.sendMessage(ChatColor.GREEN + args[1] + "を入手しました。");

                        return true;

                    } else if (args.length == 3) {
                        ItemStack is = FoodsLib.getItem(args[1]);
                        if (is == null) {
                            sender.sendMessage(ChatColor.RED + "そのIDは存在しません!");
                            return true;
                        }
                        is = new ItemStack(FoodsLib.getItem(args[1]));

                        int amount = 1;
                        try {
                            amount = Integer.valueOf(args[2]);
                        } catch (Exception error) {
                            sender.sendMessage(ChatColor.RED + "個数は数値指定してください!");
                            return true;
                        }

                        is.setAmount(amount);

                        player.getInventory().addItem(is);
                        sender.sendMessage(ChatColor.GREEN + args[1] + "を" + amount + "個入手しました。");

                        return true;

                    } else if (args.length == 4) {
                        ItemStack is = FoodsLib.getItem(args[1]);
                        if (is == null) {
                            sender.sendMessage(ChatColor.RED + "そのIDは存在しません!");
                            return true;
                        }
                        is = new ItemStack(FoodsLib.getItem(args[1]));

                        int amount = 1;
                        try {
                            amount = Integer.valueOf(args[2]);
                        } catch (Exception error) {
                            sender.sendMessage(ChatColor.RED + "個数は数値指定してください!");
                            return true;
                        }

                        is.setAmount(amount);

                        try {
                            Player target = Bukkit.getPlayer(args[3]);
                            target.getInventory().addItem(is);
                            sender.sendMessage(ChatColor.GREEN + args[1] + "を" + amount + "個 " + target.getName() + "に渡しました。");
                        } catch (Exception error) {
                            sender.sendMessage(ChatColor.RED + "プレイヤーが存在しません!");
                        }

                        return true;

                    } else {
                        sender.sendMessage(ChatColor.RED + "IDを指定してください!");
                    }
                    break;

                case "list":
                    sender.sendMessage(ChatColor.GOLD + "===================[ FOODS ]===================");
                    for (String st : FoodsLib.getItemList().keySet()) {
                        sender.sendMessage(ChatColor.GRAY + "  - " + ChatColor.GREEN + st);
                    }
                    break;

                case "save":
                    try {
                        Main.plugin.saveConfig();
                        sender.sendMessage(ChatColor.GREEN + "セーブを完了しました。");
                    } catch (Exception error) {
                        sender.sendMessage(ChatColor.RED + "セーブ中にエラーが発生しました!");
                    }
                    break;

                case "reload":
                    try {
                        Main.plugin.reloadConfig();
                        FoodsLib.load();
                        sender.sendMessage(ChatColor.GREEN + "リロードを完了しました。");
                    } catch (Exception error) {
                        sender.sendMessage(ChatColor.RED + "リロード中にエラーが発生しました!");
                    }
                    break;

                default:
                    sender.sendMessage(ChatColor.RED + "そのコマンドは存在しません。");
                    break;
            }
        }
        return true;
    }

}
