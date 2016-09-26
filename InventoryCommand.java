package adventure;
// -------------------------------------------------------------------------
/**
 *  This is the class that controls the behavior of the inventory command.
 *  All of the player's items are listed as a result of this command.
 *
 *  @author stevr76
 *  @version 2016.08.06
 */
public class InventoryCommand extends Command
{
    /**
     * This is the method that controls the behavior of the inventor command.
     *
     * @param player is the player whose item inventory is displayed as a
     * result of this command.
     * @return String is the response to the user for this command.
     */
    public String execute(Player player) {
        MyPlayer player1 = (MyPlayer)player;
        return player1.inventory();
    }

}
