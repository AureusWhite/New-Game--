package Redux2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
        if (Choices.length == 0) {
            getGui().display("There are no books in this room.", "Black");
        } else {
            getGui().display("Which book would you like to read?", "Black");
            for (String Choice : Choices) {
                getGui().display(Choice, "Black");
            }
            String book = getGui().getInput();
            readFile(book);
        }
    }

    public static void artsCrafts() {
        String Choices[] = Player.getRoom().getItemsByType("Crafts");
        getGui().display("What would you like to make?", "Black");
        for (String Choice : Choices) {
            getGui().display(Choice, "Black");
        }
        String craft = getGui().getInput();
        Player.doCraft(craft);
    }

    public static void educationalGames() {
        String Choices[] = Player.getRoom().getItemsByType("Game");
        getGui().display("Which game would you like to play?", "Black");
        for (String Choice : Choices) {
            getGui().display(Choice, "Black");
        }
        String game1 = getGui().getInput();
        Player.playGame(game1);
    }

    public static void Learning() {
        String Choices[] = {"ABCs", "Numbers", "Shapes", "Colors", "Animals", "Body Parts", "The Calender", "Opposites", "Safety", "Hygiene", "Nutrition"};
        getGui().display("Which would you like to learn?", "Black");
        for (String Choice : Choices) {
            getGui().display(Choice + "<br>", "Black");
        }
        String choice = getGui().getInput();
        Player.learn(choice);
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

    static void giveQuestToPlayer(Quest quest) {
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
                StringBuilder content;
                try (FileReader reader = new FileReader(file)) {
                    int character;
                    content = new StringBuilder();
                    while ((character = reader.read()) != -1) {
                        content.append((char) character);
                    }
                    getGui().display(content.toString(), "Black");
                }
                return content.toString();
            } catch (IOException e) {
                GameHandler.getGui().display("Error reading file.", "Red");
            }
        } else {
            try {
                file.createNewFile();
                FileWriter fw = new FileWriter(fileName);
                try (BufferedWriter bw = new BufferedWriter(fw)) {
                    bw.write("<html> <h1><center><Strong>" + room.getName() + "</h1></center></Stong><p style=\"font-size: 16;\">" + room.getDescription() + "</font><h2> Room Details </h2>" + room.getType() + "<br><br><Strong> Items in room</Strong><br>" + room.listItems() + "<br><Strong>People in room</Strong><br>" + room.listPeople() + "<br><br></html>");
                }
            } catch (IOException e) {
                GameHandler.getGui().display("Error reading file.", "Red");
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

    public static NPC getNPC(NPC effectedNPC) {
        return npcs.get(effectedNPC.getName());
    }

    static void demo() {
        readFile("demo");
        String responce;
        String[] dialogOptions = {"Don't I get a say?", "I belong to no one, I just got here", "I only woke up a few hours ago", "I don't know what's going on."};
        responce = (String) JOptionPane.showInputDialog(null,
             "What do you do?", 
             "Choose", JOptionPane.QUESTION_MESSAGE, 
             null, dialogOptions, dialogOptions[0]);
        switch (responce) {
            case "Don't I get a say?" -> readFile("demo2");
            case "I belong to no one, I just got here" -> readFile("demo2");
            case "I only woke up a few hours ago" -> readFile("demo2");
            case "I don't know what's going on." -> readFile("demo2");
            default -> {
            }
        }
        dialogOptions = new String[]{"I'm with Taliber!", "I kinda like Dishes.", "I'm with Farah.", "I don't want to deside yet."};
        responce = (String) JOptionPane.showInputDialog(null,
             "What do you do?", 
             "Choose", JOptionPane.QUESTION_MESSAGE, 
             null, dialogOptions, dialogOptions[0]);
        switch (responce) {
            case "I'm with Taliber!" -> readFile("demo3");
            case "I kinda like Dishes." -> readFile("demo3");
            case "I'm with Farah." -> readFile("demo3");
            case "I don't want to deside yet." -> readFile("demo3");
            default -> {
            }
        }

    }

    Item diaper;
    private Container box;
    public Room recoveryRoom;
    public Room kitchen;
    public Room mainRoom;
    public Room dorms;
    public Room bathroom;
    public Room hallway;
    public Room stairs;
    public Room basement;
    public Room attic;
    public Room garage;
    public Room garden;
    public Room driveway;
    public Room frontYard;
    public Room backYard;
    public Room shed;
    public Room pool;
    public Room patio;
    public Room deck;
    public Room porch;

    public Room balcony;

    public Room cubbies;
    public Room dramaArea;
    public Room changingRoom;

    public Room floorPlay;
    public Room quietArea;
    public Room homeWorkArea;
    public Room playHouse;
    public Room treeHouse;

    public Room storyBookVillage;

    public Room pillowPile;

    public Room snackArea;

    public Room greenHall;

    public Room blueHall;
    public Room redHall;
    public Room peddleToys;
    public Room lemonaidStand;
    public Room toolShed;

    public Room TRSRoom;

    public Room janitorialRoom;

    public Room foyer;

    public Room pantry;

    public Room roof;

    private Equipment trainingPants;

    private Item toy;

    public Room cogLabs;

    private Item trash;

    private String[] toyBuffs = {"Social", "Motor", "Imagenation", "Learning", "Emotional"};

    private String[] stuffyBuffs = {"Calms me down", "Helps me play pretend", "Helps me make friends", "Keeps me focused", "I dress it"};
    private Room demoRoom;

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
        foyer.addItem(box);
        pantry = new Room("Pantry", "A room where you can store food.");
        rooms.put("Pantry", pantry);
        recoveryRoom = new Room("Recovery_Room", "A room where you can recover.");
        rooms.put("Recovery_Room", recoveryRoom);
        demoRoom = new Room("Demo_Room", "A room where you can play with toys.");
        rooms.put("Demo_Room", demoRoom);
        demoRoom.addItem(toy);
        demoRoom.addItem(box);
        demoRoom.addItem(diaper);
        demoRoom.addItem(trainingPants);
        demoRoom.addItem(trash);
        demoRoom.addNPC(npcs.get("Ms_Sagely"));
        
    }

    public void createItems() {
        toy = new Item("Toy", "A toy for you to play with.", "Toy", false);
        items.put("Toy", toy);
        toy.setType("Toy");
        trash = new Item("Trash", "Trash that needs to be thrown away.", "Trash", true);
        items.put("Trash", trash);
        trash.setType("Trash");
        diaper = new Equipment("Diaper", "A diaper for you, a baby.", "Underpants");
        items.put("Diaper", diaper);
        diaper.setType("Equipment");

        trainingPants = new Equipment("Training Pants", "Training Pants, for you, a big kid", "Underpants");
        items.put("Training Pants", trainingPants);
        trainingPants.setType("Equipment");

        box = new Container("Box", "A simple cardboard box for storing items", "Furniture", false);
        box.setType("Furniture");
        box.setContraband(true);
        items.put("Box", box);
        box.addItem(diaper);
        Player.addItem(diaper);
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
        Item nothing = new Item("Nothing", "You have nothing in your pockets.");
        Player.addItem(nothing);
        nothing.setDroppable(false);
        setCharacterBio();
        setCharacterAlignment();
        setCharacterAbilities();
        updateStatus();
        Game.setRunning(true);
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

    public void setDiaper(Item diaper) {
        this.diaper = diaper;
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
        String book = getGui().getInput();
        getGui().display("Fuzzy: What is your favorite subject?", "Black");
        getGui().waitForInput();
        String subject = getGui().getInput();
        getGui().display("Fuzzy: What is your favorite activity?", "Black");
        getGui().waitForInput();
        String activity = getGui().getInput();
        getGui().display(color + " " + food + " " + toy1 + " " + game1 + " " + book + " " + subject + " " + activity, "Black");
        Player.setFavorites(color, food, toy1, game1, book, subject, activity);
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

    private void setCharacterAbilities() {
        Player.setCharacterAbilities();
    }
}
