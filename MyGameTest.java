package adventure;
import static org.junit.Assert.*;
import student.adventure.*;
import org.junit.Before;
import org.junit.Test;

/**
 *  This is the tester class for all other classes in the adventure package.
 *
 *  @author stevr76
 *  @version 2016.08.06
 */
public class MyGameTest
    extends student.TestCase {

    private String message;
    private String roomDesc;
    private String longDesc;
    private MyGame game;
    /**
     * This is run once before each test.
     */
    @Before
    public void setUp() {
        game = new MyGame();
        game.createCommands();
        game.createRooms();
    }
    /**
     * This test ensures that the setup method was successful.
     */
    @Test
    public void testInit() {
        roomDesc = game.player().getCurrentRoom().getShortDescription();
        assertEquals("in your living room", roomDesc);
    }
    /**
    * This test ensures that the welcome message is sent correctly.
    */
    @Test
    public void testWelcomeMethod() {
        String myString = "<p>Welcome to The World of Simplicity!</p>"
            + "<p>Type 'help' if you need help.</p>"
            + "<p>Hit [return] to continue...</p>";
        assertEquals(game.welcomeMessage(), myString);
    }
    /**
     * This test ensures that the examine method works correctly.
     */
    @Test
    public void testExamineBicycle() {
        executeMoves("examine");
        assertTrue(message.contains("This command requires a second word"));
        executeMoves("examine bicycle");
        assertEquals("in your living room", roomDesc);
        assertEquals(Message.bicycleDescriptionMessage(), message);
        assertTrue(longDesc.contains("bicycle"));
        executeMoves("take bicycle", "examine bicycle");
        assertEquals(Message.bicycleDescriptionMessage(), message);
        executeMoves("w", "e");
        assertTrue(longDesc.contains("There is nothing in the room"));
        executeMoves("examine ruby");
        assertEquals(Message.commandCantSeeMessage("ruby"), message);
        executeMoves("e", "e", "examine table");
        assertEquals(Message.examineDefaultMessage("table"), message);
        executeMoves("examine peanut-butter");
        assertEquals(Message.peanutButterDescriptionMessage(), message);
        executeMoves("w", "w", "examine ruby");
        assertEquals(Message.rubyDescriptionMessage(), message);
    }
    /**
     * This test ensures that the basic take command works correctly.
     */
    @Test
    public void testTakeRuby() {
        executeMoves("west", "take ruby");
        assertEquals("in your bedroom", roomDesc);
        assertEquals(Message.takeSuccessMessage("ruby"), message);
    }
    /**
     * This test ensures that the player can move east and west correctly.
     */
    @Test
    public void testEastWest() {
        executeMoves("go");
        assertEquals(message, "Go where?");
        executeMoves("go east");
        assertEquals("in your kitchen", roomDesc);
        executeMoves("go west");
        assertEquals("in your living room", roomDesc);
        executeMoves("east");
        assertEquals("in your kitchen", roomDesc);
        executeMoves("west");
        assertEquals("in your living room", roomDesc);
        executeMoves("e");
        assertEquals("in your kitchen", roomDesc);
        executeMoves("w");
        assertEquals("in your living room", roomDesc);
    }
    /**
     * This test ensures that the player can move up and down correctly.
     */
    @Test
    public void testUpDown() {
        executeMoves("go up");
        assertEquals(Message.noExitInDirectionMessage(), message);
        executeMoves("go down");
        assertEquals(Message.noExitInDirectionMessage(), message);
        executeMoves("up");
        assertEquals(Message.noExitInDirectionMessage(), message);
        executeMoves("down");
        assertEquals(Message.noExitInDirectionMessage(), message);
        executeMoves("u");
        assertEquals(Message.noExitInDirectionMessage(), message);
        executeMoves("d");
        assertEquals(Message.noExitInDirectionMessage(), message);
    }
    /**
     * This test ensures that the player can move north and south correctly.
     */
    @Test
    public void testNorthSouth() {
        executeMoves("go east");
        executeMoves("go west");
        executeMoves("go east");
        executeMoves("go north");
        assertEquals(Message.noExitInDirectionMessage(), message);
        executeMoves("go south");
        assertEquals(Message.noExitInDirectionMessage(), message);
        executeMoves("north");
        assertEquals(Message.noExitInDirectionMessage(), message);
        executeMoves("south");
        assertEquals(Message.noExitInDirectionMessage(), message);
        executeMoves("n");
        assertEquals(Message.noExitInDirectionMessage(), message);
        executeMoves("s");
        assertEquals(Message.noExitInDirectionMessage(), message);
    }
    /**
     * This test ensures that the inventory command executes correctly.
     */
    @Test
    public void testInventory() {
        executeMoves("take bicycle");
        executeMoves("inventory");
        assertTrue(message.contains("bicycle"));
        executeMoves("i");
        assertTrue(message.contains("bicycle"));
        executeMoves("drop bicycle");
        executeMoves("inventory");
        assertEquals(Message.inventoryEmptyMessage(), message);
        executeMoves("i");
        assertEquals(Message.inventoryEmptyMessage(), message);
    }
    /**
     * This test ensures that the look command executes correctly and sends
     * the correct response to the long description.
     */
    @Test
    public void testGetLongDescriptionLook() {
        executeMoves("e", "w");
        assertTrue(longDesc.contains("bicycle"));
        executeMoves("e", "take peanut-butter", "w", "w", "take ruby");
        executeMoves("e", "w");
        assertTrue(longDesc.contains("There is nothing in the room"));
        executeMoves("e", "drop peanut-butter");
        executeMoves("e", "w");
        assertTrue(longDesc.contains("bicycle"));
        assertTrue(longDesc.contains("peanut-butter"));
        executeMoves("drop ruby");
        executeMoves("e", "w");
        assertTrue(longDesc.contains("bicycle"));
        assertTrue(longDesc.contains("peanut-butter"));
        assertTrue(longDesc.contains("ruby"));
    }
    /**
     * This test ensures that the take command executes correctly.
     */
    @Test
    public void testTake() {
        executeMoves("take");
        assertTrue(message
            .contains("The take command requires a second word"));
        executeMoves("take bike");
        assertEquals(Message.commandCantSeeMessage("bike"), message);
        executeMoves("w", "take ruby", "e", "take bicycle");
        assertEquals(Message.takeNotEnoughRoomMessage("bicycle"), message);
        executeMoves("drop ruby", "take bicycle");
        assertEquals(Message.takeSuccessMessage("bicycle"), message);
        executeMoves("take bicycle");
        assertEquals(Message.takeAlreadyHaveMessage("bicycle"), message);
        executeMoves("i");
        assertTrue(message.contains("bicycle"));
        executeMoves("drop bicycle", "lock bicycle", "take bicycle");
        assertEquals(Message.takeBicycleLockedMessage(), message);
    }
    /**
     * This test ensures that the drop command executes correctly.
     */
    @Test
    public void testDrop() {
        executeMoves("drop");
        assertTrue(message
            .contains("The drop command requires a second word"));
        executeMoves("drop ruby");
        assertEquals(Message.dropDontHaveMessage("ruby"), message);
        executeMoves("take bicycle", "drop bicycle");
        assertTrue(message.contains("Successfully dropped the bicycle"));
        executeMoves("e", "w");
        assertTrue(longDesc.contains("bicycle"));
    }
    /**
     * This test ensures that the look command executes correctly when the user
     * enters the command directly.
     */
    @Test
    public void testLook() {
        executeMoves("look");
        assertTrue(message.contains("bicycle"));
    }
    /**
     * This test ensures that the eat command executes correctly.
     */
    @Test
    public void testEat() {
        executeMoves("eat");
        assertTrue(message.contains("The eat command requires a second word"));
        executeMoves("w", "take ruby", "eat ruby");
        assertEquals(Message.eatNotEdibleMessage("ruby"), message);
        executeMoves("eat peanut-butter");
        assertEquals(Message.commandDontHaveMessage("peanut-butter"), message);
        executeMoves("e", "e", "take peanut-butter", "eat peanut-butter");
        assertEquals(Message.eatSuccessMessage("peanut-butter"), message);
    }
    /**
     * This test ensures that the take take command and locked attribute work
     * together correctly.
     */
    @Test
    public void testLockTakeLockedBike() {
        executeMoves("w", "lock bicycle");
        assertEquals(Message.lockBicycleNotHereMessage(), message);
        executeMoves("e", "lock");
        assertTrue(message.contains("The lock command requires a second word"));
        executeMoves("lock ruby");
        assertEquals(Message.lockNotPossibleMessage("ruby"), message);
        executeMoves("take bicycle", "lock bicycle");
        assertEquals(Message.lockPutBicycleDownMessage(), message);
        executeMoves("drop bicycle", "lock bicycle");
        assertEquals(Message.lockBicycleMessage(), message);
        assertTrue(longDesc.contains("bicycle"));
        executeMoves("take bicycle");
        assertEquals(Message.takeBicycleLockedMessage(), message);
    }
    /**
     * This test ensures that the MyGoCommand executes correctly.
     */
    @Test
    public void testGoCommand() {
        executeMoves("s");
        assertEquals(Message.exitWithoutEatingPBMessage(), message);
        executeMoves("e", "take peanut-butter", "eat peanut-butter", "w", "s");
        assertEquals(Message.exitWithoutLockingBikeMessage(), message);
        executeMoves("lock bicycle", "s");
        assertEquals(Message.exitWithoutTakingRubyMessage(), message);
        executeMoves("w", "take ruby", "e", "s");
        assertTrue(longDesc.contains("outside your apartment"));
    }
    /**
     * This test ensures that the artwork is visible in the hallway.
     */
    @Test
    public void testHallwayArtwork() {
        testGoCommand();
        executeMoves("e");
        assertTrue(longDesc.contains("artwork"));
    }
    /**
     * This test ensures that the player can complete Ted's apartment.
     */
    public void testEnterLeaveTed() {
        testHallwayArtwork();
        executeMoves("e", "n", "s");
        assertTrue(message
            .contains("You can't leave until the dog is silent"));
        executeMoves("take chew-toy", "n", "drop chew-toy");
        executeMoves("pet barking-dog", "take chew-toy", "drop chew-toy",
            "s", "s");
        executeMoves();
        assertTrue(longDesc.contains("at your trusting neighbors"));
    }
    /**
     * This is another test to ensure that the player can complete Ted's
     * apartment.
     */
    public void testEnterLeaveTed2() {
        testHallwayArtwork();
        executeMoves("e", "n", "s");
        assertTrue(message
            .contains("You can't leave until the dog is silent"));
        executeMoves("take chew-toy", "n", "pet barking-dog", "drop chew-toy");
        executeMoves("s", "s");
        assertTrue(longDesc.contains("at your trusting neighbors"));
    }
    /**
     * This test ensures that the player must complete the tasks before leaving
     * Ted's apartment.
     */
    @Test
    public void testCannotLeaveTedAfterOnlyDroppingChewToy() {
        testHallwayArtwork();
        executeMoves("e", "n", "s");
        assertTrue(message
            .contains("You can't leave until the dog is silent"));
        executeMoves("take chew-toy", "drop chew-toy", "s");
        assertTrue(message
            .contains("You can't leave until the dog is silent"));
        executeMoves("take chew-toy", "n", "drop chew-toy", "s", "s");
        assertTrue(message
            .contains("You can't leave until the dog is silent"));
    }
    /**
     * This test ensures that the player can complete Ted's apartment.
     */
    @Test
    public void testCanLeaveTedAfterDroppingChewToyAfterPettingDog() {
        testHallwayArtwork();
        executeMoves("e", "n", "s");
        assertTrue(message
            .contains("You can't leave until the dog is silent"));
        executeMoves("take chew-toy", "drop chew-toy", "s");
        assertTrue(message
            .contains("You can't leave until the dog is silent"));
        executeMoves("take chew-toy", "n",
             "drop chew-toy", "pet barking-dog", "s", "s");
        assertTrue(longDesc
            .contains("at your trusting neighbors"));
    }
    /**
     * This test ensures that the player cannot leave Ted's until he has petted
     * the barking-dog.
     */
    @Test
    public void testCannotLeaveTedAfterOnlyPettingDog() {
        testHallwayArtwork();
        executeMoves("e", "n", "s");
        assertTrue(message
            .contains("You can't leave until the dog is silent"));
        executeMoves("n", "pet barking-dog", "s", "s");
        assertTrue(message
            .contains("You can't leave until the dog is silent"));
    }
    /**
     * This test ensures that the player can complete Zed's apartment.
     */
    @Test
    public void testEnterLeaveZed() {
        testHallwayArtwork();
        executeMoves("e", "s", "n");
        assertTrue(message
            .contains("You can't leave" +
                " until all of the weights are on the top floor"));
        executeMoves("d", "take 10-pound-weight", "take 25-pound-weight");
        executeMoves("u", "u", "drop 10-pound-weight", "drop 25-pound-weight");
        executeMoves("take power-bar", "eat power-bar", "d", "d");
        executeMoves("take 55-pound-weight", "u", "u", "drop 55-pound-weight");
        executeMoves("d", "n");
        assertTrue(longDesc.contains("at your trusting neighbors"));
    }
    /**
     * This test ensures that the player needs the power-bar in order to lift
     * the 55-pound-weight.
     */
    @Test
    public void testCannotLift55PounderWithoutPowerBar() {
        testHallwayArtwork();
        executeMoves("e", "s", "n");
        assertTrue(message
            .contains("You can't leave" +
                " until all of the weights are on the top floor"));
        executeMoves("d", "take 10-pound-weight", "take 25-pound-weight");
        executeMoves("u", "u", "drop 10-pound-weight", "drop 25-pound-weight");
        executeMoves("d", "d");
        executeMoves("take 55-pound-weight");
        assertEquals(message, Message
            .takeNotEnoughRoomMessage("55-pound-weight"));
    }
    /**
     * This test ensures that the player cannot leave Zed's after only taking
     * two weights to the top floor - it must be all three.
     */
    @Test
    public void testCannotLeaveZedWith2WeightsLifted() {
        testHallwayArtwork();
        executeMoves("e", "s", "n");
        assertTrue(message
            .contains("You can't leave" +
                " until all of the weights are on the top floor"));
        executeMoves("d", "take 10-pound-weight", "take 25-pound-weight");
        executeMoves("u", "u", "drop 10-pound-weight", "drop 25-pound-weight");
        executeMoves("d", "n");
        assertTrue(message
            .contains("You can't leave" +
                " until all of the weights are on the top floor"));
    }
    /**
     * This test ensures that the player cannot leave Zed's if the player has
     * moved three weights to the top floor and then moved the weights
     * somewhere else.
     */
    @Test
    public void testCannotLeaveZedWith3WeightsLiftedAndOneRemoved() {
        testHallwayArtwork();
        executeMoves("e", "s", "n");
        assertTrue(message
            .contains("You can't leave" +
                " until all of the weights are on the top floor"));
        executeMoves("d", "take 10-pound-weight", "take 25-pound-weight");
        executeMoves("u", "u", "drop 10-pound-weight", "drop 25-pound-weight");
        executeMoves("take power-bar", "eat power-bar", "d", "d");
        executeMoves("take 55-pound-weight", "u", "u", "drop 55-pound-weight");
        executeMoves("take 55-pound-weight");
        executeMoves("d", "drop 55-pound-weight", "n");
        assertTrue(message
            .contains("You can't leave" +
                " until all of the weights are on the top floor"));
        executeMoves("take 55-pound-weight", "u", "drop 55-pound-weight");
        executeMoves("take 10-pound-weight", "d", "drop 10-pound-weight", "n");
        assertTrue(message
            .contains("You can't leave" +
                " until all of the weights are on the top floor"));
        executeMoves("take 10-pound-weight", "u", "drop 10-pound-weight", "d");
        executeMoves("n");
        assertTrue(longDesc.contains("at your trusting neighbors"));
    }
    /**
     * This test ensures that the player can win by completing his own
     * apartment, then Ted's, then Zed's.
     */
    @Test
    public void testYouWin() {
        testHallwayArtwork();
        executeMoves("e", "s", "n");
        executeMoves("d", "take 10-pound-weight", "take 25-pound-weight");
        executeMoves("u", "u", "drop 10-pound-weight", "drop 25-pound-weight");
        executeMoves("take power-bar", "eat power-bar", "d", "d");
        executeMoves("take 55-pound-weight", "u", "u", "drop 55-pound-weight");
        executeMoves("d", "n", "n", "s");
        assertTrue(message
            .contains("You can't leave until the dog is silent"));
        executeMoves("take chew-toy", "n", "drop chew-toy");
        executeMoves("pet barking-dog", "s", "s");
        assertEquals(message, Message.youWinMessage());
    }
    /**
     * This test ensures that the player can win by completing his own
     * apartment, then Zed's, then Ted's.
     */
    @Test
    public void testYouWin2() {
        testHallwayArtwork();
        executeMoves("e", "n");
        executeMoves("take chew-toy", "n", "drop chew-toy");
        executeMoves("pet barking-dog", "s", "s", "s", "n");
        assertTrue(message
            .contains("You can't leave" +
                " until all of the weights are on the top floor"));
        executeMoves("d", "take 10-pound-weight", "take 25-pound-weight");
        executeMoves("u", "u", "drop 10-pound-weight", "drop 25-pound-weight");
        executeMoves("take power-bar", "eat power-bar", "d", "d");
        executeMoves("take 55-pound-weight", "u", "u", "drop 55-pound-weight");
        executeMoves("drop ruby", "take ruby");
        executeMoves("d", "n");
        assertEquals(message, Message.youWinMessage());
    }
    /**
     * This test ensures that the pet command execute correctly.
     */
    @Test
    public void testPet() {
        testHallwayArtwork();
        executeMoves("pet artwork");
        assertEquals(message, "You can't pet the artwork");
        executeMoves("pet bicycle");
        assertTrue(message
            .contains("I can't see a bicycle in the room to pet"));
        executeMoves("pet");
        assertTrue(message.contains("The pet command requires a second word"));
        executeMoves("e", "n", "take chew-toy", "n", "pet barking-dog");
        assertEquals(message, "The dog is quieter now");
        executeMoves("pet chew-toy");
        assertEquals(message, "You can't pet the chew-toy");

    }
    /**
     * This test ensures that the main method executes correctly.
     */
    @Test
    public void testMain() {
        MyGame.main(null);
        executeMoves("quit");
        assertEquals("Thank you for playing. Good bye.", message);
    }
    /**
     * This test ensures that the Message class works correctly.
     */
    @Test
    public void testMessage() {
        assertEquals(Message.lockBicycleMessage(),
            "You lock up the bicycle");
        Message myMessage = new Message();
        assertTrue(myMessage instanceof Message);
        String[] myHolder = {};
        assertEquals("", Message.commaSeparatedList(myHolder));
    }
    /**
     * This method allows the test class to simulate game inputs.
     *
     * @param inputs is the ordered list of String variables that make up the
     * commands executed by the game.
     */
    private void executeMoves(String... inputs) {
        for (String input : inputs) {
            Command command = game.parser().getCommand(input);
            message = command.execute(game.player());
        }
        roomDesc = game.player().getCurrentRoom().getShortDescription();
        longDesc = game.player().getCurrentRoom().getLongDescription();
    }
}
