package game.npcs;

import java.util.ArrayList;

public class MessageFactory {
    public static ArrayList<String> getRoomOneAdamMessage() {
        ArrayList<String> s = new ArrayList<String>();
        s.add("I'm Adam Salem, your mentor for this year!");
        s.add("I love building robots, I hope you do too!");
        s.add("Woohoo!");
        return s;
    }
    
    public static ArrayList<String> getRoomTwoNileMessage() {
        ArrayList<String> s = new ArrayList<String>();
        s.add("Another day");
        s.add("Another thwacker");
        s.add("sigh");
        return s;
    }
}
