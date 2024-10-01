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
                    if (!content.isEmpty()) {
                        getGui().display(content.toString(), "Black");
                    } // Display everything before first '#'
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
        GameHandler.getGui().clearTextPane();
        GameHandler.getGui().display("You were put in time out for " + i * 10 + " minutes.", "Black");
        while (!apology) {
            String[] acts = {"stealing", "pranking", "Vandalism", "Skipped", "Trespassing", "Sneaking", "climbed", "I don't know"};
            String response = (String) JOptionPane.showInputDialog(null,
                    "What did you do?",
                    "Choose", JOptionPane.QUESTION_MESSAGE,
                    null, acts, acts[0]);
            if (response == null) {
                GameHandler.getGui().display("You Will stay here until you give the correct answers.", "Red");
                break;
            }
            switch (response) {
                case "stealing" -> {
                    if (!act.equalsIgnoreCase("stealing")) {
                        getGui().display("That's not what you did", "Red");
                        break;
                    }
                    String[] reasons = {"Fun", "Profit",};
                    response = (String) JOptionPane.showInputDialog(null,
                            "Why did you steal?",
                            "Choose", JOptionPane.QUESTION_MESSAGE,
                            null, reasons, reasons[0]);

                    if (response == null) {
                        GameHandler.getGui().display("You Will stay here until you give the correct answers.", "Red");
                        break;
                    }
                    switch (response) {
                        case "Fun" -> {
                            apology = true;
                        }
                        case "Profit" -> {
                            apology = true;
                        }
                    }
                }
                case "pranking" -> {
                    if (!act.equalsIgnoreCase("pranking")) {
                        getGui().display("That's not what you did", "Red");
                        break;
                    }
                    String[] reasons = {"Fun", "Profit"};
                    response = (String) JOptionPane.showInputDialog(null,
                            "Why did you prank someone?",
                            "Choose", JOptionPane.QUESTION_MESSAGE,
                            null, reasons, reasons[0]);

                    if (response == null) {
                        GameHandler.getGui().display("You Will stay here until you give the correct answers.", "Red");
                        break;
                    }
                    switch (response) {
                        case "Fun" -> {
                            apology = true;
                        }
                        case "Profit" -> {
                            apology = true;
                        }
                    }
                }
                case "Vandalism" -> {
                    if (!act.equalsIgnoreCase("Vandalism")) {
                        getGui().display("That's not what you did", "Red");
                        break;
                    }
                    String[] reasons = {"Fun", "Profit"};
                    response = (String) JOptionPane.showInputDialog(null,
                            "Why did you vandalize?",
                            "Choose", JOptionPane.QUESTION_MESSAGE,
                            null, reasons, reasons[0]);

                    if (response == null) {
                        GameHandler.getGui().display("You Will stay here until you give the correct answers.", "Red");
                        break;
                    }
                    switch (response) {
                        case "Fun" -> {
                            apology = true;
                        }
                        case "Profit" -> {
                            apology = true;
                        }
                    }
                }
                case "Pickedon" -> {
                    if (!act.equalsIgnoreCase("Pickedon")) {
                        getGui().display("That's not what you did", "Red");
                        break;
                    }
                    String[] reasons = {"Fun", "Profit"};
                    response = (String) JOptionPane.showInputDialog(null,
                            "Why did you pick on someone?",
                            "Choose", JOptionPane.QUESTION_MESSAGE,
                            null, reasons, reasons[0]);

                    if (response == null) {
                        GameHandler.getGui().display("You Will stay here until you give the correct answers.", "Red");
                        break;
                    }
                    switch (response) {
                        case "Fun" -> {
                            apology = true;
                        }
                        case "Profit" -> {
                            apology = true;
                        }
                    }
                }
                case "Skipped" -> {
                    if (!act.equalsIgnoreCase("Skipped")) {
                        getGui().display("That's not what you did", "Red");
                        break;
                    }
                    String[] reasons = {"Fun", "Profit"};
                    response = (String) JOptionPane.showInputDialog(null,
                            "Why did you skip?",
                            "Choose", JOptionPane.QUESTION_MESSAGE,
                            null, reasons, reasons[0]);

                    if (response == null) {
                        GameHandler.getGui().display("You Will stay here until you give the correct answers.", "Red");
                        break;
                    }
                    switch (response) {
                        case "Fun" -> {
                            apology = true;
                        }
                        case "Profit" -> {
                            apology = true;
                        }
                    }
                }
                case "Trespassing" -> {
                    if (!act.equalsIgnoreCase("Trespassing")) {
                        getGui().display("That's not what you did", "Red");
                        break;
                    }
                    String[] reasons = {"Fun", "Profit"};
                    response = (String) JOptionPane.showInputDialog(null,
                            "Why did you trespass?",
                            "Choose", JOptionPane.QUESTION_MESSAGE,
                            null, reasons, reasons[0]);

                    if (response == null) {
                        GameHandler.getGui().display("You Will stay here until you give the correct answers.", "Red");
                        break;
                    }
                    switch (response) {
                        case "Fun" -> {
                            apology = true;
                        }
                        case "Profit" -> {
                            apology = true;
                        }
                    }
                }
                case "Sneaking" -> {
                    if (!act.equalsIgnoreCase("Sneaking")) {
                        getGui().display("That's not what you did", "Red");
                        break;
                    }
                    String[] reasons = {"Fun", "Profit"};
                    response = (String) JOptionPane.showInputDialog(null,
                            "Why did you sneak?",
                            "Choose", JOptionPane.QUESTION_MESSAGE,
                            null, reasons, reasons[0]);

                    if (response == null) {
                        GameHandler.getGui().display("You Will stay here until you give the correct answers.", "Red");
                        break;
                    }
                    switch (response) {
                        case "Fun" -> {
                            apology = true;
                        }
                        case "Profit" -> {
                            apology = true;
                        }
                    }
                }
                case "climbed" -> {
                    if (!act.equalsIgnoreCase("climbed")) {
                        getGui().display("That's not what you did", "Red");
                        break;
                    }
                    String[] reasons = {"Fun", "Profit"};
                    response = (String) JOptionPane.showInputDialog(null,
                            "Why did you climb?",
                            "Choose", JOptionPane.QUESTION_MESSAGE,
                            null, reasons, reasons[0]);

                    if (response == null) {
                        GameHandler.getGui().display("You Will stay here until you give the correct answers.", "Red");
                        break;
                    }
                    switch (response) {
                        case "Fun" -> {
                            apology = true;
                        }
                        case "Profit" -> {
                            apology = true;
                        }
                    }
                }
                case "I don't know" -> {
                    getGui().display("You will stay here until you give the correct answers.", "Red");
                    break;
                }
            }
        }
        if (apology) {
            getGui().display("You have been forgiven", "Green");
            getGui().unlockButtons();
        } else {
            getGui().display("You will stay here until you give the correct answers.", "Red");
        }
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
            smartboard, globe, map, calendar, compass, protractor, canvas, snackShop;

    private NPC msSagely, dawn, taliber, susy, farah, drWhite, msWhite, aureus,
            jessiem, researchStudent1, researchStudent2, jimthejanitor, joy, jessief, jim;

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

        msSagely = new NPC("Ms_Sagely", "A wisen old woman whos presence is as comforting as it is dignified.", foyer, "adult");
        npcs.put("Ms_Sagely", msSagely);
        dawn = new NPC("Dawn", "An ERE Teacher and assistant to Ms Sagely, she is dressed in fun and colorful clothing.", foyer, "child");
        npcs.put("Dawn", dawn);
        taliber = new NPC("Taliber", "A rejuve and prefect of about seven, he seems to be someone who the others want to follow.", foyer, "child");
        npcs.put("Taliber", taliber);
        susy = new NPC("Susy", "A rejuve and prefect, she acts a lot older than the eight or so she looks like. She wears a white apron and and a pink chiefs hat near always. ", foyer, "child");
        npcs.put("Susy", susy);
        farah = new NPC("Farah", "A rejuve and prefect, she looks alot older than the other students here. The others seem to come to her for advice and help, unlike the other prefects who throw their weight around.", foyer, "child");
        npcs.put("Farah", farah);
        drWhite = new NPC("Dr_White", "A thin and tall man, Dr White observes more than he talks. He can be seen standing off to the side as the other adults do their jobs.", foyer, "adult");
        npcs.put("Dr_White", drWhite);
        msWhite = new NPC("Ms_White", "The rejuve/pediatric nurse, also a doctor of her own right how ever she is rarely seen", foyer, "adult");
        npcs.put("Ms_White", msWhite);
        aureus = new NPC("Aureus", "This girl a rejuve of about 4 is as feisty as she is short, she is always wearing a dog eared touque, even to bed or when it's 100 degrees out.", foyer, "child");
        npcs.put("Aureus", aureus);
        jessiem = new NPC("Jessie", "This is jessie, brother to Jessie. Strangly for being brother and sister they hardly are ever seen together.", foyer, "child");
        npcs.put("Jessie", jessiem);
        researchStudent1 = new NPC("Research_Student_M", "As tight liped as the doctor they research under, also with the drab and white coat, He and his colleges sport an emotionless expression and always carry a 'clicker' and notepad.", foyer, "child");
        npcs.put("Research_Student_M", researchStudent1);
        researchStudent2 = new NPC("Research_Student_F", "As tight liped as the doctor they research under, also with the drab and white coat, She and her colleges sport an emotionless expression and always carry a 'clicker' and notepad.", foyer, "child");
        npcs.put("Research_Student_F", researchStudent2);
        jimthejanitor = new NPC("Jim", "He can be seen cleaning up after the kids, he is a nice man who is always willing to lend a hand.", foyer, "adult");
        npcs.put("Jim", jimthejanitor);
        joy = new NPC("Joy", "You're not sure what she does here, but she is always around, she is always smiling when the kids to listen to her tell stories or run arts and crafts.", foyer, "adult");
        npcs.put("Joy", joy);
        jessief = new NPC("Jessie", "This is jessie, sister to Jessie. Strangly for being brother and sister they hardly are ever seen together.", foyer, "child");
        npcs.put("Jessie", jessief);

    }

    public void moveItem(Item item, Room room) {
        room.getInventory().add(item);
    }

    public Game getGame() {
        return game;
    }

    public void buildRooms() {
        kitchen = new Room("Kitchen", "A room where you can cook food.");
        rooms.put("Kitchen", kitchen);
        mainRoom = new Room("Main_Room", "The main room of the daycare.");
        rooms.put("Main_Room", mainRoom);
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
        pantry = new Room("Pantry", "A room where you can store food.");
        rooms.put("Pantry", pantry);
        recoveryRoom = new Room("Recovery_Room", "A room where you can recover.");
        rooms.put("Recovery_Room", recoveryRoom);
        demoRoom = new Room("Demo_Room", "A room where you can play with toys.");
        rooms.put("Demo_Room", demoRoom);
        demoRoom.addItem(snackShop);
        demoRoom.addItem(toy);
        demoRoom.addItem(book);
        foyer.addItem(snackShop);

        Player.setRoom(recoveryRoom);
    }

    public void createItems() {
        snackShop = new Shops("Snack Shop", "A shop where you can buy snacks.");
        items.put("Snack Shop", snackShop);
        snackShop.setType("Shop/parkour/interactable");

        toy = new Item("Toy", "A toy for you to play with.", "Toy", false);
        items.put("Toy", toy);
        toy.setType("Toy");
        coloringBook = new Item("Coloring Book", "A coloring book for you to color in.", "Crafts", true);
        items.put("Coloring Book", coloringBook);
        coloringBook.setType("Crafting/Stationary");
        crayons = new Item("Crayons", "Crayons for you to color with.", "Crafts", true);
        items.put("Crayons", crayons);
        crayons.setType("Crafting/Supplies");
        markers = new Item("Markers", "Markers for you to color with.", "Crafts", true);
        items.put("Markers", markers);
        markers.setType("Crafting/Supplies");
        paint = new Item("Paint", "Paint for you to paint with.", "Crafts", true);
        items.put("Paint", paint);
        paint.setType("Crafting/Supplies");
        canvas = new Item("Canvas", "A canvas for you to paint on.", "Crafts", true);
        items.put("Canvas", canvas);
        canvas.setType("Crafting/Stationary");
        paintBrush = new Item("Paint Brush", "A paint brush for you to paint with.", "Crafts", true);
        items.put("Paint Brush", paintBrush);
        paintBrush.setType("Craft/Tool");
        glue = new Item("Glue", "Glue for you to glue with.", "Crafts", true);
        items.put("Glue", glue);
        glue.setType("Crafting/Supplies");
        scissors = new Item("Scissors", "Scissors for you to cut with.", "Crafts", true);
        items.put("Scissors", scissors);
        scissors.setType("Crafting/Tool");
        paper = new Item("Paper", "Paper for you to write on.", "Crafts", true);
        items.put("Paper", paper);
        paper.setType("Crafting/Stationary");
        pencil = new Item("Pencil", "A pencil for you to write with.", "Crafts", true);
        items.put("Pencil", pencil);
        pencil.setType("Crafting/Supplies");
        eraser = new Item("Eraser", "An eraser for you to erase with.", "Crafts", true);
        items.put("Eraser", eraser);
        eraser.setType("Crafting/Supplies");
        calculator = new Item("Calculator", "A calculator for you to calculate with.", "Crafts", true);
        items.put("Calculator", calculator);
        calculator.setType("Educational/Tool");
        ruler = new Item("Ruler", "A ruler for you to measure with.", "Crafts", true);
        items.put("Ruler", ruler);
        ruler.setType("Crafting/Tool/Educational");
        scale = new Item("Scale", "A scale for you to weigh with.", "Crafts", true);
        items.put("Scale", scale);
        scale.setType("Cooking/Tool/Educational");
        thermometer = new Item("Thermometer", "A thermometer for you to measure temperature with.", "Crafts", true);
        items.put("Thermometer", thermometer);
        thermometer.setType("Cooking/Tool/Educational");
        magnifyingGlass = new Item("Magnifying Glass", "A magnifying glass for you to magnify with.", "Crafts", true);
        items.put("Magnifying Glass", magnifyingGlass);
        magnifyingGlass.setType("Crafting/Science/Tool");
        telescope = new Item("Telescope", "A telescope for you to see far away with.", "Crafts", true);
        items.put("Telescope", telescope);
        telescope.setType("Science/Tool");
        microscope = new Item("Microscope", "A microscope for you to see small things with.", "Crafts", true);
        items.put("Microscope", microscope);
        microscope.setType("Science/Tool");
        binoculars = new Item("Binoculars", "Binoculars for you to see far away with.", "Crafts", true);
        items.put("Binoculars", binoculars);
        binoculars.setType("Exploration/Tool/Educational");
        camera = new Item("Camera", "A camera for you to take pictures with.", "Crafts", true);
        items.put("Camera", camera);
        camera.setType("Tool/Social/Entertainment");
        videoCamera = new Item("Video Camera", "A video camera for you to take videos with.", "Crafts", true);
        items.put("Video Camera", videoCamera);
        videoCamera.setType("Tech/Tool/Entertainment");
        tapeRecorder = new Item("Tape Recorder", "A tape recorder for you to record with.", "Crafts", true);
        items.put("Tape Recorder", tapeRecorder);
        tapeRecorder.setType("Tech/Tool/Entertainment");
        radio = new Item("Radio", "A radio for you to listen to music with.", "Crafts", true);
        items.put("Radio", radio);
        radio.setType("Music/Educational/Entertainment");
        television = new Item("Television", "A television for you to watch TV with.", "Crafts", true);
        items.put("Television", television);
        television.setType("Entertainment/Educational");
        computer = new Item("Computer", "A computer for you to use.", "Crafts", true);
        items.put("Computer", computer);
        computer.setType("Tool/Educational/Entertainment");
        tablet = new Item("Tablet", "A tablet for you to use.", "Crafts", true);
        items.put("Tablet", tablet);
        tablet.setType("Tool/Educational/Entertainment");
        phone = new Item("Phone", "A phone for you to use.", "Crafts", true);
        items.put("Phone", phone);
        phone.setType("Tool/Communication/Entertainment");
        speaker = new Item("Speaker", "A speaker for you to listen to music with.", "Crafts", true);
        items.put("Speaker", speaker);
        speaker.setType("Music/Furniture");
        headphones = new Item("Headphones", "Headphones for you to listen to music with.", "Crafts", true);
        items.put("Headphones", headphones);
        headphones.setType("Music/Equipment");
        microphone = new Item("Microphone", "A microphone for you to record with.", "Crafts", true);
        items.put("Microphone", microphone);
        microphone.setType("Music/Equipment");
        keyboard = new Item("Keyboard", "A keyboard for you to type with.", "Crafts", true);
        items.put("Keyboard", keyboard);
        keyboard.setType("Tool/Tech");
        mouse = new Item("Mouse", "A mouse for you to click with.", "Crafts", true);
        items.put("Mouse", mouse);
        mouse.setType("Tool/Tech");
        monitor = new Item("Monitor", "A monitor for you to see with.", "Crafts", true);
        items.put("Monitor", monitor);
        monitor.setType("Furniture/Tech");
        printer = new Item("Printer", "A printer for you to print with.", "Crafts", true);
        items.put("Printer", printer);
        printer.setType("Furniture/Tech");
        scanner = new Item("Scanner", "A scanner for you to scan with.", "Crafts", true);
        items.put("Scanner", scanner);
        scanner.setType("Furniture/Tech");
        projector = new Item("Projector", "A projector for you to project with.", "Crafts", true);
        items.put("Projector", projector);
        projector.setType("Tech/Furniture/Entertainment");
        whiteboard = new Item("Whiteboard", "A whiteboard for you to write on.", "Crafts", true);
        items.put("Whiteboard", whiteboard);
        whiteboard.setType("Educational/Furniture/Crafting");
        chalkboard = new Item("Chalkboard", "A chalkboard for you to write on.", "Crafts", true);
        items.put("Chalkboard", chalkboard);
        chalkboard.setType("Educational/Furniture/Crafting");
        smartboard = new Item("Smartboard", "A smartboard for you to write on.", "Crafts", true);
        items.put("Smartboard", smartboard);
        smartboard.setType("Educational/Tech/Crafting");
        globe = new Item("Globe", "A globe for you to learn about the world.", "Crafts", true);
        items.put("Globe", globe);
        globe.setType("Educational/Science");
        map = new Item("Map", "A map for you to learn about the world.", "Crafts", true);
        items.put("Map", map);
        map.setType("Crafts");
        calendar = new Item("Calendar", "A calendar for you to keep track of time.", "Crafts", true);
        items.put("Calendar", calendar);
        calendar.setType("Educational/Tool");
        compass = new Item("Compass", "A compass for you to find your way.", "Crafts", true);
        items.put("Compass", compass);
        compass.setType("Educational/Tool/Exploration");
        protractor = new Item("Protractor", "A protractor for you to measure angles with.", "Crafts", true);
        items.put("Protractor", protractor);
        protractor.setType("Science/Tool/Educational");

        modelingClay = new Item("Modeling Clay", "Modeling clay for you to play with.", "Crafts", true);
        items.put("Modeling Clay", modelingClay);
        modelingClay.setType("Crafting Supplies");
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
        Player.setMoney(9);
        //setCharacterBio();
        updateStatus();
        Game.setRunning(true);
        getGui().unlockButtons();
    }

    public void playGame() {
        playTutorial();
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

    public void setCharacterBio() {
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
        getGui().display("Fuzzy: What does your " + color + " " + toy1 + " help you practice?", "Black");
        String toyFuction = (String) JOptionPane.showInputDialog(null,
                "Choose an exit",
                "Exits",
                JOptionPane.QUESTION_MESSAGE,
                null, toyBuffs, toyBuffs[0]);
        switch (toyFuction) {
            case "Motor" ->
                Player.levelUpSkill(Skill.MOTOR);
            case "Social" ->
                Player.levelUpSkill(Skill.SOCIAL);
            case "Imagination" ->
                Player.levelUpSkill(Skill.IMAGINATION);
            case "Learning" ->
                Player.levelUpSkill(Skill.LEARNING);
            case "Emotional" ->
                Player.levelUpSkill(Skill.EMOTIONAL);
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
        switch (stuffyFuction) {
            case "Calms me down" ->
                Player.perks.put("Soothing", true);
            case "Helps me play pretend" ->
                Player.perks.put("Imagenary Friend", true);
            case "Helps me make friends" ->
                Player.perks.put("Tea Party Guest", true);
            case "Keeps me focused" ->
                Player.perks.put("Study Buddy", true);
            case "I dress it" ->
                Player.perks.put("Dress Up", true);
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

    private void playOutro() {
        readFile("outro");
    }

    void populateRooms() {
        //basement.addNPC();
        //attic.addNPC();
        garage.addNPC(jim);
        //garden.addNPC();
        //driveway.addNPC();
        //frontYard.addNPC();
        //backYard.addNPC();
        cogLabs.addNPC(researchStudent1);
        //pool.addNPC();
        //patio.addNPC();
        //deck.addNPC();
        //porch.addNPC();
        //balcony.addNPC();
        //roof.addNPC();
        //cubbies.addNPC();
        //dramaArea.addNPC();
        //changingRoom.addNPC();
        //floorPlay.addNPC();
        //quietArea.addNPC();
        //homeWorkArea.addNPC();
        //playHouse.addNPC();
        treeHouse.addNPC(taliber);
        storyBookVillage.addNPC(joy);
        //pillowPile.addNPC();
        //snackArea.addNPC();
        //greenHall.addNPC();
        //blueHall.addNPC();
        //redHall.addNPC();
        //peddleToys.addNPC();
        //lemonaidStand.addNPC();
        //toolShed.addNPC();
        //TRSRoom.addNPC();
        //janitorialRoom.addNPC();
        kitchen.addNPC(susy);
        foyer.addNPC(drWhite);
        //pantry.addNPC();
        //recoveryRoom.addNPC();
        //demoRoom.addNPC();
    }

    public void giveItems() {
        Player.addItem(diaper);
        drWhite.addItem(box);
        foyer.addItem(box);
        box.addItem(trash);
        box.addItem(toy);
        snackShop.addItem(diaper);
        snackShop.addItem(toy);
        toy.setPrice(5);
        diaper.setPrice(5);
        box.setPrice(6);
    }
}
