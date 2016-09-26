package adventure;
// -------------------------------------------------------------------------
/**
 *  This class controls the behavior of the take command.  Appropriate
 *  repsonse messages are sent to the user depending on how the command was
 *  formed, what items are in the room, and what room the player is in.  If
 *  the player is on Zed's top floor, a check is done to see if the player is
 *  removing weights from the top floor.  If that is true, then the player's
 *  setting for completing the weights task is set to false, even if the
 *  setting had previously been set to indicate success in Zed's apartment.
 *
 *  @author stevr76
 *  @version 2016.08.06
 */
public class TakeCommand extends Command
{
    /**
     * This method controls the action taken during the game when the take
     * command is sent.
     *
     * @param player is the player that is executing the take command.
     * @return String is the response to the user for this command.
     */
    public String execute(Player player) {
        MyPlayer newPlayer = (MyPlayer)player;
        MyRoom newRoom = (MyRoom)newPlayer.getCurrentRoom();
        if (this.hasSecondWord()) {
            if (!newPlayer.has(getSecondWord())) {
                if (newRoom.has(getSecondWord())) {
                    if (!(this.getSecondWord().equals("bicycle") &&
                        newPlayer.isLocked())) {
                        if (newPlayer.keep(getSecondWord(),
                            newRoom.give(getSecondWord()))) {
                            newRoom.removePiece(getSecondWord());
                            if (newRoom.getShortDescription()
                                .equals("on Zed's top floor") &&
                                newRoom.getNumWeights() < 3) {
                                newPlayer.setWeightsLifted(false);
                            }
                            return Message
                                .takeSuccessMessage(getSecondWord());
                        }
                        return Message
                            .takeNotEnoughRoomMessage(getSecondWord());
                    }
                    return Message.takeBicycleLockedMessage();
                }
                return Message.commandCantSeeMessage(getSecondWord());
            }
            return Message.takeAlreadyHaveMessage(getSecondWord());
        }
        return "<p> The take command requires a second word <p>";
    }
}

