package adventure;
// -------------------------------------------------------------------------
/**
 *  This class controls the behavior of the look command.  The look command
 *  is basically an inventory command for the room, as opposed to the player.
 *  The look command does not respond with items that are in the player's
 *  possession.
 *
 *  @author stevr76
 *  @version 2016.08.06
 */
public class LookCommand extends Command
{
    /**
     * This method controls the behavior of the look command.
     *
     * @param player is the player that performs the look command.
     * @return String is the response to the user for this command.
     */
    public String execute(Player player) {
        MyPlayer player1 = (MyPlayer)player;
        MyRoom newRoom = (MyRoom)player1.getCurrentRoom();
        return newRoom.look();
    }

}
