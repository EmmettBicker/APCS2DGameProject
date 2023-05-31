package game.utils;

import game.Constants;
import game.GameStates;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.Point;
import java.util.ArrayList;
import game.wallFactory.Wall;
import game.wallFactory.WallFactory;

/**
 * The GeneralUtils class provides utility methods for various general operations in the game.
 * It contains methods for checking proximity, centering door end destination, getting a rectangle from a point and image,
 * handling wall collisions, and checking collision with walls.
 */
public class GeneralUtils {
    private GeneralUtils() {
    }

    /**
     * Checks if two points are close to each other within a threshold.
     *
     * @param p1 the first point
     * @param p2 the second point
     * @return true if the points are close to each other, false otherwise
     */
    public static boolean isClose(Point p1, Point p2) {
        return Math.abs(p1.getX() - p2.getX()) < Constants.CLOSENESS_THRESHOLD
                && Math.abs(p1.getY() - p2.getY()) < Constants.CLOSENESS_THRESHOLD;
    }

    /**
     * Centers the end destination point of a door.
     *
     * @param p the point representing the door's position
     * @return the centered end destination point
     */
    public static Point centerDoorEndDestination(Point p) {
        int playerHeight = 60;
        int playerWidth = 56;
        return new Point(p.x + Constants.DOOR_WIDTH / 2 - playerWidth / 2,
                p.y + Constants.DOOR_HEIGHT / 2 - playerHeight / 2);
    }

    /**
     * Creates a rectangle from a point and an image.
     *
     * @param p the point representing the top-left corner of the rectangle
     * @param b the image used to determine the width and height of the rectangle
     * @return the created rectangle
     */
    public static Rectangle getRectangleFromPointAndImage(Point p, BufferedImage b) {
        return new Rectangle((int) p.getX(), (int) p.getY(), b.getWidth(), b.getHeight());
    }

    /**
     * Handles wall collision by modifying the current position.
     *
     * @param interactingHitbox the hitbox representing the interacting object
     * @param currentPos        the current position that will be modified in case of collision
     */
    public static void wallCollision(Rectangle interactingHitbox, Point currentPos) {
        GameStates.GameplayStates currentRoom = GameStates.getGameplayState();
        ArrayList<Wall> currentRoomWalls = WallFactory.getRoomWallArray(currentRoom);

        for (Wall wall : currentRoomWalls) {
            Rectangle wallHitbox = wall.getWallHitBox();
            double wallWidth = wallHitbox.getWidth();
            double wallHeight = wallHitbox.getHeight();

            if (interactingHitbox.intersects(wallHitbox)) {
                double dx = interactingHitbox.getCenterX() - wallHitbox.getCenterX();
                double dy = interactingHitbox.getCenterY() - wallHitbox.getCenterY();

                if (Math.abs(dx) / wallWidth > Math.abs(dy) / wallHeight) {
                    if (dx < 0) {
                        currentPos.x = (int) (wallHitbox.getX() - interactingHitbox.getWidth());
                    } else {
                        currentPos.x = (int) (wallHitbox.getX() + wallHitbox.getWidth());
                    }
                } else {
                    if (dy < 0) {
                        currentPos.y = (int) (wallHitbox.getY() - interactingHitbox.getHeight());
                    } else {
                        currentPos.y = (int) (wallHitbox.getY() + wallHitbox.getHeight());
                    }
                }
            }
        }
    }

    /**
     * Checks if a rectangle collides with any walls in the current room.
     *
     * @param rect the rectangle to check for collision
     * @return true if the rectangle collides with a wall, false otherwise
     */
    public static boolean doesCollide(Rectangle rect) {
        GameStates.GameplayStates currRoom = GameStates.getGameplayState();
        ArrayList<Wall> currRoomWalls = WallFactory.getRoomWallArray(currRoom);

        for (Wall wall : currRoomWalls) {
            if (rect.intersects(wall.getWallHitBox())) {
                return true;
            }
        }
        return false;
    }
}
