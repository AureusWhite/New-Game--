package Redux2;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

public class GameHandler {

    private static final Map<String, Room> rooms = new HashMap<>();
    static final Map<String, NPC> npcs = new HashMap<>();
    static final Map<String, Item> items = new HashMap<>();
    private static final ArrayList<Quest> quests = new ArrayList<>();
    private static Clock clock;
    public static Room room;
    private static GUI gui;
    private static Game game;
    public static String fileSection2 = "";
    public static String fileSection3 = "";
    public static String fileSection4 = "";

    public static Room getRoomByName(String name) {
        return rooms.get(name);
    }

    public static Room getRoom() {
        return GameHandler.room;
    }

    public static Room getRoom(String roomName) {
        return rooms.get(roomName);
    }

    public static GUI getGui() {
        return gui;
    }

    public static void updateStatus() {
        getGui().getStatsLabel().setText("Player: " + Player.getName() + "    | |    Experience: " + Player.getExperience() + "    | |    Shiny Pennies: " + Player.getMoney() + "    | |    Resilience: " + Player.getResilience() + "    | |    Time: " + GameHandler.getClock().getTimeOfDay() + "    | |    Hunger/Thirst: " + Player.getHungerThirst() + "    | |    Alignment: " + Player.getAlignment());
    }

    public static void storyTime() {
        String[] Choices = Player.getRoom().getItemsByType("Book");
        String selectedBook = (String) JOptionPane.showInputDialog(null,
                "Which book would you like to read?",
                "Choose", JOptionPane.QUESTION_MESSAGE,
                null, Choices, Choices[0]);
        readFile(selectedBook);
    }

    public static void artsCrafts() {
        String Choices[] = Player.getRoom().getItemsByType("Crafts");
        String selectedCraft = (String) JOptionPane.showInputDialog(null,
                "Which craft would you like to do?",
                "Choose", JOptionPane.QUESTION_MESSAGE,
                null, Choices, Choices[0]);
        switch (selectedCraft) {
            case "Painting" -> {
                getGui().display("You paint a picture", "Black");
            }
            case "Drawing" -> {
                getGui().display("You draw a picture", "Black");
            }
            case "Sculpting" -> {
                getGui().display("You sculpt a figure", "Black");
            }
            case "Collage" -> {
                getGui().display("You make a collage", "Black");
            }
            case "Origami" -> {
                getGui().display("You make an origami figure", "Black");
            }
            case "Sewing" -> {
                getGui().display("You sew a patch", "Black");
            }
            case "Knitting" -> {
                getGui().display("You knit a square", "Black");
            }
            case "Crocheting" -> {
                getGui().display("You crochet a circle", "Black");
            }
            case "Beading" -> {
                getGui().display("You make a beaded bracelet", "Black");
            }
            case "Jewelry Making" -> {
                getGui().display("You make a necklace", "Black");
            }
            case "Pottery" -> {
                getGui().display("You make a pot", "Black");
            }
            case "Woodworking" -> {
                getGui().display("You make a birdhouse", "Black");
            }
            case "Metalworking" -> {
                getGui().display("You make a keychain", "Black");
            }
            case "Leatherworking" -> {
                getGui().display("You make a wallet", "Black");
            }
            case "Glassblowing" -> {
                getGui().display("You make a vase", "Black");
            }
            case "Candle Making" -> {
                getGui().display("You make a candle", "Black");
            }
            case "Soap Making" -> {
                getGui().display("You make a bar of soap", "Black");
            }
        }
    }

    public static void educationalGames() {
        String Choices[] = Player.getRoom().getItemsByType("Game");
        String selectedGame = (String) JOptionPane.showInputDialog(null,
                "Which game would you like to play?",
                "Choose", JOptionPane.QUESTION_MESSAGE,
                null, Choices, Choices[0]);
        switch (selectedGame) {
            case "Math" -> {
                getGui().display("You play a math game", "Black");
            }
            case "Reading" -> {
                getGui().display("You play a reading game", "Black");
            }
            case "Science" -> {
                getGui().display("You play a science game", "Black");
            }
            case "History" -> {
                getGui().display("You play a history game", "Black");
            }
            case "Geography" -> {
                getGui().display("You play a geography game", "Black");
            }
            case "Art" -> {
                getGui().display("You play an art game", "Black");
            }
            case "Music" -> {
                getGui().display("You play a music game", "Black");
            }
            case "Physical Education" -> {
                getGui().display("You play a physical education game", "Black");
            }
            case "Health" -> {
                getGui().display("You play a health game", "Black");
            }
            case "Language" -> {
                getGui().display("You play a language game", "Black");
            }
            default -> {
            }
        }
    }

    public static void Learning() {
        String Choices[] = {"ABCs", "Numbers", "Shapes", "Colors", "Animals", "Body Parts", "The Calender", "Opposites", "Safety", "Hygiene", "Nutrition"};
        String selectedLearn = (String) JOptionPane.showInputDialog(null,
                "What would you like to learn about?",
                "Choose", JOptionPane.QUESTION_MESSAGE,
                null, Choices, Choices[0]);
        switch (selectedLearn) {
            case "ABCs" -> {
                getGui().display("You study your ABCs", "Black");
            }
            case "Numbers" -> {
                getGui().display("You study your numbers", "Black");
            }
            case "Shapes" -> {
                getGui().display("You study your shapes", "Black");
            }
            case "Colors" -> {
                getGui().display("You study your colors", "Black");
            }
            case "Animals" -> {
                getGui().display("You study animals", "Black");
            }
            case "Body Parts" -> {
                getGui().display("You study body parts", "Black");
            }
            case "The Calender" -> {
                getGui().display("You study the calender", "Black");
            }
            case "Opposites" -> {
                getGui().display("You study opposites", "Black");
            }
            case "Safety" -> {
                getGui().display("You study safety", "Black");
            }
            case "Hygiene" -> {
                getGui().display("You study hygiene", "Black");
            }
            case "Nutrition" -> {
                getGui().display("You study nutrition", "Black");
            }
            default -> {
            }
        }
    }

    public static void puzzles() {
        String Choices[] = Player.getRoom().getItemsByType("Puzzle");
        getGui().display("Which puzzle would you like to do?", "Black");
        for (String Choice : Choices) {
            getGui().display(Choice, "Black");
        }
        String puzzle = getGui().getInput();
        Player.doPuzzle(puzzle);
    }

    public static void language() {
        int outcome = (int) (Math.random() * 100);
        if (outcome < 50) {
            getGui().display("You learned something", "Black");
            Player.setExperience(Player.getExperience() + 1);
            Player.setResilience(Player.getResilience() + 1);

        } else {
            getGui().display("You didn't learn anything.", "Black");
            Player.setResilience(Player.getResilience() - 1);
            Player.setExperience(Player.getExperience() + 2);
        }
    }

    public static Quest getQuest(String questName) {
        for (Quest quest : quests) {
            if (quest.getName().equalsIgnoreCase(questName)) {
                return quest;
            }
        }
        return null;
    }

    public static Quest getQuestByName(String string) {
        for (Quest quest : quests) {
            if (quest.getName().equalsIgnoreCase(string)) {
                return quest;
            }
        }
        return null;
    }

    public static Map<String, Room> getRooms() {
        return rooms;
    }

    public static Map<String, NPC> getNpcs() {
        return npcs;
    }

    public static Map<String, Item> getItems() {
        return items;
    }

    public static ArrayList<Quest> getQuests() {
        return quests;
    }

    public static void setClock(Clock clock) {
        GameHandler.clock = clock;
    }

    public static void setRoom(Room room) {
        GameHandler.room = room;
    }

    public static void setGui(GUI gui) {
        GameHandler.gui = gui;
    }

    public static void setGame(Game game) {
        GameHandler.game = game;
    }

    public static NPC getNPC(NPC effectedNPC) {
        return npcs.get(effectedNPC.getName());
    }

    public static void removeItemFromRoom(Item item) {
        List<Item> itemsI = Player.getRoom().getInventory();
        Iterator<Item> iterator = itemsI.iterator();
        while (iterator.hasNext()) {
            Item currentItem = iterator.next();
            if (currentItem.equals(item)) {
                iterator.remove();
                break;
            }
        }
    }

    public static String getFileSection2() {
        return fileSection2;
    }

    public static void setFileSection2(String fileSection2) {
        GameHandler.fileSection2 = fileSection2;
    }

    public static String getFileSection3() {
        return fileSection3;
    }

    public static void setFileSection3(String fileSection3) {
        GameHandler.fileSection3 = fileSection3;
    }

    public static String getFileSection4() {
        return fileSection4;
    }

    public static void setFileSection4(String fileSection4) {
        GameHandler.fileSection4 = fileSection4;
    }

    public static void giveQuestToPlayer(Quest quest) {
        Quest quest1 = quests.get(quests.indexOf(quest));
        Player.addQuest(quest1);
    }

    static NPC getNPCByName(String person) {
        return npcs.get(person);
    }

    static Item getItemByName(String itemName) {
        return items.get(itemName);
    }

    static String readFile(String fileName) {
        File file = new File(fileName.concat(".txt"));
        if (file.exists()) {
            try {
                StringBuilder content = new StringBuilder();
                StringBuilder section2 = new StringBuilder();
                StringBuilder section3 = new StringBuilder();
                StringBuilder section4 = new StringBuilder();

                try (FileReader reader = new FileReader(file)) {
                    int character;
                    int sectionCount = 1; // Track which section we're in
                    while ((character = reader.read()) != -1) {
                        if (character == '#') {
                            sectionCount++; // Increment when we encounter a '#'
                            continue; // Skip appending '#'
                        }

                        // Append to content based on which section we're in
                        switch (sectionCount) {
                            case 1 ->
                                content.append((char) character); // First section (before the first '#')
                            case 2 ->
                                section2.append((char) character); // Second section (between first and second '#')
                            case 3 ->
                                section3.append((char) character); // Third section (between second and third '#')
                            case 4 ->
                                section4.append((char) character); // Fourth section (between third and fourth '#')
                            default -> {
                            }
                        }
                    }

                    // Display the sections
                    if(!content.isEmpty()){getGui().display(content.toString(), "Black");} // Display everything before first '#'
                    if (section2.length() > 0) {
                        fileSection2 = section2.toString(); // Display second section
                    }
                    if (section3.length() > 0) {
                        fileSection3 = section3.toString();
                    }
                    if (section4.length() > 0) {
                        fileSection4 = section4.toString();
                    }
                }
                return content.toString(); // Return the content before first '#'
            } catch (IOException e) {
                GameHandler.getGui().display("Error reading file.", "Red");
            }
        } else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                GameHandler.getGui().display("Error creating file.", "Red");
            }
        }
        return null;
    }

    static Clock getClock() {
        if (clock == null) {
            clock = new Clock(game);
        }
        return clock;
    }

    static void createQuests() {
        Quest fetchQuest = new Quest("Fetch Quest", "Fetch the item from the room", new Item[]{getItemByName("Toy")}, 10, 10, "Fetch", getItemByName("Toy"), getNPCByName("Ms_Sagely"), getRoomByName("Foyer"));
        quests.add(fetchQuest);
        fetchQuest.setType("fetch");
        Quest escortQuest = new Quest("Escort Quest", "Escort the NPC to the room", new Item[]{getItemByName("Toy")}, 10, 10, "Escort", getItemByName("Toy"), getNPCByName("Dawn"), getRoomByName("Foyer"));
        quests.add(escortQuest);
        escortQuest.setType("escort");
        Quest tidyUp = new Quest("Tidy Up", "Clean up the room", new Item[]{getItemByName("Toy")}, 10, 10, "Clean", getItemByName("Toy"), getNPCByName("Ms_Sagely"), getRoomByName("Foyer"));
        quests.add(tidyUp);
        tidyUp.setType("tidyUp");
        npcs.get("Ms_Sagely").setQuest(tidyUp);
    }

    static void demo() {
        readFile("demo");
        String response;
        String[] dialogOptions = {"Don't I get a say?", "I belong to no one, I just got here", "I only woke up a few hours ago", "I don't know what's going on."};
        response = (String) JOptionPane.showInputDialog(null,
                "What do you do?",
                "Choose", JOptionPane.QUESTION_MESSAGE,
                null, dialogOptions, dialogOptions[0]);
        switch (response) {
            case "Don't I get a say?" ->
                getGui().display(fileSection2, "Black");
            case "I belong to no one, I just got here" ->
                getGui().display(fileSection3, "Black");
            case "I only woke up a few hours ago" ->
                getGui().display(fileSection4, "Black");
            case "I don't know what's going on." ->
                getGui().display(fileSection2, "Black");
            default -> {
            }
        }
        dialogOptions = new String[]{"I'm with Taliber!", "I kinda like Dishes.", "I'm with Farah.", "I don't want to deside yet."};
        response = (String) JOptionPane.showInputDialog(null,
                "What do you do?",
                "Choose", JOptionPane.QUESTION_MESSAGE,
                null, dialogOptions, dialogOptions[0]);
        switch (response) {
            case "I'm with Taliber!" ->
                readFile("demo3");
            case "I kinda like Dishes." ->
                readFile("demo3");
            case "I'm with Farah." ->
                readFile("demo3");
            case "I don't want to deside yet." ->
                readFile("demo3");
            default -> {
                break;
            }
        }

    }

    static void playerTimeOut(int i, String act, NPC npc) {
        boolean apology = false;
        getGui().lockButtons();
        getGui().display("You have been timed out", "Red");
        while (!apology) {
            String[] acts = {"I stole","I pranked someone","Vandalism","I picked on someone","I skipped class","I was trespassing","sneaking"};
            String response = (String) JOptionPane.showInputDialog(null,
            "Why did you steal?",
            "Choose", JOptionPane.QUESTION_MESSAGE,
            null, acts, acts[0]);
            switch (response) {
                case "I stole" -> {
                    if(!act.equals("Stealing")){
                        getGui().display("That's not what you did", "Red");
                        break;
                    }
                    String[] reasons = {"Because I thought it was fun", "I wanted it", "I was peer-pressured", "I was upset", "I didn't think it mattered", "I was bored"};
                    response = (String) JOptionPane.showInputDialog(null,
                            "Why did you steal?",
                            "Choose", JOptionPane.QUESTION_MESSAGE,
                            null, reasons, reasons[0]);

                    switch (response) {
                        case "Because I thought it was fun" -> {
                            String[] wrong = {"Stealing is wrong", "It's not okay to steal", "Stealing is unfair", "Stealing is hurtful", "It's against the rules"};
                            response = (String) JOptionPane.showInputDialog(null,
                                    "Is stealing wrong?",
                                    "Choose", JOptionPane.QUESTION_MESSAGE,
                                    null, wrong, wrong[0]);

                            switch (response) {
                                case "Stealing is wrong" -> {
                                    String[] why = {"I don't really know", "I wouldn't like it if someone stole from me", "I could get caught", "It's not fair to others", "It causes harm", "It goes against my values"};
                                    response = (String) JOptionPane.showInputDialog(null,
                                            "Why is stealing wrong?",
                                            "Choose", JOptionPane.QUESTION_MESSAGE,
                                            null, why, why[0]);

                                    switch (response) {
                                        case "I don't really know" -> {
                                            String[] yesNo = {"Yes", "No"};
                                            getGui().display("Stealing hurts others, and it’s important to respect what belongs to them. How would you feel if someone took something of yours?", "Black");
                                            getGui().display("Will you steal again?", "Black");
                                            response = (String) JOptionPane.showInputDialog(null,
                                                    "Will you steal again?",
                                                    "Choose", JOptionPane.QUESTION_MESSAGE,
                                                    null, yesNo, yesNo[0]);
                                            switch (response) {
                                                case "Yes" -> {
                                                    getGui().display("Stealing isn’t the right choice. Think about others’ feelings.", "Black");
                                                    break;
                                                }
                                                case "No" -> {
                                                    getGui().display("That’s a good decision. Respecting others is important.", "Black");
                                                    apology = true;
                                                    break;
                                                }
                                                default -> {
                                                    break;
                                                }
                                            }
                                        }
                                        case "I wouldn't like it if someone stole from me" -> {
                                            getGui().display("Exactly, treating others how you want to be treated is important.", "Black");
                                            apology = true;
                                        }
                                        case "I could get caught" -> {
                                            getGui().display("Yes, but it’s about more than that—it’s about doing the right thing.", "Black");
                                            apology = true;
                                        }
                                        case "It's not fair to others" -> {
                                            getGui().display("Exactly! Fairness is key.", "Black");
                                            apology = true;
                                        }
                                        case "It causes harm" -> {
                                            getGui().display("Right, stealing affects others negatively.", "Black");
                                            apology = true;
                                        }
                                        case "It goes against my values" -> {
                                            getGui().display("That’s a strong reason—following your values is important.", "Black");
                                            apology = true;
                                        }
                                        default -> {
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                case "I pranked someone" -> {
                    String[] reasons = {"Because I thought it was funny", "I wanted attention", "I was peer-pressured", "I was upset", "I didn't think they'd mind", "I was bored"};
                    response = (String) JOptionPane.showInputDialog(null,
                            "Why did you prank someone?",
                            "Choose", JOptionPane.QUESTION_MESSAGE,
                            null, reasons, reasons[0]);

                    switch (response) {
                        case "Because I thought it was funny" -> {
                            String[] wrong = {"Pranking can be hurtful", "Not everyone enjoys being pranked", "Pranking isn't always fun", "It can go too far", "Pranks can be harmful"};
                            response = (String) JOptionPane.showInputDialog(null,
                                    "Is pranking wrong?",
                                    "Choose", JOptionPane.QUESTION_MESSAGE,
                                    null, wrong, wrong[0]);

                            switch (response) {
                                case "Pranking can be hurtful" -> {
                                    String[] why = {"I didn’t think about that", "I wouldn’t like it if someone pranked me", "It could embarrass them", "It could make them upset", "I don’t want to hurt anyone", "It’s just not kind"};
                                    response = (String) JOptionPane.showInputDialog(null,
                                            "Why is pranking wrong?",
                                            "Choose", JOptionPane.QUESTION_MESSAGE,
                                            null, why, why[0]);

                                    switch (response) {
                                        case "I didn’t think about that" -> {
                                            String[] yesNo = {"Yes", "No"};
                                            getGui().display("Pranks can seem fun at first, but they often hurt or embarrass others. How would you feel if someone did it to you?", "Black");
                                            getGui().display("Will you prank again?", "Black");
                                            response = (String) JOptionPane.showInputDialog(null,
                                                    "Will you prank again?",
                                                    "Choose", JOptionPane.QUESTION_MESSAGE,
                                                    null, yesNo, yesNo[0]);

                                            switch (response) {
                                                case "Yes" -> {
                                                    getGui().display("Pranks are not always harmless. Consider the other person’s feelings.", "Black");
                                                    break;
                                                }
                                                case "No" -> {
                                                    getGui().display("Good decision. Being thoughtful about others is important.", "Black");
                                                    apology = true;
                                                    break;
                                                }
                                                default -> {
                                                    break;
                                                }
                                            }
                                        }
                                        case "I wouldn’t like it if someone pranked me" -> {
                                            getGui().display("Exactly, treating others the way you'd like to be treated is important.", "Black");
                                            apology = true;
                                        }
                                        case "It could embarrass them" -> {
                                            getGui().display("Right, nobody likes feeling embarrassed in front of others.", "Black");
                                            apology = true;
                                        }
                                        case "It could make them upset" -> {
                                            getGui().display("Yes, and it’s important to avoid causing harm to others.", "Black");
                                            apology = true;
                                        }
                                        case "I don’t want to hurt anyone" -> {
                                            getGui().display("That’s the right attitude, pranks can sometimes hurt more than you realize.", "Black");
                                            apology = true;
                                        }
                                        case "It’s just not kind" -> {
                                            getGui().display("Exactly! Being kind and considerate is always better than a prank.", "Black");
                                            apology = true;
                                        }
                                        default -> {
                                            break;

                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                case "Vandalism" -> {
                    String[] reasons = {"I thought it would be fun", "I was angry", "I wanted to make a statement", "I was pressured into it", "I didn’t care about the consequences", "I was bored"};
                    response = (String) JOptionPane.showInputDialog(null,
                            "Why did you vandalize something?",
                            "Choose", JOptionPane.QUESTION_MESSAGE,
                            null, reasons, reasons[0]);

                    switch (response) {
                        case "I thought it would be fun" -> {
                            String[] wrong = {"Vandalism is destructive", "You shouldn’t damage other people's property", "Vandalism isn’t right", "It’s harmful", "It’s disrespectful"};
                            response = (String) JOptionPane.showInputDialog(null,
                                    "Is vandalism wrong?",
                                    "Choose", JOptionPane.QUESTION_MESSAGE,
                                    null, wrong, wrong[0]);

                            switch (response) {
                                case "Vandalism is destructive" -> {
                                    String[] why = {"I didn’t realize", "I wouldn’t like it if someone vandalized my things", "It’s illegal", "It causes trouble for others", "It hurts the community", "It’s disrespectful"};
                                    response = (String) JOptionPane.showInputDialog(null,
                                            "Why is vandalism wrong?",
                                            "Choose", JOptionPane.QUESTION_MESSAGE,
                                            null, why, why[0]);

                                    switch (response) {
                                        case "I didn’t realize" -> {
                                            String[] yesNo = {"Yes", "No"};
                                            getGui().display("Vandalism damages property and can hurt the community. How would you feel if someone vandalized your things?", "Black");
                                            getGui().display("Will you vandalize again?", "Black");
                                            response = (String) JOptionPane.showInputDialog(null,
                                                    "Will you vandalize again?",
                                                    "Choose", JOptionPane.QUESTION_MESSAGE,
                                                    null, yesNo, yesNo[0]);

                                            switch (response) {
                                                case "Yes" -> {
                                                    getGui().display("Vandalism isn’t the right choice. Consider the impact on others.", "Black");
                                                    break;
                                                }
                                                case "No" -> {
                                                    getGui().display("That’s a good decision. Respecting others’ property is important.", "Black");
                                                    apology = true;
                                                    break;
                                                }
                                                default -> {
                                                    break;
                                                }
                                            }
                                        }
                                        case "I wouldn’t like it if someone vandalized my things" -> {
                                            getGui().display("Exactly, it’s important to treat others’ property with respect.", "Black");
                                            apology = true;
                                        }
                                        case "It’s illegal" -> {
                                            getGui().display("Yes, and breaking the law has serious consequences.", "Black");
                                            apology = true;
                                        }
                                        case "It causes trouble for others" -> {
                                            getGui().display("Exactly, vandalism causes unnecessary problems for everyone.", "Black");
                                            apology = true;
                                        }
                                        case "It hurts the community" -> {
                                            getGui().display("Right, vandalism damages the environment we all share.", "Black");
                                            apology = true;
                                        }
                                        case "It’s disrespectful" -> {
                                            getGui().display("That’s a good point, vandalism shows a lack of respect for others.", "Black");
                                            apology = true;
                                        }
                                        default -> {
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                case "I picked on someone" -> {
                    String[] reasons = {"I thought it was funny", "I was trying to fit in", "I was angry", "I wanted attention", "I was bored", "I didn't think they'd mind"};
                    response = (String) JOptionPane.showInputDialog(null,
                            "Why did you pick on someone?",
                            "Choose", JOptionPane.QUESTION_MESSAGE,
                            null, reasons, reasons[0]);

                    switch (response) {
                        case "I thought it was funny" -> {
                            String[] wrong = {"Picking on others is harmful", "It’s hurtful to make fun of people", "Bullying is never okay", "It’s not kind", "It hurts people’s feelings"};
                            response = (String) JOptionPane.showInputDialog(null,
                                    "Is picking on someone wrong?",
                                    "Choose", JOptionPane.QUESTION_MESSAGE,
                                    null, wrong, wrong[0]);

                            switch (response) {
                                case "Picking on others is harmful" -> {
                                    String[] why = {"I didn't think about it", "I wouldn't like it if someone picked on me", "It can hurt them", "I don't want to make someone feel bad", "It's unkind", "It’s unfair to target others"};
                                    response = (String) JOptionPane.showInputDialog(null,
                                            "Why is picking on someone wrong?",
                                            "Choose", JOptionPane.QUESTION_MESSAGE,
                                            null, why, why[0]);

                                    switch (response) {
                                        case "I didn't think about it" -> {
                                            String[] yesNo = {"Yes", "No"};
                                            getGui().display("Picking on others can cause real harm, even if it seems like a joke. How would you feel if someone picked on you?", "Black");
                                            getGui().display("Will you pick on someone again?", "Black");
                                            response = (String) JOptionPane.showInputDialog(null,
                                                    "Will you pick on someone again?",
                                                    "Choose", JOptionPane.QUESTION_MESSAGE,
                                                    null, yesNo, yesNo[0]);

                                            switch (response) {
                                                case "Yes" -> {
                                                    getGui().display("It’s important to be kind to others. Picking on someone isn’t the right choice.", "Black");
                                                    break;
                                                }
                                                case "No" -> {
                                                    getGui().display("Good decision. Treating people with respect is always the best choice.", "Black");
                                                    apology = true;
                                                    break;
                                                }
                                                default -> {
                                                    break;
                                                }
                                            }
                                        }
                                        case "I wouldn't like it if someone picked on me" -> {
                                            getGui().display("Exactly, it’s important to treat others the way you’d want to be treated.", "Black");
                                            apology = true;
                                        }
                                        case "It can hurt them" -> {
                                            getGui().display("Yes, picking on someone can damage their feelings, even if it doesn’t seem like a big deal.", "Black");
                                            apology = true;
                                        }
                                        case "I don't want to make someone feel bad" -> {
                                            getGui().display("That’s the right mindset. Being considerate of others’ feelings is important.", "Black");
                                            apology = true;
                                        }
                                        case "It's unkind" -> {
                                            getGui().display("Correct, kindness and respect are always better than hurtful actions.", "Black");
                                            apology = true;
                                        }
                                        case "It’s unfair to target others" -> {
                                            getGui().display("Absolutely, it’s wrong to single out or target someone. Everyone deserves respect.", "Black");
                                            apology = true;
                                        }
                                        default -> {
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                case "I skipped class" -> {
                    String[] reasons = {"I didn't feel like going", "I wanted to do something else", "I was avoiding something", "I was angry", "I was bored", "I didn’t think it was important"};
                    response = (String) JOptionPane.showInputDialog(null,
                            "Why did you skip class?",
                            "Choose", JOptionPane.QUESTION_MESSAGE,
                            null, reasons, reasons[0]);

                    switch (response) {
                        case "I didn't feel like going" -> {
                            String[] wrong = {"Skipping class is not responsible", "You shouldn’t skip class", "Skipping class can hurt your future", "It’s important to attend class", "Skipping class can have consequences"};
                            response = (String) JOptionPane.showInputDialog(null,
                                    "Is skipping class wrong?",
                                    "Choose", JOptionPane.QUESTION_MESSAGE,
                                    null, wrong, wrong[0]);

                            switch (response) {
                                case "Skipping class is not responsible" -> {
                                    String[] why = {"I didn't think about it", "I might miss important lessons", "It’s part of my responsibility", "I could fall behind", "It’s disrespectful to the teacher", "Skipping could get me in trouble"};
                                    response = (String) JOptionPane.showInputDialog(null,
                                            "Why is skipping class wrong?",
                                            "Choose", JOptionPane.QUESTION_MESSAGE,
                                            null, why, why[0]);

                                    switch (response) {
                                        case "I didn't think about it" -> {
                                            String[] yesNo = {"Yes", "No"};
                                            getGui().display("Skipping class can lead to problems down the road, like falling behind or missing important information.", "Black");
                                            getGui().display("Will you skip class again?", "Black");
                                            response = (String) JOptionPane.showInputDialog(null,
                                                    "Will you skip class again?",
                                                    "Choose", JOptionPane.QUESTION_MESSAGE,
                                                    null, yesNo, yesNo[0]);

                                            switch (response) {
                                                case "Yes" -> {
                                                    getGui().display("Skipping class might seem harmless now, but it can hurt your progress in the long run.", "Black");
                                                    break;
                                                }
                                                case "No" -> {
                                                    getGui().display("Good choice. Going to class regularly is key to staying on track.", "Black");
                                                    apology = true;
                                                    break;
                                                }
                                                default -> {
                                                    break;
                                                }
                                            }
                                        }
                                        case "I might miss important lessons" -> {
                                            getGui().display("Exactly. Skipping class could cause you to miss valuable information.", "Black");
                                            apology = true;
                                        }
                                        case "It’s part of my responsibility" -> {
                                            getGui().display("Yes, attending class is your responsibility, and skipping can lead to trouble.", "Black");
                                            apology = true;
                                        }
                                        case "I could fall behind" -> {
                                            getGui().display("Correct. Missing classes can lead to falling behind on important work.", "Black");
                                            apology = true;
                                        }
                                        case "It’s disrespectful to the teacher" -> {
                                            getGui().display("Good point. Skipping class shows a lack of respect for your teacher’s effort.", "Black");
                                            apology = true;
                                        }
                                        case "Skipping could get me in trouble" -> {
                                            getGui().display("True. Skipping class can lead to disciplinary actions and bigger problems.", "Black");
                                            apology = true;
                                        }
                                        default -> {
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                case "I was trespassing" -> {
                    String[] reasons = {"I was curious", "I wanted to see something", "I was dared", "I was looking for a shortcut", "I didn’t think it was a big deal", "I was bored"};
                    response = (String) JOptionPane.showInputDialog(null,
                            "Why were you trespassing?",
                            "Choose", JOptionPane.QUESTION_MESSAGE,
                            null, reasons, reasons[0]);

                    switch (response) {
                        case "I was curious" -> {
                            String[] wrong = {"Trespassing is illegal", "You should respect people's property", "Trespassing is dangerous", "It's not right to go where you don't belong", "Trespassing can lead to trouble"};
                            response = (String) JOptionPane.showInputDialog(null,
                                    "Is trespassing wrong?",
                                    "Choose", JOptionPane.QUESTION_MESSAGE,
                                    null, wrong, wrong[0]);

                            switch (response) {
                                case "Trespassing is illegal" -> {
                                    String[] why = {"I didn't realize it", "People deserve their privacy", "I don't want to break the law", "I could get hurt or cause damage", "I don't want to get in trouble", "I should respect others' boundaries"};
                                    response = (String) JOptionPane.showInputDialog(null,
                                            "Why is trespassing wrong?",
                                            "Choose", JOptionPane.QUESTION_MESSAGE,
                                            null, why, why[0]);

                                    switch (response) {
                                        case "I didn't realize it" -> {
                                            String[] yesNo = {"Yes", "No"};
                                            getGui().display("Trespassing can have legal consequences, and it's important to be aware of boundaries.", "Black");
                                            getGui().display("Will you trespass again?", "Black");
                                            response = (String) JOptionPane.showInputDialog(null,
                                                    "Will you trespass again?",
                                                    "Choose", JOptionPane.QUESTION_MESSAGE,
                                                    null, yesNo, yesNo[0]);

                                            switch (response) {
                                                case "Yes" -> {
                                                    getGui().display("You should really avoid trespassing, it could cause problems.", "Black");
                                                    break;
                                                }
                                                case "No" -> {
                                                    getGui().display("Good. It's important to respect people's property.", "Black");
                                                    apology = true;
                                                    break;
                                                }
                                                default -> {
                                                    break;
                                                }
                                            }
                                        }
                                        case "People deserve their privacy" -> {
                                            getGui().display("Exactly. Everyone has a right to their privacy, and trespassing violates that.", "Black");
                                            apology = true;
                                        }
                                        case "I don't want to break the law" -> {
                                            getGui().display("That's right. Trespassing is illegal and can lead to consequences.", "Black");
                                            apology = true;
                                        }
                                        case "I could get hurt or cause damage" -> {
                                            getGui().display("Yes, trespassing can be dangerous for you or others.", "Black");
                                            apology = true;
                                        }
                                        case "I don't want to get in trouble" -> {
                                            getGui().display("Good thinking. Avoiding trouble is always a smart move.", "Black");
                                            apology = true;
                                        }
                                        case "I should respect others' boundaries" -> {
                                            getGui().display("Absolutely, respecting boundaries is important.", "Black");
                                            apology = true;
                                        }
                                        default -> {
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                case "sneaking" -> {
                    String[] reasons = {"I was curious", "I wanted to see something", "I was dared", "I was bored", "I wanted to surprise someone", "I thought it would be funny"};
                    response = (String) JOptionPane.showInputDialog(null,
                            "Why did you sneak?",
                            "Choose", JOptionPane.QUESTION_MESSAGE,
                            null, reasons, reasons[0]);

                    switch (response) {
                        case "I was curious" -> {
                            String[] wrong = {"Sneaking can be disrespectful", "You should not sneak", "Sneaking can lead to trouble", "It's not kind to invade someone's space", "Sneaking can hurt feelings"};
                            response = (String) JOptionPane.showInputDialog(null,
                                    "Is sneaking wrong?",
                                    "Choose", JOptionPane.QUESTION_MESSAGE,
                                    null, wrong, wrong[0]);

                            switch (response) {
                                case "Sneaking can be disrespectful" -> {
                                    String[] why = {"I dunno", "I wouldn't like it if someone sneaked on me", "I don't want to get in trouble", "I don't want to hurt anyone", "I don't want to be mean"};
                                    response = (String) JOptionPane.showInputDialog(null,
                                            "Why is sneaking wrong?",
                                            "Choose", JOptionPane.QUESTION_MESSAGE,
                                            null, why, why[0]);

                                    switch (response) {
                                        case "I dunno" -> {
                                            String[] yesNo = {"Yes", "No"};
                                            getGui().display("Sneaking can invade someone’s privacy. How would you feel if someone sneaked on you?", "Black");
                                            getGui().display("Will you sneak again?", "Black");
                                            response = (String) JOptionPane.showInputDialog(null,
                                                    "Will you sneak again?",
                                                    "Choose", JOptionPane.QUESTION_MESSAGE,
                                                    null, yesNo, yesNo[0]);

                                            switch (response) {
                                                case "Yes" -> {
                                                    getGui().display("You should think carefully before sneaking again.", "Black");
                                                    break;
                                                }
                                                case "No" -> {
                                                    getGui().display("Good decision! Respecting others is important.", "Black");
                                                    apology = true;
                                                    break;
                                                }
                                                default -> {
                                                    break;
                                                }
                                            }
                                        }
                                        case "I wouldn't like it if someone sneaked on me" -> {
                                            getGui().display("Exactly! It's important to treat others how you would want to be treated.", "Black");
                                            apology = true;
                                        }
                                        case "I don't want to get in trouble" -> {
                                            getGui().display("It's wise to avoid getting into trouble. Think before you act.", "Black");
                                            apology = true;
                                        }
                                        case "I don't want to hurt anyone" -> {
                                            getGui().display("That’s right! Sneaking can hurt people's feelings.", "Black");
                                            apology = true;
                                        }
                                        case "I don't want to be mean" -> {
                                            getGui().display("Yes, sneaking can come off as mean or inconsiderate. Great awareness!", "Black");
                                            apology = true;
                                        }
                                        default -> {
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        getGui().unlockButtons();
    }

    private Container box;
    public Room recoveryRoom, kitchen, mainRoom,
            dorms, bathroom, hallway, stairs, basement,
            attic, garage, garden, driveway, frontYard,
            backYard, shed, pool, patio, deck, porch,
            balcony, cubbies, dramaArea, changingRoom,
            floorPlay, quietArea, homeWorkArea, playHouse,
            treeHouse, storyBookVillage, pillowPile,
            snackArea, greenHall, blueHall, redHall,
            peddleToys, lemonaidStand, toolShed, TRSRoom,
            janitorialRoom, foyer, pantry, roof, demoRoom, cogLabs;

    private Equipment trainingPants, diaper;

    private Item toy, book, trash, modelingClay, coloringBook, crayons,
            markers, paint, paintBrush, glue, scissors, paper, pencil, eraser,
            calculator, ruler, scale, thermometer, magnifyingGlass, telescope, microscope,
            binoculars, camera, videoCamera, tapeRecorder, radio, television,
            computer, tablet, phone, speaker, headphones, microphone, keyboard,
            mouse, monitor, printer, scanner, projector, whiteboard, chalkboard,
            smartboard, globe, map, calendar, compass, protractor;

    private String[] toyBuffs = {"Social", "Motor", "Imagenation", "Learning", "Emotional"};
    private String[] stuffyBuffs = {"Calms me down", "Helps me play pretend", "Helps me make friends", "Keeps me focused", "I dress it"};

    public GameHandler(GUI gui1, Game game1) {
        game = game1;
        gui = gui1;
    }

    public Room movePlayer(Room room) {
        Player.setRoom(room);
        return Player.room;
    }

    public void setUpNPCs() {

        NPC mssagely = new NPC("Ms_Sagely", "A wise old owl who has been around for centuries.", foyer, "adult");
        npcs.put("Ms_Sagely", mssagely);
        NPC dawn = new NPC("Dawn", "An ERE student who is eager to learn.", foyer, "child");
        npcs.put("Dawn", dawn);
        NPC taliber = new NPC("Taliber", "A student who is always up to something.", foyer, "child");
        npcs.put("Taliber", taliber);
        NPC susy = new NPC("Susy", "A student who is always up to something.", foyer, "child");
        npcs.put("Susy", susy);
        NPC farah = new NPC("Farah", "A student who is always up to something.", foyer, "child");
        npcs.put("Farah", farah);
        NPC drWhite = new NPC("Dr_White", "A doctor who is always up to something.", foyer, "adult");
        npcs.put("Dr_White", drWhite);
        NPC msWhite = new NPC("Ms_White", "A teacher who is always up to something.", foyer, "adult");
        npcs.put("Ms_White", msWhite);
        NPC aureus = new NPC("Aureus", "A student who is always up to something.", foyer, "child");
        npcs.put("Aureus", aureus);
        NPC Jessie = new NPC("Jessie", "A student who is always up to something.", foyer, "child");
        npcs.put("Jessie", Jessie);

    }

    public void moveItem(Item item, Room room) {
        room.getInventory().add(item);
    }

    public void moveItems() {
        moveItem(diaper, recoveryRoom);
    }

    public Game getGame() {
        return game;
    }

    public void buildRooms() {
        kitchen = new Room("Kitchen", "A room where you can cook food.");
        rooms.put("Kitchen", kitchen);
        mainRoom = new Room("Main_Room", "The main room of the daycare.");
        rooms.put("Main_Room", mainRoom);
        npcs.get("Dawn").setRoom(mainRoom);
        dorms = new Room("Dorms", "A room where you can sleep.");
        rooms.put("Dorms", dorms);
        bathroom = new Room("Bathroom", "A room where you can clean yourself.");
        rooms.put("Bathroom", bathroom);
        hallway = new Room("Hallway", "A hallway that connects the rooms.");
        rooms.put("Hallway", hallway);
        stairs = new Room("Stairs", "A staircase that leads to the basement and attic.");
        rooms.put("Stairs", stairs);
        basement = new Room("Basement", "A room where you can store things.");
        rooms.put("Basement", basement);
        attic = new Room("Attic", "A room where you can store things.");
        rooms.put("Attic", attic);
        garage = new Room("Garage", "A room where you can store vehicles.");
        rooms.put("Garage", garage);
        garden = new Room("Garden", "A room where you can grow plants.");
        rooms.put("Garden", garden);
        driveway = new Room("Driveway", "A driveway that leads to the street.");
        rooms.put("Driveway", driveway);
        frontYard = new Room("Front_Yard", "The front yard of the daycare.");
        rooms.put("Front_Yard", frontYard);
        backYard = new Room("Back_Yard", "The back yard of the daycare.");
        rooms.put("Back_Yard", backYard);
        cogLabs = new Room("cogLabs", "A room with a sign that says \"Please do not take the box\"");
        rooms.put("cogLabs", cogLabs);
        cogLabs.addItem(box);
        pool = new Room("Pool", "A pool where you can swim.");
        rooms.put("Pool", pool);
        patio = new Room("Patio", "A patio where you can relax.");
        rooms.put("Patio", patio);
        deck = new Room("Deck", "A deck where you can relax.");
        rooms.put("Deck", deck);
        porch = new Room("Porch", "A porch where you can relax.");
        rooms.put("Porch", porch);
        balcony = new Room("Balcony", "A balcony where you can relax.");
        rooms.put("Balcony", balcony);
        roof = new Room("Roof", "A roof where you can relax.");
        rooms.put("Roof", roof);
        cubbies = new Room("Cubbies", "A room where you can store your things.");
        rooms.put("Cubbies", cubbies);
        dramaArea = new Room("Drama_Area", "A room where you can act out plays.");
        rooms.put("Drama_Area", dramaArea);
        changingRoom = new Room("Changing_Room", "A room where you can change your clothes.");
        rooms.put("Changing_Room", changingRoom);
        floorPlay = new Room("Floor_Play", "A room where you can play on the floor.");
        rooms.put("Floor_Play", floorPlay);
        quietArea = new Room("Quiet_Area", "A room where you can relax.");
        rooms.put("Quiet_Area", quietArea);
        homeWorkArea = new Room("Homework_Area", "A room where you can do your homework.");
        rooms.put("Homework_Area", homeWorkArea);
        playHouse = new Room("Play_House", "A room where you can play house.");
        rooms.put("Play_House", playHouse);
        treeHouse = new Room("Tree_House", "A room where you can play in a tree house.");
        rooms.put("Tree_House", treeHouse);
        storyBookVillage = new Room("Storybook_Village", "A room where you can read books.");
        rooms.put("Storybook_Village", storyBookVillage);
        pillowPile = new Room("Pillow_Pile", "A room where you can relax on a pile of pillows.");
        rooms.put("Pillow_Pile", pillowPile);
        snackArea = new Room("Snack_Area", "A room where you can eat snacks.");
        rooms.put("Snack_Area", snackArea);
        greenHall = new Room("Green_Hall", "A hallway that connects the rooms.");
        rooms.put("Green_Hall", greenHall);
        blueHall = new Room("Blue_Hall", "A hallway that connects the rooms.");
        rooms.put("Blue_Hall", blueHall);
        redHall = new Room("Red_Hall", "A hallway that connects the rooms.");
        rooms.put("Red_Hall", redHall);
        peddleToys = new Room("Peddle_Toys", "A room where you can play with peddle toys.");
        rooms.put("Peddle_Toys", peddleToys);
        lemonaidStand = new Room("Lemonaid_Stand", "A room where you can sell lemonaid.");
        rooms.put("Lemonaid_Stand", lemonaidStand);
        toolShed = new Room("Tool_Shed", "A shed where you can store tools.");
        rooms.put("Tool_Shed", toolShed);
        TRSRoom = new Room("TRSRoom", "A room where you can relax.");
        rooms.put("TRSRoom", TRSRoom);
        janitorialRoom = new Room("Janitorial_Room", "A room where you can clean.");
        rooms.put("Janitorial_Room", janitorialRoom);
        foyer = new Room("Foyer", "The foyer of the daycare.");
        rooms.put("Foyer", foyer);
        Player.setRoom(foyer);
        npcs.get("Ms_Sagely").setRoom(foyer);
        foyer.addItem(toy);
        toy.setTakable(true);
        foyer.addItem(box);
        foyer.addItem(modelingClay);
        pantry = new Room("Pantry", "A room where you can store food.");
        rooms.put("Pantry", pantry);
        recoveryRoom = new Room("Recovery_Room", "A room where you can recover.");
        rooms.put("Recovery_Room", recoveryRoom);
        demoRoom = new Room("Demo_Room", "A room where you can play with toys.");
        rooms.put("Demo_Room", demoRoom);
        demoRoom.addItem(toy);
        demoRoom.addItem(box);
        demoRoom.addItem(diaper);
        diaper.setTakable(true);
        demoRoom.addItem(trainingPants);
        demoRoom.addItem(trash);
        demoRoom.addNPC(npcs.get("Ms_Sagely"));
        demoRoom.addNPC(npcs.get("Dawn"));
        demoRoom.addNPC(npcs.get("Taliber"));
        demoRoom.addNPC(npcs.get("Susy"));
        demoRoom.addNPC(npcs.get("Farah"));
        demoRoom.addNPC(npcs.get("Dr_White"));
        demoRoom.addNPC(npcs.get("Ms_White"));
        demoRoom.addNPC(npcs.get("Aureus"));
        demoRoom.addNPC(npcs.get("Jessie"));


    }

    public void createItems() {
        toy = new Item("Toy", "A toy for you to play with.", "Toy", false);
        items.put("Toy", toy);
        toy.setType("Toy");
        coloringBook = new Item("Coloring Book", "A coloring book for you to color in.", "Crafts", true);
        items.put("Coloring Book", coloringBook);
        coloringBook.setType("Crafts");
        crayons = new Item("Crayons", "Crayons for you to color with.", "Crafts", true);
        items.put("Crayons", crayons);
        crayons.setType("Crafts");
        markers = new Item("Markers", "Markers for you to color with.", "Crafts", true);
        items.put("Markers", markers);
        markers.setType("Crafts");
        paint = new Item("Paint", "Paint for you to paint with.", "Crafts", true);
        items.put("Paint", paint);
        paint.setType("Crafts");
        paintBrush = new Item("Paint Brush", "A paint brush for you to paint with.", "Crafts", true);
        items.put("Paint Brush", paintBrush);
        paintBrush.setType("Crafts");
        glue = new Item("Glue", "Glue for you to glue with.", "Crafts", true);
        items.put("Glue", glue);
        glue.setType("Crafts");
        scissors = new Item("Scissors", "Scissors for you to cut with.", "Crafts", true);
        items.put("Scissors", scissors);
        scissors.setType("Crafts");
        paper = new Item("Paper", "Paper for you to write on.", "Crafts", true);
        items.put("Paper", paper);
        paper.setType("Crafts");
        pencil = new Item("Pencil", "A pencil for you to write with.", "Crafts", true);
        items.put("Pencil", pencil);
        pencil.setType("Crafts");
        eraser = new Item("Eraser", "An eraser for you to erase with.", "Crafts", true);
        items.put("Eraser", eraser);
        eraser.setType("Crafts");
        calculator = new Item("Calculator", "A calculator for you to calculate with.", "Crafts", true);
        items.put("Calculator", calculator);
        calculator.setType("Crafts");
        ruler = new Item("Ruler", "A ruler for you to measure with.", "Crafts", true);
        items.put("Ruler", ruler);
        ruler.setType("Crafts");
        scale = new Item("Scale", "A scale for you to weigh with.", "Crafts", true);
        items.put("Scale", scale);
        scale.setType("Crafts");
        thermometer = new Item("Thermometer", "A thermometer for you to measure temperature with.", "Crafts", true);
        items.put("Thermometer", thermometer);
        thermometer.setType("Crafts");
        magnifyingGlass = new Item("Magnifying Glass", "A magnifying glass for you to magnify with.", "Crafts", true);
        items.put("Magnifying Glass", magnifyingGlass);
        magnifyingGlass.setType("Tool");
        telescope = new Item("Telescope", "A telescope for you to see far away with.", "Crafts", true);
        items.put("Telescope", telescope);
        telescope.setType("Crafts");
        microscope = new Item("Microscope", "A microscope for you to see small things with.", "Crafts", true);
        items.put("Microscope", microscope);
        microscope.setType("Crafts");
        binoculars = new Item("Binoculars", "Binoculars for you to see far away with.", "Crafts", true);
        items.put("Binoculars", binoculars);
        binoculars.setType("Crafts");
        camera = new Item("Camera", "A camera for you to take pictures with.", "Crafts", true);
        items.put("Camera", camera);
        camera.setType("Crafts");
        videoCamera = new Item("Video Camera", "A video camera for you to take videos with.", "Crafts", true);
        items.put("Video Camera", videoCamera);
        videoCamera.setType("Crafts");
        tapeRecorder = new Item("Tape Recorder", "A tape recorder for you to record with.", "Crafts", true);
        items.put("Tape Recorder", tapeRecorder);
        tapeRecorder.setType("Crafts");
        radio = new Item("Radio", "A radio for you to listen to music with.", "Crafts", true);
        items.put("Radio", radio);
        radio.setType("Crafts");
        television = new Item("Television", "A television for you to watch TV with.", "Crafts", true);
        items.put("Television", television);
        television.setType("Crafts");
        computer = new Item("Computer", "A computer for you to use.", "Crafts", true);
        items.put("Computer", computer);
        computer.setType("Crafts");
        tablet = new Item("Tablet", "A tablet for you to use.", "Crafts", true);
        items.put("Tablet", tablet);
        tablet.setType("Crafts");
        phone = new Item("Phone", "A phone for you to use.", "Crafts", true);
        items.put("Phone", phone);
        phone.setType("Crafts");
        speaker = new Item("Speaker", "A speaker for you to listen to music with.", "Crafts", true);
        items.put("Speaker", speaker);
        speaker.setType("Crafts");
        headphones = new Item("Headphones", "Headphones for you to listen to music with.", "Crafts", true);
        items.put("Headphones", headphones);
        headphones.setType("Crafts");
        microphone = new Item("Microphone", "A microphone for you to record with.", "Crafts", true);
        items.put("Microphone", microphone);
        microphone.setType("Crafts");
        keyboard = new Item("Keyboard", "A keyboard for you to type with.", "Crafts", true);
        items.put("Keyboard", keyboard);
        keyboard.setType("Crafts");
        mouse = new Item("Mouse", "A mouse for you to click with.", "Crafts", true);
        items.put("Mouse", mouse);
        mouse.setType("Crafts");
        monitor = new Item("Monitor", "A monitor for you to see with.", "Crafts", true);
        items.put("Monitor", monitor);
        monitor.setType("Crafts");
        printer = new Item("Printer", "A printer for you to print with.", "Crafts", true);
        items.put("Printer", printer);
        printer.setType("Crafts");
        scanner = new Item("Scanner", "A scanner for you to scan with.", "Crafts", true);
        items.put("Scanner", scanner);
        scanner.setType("Crafts");
        projector = new Item("Projector", "A projector for you to project with.", "Crafts", true);
        items.put("Projector", projector);
        projector.setType("Crafts");
        whiteboard = new Item("Whiteboard", "A whiteboard for you to write on.", "Crafts", true);
        items.put("Whiteboard", whiteboard);
        whiteboard.setType("Crafts");
        chalkboard = new Item("Chalkboard", "A chalkboard for you to write on.", "Crafts", true);
        items.put("Chalkboard", chalkboard);
        chalkboard.setType("Crafts");
        smartboard = new Item("Smartboard", "A smartboard for you to write on.", "Crafts", true);
        items.put("Smartboard", smartboard);
        smartboard.setType("Crafts");
        globe = new Item("Globe", "A globe for you to learn about the world.", "Crafts", true);
        items.put("Globe", globe);
        globe.setType("Crafts");
        map = new Item("Map", "A map for you to learn about the world.", "Crafts", true);
        items.put("Map", map);
        map.setType("Crafts");
        calendar = new Item("Calendar", "A calendar for you to keep track of time.", "Crafts", true);
        items.put("Calendar", calendar);
        calendar.setType("Crafts");
        compass = new Item("Compass", "A compass for you to find your way.", "Crafts", true);
        items.put("Compass", compass);
        compass.setType("Crafts");
        protractor = new Item("Protractor", "A protractor for you to measure angles with.", "Crafts", true);
        items.put("Protractor", protractor);
        protractor.setType("Crafts");

        modelingClay = new Item("Modeling Clay", "Modeling clay for you to play with.", "Crafts", true);
        items.put("Modeling Clay", modelingClay);
        modelingClay.setType("Crafts");
        trash = new Item("Trash", "Trash that needs to be thrown away.", "Trash", true);
        items.put("Trash", trash);
        trash.setType("Trash");
        diaper = new Equipment("Diaper", "A diaper for you, a baby.", "Underpants");
        items.put("Diaper", diaper);
        diaper.setType("Equipment");
        book = new Item("Book", "A book for you to read.", "Book", true);
        items.put("Book", book);
        book.setType("Book");
        trainingPants = new Equipment("Training Pants", "Training Pants, for you, a big kid", "Underpants");
        items.put("Training Pants", trainingPants);
        trainingPants.setType("Equipment");
        box = new Container("Box", "A simple cardboard box for storing items", "Furniture", false);
        box.setType("Furniture");
        box.setContraband(true);
        items.put("Box", box);
        box.addItem(diaper);
        Player.addItem(diaper);
        Player.addItem(book);
        Player.addItem(trainingPants);
    }

    public void playIntro() {
        readFile("intro1");
        getGui().waitForInput();
        readFile("intro2");
        getGui().waitForInput();
        readFile("intro3");
        getGui().waitForInput();
    }

    public void setupPlayer() {
        setCharacterBio();
        setCharacterAlignment();
        updateStatus();
        Game.setRunning(true);
        getGui().unlockButtons();
    }

    public void playGame() {
        playTutorial();
        playChapter1();
        playChapter2();
        playChapter3();
        playChapter4();
        playChapter5();
        playEnding();
    }

    public void endGame() {
        playOutro();
    }

    public void buildExits() {
        //BackYard
        backYard.addExit(deck);
        backYard.addExit(patio);
        backYard.addExit(playHouse);
        backYard.addExit(pool);
        backYard.addExit(toolShed);
        backYard.addExit(treeHouse);
        backYard.addExit(foyer);

        //Bathroom
        bathroom.addExit(changingRoom);

        //BlueHall
        blueHall.addExit(foyer);
        blueHall.addExit(redHall);

        //ChangingRoom
        changingRoom.addExit(bathroom);

        //Cubbies
        cubbies.addExit(dramaArea);
        cubbies.addExit(floorPlay);
        cubbies.addExit(homeWorkArea);
        cubbies.addExit(quietArea);
        cubbies.addExit(storyBookVillage);
        cubbies.addExit(mainRoom);
        cubbies.addExit(pillowPile);

        //Deck
        deck.addExit(backYard);

        //DemoRoom
        demoRoom.addExit(foyer);

        //Dorms
        dorms.addExit(foyer);
        dorms.addExit(greenHall);

        //DramaArea
        dramaArea.addExit(cubbies);
        dramaArea.addExit(floorPlay);
        dramaArea.addExit(homeWorkArea);
        dramaArea.addExit(quietArea);
        dramaArea.addExit(storyBookVillage);
        dramaArea.addExit(mainRoom);
        dramaArea.addExit(pillowPile);

        //FloorPlay
        floorPlay.addExit(cubbies);
        floorPlay.addExit(dramaArea);
        floorPlay.addExit(homeWorkArea);
        floorPlay.addExit(quietArea);
        floorPlay.addExit(storyBookVillage);
        floorPlay.addExit(mainRoom);
        floorPlay.addExit(pillowPile);

        //Foyer
        foyer.addExit(backYard);
        foyer.addExit(blueHall);
        foyer.addExit(dorms);
        foyer.addExit(frontYard);
        foyer.addExit(greenHall);
        foyer.addExit(kitchen);
        foyer.addExit(mainRoom);
        foyer.addExit(recoveryRoom);
        foyer.addExit(cogLabs);
        foyer.addExit(demoRoom);

        //FrontYard
        frontYard.addExit(foyer);
        frontYard.addExit(lemonaidStand);
        frontYard.addExit(porch);

        //GreenHall
        greenHall.addExit(dorms);
        greenHall.addExit(foyer);

        //HomeWorkArea
        homeWorkArea.addExit(cubbies);
        homeWorkArea.addExit(dramaArea);
        homeWorkArea.addExit(floorPlay);
        homeWorkArea.addExit(quietArea);
        homeWorkArea.addExit(storyBookVillage);
        homeWorkArea.addExit(mainRoom);
        homeWorkArea.addExit(pillowPile);

        //Kitchen
        kitchen.addExit(foyer);
        kitchen.addExit(pantry);

        //LemonaidStand
        lemonaidStand.addExit(frontYard);

        //MainRoom
        mainRoom.addExit(cubbies);
        mainRoom.addExit(dramaArea);
        mainRoom.addExit(floorPlay);
        mainRoom.addExit(homeWorkArea);
        mainRoom.addExit(pillowPile);
        mainRoom.addExit(quietArea);
        mainRoom.addExit(snackArea);
        mainRoom.addExit(storyBookVillage);
        mainRoom.addExit(foyer);

        //Pantry
        pantry.addExit(kitchen);

        //Patio
        patio.addExit(backYard);

        //PillowPile
        pillowPile.addExit(cubbies);
        pillowPile.addExit(dramaArea);
        pillowPile.addExit(floorPlay);
        pillowPile.addExit(homeWorkArea);
        pillowPile.addExit(quietArea);
        pillowPile.addExit(storyBookVillage);
        pillowPile.addExit(mainRoom);

        //PlayHouse
        playHouse.addExit(backYard);

        //Pool
        pool.addExit(backYard);

        //Porch
        porch.addExit(frontYard);

        //laboratories
        cogLabs.addExit(foyer);

        //QuietArea
        quietArea.addExit(cubbies);
        quietArea.addExit(dramaArea);
        quietArea.addExit(floorPlay);
        quietArea.addExit(homeWorkArea);
        quietArea.addExit(storyBookVillage);
        quietArea.addExit(mainRoom);
        quietArea.addExit(pillowPile);

        //RecoveryRoom
        recoveryRoom.addExit(foyer);

        //RedHall
        redHall.addExit(blueHall);

        //SnackArea
        snackArea.addExit(mainRoom);

        //StoryBookVillage
        storyBookVillage.addExit(cubbies);
        storyBookVillage.addExit(dramaArea);
        storyBookVillage.addExit(floorPlay);
        storyBookVillage.addExit(homeWorkArea);
        storyBookVillage.addExit(quietArea);
        storyBookVillage.addExit(pillowPile);
        storyBookVillage.addExit(mainRoom);

        //ToolShed
        toolShed.addExit(backYard);

        //TreeHouse
        treeHouse.addExit(backYard);

    }

    public void setUpgame() {

    }

    public Item getDiaper() {
        return diaper;
    }

    public Container getBox() {
        return box;
    }

    public void setBox(Container box) {
        this.box = box;
    }

    public Room getRecoveryRoom() {
        return recoveryRoom;
    }

    public void setRecoveryRoom(Room recoveryRoom) {
        this.recoveryRoom = recoveryRoom;
    }

    public Room getKitchen() {
        return kitchen;
    }

    public void setKitchen(Room kitchen) {
        this.kitchen = kitchen;
    }

    public Room getMainRoom() {
        return mainRoom;
    }

    public void setMainRoom(Room mainRoom) {
        this.mainRoom = mainRoom;
    }

    public Room getDorms() {
        return dorms;
    }

    public void setDorms(Room dorms) {
        this.dorms = dorms;
    }

    public Room getBathroom() {
        return bathroom;
    }

    public void setBathroom(Room bathroom) {
        this.bathroom = bathroom;
    }

    public Room getHallway() {
        return hallway;
    }

    public void setHallway(Room hallway) {
        this.hallway = hallway;
    }

    public Room getStairs() {
        return stairs;
    }

    public void setStairs(Room stairs) {
        this.stairs = stairs;
    }

    public Room getBasement() {
        return basement;
    }

    public void setBasement(Room basement) {
        this.basement = basement;
    }

    public Room getAttic() {
        return attic;
    }

    public void setAttic(Room attic) {
        this.attic = attic;
    }

    public Room getGarage() {
        return garage;
    }

    public void setGarage(Room garage) {
        this.garage = garage;
    }

    public Room getGarden() {
        return garden;
    }

    public void setGarden(Room garden) {
        this.garden = garden;
    }

    public Room getDriveway() {
        return driveway;
    }

    public void setDriveway(Room driveway) {
        this.driveway = driveway;
    }

    public Room getFrontYard() {
        return frontYard;
    }

    public void setFrontYard(Room frontYard) {
        this.frontYard = frontYard;
    }

    public Room getBackYard() {
        return backYard;
    }

    public void setBackYard(Room backYard) {
        this.backYard = backYard;
    }

    public Room getShed() {
        return shed;
    }

    public void setShed(Room shed) {
        this.shed = shed;
    }

    public Room getPool() {
        return pool;
    }

    public void setPool(Room pool) {
        this.pool = pool;
    }

    public Room getPatio() {
        return patio;
    }

    public void setPatio(Room patio) {
        this.patio = patio;
    }

    public Room getDeck() {
        return deck;
    }

    public void setDeck(Room deck) {
        this.deck = deck;
    }

    public Room getPorch() {
        return porch;
    }

    public void setPorch(Room porch) {
        this.porch = porch;
    }

    public Room getBalcony() {
        return balcony;
    }

    public void setBalcony(Room balcony) {
        this.balcony = balcony;
    }

    public Room getCubbies() {
        return cubbies;
    }

    public void setCubbies(Room cubbies) {
        this.cubbies = cubbies;
    }

    public Room getDramaArea() {
        return dramaArea;
    }

    public void setDramaArea(Room dramaArea) {
        this.dramaArea = dramaArea;
    }

    public Room getChangingRoom() {
        return changingRoom;
    }

    public void setChangingRoom(Room changingRoom) {
        this.changingRoom = changingRoom;
    }

    public Room getFloorPlay() {
        return floorPlay;
    }

    public void setFloorPlay(Room floorPlay) {
        this.floorPlay = floorPlay;
    }

    public Room getQuietArea() {
        return quietArea;
    }

    public void setQuietArea(Room quietArea) {
        this.quietArea = quietArea;
    }

    public Room getHomeWorkArea() {
        return homeWorkArea;
    }

    public void setHomeWorkArea(Room homeWorkArea) {
        this.homeWorkArea = homeWorkArea;
    }

    public Room getPlayHouse() {
        return playHouse;
    }

    public void setPlayHouse(Room playHouse) {
        this.playHouse = playHouse;
    }

    public Room getTreeHouse() {
        return treeHouse;
    }

    public void setTreeHouse(Room treeHouse) {
        this.treeHouse = treeHouse;
    }

    public Room getStoryBookVillage() {
        return storyBookVillage;
    }

    public void setStoryBookVillage(Room storyBookVillage) {
        this.storyBookVillage = storyBookVillage;
    }

    public Room getPillowPile() {
        return pillowPile;
    }

    public void setPillowPile(Room pillowPile) {
        this.pillowPile = pillowPile;
    }

    public Room getSnackArea() {
        return snackArea;
    }

    public void setSnackArea(Room snackArea) {
        this.snackArea = snackArea;
    }

    public Room getGreenHall() {
        return greenHall;
    }

    public void setGreenHall(Room greenHall) {
        this.greenHall = greenHall;
    }

    public Room getBlueHall() {
        return blueHall;
    }

    public void setBlueHall(Room blueHall) {
        this.blueHall = blueHall;
    }

    public Room getRedHall() {
        return redHall;
    }

    public void setRedHall(Room redHall) {
        this.redHall = redHall;
    }

    public Room getPeddleToys() {
        return peddleToys;
    }

    public void setPeddleToys(Room peddleToys) {
        this.peddleToys = peddleToys;
    }

    public Room getLemonaidStand() {
        return lemonaidStand;
    }

    public void setLemonaidStand(Room lemonaidStand) {
        this.lemonaidStand = lemonaidStand;
    }

    public Room getToolShed() {
        return toolShed;
    }

    public void setToolShed(Room toolShed) {
        this.toolShed = toolShed;
    }

    public Room getTRSRoom() {
        return TRSRoom;
    }

    public void setTRSRoom(Room tRSRoom) {
        TRSRoom = tRSRoom;
    }

    public Room getJanitorialRoom() {
        return janitorialRoom;
    }

    public void setJanitorialRoom(Room janitorialRoom) {
        this.janitorialRoom = janitorialRoom;
    }

    public Room getFoyer() {
        return foyer;
    }

    public void setFoyer(Room foyer) {
        this.foyer = foyer;
    }

    public Room getPantry() {
        return pantry;
    }

    public void setPantry(Room pantry) {
        this.pantry = pantry;
    }

    public Room getRoof() {
        return roof;
    }

    public void setRoof(Room roof) {
        this.roof = roof;
    }

    public Equipment getTrainingPants() {
        return trainingPants;
    }

    public void setTrainingPants(Equipment trainingPants) {
        this.trainingPants = trainingPants;
    }

    public Item getToy() {
        return toy;
    }

    public void setToy(Item toy) {
        this.toy = toy;
    }

    public Room getCogLabs() {
        return cogLabs;
    }

    public void setCogLabs(Room cogLabs) {
        this.cogLabs = cogLabs;
    }

    public Item getTrash() {
        return trash;
    }

    public void setTrash(Item trash) {
        this.trash = trash;
    }

    public String[] getToyBuffs() {
        return toyBuffs;
    }

    public void setToyBuffs(String[] toyBuffs) {
        this.toyBuffs = toyBuffs;
    }

    public String[] getStuffyBuffs() {
        return stuffyBuffs;
    }

    public void setStuffyBuffs(String[] stuffyBuffs) {
        this.stuffyBuffs = stuffyBuffs;
    }

    public Room getDemoRoom() {
        return demoRoom;
    }

    public void setDemoRoom(Room demoRoom) {
        this.demoRoom = demoRoom;
    }

    public Item getModelingClay() {
        return modelingClay;
    }

    public void setModelingClay(Item modelingClay) {
        this.modelingClay = modelingClay;
    }

    private void setCharacterAlignment() {
        getGui().display("Choose your alignment: Rebel or Loyalist.", "Black");
        readFile("alignments");
        Player.setAlignment(getGui().getInput());
    }

    private void playChapter2() {
        if (Player.alignment.equalsIgnoreCase("Rebel")) {
            readFile("rebelChapter2");
        } else {
            readFile("loyalistChapter2");
        }
    }

    private void setCharacterBio() {
        explainCharacterBio();
        dialogWithFuzzy();

    }

    private void dialogWithFuzzy() {
        getGui().display("Fuzzy: What is your name?", "Black");
        getGui().waitForInput();
        Player.setName(getGui().getInput());
        getGui().display("Fuzzy: How old are you?", "Black");
        getGui().waitForInput();
        Player.setAge(getGui().getInput());

        getGui().display("Fuzzy: What is your favorite color?", "Black");
        getGui().waitForInput();
        String color = getGui().getInput();
        getGui().display("Fuzzy: What is your favorite toy?", "Black");
        getGui().waitForInput();
        String toy1 = getGui().getInput();
        Item playersToy = new Item(color + " " + toy1, "A " + color + " " + toy1 + " ", "Toy", false);
        items.put(color + " " + toy1, playersToy);
        playersToy.setType("Toy");
        Player.addItem(playersToy);
        getGui().display("Fuzzy: What does your " + color + " " + toy1 + " do? ", "Black");
        String toyFuction = (String) JOptionPane.showInputDialog(null,
                "Choose an exit",
                "Exits",
                JOptionPane.QUESTION_MESSAGE,
                null, toyBuffs, toyBuffs[0]);
        if (toyFuction.equalsIgnoreCase("Motor")) {
            playersToy.setBuff("Motor");
        } else if (toyFuction.equalsIgnoreCase("Social")) {
            playersToy.setBuff("Social");
        } else if (toyFuction.equalsIgnoreCase("Emotional")) {
            playersToy.setBuff("Emotional");
        } else if (toyFuction.equalsIgnoreCase("Imagenation")) {
            playersToy.setBuff("Imagenation");
        } else if (toyFuction.equalsIgnoreCase("Learning")) {
            playersToy.setBuff("Learning");
        }
        getGui().display(playersToy.getName() + " " + playersToy.getDescription(), "Black");

        getGui().display("What is your favorite animal?", "Black");
        getGui().waitForInput();
        String animal = getGui().getInput();
        getGui().display(animal, color);
        Item stuffy = new Item(animal, "A " + color + " " + animal + " stuffy", "Toy", true);
        items.put(animal, stuffy);
        stuffy.setType("Toy");
        Player.addItem(stuffy);
        getGui().display("Fuzzy: What does your " + color + " " + animal + " do? ", "Black");
        String stuffyFuction = (String) JOptionPane.showInputDialog(null,
                "Choose an exit",
                "Exits",
                JOptionPane.QUESTION_MESSAGE,
                null, stuffyBuffs, stuffyBuffs[0]);
        if (stuffyFuction.equalsIgnoreCase("Calms me down")) {
            stuffy.setBuff("Soothing");
        } else if (stuffyFuction.equalsIgnoreCase("Helps me play pretend")) {
            stuffy.setBuff("Imagenary Friend");
        } else if (stuffyFuction.equalsIgnoreCase("Helps me make friends")) {
            stuffy.setBuff("Tea Party Guest");
        } else if (stuffyFuction.equalsIgnoreCase("Keeps me focused")) {
            stuffy.setBuff("Study Buddy");
        } else if (stuffyFuction.equalsIgnoreCase("I dress it")) {
            stuffy.setBuff("Dress Up");
        }
        getGui().display(stuffy.getName() + " " + stuffy.getDescription(), "Black");
        getGui().display("Fuzzy: What is your favorite food?", "Black");
        getGui().waitForInput();
        String food = getGui().getInput();
        getGui().display("Fuzzy: What is your favorite game?", "Black");
        getGui().waitForInput();
        String game1 = getGui().getInput();
        getGui().display("Fuzzy: What is your favorite book?", "Black");
        getGui().waitForInput();
        String book1 = getGui().getInput();
        getGui().display("Fuzzy: What is your favorite subject?", "Black");
        getGui().waitForInput();
        String subject = getGui().getInput();
        getGui().display("Fuzzy: What is your favorite activity?", "Black");
        getGui().waitForInput();
        String activity = getGui().getInput();
        getGui().display(color + " " + food + " " + toy1 + " " + game1 + " " + book + " " + subject + " " + activity, "Black");
        Player.setFavorites(color, food, toy1, game1, book1, subject, activity);
        getGui().display("Fuzzy: Do you have perfered pronouns?", "Black");
        Player.setPronouns();

    }

    private void explainCharacterBio() {
        readFile("characterBio");
    }

    private void playTutorial() {
        readFile("tutorial");
    }

    private void playChapter1() {
        if (Player.alignment.equalsIgnoreCase("Rebel")) {
            readFile("rebelChapter1");
        } else {
            readFile("loyalistChapter1");
        }
    }

    private void playChapter3() {
        if (Player.alignment.equalsIgnoreCase("Rebel")) {
            readFile("rebelChapter3");
        } else {
            readFile("loyalistChapter3");
        }
    }

    private void playChapter4() {
        if (Player.alignment.equalsIgnoreCase("Rebel")) {
            readFile("rebelChapter4");
        } else {
            readFile("loyalistChapter4");
        }
    }

    private void playChapter5() {
        if (Player.alignment.equalsIgnoreCase("Rebel")) {
            readFile("rebelChapter5");
        } else {
            readFile("loyalistChapter5");
        }
    }

    private void playEnding() {
        if (Player.alignment.equalsIgnoreCase("Rebel")) {
            readFile("rebelEnding");
        } else {
            readFile("loyalistEnding");
        }
    }

    private void playOutro() {
        readFile("outro");
    }
}
