package adventure;
import student.adventure.*;

/**
 *  This class controls the intitial state of the game.  The rooms are created
 *  along with their exits and objects, and the commands are set.
 *
 *  @author stevr76
 *  @version 2016.08.06
 */
public class MyGame
        extends Game
{
    /**
     * This is the constructor for the game.
     */
    public MyGame()
    {
        super(new MyPlayer(), new Parser());
    }

    /**
     * This method returns the welcome message.
     *
     * @return String that is the welcome message.
     */
    public String welcomeMessage()
    {
        return
            "<p>Welcome to The World of Simplicity!</p>"
            + "<p>Type 'help' if you need help.</p>"
            + "<p>Hit [return] to continue...</p>";
    }

    /**
     * This method creates the rooms, adds the exits, creates the items,
     * and adds them to the rooms.
     */
    public void createRooms()
    {
        // create the rooms
        MyRoom outside = new MyRoom("outside your apartment");
        MyRoom livingRoom = new MyRoom("in your living room");
        MyRoom bedRoom = new MyRoom("in your bedroom");
        MyRoom kitchen = new MyRoom("in your kitchen");
        MyRoom eastHallway = new MyRoom("in the hallway");
        MyRoom byNeighbors = new MyRoom("at your trusting neighbors");
        MyRoom zedMainFloor = new MyRoom("on Zed's main floor");
        MyRoom zedBasement = new MyRoom("in Zed's basement");
        MyRoom zedTopFloor = new MyRoom("on Zed's top floor");
        MyRoom tedLivingRoom = new MyRoom("in Ted's living room");
        MyRoom tedDogRoom = new MyRoom("in Ted's bedroom");

        // initialize room exits
        outside.setExit("north", livingRoom);
        outside.setExit("east", eastHallway);
        livingRoom.setExit("west", bedRoom);
        livingRoom.setExit("south", outside);
        livingRoom.setExit("east", kitchen);
        bedRoom.setExit("east", livingRoom);
        kitchen.setExit("west", livingRoom);
        eastHallway.setExit("east", byNeighbors);
        eastHallway.setExit("west", outside);
        byNeighbors.setExit("west", eastHallway);
        byNeighbors.setExit("north", tedLivingRoom);
        byNeighbors.setExit("south", zedMainFloor);
        tedLivingRoom.setExit("south", byNeighbors);
        tedLivingRoom.setExit("north", tedDogRoom);
        tedDogRoom.setExit("south", tedLivingRoom);
        zedMainFloor.setExit("north", byNeighbors);
        zedMainFloor.setExit("down", zedBasement);
        zedMainFloor.setExit("up", zedTopFloor);
        zedBasement.setExit("up", zedMainFloor);
        zedTopFloor.setExit("down", zedMainFloor);

        //create objects and add them to rooms
        String[][] holder = new String[11][2];
        holder[0][0] = Message.bicycleDescriptionMessage();
        holder[0][1] = "50";
        livingRoom.addPiece("bicycle", holder[0]);
        holder[1][0] = Message.rubyDescriptionMessage();
        holder[1][1] = "1";
        bedRoom.addPiece("ruby", holder[1]);
        holder[2][0] = Message.peanutButterDescriptionMessage();
        holder[2][1] = "1";
        kitchen.addPiece("peanut-butter", holder[2]);
        holder[3][0] = "0";
        holder[3][1] = "100";
        kitchen.addPiece("table", holder[3]);
        holder[4][0] = "0";
        holder[4][1] = "100";
        eastHallway.addPiece("artwork", holder[4]);
        holder[5][0] = "This doesn't look too heavy";
        holder[5][1] = "10";
        zedBasement.addPiece("10-pound-weight", holder[5]);
        holder[6][0] = "This is a little bit of a heavy lift";
        holder[6][1] = "25";
        zedBasement.addPiece("25-pound-weight", holder[6]);
        holder[7][0] = "You can't lift this without supplements";
        holder[7][1] = "55";
        zedBasement.addPiece("55-pound-weight", holder[7]);
        holder[8][0] = "This is good preparation for heavy lifting";
        holder[8][1] = "1";
        zedTopFloor.addPiece("power-bar", holder[8]);
        holder[9][0] = "This is a nice treat for a dog";
        holder[9][1] = "1";
        tedLivingRoom.addPiece("chew-toy", holder[9]);
        holder[10][0] = "This is a small but loud dog";
        holder[10][1] = "100";
        tedDogRoom.addPiece("barking-dog", holder[10]);

        // the player starts the game with the player in his living room
        player().setCurrentRoom(livingRoom);
    }

    /**
     * This method instantiates the commands.
     */
    public void createCommands()
    {
        CommandWords commands = parser().commandWords();
        commands.addCommand("go",   new MyGoCommand("old"));
        commands.addCommand("help", new HelpCommand(commands));
        commands.addCommand("quit", new QuitCommand());
        commands.addCommand("examine", new ExamineCommand());
        commands.addCommand("inventory", new InventoryCommand());
        commands.addCommand("i", new InventoryCommand());
        commands.addCommand("look", new LookCommand());
        commands.addCommand("take", new TakeCommand());
        commands.addCommand("drop", new DropCommand());
        commands.addCommand("lock", new LockCommand());
        commands.addCommand("eat", new EatCommand());
        commands.addCommand("north", new MyGoCommand("north"));
        commands.addCommand("east", new MyGoCommand("east"));
        commands.addCommand("south", new MyGoCommand("south"));
        commands.addCommand("west", new MyGoCommand("west"));
        commands.addCommand("up", new MyGoCommand("up"));
        commands.addCommand("down", new MyGoCommand("down"));
        commands.addCommand("n", new MyGoCommand("north"));
        commands.addCommand("e", new MyGoCommand("east"));
        commands.addCommand("s", new MyGoCommand("south"));
        commands.addCommand("w", new MyGoCommand("west"));
        commands.addCommand("u", new MyGoCommand("up"));
        commands.addCommand("d", new MyGoCommand("down"));
        commands.addCommand("pet", new PetCommand());
    }

    /**
     * This method instantiates the game and starts it with the play() method.
     *
     * @param args is the unused parameter containing the command line
     * parameters.
     */
    public static void main(String[] args) {
        Game myGame = new MyGame();
        myGame.play();
    }
}


