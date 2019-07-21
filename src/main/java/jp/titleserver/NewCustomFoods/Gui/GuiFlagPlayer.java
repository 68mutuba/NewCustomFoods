package jp.titleserver.NewCustomFoods.Gui;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GuiFlagPlayer {

    public static Map<UUID, INVENTORY_TYPE> OpenPlayers = new HashMap<>();
    public static Map<UUID, String> GuiGetID = new HashMap<>();

    public enum INVENTORY_TYPE {
        ITEM_EDIT,
        POTION_EDIT
    }

    public static INVENTORY_TYPE getEditInventoryType(UUID uuid) {
        if (!(OpenPlayers.containsKey(uuid))) {
            //NOT OPENING
            return null;
        } else {
            return OpenPlayers.get(uuid);
        }
    }

}
