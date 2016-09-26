package adventure;
// -------------------------------------------------------------------------
/**
 *  This class determines the behavior of the drop command and the items
 *  the command applied to.  If the chew-toy is dropped in the room that has
 *  the barking-dog, the player's gaveDogToy boolean attribute is set to true.
 *  If the chew-toy is dropped in that room and the player's hasPettedDog
 *  boolean attribute is set to true, then the barking-dog is switched with
 *  the silent-dog, and the player has beaten the challenge of Ted's apartment.
 *  Also, the drop command requires two words and gives appropriate responses
 *  in the cases where the player has or does not have the item to be dropped.
 *
 *  @author stevr76
 *  @version 2016.08.06
 */
public class DropCommand extends Command
{
    /**
     * This method determines the behavior of the drop command.
     *
     * @param player is the user of the drop command.
     * @return String is the response to the user for this command.
     */
    public String execute(Player player) {
        MyPlayer newPlayer = (MyPlayer)player;
        MyRoom newRoom = (MyRoom)newPlayer.getCurrentRoom();
        if (this.hasSecondWord()) {
            if (newPlayer.has(getSecondWord())) {
                newRoom.addPiece(getSecondWord(),
                    newPlayer.give(getSecondWord()));
                newPlayer.drop(getSecondWord());
                if (getSecondWord().equals("chew-toy") &&
                    newRoom.getShortDescription()
                        .equals("in Ted's bedroom")) {
                    newPlayer.setGaveDogToy(true);
                    if (newPlayer.hasPettedDog() &&
                        !newPlayer.hasSwitchedDogs()) {
                        newRoom.switchDogs();
                        newPlayer.setSwitchedDogs(true);
                    }
                    return "The dog is quieter now";
                }
                if (newRoom.getNumWeights() == 3) {
                    newPlayer.setWeightsLifted(true);
                }
                return "Successfully dropped the " + this.getSecondWord();
            }
            return Message.dropDontHaveMessage(getSecondWord());
        }
        return "<p> The drop command requires a second word <p>";
    }

}
