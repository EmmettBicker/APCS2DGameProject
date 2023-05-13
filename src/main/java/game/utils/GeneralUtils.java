package game.utils;

import game.Constants;
import game.GameStates;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.Point;
import java.util.ArrayList;
import game.wallFactory.Wall;
import game.wallFactory.WallFactory;

public class GeneralUtils {
    private GeneralUtils() {
    }

    public static boolean isClose(Point p1, Point p2) {
        return Math.abs(p1.getX() - p2.getX()) < Constants.CLOSENESS_THRESHOLD
                && Math.abs(p1.getY() - p2.getY()) < Constants.CLOSENESS_THRESHOLD;
    }

    public static Point centerDoorEndDestination(Point p) {
        int playerHeight = 60;
        int playerWidth = 56;
        return new Point(p.x + Constants.DOOR_WIDTH / 2 - playerWidth / 2,
                p.y + Constants.DOOR_HEIGHT / 2 - playerHeight / 2);
    }

    public static Rectangle getRectangleFromPointAndImage(Point p, BufferedImage b)
    {
        return new Rectangle((int) p.getX(), (int) p.getY(), b.getWidth(), b.getHeight());
        
    }

    /*
     * This MODIFIES currentPos
     */
    public static void wallCollision(Rectangle interactingHitbox, Point currentPos) {
        GameStates.GameplayStates currentRoom = GameStates.getGameplayState();
        ArrayList<Wall> currentRoomWalls = WallFactory.getRoomWallArray(currentRoom);

        for (Wall wall : currentRoomWalls) {

            // check for collision with wall sprites
            Rectangle wallHitbox = wall.getWallHitBox();
            double wallWidth = wallHitbox.getWidth();
            double wallHeight = wallHitbox.getHeight();

            if (interactingHitbox.intersects(wallHitbox)) {
                // determine the direction of collision
                double dx = interactingHitbox.getCenterX() - wallHitbox.getCenterX();
                double dy = interactingHitbox.getCenterY() - wallHitbox.getCenterY();
                // handle the collision based on the direction
                if (Math.abs(dx) / wallWidth > Math.abs(dy) / wallHeight) {
                    // collided in x direction
                    if (dx < 0) {
                        // collided on right side of wall
                        currentPos.x = (int) (wallHitbox.getX() - interactingHitbox.getWidth());
                    } else {
                        // collided on left side of wall
                        currentPos.x = (int) (wallHitbox.getX() + wallHitbox.getWidth());
                    }
                } else {
                    // collided in y direction
                    if (dy < 0) {

                        // collided on bottom side of wall
                        currentPos.y = (int) (wallHitbox.getY() - interactingHitbox.getHeight());
                    } else {
                        // collided on top side of wall
                        currentPos.y = (int) (wallHitbox.getY() + wallHitbox.getHeight());
                    }
                }
            }
        }

    }
}
