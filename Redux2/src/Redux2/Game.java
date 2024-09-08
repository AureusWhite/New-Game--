package Redux2;
public class Game {

    private static GUI gui;
    public static boolean running;
    private GameHandler gameHandler;

    public GameHandler getGameHandler() {
        return gameHandler;
    }

    public Game() {
    }

    public void start(GameHandler gameHandler2) {
        gameHandler = gameHandler2;
        gameHandler.createItems();
        gameHandler.setUpNPCs();
        beginGame(gameHandler);

    }

    public static GUI getGui() {
        return gui;
    }

    public void beginGame(GameHandler gamehandler) {
        gamehandler.buildRooms();
        gamehandler.buildExits();
        gamehandler.playIntro();
        gamehandler.setupPlayer();
        gamehandler.playGame();
        gamehandler.endGame();
    }

    public static boolean isRunning() {
        return running;
    }

    public static void setRunning(boolean b) {
        Game.running = b;
    }
}
