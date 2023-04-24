package game.utils;

import game.Constants;
import java.awt.Point;

public class GeneralUtils {
    private GeneralUtils() {}

    public static boolean isClose(Point p1, Point p2)
    {
        return Math.abs(p1.getX()-p2.getX()) < Constants.CLOSENESS_THRESHOLD && Math.abs(p1.getY()-p2.getY()) < Constants.CLOSENESS_THRESHOLD;
    }
}
