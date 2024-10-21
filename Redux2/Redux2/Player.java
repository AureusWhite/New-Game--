package Redux2;

import java.util.ArrayList;
import java.util.HashMap;
//import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.swing.JOptionPane;

public class Player {
    static Random rand = new Random();

    //primitives
    static String[] pronouns;
    public static String alignment = "Newbie";
    public static int age = 0, maturity = 0, energy = 100, blatter = 10, pocketSize = 0, backpackSize = 0, experience = 0, money = 0, resilience = 0, hunger = 10, thirst = 10;
    private static boolean abilitiesSet, ageSet, alignmentSet, pottyTrained, leader, playerIsHidden;
    //arrays
    public final static ArrayList<Item> backpack = new ArrayList<>();
    public final static ArrayList<Item> pockets = new ArrayList<>();
    public final static ArrayList<Item> hands = new ArrayList<>();
    public final static HashMap<String, Equipment> equipment = new HashMap<>();
    public final static ArrayList<Quest> quests = new ArrayList<>();
    public final static HashMap<String, Integer> stats = new HashMap<>();
    public final static HashMap<Activities, Proficiencies> activities = new HashMap<>();

    //objects
    private static Room room;
    private static String name;
    private static String[] favorites;
    private static Map<Skill, Integer> skillLevels = new HashMap<>(); // Maps skills to levels
    private static ArrayList<Card> pawDeck = new ArrayList<>();
    private static ArrayList<Card> hand = new ArrayList<>();
    private static int mood;
    private static final ArrayList<Paw> paws = new ArrayList<>();
    private static final ArrayList<PlayerStatus> Status = new ArrayList<>();
    private static final ArrayList<Proficiencies> proficiencies = new ArrayList<>();

    public static ArrayList<Item> getHands() {
        return hands;
    }

    public boolean getLeader() {
        return leader;
    }

    public static boolean hasFigure(boolean b) {
        for (Item item : hands) {
            if (item.getName().equals("Paw Figure")) {
                return b;
            }
        }
        return b;
    }

    public static void initualizeSkills() {
        skillLevels.put(Skill.FINE_MOTOR, 1);
        skillLevels.put(Skill.GROSS_MOTOR, 1);
        skillLevels.put(Skill.SOCIAL, 1);
        skillLevels.put(Skill.EMOTIONAL, 1);
        skillLevels.put(Skill.IMAGINATION, 1);
        skillLevels.put(Skill.LEARNING, 1);
        skillLevels.put(Skill.PROBLEM_SOLVING, 1);
        skillLevels.put(Skill.COGNITIVE, 1);
    }

    public static ArrayList<Paw> getPaws() {
        return paws;

    }

    public static int getBlatter() {
        return blatter;
    }

    private static void setSleeping(boolean b) {
        if (b) {
            setStatus(PlayerStatus.SLEEPING);
        } else {
            removeStatus(PlayerStatus.SLEEPING);
        }
    }

    private static boolean isSleeping() {
        return Status.contains(PlayerStatus.SLEEPING);
    }

    public static void setStatus(PlayerStatus status) {
        GameHandler.getGui().display("You are " + status, "Black");
        Status.add(status);
    }

    public static ArrayList<PlayerStatus> getStatus() {
        return Status;
    }

    static void removeStatus(PlayerStatus playerStatus) {
        GameHandler.getGui().display("You are no longer " + playerStatus, "Black");
        Status.remove(playerStatus);
    }

    static ArrayList<Proficiencies> getProficiencies() {
        return proficiencies;
    }

    public static boolean hasItemByName(String itemName) {
        for (Item item : hands) {
            if (item.getName().equals(itemName)) {
                return true;
            }
        }
        for (Item item : pockets) {
            if (item.getName().equals(itemName)) {
                return true;
            }
        }
        for (Item item : backpack) {
            if (item.getName().equals(itemName)) {
                return true;
            }
        }
        return false;
    }

    private static void setMood(int i) {
        mood += i;
        switch (mood) {
            case 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100 ->
                setStatus(PlayerStatus.JUBILANT);
            case 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89 ->
                setStatus(PlayerStatus.HAPPY);
            case 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69 ->
                setStatus(PlayerStatus.CONTENT);
            case 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49 ->
                setStatus(PlayerStatus.UPSET);
            case 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29 ->
                setStatus(PlayerStatus.TANTRUM);
            case 1, 2, 3, 4, 5, 6, 7, 8, 9 ->
                setStatus(PlayerStatus.MELTDOWN);
            case 0 ->
                tantrum();
        }
    }

    static boolean isHeld() {
        return Player.Status.contains(PlayerStatus.HOLDING_HANDS) || Player.Status.contains(PlayerStatus.CARRIED);
    }

    public static boolean isPlayerIsHidden() {
        return playerIsHidden;
    }

    public static void setPlayerIsHidden(boolean playerIsHidden) {
        Player.playerIsHidden = playerIsHidden;
    }

    public ArrayList<Item> gatHands() {
        return hands;
    }

    public static void setEnergy(int energy) {
        Player.energy = energy;
    }

    public static void setPawDeck(ArrayList<Card> pawDeck1) {
        pawDeck = pawDeck1;
    }

    public static void setHand() {
        hand.clear();
        for (int i = 0; i < 5; i++) {
            hand.add(pawDeck.get(i));
        }
    }

    public static int getSkillLevel(Skill skill) {
        Integer level = skillLevels.get(skill);
        if (level == null) {
            level = rand.nextInt(age+3);
            setSkillLevel(skill, level);
        }
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
        GameHandler.updateAchievementsForRoomVisit(room1);
        room = room1;
        room.initializeRoomFiles();
        GameHandler.getGui().updateSidePanels();
        if (room == GameHandler.getRoomByName("Demo_Room")) {
            GameHandler.demo();
        }
    }

    public void reduceEnergy(int i) {
        energy -= i;
    }

    public void increaseEnergy(int i) {
        energy += i;
    }

    public void applyFlowBonus() {
        // Apply the flow bonus to the player's energy
    }

    public void dynamicEnergyConsumption(boolean isPhysical, boolean isMental) {
        // Adjust energy based on activity and time of day
    }

    public void applyXPBonus() {
        // Apply the XP bonus to the player's experience
    }

    public void dynamicXPGeneration(boolean isPhysical, boolean isMental) {
        // Adjust XP based on activity and time of day
    }

    public void applyResilienceBonus() {
        // Apply the resilience bonus to the player's resilience
    }

    public void progressiveFlowBonus() {
        // Increase the flow bonus over time
    }

    public static Room getRoom() {
        return Player.room;
    }

    public static void setAge() {
        GameHandler.getGui().display("Please enter your age.", "Black");
        GameHandler.getGui().waitForInput();
        String input = GameHandler.getGui().getInput();
        GUI.getJTextField().setText("");
        GameHandler.getGui().display("You are " + input + " years old.", "Black");
        age = Integer.parseInt(input);
        ageSet = true;
    }

    public static void equip(Equipment equipment1, String slot) {
        // Check if the slot already has an item equipped
        if (equipment.containsKey(slot)) {
            Equipment currentEquipment = equipment.get(slot);
            currentEquipment.setEquipped(false);
            Player.equipment.remove(currentEquipment.getSlot());
        } else {
            // Equip the item
            equipment.put(slot, equipment1);
            GameHandler.updateAchievementsForEquip(equipment1);
            equipment1.setEquipped(true);
            calculatePockets();
        }
    }

    public static void unequip(Equipment equipment1) {
        equipment1.setEquipped(false);
        equipment.remove(equipment1.getSlot());
        calculatePockets();
    }

    public static void calculatePockets() {
        pocketSize = 0;
        backpackSize = 0;
        for (Equipment equip : equipment.values()) {
            if (equip.getPockets() == 0) {
                GameHandler.getGui().display(equip.getName() + " has no pockets", "Black");
            } else if (equip.getSlot().equalsIgnoreCase("back")) {
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

    public static void setHunger(int hunger1) {
        if (hunger < 60) {
            GameHandler.getGui().display("Your Stomach growls", "Black");
        } else if (hunger < 40) {
            GameHandler.getGui().display("You are getting a little hangry", "Black");
            Player.setMood(-10);
            getRoom().attractAttention("Hangry");
        } else if (hunger < 20) {
            GameHandler.getGui().display("You cry out in hunger", "Black");
            Player.setMood(-25);
            getRoom().attractAttention("Crying");
        }
    }

    public static void setThirst(int thirst1) {
        if (thirst < 0) {
            thirst = 0;
            getRoom().attractAttention("Crying");
        }
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
            setSleeping(true);
            FatherTime.getClock().moveTime(time);
            GameHandler.getGui().display("You took a nap for " + FatherTime.getClock().formatByHourMinute(time), "Black");
            setSleeping(false);
        } else {
            GameHandler.getGui().display("You are not tired.", "Black");
        }
    }

    public static void potty() {

        if (getRoom().getType().equals(RoomType.BATHROOM)) {
            if (Player.isPottyTrained()) {
                GameHandler.getGui().display("You used the potty.", "Black");
                blatter = 0;
                addMaturity(0);
                addXP(10);
            } else {
                GameHandler.getGui().display("You are not potty trained.", "Black");
            }
        } else {
            GameHandler.getGui().display("You are not in the bathroom.", "Black");
        }
    }

    public static void setBlatter(int i) {
        blatter = i;
        checkBlatter();
    }

    public static void checkBlatter() {
        GameHandler.getGui().display("Blatter: " + blatter, "Black");
        if (blatter > 100) {
            accident();
        } else if (pottyTrained) {
            switch (blatter) {
                case 96, 97, 98, 99, 100 ->
                    GameHandler.getGui().display("You need to use the potty right now!", "Black");
                case 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95 ->
                    GameHandler.getGui().display("You need to potty soon.", "Black");
                case 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74 ->
                    GameHandler.getGui().display("You should use the potty.", "Black");
                case 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49 ->
                    GameHandler.getGui().display("You might need to potty.", "Black");
                case 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24 ->
                    GameHandler.getGui().display("You do not need to potty.", "Black");

            }
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
        Random rand = new Random();
        switch (getSkillLevel(Skill.FINE_MOTOR)) {
            case 1 -> {
                GameHandler.getGui().display("You attempt to sneak but comically fumble around making even more noise", "Black");
                int outcome = rand.nextInt(20) + 1;
                if (outcome > 18) {
                    GameHandler.getGui().display("They must have been on their phone or something because no one noticed that, and now they can't see you. Proboblu still know you're there though.", "Black");
                    PlayerStatus.HIDDEN.activate();
                } else if (outcome == 17 || outcome == 18) {
                    levelUpSkill(Skill.FINE_MOTOR);
                } else {
                    GameHandler.getGui().display(Player.getRoom().randomNPC(false).getName() + " noticed you and smirked with a laugh.", "Black");
                }
            }
            case 2 -> {
                GameHandler.getGui().display("This time you know not to stomp and pretend to be a dinosaur, they are not allowed at school,", "Black");
                int outcome = rand.nextInt(20) + 1;
                if (outcome > 16) {
                    GameHandler.getGui().display("Somehow you manage to get out of sight unnoticed. Try not to giggle when they look for you, not like last time.", "Black");
                    PlayerStatus.HIDDEN.activate();
                } else if (outcome == 15 || outcome == 16) {
                    levelUpSkill(Skill.FINE_MOTOR);
                } else {
                    GameHandler.getGui().display(Player.getRoom().randomNPC(false).getName() + " noticed you and smirked with a laugh.", "Black");
                }
                GameHandler.getGui().display(Player.getRoom().randomNPC(false).getName() + ": Hello, " + Player.getName() + " are you a bank robber? I can see you.", "Black");
            }
            case 3 -> {
                GameHandler.getGui().display("Giggles in check and serious faced you pretend to be a ninja, even though they are also not allowed at school, a ninja is queit about their mischeif", "Black");
                int outcome = rand.nextInt(20) + 1;
                if (outcome > 14) {
                    GameHandler.getGui().display("You cannot be seen, the paper ninja stars however may be.", "Black");
                    PlayerStatus.HIDDEN.activate();
                } else if (outcome == 13 || outcome == 14) {
                    levelUpSkill(Skill.FINE_MOTOR);
                } else {
                    GameHandler.getGui().display(Player.getRoom().randomNPC(false).getName() + ": I see you, " + Player.getName() + ", you are not a ninja.", "Black");
                }
            }
            case 4 -> {
                GameHandler.getGui().display("Okay rule one, be quiet, rule two don't throw things, sneaking is getting easier.", "Black");
                int outcome = rand.nextInt(20) + 1;
                if (outcome > 12) {
                    GameHandler.getGui().display("Wow, people notice less if do not beaning them in the eye with a paper shuruken. Good to know", "Black");
                    PlayerStatus.HIDDEN.activate();
                } else if (outcome == 11 || outcome == 12) {
                    levelUpSkill(Skill.FINE_MOTOR);
                } else {
                    GameHandler.getGui().display(Player.getRoom().randomNPC(false).getName() + " *clears their throat* It's obvious they can see you sneaking off.", "Black");
                }
            }
            case 5 -> {
                GameHandler.getGui().display("You attempt to sneak, making as little sound as possible", "Black");
                int outcome = rand.nextInt(20) + 1;
                if (outcome > 10) {
                    GameHandler.getGui().display("you slip away into hiding keeping out of sight.", "Black");
                    PlayerStatus.HIDDEN.activate();
                } else if (outcome == 9 || outcome == 10) {
                    levelUpSkill(Skill.FINE_MOTOR);
                } else {
                    GameHandler.getGui().display(Player.getRoom().randomNPC(false).getName() + " *clears their throat* It's obvious they can see you sneaking off.", "Black");
                }
            }
            case 6 -> {
                GameHandler.getGui().display("You attempt to sneak and slip away and are very hard to notice", "Black");
                int outcome = rand.nextInt(20) + 1;
                if (outcome > 8) {
                    GameHandler.getGui().display("You easily duck into cover, quick reflexes keeping you hidden", "Black");
                    PlayerStatus.HIDDEN.activate();
                } else if (outcome == 7 || outcome == 8) {
                    levelUpSkill(Skill.FINE_MOTOR);
                } else {
                    GameHandler.getGui().display(Player.getRoom().randomNPC(false).getName() + " : " + Player.getName() + " what are you up to?, I barely saw you out of the corner of my eye.", "Black");
                }
            }
            case 7, 8, 9, 10 -> {
                GameHandler.getGui().display("Small framed, light footed, you vanish as soon as they turn away.", "Black");
                PlayerStatus.HIDDEN.activate();
            }
        }
    }

    public static boolean isLeader() {
        return PlayerStatus.LEADER.isActive();
    }

    public static void setFavorites(String color, String food, String toy, String game, String book, String subject, String activity) {
        favorites = new String[]{color, food, toy, game, book, subject, activity};

    }

    public static HashMap<String, Integer> getStats() {
        return stats;
    }

    public static boolean isAgeSet() {
        return ageSet;
    }

    public static void setPronouns(String[] pronouns) {
        Player.pronouns = pronouns;
    }

    public static void setName(String input) {
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

    static void addItem(Item item) {
        String[] options = {"Pockets", "Backpack", "Hands", "Equip", "Eat"};
        int choice = JOptionPane.showOptionDialog(null, "Where would you like to put the " + item.getName() + "?", "Choose a location", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0 -> {
                if (pockets.size() < pocketSize) {
                    GameHandler.getGui().display("You put the " + item.getName() + " in your pockets", "Black");

                    List<String> pocketedClothing = new ArrayList<>();
                    for (Equipment equip : getEquipment().values()) {
                        if (equip.getPockets() > 0) {
                            pocketedClothing.add(equip.getName());
                        }
                    }

                    if (!pocketedClothing.isEmpty()) {
                        String[] pocketedClothingArray = pocketedClothing.toArray(String[]::new);
                        int equipChoice = JOptionPane.showOptionDialog(null, "Select the equipment to put the " + item.getName() + " in:", "Choose a location", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, pocketedClothingArray, pocketedClothingArray[0]);

                        if (equipChoice >= 0) {
                            Equipment selectedEquip = getEquipment().values().stream()
                                    .filter(e -> e.getName().equals(pocketedClothingArray[equipChoice]))
                                    .findFirst().orElse(null);

                            if (selectedEquip != null) {
                                selectedEquip.addItem(item);
                            }
                        } else {
                            GameHandler.getGui().display("No equipment selected.", "Black");
                            getRoom().addItem(item); // Item dropped if no pocketed equipment is selected
                        }
                    } else {
                        GameHandler.getGui().display("You don't have any equipment with pockets.", "Black");
                        getRoom().addItem(item); // Item dropped if no pocketed equipment exists
                    }
                } else {
                    GameHandler.getGui().display("Your pockets are full or you don't have any.", "Black");
                    getRoom().addItem(item); // Item dropped if pockets are full
                }
            }
            case 1 -> {
                if (backpack != null && backpack.size() < backpackSize) {
                    GameHandler.getGui().display("You put the " + item.getName() + " in your backpack", "Black");
                    backpack.add(item);
                } else {
                    GameHandler.getGui().display("Your backpack is full or you don't have one.", "Black");
                    getRoom().addItem(item); // Item dropped if no space in backpack
                }
            }
            case 2 -> {
                if (hands.size() < 2) {
                    GameHandler.getGui().display("You hold the " + item.getName() + " in your hand", "Black");
                    hands.add(item);
                } else {
                    GameHandler.getGui().display("Your hands are full.", "Black");
                    getRoom().addItem(item); // Item dropped if hands are full
                }
            }
            case 3 -> {
                if (item instanceof Equipment equipment1) {
                    equip(equipment1, equipment1.getSlot());
                } else {
                    GameHandler.getGui().display("You can't equip that.", "Black");
                    getRoom().addItem(item); // Item dropped if not equippable
                }
            }
            case 4 -> {
                // Check if the item has conditions and the FOOD condition is present
                if (item.getTypes() != null && (item.getTypes().containsKey(ItemType.FOOD) || item.getTypes().containsKey(ItemType.DRINK))) {
                    item.use(); // Eating the item if it’s food
                } else {
                    GameHandler.getGui().display("You can't eat that.", "Black");
                    Player.addItem(item); // Add the item back to the player's inventory if not food
                }
            }
            default -> {
                GameHandler.getGui().display("Action canceled.", "Black"); // Handle cancel or invalid option
            }
        }
    }

    static void setLeader(boolean b) {
        leader = b;
    }

    public static void addQuest(Quest quest1) {
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

    public static void setPronouns() {
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

    static int getEnergy() {
        return energy;
    }

    private static boolean isAlignmentSet() {
        return alignmentSet;
    }

    static int getHunger() {
        return hunger;
    }

    static int getThirst() {
        return thirst;
    }

    public static void setProficiencies() {
        if (getSkillLevel(Skill.SOCIAL) >= 2) {
            getProficiencies().add(Proficiencies.SIMPLE_INSTRUCTIONS);
            getProficiencies().add(Proficiencies.PARALLEL_PLAY);
        }
        if (getSkillLevel(Skill.FINE_MOTOR) >= 2) {
            getProficiencies().add(Proficiencies.DRAW);
            getProficiencies().add(Proficiencies.CRAFT);
        }
        if (getSkillLevel(Skill.GROSS_MOTOR) >= 2) {
            getProficiencies().add(Proficiencies.TODDLE);
            getProficiencies().add(Proficiencies.CRAWL);
        }
        if (getSkillLevel(Skill.IMAGINATION) >= 2) {
            getProficiencies().add(Proficiencies.PRETEND);
            getProficiencies().add(Proficiencies.PERFORM);
        }
        if (getSkillLevel(Skill.LEARNING) >= 2) {
            getProficiencies().add(Proficiencies.READ);
            getProficiencies().add(Proficiencies.LISTEN);
        }
        if (getSkillLevel(Skill.EMOTIONAL) >= 2) {
            getProficiencies().add(Proficiencies.EMPATHIZE);
            getProficiencies().add(Proficiencies.EXPRESS_DISTRESS);
        }
        if (getSkillLevel(Skill.SELF_CARE) >= 2) {
            getProficiencies().add(Proficiencies.NAP);
            getProficiencies().add(Proficiencies.EAT_HELPED);
        }
        if (getSkillLevel(Skill.COGNITIVE) >= 2) {
            getProficiencies().add(Proficiencies.SOLVE);
            getProficiencies().add(Proficiencies.MEMORIZE);
        }
        if (getSkillLevel(Skill.COMMUNICATION) >= 2) {
            getProficiencies().add(Proficiencies.SPEAK);
            getProficiencies().add(Proficiencies.PAYATTENTION);
        }
        if (getSkillLevel(Skill.SOCIAL) >= 4) {
            getProficiencies().add(Proficiencies.BEFRIEND);
            getProficiencies().add(Proficiencies.SHARE);
        }
        if (getSkillLevel(Skill.FINE_MOTOR) >= 4) {
            getProficiencies().add(Proficiencies.DRESS);
            getProficiencies().add(Proficiencies.OPEN);
        }
        if (getSkillLevel(Skill.GROSS_MOTOR) >= 4) {
            getProficiencies().add(Proficiencies.WALK);
            getProficiencies().add(Proficiencies.CLIMB);
        }
        if (getSkillLevel(Skill.IMAGINATION) >= 4) {
            getProficiencies().add(Proficiencies.DIRECT);
            getProficiencies().add(Proficiencies.CREATE);
        }
        if (getSkillLevel(Skill.LEARNING) >= 4) {
            getProficiencies().add(Proficiencies.READALOUD);
            getProficiencies().add(Proficiencies.WRITE);
        }
        if (getSkillLevel(Skill.EMOTIONAL) >= 4) {
            getProficiencies().add(Proficiencies.SOOTHE_SELF);
            getProficiencies().add(Proficiencies.EXPRESS_NEEDS);
        }
        if (getSkillLevel(Skill.SELF_CARE) >= 4) {
            getProficiencies().add(Proficiencies.BATHE_SELF);
            getProficiencies().add(Proficiencies.BRUSH_SELF);
        }
        if (getSkillLevel(Skill.COGNITIVE) >= 4) {
            getProficiencies().add(Proficiencies.SOLVE_MEDIUM);
            getProficiencies().add(Proficiencies.COMPLEX_INSTRUCTIONS);
        }
        if (getSkillLevel(Skill.COMMUNICATION) >= 4) {
            getProficiencies().add(Proficiencies.PUBLIC_SPEAK);
            getProficiencies().add(Proficiencies.LISTEN);
        }
        if (getSkillLevel(Skill.SOCIAL) >= 6) {
            getProficiencies().add(Proficiencies.LEADER);
            getProficiencies().add(Proficiencies.CLIQUE);
        }
        if (getSkillLevel(Skill.FINE_MOTOR) >= 6) {
            getProficiencies().add(Proficiencies.DRAW_WELL);
            getProficiencies().add(Proficiencies.CRAFT_LVL2);
        }
        if (getSkillLevel(Skill.GROSS_MOTOR) >= 6) {
            getProficiencies().add(Proficiencies.RUN);
            getProficiencies().add(Proficiencies.JUMP);
        }
        if (getSkillLevel(Skill.IMAGINATION) >= 6) {
            getProficiencies().add(Proficiencies.BRAVO);
            getProficiencies().add(Proficiencies.COSTUMEDESIGN);
        }
        if (getSkillLevel(Skill.LEARNING) >= 6) {
            getProficiencies().add(Proficiencies.TUTOR);
            getProficiencies().add(Proficiencies.WRITE_WELL);
        }
        if (getSkillLevel(Skill.EMOTIONAL) >= 6) {
            getProficiencies().add(Proficiencies.SOOTH_OTHERS);
            getProficiencies().add(Proficiencies.SHARE_LVL2);
        }
        if (getSkillLevel(Skill.SELF_CARE) >= 6) {
            getProficiencies().add(Proficiencies.DRESS_SELF);
            getProficiencies().add(Proficiencies.KEEP_CLEAN);
        }
        if (getSkillLevel(Skill.COGNITIVE) >= 6) {
            getProficiencies().add(Proficiencies.SOLVE_COMPLEX);
            getProficiencies().add(Proficiencies.MEMORIZE_MORE);
        }
        if (getSkillLevel(Skill.COMMUNICATION) >= 6) {
            getProficiencies().add(Proficiencies.SPEAK_CLEARLY);
            getProficiencies().add(Proficiencies.LISTEN_CAREFULLY);
        }
        if (getSkillLevel(Skill.SOCIAL) >= 8) {
            getProficiencies().add(Proficiencies.POPULAR);
            getProficiencies().add(Proficiencies.CHARISMATIC);
        }
        if (getSkillLevel(Skill.FINE_MOTOR) >= 8) {
            getProficiencies().add(Proficiencies.DRAW_EXPERT);
            getProficiencies().add(Proficiencies.CRAFT_LVL3);
        }
        if (getSkillLevel(Skill.GROSS_MOTOR) >= 8) {
            getProficiencies().add(Proficiencies.DODGE);
            getProficiencies().add(Proficiencies.BALANCE);
        }
        if (getSkillLevel(Skill.IMAGINATION) >= 8) {
            getProficiencies().add(Proficiencies.DIRECT_LVL2);
            getProficiencies().add(Proficiencies.CREATE_LVL2);
        }
        if (getSkillLevel(Skill.LEARNING) >= 8) {
            getProficiencies().add(Proficiencies.TUTOR_LVL2);
            getProficiencies().add(Proficiencies.WRITE_EXPERT);
        }
        if (getSkillLevel(Skill.EMOTIONAL) >= 8) {
            getProficiencies().add(Proficiencies.SOOTH_LVL2);
            getProficiencies().add(Proficiencies.SHARE_LVL3);
        }
        if (getSkillLevel(Skill.SELF_CARE) >= 8) {
            getProficiencies().add(Proficiencies.DRESS_WELL);
            getProficiencies().add(Proficiencies.KEEP_CLEAN_LVL2);
        }
        if (getSkillLevel(Skill.COGNITIVE) >= 8) {
            getProficiencies().add(Proficiencies.SOLVE_EXPERT);
            getProficiencies().add(Proficiencies.MEMORIZE_EXPERT);
        }
        if (getSkillLevel(Skill.COMMUNICATION) >= 8) {
            getProficiencies().add(Proficiencies.FOLLOW_CONVERSATION);
            getProficiencies().add(Proficiencies.LISTEN_CAREFULLY_LVL2);
        }
        displayProficiencies();

    }

    private static void displayProficiencies() {
        if (proficiencies.isEmpty()) {
            GameHandler.getGui().display("You have no proficiencies.", "Black");
        }
        for (Proficiencies proficiency : proficiencies) {
            GameHandler.getGui().display(proficiency.getName(), "Black");
        }
    }

    public static void setStats(int age) {
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

    public static void setHidden(boolean b) {
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

    public static int getPocketSize() {
        return pocketSize;
    }

    static void removeItem(Item item) {
        if (item == null) {
            GameHandler.getGui().display("Item does not exist?", "Black");
            return;
        }

        ArrayList<Item> itemLocation = locateItem(item);
        if (itemLocation != null) {
            itemLocation.remove(item);
            GameHandler.getGui().display("You successfully removed " + item.getName() + " from your inventory", "Black");
        } else {
            GameHandler.getGui().display("Error: The item was not found in any inventory.", "Red");
        }
    }

    static ArrayList<Item> locateItem(Item item) {
        if (pockets.contains(item)) {
            GameHandler.getGui().display("Item found in pockets", "Black");
            return pockets;
        }
        if (backpack.contains(item)) {
            GameHandler.getGui().display("Item found in backpack", "Black");
            return backpack;
        }
        if (hands.contains(item)) {
            GameHandler.getGui().display("Item found in hands", "Black");
            return hands;
        }

        GameHandler.getGui().display("Item not found in any inventory", "Red");
        return null;
    }

    public static String[] getEquipmentChoices() {
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

    public static int getMaturity() {
        maturity = 1;
        for (int i : stats.values()) {
            if (getSkillLevel(Skill.SELF_CARE) >= 2) {
                maturity += i * 1.3;
            } else if (getSkillLevel(Skill.EMOTIONAL) >= 3) {
                maturity += i * 1;
            } else if (getSkillLevel(Skill.GROSS_MOTOR) >= 2) {
                maturity += i * 1;
            } else if (getSkillLevel(Skill.LEARNING) >= 3) {
                maturity += i * 1;
            } else if (getSkillLevel(Skill.SOCIAL) >= 2) {
                maturity += i * 1.1;
            } else if (getSkillLevel(Skill.FINE_MOTOR) > 2) {
                maturity += i * 1.2;
            } else if (getSkillLevel(Skill.IMAGINATION) > 3) {
                maturity += i * 1.2;
            } else {
                maturity += i * 1;
            }
        }
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

    public static boolean isNude(NPC npc) {
        if (equipment.containsKey("Underpants") && equipment.containsKey("Top") && equipment.containsKey("Bottom") && equipment.containsKey("Shoes")) {
            return false;
        } else {
            npc.noticePlayer("nude");
            return true;
        }
    }

    public static ArrayList<Card> getHand() {
        return hand;
    }

    public static void setHand(ArrayList<Card> hand) {
        Player.hand = hand;
    }

    public static ArrayList<Card> getPawDeck() {
        return pawDeck;
    }

    public Player(String name, String discription, Room room) {

    }

    public void levelDownSkill(Skill skill) {
        skillLevels.put(skill, skillLevels.get(skill) - 1);
    }

    public static void setSkillLevel(Skill skill, int level) {
        skillLevels.put(skill, level);
    }

    public void setSkillLevels(Map<Skill, Integer> skillLevels) {
        Player.skillLevels = skillLevels;
    }

    public Map<Skill, Integer> getSkillLevels() {
        return skillLevels;
    }

    public static ArrayList<Item> getPockets() {
        return pockets;
    }

    public void setPronouns(String subjective, String objective, String possessive, String reference) {
        pronouns = new String[]{subjective, objective, possessive, reference};
    }

    public static void dropItem(Item item) {
        GameHandler.getGui().display("You drop the " + item.getName() + ".", "Black");
        getRoom().addItem(item);
        removeItem(item);
    }

    private static void accident() {
        GameHandler.getGui().display("You had an accident.", "Black");
        blatter = 0;
        addMaturity(-5);
        if (isSleeping()) {
            GameHandler.getGui().display("You wet the bed.", "Black");
        }
        Equipment underpants = equipment.get("Underpants");
        if (underpants == null) {
            GameHandler.getGui().display("You are not wearing underpants.", "Black");
            setStatus(PlayerStatus.WET_CLOTHING);
            return;
        }
        Equipment bottoms = equipment.get("Bottom");
        GameHandler.getGui().display(underpants.getName(), "Black");
        switch (underpants.getName()) {
            case "Diaper" -> {
                underpants.setCondition(ItemCondition.WET, true);
                GameHandler.getGui().display("Your " + underpants.getName() + " is wet.", "Black");
                setStatus(PlayerStatus.WET_DIAPER);
                Player.getRoom().attractAttention("wetSelf");
            }
            case "Training Pants" -> {
                underpants.setCondition(ItemCondition.WET, true);
                GameHandler.getGui().display("Your " + underpants.getName() + " are wet.", "Black");
                setStatus(PlayerStatus.WET_DIAPER);
                Player.getRoom().attractAttention("wetSelf");
            }
            case "Underpants" -> {
                underpants.setCondition(ItemCondition.WET, true);
                GameHandler.getGui().display("Your " + underpants.getName() + " and " + bottoms.getName() + " are wet.", "Black");
                Player.getRoom().attractAttention("wetSelf");
                setStatus(PlayerStatus.WET_CLOTHING);
            }
            default ->
                throw new AssertionError();
        }
    }

    public static void beMoved(NPC npc, Events event) {
        String options[] = {"Upsies!", "Hold Hands", "Refuse"};
        String choice = (String) JOptionPane.showInputDialog(null, "How would you like to be moved?", "Choose an option", JOptionPane.DEFAULT_OPTION, null, options, options[0]);
        switch (choice) {
            case "Upsies!" -> {
                GameHandler.getGui().display("You are picked up by " + npc.getName() + " and carried to " + event.getRoom().getName() + " for " + event.getName(), "Black");
                npc.movePlayer(choice, event);
                setRoom(room);
            }
            case "Hold Hand" -> {
                GameHandler.getGui().display("You hold hands with " + npc.getName() + " and walk to " + event.getRoom().getName() + " together  for " + event.getName(), "Black");
                npc.movePlayer(choice, event);
                setRoom(room);
            }
            case "Refuse" -> {
                GameHandler.getGui().display("You refuse to move.", "Black");
                npc.movePlayer(choice, event);
            }
        }

    }

    public static PawFigure getPawFigure() {
        PawFigure pawFigure = (PawFigure) GameHandler.getItemByName(getFigureChoices());
        return pawFigure;
    }

    private static String getFigureChoices() {
        String choice = null;
        int i = 0;
        String[] choices = new String[hands.size()];
        for (Item item : hands) {
            if (item instanceof PawFigure) {
                choices[i] = item.getName();
                i++;
            } else {
                GameHandler.getGui().display("You have no figures in your hands", "Black");
            }
            choice = (String) JOptionPane.showInputDialog(null, "Choose a figure", "Choose a figure", JOptionPane.DEFAULT_OPTION, null, choices, choices[0]);
        }
        return choice;

    }

    public static void addSkillLevel(Skill skill, Integer integer) {
        setSkillLevel(skill, getSkillLevel(skill) + integer);
    }

    public static void displaySkills() {
        for (Skill skill : skillLevels.keySet()) {
            GameHandler.getGui().display(skill + ": " + skillLevels.get(skill), "Black");
        }
    }
}
