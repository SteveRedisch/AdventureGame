package adventure;
import java.util.Map;

import java.util.Set;
import java.util.HashMap;
// -------------------------------------------------------------------------
/**
 *  This class contains all data for one room of the game, including the items
 *  in the room.  The room data includes a number of weights, which is used
 *  to track how many of the three weights the player has been able to store
 *  on Zed's top floor.
 *
 *  @author stevr76
 *  @version 2016.08.06
 */
public class MyRoom extends Room
{
    /**
     * This holds the items in the room
     */
    Map<String, String[]> roomPieces;
    /**
     * This tracks the number of weights - it is only used in the top floor of
     * Zed's apartment
     */
    int numWeights;

    /**
     * Constructor sets numWeights to 0 for each room
     *
     * @param desc is the short description of the room.
     */
    public MyRoom(String desc) {
        super(desc);
        roomPieces = new HashMap<>();
        numWeights = 0;
    }
    /**
     * This is the getter method for the numWeights room attribute.
     *
     * @return int is the number of weights put in the room by the player.
     */
    public int getNumWeights() {
        return numWeights;
    }
    /**
     * This method returns the long description of the room.  It is dependent
     * on the look method, which is analagous to the inventory method for
     * a player.  Look works for rooms, not players.
     *
     * @return String long description of room, including items.
     */
    public String getLongDescription() {
        return super.getLongDescription() + this.look();
    }
    /**
     *  This mtehod adds an item to a room.  If the room is the weight
     *  collecting room, and the item is a weight, then numWeights is
     *  incremented.
     *
     *   @param name is the name of the item.
     *   @param value is a Sring[] that contains the description and weight of
     *   the item.
     */
    public void addPiece(String name, String[] value) {
        roomPieces.put(name, value);
        if ((value[1].equals("10") || value[1].equals("25") ||
            value[1].equals("55")) &&
            this.getShortDescription()
                .equals("on Zed's top floor")) {
            numWeights++;
        }
    }
    /**
     * This method changes the description of the bicycle.
     */
    public void changeBicycle() {
        String[] holder = new String[2];
        holder[0] = Message.bicycleLockedMessage();
        holder[1] = "50";
        //playerStuff.remove("bicycle");
        roomPieces.put("bicycle", holder);
    }
    /**
     * This method removes an item from a room.  If the room is the weight
     * collection room, and the item is a weight, then numWeights is
     * decremented.
     *
     * @param name is the name of the item to be removed from the room.
     */
    public void removePiece(String name) {
        String myWeight = roomPieces.get(name)[1];
        roomPieces.remove(name);
        if ((myWeight.equals("10") || myWeight.equals("25") ||
            myWeight.equals("55")) &&
            this.getShortDescription().contains("top floor")) {
            numWeights--;
        }
    }
    /**
     * This method returns a boolean that is true if the item is in the room
     * and false otherwise.  If the player has an item, the room does not
     * have the item.  This method is for the room only.
     *
     * @param name is the name of the item to be removed from the room.
     * @return boolean determines whether or not the room contains the item.
     */
    public boolean has(String name) {
        return (roomPieces.get(name) != null);
    }
    /**
     * This method returns the value of an item if that item is in this room.
     *
     * @param name is the name of the item.
     * @return is the value of the item, including a String description and a
     * String weight, together in a String[].
     */
    public String[] give(String name) {
        return roomPieces.get(name);
    }
    /**
     * This method returns the description of an item. It is used in the look
     * command.
     *
     * @param name is the name of the item.
     * @return String is the description of the item.
     */
    public String describe(String name) {
        if (roomPieces.get(name) != null) {
            return roomPieces.get(name)[0];
        }
        return "none";
    }
    /**
     * This method gives an inventory of the items in the room.
     *
     * @return String containing a comma separated list of the names of the
     * items in the room.
     */
    public String look() {
        Set<String> keys = roomPieces.keySet();
        String[] holder = new String[roomPieces.size()];
        int count = 0;
        for (String i : keys) {
            holder[count] = i;
            count++;
        }
        if (holder.length != 0) {
            return "<p> This is what is in the room: " +
                Message.commaSeparatedList(holder) + "<p>";
        }
        return "<p> There is nothing in the room <p>";
    }
    /**
     * When the user has petted the barking-dog and dropped the chew-toy in the
     * room with the barking-dog, this method changes the barking-dog to a
     * silent-dog.
     */
    public void switchDogs() {
        String[] holder = new String[2];
        holder[0] = "This is a silent dog";
        holder[1] = "100";
        roomPieces.put("silent-dog", holder);
        roomPieces.remove("barking-dog");
    }

}
