package Redux2;

import java.util.ArrayList;
import javax.swing.JOptionPane;

class Commands {

    private static PawsAndProwess pawsAndProwess;
    public static PawsAndProwess getPawsAndProwess() {
        return pawsAndProwess;
    }
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
                case "point" -> {
                    Player.performAction(Skill.SOCIAL, Ability.POINT, argument);
                }
                case "play" -> {
                    pawsAndProwess = new PawsAndProwess();
                }
                case "inventory" ->
                    GameHandler.getGui().display("You checked your inventory", "Black");
                    case "help" -> {
                        NPC helpingNPC = Player.getRoom().getFirstNPC();
                        String[] helpWith = {"Dressing", "Taking a bath", "A hug"};
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
                        }
                    }
                    
                case "quit" ->
                    GameHandler.getGui().display("You quit the game", "Black");
                case "quests" -> {
                    GameHandler.getGui().display("You checked your quests", "Black");
                    Player.displayQuests();
                }
                case "time" -> {
                    FatherTime.getClock().moveTime(25);
                }
                case "phase" -> {
                    FatherTime.nextPhase();
                    GameHandler.getGui().display("You checked the phase", "Black");
                    GameHandler.getGui().display(FatherTime.getCurrentPhase().toString(), "Black");
                }
                case "routine"->{ 
                    RoutineManager.checkRoutine();
                    GameHandler.getGui().display("You checked the routine", "Black");
                }
                case "story" -> {
                    Player.getRoom().getFirstNPC().guidePlayer(Events.STORY_TIME);
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
        public ArrayList<Item> gatHands(){
            return Player.getHands();
        }
        public void setHands(ArrayList<Item> hands1){
            this.hands = hands1;
        }

    }
