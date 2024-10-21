package Redux2;

import java.util.ArrayList;
import javax.swing.JOptionPane;

class Commands {

    private static PawsAndProwess pawsAndProwess;
    private static String argument = "";
    private static String command = "";

    public static String getCommand() {
        return command;
    }

    public static void setCommand(String command) {
        Commands.command = command;
    }

    public static String getArgument() {
        return argument;
    }

    public static void setArgument(String argument) {
        Commands.argument = argument;
    }

    public static PawsAndProwess getPawsAndProwess() {
        return pawsAndProwess;
    }

    public static void execute(String text) {
        if (text.contains(" ")) {
            setCommand(text.substring(0, text.indexOf(" ")));
            setArgument(text.substring(text.indexOf(" ") + 1));
        } else {
            setCommand(text);
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

                }
                case "craft" -> {
                    String[] items = Player.getRoom().getItemChoices();
                    String choice1 = (String) JOptionPane.showInputDialog(null, "First Item?", "Crafting", JOptionPane.QUESTION_MESSAGE, null, items, items[0]);
                    String choice2 = (String) JOptionPane.showInputDialog(null, "Second Item?", "Crafting", JOptionPane.QUESTION_MESSAGE, null, items, items[0]);
                    Item item1 = GameHandler.getItemByName(choice1);
                    Item item2 = GameHandler.getItemByName(choice2);
                    if (item1 == null || item2 == null) {
                        GameHandler.getGui().display("Invalid item", "Black");
                        return;
                    }
                    if (item1.getName().equals(item2.getName())) {
                        GameHandler.getGui().display("You can't craft the same item", "Black");
                        return;
                    }
                    Crafting crafting = new Crafting("Crafting");
                    crafting.createRecipes();
                    Item result = crafting.Craft(item1, item2);
                    if (result.getName().equals("Mess")) {
                        Player.getRoom().addItem(result);
                    } else {
                        Player.getRoom().addItem(result);
                    }

                }
                case "play" -> {
                    pawsAndProwess = new PawsAndProwess();
                }
                case "inventory" ->
                    GameHandler.getGui().display("You checked your inventory", "Black");

                case "help" -> {
                    NPC helpingNPC = Player.getRoom().randomNPC(true);
                    if (helpingNPC == null) {
                        GameHandler.getGui().display("There is no one to help you", "Black");
                        return;
                    }
                    String[] helpWith = {"Dressing", "Taking a bath", "A hug", "Uppies!"};
                    String choice = (String) JOptionPane.showInputDialog(null, "What do you need help with?", "Help", JOptionPane.QUESTION_MESSAGE, null, helpWith, helpWith[0]);
                    switch (choice) {
                        case "Dressing" -> {
                            GameHandler.getGui().display("You asked for help with dressing", "Black");
                            if (Player.getProficiencies().contains(Proficiencies.DRESS)) {
                                GameHandler.getGui().display(helpingNPC.getName() + ": You can dress yourself...", "Black");
                            } else {
                                GameHandler.getGui().display(helpingNPC.getName() + ": I can help you dress", "Black");
                                helpingNPC.dressPlayer();
                            }
                        }
                        case "Taking a bath" -> {
                            GameHandler.getGui().display("You asked for help with taking a bath", "Black");
                            // Add logic for taking a bath if needed
                        }
                        case "A hug" -> {
                            GameHandler.getGui().display("You asked for a hug", "Black");
                            // Add logic for a hug if needed
                        }
                        case "Uppies!" -> {
                            GameHandler.getGui().display("You asked for uppies", "Black");
                            GameHandler.getGui().display(helpingNPC.getName() + " picked you up", "Black");
                            Player.setStatus(PlayerStatus.CARRIED);
                        }
                    }
                }

                case "buff" -> {
                    Player.displaySkills();
                    Buffs.createBuffs();
                    Buffs.applyRandomBuff();
                    Player.displaySkills();
                }
                case "quests" -> {
                    GameHandler.getGui().display("You checked your quests", "Black");
                    for (Quest quest : Player.getQuests()) {
                        GameHandler.getGui().display(quest.getName(), "Black");
                    }

                }

                case "time" -> {
                    FatherTime.getClock().moveTime(25);
                }
                case "phase" -> {
                    FatherTime.nextPhase();
                    GameHandler.getGui().display("You checked the phase", "Black");
                    GameHandler.getGui().display(FatherTime.getCurrentPhase().toString(), "Black");
                }
                case "routine" -> {
                    RoutineManager.checkRoutine();
                    GameHandler.getGui().display("You checked the routine", "Black");
                }
                case "story" -> {
                    if (Player.getRoom().randomNPC(true) == null) {
                        GameHandler.getGui().display("There is no one here to guide you.", "Black");
                        return;
                    }
                    Player.getRoom().randomNPC(true).guidePlayer(Events.STORY_TIME);
                }
                case "wander" -> {
                    //GameHandler.getNPCByName("Fuzzy").wander(ROOMTYPE.GREEN);
                }
                default ->
                    GameHandler.getGui().display("Invalid command", "Black");

            }
        }
        GUI.getJTextField().setText("");
    }
    private ArrayList<Item> hands;

    public ArrayList<Item> getHands() {
        return hands;
    }

    public ArrayList<Item> gatHands() {
        return Player.getHands();
    }

    public void setHands(ArrayList<Item> hands1) {
        this.hands = hands1;
    }

}
