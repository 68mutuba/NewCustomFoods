package jp.titleserver.NewCustomFoods.Utils;

import jp.titleserver.NewCustomFoods.Main;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class FoodsLib {

    private static Map<String, ItemStack> toItemStack = new HashMap<>();

    public static void load() {
        toItemStack.clear();
        Main.debug("内部データベース消去");

        for (String st : Main.plugin.getConfig().getKeys(false)) {

            if (!(Main.plugin.getConfig().contains(st + ".item"))) {
                Main.debug("Can't Found ItemStack: " + st);
                continue;
            }

            try {
                ItemStack is = new ItemStack((ItemStack)Main.plugin.getConfig().get(st + ".item"));
                is.setAmount(1);
                toItemStack.put(st, is);
            } catch(Exception error) {
                Main.debug("Can't load ItemStack: " + st);
                continue;
            }

        }
    }

    public static boolean addItem(String Id, ItemStack is) {
        if (toItemStack.containsKey(Id)) {
            Main.debug(Id + "は既に存在するので登録を中止しました。");
            return false;  //FAILED
        } else {
            is.setAmount(1);
            toItemStack.put(Id, is);
            Main.plugin.getConfig().set(Id + ".item", is);
            return true;  //SUCCESS
        }
    }

    public static ItemStack getItem(String Id) {
        if (toItemStack.containsKey(Id)) {
            return toItemStack.get(Id);
        } else {
            return null;
        }
    }

    public static boolean changeItem(String Id, ItemStack is) {
        if (toItemStack.containsKey(Id)) {
            try {
                toItemStack.put(Id, is);
                return true;
            } catch(Exception error) {
                return false;
            }
        } else {
            Main.debug("NOT EXIST ID!");
            return false;
        }
    }

    public static Map<String, ItemStack> getItemList() {
        return toItemStack;
    }

    public static boolean remove(String Id) {
        if (toItemStack.containsKey(Id)) {
            toItemStack.remove(Id);
            Main.plugin.getConfig().set(Id, null);
            return true;  //SUCCESS
        } else {
            return false;  //FAILED
        }
    }

    public static void clear() {
        toItemStack.clear();
    }
}
