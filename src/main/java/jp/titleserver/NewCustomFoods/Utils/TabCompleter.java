package jp.titleserver.NewCustomFoods.Utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TabCompleter implements org.bukkit.command.TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (!command.getName().equalsIgnoreCase("customfood")) return null;
        if (!sender.hasPermission("CustomFoods.op")) return null;
        if (args.length == 1) {
            if (args[0].length() == 0) {
                return Arrays.asList("create", "edit", "give", "list", "save", "reload");
            } else {
                //入力されている文字列と先頭一致
                if ("create".startsWith(args[0])) {
                    return Collections.singletonList("create");
                } else if ("edit".startsWith(args[0])) {
                    return Collections.singletonList("edit");
                } else if ("give".startsWith(args[0])) {
                    return Collections.singletonList("give");
                } else if ("list".startsWith(args[0])) {
                    return Collections.singletonList("list");
                } else if ("save".startsWith(args[0])) {
                    return Collections.singletonList("save");
                } else if ("reload".startsWith(args[0])) {
                    return Collections.singletonList("reload");
                }
            }
        } else if (args.length == 2) {
            if (args[0].equals("edit") || args[0].equals("give")) {
                if (FoodsLib.getItemList().keySet().isEmpty()) {
                    return null;
                }

                if (args[1].length() == 0) {
                    return new ArrayList<>(FoodsLib.getItemList().keySet());
                } else {
                    List<String> ret = new ArrayList<>();
                    for (String st : FoodsLib.getItemList().keySet()) {
                        if (st.startsWith(args[1])) {
                            ret.add(st);
                        }
                    }

                    if (ret.isEmpty()) {
                        return null;
                    } else {
                        return ret;
                    }
                }
            }
        }
        return null;
    }

}
