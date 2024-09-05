
class Commands {

    public static void execute(String text) {
        if (text.matches("[1-9]+")) {
            GameHandler.getGui().display("", "Black");
        } else {
            switch (text) {
                case "move" -> {
                }
                case "look" ->
                    GameHandler.getGui().display("You looked", "Black");
                case "use" ->
                    GameHandler.getGui().display("You used", "Black");
                case "talk" ->
                    GameHandler.getGui().display("You talked", "Black");
                case "inventory" ->
                    GameHandler.getGui().display("You checked your inventory", "Black");
                case "help" ->
                    GameHandler.getGui().display("You asked for help", "Black");
                case "quit" ->
                    GameHandler.getGui().display("You quit the game", "Black");
                default ->
                    GameHandler.getGui().display("Invalid command", "Black");

            }
        }
        GUI.getJTextField().setText("");
    }

}
