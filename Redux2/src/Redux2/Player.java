package Redux2;

import java.util.ArrayList;
import java.util.HashMap;

public class Player {

    public static String alignment = "Newbie";
    public static int age;
    public static String social;
    public static String motor;
    public static String imagenation;
    public static String learning;
    public static String emotional;
    public static ArrayList<Item> Pinventory = new ArrayList<>();
    public static ArrayList<Consumable> consumables = new ArrayList<>();
    public static HashMap<String, Equipment> equipment = new HashMap<>();
    public static ArrayList<Quest> quests = new ArrayList<>();
    private static boolean abilitiesSet;
    private static boolean ageSet;
    private static boolean alignmentSet;
    private static int experience;
    private static int money;
    private static int resilience;
    static Room room;
    private static String name;
    private static int thirst = 100;
    private static int hunger = 100;
    private static int energy;
    private static String status;

    public static void setEnergy(int energy) {
        Player.energy = energy;
    }

    public static void useEnergy(int energy) {
        Player.energy -= energy;
    }

    public static boolean isAbilitiesSet() {
        return abilitiesSet;
    }

    public static void setAlignment(String input) {
        GameHandler.getGui().display("Are you a Rebel or a Loyalist?", "Black");
        alignmentSet = false;
        while (!isAlignmentSet()) {
            GameHandler.getGui().waitForInput();
            input = GameHandler.getGui().getInput();
            alignment = input;
            if (alignment.equalsIgnoreCase("Rebel")) {
                alignmentSet = true;
                break;
            } else if (alignment.equalsIgnoreCase("Loyalist")) {
                alignment = input;
                alignmentSet = true;
            } else {
                GameHandler.getGui().display("Please enter Rebel or Loyalist.", "Blue");
            }
        }
        GUI.getJTextField().setText("");
        GameHandler.getGui().display("You are a " + alignment, "Black");
    }

    public static void setRoom(Room room1) {
        room = room1;
        room.initializeRoomFiles();
    }

    public static Room getRoom() {
        return Player.room;
    }

    public static void takeItem(Item item) {
        addItem(item);
        getRoom().removeItem(item);
    }

    public static void setAge(String input) {
        GameHandler.getGui().display("Please enter your age.", "Black");
        while (!input.matches("[2-9]+")) {
            GameHandler.getGui().waitForInput();
            input = GameHandler.getGui().getInput();
        }
        GUI.getJTextField().setText("");
        if (ageSet) {
            GameHandler.getGui().display("You have already set your age.", "Black");
        } else {
            age = Integer.parseInt(input);
            ageSet = true;
        }
        GameHandler.getGui().display("You are " + age + " years old.", "Black");
    }

    public static void equip(Equipment equipment1, String slot) {
        if (!equipment.containsKey(slot)) {
            equipment.put(slot, equipment1);
        } else {
            equipment.remove(slot);
            equipment.put(slot, equipment1);
        }

    }

    public static void setAbilities(String social, String motor, String imagenation, String learning, String emotional) {
        setSocial(social);
        setMotor(motor);
        setImagenation(imagenation);
        setLearning(learning);
        setEmotional(emotional);
    }

    public static void setCharacterAbilities() {
        while (!isAbilitiesSet()) {
            GameHandler.getGui().display("Please enter your social ability.", "Black");
            GameHandler.getGui().waitForInput();
            social = GameHandler.getGui().getInput();
            GUI.getJTextField().setText("");
            GameHandler.getGui().display("Please enter your motor ability.", "Black");
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

    public static int getExperience() {
        return experience;
    }

    public static void setExperience(int experience1) {
        experience = experience1;
    }

    public static void setMoney(int money1) {
        money = money1;
    }

    public static void setResilience(int resilience1) {
        resilience = resilience1;
    }

    public static void setInventory(ArrayList<Item> inventory) {
        Pinventory = inventory;
    }

    public static void setConsumables(ArrayList<Consumable> consumables1) {
        consumables = consumables1;
    }

    public static void setEquipment(HashMap<String, Equipment> equipment1) {
        equipment = equipment1;
    }
    public static void setHunger(int hunger1) {
        hunger = hunger1;
    }

    public static void setThirst(int thirst1) {
        thirst = thirst1;
    }

    public static void setAge(int age1) {
        age = age1;
    }

    public static void setSocial(String social1) {
        social = social1;
    }

    public static void setMotor(String motor1) {
        motor = motor1;
    }

    public static void setImagenation(String imagenation1) {
        imagenation = imagenation1;
    }

    public static void setLearning(String learning1) {
        learning = learning1;
    }
    public static void setEmotional(String emotional1) {
        emotional = emotional1;
    }

    public static void setAbilitiesSet(boolean abilitiesSet1) {
        abilitiesSet = abilitiesSet1;
    }

    public static void setAgeSet(boolean ageSet1) {
        ageSet = ageSet1;
    }

    public static void setAlignmentSet(boolean alignmentSet1) {
        alignmentSet = alignmentSet1;
    }

    public static int getMoney() {
        return money;
    }

    public static int getResilience() {
        return resilience;
    }

    public static ArrayList<Item> getInventory() {
        return Pinventory;
    }

    public static void reciveItem(NPC npc, Item item) {
        if (checkItem()) {
            addItem(item);
            npc.removeItem(item);
        } else {
            GameHandler.getGui().display("You can't carry anymore items.", "Black");
        }
    }

    public static String[] getItemChoises() {
        String[] items = new String[Player.getInventory().size()];
        for (int i = 0; i < Player.getInventory().size(); i++) {
            items[i] = Player.getInventory().get(i).getName();
        }
        return items;
    }

    public static String getAlignment() {
        return alignment;
    }

    public static String getHungerThirst() { //returns the hunger and thirst status of the player and sets the status to "Hungery" if the player is hungry, "Thirsty" if the player is thirsty, and "Hungery and Thirsty" if the player is both hungry and thirsty
        String HT = "Content";
        if (getHunger() < 25) {
            HT = "Hungery";

        }
        if (getThirst() < 25) {
            HT = "Thirsty";
        }
        if (getHunger() < 25 && getThirst() < 25) {
            HT = "Hungery and Thirsty";
        }
        return HT;
    }

    public static HashMap<String, Equipment> getEquipment() {
        return equipment;
    }

    public static void nap() {
        if (Player.getEnergy() < 100) {
            int minTime = 30;
            int maxTime = 90;
            int time = minTime + (int) (Math.random() * (maxTime - minTime));
            GameHandler.getClock().moveTime(time);
            GameHandler.getGui().display("You took a nap for " + GameHandler.getClock().formatByHourMinute(time), "Black");
        } else {
            GameHandler.getGui().display("You are not tired.", "Black");
        }
    }

    public static void potty() {
        GameHandler.getGui().display("You went potty.", "Black");
    }

    public static void tantrum() {
        GameHandler.getGui().display("You threw a tantrum.", "Black");
    }

    public static void eatDrink() {
        GameHandler.getGui().display("You ate and drank.", "Black");
    }

    public static void reflect() {
        GameHandler.getGui().display("You reflected on your day.", "Black");
    }

    static void displayInventory() {
        for (Item item : Pinventory) {
            GameHandler.getGui().display(item.getName() + "in your pockets", "Black");
        }
    }

    static void setName(String input) {
        GameHandler.getGui().display("Please enter your name.", "Black");
        GameHandler.getGui().waitForInput();
        input = GameHandler.getGui().getInput();
        GUI.getJTextField().setText("");
        GameHandler.getGui().display("Hello," + input, "Black");
        name = input;
    }

    static String getName() {
        return Player.name;
    }

    static int getAge() {
        return Player.age;
    }

    static void removeItem(Item item) {
        Pinventory.remove(item);
    }

    static void addItem(Item item) {
        Pinventory.add(item);
    }

    private static int getEnergy() {
        return energy;
    }

    private static boolean isAlignmentSet() {
        return alignmentSet;
    }

    private static boolean checkItem() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private static int getHunger() {
        return hunger;
    }

    private static int getThirst() {
        return thirst;
    }

    static void getPunished(String act, int i) {
        timeOut(i);
    }

    private static void setStatus(String status1) {
        status = status1;
    }

    private static void timeOut(int i) {
        GameHandler.getClock().moveTime(i * 10);
        GameHandler.getGui().display("You were put in time out for " + i * 10 + " minutes.", "Black");
    }

    public static String getStatus() {
        return status;
    }
    static void setLeader(boolean b) {
        leader=b;
    }
    private static boolean leader=true;

    static void addXP(int reward) {
        experience += reward;
    }

    static void addQuest(Quest quest1) {
        Player.quests.add(quest1);
    }

    static void displayQuests() {
        for (Quest quest : quests) {
            GameHandler.getGui().display(quest.getName(), "Black");
        }
    }

    static ArrayList<Quest> getQuests() {
        return quests;
    }

    static void removeQuest(Quest aThis) {
        quests.remove(aThis);
    }

    public Player(String name, String discription, Room room) {

    }

    public void calculateAttributes() {
        int pAge = getAge();
        switch (pAge) {
            case 2, 3, 4 -> {
                setSocial("Expressive");
                setMotor("sneak");
                setImagenation("Pretend Play");
                setLearning("Exploration");
                setEmotional("Unregulated");
            }
            case 5, 6, 7 -> {
                setSocial("Mixed Cooperative");
                setMotor("Walk, Run, Jump");
                setImagenation("Pretend Play, Drawing, Building");
                setLearning("Passive Learning");
                setEmotional("Semiregulated");
            }
            case 8, 9, 10, 11, 12 -> {
                setSocial("Cooperative");
                setMotor("Walk, Run, Jump, Skip, Hop");
                setImagenation("Pretend Play, Storytelling, Drawing, Building");
                setLearning("Active Learning");
                setEmotional("Regulated");
            }
        }
    }

    public static void giveItemToNPC(Item item, NPC npc) {
        if (hasItem(item)) {
            npc.reciveItem(item);
            removeItem(item);
        } else {
            GameHandler.getGui().display("You don't have that item.", "Black");
        }
    }

    static boolean hasItem(Item item2) {
        for (Item item : Pinventory) {
            if (item.getName().equals(item2.getName())) {
                return true;
            }
        }
        return false;
    }

    public void dropItem(Item item) {
        getRoom().addItem(item);
        removeItem(item);
    }

    public static void sneak() {
        if(motor == null){
            GameHandler.getGui().display("You can't sneak.", "Black");
            return;
        }
        if (motor.contains("sneak")) {
            GameHandler.getGui().display("You attempt to sneak unseen", "Black");
            if (getRoom().getNPCs().size() == 1) {
                GameHandler.getGui().display("No one here to see you.", "Black");
                Player.setStatus("Hidden");
                return;
            }
            int outcome = (int) (Math.random() * 100);
            if (outcome < 50) {
                GameHandler.getGui().display("You were seen.", "Black");
                Player.getRoom().getFirstNPC().setSuspicion();
                Player.setStatus("Seen");
                Player.getRoom().getFirstNPC().caughtPlayer("sneaking");
            } else {
                GameHandler.getGui().display("You were not seen.", "Black");
                Player.setStatus("Hidden");
            }

        } else {
            GameHandler.getGui().display("You can't sneak.", "Black");
        }
    }

    public static void doCraft(String craft) {
        getRoom().getItemByName(craft).craft();
    }

    public static void playGame(String game) {
        getRoom().getItemByName(game).play();
    }

    public static void learn(String choice) {
        getRoom().getItemByName(choice).study();
    }

    public static void doPuzzle(String puzzle) {
        getRoom().getItemByName(puzzle).solve();
    }

    public static Item getItemByType(String string) {
        for (Item item : Pinventory) {
            if (item.getType().equals(string)) {
                return item;
            }
        }
        return null;
    }
    public static boolean isLeader() {
        return leader;
    }

}
