package adventure;
// -------------------------------------------------------------------------
/**
 *  This class controls the actions of the pet command.  Appropriate responses
 *  are sent depending on whether the user has entered the correct number of
 *  words, where the user is, and what is in the room.  If the barking-dog is
 *  in the room and the command is issued correctly, then the booleann setting
 *  for hasPetted is set to true and the response indicates that the dog has
 *  become quieter, but not silent.  If the player pets the dog and has already
 *  dropped the chew-toy in the room, then the barking-dog is switched out with
 *  a silent-dog, and the player has passed Ted's apartment.
 *
 *  @author stevr76
 *  @version 2016.08.06
 */
public class PetCommand extends Command
{
    /**
     * This method controls the behavior of the pet command.
     *
     * @param player is the player that is executing the pet command.
     * @return String is the response to the user for this command.
     */
    public String execute(Player player) {
        MyPlayer newPlayer = (MyPlayer)player;
        MyRoom newRoom = (MyRoom)newPlayer.getCurrentRoom();
        if (this.hasSecondWord()) {
            if (newRoom.has(getSecondWord()) ||
                newPlayer.has(getSecondWord())) {
                if (getSecondWord().equals("barking-dog")) {
                    newPlayer.setPettedDog(true);
                    if (newPlayer.hasGaveDogToy()) {
                        newRoom.switchDogs();
                        newPlayer.setSwitchedDogs(true);
                    }
                    return "The dog is quieter now";
                }
                return "You can't pet the " + getSecondWord();
            }
            return "<p>I can't see a " + getSecondWord() +
                    " in the room to pet<p>";
        }
        return "<p> The pet command requires a second word <p>";
    }

}
