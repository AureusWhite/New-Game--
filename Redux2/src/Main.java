
public class Main {

    public void main(String[] args) throws Exception {
        Game game = new Game();
        GUI gui = new GUI();
        GameHandler gameHandler = new GameHandler(gui, game);
        gameHandler.setUpgame();
        game.start(gameHandler);

    }
}
