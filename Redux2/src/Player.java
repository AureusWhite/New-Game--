import java.util.ArrayList;

public class Player{

    public static  String alignment = "Newbie";
    public static int age;
    public static String social;
    public static String motor;
    public static String imagenation;
    public static  String learning;
    public static String emotional;
    public static ArrayList<Item> Pinventory = new ArrayList<>();
    public static ArrayList<QuestItem> questItems = new ArrayList<>(); 
    public static ArrayList<Consumable> consumables = new ArrayList<>();
    public static ArrayList<Equipment> equipment = new ArrayList<>();
    private static  boolean abilitiesSet;
    private static boolean ageSet;
    private static boolean alignmentSet;
    private static int experience;
    private static int money;
    private static int resilience;
    static Room room;


    public static  boolean isAbilitiesSet() {
        return abilitiesSet;
    }
    public static  void setAlignment(String input) {
        GameHandler.getGui().display("Are you a Rebel or a Loyalist?","Black");
        alignmentSet = false;
        while(!isAlignmentSet()){
            GameHandler.getGui().waitForInput();
            input = GameHandler.getGui().getInput();
            alignment = input;
            if(alignment.equalsIgnoreCase("Rebel")){
                alignmentSet = true;
                break;
            }else if(alignment.equalsIgnoreCase("Loyalist")){
                alignment= input;
                alignmentSet = true;
            }else{
                GameHandler.getGui().display("Please enter Rebel or Loyalist.","Blue");
            }
        }
        GUI.getJTextField().setText("");
        GameHandler.getGui().display("You are a " + alignment,"Black");
    }

    private static  boolean isAlignmentSet() {
        return alignmentSet;
    }


public static void setRoom(Room room1) {
    room1.initializeRoomFiles();
    Player.room = room1;
}

    private static void setSocial(String social) {
        Player.social = social;
        
    }

    private static void setMotor(String motor) {
        Player.motor = motor;
    }

    private static void setLearning(String learning) {
        Player.learning = learning;
    }

    private static void setImagenation(String imagenation) {
        Player.imagenation = imagenation;
    }

    private static void setEmotional(String emotional) {
        Player.emotional = emotional;
    }

    private static boolean checkItem() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    static void displayInventory() {
        for(Item item : Pinventory){
            GameHandler.getGui().display(item.getName()+"in your pockets","Black");
        }
    }
    public Player(String name, String discription, Room room) {


    }
    public void giveItemToNPC(Item item, NPC npc) {
        npc.reciveItem(item);
        removeItem(item);
    }
    public static Room getRoom() {
        return Player.room;
    }

    public static void takeItem(Item item) {
        addItem(item);
        getRoom().removeItem(item);
    }
    public void dropItem(Item item) {
        getRoom().addItem(item);
        removeItem(item);
    }
    public static void setAge(String input) {
        GameHandler.getGui().display("Please enter your age.","Black");
        while(!input.matches("[2-9]+")){
            GameHandler.getGui().waitForInput();
            input = GameHandler.getGui().getInput();
        }
        GUI.getJTextField().setText("");
        if(ageSet){
            GameHandler.getGui().display("You have already set your age.","Black");
        } else {
            age = Integer.parseInt(input);
            ageSet = true;
        }
        GameHandler.getGui().display("You are " + age + " years old.","Black");
    }
    public static  void setAbilities(String social, String motor, String imagenation, String learning, String emotional) {
        setSocial(social);
        setMotor(motor);
        setImagenation(imagenation);
        setLearning(learning);
        setEmotional(emotional);
    
        abilitiesSet=true;
    }
    public static void setCharacterAbilities() {
        while(!isAbilitiesSet()){
        GameHandler.getGui().display("Please enter your social ability.", "Black");
        GameHandler.getGui().waitForInput();
        social = GameHandler.getGui().getInput();
        GUI.getJTextField().setText("");
        GameHandler.getGui().display("Please enter your motor ability.",   "Black");
        GameHandler.getGui().waitForInput();
        motor = GameHandler.getGui().getInput();
        GUI.getJTextField().setText("");
        GameHandler.getGui().display("Please enter your imagenation ability.", "Black");
        GameHandler.getGui().waitForInput();
        imagenation = GameHandler.getGui().getInput();
        GUI.getJTextField().setText("");
        GameHandler.getGui().display("Please enter your learning ability.", "Black");
        GameHandler.getGui().waitForInput();
        learning = GameHandler.getGui().getInput();
        GUI.getJTextField().setText("");
        GameHandler.getGui().display("Please enter your emotional ability.", "Black");
        GameHandler.getGui().waitForInput();
        emotional = GameHandler.getGui().getInput();
        GUI.getJTextField().setText("");
        setAbilities(social, motor, imagenation, learning, emotional);
            abilitiesSet = true;
    }
        GameHandler.getGui().display("Your abilities are: " + social + ", " + motor + ", " + imagenation + ", " + learning + ", " + emotional, "Black");
    }
    public  int getExperience() {
        return experience;
    }
    public  int getMoney() {
        return money;
    }
    public  int getResilience() {
        return resilience;
    }

    static void removeItem(Item item) {
        Pinventory.remove(item);
    }
    public static ArrayList<Item> getInventory() {
        return Pinventory;
    }
    static void addItem(Item item) {
        Pinventory.add(item);
    }
    public static void reciveItem(NPC npc, Item item) {
        if(checkItem()){
            addItem(item);
            npc.removeItem(item);
        } else {
            GameHandler.getGui().display("You can't carry anymore items.","Black");
        }
    }
    public static String[] getItemChoises() {
        String[] items = new String[Player.getInventory().size()];
        for (int i = 0; i < Player.getInventory().size(); i++) {
            items[i] = Player.getInventory().get(i).getName();
        }
        return items;
    }
}