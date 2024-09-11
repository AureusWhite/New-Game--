package Redux2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    public static GUI getGui() {
        return gui;
    }
    static void giveQuestToPlayer(Quest quest){
        Quest quest1 = quests.get(quests.indexOf(quest));
        Player.addQuest(quest1);
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
                            bw.write("<html> <h1><center><Strong>"+room.getName()+"</h1></center></Stong><p style=\"font-size: 16;\">"+room.getDescription()+"</font><h2> Room Details </h2>"+room.getType()+"<br><br><Strong> Items in room</Strong><br>"+room.listItems()+"<br><Strong>People in room</Strong><br>"+room.listPeople()+"<br><br></html>");
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
    public static Quest getQuest(String questName){
        for(Quest quest: quests){
            if(quest.getName().equalsIgnoreCase(questName)){
                return quest;
            }
        }
        return null;
    }
    private void setCharacterBio() {
        explainCharacterBio();
        Player.setName(getGui().getInput());
        Player.setAge(getGui().getInput());
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
    static void createQuests(){
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

    public static Quest getQuestByName(String string) {
        for (Quest quest : quests) {
            if (quest.getName().equalsIgnoreCase(string)) {
                return quest;
            }
        }
        return null;
    }
}
