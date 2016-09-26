package adventure;
// -------------------------------------------------------------------------
/**
 *  This class controls the behavior of the eat command.  The command requires
 *  two words.  Also, appropriate responses are given if the player attempts
 *  to eat something that is not in the inventory or if an item is not edible.
 *  A different response is given if the player successfully eats an edible
 *  item.  There are two edible items in this game - the peanut-butter and the
 *  power-bar.  Eating the peanut-butter causes the player's isHungry boolean
 *  attribute to change to false. Eating the power-bar changes the player's
 *  isStronger boolean attribute to true, which allows the player to lift the
 *  55-pound-weight in Zed's apartment.
 *
 *  @author stevr76
 *  @version 2016.08.06
 */
public class EatCommand extends Command
{
    /**
     * This method controls the behavior of the eat command.
     *
     * @param player is the player that is performing the eat command.
     * @return String is the response to the user for this command.
     */
    public String execute(Player player) {
        MyPlayer newPlayer = (MyPlayer)player;
        if (this.hasSecondWord()) {
            if (newPlayer.has(getSecondWord())) {
                if (this.getSecondWord().equals("peanut-butter") ||
                    this.getSecondWord().equals("power-bar")) {
                    if (getSecondWord().equals("peanut-butter")) {
                        newPlayer.setHungry(false);
                    }
                    if (getSecondWord().equals("power-bar")) {
                        newPlayer.setStronger(true);
                    }
                    newPlayer.drop(getSecondWord());
                    return Message.eatSuccessMessage(getSecondWord());
                }
                return Message.eatNotEdibleMessage(getSecondWord());

            }
            return Message.commandDontHaveMessage(getSecondWord());
        }
        return "<p> The eat command requires a second word <p>";
    }

}
