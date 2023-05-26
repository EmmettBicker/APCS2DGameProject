package game;

public class Constants {
    public static final int CANVAS_WIDTH = 1280;
    public static final int CANVAS_HEIGHT = 720;
    public static final int CLOSENESS_THRESHOLD = 10;
    public static final int PLAYER_SPEED = 30;
    public static final int BASIC_ENEMY_SPEED = 3;
    public static final int HEALTH_BAR_PADDING = 7;
    public static final int HEALTH_BAR_GAP_WIDTH = 3;

    public static final int DOOR_WIDTH = 80; // px
    public static final int DOOR_HEIGHT = 100; // px

    public static final int BASIC_ENEMY_ATTACK_DAMAGE = 3;
    public static final int BASIC_PLAYER_ATTACK_DAMAGE = 1;

    public static final long HEALTH_REGEN_DELAY = 5000; // 5 seconds in milliseconds
    public static final int HEALTH_REGEN_AMOUNT = 1;
    public static final int DELAY_BETWEEN_DAMAGE_TICKS = 1000; // milliseconds
    public static final int DELAY_FOR_WEAPON_DEPLOYMENT = 750;
    public static final int PROJECTILE_SPEED = 7;

    public static class NPCS {
        public static final int ADAM_NPC_WIDTH = 60;
        public static final int ADAM_NPC_HEIGHT = 100;
        
        
        public static final int SPRITE_DEALER_HEIGHT = 600;
        public static final int SPRITE_DEALER_WIDTH = (int) (0.655 * SPRITE_DEALER_HEIGHT);

    }

}
