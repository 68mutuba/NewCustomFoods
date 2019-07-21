package jp.titleserver.NewCustomFoods.Utils;

import jp.titleserver.NewCustomFoods.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class SetInitialItem {

    public static Inventory setEditGuiInitialItem(Inventory inv) {
        new BukkitRunnable() {
            @Override
            public void run() {
                //BLUE_GLASS_PANE
                ItemStack is = new ItemStack(Material.STAINED_GLASS_PANE);
                ItemMeta im = is.getItemMeta();
                List<String> lore = new ArrayList<>();

                is.setDurability((short)3);
                im.setDisplayName(ChatColor.RESET + "");
                is.setItemMeta(im);

                for (int i = 2; i <= 24; i++) {
                    if (i == 2 ||
                            i == 3 ||
                            i == 5 ||
                            i == 6 ||
                            i == 9 ||
                            i == 10 ||
                            i == 11 ||
                            i == 15 ||
                            i == 16 ||
                            i == 17 ||
                            i == 20 ||
                            i == 21 ||
                            i == 23 ||
                            i == 24
                    ) {
                        inv.setItem(i, is);
                    }
                }

                //RED_GLASS_PANE
                is.setDurability((short)14);
                inv.setItem(4, is);
                inv.setItem(12, is);
                inv.setItem(14, is);
                inv.setItem(22, is);

                //ITEM_CHANGE
                is.setType(Material.ANVIL);
                is.setDurability((short)0);
                im.setDisplayName(ChatColor.GOLD + "アイテム");
                lore.add(ChatColor.GREEN + "クリックでアイテムを変更");
                im.setLore(lore);
                is.setItemMeta(im);

                inv.setItem(27, is);

                //CHANGE_POTION
                is.setType(Material.DRAGONS_BREATH);
                is.setDurability((short)0);
                im.setDisplayName(ChatColor.GOLD + "ポーション効果");
                lore.clear();
                lore.add(ChatColor.GREEN + "クリックでポーション効果を編集");
                im.setLore(lore);
                is.setItemMeta(im);

                inv.setItem(49, is);

                //REMOVE
                is.setType(Material.BARRIER);
                is.setDurability((short)0);
                im.setDisplayName(ChatColor.RED + "設定削除");
                lore.clear();
                lore.add(ChatColor.DARK_RED + "クリックでこのアイテムの設定を削除");
                lore.add(ChatColor.DARK_RED + "注意: 元に戻せません。");
                im.setLore(lore);
                is.setItemMeta(im);

                inv.setItem(53, is);

            }
        }.runTaskAsynchronously(Main.plugin);

        return inv;
    }

    public static Inventory setPotionEditGuiInitialItem(Inventory inv) {
        new BukkitRunnable() {
            @Override
            public void run() {
                ItemStack is = new ItemStack(Material.STAINED_GLASS_PANE);
                ItemMeta im = is.getItemMeta();
                List<String> lore = new ArrayList<>();

                //======================================================//
                is.setType(Material.SUGAR);
                is.setDurability((short)0);
                im.setDisplayName(ChatColor.GOLD + "スピード");
                lore.add(ChatColor.GREEN + "移動速度を上昇させます。");
                im.setLore(lore);
                is.setItemMeta(im);

                inv.setItem(0, is);

                //======================================================//
                is.setType(Material.SOUL_SAND);
                is.setDurability((short)0);
                im.setDisplayName(ChatColor.GOLD + "鈍足");
                lore.clear();
                lore.add(ChatColor.GREEN + "移動速度を低下させます。");
                im.setLore(lore);
                is.setItemMeta(im);

                inv.setItem(9, is);

                //======================================================//
                is.setType(Material.DIAMOND_SWORD);
                is.setDurability((short)0);
                im.setDisplayName(ChatColor.GOLD + "攻撃力上昇");
                lore.clear();
                lore.add(ChatColor.GREEN + "攻撃力を上昇させます。");
                im.setLore(lore);
                is.setItemMeta(im);

                inv.setItem(18, is);

                //======================================================//
                is.setType(Material.WOOD_SWORD);
                is.setDurability((short)0);
                im.setDisplayName(ChatColor.GOLD + "攻撃力低下");
                lore.clear();
                lore.add(ChatColor.GREEN + "攻撃力を低下させます。");
                im.setLore(lore);
                is.setItemMeta(im);

                inv.setItem(27, is);

                //======================================================//
                is.setType(Material.DIAMOND_PICKAXE);
                is.setDurability((short)0);
                im.setDisplayName(ChatColor.GOLD + "採掘速度上昇");
                lore.clear();
                lore.add(ChatColor.GREEN + "採掘速度を上昇させます。");
                im.setLore(lore);
                is.setItemMeta(im);

                inv.setItem(36, is);

                //======================================================//
                is.setType(Material.WOOD_PICKAXE);
                is.setDurability((short)0);
                im.setDisplayName(ChatColor.GOLD + "採掘速度低下");
                lore.clear();
                lore.add(ChatColor.GREEN + "採掘速度を低下させます。");
                im.setLore(lore);
                is.setItemMeta(im);

                inv.setItem(45, is);

                //======================================================//
                is.setType(Material.GOLD_INGOT);
                is.setDurability((short)0);
                im.setDisplayName(ChatColor.GOLD + "幸運");
                lore.clear();
                lore.add(ChatColor.GREEN + "得られる物のレアリティを上昇させます。");
                im.setLore(lore);
                is.setItemMeta(im);

                inv.setItem(2, is);

                //======================================================//
                is.setType(Material.NETHER_BRICK_ITEM);
                is.setDurability((short)0);
                im.setDisplayName(ChatColor.GOLD + "不運");
                lore.clear();
                lore.add(ChatColor.GREEN + "得られる物のレアリティを低下させます。");
                im.setLore(lore);
                is.setItemMeta(im);

                inv.setItem(11, is);

                //======================================================//
                is.setType(Material.INK_SACK);
                is.setDurability((short)1);
                im.setDisplayName(ChatColor.GOLD + "即時回復");
                lore.clear();
                lore.add(ChatColor.GREEN + "即時にHPを回復させます。");
                im.setLore(lore);
                is.setItemMeta(im);

                inv.setItem(20, is);

                //======================================================//
                is.setType(Material.FLINT);
                is.setDurability((short)0);
                im.setDisplayName(ChatColor.GOLD + "即時ダメージ");
                lore.clear();
                lore.add(ChatColor.GREEN + "即時にダメージを与えます。");
                im.setLore(lore);
                is.setItemMeta(im);

                inv.setItem(29, is);

                //======================================================//
                is.setType(Material.SPECKLED_MELON);
                is.setDurability((short)0);
                im.setDisplayName(ChatColor.GOLD + "HP持続回復");
                lore.clear();
                lore.add(ChatColor.GREEN + "HPを持続的に回復させます。");
                im.setLore(lore);
                is.setItemMeta(im);

                inv.setItem(38, is);

                //======================================================//
                is.setType(Material.SKULL_ITEM);
                is.setDurability((short)1);
                im.setDisplayName(ChatColor.GOLD + "ウィザー");
                lore.clear();
                lore.add(ChatColor.GREEN + "定期ダメージを与えます。");
                lore.add(ChatColor.GREEN + "毒と異なり死に至ります。");
                im.setLore(lore);
                is.setItemMeta(im);

                inv.setItem(47, is);

                //======================================================//
                is.setType(Material.EYE_OF_ENDER);
                is.setDurability((short)0);
                im.setDisplayName(ChatColor.GOLD + "暗視");
                lore.clear();
                lore.add(ChatColor.GREEN + "暗闇でも視界を明るくします。");
                im.setLore(lore);
                is.setItemMeta(im);

                inv.setItem(4, is);

                //======================================================//
                is.setType(Material.ENDER_PEARL);
                is.setDurability((short)0);
                im.setDisplayName(ChatColor.GOLD + "盲目");
                lore.clear();
                lore.add(ChatColor.GREEN + "視界を暗くします。");
                im.setLore(lore);
                is.setItemMeta(im);

                inv.setItem(13, is);

                //======================================================//
                is.setType(Material.BREAD);
                is.setDurability((short)0);
                im.setDisplayName(ChatColor.GOLD + "食事ゲージ回復");
                lore.clear();
                lore.add(ChatColor.GREEN + "食事ゲージを回復させます。");
                im.setLore(lore);
                is.setItemMeta(im);

                inv.setItem(22, is);

                //======================================================//
                is.setType(Material.ROTTEN_FLESH);
                is.setDurability((short)0);
                im.setDisplayName(ChatColor.GOLD + "空腹");
                lore.clear();
                lore.add(ChatColor.GREEN + "定期的に食事ゲージを減少させます。");
                im.setLore(lore);
                is.setItemMeta(im);

                inv.setItem(31, is);

                //======================================================//
                is.setType(Material.RAW_FISH);
                is.setDurability((short)3);
                im.setDisplayName(ChatColor.GOLD + "毒");
                lore.clear();
                lore.add(ChatColor.GREEN + "定期ダメージを与えます。");
                lore.add(ChatColor.GREEN + "ウィザーと異なり死に至らない場合があります。");
                im.setLore(lore);
                is.setItemMeta(im);

                inv.setItem(40, is);

                //======================================================//
                is.setType(Material.RAW_FISH);
                is.setDurability((short)0);
                im.setDisplayName(ChatColor.GOLD + "水中呼吸");
                lore.clear();
                lore.add(ChatColor.GREEN + "空気ゲージの減少を減らします。");
                im.setLore(lore);
                is.setItemMeta(im);

                inv.setItem(49, is);

                //======================================================//
                is.setType(Material.GOLDEN_CARROT);
                is.setDurability((short)0);
                im.setDisplayName(ChatColor.GOLD + "HP一時増強");
                lore.clear();
                lore.add(ChatColor.GREEN + "使い捨ての追加HPを付与します");
                im.setLore(lore);
                is.setItemMeta(im);

                inv.setItem(6, is);

                //======================================================//
                is.setType(Material.FEATHER);
                is.setDurability((short)0);
                im.setDisplayName(ChatColor.GOLD + "浮遊");
                lore.clear();
                lore.add(ChatColor.GREEN + "プレイヤーを浮遊させます。");
                im.setLore(lore);
                is.setItemMeta(im);

                inv.setItem(15, is);

                //======================================================//
                is.setType(Material.NETHER_STAR);
                is.setDurability((short)0);
                im.setDisplayName(ChatColor.GOLD + "発光");
                lore.clear();
                lore.add(ChatColor.GREEN + "壁越しでも視認できる光を付与します。");
                im.setLore(lore);
                is.setItemMeta(im);

                inv.setItem(24, is);

                //======================================================//
                is.setType(Material.MAGMA_CREAM);
                is.setDurability((short)0);
                im.setDisplayName(ChatColor.GOLD + "火炎耐性");
                lore.clear();
                lore.add(ChatColor.GREEN + "炎ダメージを無力化させます。");
                im.setLore(lore);
                is.setItemMeta(im);

                inv.setItem(33, is);

                //======================================================//
                is.setType(Material.BONE);
                is.setDurability((short)0);
                im.setDisplayName(ChatColor.GOLD + "透明化");
                lore.clear();
                lore.add(ChatColor.GREEN + "プレイヤーを透明にさせます。");
                im.setLore(lore);
                is.setItemMeta(im);

                inv.setItem(42, is);

                //======================================================//
                is.setType(Material.SPIDER_EYE);
                is.setDurability((short)0);
                im.setDisplayName(ChatColor.GOLD + "吐き気");
                lore.clear();
                lore.add(ChatColor.GREEN + "視界を歪ませます。");
                im.setLore(lore);
                is.setItemMeta(im);

                inv.setItem(51, is);

                //======================================================//
                is.setType(Material.DIAMOND_CHESTPLATE);
                is.setDurability((short)0);
                im.setDisplayName(ChatColor.GOLD + "耐性");
                lore.clear();
                lore.add(ChatColor.GREEN + "ダメージを軽減します。");
                im.setLore(lore);
                is.setItemMeta(im);

                inv.setItem(8, is);

                //======================================================//
                is.setType(Material.RABBIT_FOOT);
                is.setDurability((short)0);
                im.setDisplayName(ChatColor.GOLD + "跳躍力上昇");
                lore.clear();
                lore.add(ChatColor.GREEN + "高くジャンプできるようにします。");
                im.setLore(lore);
                is.setItemMeta(im);

                inv.setItem(17, is);

                //======================================================//
                is.setType(Material.TOTEM);
                is.setDurability((short)0);
                im.setDisplayName(ChatColor.GOLD + "HP持続増強");
                lore.clear();
                lore.add(ChatColor.GREEN + "回復する追加ハートを付与させます。");
                im.setLore(lore);
                is.setItemMeta(im);

                inv.setItem(26, is);

                //======================================================//
                is.setType(Material.DRAGONS_BREATH);
                is.setDurability((short)0);
                im.setDisplayName(ChatColor.GOLD + "現在の効果を確認");
                lore.clear();
                lore.add(ChatColor.GREEN + "現在フードに付与されている効果を確認します。");
                im.setLore(lore);
                is.setItemMeta(im);

                inv.setItem(44, is);

                //======================================================//
                is.setType(Material.BARRIER);
                is.setDurability((short)0);
                im.setDisplayName(ChatColor.RED + "全ての効果を削除");
                lore.clear();
                lore.add(ChatColor.GREEN + "現在フードに付与されている効果を全て削除します。");
                im.setLore(lore);
                is.setItemMeta(im);

                inv.setItem(53, is);

            }
        }.runTaskAsynchronously(Main.plugin);

        return inv;
    }

}
