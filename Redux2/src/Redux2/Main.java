package Redux2;

public class Main {

    public void main(String[] args) throws Exception {
        Game game = new Game();
        GUI gui = new GUI();
        gui.lockButtons();
        GameHandler gameHandler = new GameHandler(gui, game);
        gameHandler.setUpgame();
        game.start(gameHandler);

    }
}
