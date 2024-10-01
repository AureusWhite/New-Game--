package Redux2;

public class Game {

    private static GUI gui;
    public static boolean running;

    public static GUI getGui() {
        return gui;
    }

    public static boolean isRunning() {
        return running;
    }

    public static void setRunning(boolean b) {
        
        Game.running = b;
    }

    public static void setGui(GUI gui) {
        Game.gui = gui;
    }

    private GameHandler gameHandler;

    public Game() {
    }

    public GameHandler getGameHandler() {
        return gameHandler;
    }

    public void start(GameHandler gameHandler2) {
        gameHandler = gameHandler2;
        gameHandler.createItems();
        gameHandler.setUpNPCs();
        beginGame(gameHandler);

    }

    public void beginGame(GameHandler gamehandler) {
        gamehandler.buildRooms();
        gamehandler.populateRooms();
        gamehandler.buildExits();
        GameHandler.createQuests();
        gamehandler.playIntro();
        Player.initializeSkills();
        gamehandler.setupPlayer();
        GameHandler.getGui().unlockButtons();
        gamehandler.giveItems();
        gamehandler.playGame();
        gamehandler.endGame();
    }

    public void setGameHandler(GameHandler gameHandler) {
        this.gameHandler = gameHandler;
    }
}
