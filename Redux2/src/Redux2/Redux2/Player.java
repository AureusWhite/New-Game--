package Redux2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.swing.JOptionPane;

public class Player {

    public static String alignment = "Newbie";
    public static int age;
    public static ArrayList<Item> backpack = new ArrayList<>();
    public static ArrayList<Item> pockets = new ArrayList<>();
    public static ArrayList<Item> hands = new ArrayList<>();
    public static ArrayList<Consumable> consumables = new ArrayList<>();
    public static HashMap<String, Equipment> equipment = new HashMap<>();
    public static ArrayList<Quest> quests = new ArrayList<>();
    public static HashMap<String, Integer> stats = new HashMap<>();
    public static HashMap<String, Boolean> perks = new HashMap<>();
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
    private static int pocketSize = 0;
    private static int backpackSize = 0;
    private static int energy;
    static String[] pronouns;
    private static String[] favorites;
    private static Map<Skill, Integer> skillLevels = new HashMap<>(); // Maps skills to levels
    static Map<Skill, Map<Ability, Effect>> abilities = new HashMap<>(); // Maps skills to abilities and effects
    private static ArrayList<Card> pawDeck = new ArrayList<>();
    private static ArrayList<Card> hand = new ArrayList<>();
    
    private static boolean leader = true;
    private static boolean playerIsHidden;
    private static boolean pottyTrained = false;
    private static int blatter;
    private static int maturity = 0;

    public static void setEnergy(int energy) {
        Player.energy = energy;
    }
    public static void setPawDeck(ArrayList<Card> pawDeck1) {
        pawDeck = pawDeck1;
    }
    public static void setHand(){
        for(int i = 0; i < 5; i++){
            hand.add(pawDeck.get(i));
        }
    }

    public static void initializeSkills() {
        Map<Ability, Effect> socialAbilities = new HashMap<>();
        Map<Ability, Effect> motorAbilities = new HashMap<>();
        Map<Ability, Effect> imaginationAbilities = new HashMap<>();
        Map<Ability, Effect> learningAbilities = new HashMap<>();
        Map<Ability, Effect> emotionalAbilities = new HashMap<>();
        for (Skill skill : Skill.values()) {
            skillLevels.put(skill, 1);
        }

        socialAbilities.put(Ability.CRY, new Effect("Cries", (argument) -> {
            for (NPC npc : room.getNPCs()) {
                if (npc.getName().equals(argument)) {
                    npc.persuade(Ability.CRY);
                }

            }
        }));
        socialAbilities.put(Ability.POINT, new Effect("Points", (argument) -> {
            List<Item> items = getRoom().getInventory();
            Iterator<Item> iterator = items.iterator();
            while (iterator.hasNext()) {
                Item item = iterator.next();
                if (item.getName().equals(argument)) {
                    if (!item.isContraband() && item.isTakable()) {
                        NPC npc = getRoom().getFirstNPC();
                        GameHandler.getGui().display("You pointed at the " + item.getName(), "Black");
                        GameHandler.getGui().display("You want the " + item.getName(), "Black");
                        npc.askForItem(item);

                    } else {
                        GameHandler.getGui().display("You can't take that.", "Black");
                    }
                }
            }
        }));
        socialAbilities.put(Ability.NAME, new Effect("Names", (argument) -> {
            if (GameHandler.getNPCByName(argument) != null) {
                NPC npc1 = GameHandler.getNPCByName(argument);
                GameHandler.getGui().display("You named the " + npc1.getName(), "Black");
                NPC.followPlayer(npc1);
            } else {
                if (GameHandler.getItemByName(argument) != null) {
                    Item item = GameHandler.getItemByName(argument);
                    GameHandler.getGui().display("You named the " + item.getName(), "Black");
                    NPC npc1 = getRoom().getFirstNPC();
                    GameHandler.getGui().display(Player.getRoom().getFirstNPC() + ":" + "You want the " + item.getName() + "?", "Black");
                    String answer = JOptionPane.showInputDialog("Yes or No");
                    if (answer.equalsIgnoreCase("Yes")) {
                        if (Player.getRoom().hasItem(item)) {
                            GameHandler.getGui().display(npc1.getName() + ": You may have the " + item.getName(), "Black");
                        }

                    }

                } else {
                    if (GameHandler.getRoomByName(argument) != null) {
                        Room room1 = GameHandler.getRoomByName(argument);
                        GameHandler.getGui().display("You named the " + room1.getName(), "Black");
                        room1.setName(argument);
                    } else {
                        GameHandler.getGui().display("You can't name that.", "Black");
                    }
                }
            }
        }));
        socialAbilities.put(Ability.ASK, new Effect("Asks", (argument) -> {
            System.out.println("Asking...");
        }));
        socialAbilities.put(Ability.NEGOTIATE, new Effect("Negotiates", (argument) -> {
            System.out.println("Negotiating...");
        }));
        socialAbilities.put(Ability.MEDIATE, new Effect("Mediates", (argument) -> {
            System.out.println("Mediating...");
        }));
        abilities.put(Skill.SOCIAL, socialAbilities);
        motorAbilities.put(Ability.CRAWL, new Effect("Crawls", (argument) -> {
            System.out.println("Crawling...");
        }));
        motorAbilities.put(Ability.WALK, new Effect("Walks", (argument) -> {
            System.out.println("Walking...");
        }));
        motorAbilities.put(Ability.RUN, new Effect("Runs", (argument) -> {
            System.out.println("Running...");
        }));
        motorAbilities.put(Ability.CLIMB, new Effect("Climbs", (argument) -> {
            System.out.println("Climbing...");
        }));
        motorAbilities.put(Ability.SNEAK, new Effect("Sneaks", (argument) -> {
            System.out.println("Sneaking...");
        }));
        motorAbilities.put(Ability.KICK, new Effect("Kicks", (argument) -> {
            System.out.println("Kicking...");
        }));
        motorAbilities.put(Ability.SKIP, new Effect("Skips", (argument) -> {
            System.out.println("Skipping...");
        }));
        abilities.put(Skill.MOTOR, motorAbilities);
        imaginationAbilities.put(Ability.IMITATE, new Effect("Imitates", (argument) -> {
            System.out.println("Imitating...");
        }));
        imaginationAbilities.put(Ability.DOLLS, new Effect("Plays with dolls", (argument) -> {
            System.out.println("Playing with dolls...");
        }));
        imaginationAbilities.put(Ability.PRODUCE, new Effect("Produces", (argument) -> {
            System.out.println("Producing...");
        }));
        imaginationAbilities.put(Ability.DIRECT, new Effect("Directs", (argument) -> {
            System.out.println("Directing...");
        }));
        imaginationAbilities.put(Ability.PLAY_ALONG, new Effect("Plays along", (argument) -> {
            System.out.println("Playing along...");
        }));
        abilities.put(Skill.IMAGINATION, imaginationAbilities);
        learningAbilities.put(Ability.READ, new Effect("Reads", (argument) -> {
            System.out.println("Reading...");
        }));
        learningAbilities.put(Ability.WRITE, new Effect("Writes", (argument) -> {
            System.out.println("Writing...");
        }));
        learningAbilities.put(Ability.BASICS, new Effect("Learns the basics", (argument) -> {
            System.out.println("Learning the basics...");
        }));
        learningAbilities.put(Ability.INTERMEDIATE, new Effect("Learns intermediate concepts", (argument) -> {
            System.out.println("Learning intermediate concepts...");
        }));
        learningAbilities.put(Ability.ADVANCED, new Effect("Learns advanced concepts", (argument) -> {
            System.out.println("Learning advanced concepts...");
        }));
        learningAbilities.put(Ability.REMEDIATED, new Effect("Revisits and relearns", (argument) -> {
            System.out.println("Revisiting and relearning...");
        }));
        abilities.put(Skill.LEARNING, learningAbilities);
        emotionalAbilities.put(Ability.EXPRESS, new Effect("Expresses emotions", (argument) -> {
            System.out.println("Expressing emotions...");
        }));
        emotionalAbilities.put(Ability.INTERPRET, new Effect("Interprets emotions", (argument) -> {
            System.out.println("Interpreting emotions...");
        }));
        emotionalAbilities.put(Ability.SELF_SOOTHE, new Effect("Soothes themselves", (argument) -> {
            System.out.println("Soothing themselves...");
        }));
        emotionalAbilities.put(Ability.SOOTHE_OTHERS, new Effect("Soothes others", (argument) -> {
            System.out.println("Soothing others...");
        }));
        emotionalAbilities.put(Ability.RESILIENCE, new Effect("Builds resilience", (argument) -> {
            System.out.println("Building resilience...");
        }));
        emotionalAbilities.put(Ability.TEMPERANCE, new Effect("Practices temperance", (argument) -> {
            System.out.println("Practicing temperance...");
        }));
        abilities.put(Skill.EMOTIONAL, emotionalAbilities);

    }

    public static void performAction(Skill skill, Ability ability, String argument) {
        if (canPerform(skill, ability)) {
            // Retrieve and apply the effect associated with the ability
            Effect effect = abilities.get(skill).get(ability);
            if (effect != null) {
                effect.applyEffect(argument);
            } else {
                System.out.println("No effect available for this ability.");
            }
        } else {
            System.out.println("Skill level too low to perform this action.");
        }
    }

    public static int getSkillLevel(Skill skill) {
        return skillLevels.get(skill);
    }

    public static void levelUpSkill(Skill skill) {
        skillLevels.put(skill, skillLevels.get(skill) + 1);
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
        if (room == GameHandler.getRoomByName("Demo_Room")) {
            GameHandler.demo();
        }
    }

    public static Room getRoom() {
        return Player.room;
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
        setUpStats();
        setStats(age);

    }

    public static void equip(Equipment equipment1, String slot) {
        // Check if the slot already has an item equipped
        if (equipment.containsKey(slot)) {
            Equipment currentEquipment = equipment.get(slot);
            currentEquipment.setEquipped(false);
            Player.equipment.remove(currentEquipment.getSlot());
            dropItem(equipment1);
        } else {
            // Equip the item
            equipment.put(slot, equipment1);
            equipment1.setEquipped(true);
            calculatePockets();
        }
    }

    public static void unequip(Equipment equipment1) {
        equipment1.setEquipped(false);
        equipment.remove(equipment1.getSlot());
        dropItem(equipment1);
        calculatePockets();
    }

    public static void calculatePockets() {
        pocketSize = 0;
        backpackSize = 0;
        for (Equipment equip : equipment.values()) {
            if (equip.getSlot().equalsIgnoreCase("back")) {
                backpackSize += equip.getPockets();
            } else {
                pocketSize += equip.getPockets();
            }
        }
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

    public static String[] getItemChoises() {
        if (pockets.isEmpty() && backpack.isEmpty() && hands.isEmpty()) {
            return null;
        }

        List<String> itemsList = new ArrayList<>();

        if (!hands.isEmpty()) {
            itemsList.add("-Hands-");
            for (Item item : hands) {
                if (item != null && item.getName() != null && !item.getName().equals("")) {
                    itemsList.add(item.getName());
                }
            }
        }

        if (!pockets.isEmpty()) {
            itemsList.add("-Pockets-");
            for (Item item : pockets) {
                if (item != null && item.getName() != null && !item.getName().equals("")) {
                    itemsList.add(item.getName());
                }
            }
        }

        if (!backpack.isEmpty()) {
            itemsList.add("-Backpack-");
            for (Item item : backpack) {
                if (item != null && item.getName() != null && !item.getName().equals("")) {
                    itemsList.add(item.getName());
                }
            }
        }

        return itemsList.toArray(String[]::new);
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
        if (getRoom().getType().equals("Bathroom")) {
            if (Player.getPottyTrained()) {
                GameHandler.getGui().display("You used the potty.", "Black");
                setBlatter(0);
                addMaturity(1);
                addXP(getMaturity());
            } else {
                GameHandler.getGui().display("You are not potty trained.", "Black");
            }
        } else {
            setBlatter(101);
            checkBlatter();
        }
    }

    private static void setBlatter(int i) {
        blatter = i;
    }

    public static void checkBlatter() {
        if (blatter > 100) {
            accident();
            setBlatter(0);
        }
        if (pottyTrained) {
            switch (blatter) {
                case 100 ->
                    GameHandler.getGui().display("You need to use the potty now.", "Black");
                case 75 ->
                    GameHandler.getGui().display("You need to potty soon.", "Black");
                case 50 ->
                    GameHandler.getGui().display("You should use the potty.", "Black");
                case 25 ->
                    GameHandler.getGui().display("You might need to potty.", "Black");
                case 0 ->
                    GameHandler.getGui().display("You do not need to potty.", "Black");
            }
        } else {

        }
    }

    public static void tantrum() {
        if (resilience < 10) {
            GameHandler.getGui().display("You had a tantrum.", "Black");
            setResilience(resilience += 10);
        } else {
            GameHandler.getGui().display("You are too resilient for a tantrum.", "Black");
        }
    }

    public static void eatDrink() {
        for (Consumable consumable : consumables) {
            if (consumable.getType().equals("Food")) {
                consumable.use();
            }
            if (consumable.getType().equals("Drink")) {
                consumable.use();
            }
        }
    }

    public static void reflect() {
        if (getResilience() < 10) {
            GameHandler.getGui().display("You reflected on your actions.", "Black");
            setResilience(resilience += 10);
        } else {
            GameHandler.getGui().display("You are too resilient for reflection.", "Black");
        }
    }

    public static void addXP(int reward) {
        experience += reward;
    }

    public static void setUpPerks() {
        perks.put("Social", false);
        perks.put("Motor", false);
        perks.put("Imagenation", false);
        perks.put("Learning", false);
        perks.put("Emotional", false);
    }

    public static boolean playerHasPerk(String perk) {
        return perks.get(perk);
    }

    public static void addPerk(String perk) {
        perks.put(perk, false);
    }

    public static void removePerk(String perk) {
        perks.remove(perk);
    }

    public static void setPerk(String perk) {
        perks.put(perk, true);
    }

    public static void removeXP(int reward) {
        experience -= reward;
    }

    public static void addMoney(int reward) {
        money += reward;
    }

    public static void removeMoney(int reward) {
        money -= reward;
    }

    public static void addResilience(int reward) {
        resilience += reward;
    }

    public static void removeResilience(int reward) {
        resilience -= reward;
    }

    public static void addHunger(int reward) {
        hunger += reward;
    }

    public static void removeHunger(int reward) {
        hunger -= reward;
    }

    public static void addThirst(int reward) {
        thirst += reward;
    }

    public static void removeThirst(int reward) {
        thirst -= reward;
    }

    public static void addEnergy(int reward) {
        energy += reward;
    }

    public static void removeEnergy(int reward) {
        energy -= reward;
    }

    public static void addSocial(int reward) {
        stats.put("Social", stats.get("Social") + reward);
    }

    public static void removeSocial(int reward) {
        stats.put("Social", stats.get("Social") - reward);
    }

    public static void addMotor(int reward) {
        stats.put("Motor", stats.get("Motor") + reward);
    }

    public static void removeMotor(int reward) {
        stats.put("Motor", stats.get("Motor") - reward);
    }

    public static void addImagenation(int reward) {
        stats.put("Imagenation", stats.get("Imagenation") + reward);
    }

    public static void removeImagenation(int reward) {
        stats.put("Imagenation", stats.get("Imagenation") - reward);
    }

    public static void addLearning(int reward) {
        stats.put("Learning", stats.get("Learning") + reward);
    }

    public static void removeLearning(int reward) {
        stats.put("Learning", stats.get("Learning") - reward);
    }

    public static void addEmotional(int reward) {
        stats.put("Emotional", stats.get("Emotional") + reward);
    }

    public static void removeEmotional(int reward) {
        stats.put("Emotional", stats.get("Emotional") - reward);
    }

    public static void addPronouns(String[] pronouns1) {
        pronouns = pronouns1;
    }

    public static String[] getPronouns() {
        return pronouns;
    }

    public static void setFavorites(String[] favorites1) {
        favorites = favorites1;
    }

    public static String[] getFavorites() {
        return favorites;
    }

    public static void sneak() {
        if (getSkillLevel(Skill.MOTOR) < 4) {
            GameHandler.getGui().display("You can't sneak.", "Black");
            levelUpSkill(Skill.MOTOR);
            return;
        }
        if (getSkillLevel(Skill.MOTOR) >= 4) {
            {
                if (playerIsHidden) {
                    GameHandler.getGui().display("You are already hidden.", "Black");
                    playerIsHidden = false;
                    return;
                }
                if (!playerIsHidden) {
                    for (NPC npc : getRoom().getNPCs()) {
                        if (npc.getSuspicion() >= 1) {
                            GameHandler.getGui().display(npc.getName() + ": I can see you. ", "Black");
                            return;
                        }
                    }
                }
                GameHandler.getGui().display("You attempt to sneak unseen", "Black");
                if (getRoom().getNPCs().isEmpty()) {
                    GameHandler.getGui().display("No one here to see you.", "Black");
                    setHidden(true);
                    return;
                }
                int outcome = (int) (Math.random() * 100);
                if (outcome > getSkillLevel(Skill.MOTOR) - 3) {
                    GameHandler.getGui().display("You were seen.", "Black");
                    Player.getRoom().getFirstNPC().setSuspicion(1, "sneaking");
                    Player.setHidden(false);
                    Player.getRoom().getFirstNPC().caughtPlayer("sneaking");
                } else {
                    GameHandler.getGui().display("You were not seen.", "Black");
                    setHidden(true);
                }
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

    public static boolean isLeader() {
        return leader;
    }

    public static void setFavorites(String color, String food, String toy, String game, String book, String subject, String activity) {
        favorites = new String[]{color, food, toy, game, book, subject, activity};

    }

    public static ArrayList<Consumable> getConsumables() {
        return consumables;
    }

    public static void setQuests(ArrayList<Quest> quests) {
        Player.quests = quests;
    }

    public static HashMap<String, Integer> getStats() {
        return stats;
    }

    public static void setStats(HashMap<String, Integer> stats) {
        Player.stats = stats;
    }

    public static HashMap<String, Boolean> getPerks() {
        return perks;
    }

    public static void setPerks(HashMap<String, Boolean> perks) {
        Player.perks = perks;
    }

    public static boolean isAgeSet() {
        return ageSet;
    }

    public static void setPronouns(String[] pronouns) {
        Player.pronouns = pronouns;
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
        if (item == null) {
            GameHandler.getGui().display("Item does not exsist?", "Black");
        }
        locateItem(item).remove(item);
    }

    static void addItem(Item item) {
        String[] options = {"Pockets", "Backpack", "Hands", "Equip"};
        int choice = JOptionPane.showOptionDialog(null, "Where would you like to put the " + item.getName() + "?", "Choose a location", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        switch (choice) {
            case 0 -> {
                if (pockets.size() < pocketSize) {
                    GameHandler.getGui().display("You put the " + item.getName() + " in your pockets", "Black");
                    pockets.add(item);
                } else {
                    GameHandler.getGui().display("Your pockets are full or you don't have any", "Black");
                    getRoom().addItem(item);
                }
                break;
            }
            case 1 -> {
                if (backpack.size() < backpackSize) {
                    GameHandler.getGui().display("You put the " + item.getName() + "in your backpack", "Black");
                    backpack.add(item);
                    break;
                } else {
                    GameHandler.getGui().display("Your backpack is full or you don't have one.", "Black");
                    getRoom().addItem(item);
                }
                break;
            }
            case 2 -> {
                if (hands.size() < 2) {
                    GameHandler.getGui().display("You hold the " + item.getName() + " in your hand", "Black");
                    hands.add(item);
                    break;
                } else {
                    GameHandler.getGui().display("You can't hold that.", "Black");
                    getRoom().addItem(item);
                }
                break;
            }
            case 3 -> {
                if (item instanceof Equipment equipment1) {
                    equip(equipment1, equipment1.getSlot());
                    break;
                } else {
                    GameHandler.getGui().display("You can't equip that.", "Black");
                    getRoom().addItem(item);
                }
                break;
            }
        }
    }

    static void setLeader(boolean b) {
        leader = b;
    }

    static void addQuest(Quest quest1) {
        Player.quests.add(quest1);
    }

    public static void displayQuests() {
        if (quests.isEmpty()) {
            GameHandler.getGui().display("You have no quests.", "Black");
        }
        for (Quest quest : quests) {
            GameHandler.getGui().display(quest.getName(), "Black");
        }
    }

    public static ArrayList<Quest> getQuests() {
        return quests;
    }

    public static void removeQuest(Quest aThis) {
        quests.remove(aThis);
    }

    static void setPronouns() {
        GameHandler.getGui().display("Please enter your subjective pronoun choose any you like (he/she/they/other)", "Black");
        GameHandler.getGui().waitForInput();
        String subjective = GameHandler.getGui().getInput();
        GUI.getJTextField().setText("");
        GameHandler.getGui().display("Please enter your objective pronoun choose any you like (him/her/them/other)", "Black");
        GameHandler.getGui().waitForInput();
        String objective = GameHandler.getGui().getInput();
        GUI.getJTextField().setText("");
        GameHandler.getGui().display("Please enter your possessive pronoun choose any you like (his/her/their/other)", "Black");
        GameHandler.getGui().waitForInput();
        String possessive = GameHandler.getGui().getInput();
        GUI.getJTextField().setText("");
        GameHandler.getGui().display("Please enter your prefered reference phrase if any (Young Lady, Young Man, Young one, Youngin, Little one. etc)", "Black");
        GameHandler.getGui().waitForInput();
        String reference = GameHandler.getGui().getInput();
        GUI.getJTextField().setText("");
        Player.pronouns = new String[]{subjective, objective, possessive, reference};
        GameHandler.getGui().display("Your pronouns are: " + subjective + ", " + objective + ", " + possessive + ", " + reference, "Black");
    }

    private static boolean canPerform(Skill skill, Ability ability) {
        // Check if the player has enough skill level to use the ability
        // You can define thresholds based on skill and ability
        int levelRequired = determineLevelRequired(skill, ability);
        return getSkillLevel(skill) >= levelRequired;
    }

    private static int determineLevelRequired(Skill skill, Ability ability) {
        switch (skill) {
            case SOCIAL -> {
                switch (ability) {
                    case CRY -> {
                        return 0;
                    }
                    case POINT -> {
                        return 1;
                    }
                    case NAME -> {
                        return 2;
                    }
                    case ASK -> {
                        return 3;
                    }
                    case NEGOTIATE -> {
                        return 4;
                    }
                    case MEDIATE -> {
                        return 5;
                    }
                    default ->
                        throw new IllegalArgumentException("Unexpected value: " + ability);
                }
            }

            case MOTOR -> {
                switch (ability) {
                    case CRAWL -> {
                        return 0;
                    }
                    case WALK -> {
                        return 1;
                    }
                    case RUN -> {
                        return 2;
                    }
                    case CLIMB -> {
                        return 3;
                    }
                    case SNEAK -> {
                        return 4;
                    }
                    case KICK -> {
                        return 5;
                    }
                    case SKIP -> {
                        return 6;
                    }
                    default ->
                        throw new IllegalArgumentException("Unexpected value: " + ability);
                }
            }

            case IMAGINATION -> {
                switch (ability) {
                    case IMITATE -> {
                        return 0;
                    }
                    case DOLLS -> {
                        return 1;
                    }
                    case PRODUCE -> {
                        return 2;
                    }
                    case DIRECT -> {
                        return 3;
                    }
                    case PLAY_ALONG -> {
                        return 4;
                    }
                    default ->
                        throw new IllegalArgumentException("Unexpected value: " + ability);
                }
            }

            case LEARNING -> {
                switch (ability) {
                    case READ -> {
                        return 0;
                    }
                    case WRITE -> {
                        return 1;
                    }
                    case BASICS -> {
                        return 2;
                    }
                    case INTERMEDIATE -> {
                        return 3;
                    }
                    case ADVANCED -> {
                        return 4;
                    }
                    case REMEDIATED -> {
                        return 5;
                    }
                    default ->
                        throw new IllegalArgumentException("Unexpected value: " + ability);
                }
            }

            case EMOTIONAL -> {
                switch (ability) {
                    case EXPRESS -> {
                        return 0;
                    }
                    case INTERPRET -> {
                        return 1;
                    }
                    case SELF_SOOTHE -> {
                        return 2;
                    }
                    case SOOTHE_OTHERS -> {
                        return 3;
                    }
                    case RESILIENCE -> {
                        return 4;
                    }
                    case TEMPERANCE -> {
                        return 5;
                    }
                    default ->
                        throw new IllegalArgumentException("Unexpected value: " + ability);
                }
            }

        }
        return -1;
    }

    private static int getEnergy() {
        return energy;
    }

    private static boolean isAlignmentSet() {
        return alignmentSet;
    }

    private static int getHunger() {
        return hunger;
    }

    private static int getThirst() {
        return thirst;
    }

    private static void setStats(int age) {
        int totalStats = 0;

        do {
            for (int i = 0; i < 5; i++) {
                Random random = new Random();
                switch (i) {
                    case 0 -> {
                        int tempStat = random.nextInt(age + 3) + 3;
                        stats.put("Social", tempStat);
                        totalStats += tempStat;
                    }
                    case 1 -> {
                        int tempStat = random.nextInt(age + 3) + 3;
                        stats.put("Motor", tempStat);
                        totalStats += tempStat;
                    }
                    case 2 -> {
                        int tempStat = random.nextInt(age + 3) + 3;
                        stats.put("Imagenation", tempStat);
                        totalStats += tempStat;
                    }
                    case 3 -> {
                        int tempStat = random.nextInt(age + 3) + 3;
                        stats.put("Learning", tempStat);
                        totalStats += tempStat;
                    }
                    case 4 -> {
                        int tempStat = random.nextInt(age) + 3;
                        stats.put("Emotional", tempStat);
                        totalStats += tempStat;
                    }
                    default -> {
                    }
                }

            }
        } while (totalStats <= age * 4 && totalStats >= age * 2);
        GameHandler.getGui().display("Your stats are: " + stats.get("Social") + ", " + stats.get("Motor") + ", " + stats.get("Imagenation") + ", " + stats.get("Learning") + ", " + stats.get("Emotional"), "Black");
    }

    private static void setUpStats() {
        for (int i = 0; i < 5; i++) {
            stats.put("Social", 0);
            stats.put("Motor", 0);
            stats.put("Imagenation", 0);
            stats.put("Learning", 0);
            stats.put("Emotional", 0);
        }
    }

    private static void setHidden(boolean b) {
        playerIsHidden = b;
    }

    public static int giveMoney(int price) {
        if (money >= price) {
            money -= price;
            return 1;
        } else {
            GameHandler.getGui().display("You don't have enough money.", "Black");
            return 0;
        }
    }

    static int getPocketSize() {
        return pocketSize;
    }

    private static ArrayList<Item> locateItem(Item item) {
        if (pockets.contains(item)) {
            return pockets;
        }
        if (backpack.contains(item)) {
            return backpack;
        }
        if (hands.contains(item)) {
            return hands;
        }
        return null;
    }

    static String[] getEquipmentChoices() {
        int i = 0;
        String[] items = new String[equipment.size()];
        for (Equipment item : equipment.values()) {
            if (item != null && item.getName() != null && !item.getName().equals("")) {
                items[i] = item.getName();
                i++;
            } else {
                GameHandler.getGui().display("You have no equipment.", "Black");
            }

        }
        return items;
    }

    private static boolean getPottyTrained() {
        return pottyTrained;
    }

    private static int getMaturity() {
        return maturity;
    }

    private static void addMaturity(int i) {
        maturity += i;
    }

    public static boolean isPottyTrained() {
        return pottyTrained;
    }

    public static void setPottyTrained(boolean pottyTrained) {
        Player.pottyTrained = pottyTrained;
    }

    public static boolean isNude() {
        if (equipment.containsKey("Underpants") && equipment.containsKey("Top") && equipment.containsKey("Bottom") && equipment.containsKey("Shoes")) {
            return false;
        } else {
            if (!equipment.containsKey("Underpants")) {
                GameHandler.getGui().display("You are missing underpants.", "Black");
                return true;
            } else if (!equipment.containsKey("Bottom")) {
                GameHandler.getGui().display("You are missing a bottom.", "Black");
                return true;
            } else if (!equipment.containsKey("Top")) {
                GameHandler.getGui().display("You are missing a top.", "Black");
                return true;
            } else if (!equipment.containsKey("Shoes")) {
                GameHandler.getGui().display("You are missing shoes.", "Black");
                return true;
            }
            return true;
        }
    }

    public static ArrayList<Card> getHand() {
        return hand;
    }

    public static void setHand(ArrayList<Card> hand) {
        Player.hand = hand;
    }

    static ArrayList<Card> getPawDeck() {
        return pawDeck;
    }

    public Player(String name, String discription, Room room) {

    }

    public void levelDownSkill(Skill skill) {
        skillLevels.put(skill, skillLevels.get(skill) - 1);
    }

    public void setSkillLevel(Skill skill, int level) {
        skillLevels.put(skill, level);
    }

    public void setSkillLevels(Map<Skill, Integer> skillLevels) {
        Player.skillLevels = skillLevels;
    }

    public Map<Skill, Integer> getSkillLevels() {
        return skillLevels;
    }

    public void setAbilities(Map<Skill, Map<Ability, Effect>> abilities) {
        Player.abilities = abilities;
    }

    public Map<Skill, Map<Ability, Effect>> getAbilities() {
        return abilities;
    }

    public void addAbility(Skill skill, Ability ability, Effect effect) {
        abilities.get(skill).put(ability, effect);
    }

    public static ArrayList<Item> getPockets() {
        return pockets;
    }

    public void removeAbility(Skill skill, Ability ability) {
        abilities.get(skill).remove(ability);
    }

    public void setAbility(Skill skill, Ability ability, Effect effect) {
        abilities.get(skill).put(ability, effect);
    }

    public void setPronouns(String subjective, String objective, String possessive, String reference) {
        pronouns = new String[]{subjective, objective, possessive, reference};
    }

    public static void dropItem(Item item) {
        if (item.isEquipped()) {
            GameHandler.getGui().display("You may not drop an equipped item.", "Black");
        } else {
            GameHandler.getGui().display("You drop the " + item.getName() + ".", "Black");
            getRoom().addItem(item);
            removeItem(item);
        }
    }

    private static void accident() {
        GameHandler.getGui().display("You had an accident.", "Black");
        setBlatter(0);
        addMaturity(-5);

        Equipment underpants = equipment.get("Underpants");
        GameHandler.getGui().display(underpants.getName(), "Black");
        switch (underpants.getName()) {
            case "Diaper" -> {
                underpants.setCondition("Soaked");
                GameHandler.getGui().display("Your " + underpants.getName() + " is leaking.", "Black");
            }
            default ->
                throw new AssertionError();
        }
    }
}
