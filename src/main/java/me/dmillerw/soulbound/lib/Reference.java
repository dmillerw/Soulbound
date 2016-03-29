package me.dmillerw.soulbound.lib;

/**
 * @author dmillerw
 */
public class Reference {

    public static final String ID = "Soulbound";
    public static final String NAME = "Soulbound";
    public static final String VERSION = "1.0";

    public static final String NBT_KEY_SOULBOUND = "soulbound";
    public static final String NBT_KEY_OWNER_MOST = "owner_most";
    public static final String NBT_KEY_OWNER_LEAST = "owner_least";
    public static final String NBT_KEY_CAN_DROP = "can_drop";
    public static final String NBT_KEY_CAN_MOVE = "can_move";
    public static final String NBT_KEY_KEEP_ON_DEATH = "keep_on_death";

    public static final String COMMAND_SOULBIND = "soulbind";
    public static final String COMMAND_SOULBIND_USAGE = "/" + COMMAND_SOULBIND + " [slot] [can-drop] [can-move] [keep-on-death]";
}
