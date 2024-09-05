
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;



public class GameHandler {
    private static final Map<String, Room> rooms = new HashMap<>();
    private static final Map<String, NPC> npcs = new HashMap<>();
    private static final Map<String, Item> items = new HashMap<>();

    static NPC getNPCByName(String person) {
     return npcs.get(person);   
    }

    static Item getItemByName(String itemName) {
        return items.get(itemName);
    }
private final Game game;
    Item diaper;
    private Container box;
    public static Room room;
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
    private static GUI gui;


    public GameHandler(GUI gui1, Game game1) {
        game = game1;
        gui = gui1;
    }

    public Room movePlayer(Room room) {
        Player.setRoom(room);
        return Player.room;
    }
    public void setUpNPCs(){
        NPC mssagely = new NPC("Ms_Sagely", "A wise old owl who has been around for centuries.", foyer);
        npcs.put("Ms_Sagely", mssagely);
        NPC dawn = new NPC("Dawn", "An ECE student who is eager to learn.", foyer);
        npcs.put("Dawn", dawn);

    }
    public void moveItem(Item item, Room room) {
        room.getInventory().add(item);
    }
    public void moveItems(){
        moveItem(diaper, recoveryRoom);
    }
    public Game getGame() {
        return game;
    }
    public void buildRooms() {
        kitchen = new Room("Kitchen", "A room where you can cook food.");
        rooms.put("Kitchen", kitchen);
        kitchen.addNPC(npcs.get("Ms_Sagely"));
        kitchen.addItem(box);
        mainRoom = new Room("Main_Room", "The main room of the daycare.");
        rooms.put("Main_Room", mainRoom);
        mainRoom.addNPC(npcs.get("Dawn"));
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
        shed = new Room("Shed", "A shed where you can store tools.");
        rooms.put("Shed", shed);
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
        pantry = new Room("Pantry", "A room where you can store food.");
        rooms.put("Pantry", pantry);
        recoveryRoom = new Room("Recovery_Room", "A room where you can recover.");
        rooms.put("Recovery_Room", recoveryRoom);
        



    }
    public void createItems() {
        diaper = new Item("Diaper", "A diaper for you, a baby.");
        items.put("Diaper", diaper);
        diaper.setType("Equipment");
        box = new Container("Box", "A box that contains a key.", "Container", true);
        items.put("Box", box);
        box.addItem(diaper);
        Player.addItem(diaper);
    }
    public void playIntro() {
        readFile("intro1");
        getGui().waitForInput();
        readFile("intro2");
    }
     static String readFile(String fileName) {
        File file = new File(fileName.concat(".txt"));
        if(file.exists()) {
            try {
                StringBuilder content;
                try (FileReader reader = new FileReader(file)) {
                    int character;
                    content = new StringBuilder();
                    while((character = reader.read()) != -1) {
                        content.append((char) character);
                    }   getGui().display(content.toString(), "Black");
                }
                return content.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            getGui().display("File not found.", "Red");
        }
        return null;
    }

    public void setupPlayer() {
        Item nothing = new Item("Nothing", "You have nothing in your pockets.");
        Player.addItem(nothing);
        nothing.setDroppable(false);

        setCharacterBio();
        setCharacterAlignment();
        setCharacterAbilities();
        Game.setRunning(true);
    }
    private void setCharacterAlignment() {
        getGui().display("Choose your alignment: Rebel or Loyalist.", "Black");
        readFile("alignments");
        Player.setAlignment(getGui().getInput());
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
    private void playChapter2() {
        if(Player.alignment.equalsIgnoreCase("Rebel")){
            readFile("rebelChapter2");
        } else {
            readFile("loyalistChapter2");
        }
    }
    public void endGame() {
        playOutro();
    }

    private void setCharacterBio() {
        Player.setAge(getGui().getInput()); 
    }

    private void playTutorial() {
        readFile("tutorial");
    }

    private void playChapter1() {
        if(Player.alignment.equalsIgnoreCase("Rebel")){
            readFile("rebelChapter1");
        } else {
            readFile("loyalistChapter1");
        }
    }

    private void playChapter3() {
        if(Player.alignment.equalsIgnoreCase("Rebel")){
            readFile("rebelChapter3");
        } else {
            readFile("loyalistChapter3");
        }
    }

    private void playChapter4() {
        if(Player.alignment.equalsIgnoreCase("Rebel")){
            readFile("rebelChapter4");
        } else {
            readFile("loyalistChapter4");
        }
    }

    private void playChapter5() {
        if(Player.alignment.equalsIgnoreCase("Rebel")){
            readFile("rebelChapter5");
        } else {
            readFile("loyalistChapter5");
        }
    }

    private void playEnding() {
        if(Player.alignment.equalsIgnoreCase("Rebel")){
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
    public void buildExits(){
        //BackYard
            backYard.addExit(deck);
            backYard.addExit(patio);
            backYard.addExit(playHouse);
            backYard.addExit(pool);
            backYard.addExit(shed);
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
        
        //Shed
            shed.addExit(backYard);
        
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
        public static Room getRoomByName(String name) {
            return rooms.get(name);
        }
    public static Room getRoom() {
        return GameHandler.room;
    }
    public void setUpgame() {

    }
    public static GUI getGui() {
        return gui;
    }
}
