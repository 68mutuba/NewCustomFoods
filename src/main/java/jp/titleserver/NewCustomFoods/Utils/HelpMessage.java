package jp.titleserver.NewCustomFoods.Utils;

import jp.titleserver.NewCustomFoods.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelpMessage {

    public static void helpMessage(CommandSender sender) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e============&l[ &6&lCustomFoods &e&l]&e============"));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7/cf &acreate &b<ID>"));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7/cf &aedit &b<ID>"));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7/cf &agive &b<ID> &e[amount] &c[player]"));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7/cf &alist"));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7/cf &asave"));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7/cf &areload"));
    }

    public static void checkEffects(Player player, String ID) {
        player.sendMessage(ChatColor.AQUA + "================[" + ID + "のポーション情報" + "]================");

        if (!(Main.plugin.getConfig().contains(ID + ".potion"))) {
            player.sendMessage(ChatColor.RED + "ポーション効果はありません。");
            return;
        }

        for (String st : Main.plugin.getConfig().getConfigurationSection(ID + ".potion").getKeys(false)) {
            player.sendMessage(ChatColor.GOLD + st);
            player.sendMessage(ChatColor.GRAY + "  LEVEL: " + ChatColor.GREEN + Main.plugin.getConfig().getInt(ID + ".potion." + st + ".level"));
            player.sendMessage(ChatColor.GRAY + "  DURATION: " + ChatColor.GREEN + Main.plugin.getConfig().getInt(ID + ".potion." + st + ".duration"));
            player.sendMessage(ChatColor.GRAY + "  PARTICLE: " + ChatColor.GREEN + Main.plugin.getConfig().getBoolean(ID + ".potion." + st + ".particle"));
        }
    }

}
