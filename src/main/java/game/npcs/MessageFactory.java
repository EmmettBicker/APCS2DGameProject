package game.npcs;

import java.util.ArrayList;

/**
 * The MessageFactory class provides static methods to retrieve messages for different NPCs in different rooms.
 */
public class MessageFactory {

    /**
     * Retrieves the messages for Adam in Room One.
     *
     * @return an ArrayList of strings representing Adam's messages
     */
    public static ArrayList<String> getRoomOneAdamMessage() {
        ArrayList<String> s = new ArrayList<String>();
        s.add("I'm Adam Salem, your mentor for this year!");
        s.add("I love building robots, I hope you do too!");
        s.add("Woohoo!");
        return s;
    }

    /**
     * Retrieves the messages for Nile in Room Two.
     *
     * @return an ArrayList of strings representing Nile's messages
     */
    public static ArrayList<String> getRoomTwoNileMessage() {
        ArrayList<String> s = new ArrayList<String>();
        s.add("Another day");
        s.add("Another thwacker");
        s.add("sigh");
        return s;
    }

    /**
     * Retrieves the messages for Caroline in Room Three.
     *
     * @return an ArrayList of strings representing Caroline's messages
     */
    public static ArrayList<String> getRoomThreeCarolineMessage() {
        ArrayList<String> s = new ArrayList<String>();
        s.add("you look dead tired dawg!");
        s.add("go down & interact for some Sprite!");
        s.add("slayyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy\n"
                + "yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy");
        return s;
    }

    /**
     * Retrieves the messages for Alice in Room Four.
     *
     * @return an ArrayList of strings representing Alice's messages
     */
    public static ArrayList<String> getRoomFourAliceMessage() {
        ArrayList<String> s = new ArrayList<String>();
        s.add("Don't go through that door");
        s.add("I was bored last night and made a monster");
        s.add("I made sure the door needs 2 thwackers, 2 gears, and 2 bolts to open so no one goes through");
        return s;
    }
}
