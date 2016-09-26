package adventure;
// -------------------------------------------------------------------------
/**
 *  This class controls the behavior of the go command. It is modified so that
 *  the player cannot leave each apartment without completing all of the tasks.
 *  The player cannot leave his own apartment unless the boolean for isHungry
 *  is false and isLocked is true. Also, the player must have the ruby before
 *  leaving. Appropriate responses are given so that the user knows what is
 *  expected of him. To leave Ted's apartment, the barking-dog must have been
 *  switched with the silent-dog.  To leave Zed's apartment, all of the weights
 *  must be on the top floor. If all three apartments have been beaten, the
 *  youWin message is sent upon exiting the third apartment.
 *
 *  @author stevr76
 *  @version 2016.08.06
 */
public class MyGoCommand extends GoCommand
{
    //this is the second word of the command.
    private String secondWord;

    //this is the constructor for the default case
//    public MyGoCommand() {
//        super();
//        secondWord = "0";
//    }
    /**
     * This constructor stores the second word of the command, to be
     * set in the execute method.
     *
     * @param direction is the second word of the super go command.
     */
    public MyGoCommand(String direction) {
        secondWord = direction;
    }
    /**
     * This method controls the behavior of the go command.
     *
     * @param player is the player that performs the action of the command.
     * @return String is the response to the user for this command.
     */
    public String execute(Player player) {
        if (!secondWord.equals("old")) {
            this.setSecondWord(secondWord);
        }
        MyPlayer newPlayer = (MyPlayer)player;
        if (this.hasSecondWord() && this.getSecondWord().equals("south")
            && newPlayer.getCurrentRoom().getShortDescription()
            .equals("in your living room")) {
            if (newPlayer.isHungry()) {
                return Message.exitWithoutEatingPBMessage();
            }
            if (!newPlayer.isLocked()) {
                return Message.exitWithoutLockingBikeMessage();
            }
            if (!newPlayer.has("ruby")) {
                return Message.exitWithoutTakingRubyMessage();
            }
        }
        if (this.hasSecondWord() && this.getSecondWord().equals("south")
            && newPlayer.getCurrentRoom().getShortDescription()
            .equals("in Ted's living room")) {
            if (!newPlayer.hasSwitchedDogs()) {
                return "<p>You can't leave until the dog is silent<p>";
            }
            if (newPlayer.hasWeightsLifted()) {
                return Message.youWinMessage();
            }
        }
        if (this.hasSecondWord() && this.getSecondWord().equals("north")
            && newPlayer.getCurrentRoom().getShortDescription()
            .equals("on Zed's main floor")) {
            if (!newPlayer.hasWeightsLifted()) {
                return "<p>You can't leave" +
                    " until all of the weights are on the top floor<p>";
            }
            if (newPlayer.hasSwitchedDogs()) {
                return Message.youWinMessage();
            }
        }
        return super.execute(newPlayer);
    }
}
