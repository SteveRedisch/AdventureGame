package adventure;
import java.util.*;

// -------------------------------------------------------------------------
/**
 *  The player class contains the instance data that represents the player
 *  object throughout the course of the game.  The player's progrss is tracked
 *  by setting its attributes to indicate what part of the game has been
 *  completed.
 *
 *  @author stevr76
 *  @version 2016.08.06
 */
public class MyPlayer extends Player
{
    //keeps the player's items
    private Map<String, String[]> playerStuff;
    //total weight currently carried
    private int weight;
    //tracks whether peanut-butter has been eaten
    private boolean hungry;
    //tracks whether bicycle has been locked
    private boolean locked;
    //tracks whether chew-toy has been dropped in the dog's room
    private boolean gaveDogToy;
    //tracks whether the power-bar has been eaten
    private boolean stronger;
    //tracks whether all weights are on the top floor at Zeds' apt
    private boolean weightsLifted;
    //tracks whether the dog has been petted
    private boolean pettedDog;
    //tracks whether the user has completd Ted's apt and the dogs have been
    //switched from barking-dog to silent-dog
    private boolean switchedDogs;
    /**
     * This is the constructor. The player's pre-game attributes are set.
     */
    public MyPlayer() {
        //initialize the variables
        playerStuff = new HashMap<>();
        weight = 0;
        hungry = true;
        locked = false;
        gaveDogToy = false;
        stronger = false;
        weightsLifted = false;
        pettedDog = false;
        switchedDogs = false;
    }
    /**
     * This is the getter method for switchedDogs attribute
     *
     * @return boolean indicator for attribute in question
     */
    public boolean hasSwitchedDogs() {
        return switchedDogs;
    }
    /**
     * This is the setter method for switchedDogs attribute
     *
     * @param value is the value that switchedDogs will be set to
     */
    public void setSwitchedDogs(boolean value) {
        switchedDogs = value;
    }
    /**
     * This is the getter method for the pettedDog attribute
     *
     * @return boolean is the value of the attribute
     */
    public boolean hasPettedDog() {
        return pettedDog;
    }
    /**
     * This is the setter method for the pettedDog attribute
     *
     * @param value is the value that the attribute is set to
     */
    public void setPettedDog(boolean value) {
        pettedDog = value;
    }
    /**
     * This is the getter method for the weightsLifted attribute
     *
     * @return boolean is the value of the attribute
     */
    public boolean hasWeightsLifted() {
        return weightsLifted;
    }
    /**
     * This is the setter method for the weigthsLifted attribute
     *
     * @param value is the value that the attribute will be set to.
     */
    public void setWeightsLifted(boolean value) {
        weightsLifted = value;
    }
    /**
     * This is the setter method for the stronger attribute
     *
     * @param value is the boolean that the attribute will be set to
     */
    public void setStronger(boolean value) {
        stronger = value;
    }
    /**
     * This is the getter method for the gaveDogToy attribute.
     *
     * @return boolean is the value of the attribute.
     */
    public boolean hasGaveDogToy() {
        return gaveDogToy;
    }
    /**
     * This is the setter method for the gaveDogToy attribute.
     *
     * @param value is the boolean that the attribute is set to.
     */
    public void setGaveDogToy(boolean value) {
        gaveDogToy = value;
    }
    /**
     * This is the getter method for the hungry attribute.
     *
     * @return boolean is the value of the attribute.
     */
    public boolean isHungry() {
        return hungry;
    }
    /**
     * This is the setter method for the hungry attribute.
     *
     * @param value is the boolean that the attribute is set to.
     */
    public void setHungry(boolean value) {
        hungry = value;
    }
    /**
     * This is the getter method for the locked attribute.
     *
     * @return the boolean value of the attribute.
     */
    public boolean isLocked() {
        return locked;
    }
    /**
     * This is the setter method for the locked attribute.
     *
     * @param value is the boolean that the attribute is set to.
     */
    public void setLocked(boolean value) {
        locked = value;
    }
    /**
     * This method determines whether the user possesses an item.
     *
     * @param key is the name of the item.
     * @return boolean value to show if the player has the item.
     */
    public boolean has(String key) {
        return (playerStuff.get(key) != null);
    }
    /**
     * This method returns the description and weight of an item.
     *
     * @param key is the name of the item.
     * @return is the String[] containing the item's information.
     */
    public String[] give(String key) {
        return playerStuff.get(key);
    }
    /**
     * This method returns the description of the item.
     *
     * @param key is the name of the item.
     * @return String is the description of the item.
     */
    public String describe(String key) {
        if (playerStuff.get(key) != null) {
            return playerStuff.get(key)[0];
        }
        return "none";
    }
    /**
     * This method determines whether or not an item can be added to a player's
     * inventory, depending on how heavy the item is and how much weight the
     * player is currently carrying.  Eating the power-bar makes the player
     * become able to carry a heavier load.
     *
     * @param key is the name of the item/
     * @param item is the String[] containing the description of the item and
     * the weight of the item.
     * @return true if the item was kept, false if not.
     */
    public boolean keep(String key, String[] item) {
        int maxPower = (stronger) ? 60 : 50;
        if (Integer.parseInt(item[1]) + weight <= maxPower) {
            playerStuff.put(key, item);
            weight += Integer.parseInt(item[1]);
            return true;
        }
        return false;
    }
    /**
     * This method returns a String containin the names of each item that the
     * player is carrrying.
     *
     * @return String that will be outputted.
     */
    public String inventory() {
        Set<String> keys = playerStuff.keySet();
        String[] holder = new String[playerStuff.size()];
        int count = 0;
        for (String i : keys) {
            holder[count] = i;
            count++;
        }
        if (holder.length != 0) {
            return "<p> You are carrying: "
                + Message.commaSeparatedList(holder) + "<p>";
        }
        return Message.inventoryEmptyMessage();
    }
    /**
     * This method removes an item from the player's item list and reduced the
     * weight of that item from the total.
     *
     * @param key is the name of the item to be dropped.
     */
    public void drop(String key) {
        weight -= Integer.parseInt(playerStuff.get(key)[1]);
        playerStuff.remove(key);
    }
}
