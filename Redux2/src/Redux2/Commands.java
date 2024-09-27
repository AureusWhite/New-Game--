package Redux2;

class Commands {

    public static void execute(String text) {
        String command;
        String argument;

        if (text.contains(" ")) {
            command = text.substring(0, text.indexOf(" "));
            argument = text.substring(text.indexOf(" ") + 1);
        } else {
            command = text;
            argument = "";

        }
        if (text.matches("[1-9]+")) {
        } else {
            switch (command) {
                case "Equipment" -> {
                    GameHandler.getGui().display("You checked your equipment", "Black");
                    for (Equipment equips : Player.getEquipment().values()) {
                        GameHandler.getGui().display(equips.getName(), "Black");
                    }
                }
                case "cry" -> {
                    Player.performAction(Skill.SOCIAL, Ability.CRY, argument);
                }
                case "point" ->
                    Player.performAction(Skill.SOCIAL, Ability.POINT, argument);
                case "talk" ->
                    GameHandler.getGui().display("You talked", "Black");
                case "inventory" ->
                    GameHandler.getGui().display("You checked your inventory", "Black");
                case "help" ->
                    GameHandler.getGui().display("You asked for help", "Black");
                case "quit" ->
                    GameHandler.getGui().display("You quit the game", "Black");
                case "quests" -> {
                    GameHandler.getGui().display("You checked your quests", "Black");
                    Player.displayQuests();
                }
                default ->
                    GameHandler.getGui().display("Invalid command", "Black");

            }
        }
        GUI.getJTextField().setText("");
    }

}
