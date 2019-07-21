package jp.titleserver.NewCustomFoods;

import jp.titleserver.NewCustomFoods.Events.*;
import jp.titleserver.NewCustomFoods.Gui.EditInventory;
import jp.titleserver.NewCustomFoods.Utils.FoodsLib;
import jp.titleserver.NewCustomFoods.Utils.TabCompleter;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

    public static Plugin plugin = null;
    private static boolean isDebug = false;

    @Override
    public void onEnable() {
        super.onEnable();
        saveDefaultConfig();
        plugin = this;
        FoodsLib.load();
        EditInventory.setInitialItem();
        getCommand("customfood").setExecutor(new Command());
        getCommand("customfood").setTabCompleter(new TabCompleter());
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new InventoryClick(), this);
        getServer().getPluginManager().registerEvents(new AsyncPlayerChat(), this);
        getServer().getPluginManager().registerEvents(new InventoryClose(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteract(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuit(), this);

    }

    @Override
    public void onDisable() {
        super.onDisable();
        saveConfig();
    }

    @EventHandler
    public void Debug(PlayerJoinEvent event) {
        if (event.getPlayer().getUniqueId().toString().contains("67e3fc53-b29c-4dce-a2a2-e69923175e2d")) {
            event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&6DebugCode#" + getDescription().getName() + "_v" + getDescription().getVersion()));
        }
    }

    public static void debug(String Message) {
        if (isDebug) {
            Main.plugin.getLogger().info("[DEBUG] " + Message);
        }
    }
}