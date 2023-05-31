package game;

/**
 * This class contains constants used in the game.
 */
public class Constants {

    /**
     * The width of the game canvas.
     */
    public static final int CANVAS_WIDTH = 1280;

    /**
     * The height of the game canvas.
     */
    public static final int CANVAS_HEIGHT = 720;

    /**
     * The threshold value for closeness.
     */
    public static final int CLOSENESS_THRESHOLD = 10;

    /**
     * The speed of the player.
     */
    public static final int PLAYER_SPEED = 30;

    /**
     * The speed of the basic enemy.
     */
    public static final int BASIC_ENEMY_SPEED = 3;

    /**
     * The padding for the health bar.
     */
    public static final int HEALTH_BAR_PADDING = 7;

    /**
     * The gap width for the health bar.
     */
    public static final int HEALTH_BAR_GAP_WIDTH = 3;

    /**
     * The width of the door in pixels.
     */
    public static final int DOOR_WIDTH = 80;

    /**
     * The height of the door in pixels.
     */
    public static final int DOOR_HEIGHT = 100;

    /**
     * The attack damage of the basic enemy.
     */
    public static final int BASIC_ENEMY_ATTACK_DAMAGE = 3;

    /**
     * The attack damage of the basic player.
     */
    public static final int BASIC_PLAYER_ATTACK_DAMAGE = 1;

    /**
     * The delay in milliseconds for health regeneration.
     */
    public static final long HEALTH_REGEN_DELAY = 5000;

    /**
     * The amount of health regenerated per regeneration interval.
     */
    public static final int HEALTH_REGEN_AMOUNT = 1;

    /**
     * The delay in milliseconds between damage ticks.
     */
    public static final int DELAY_BETWEEN_DAMAGE_TICKS = 1000;

    /**
     * The delay in milliseconds for weapon deployment.
     */
    public static final int DELAY_FOR_WEAPON_DEPLOYMENT = 750;

    /**
     * The speed of the projectile.
     */
    public static final int PROJECTILE_SPEED = 7;

    /**
     * Constants related to NPCs.
     */
    public static class NPCS {

        /**
         * The width of the Adam NPC.
         */
        public static final int ADAM_NPC_WIDTH = 60;

        /**
         * The height of the Adam NPC.
         */
        public static final int ADAM_NPC_HEIGHT = 100;

        /**
         * The height of the sprite dealer.
         */
        public static final int SPRITE_DEALER_HEIGHT = 600;

        /**
         * The width of the sprite dealer calculated based on the height.
         */
        public static final int SPRITE_DEALER_WIDTH = (int) (0.655 * SPRITE_DEALER_HEIGHT);
    }
}
