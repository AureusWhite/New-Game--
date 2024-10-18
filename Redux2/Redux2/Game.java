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
        beginGame(gameHandler);
    }

    public void beginGame(GameHandler gamehandler) {
        gameHandler.createItems();
        gameHandler.setUpNPCs();
        gamehandler.setNPCTypes();
        gameHandler.createAchievements();
        FatherTime.setPhase(FatherTime.DayPhase.MORNING);
        gamehandler.createRoutine();
        gamehandler.buildRooms();
        
        gamehandler.buildExits();
        //GameHandler.createQuests();
        gamehandler.playIntro();
        Player.initualizeSkills();
        gamehandler.setupPlayer();
        GameHandler.getGui().unlockButtons();
        gamehandler.giveItems();
        gamehandler.populateRooms();
        gamehandler.playGame();
        gamehandler.endGame();
    }

    public void setGameHandler(GameHandler gameHandler) {
        this.gameHandler = gameHandler;
    }
}
