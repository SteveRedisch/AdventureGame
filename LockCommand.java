package adventure;
// -------------------------------------------------------------------------
/**
 *  This class controls the behavior of the lock command. Appropriate repsonses
 *  are sent if the user fails to enter two words for the command, if the
 *  object of the lock command cannot be locked, if the lock command is
 *  attempted on a bicycle that is held by the player, if the lock command
 *  is attempted on the bicycle when the bicycle is not in the room, and a
 *  success response is sent if the bicycle is successfully locked.  In that
 *  case, the player's hasLocked boolean attribute is set to true for later
 *  use.
 *
 *  @author stevr76
 *  @version 2016.08.06
 */
public class LockCommand extends Command
{
    /**
     * This is the method that controls the behavior of the lock command
     *
     * @param player is the player that performs the lock command.
     * @return String is the response to the user for this command.
     */
    public String execute(Player player) {
        MyPlayer newPlayer = (MyPlayer)player;
        MyRoom newRoom = (MyRoom)player.getCurrentRoom();
        if (this.hasSecondWord()) {
            if (this.getSecondWord().equals("bicycle")) {
                if (!newPlayer.has("bicycle")) {
                    if (newRoom.has("bicycle")) {
                        newPlayer.setLocked(true);
                        newRoom.changeBicycle();
                        return Message.lockBicycleMessage();
                    }
                    return Message.lockBicycleNotHereMessage();
                }
                return Message.lockPutBicycleDownMessage();
            }
            return Message.lockNotPossibleMessage(getSecondWord());
        }
        return "<p> The lock command requires a second word <p>";
    }

}
