package adventure;
import student.adventure.*;

/**
 *  This class controls the predetermined responses given in many of the
 *  command classes.
 *
 *  @author stevr76
 *  @version 2016.08.06
 */

public class Message {

    /**
     * Description of the peanut butter.
     * @return description of peanut butter
     */
    public static final String peanutButterDescriptionMessage() {
        return "You absolutely love peanut butter!";
    }

    /**
     * Description of the bicycle.
     * @return description of bike
     */
    public static final String bicycleDescriptionMessage() {
        return "You use your bicycle to get to work.";
    }

    /**
     * Message added to description of bicycle when it is locked.
     * @return bike is locked message
     */
    public static final String bicycleLockedMessage() {
        return "The bicycle is locked up securely.";
    }

    /**
     * Message you get when you try to take the locked bicycle.
     * @return can't take locked bike message
     */
    public static final String takeBicycleLockedMessage() {
        return "You can't take the bicycle because it's locked.";
    }

    /**
     * Description of the ruby.
     * @return ruby description
     */
    public static final String rubyDescriptionMessage() {
        return "You inherited the precious ruby from your great aunt.";
    }

    /**
     * Message when trying to leave before you eat peanut butter.
     * @return exit without eating message
     */
    public static final String exitWithoutEatingPBMessage() {
        return "You don't want to leave your apartment on an empty stomach.";
    }

    /**
     * Message when trying to leave without locking up bike.
     * @return exit without locking bike message
     */
    public static final String exitWithoutLockingBikeMessage() {
        return "You feel a little uncomfortable leaving your bike unlocked, " +
            "even though it is inside.";
    }

    /**
     * Message when trying to leave without taking ruby.
     * @return exit without taking ruby message
     */
    public static final String exitWithoutTakingRubyMessage() {
        return "You feel like you are forgetting something valuable.";
    }

    /**
     * Message when there is no exit in a particular direction.
     * @return no exit in direction message
     */
    public static final String noExitInDirectionMessage() {
        return "There is no exit in that direction!";
    }

    /**
     * Message when player has won.
     * @return you win message
     */
    public static final String youWinMessage() {
        return "Congratulations. You won!";
    }

    /**
     * Message shown when command is request on an object, but the object is
     * neither in your inventory nor in the room.
     * @param objName second word in command
     * @return object not visible message
     */
    public static final String commandCantSeeMessage(String objName) {
        return "You don't see any " + objName + ".";
    }

    /**
     * Message shown when you need to be holding something, but you are not. For
     * example, you need to hold something in order to eat it.
     * @param objName second word in command
     * @return not holding the object message
     */
    public static final String commandDontHaveMessage(String objName) {
        return "You don't have the " + objName + ".";
    }

    /**
     * Default message when examining something that has an empty description.
     * @param objName second word in command
     * @return default examine message
     */
    public static final String examineDefaultMessage(String objName) {
        return "You don't see anything special about the " + objName + ".";
    }

    /**
     * Message when trying to east something that is not edible.
     * @param objName second word in command
     * @return object not edible message
     */
    public static final String eatNotEdibleMessage(String objName) {
        return "You can't eat the " + objName + ".";
    }

    /**
     * Message when successfully eating something.
     * @param objName second word in command
     * @return eat object message
     */
    public static final String eatSuccessMessage(String objName) {
        return "You eat the " + objName + ". Delicious!";
    }

    /**
     * Message you get when you don't have enough room in your inventory. In
     * other words, taking something would result in carrying too much weight.
     * @param objName second word in command
     * @return not enough room in inventory message
     */
    public static final String takeNotEnoughRoomMessage(String objName) {
        return "You don't have enough room in your inventory to take the " +
            objName + ".";
    }

    /**
     * Message when you successfully take an object.
     * @param objName second word in command
     * @return take object message
     */
    public static final String takeSuccessMessage(String objName) {
        return "You take the " + objName + ".";
    }

    /**
     * Message you get when you try to take an object you already have.
     * @param objName second word in command
     * @return already have object message
     */
    public static final String takeAlreadyHaveMessage(String objName) {
        return "You already have the " + objName + ".";
    }

    /**
     * Message you get when you try to drop something you do not have.
     * @param objName second word in command
     * @return don't have object message
     */
    public static final String dropDontHaveMessage(String objName) {
        return "You don't have the " + objName + ".";
    }

    /**
     * Message you get when your inventory is empty.
     * @return inventory empty message
     */
    public static final String inventoryEmptyMessage() {
        return "You're not carrying anything.";
    }

    /**
     * Message you get when you are holding your bicycle when trying to lock it.
     * @return drop bicycle before locking message
     */
    public static final String lockPutBicycleDownMessage() {
        return "You need to put the bicycle down before you can lock it.";
    }

    /**
     * Message you get when you try to lock your bicycle but it is not in the
     * room.
     * @return bicycle is not here message
     */
    public static final String lockBicycleNotHereMessage() {
        return "The bicycle isn't here.";
    }

    /**
     * Message you get when you successfully lock your bicycle.
     * @return lock bicycle message
     */
    public static final String lockBicycleMessage() {
        return "You lock up the bicycle";
    }

    /**
     * Message you get when you try to lock something other than your bicycle.
     * @param objName second word in command
     * @return can't lock object message
     */
    public static final String lockNotPossibleMessage(String objName) {
        return "You can't lock the " + objName + ".";
    }

    /**
     * Returns a comma separated list of the words in the array.
     * @param words objects to be separated
     * @return comma separated list
     */
    public static final String commaSeparatedList(String[] words) {
        if (words.length == 0) {
            return "";
        }
        if (words.length == 1) {
            return words[0];
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            if (i == words.length - 1) {
                sb.append(words[i]);
            }
            else if (i == words.length - 2) {
                sb.append(words[i]).append(" and ");
            }
            else {
                sb.append(words[i]).append(", ");
            }
        }
        return sb.toString();
    }
}
