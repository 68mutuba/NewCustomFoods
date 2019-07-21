package jp.titleserver.NewCustomFoods.Gui;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ChatFlagPlayer {

    public static Map<UUID, CHAT_TYPE> ChatFlagPlayers = new HashMap<>();
    public static Map<UUID, String> ChatGetID = new HashMap<>();

    public enum CHAT_TYPE {
        ITEM,
        FOODPOINT,
        COOLDOWN,
        PARTICLE,
        COMMAND,
        HEALTHPOINT,
        SOUND
    }

    public static CHAT_TYPE getEditInventoryType(UUID uuid) {
        if (!(ChatFlagPlayers.containsKey(uuid))) {
            //NOT OPENING
            return null;
        } else {
            return ChatFlagPlayers.get(uuid);
        }
    }

    public static void remove(UUID uuid) {
        ChatFlagPlayers.remove(uuid);
        ChatGetID.remove(uuid);
    }

}
