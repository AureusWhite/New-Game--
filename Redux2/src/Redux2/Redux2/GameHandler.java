package Redux2;

import Redux2.Achievements.NPCStatus;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.JOptionPane;


public class GameHandler {

    private static final Map<String, Room> rooms = new HashMap<>();
    static final Map<String, NPC> npcs = new HashMap<>();
    static final Map<String, Item> items = new HashMap<>();
    //private static final ArrayList<Quest> quests = new ArrayList<>();
    public static Room room;
    private static GUI gui;
    private static Game game;
    public static String fileSection2 = "";
    public static String fileSection3 = "";
    public static String fileSection4 = "";
    private final static ArrayList<Achievements> achievements = new ArrayList<>();

    private static String toSentenceCase(String name) {
        name = name.toLowerCase();
        name = name.replace("_", " ");
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        return name;
    }

    static void storyTime() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private Container box,foodTray;
    
    public Room recoveryRoom, kitchen, mainRoom,
            dorms, bathroom, hallway, stairs, basement,
            attic, garage, garden, driveway, frontYard,
            backYard, shed, pool, patio, deck, porch,
            balcony, cubbies, dramaArea, changingRoom,
            floorPlay, quietArea, homeWorkArea, playHouse,
            treeHouse, storyBookVillage, pillowPile,
            snackArea, greenHall, blueHall, redHall,
            peddleToys, lemonaidStand, toolShed, TRSRoom,
            janitorialRoom, foyer, pantry, roof, demoRoom, cogLabs,classRoom;

    private Equipment trainingPants, diaper, thickDiapers, backPack, underPants, uniformTop, uniformBottom, uniformHat, uniformShoes, uniBackPack;

    private Item toy, book, trash, modelingClay, coloringBook, crayons,
            markers, paint, paintBrush, glue, scissors, paper, pencil, eraser,
            calculator, ruler, scale, thermometer, magnifyingGlass, telescope, microscope,
            binoculars, camera, videoCamera, tapeRecorder, radio, television,
            computer, tablet, phone, speaker, headphones, microphone, keyboard,
            mouse, monitor, printer, scanner, projector, whiteboard, chalkboard,
            smartboard, globe, map, calendar, compass, protractor, canvas, snackShop,pickapaw,pawFigure,limitedEditionPaw;
    private static Item foodTrayDispenser;

    private NPC msSagely, dawn, taliber, susy, farah, drWhite, msWhite, aureus,
            jessiem, researchStudent1, researchStudent2, jimthejanitor, joy, jessief, jim, fuzzy;


    public void createItems() {
        foodTray = new Container("Food Tray", "A tray for you to eat food on.", "Food Tray", true);
        items.put("Food Tray", foodTray);
        foodTrayDispenser = new Item("Food Tray Dispenser", "A machine that dispenses food trays.", "Machine/Interactable", false);
        items.put("Food Tray Dispenser", foodTrayDispenser);
        foodTrayDispenser.getConditions().put(ItemCondition.TAKEABLE, false);
        foodTrayDispenser.getConditions().put(ItemCondition.INTERACTABLE, true);
        foodTrayDispenser.getConditions().put(ItemCondition.DISPENCER, true);
        pickapaw = new Shops("Pick a Paw", "A shop where you can buy Paws and Paw and Prowess cards.");
        items.put("Pick a Paw", pickapaw);
        pickapaw.setType("Shop/Trading/Interactable");
        pawFigure = new PawFigure();
        items.put("Paw Figure", pawFigure);
        pawFigure.setType("Paw");
        limitedEditionPaw = new Item("Limited Edition Paw", "A limited edition paw.", "Paw", false);
        items.put("Limited Edition Paw", limitedEditionPaw);
        limitedEditionPaw.setType("Paw");
        snackShop = new Shops("Snack Shop", "A shop where you can buy snacks.");
        items.put("Snack Shop", snackShop);
        snackShop.setType("Shop/parkour/interactable");
        backPack = new Equipment("Back Pack", "A back pack for you to carry things in.", "Equipment");
        items.put("Back Pack", backPack);
        backPack.setType("Equipment/Storage");
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
        diaper = new Equipment("Diaper", "A diaper for you, a baby :P.", "Underpants");
        items.put("Diaper", diaper);
        diaper.setType("Equipment");
        thickDiapers = new Equipment("Thick Diapers", "A diaper for you, a baby.", "Underpants");
        items.put("Thick Diapers", thickDiapers);
        thickDiapers.setType("Equipment");
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
        underPants = new Equipment("Underpants", "Underpants, for you, a big kid", "Underpants");
        items.put("Underpants", underPants);
        underPants.setType("Equipment");
        uniformTop = new Equipment("Uniform Top", "A top for your uniform", "Top");
        items.put("Uniform Top", uniformTop);
        uniformTop.setType("Equipment");
        uniformBottom = new Equipment("Uniform Bottom", "A bottom for your uniform", "Bottom");
        items.put("Uniform Bottom", uniformBottom);
        uniformBottom.setType("Equipment");
        uniformHat = new Equipment("Uniform Hat", "A hat for your uniform", "Hat");
        items.put("Uniform Hat", uniformHat);
        uniformHat.setType("Equipment");
        uniformShoes = new Equipment("Uniform Shoes", "Shoes for your uniform", "Shoes");
        items.put("Uniform Shoes", uniformShoes);
        uniformShoes.setType("Equipment");
        uniBackPack = new Equipment("Uniform Back Pack", "A back pack for your uniform", "Back Pack");
        items.put("Uniform Back Pack", uniBackPack);
        uniBackPack.setType("Equipment");
        uniBackPack.setSlot("Back");
        uniformBottom.setSlot("Bottom");
        uniformTop.setSlot("Top");
        uniformHat.setSlot("Hat");
        uniformShoes.setSlot("Shoes");
        trainingPants.setSlot("Underpants");
        diaper.setSlot("Underpants");
        thickDiapers.setSlot("Underpants");
        underPants.setSlot("Underpants");

    }

    public void setUpNPCs() {
        fuzzy = new NPC("Fuzzy", "A large stuffed bear, he is always around and always seems to be watching.", foyer, "companion");
        npcs.put("Fuzzy", fuzzy);
        msSagely = new NPC("Ms_Sagely", "A wisen old woman whos presence is as comforting as it is dignified.", foyer, "adult");
        npcs.put("Ms_Sagely", msSagely);
        dawn = new NPC("Dawn", "An ERE TeAchr and assistant to Ms Sagely, she is dressed in fun and colorful clothing.", foyer, "child");
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
    public void createAchievements() {
        Achievements explorer = new Achievements("Exsplore", "Exsplore all the rooms in the house", 10);
        explorer.addRequiredPlace(foyer);
        explorer.addRequiredPlace(dorms);

        Achievements snappyDresser = new Achievements("Snappy Dresser", "Wear a uniform", 10);
        snappyDresser.addRequiredEquipment(uniformTop);
        snappyDresser.addRequiredEquipment(uniformBottom);
        snappyDresser.addRequiredEquipment(uniformHat);
        snappyDresser.addRequiredEquipment(uniformShoes);
        snappyDresser.addRequiredEquipment(uniBackPack);

        Achievements collector = new Achievements("Collector", "Collect all the toys", 10);
        collector.addRequiredItem(toy);

        Achievements socialite = new Achievements("Socialite", "Make a friend", 10);
        socialite.addRequiredNPC(fuzzy, NPCStatus.FRIEND);

        Achievements nemisis = new Achievements("Nemisis", "Make an enemy", 10);
        nemisis.addRequiredNPC(fuzzy, NPCStatus.ENEMY);

        Achievements bookWorm = new Achievements("Book Worm", "Read all the books", 10);
        bookWorm.addRequiredItem(book);

        Achievements meetEveryone = new Achievements("Meet Everyone", "Speak to all NPCs", 20);
        meetEveryone.addRequiredNPC(fuzzy, NPCStatus.SPOKEN_TO);
        meetEveryone.addRequiredNPC(msSagely, NPCStatus.SPOKEN_TO);
        meetEveryone.addRequiredNPC(dawn, NPCStatus.SPOKEN_TO);
        meetEveryone.addRequiredNPC(taliber, NPCStatus.SPOKEN_TO);
        meetEveryone.addRequiredNPC(susy, NPCStatus.SPOKEN_TO);
        meetEveryone.addRequiredNPC(farah, NPCStatus.SPOKEN_TO);
        meetEveryone.addRequiredNPC(drWhite, NPCStatus.SPOKEN_TO);
        meetEveryone.addRequiredNPC(msWhite, NPCStatus.SPOKEN_TO);
        meetEveryone.addRequiredNPC(aureus, NPCStatus.SPOKEN_TO);
        meetEveryone.addRequiredNPC(jessiem, NPCStatus.SPOKEN_TO);
        meetEveryone.addRequiredNPC(researchStudent1, NPCStatus.SPOKEN_TO);
        meetEveryone.addRequiredNPC(researchStudent2, NPCStatus.SPOKEN_TO);
        meetEveryone.addRequiredNPC(jimthejanitor, NPCStatus.SPOKEN_TO);
        meetEveryone.addRequiredNPC(joy, NPCStatus.SPOKEN_TO);
        meetEveryone.addRequiredNPC(jessief, NPCStatus.SPOKEN_TO);
            // Add all the achievements to the list
            achievements.add(explorer);
            achievements.add(snappyDresser);
            achievements.add(collector);
            achievements.add(socialite);
            achievements.add(bookWorm);
            achievements.add(meetEveryone);
            achievements.add(nemisis);
    }
    public void createRoutine() {
        final RoutineManager routineManager1 = new RoutineManager();
        this.routineManager = routineManager1;
    }

    public RoutineManager getRoutineManager() {
        return this.routineManager;
    } 
    public static void updateAchievementsForNPC(NPC npc, NPCStatus status) {
        for (Achievements achievement : achievements) {
            if (achievement.getRequiredNPC().containsKey(npc)) {
                achievement.getRequiredNPC().put(npc, status);
                achievement.updateAchievement();
            }
        }

    }
    public void buildRooms() {
        kitchen = new Room("Kitchen", "A room where you can cook food.", Room.ROOMTYPE.KITCHEN);
        rooms.put("Kitchen", kitchen);

        classRoom = new Room("Class_Room", "A room where you can learn.", Room.ROOMTYPE.SCHOOL);
        rooms.put("Class_Room", classRoom);
        
        mainRoom = new Room("Main_Room", "The main room of the daycare.", Room.ROOMTYPE.GREEN);
        rooms.put("Main_Room", mainRoom);
        
        dorms = new Room("Dorms", "A room where you can sleep.", Room.ROOMTYPE.GREEN);
        rooms.put("Dorms", dorms);
        
        bathroom = new Room("Bathroom", "A room where you can clean yourself.", Room.ROOMTYPE.BATHROOM);
        rooms.put("Bathroom", bathroom);
        
        hallway = new Room("Hallway", "A hallway that connects the rooms.", Room.ROOMTYPE.INDOOR);
        rooms.put("Hallway", hallway);
        
        stairs = new Room("Stairs", "A staircase that leads to the basement and attic.", Room.ROOMTYPE.INDOOR);
        rooms.put("Stairs", stairs);
        
        basement = new Room("Basement", "A room where you can store things.", Room.ROOMTYPE.INDOOR);
        rooms.put("Basement", basement);
        
        attic = new Room("Attic", "A room where you can store things.", Room.ROOMTYPE.INDOOR);
        rooms.put("Attic", attic);
        
        garage = new Room("Garage", "A room where you can store vehicles.", Room.ROOMTYPE.INDOOR);
        rooms.put("Garage", garage);
        
        garden = new Room("Garden", "A room where you can grow plants.", Room.ROOMTYPE.OUTDOOR);
        rooms.put("Garden", garden);
        
        driveway = new Room("Driveway", "A driveway that leads to the street.", Room.ROOMTYPE.OUTDOOR);
        rooms.put("Driveway", driveway);
        
        frontYard = new Room("Front_Yard", "The front yard of the daycare.", Room.ROOMTYPE.OUTDOOR);
        rooms.put("Front_Yard", frontYard);
        
        backYard = new Room("Back_Yard", "The back yard of the daycare.", Room.ROOMTYPE.OUTDOOR);
        rooms.put("Back_Yard", backYard);
        
        cogLabs = new Room("cogLabs", "A room with a sign that says \"Please do not take the box\"", Room.ROOMTYPE.LABORATORY);
        rooms.put("cogLabs", cogLabs);
        
        pool = new Room("Pool", "A pool where you can swim.", Room.ROOMTYPE.OUTDOOR);
        rooms.put("Pool", pool);
        
        patio = new Room("Patio", "A patio where you can relax.", Room.ROOMTYPE.OUTDOOR);
        rooms.put("Patio", patio);
        
        deck = new Room("Deck", "A deck where you can relax.", Room.ROOMTYPE.OUTDOOR);
        rooms.put("Deck", deck);
        
        porch = new Room("Porch", "A porch where you can relax.", Room.ROOMTYPE.OUTDOOR);
        rooms.put("Porch", porch);
        
        balcony = new Room("Balcony", "A balcony where you can relax.", Room.ROOMTYPE.OUTDOOR);
        rooms.put("Balcony", balcony);
        
        roof = new Room("Roof", "A roof where you can relax.", Room.ROOMTYPE.OUTDOOR);
        rooms.put("Roof", roof);
        
        cubbies = new Room("Cubbies", "A room where you can store your things.", Room.ROOMTYPE.GREEN);
        rooms.put("Cubbies", cubbies);
        
        dramaArea = new Room("Drama_Area", "A room where you can act out plays.", Room.ROOMTYPE.GREEN);
        rooms.put("Drama_Area", dramaArea);
        
        changingRoom = new Room("Changing_Room", "A room where you can change your clothes.", Room.ROOMTYPE.GREEN);
        rooms.put("Changing_Room", changingRoom);
        
        floorPlay = new Room("Floor_Play", "A room where you can play on the floor.", Room.ROOMTYPE.GREEN);
        rooms.put("Floor_Play", floorPlay);
        
        quietArea = new Room("Quiet_Area", "A room where you can relax.", Room.ROOMTYPE.GREEN);
        rooms.put("Quiet_Area", quietArea);
        
        homeWorkArea = new Room("Homework_Area", "A room where you can do your homework.", Room.ROOMTYPE.GREEN);
        rooms.put("Homework_Area", homeWorkArea);
        
        playHouse = new Room("Play_House", "A room where you can play house.", Room.ROOMTYPE.GREEN);
        rooms.put("Play_House", playHouse);
        
        treeHouse = new Room("Tree_House", "A room where you can play in a tree house.", Room.ROOMTYPE.GREEN);
        rooms.put("Tree_House", treeHouse);
        
        storyBookVillage = new Room("Storybook_Village", "A room where you can read books.",    Room.ROOMTYPE.GREEN);
        rooms.put("Storybook_Village", storyBookVillage);
        
        pillowPile = new Room("Pillow_Pile", "A room where you can relax on a pile of pillows.", Room.ROOMTYPE.GREEN);
        rooms.put("Pillow_Pile", pillowPile);
        
        snackArea = new Room("Snack_Area", "A room where you can eat snacks.", Room.ROOMTYPE.GREEN);
        rooms.put("Snack_Area", snackArea);
        
        greenHall = new Room("Green_Hall", "A hallway that connects the rooms.", Room.ROOMTYPE.GREEN);
        rooms.put("Green_Hall", greenHall);
        
        blueHall = new Room("Blue_Hall", "A hallway that connects the rooms.", Room.ROOMTYPE.GREEN);
        rooms.put("Blue_Hall", blueHall);
        
        redHall = new Room("Red_Hall", "A hallway that connects the rooms.", Room.ROOMTYPE.GREEN);
        rooms.put("Red_Hall", redHall);
        
        peddleToys = new Room("Peddle_Toys", "A room where you can play with peddle toys.", Room.ROOMTYPE.GREEN);
        rooms.put("Peddle_Toys", peddleToys);
        
        lemonaidStand = new Room("Lemonaid_Stand", "A room where you can sell lemonaid.", Room.ROOMTYPE.GREEN);
        rooms.put("Lemonaid_Stand", lemonaidStand);
        
        toolShed = new Room("Tool_Shed", "A shed where you can store tools.", Room.ROOMTYPE.GREEN);
        rooms.put("Tool_Shed", toolShed);
        
        TRSRoom = new Room("TRSRoom", "A room where you can relax.", Room.ROOMTYPE.GREEN);
        rooms.put("TRSRoom", TRSRoom);
        
        janitorialRoom = new Room("Janitorial_Room", "A room where you can clean.", Room.ROOMTYPE.GREEN);
        rooms.put("Janitorial_Room", janitorialRoom);
        
        foyer = new Room("Foyer", "The foyer of the daycare.", Room.ROOMTYPE.BLUE);
        rooms.put("Foyer", foyer);
        
        pantry = new Room("Pantry", "A room where you can store food.", Room.ROOMTYPE.KITCHEN);
        rooms.put("Pantry", pantry);
        
        recoveryRoom = new Room("Recovery_Room", "A room where you can recover.", Room.ROOMTYPE.GREEN);
        rooms.put("Recovery_Room", recoveryRoom);
        
        demoRoom = new Room("Demo_Room", "A room where you can play with toys.", Room.ROOMTYPE.BLUE);
        rooms.put("Demo_Room", demoRoom);
        
        demoRoom.addItem(snackShop);
        snackArea.addItem(foodTrayDispenser);
        demoRoom.addItem(toy);
        demoRoom.addItem(book);
        foyer.addItem(snackShop);
        recoveryRoom.addItem(trainingPants);
        recoveryRoom.addItem(diaper);
        recoveryRoom.addItem(backPack);
        recoveryRoom.addItem(underPants);
        recoveryRoom.addItem(uniformTop);
        recoveryRoom.addItem(uniformBottom);
        recoveryRoom.addItem(uniformHat);
        recoveryRoom.addItem(uniformShoes);
        recoveryRoom.addItem(uniBackPack);
        mainRoom.addItem(pickapaw);
        Player.setRoom(recoveryRoom);
        fuzzy.setRoom(recoveryRoom);
        NPC.followPlayer(fuzzy);
    }
    public void populateRooms() {
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
        //demoRoom.addNPC();
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
        changingRoom.addExit(foyer);

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
        foyer.addExit(changingRoom);

        //FrontYard
        frontYard.addExit(foyer);
        frontYard.addExit(lemonaidStand);
        frontYard.addExit(porch);

        //GreenHall
        greenHall.addExit(dorms);
        greenHall.addExit(foyer);
        greenHall.addExit(classRoom);

        //ClassRoom
        classRoom.addExit(greenHall);

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
        mainRoom.addExit(demoRoom);

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
    public void giveItems() {
        drWhite.addItem(box);
        foyer.addItem(box);
        foyer.addItem(trash);
        box.addItem(trash);
        box.addItem(toy);
        snackShop.addItem(thickDiapers);
        snackShop.addItem(toy);
        toy.setPrice(5);
        diaper.setPrice(5);
        diaper.setPockets(1);
        thickDiapers.setPrice(5);
        thickDiapers.setPockets(2);
        uniBackPack.setPockets(5);
        backPack.setPockets(10);
        box.setPrice(6);
        box.setContraband(true);
        pickapaw.addItem(pawFigure);
        pickapaw.addItem(limitedEditionPaw);
        limitedEditionPaw.setPrice(50);
        pawFigure.setPrice(10);
        Player.addItem(pawFigure);
        Player.addItem(diaper);
    }
    public void playGame() {
        playTutorial();
    }
    public void endGame() {
        playOutro();
    }
    public static Room getRoomByName(String name) {
        return rooms.get(name);
    }
    public static GUI getGui() {
        return gui;
    }
    public static void updateStatus() {
        getGui().getStatsLabel().setText("Player: " + Player.getName() + "    | |    Experience: " + Player.getExperience() + "    | |    Shiny Pennies: " + Player.getMoney() + "    | |    Resilience: " + Player.getResilience() + "    | |    Time: " + FatherTime.getClock().getTimeOfDay() + "    | |    Hunger/Thirst: " + Player.getHungerThirst() + "    | |    Alignment: " + Player.getAlignment());
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
    public static NPC getNPCByName(String person) {
        return npcs.get(person);
    }
    public static String getFileSection2() {
        return fileSection2;
    }

    public static void setFileSection2(String fileSection2) {
        fileSection2 = fileSection2;
    }

    public static String getFileSection3() {
        return fileSection3;
    }

    public static void setFileSection3(String fileSection3) {
        fileSection3 = fileSection3;
    }

    public static String getFileSection4() {
        return fileSection4;
    }

    public static void setFileSection4(String fileSection4) {
        fileSection4 = fileSection4;
    }
    public static PawFigure getFigureByName(String name) {
        for (Item item : items.values()) {
            if (item instanceof PawFigure figure) {
                if (figure.getName().equalsIgnoreCase(name)) {
                    getGui().display("This happened!", "Black");
                    return figure;
                }
            }
        }
        return null;

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
                getGui().display("Error reading file.", "Red");
            }
        } else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                getGui().display("Error creating file.", "Red");
            }
        }
        return null;
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
    public static void demo() {
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
    public void buildRoomTypes(){
        Room.roomType.put(kitchen,Room.ROOMTYPE.KITCHEN);
        Room.roomType.put(classRoom,Room.ROOMTYPE.BLUE);
        Room.roomType.put(mainRoom,Room.ROOMTYPE.GREEN);
        Room.roomType.put(dorms,Room.ROOMTYPE.GREEN);
        Room.roomType.put(bathroom,Room.ROOMTYPE.BATHROOM);
        Room.roomType.put(hallway,Room.ROOMTYPE.RED);
        Room.roomType.put(stairs,Room.ROOMTYPE.RED);
        Room.roomType.put(basement,Room.ROOMTYPE.RED);
        Room.roomType.put(attic,Room.ROOMTYPE.RED);
        Room.roomType.put(garage,Room.ROOMTYPE.RED);
        Room.roomType.put(garden,Room.ROOMTYPE.BLUE);
        Room.roomType.put(driveway,Room.ROOMTYPE.RED);
        Room.roomType.put(frontYard,Room.ROOMTYPE.BLUE);
        Room.roomType.put(backYard,Room.ROOMTYPE.BLUE);
        Room.roomType.put(cogLabs,Room.ROOMTYPE.BLUE);
        Room.roomType.put(pool,Room.ROOMTYPE.RED);
        Room.roomType.put(patio,Room.ROOMTYPE.BLUE);
        Room.roomType.put(deck,Room.ROOMTYPE.BLUE);
        Room.roomType.put(porch,Room.ROOMTYPE.BLUE);
        Room.roomType.put(balcony,Room.ROOMTYPE.BLUE);
        Room.roomType.put(roof,Room.ROOMTYPE.RED);
        Room.roomType.put(cubbies,Room.ROOMTYPE.GREEN);
        Room.roomType.put(dramaArea,Room.ROOMTYPE.GREEN);
        Room.roomType.put(changingRoom,Room.ROOMTYPE.GREEN);
        Room.roomType.put(floorPlay,Room.ROOMTYPE.GREEN);
        Room.roomType.put(quietArea,Room.ROOMTYPE.GREEN);
        Room.roomType.put(homeWorkArea,Room.ROOMTYPE.GREEN);
        Room.roomType.put(playHouse,Room.ROOMTYPE.BLUE);
        Room.roomType.put(treeHouse,Room.ROOMTYPE.BLUE);
        Room.roomType.put(storyBookVillage,Room.ROOMTYPE.GREEN);
        Room.roomType.put(pillowPile,Room.ROOMTYPE.GREEN);
        Room.roomType.put(snackArea,Room.ROOMTYPE.GREEN);
        Room.roomType.put(greenHall,Room.ROOMTYPE.GREEN);
        Room.roomType.put(blueHall,Room.ROOMTYPE.BLUE);
        Room.roomType.put(redHall,Room.ROOMTYPE.RED);
        Room.roomType.put(peddleToys,Room.ROOMTYPE.BLUE);
        Room.roomType.put(lemonaidStand,Room.ROOMTYPE.BLUE);
        Room.roomType.put(toolShed,Room.ROOMTYPE.RED);
        Room.roomType.put(TRSRoom,Room.ROOMTYPE.RED);
        Room.roomType.put(janitorialRoom,Room.ROOMTYPE.RED);
        Room.roomType.put(foyer,Room.ROOMTYPE.GREEN);
        Room.roomType.put(pantry,Room.ROOMTYPE.KITCHEN);
        Room.roomType.put(recoveryRoom,Room.ROOMTYPE.GREEN);
        Room.roomType.put(demoRoom,Room.ROOMTYPE.BLUE);
    }
    static void playerTimeOut(int i, String act, NPC npc) {
        boolean apology = false;
        getGui().lockButtons();
        getGui().clearTextPane();
        getGui().display("You were put in time out for " + i * 10 + " minutes.", "Black");
        while (!apology) {
            String[] acts = {"stealing", "pranking", "Vandalism", "Skipped", "Trespassing", "Sneaking", "climbed", "I don't know"};
            String response = (String) JOptionPane.showInputDialog(null,
                    "What did you do?",
                    "Choose", JOptionPane.QUESTION_MESSAGE,
                    null, acts, acts[0]);
            if (response == null) {
                getGui().display("You Will stay here until you give the correct answers.", "Red");
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
                        getGui().display("You Will stay here until you give the correct answers.", "Red");
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
                        getGui().display("You Will stay here until you give the correct answers.", "Red");
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
                        getGui().display("You Will stay here until you give the correct answers.", "Red");
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
                        getGui().display("You Will stay here until you give the correct answers.", "Red");
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
                        getGui().display("You Will stay here until you give the correct answers.", "Red");
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
                        getGui().display("You Will stay here until you give the correct answers.", "Red");
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
                        getGui().display("You Will stay here until you give the correct answers.", "Red");
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
                        getGui().display("You Will stay here until you give the correct answers.", "Red");
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

    private static ArrayList<Achievements> getAchievements() {
        return achievements;
    }
    public static Achievements getAchievementByName(String name) {
        for (Achievements achievement : achievements) {
            if (achievement.getName().equalsIgnoreCase(name)) {
                return achievement;
            }
        }
        return null;
    }
    private String[] toyBuffs = {"Social", "Motor", "Imagenation", "Learning", "Emotional"};
    private String[] stuffyBuffs = {"Calms me down", "Helps me play pretend", "Helps me make friends", "Keeps me focused", "I dress it"};
    private RoutineManager routineManager;

    public GameHandler(GUI gui1, Game game1) {
        game = game1;
        gui = gui1;
    }

    public Room movePlayer(Room room) {
        Player.setRoom(room);
        return Player.getRoom();
    }

    

    public void moveItem(Item item, Room room) {
        room.getInventory().add(item);
    }

    public Game getGame() {
        return game;
    }
    public static void updateAchievementsForRoomVisit(Room room) {
        // Assuming you have a list of achievements somewhere in the game, like a GameHandler or AchievementManager
        for (Achievements Achievement : getAchievements()) {
            // Check if the achievement involves visiting rooms
            if (Achievement.getRequiredPlaces().containsKey(room)) {
                Achievement.markRoomVisited(room); // Mark the room as visited for the achievement
            }
        }
    }
    public static void updateAchievementsForEquip(Equipment equipment) {
        // Assuming you have a list of achievements somewhere in the game, like a GameHandler or AchievementManager
        for (Achievements Achievement : getAchievements()) {
            // Check if the achievement involves equipping items
            if (Achievement.getRequiredEquipment().containsKey(equipment)) {
                Achievement.markEquipmentEquipped(equipment); // Mark the equipment as equipped for the achievement
            }
        }
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

    public void setCharacterBio() {
        explainCharacterBio();
    }

    public static void makeFoodTray() {
        Container foodTray1 = (Container) getItems().get("Food Tray");
                if (foodTray1 == null) {
                    foodTray1 = new Container("Food Tray", "A plastic food tray", "Container", false);
                    getItems().put("Food Tray", foodTray1); // Add it to game items
                }
    
                Random rand = new Random();
                int fruitIndex = rand.nextInt(EatingAndFood.fruitChoices.values().length);
                int vegetableIndex = rand.nextInt(EatingAndFood.vegetableChoices.values().length);
                int junkIndex = rand.nextInt(EatingAndFood.junkChoices.values().length);
                int proteinIndex = rand.nextInt(EatingAndFood.proteinChoices.values().length);
                int drinkIndex = rand.nextInt(EatingAndFood.drinkChoices.values().length);
                // Create food items
                Item drink = new EatingAndFood(EatingAndFood.drinkChoices.values()[drinkIndex].toString(), "A delicious " + EatingAndFood.drinkChoices.values()[drinkIndex].toString(), EatingAndFood.FoodType.DRINK, 5);
                Item fruit = new EatingAndFood(EatingAndFood.fruitChoices.values()[fruitIndex].toString(), "A delicious " + EatingAndFood.fruitChoices.values()[fruitIndex].toString(), EatingAndFood.FoodType.FRUIT, 5);
                Item veg = new EatingAndFood(EatingAndFood.vegetableChoices.values()[vegetableIndex].toString(), "A delicious " + EatingAndFood.vegetableChoices.values()[vegetableIndex].toString(), EatingAndFood.FoodType.VEGETABLE, 5);
                Item junk = new EatingAndFood(EatingAndFood.junkChoices.values()[junkIndex].toString(), "A delicious " + EatingAndFood.junkChoices.values()[junkIndex].toString(), EatingAndFood.FoodType.JUNK, 5);
                Item protein = new EatingAndFood(EatingAndFood.proteinChoices.values()[proteinIndex].toString(), "A delicious " + EatingAndFood.proteinChoices.values()[proteinIndex].toString(), EatingAndFood.FoodType.MEAL, 5);
                drink.getConditions().put(ItemCondition.DRINK, true);
                drink.setName(toSentenceCase(drink.getName()));

                fruit.getConditions().put(ItemCondition.FOOD, true);
                fruit.setName(toSentenceCase(fruit.getName()));
                
                veg.getConditions().put(ItemCondition.FOOD, true);
                veg.setName(toSentenceCase(veg.getName()));
                
                junk.getConditions().put(ItemCondition.FOOD, true);
                junk.setName(toSentenceCase(junk.getName()));
                
                protein.getConditions().put(ItemCondition.FOOD, true);
                protein.setName(toSentenceCase(protein.getName()));
                
                fruit.setSaturation(7);
                veg.setSaturation(10);
                junk.setSaturation(3);
                protein.setSaturation(15);
                drink.setSaturation(5);

                foodTray1.getInventory().clear(); // Clear the food tray
                foodTray1.addItem(fruit);
                foodTray1.addItem(veg);
                foodTray1.addItem(junk);
                foodTray1.addItem(protein);
                foodTray1.addItem(drink);

                items.put(fruit.getName(), fruit);
                items.put(veg.getName(), veg);
                items.put(junk.getName(), junk);
                items.put(protein.getName(), protein);
                items.put(drink.getName(), drink);

    
                // Display success message
                getGui().display("You filled the food tray with delicious items!", "Red");
                foodTrayDispenser.addItem(foodTray1);
                foodTray1.displayInventory();
                foodTrayDispenser.displayInventory();
    }

}
