package Redux2;

public class Main {

    public static void main(String[] args) throws Exception {
        Game game = new Game();
        GUI gui = new GUI();
        gui.lockButtons();
        GameHandler gameHandler = new GameHandler(gui, game);
        gameHandler.setUpgame();
        game.start(gameHandler);

    }
}
