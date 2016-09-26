package adventure;
// -------------------------------------------------------------------------
/**
 *  This class controls the behavior of the examine command.  Appropriate
 *  responses are sent depending for the cases where the item is not in the
 *  room, where the item has no special description, where the item actually
 *  does have a unique description, and a response is sent to tell the user
 *  that the command requires two words in the case that the user only enters
 *  one word.
 *
 *  @author stevr76
 *  @version 2016.08.06
 */
public class ExamineCommand extends Command
{
    /**
     * This is the method that controls the behavior of the examine command.
     *
     * @param player is the player that performs the examine action.
     * @return String is the response to the user for this command.
     */
    public String execute(Player player) {
        MyPlayer player1 = (MyPlayer)player;
        if (hasSecondWord()) {
            if (!player1.describe(getSecondWord()).equals("none")) {
                return player1.give(getSecondWord())[0];
            }
            MyRoom newRoom = (MyRoom)player.getCurrentRoom();
            if (!newRoom.describe(getSecondWord()).equals("0")) {
                if (!(newRoom.describe(getSecondWord()).equals("none"))) {
                    return newRoom.give(getSecondWord())[0];
                }
                return Message.commandCantSeeMessage(getSecondWord());
            }
            return Message.examineDefaultMessage(getSecondWord());
        }
        return "<p>This command requires a second word.<p>";
    }
}
