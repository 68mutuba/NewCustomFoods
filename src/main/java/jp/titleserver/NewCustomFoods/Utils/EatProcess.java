package jp.titleserver.NewCustomFoods.Utils;

import jp.titleserver.NewCustomFoods.Main;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class EatProcess {

    public static void eat(Player player, String ID, Material type) {

        //FOODPOINT
        if (Main.plugin.getConfig().contains(ID + ".food")) {
            int foodResult = player.getFoodLevel() + Main.plugin.getConfig().getInt(ID + ".food");

            if (foodResult > 20) {
                Main.debug(player.getSaturation() + "////" + (player.getSaturation() + (double)(foodResult - 20)));
            }
            if (foodResult < 0) {
                foodResult = 0;
            }

            float satuResult = player.getSaturation() + (float)(Main.plugin.getConfig().getInt(ID + ".food") / 2);
            if (satuResult > 20) {
                satuResult = 20;
            }
            if (satuResult < 0) {
                satuResult = 0;
            }

            player.setFoodLevel(foodResult);
            player.setSaturation((satuResult));
            Main.debug(foodResult + "");
            Main.debug(player.getSaturation() + "");

        }

        //HEALTHPOINT
        if (Main.plugin.getConfig().contains(ID + ".health")) {
            double healthResult = player.getHealth() + Main.plugin.getConfig().getDouble(ID + ".health");

            if (player.getMaxHealth() <= healthResult) {
                healthResult = player.getMaxHealth();
            }

            if (healthResult < 0) {
                healthResult = 0;
            }

//            player.setHealthScale(healthResult);
            player.setHealth(healthResult);
        }

        //COOLDOWN
        if (Main.plugin.getConfig().contains(ID + ".cooldown")) {
            int cooltime = Main.plugin.getConfig().getInt(ID + ".cooldown");

            Cooldown.setCooldown(player, type, cooltime * 20);
        }

        //COMMAND
        if (Main.plugin.getConfig().contains(ID + ".command")) {
            String command = Main.plugin.getConfig().getString(ID + ".command");
            command = command.replaceAll("<player>", player.getName());

            Bukkit.getConsoleSender().sendMessage(player.getName() + " issued server command: /" + command);
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
        }

        //SOUND
        if (Main.plugin.getConfig().contains(ID + ".sound")) {
            String[] sound = Main.plugin.getConfig().getString(ID + ".sound").split(",");

            player.getLocation().getWorld().playSound(player.getLocation(), Sound.valueOf(sound[0]), Float.valueOf(sound[2]), Float.valueOf(sound[1]));
        }

        //PARTICLE
        if (Main.plugin.getConfig().contains(ID + ".particle")) {
            String[] particle = Main.plugin.getConfig().getString(ID + ".particle").split(",");

            player.getLocation().getWorld().spawnParticle(Particle.valueOf(particle[0]), player.getLocation(), Integer.parseInt(particle[1]), Double.valueOf(particle[2]), Double.valueOf(particle[3]), Double.valueOf(particle[4]));

        }

            //POTION
        if (Main.plugin.getConfig().contains(ID + ".potion")) {
            for (String st : Main.plugin.getConfig().getConfigurationSection(ID + ".potion").getKeys(false)) {
                player.addPotionEffect(
                        new PotionEffect(
                                PotionEffectType.getByName(st),
                                Main.plugin.getConfig().getInt(ID + ".potion." + st + ".duration") * 20,
                                Main.plugin.getConfig().getInt(ID + ".potion." + st + ".level") - 1,
                                false,
                                Main.plugin.getConfig().getBoolean(ID + ".potion." + st + ".particle")
                        ), true
                );
            }
        }
    }

}
