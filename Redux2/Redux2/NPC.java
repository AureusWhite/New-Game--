package Redux2;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.swing.JOptionPane;

public class NPC extends Character {

    public static void followPlayer(NPC npc) {
        if (true) {
            Player.setLeader(true);
            npc.follower = true;
            GameHandler.getGui().display(npc.getName() + " is following you", "black");
            npc.setRoom(Player.getRoom());
        }
        /*else {
            GameHandler.getGui().display(npc.getName() + " is not interested in following you", "black");
        }*/
    }
    private final Map<Skill, Integer> skillLevels = new HashMap<>();

    public Map<Skill, Integer> getSkillLevels() {
        return this.skillLevels;
    }
    private int npcAge;
    private boolean faction;
    private final Map<Integer, Double> pRep = new HashMap<>();
    private boolean confederate;
    private int suspicion;
    private String playerRep = "Good";
    private String alignment;
    boolean follower;
    private final HashMap<NPCType, Boolean> types = new HashMap<>();
    private final HashMap<NPCStatus, Boolean> npcStatus = new HashMap<>();
    private Quest currentQuest;
    private final ArrayList<Quest> quests = new ArrayList<>();

    public NPC(String name, String description, Room room, String type) {
        super(name, description, room);
        this.pRep.put(0, 1.33);
        this.pRep.put(1, 1.33);
        this.pRep.put(2, 1.33);
        this.pRep.put(3, 1.33);
        initualizeSkills();
    }

    public int getNpcAge() {
        return npcAge;
    }

    public Quest getCurrentQuest() {
        return this.currentQuest;
    }

    public void wander(RoomType roomType) {
        // Get the current room of the NPC
        Room currentRoom = this.getRoom();
        // Create a list to store valid rooms of the specified type (GREEN in this case)
        List<Room> validRooms = new ArrayList<>();
        // Iterate through all exits (which are room names) and resolve the actual Room objects
        for (String exitName : currentRoom.getExits()) {
            Room adjacentRoom = GameHandler.getRoomByName(exitName);  // Resolve the Room object from the name
            if (adjacentRoom.getType() == roomType) {
                validRooms.add(adjacentRoom);  // Add to the valid rooms list if it matches the roomType
            }
        }

        // If there are valid rooms of the specified type
        if (!validRooms.isEmpty()) {
            // Pick a random room from the valid rooms
            Random random = new Random();
            int roomNumber = random.nextInt(validRooms.size());
            Room destination = validRooms.get(roomNumber);

            // Move the NPC to the chosen room
            this.setRoom(destination);
            currentRoom.removeNPC(this);
            System.out.println(this.getName() + " has wandered to " + destination.getName());
        } else {
            // No valid rooms found
            System.out.println("No adjacent rooms of the specified type.");
        }
    }

    public void routine() {
        if (this.getType().containsKey(NPCType.REJUVE)) {
            FatherTime.DayPhase phase = FatherTime.getCurrentPhase();
            switch (phase) {
                case MORNING -> {
                    //wander(Room.ROOMTYPE.GREEN);
                }
                case BREAKFAST -> {
                    setRoom(GameHandler.getRoomByName("Snack Area"));
                }
                case SCHOOL_STRUCTUREDPLAY -> {
                    setRoom(GameHandler.getRoomByName("Classroom"));

                }
                case LUNCH -> {
                    setRoom(GameHandler.getRoomByName("Snack Area"));

                }
                case NAP -> {
                    setRoom(GameHandler.getRoomByName("Dorms"));

                }
                case FREETIME -> {
                    // wander(Room.ROOMTYPE.GREEN);

                }
                case DINNER -> {
                    setRoom(GameHandler.getRoomByName("Snack Area"));

                }
                case DORMS -> {
                    setRoom(GameHandler.getRoomByName("Dorms"));

                }
                case BATHS_BRUSHES -> {
                    setRoom(GameHandler.getRoomByName("Bathrooms"));

                }
                case TIDYUP -> {
                    setRoom(GameHandler.getRoomByName("Classroom"));

                }
                case GREEN -> {
                    //  wander(Room.ROOMTYPE.GREEN);

                }
                case RED -> {
                    setRoom(GameHandler.getRoomByName("Dorms"));

                }
                case BLUE -> {
                    setRoom(GameHandler.getRoomByName("Classroom"));

                }
                case NIGHT -> {
                    setRoom(GameHandler.getRoomByName("Dorms"));

                }

            }
        }
    }

    public void setNpcAge(int npcAge) {
        this.npcAge = npcAge;
    }

    public void reciveItem(Item item) {

        GameHandler.getGui().display("You have given " + this.getName() + " an item", "black");
    }

    public void giveItemToPlayer(Item item) {
        Player.addItem(item);
        this.removeItem(item);
    }

    public void bePutDownForNap() {
        Random rand = new Random();
        int mood = 4;
        if (this.getStatus().containsKey(NPCStatus.BADMOOD)) {
            mood = 6;
        }
        int num = rand.nextInt(mood - (Player.getSkillLevel(Skill.SOCIAL)));
        switch (num) {
            case 0 ->
                GameHandler.getGui().display("You put " + this.getName() + " down for a nap and they go to sleep peacefully", "black");
            case 1 -> {
                GameHandler.getGui().display("You put " + this.getName() + " down for a nap and they cry and fuss", "black");
                FatherTime.getClock().moveTime(5);
            }
            case 2 -> {
                GameHandler.getGui().display("You put " + this.getName() + " down for a nap and they refuse to sleep", "black");
                FatherTime.getClock().moveTime(5);
                this.setStatus(NPCStatus.BADMOOD, true);
            }
            default -> {
                GameHandler.getGui().display("You put " + this.getName() + " down for a nap but they kick you and run away", "black");
                this.setFollower(false);
                this.wander(RoomType.GREEN);
            }
        }
    }

    public String getDialog() {
        GameHandler.fileSection2 = "Hello, I am " + this.getName().replace("_", " ");
        GameHandler.fileSection3 = "Good afternoon, I am " + this.getName().replace("_", " ");
        GameHandler.fileSection4 = "Good evening, I am " + this.getName().replace("_", " ");
        GameHandler.readFile(this.getName());
        switch (FatherTime.getClock().getTimeOfDay()) {
            case "Morning" -> {
                return GameHandler.fileSection2;
            }
            case "Afternoon" -> {
                return GameHandler.fileSection3;
            }
            case "Evening" -> {
                return GameHandler.fileSection4;
            }
            case "Night" -> {
                return "Good night, I am " + this.getName().replace("_", " ") + ".";
            }
            default -> {
                return "Hello, I am " + this.getName().replace("_", " ") + ".";
            }
        }
    }

    public void getPranked() {
        if (this.getType().containsKey(NPCType.ADULT) || this.getType().containsKey(NPCType.COMPANION)) {
            Random random = new Random();
            int num = random.nextInt(2);
            switch (num) {
                case 0 -> {
                    GameHandler.getGui().display("You walk up and place a pickle into " + this.getName() + "'s pocket.", "black");
                    caughtPlayer("pranking");
                    break;
                }
                case 1 -> {
                    GameHandler.getGui().display("You put a plastic spider into " + this.getName() + "'s hair.", "black");
                    caughtPlayer("pranking");
                    break;
                }
            }
        } else if (this.getType().containsKey(NPCType.REJUVE)) {
            GameHandler.getGui().display(this.getName() + " is not amused by your prank", "black");
        }
    }

    public void caughtPlayer(String act) {
        for (NPC npc : this.getRoom().getNPCs()) {
            if (npc.getType().containsKey(NPCType.ADULT)) {
                GameHandler.getGui().display("You are caught by " + this.getName() + " while " + act, "black");
                this.disipline(act, npc, 10);
            }
            if (npc.getType().containsKey(NPCType.REJUVE)) {
                GameHandler.getGui().display("You are caught by " + this.getName() + " while " + act, "black");
            }
            if (npc.getType().containsKey(NPCType.COMPANION)) {
                GameHandler.getGui().display("You are caught by " + this.getName() + " while " + act, "black");
                this.disipline(act, npc, 10);
            }

        }
    }

    public String getPlayerRep() {
        return this.playerRep;
    }

    @Override
    public void setRoom(Room room) {
        room.addNPC(this);
        this.room = room;
    }

    public int getSkillLevel(Skill skill) {
        return skillLevels.get(skill);
    }

    public void setSuspicion(int suspicion, String reason) {
        if (this.getType().containsKey(NPCType.ADULT)) {
            GameHandler.getGui().display(this.getName() + " is suspicious of you", "black");
            this.suspicion += 1;
        } else {
            GameHandler.getGui().display(this.getName() + " is suspicious of you", "black");
        }
    }

    public void followPlayer() {
        this.getRoom().removeNPC(this);
        this.setRoom(Player.getRoom());
    }

    public final void initualizeSkills() {
        this.skillLevels.put(Skill.FINE_MOTOR, 1);
        this.skillLevels.put(Skill.GROSS_MOTOR, 1);
        this.skillLevels.put(Skill.SOCIAL, 1);
        this.skillLevels.put(Skill.EMOTIONAL, 1);
        this.skillLevels.put(Skill.IMAGINATION, 1);
        this.skillLevels.put(Skill.LEARNING, 1);
    }

    public void setType(NPCType type) {
        this.types.put(type, true);
    }

    public void setFaction(boolean faction) {
        this.faction = faction;
    }

    public void setConfederate(boolean confederate) {
        this.confederate = confederate;
    }

    public void setPlayerRep(String playerRep) {
        this.playerRep = playerRep;
    }

    public boolean isLoyalist() {
        return faction;
    }

    public boolean isConfederate() {
        return confederate;
    }

    public int getSuspicion() {
        return suspicion;
    }

    public void setAlignment(String string) {
        this.alignment = string;
    }

    public String getAlignment() {
        return alignment;
    }

    public boolean isFollower() {
        return follower;
    }

    public String getResponse(String type, String act) {
        switch (type) {
            case "persuasion" -> {
                switch (act.toLowerCase()) {
                    case "cry" -> {
                        if (this.pRep.get(1) > 0) {
                            return this.getName() + " is moved by your tears";
                        } else {
                            return this.getName() + " is not moved by your tears";
                        }
                    }
                    case "pout" -> {
                        if (this.pRep.get(1) > 0) {
                            return this.getName() + " is moved by your pout";
                        } else {
                            return this.getName() + " is not moved by your pout";
                        }
                    }
                    case "silly" -> {
                        if (this.pRep.get(1) > 0) {
                            return this.getName() + " is moved by your silliness";
                        } else {
                            return this.getName() + " is not moved by your silliness";
                        }
                    }
                    case "mediate" -> {
                        if (this.pRep.get(2) > 0) {
                            return this.getName() + " is willing to do as you ask";
                        } else {
                            return this.getName() + " is not willing to do as you ask";
                        }
                    }
                    default -> {
                        return "I am not sure what you are asking";
                    }
                }
            }
            case "question" -> {
                switch (act.toLowerCase()) {
                    case "name" -> {
                        GameHandler.getGui().display(this.getName() + ": I am " + this.getName(), "black");
                    }
                    case "age" -> {
                        if (this.getAge() == 0) {
                            GameHandler.getGui().display(this.getName() + ": I am an Adult", "black");
                        } else {
                            GameHandler.getGui().display(this.getName() + ": I am " + this.getAge() + " years old", "black");
                        }
                    }
                    case "like" -> {
                        GameHandler.getGui().display(this.getName() + ": I like " + this.getLikes(), "black");
                    }
                    case "dislike" -> {
                        GameHandler.getGui().display(this.getName() + ": I dislike " + this.getDislikes(), "black");
                    }
                    default -> {
                        GameHandler.getGui().display("I am not sure what you are asking", "black");
                    }
                }
            }
            case "statement" -> {
                switch (act.toLowerCase()) {
                    case "like" -> {
                        return "Hello, I am " + this.getName();
                    }
                    case "dislike" -> {
                        return "Goodbye, I am " + this.getName();
                    }
                    case "nice" -> {
                        return "Awwe, thank you";
                    }
                    case "mean" -> {
                        return "Only when I have to be";
                    }
                    default -> {
                        return "I am not sure what you are asking";
                    }
                }
            }
            case "greeting" -> {
                return "Hello, I am " + this.getName();
            }
            case "farewell" -> {
                return "Goodbye, I am " + this.getName();
            }
            case "good morning" -> {
                return "Good morning, I am " + this.getName();
            }
            case "good night" -> {
                return "Good night, I am " + this.getName();
            }
            default -> {
                return "I am not sure what you are asking";
            }
        }
        return "I am not sure what you are asking";
    }

    private String getLikes() {
        return "I like well behaved children";
    }

    private String getDislikes() {
        return "I dislike misbehaving children";
    }

    public Map<Integer, Double> getpRep() {
        return pRep;
    }

    public void setFollower(boolean follower) {
        this.follower = follower;
    }

    public int getAge() {
        if (this.npcAge == 0) {
            this.npcAge = 0;
        }
        return this.npcAge;
    }

    public void tattle(String act) {
        for (NPC npc : this.getRoom().getNPCs()) {
            if (npc.getType().containsKey(NPCType.ADULT)) {
                GameHandler.getGui().display("You are caught by " + this.getName() + " while " + act, "black");
                this.disipline(act, npc, 10);
            }
        }
    }

    private void disipline(String act, NPC npc, int i) {
        switch (act.toLowerCase()) {
            case "stealing" -> {
                GameHandler.getGui().display(this.getName() + " takes the item from you and returns it to its owner", "black");
                GameHandler.playerTimeOut(i, act, npc);
            }
            case "pranking" -> {
                GameHandler.getGui().display(this.getName() + " takes the item from you and returns it to its owner", "black");
                GameHandler.playerTimeOut(i, act, npc);
            }
            case "vandalism" -> {
                GameHandler.getGui().display(this.getName() + " takes the item from you and returns it to its owner", "black");
                GameHandler.playerTimeOut(i, act, npc);
            }
            case "climbed" -> {
                GameHandler.getGui().display(this.getName() + " takes the item from you and returns it to its owner", "black");
                GameHandler.playerTimeOut(i, act, npc);
            }
            case "skipped" -> {
                GameHandler.getGui().display(this.getName() + " takes the item from you and returns it to its owner", "black");
                GameHandler.playerTimeOut(i, act, npc);
            }
            case "trespassing" -> {
                GameHandler.getGui().display(this.getName() + " takes the item from you and returns it to its owner", "black");
                GameHandler.playerTimeOut(i, act, npc);
            }
            case "sneaking" -> {
                GameHandler.getGui().display(this.getName() + " takes the item from you and returns it to its owner", "black");
                GameHandler.playerTimeOut(i, act, npc);
            }

        }
    }

    HashMap<NPCType, Boolean> getType() {
        return types;
    }

    public void adjustPlayerRep(double i, double i0, double i1, double i2) {
        DecimalFormat df = new DecimalFormat("#.#");
        for (Map.Entry<Integer, Double> entry : this.pRep.entrySet()) {
            int key = entry.getKey();
            double value = entry.getValue();
            if (key == 0) {
                value *= i * 1.1;
            }
            if (key == 1) {
                value *= i0 * 1.1;
            }
            if (key == 2) {
                value *= i1 * 1.1;
            }
            if (key == 3) {
                value *= i2 * 1.1;
            }
            this.pRep.put(key, value);
        }
        GameHandler.getGui().display("Your reputation with " + this.getName() + " has changed", "black");
        GameHandler.getGui().display("Rebelion/Respect: " + df.format(this.pRep.get(0)), "black");
        GameHandler.getGui().display("Pretentious/Authentic: " + df.format(this.pRep.get(1)), "black");
        GameHandler.getGui().display("Pragmatic/Idealistic: " + df.format(this.pRep.get(2)), "black");
        GameHandler.getGui().display("Leanient/Strict: " + df.format(this.pRep.get(3)), "black");
    }

    public void takeItem(Item item) {
        this.addItem(item);
        GameHandler.removeItemFromRoom(item);

    }

    public void playedWith(Item toy) {
        if (!this.getType().containsKey(NPCType.REJUVE)) {
            GameHandler.getGui().display(this.getName() + " is playing with " + toy.getName() + " with you.", "black");
        } else {
            GameHandler.getGui().display(this.getName() + " is not interested in playing with you", "black");
        }
    }

    public void trade(Item givenItem, String takenItem) {
        for (Item item : this.getInventory()) {
            if (item.getName().equals(takenItem) && Math.abs(item.getPrice() - givenItem.getPrice()) <= 1) {
                this.removeItem(item);
                this.addItem(givenItem);
                Player.removeItem(givenItem);
                Player.addItem(item);
                GameHandler.getGui().display("You have traded " + givenItem.getName() + " for " + item.getName(), "black");
                break;
            } else {
                GameHandler.getGui().display("The trade was not successful", "black");
                break;
            }
        }
    }

    public String[] getItemChoices() {
        String[] items = new String[this.getInventory().size()];
        for (int i = 0; i < this.getInventory().size(); i++) {
            items[i] = this.getInventory().get(i).getName();
        }
        return items;
    }

    public void barter() {
        Random random = new Random();
        int num = random.nextInt(2);
        switch (num) {
            case 0 -> {
                GameHandler.getGui().display(this.getName() + " is not interested in bartering with you", "black");
            }
            case 1 -> {
                GameHandler.getGui().display(this.getName() + " is willing to barter with you", "black");
                GameHandler.getGui().display("What would you like to trade?", "black");
            }
        }
    }

    public void guidePlayer(Events event) {
        GameHandler.getGui().display(this.getName() + ": " + Player.getName() + "You need to go to the " + event.getRoom().getName() + " for " + event.getName(), "black");
        Player.beMoved(this, event);
    }

    public void movePlayer(String action, Events event) {
        switch (action) {

            case "Upsies!" -> {
                GameHandler.getGui().display(this.getName() + " picks you up", "black");
                Player.setStatus(PlayerStatus.CARRIED);
            }
            case "Downsies!" -> {
                GameHandler.getGui().display(this.getName() + " puts you down", "black");
                Player.removeStatus(PlayerStatus.CARRIED);
            }
            case "Hold Hand" -> {
                GameHandler.getGui().display(this.getName() + " takes your hand", "black");
                Player.setStatus(PlayerStatus.HOLDING_HANDS);
            }
            case "Refuse" -> {
                GameHandler.getGui().display(this.getName() + " takes a moment to react to your refusal", "black");
                if (event.getImportance() < 3) {
                    GameHandler.getGui().display(this.getName() + " is not happy with your refusal, but leaves you to miss out on " + event.getName(), "black");
                }
            }
        }
    }

    public void noticePlayer() {
        if (Player.getStatus().isEmpty()) {
            GameHandler.getGui().display(this.getName() + " does not notice anything", "black");
        }
        for (PlayerStatus status : Player.getStatus()) {
            switch (status) {
                case WET_CLOTHING -> {
                    noticePlayer("wetSelf");
                }
                case SMELLY -> {
                    noticePlayer("smell");
                }
                case DIRTY -> {
                    noticePlayer("dirty");
                }
                case TIRED -> {
                    noticePlayer("tired");
                }
                case UPSET -> {
                    noticePlayer("upset");
                }
                case DIRTY_FACE -> {
                    noticePlayer("dirty");
                }
                case DIRTY_HANDS -> {
                    noticePlayer("dirty");
                }
                case DIRTY_DIAPER -> {
                    noticePlayer("smell");
                }
                case CRYING -> {
                    noticePlayer("upset");
                }
                case HURT -> {
                    noticePlayer("hurt");
                }
                case DISOBEDIENT -> {
                    noticePlayer("disobedient");
                }
                case TANTRUM -> {
                    noticePlayer("tantrum");
                }
                case MELTDOWN -> {
                    noticePlayer("meltdown");
                }
                case JUBILANT -> {
                    noticePlayer("jubilant");
                }
                case CARRIED -> {
                    noticePlayer("carried");
                }
                case HOLDING_HANDS -> {
                    noticePlayer("holdingHands");
                }
                case DIRTY_CLOTHING -> {
                    noticePlayer("dirty");
                }
                default -> {
                }

            }
        }

    }

    public void noticePlayer(String reason) {
        String[] choices = {"Go With", "Ignore", "Ask Why", "Negotiate", "Refuse"};

        switch (reason) {
            case "wetSelf" -> {
                GameHandler.getGui().display(this.getName() + " notices you are wet", "black");
                GameHandler.getGui().display(this.getName() + ": Oh no, you're all wet! Let's get you nice and dry, okay? Come with me to the changing room.", "black");
                String choice = JOptionPane.showInputDialog(null, "What would you like to do?", "Notice", JOptionPane.DEFAULT_OPTION, null, choices, choices[0]).toString();
                switch (choice) {
                    case "Go With" -> {
                        GameHandler.getGui().display(this.getName() + " smiles and takes your hand. You go to the changing room together.", "black");
                        Player.removeStatus(PlayerStatus.WET_CLOTHING);
                        Player.removeStatus(PlayerStatus.WET_DIAPER);
                        Player.setRoom(GameHandler.getRoomByName("Changing_Room"));
                        dressPlayer();
                    }
                    case "Ignore" -> {
                        GameHandler.getGui().display(this.getName() + ": Silly, you're soaked! You can't just stay like that.", "black");
                        noticePlayer("wetSelf");
                    }
                    case "Ask Why" -> {
                        GameHandler.getGui().display(this.getName() + ": We don't want to stay in wet clothes, right? It's yucky and uncomfortable!", "black");
                        noticePlayer("wetSelf");
                    }
                    case "Negotiate" -> {
                        GameHandler.getGui().display(this.getName() + ": Hmm... how about this? I can help you, or you can try doing it yourself. What do you think?", "black");
                        noticePlayer("wetSelf");
                    }
                    case "Refuse" -> {
                        GameHandler.getGui().display(this.getName() + ": Oh dear... well, I can't leave you like this. Let's go, I’ll carry you.", "black");
                        GameHandler.getGui().display(this.getName() + " gently picks you up and takes you to the changing room.", "black");
                        Player.setStatus(PlayerStatus.CARRIED);
                        Player.removeStatus(PlayerStatus.WET_CLOTHING);
                        Player.removeStatus(PlayerStatus.WET_DIAPER);
                        Player.setRoom(GameHandler.getRoomByName("Changing_Room"));
                        dressPlayer();
                    }
                }
            }
            case "smell" -> {
                GameHandler.getGui().display(this.getName() + " sniffs the air and notices you smell bad.", "black");
                GameHandler.getGui().display(this.getName() + ": Uh-oh, I think someone needs a bath! Do you want to come with me and get all clean?", "black");
                String choice = JOptionPane.showInputDialog(null, "What would you like to do?", "Notice", JOptionPane.DEFAULT_OPTION, null, choices, choices[0]).toString();
                switch (choice) {
                    case "Go With" -> {
                        GameHandler.getGui().display(this.getName() + " smiles and leads you to the bathroom.", "black");
                        Player.setRoom(GameHandler.getRoomByName("Bathroom"));
                        // Bath interaction logic here
                    }
                    case "Ignore" -> {
                        GameHandler.getGui().display(this.getName() + ": Hmm, you really should take a bath. It’ll be quick, I promise!", "black");
                        noticePlayer("smell");
                    }
                    case "Ask Why" -> {
                        GameHandler.getGui().display(this.getName() + ": Because we don't want to be all stinky, do we? Baths make us feel fresh!", "black");
                        noticePlayer("smell");
                    }
                    case "Negotiate" -> {
                        GameHandler.getGui().display(this.getName() + ": How about we make it fun? You can bring your favorite toy and splash around!", "black");
                        noticePlayer("smell");
                    }
                    case "Refuse" -> {
                        GameHandler.getGui().display(this.getName() + ": Well, I can't let you stay stinky. Let’s get you clean, okay?", "black");
                        GameHandler.getGui().display(this.getName() + " gently takes your hand and leads you to the bathroom.", "black");
                        Player.setRoom(GameHandler.getRoomByName("Bathroom"));
                    }
                }
            }
            case "dirty" -> {
                GameHandler.getGui().display(this.getName() + " notices you are dirty.", "black");
                GameHandler.getGui().display(this.getName() + ": Oh my! You've got dirt all over! Let’s go wash up.", "black");
                String choice = JOptionPane.showInputDialog(null, "What would you like to do?", "Notice", JOptionPane.DEFAULT_OPTION, null, choices, choices[0]).toString();
                switch (choice) {
                    case "Go With" -> {
                        GameHandler.getGui().display(this.getName() + " takes your hand and leads you to wash up.", "black");
                        Player.setRoom(GameHandler.getRoomByName("Washroom"));
                        // Washing interaction logic here
                    }
                    case "Ignore" -> {
                        GameHandler.getGui().display(this.getName() + ": But you're all messy! We should clean up before continuing.", "black");
                        noticePlayer("dirty");
                    }
                    case "Ask Why" -> {
                        GameHandler.getGui().display(this.getName() + ": We don't want to get more dirt everywhere, right? Let's clean you up.", "black");
                        noticePlayer("dirty");
                    }
                    case "Negotiate" -> {
                        GameHandler.getGui().display(this.getName() + ": How about we wash just a little bit, and then you can go play?", "black");
                        noticePlayer("dirty");
                    }
                    case "Refuse" -> {
                        GameHandler.getGui().display(this.getName() + ": Well, I can't let you stay all messy. Let’s wash up, okay?", "black");
                        GameHandler.getGui().display(this.getName() + " gently takes you to the washroom.", "black");
                        Player.setRoom(GameHandler.getRoomByName("Washroom"));
                    }
                }
            }
            case "tired" -> {
                GameHandler.getGui().display(this.getName() + " notices you rubbing your eyes, looking sleepy.", "black");
                GameHandler.getGui().display(this.getName() + ": Oh no, someone looks very tired! How about a nice nap?", "black");
                String choice = JOptionPane.showInputDialog(null, "What would you like to do?", "Notice", JOptionPane.DEFAULT_OPTION, null, choices, choices[0]).toString();
                switch (choice) {
                    case "Go With" -> {
                        GameHandler.getGui().display(this.getName() + " tucks you in gently, whispering, 'Sweet dreams!'", "black");
                        Player.setRoom(GameHandler.getRoomByName("Nap_Room"));
                        // Nap interaction logic here
                    }
                    case "Ignore" -> {
                        GameHandler.getGui().display(this.getName() + ": You’ll feel much better after a nap! Let's rest for a bit.", "black");
                        noticePlayer("tired");
                    }
                    case "Ask Why" -> {
                        GameHandler.getGui().display(this.getName() + ": Because rest is important, silly! We want to have lots of energy for playing later!", "black");
                        noticePlayer("tired");
                    }
                    case "Negotiate" -> {
                        GameHandler.getGui().display(this.getName() + ": How about a short nap? Just a little one, and then we can play some more!", "black");
                        noticePlayer("tired");
                    }
                    case "Refuse" -> {
                        GameHandler.getGui().display(this.getName() + ": Well, I can’t let you stay up all tired. Let’s rest for a while.", "black");
                        GameHandler.getGui().display(this.getName() + " gently takes you to the nap room.", "black");
                        Player.setRoom(GameHandler.getRoomByName("Nap_Room"));
                    }
                }
            }
            case "hungry" -> {
                GameHandler.getGui().display(this.getName() + " hears your tummy rumbling.", "black");
                GameHandler.getGui().display(this.getName() + ": Someone’s tummy is growling! Let's get a snack, okay?", "black");
                String choice = JOptionPane.showInputDialog(null, "What would you like to do?", "Notice", JOptionPane.DEFAULT_OPTION, null, choices, choices[0]).toString();
                switch (choice) {
                    case "Go With" -> {
                        GameHandler.getGui().display(this.getName() + " leads you to the lunchroom for a snack.", "black");
                        Player.setRoom(GameHandler.getRoomByName("Lunchroom"));
                        // Snack interaction logic here
                    }
                    case "Ignore" -> {
                        GameHandler.getGui().display(this.getName() + ": But you're hungry! Come on, let's get something yummy!", "black");
                        noticePlayer("hungry");
                    }
                    case "Ask Why" -> {
                        GameHandler.getGui().display(this.getName() + ": Because we don't want a grumbly tummy, right? Let's fill it up!", "black");
                        noticePlayer("hungry");
                    }
                    case "Negotiate" -> {
                        GameHandler.getGui().display(this.getName() + ": How about a snack now and a special treat later? Sounds good, huh?", "black");
                        noticePlayer("hungry");
                    }
                    case "Refuse" -> {
                        GameHandler.getGui().display(this.getName() + ": Well, I can’t let you stay hungry! Let’s grab a bite.", "black");
                        GameHandler.getGui().display(this.getName() + " gently takes your hand and leads you to the lunchroom.", "black");
                        Player.setRoom(GameHandler.getRoomByName("Lunchroom"));
                    }
                }
            }
            case "nude" -> {
                GameHandler.getGui().display(this.getName() + " notices you are nude.", "black");
                GameHandler.getGui().display(this.getName() + ": Oh dear, you need to get dressed! Let's go to the changing room.", "black");
                String choice = JOptionPane.showInputDialog(null, "What would you like to do?", "Notice", JOptionPane.DEFAULT_OPTION, null, choices, choices[0]).toString();
                switch (choice) {
                    case "Go With" -> {
                        GameHandler.getGui().display(this.getName() + " takes your hand and leads you to the changing room.", "black");
                        Player.setRoom(GameHandler.getRoomByName("Changing_Room"));
                        dressPlayer();
                    }
                    case "Ignore" -> {
                        GameHandler.getGui().display(this.getName() + ": You can't stay like that! Let's get you dressed, okay?", "black");
                        noticePlayer("nude");
                    }
                    case "Ask Why" -> {
                        GameHandler.getGui().display(this.getName() + ": Because we don't want to be naked in public, right? Let's get you dressed.", "black");
                        noticePlayer("nude");
                    }
                    case "Negotiate" -> {
                        GameHandler.getGui().display(this.getName() + ": How about you pick out your favorite outfit? Then we can go play!", "black");
                        noticePlayer("nude");
                    }
                    case "Refuse" -> {
                        GameHandler.getGui().display(this.getName() + ": Well, I can’t let you stay undressed. Let’s get you dressed, okay?", "black");
                        GameHandler.getGui().display(this.getName() + " gently takes your hand and leads you to the changing room.", "black");
                        Player.setRoom(GameHandler.getRoomByName("Changing_Room"));
                        dressPlayer();
                    }
                }
            }
        }
    }

    public void dressPlayer() {
        if (!Player.getEquipment().containsKey("Underpants")) {
            Player.equip(new Equipment("Underwear", "Clean Underwear", "underpants"), "Underpants");
        }
        if (!Player.getEquipment().containsKey("Top")) {
            Player.equip(new Equipment("Uniform Shirt", "Clean Shirt", "Top"), "Top");
        }
        if (!Player.getEquipment().containsKey("Bottom")) {
            Player.equip(new Equipment("Uniform Pants", "Clean Pants", "Bottom"), "Bottom");
        }
        if (!Player.getEquipment().containsKey("Socks")) {
            Player.equip(new Equipment("Uniform Socks", "Clean Socks", "Socks"), "Socks");
        }
        if (!Player.getEquipment().containsKey("Shoes")) {
            Player.equip(new Equipment("Uniform Shoes", "Clean Shoes", "Shoes"), "Shoes");
        }

        GameHandler.getGui().display(this.getName() + " helps you get dressed", "black");
        GameHandler.getGui().display("removing all clothing related status effects", "black");
        if (Player.getStatus().contains(PlayerStatus.WET_CLOTHING)) {
            Player.removeStatus(PlayerStatus.WET_CLOTHING);
        }
        if (Player.getStatus().contains(PlayerStatus.WET_DIAPER)) {
            Player.removeStatus(PlayerStatus.WET_DIAPER);
        }
        if (Player.getStatus().contains(PlayerStatus.DIRTY_CLOTHING)) {
            Player.removeStatus(PlayerStatus.DIRTY_CLOTHING);
        }
        if (Player.getStatus().contains(PlayerStatus.DIRTY_DIAPER)) {
            Player.removeStatus(PlayerStatus.DIRTY_DIAPER);
        }
        GameHandler.getGui().display("emptying pockets", "black");
        if (Player.getEquipment().isEmpty()) {

        }
        for (Equipment equipment : Player.getEquipment().values()) {
            if (!equipment.needsChanged()) {
                continue;
            }
            String name = equipment.getName();
            equipment.emptyPockets(this);
            GameHandler.getGui().display("removing " + name, "black");
            if (equipment.getSlot().equals("underpants")) {
                switch (name) {
                    case "Diaper" -> {
                        Player.unequip(equipment);
                        Player.equip(new Equipment("Diaper", "A diaper for you, a baby :).", "underpants"), "Underpants");
                        GameHandler.getGui().display("You are now wearing a new diaper", "black");
                    }
                    case "Pull-Up" -> {
                        Player.unequip(equipment);
                        Player.equip(new Equipment("Diaper", "A diaper for you, a baby :).", "underpants"), "Underpants");
                        GameHandler.getGui().display("You are now wearing a diaper instead of training pants", "black");
                    }
                    case "Training Pants" -> {
                        Player.unequip(equipment);
                        Player.equip(new Equipment("Pull-Up", "A pull-up for you, almost a kid :P.", "underpants"), "Underpants");
                        GameHandler.getGui().display("Your training pants have been replaced with a pull-up", "black");
                    }
                    case "Underwear" -> {
                        Player.unequip(equipment);
                        Player.equip(new Equipment("Training Pants", "Training pants for you, a big kid.. kinda :P.", "underpants"), "Underpants");
                        GameHandler.getGui().display("Your underwear has been replaced with training pants", "black");
                    }
                    default -> {
                        Player.unequip(equipment);
                        Player.equip(new Equipment("Training Pants", "Training pants for you, a big kid.. kinda :P.", "underpants"), "Underpants");
                        GameHandler.getGui().display("Your " + name + " has been replaced with training pants", "black");

                    }
                }
                if (equipment.getSlot().equals("top")) {
                    Player.unequip(equipment);
                    Player.equip(new Equipment("Uniform Shirt", "Clean Shirt", "Top"), "Top");
                    GameHandler.getGui().display("You are now wearing a clean shirt", "black");
                }
                if (equipment.getSlot().equals("bottom")) {
                    Player.unequip(equipment);
                    Player.equip(new Equipment("Uniform Pants", "Clean Pants", "Bottom"), "Bottom");
                    GameHandler.getGui().display("You are now wearing clean pants", "black");
                }
                if (equipment.getSlot().equals("socks")) {
                    Player.unequip(equipment);
                    Player.equip(new Equipment("Uniform Socks", "Clean Socks", "Socks"), "Socks");
                    GameHandler.getGui().display("You are now wearing clean socks", "black");

                }
                if (equipment.getSlot().equals("shoes")) {
                    Player.unequip(equipment);
                    Player.equip(new Equipment("Uniform Shoes", "Clean Shoes", "Shoes"), "Shoes");
                    GameHandler.getGui().display("You are now wearing clean shoes", "black");
                }
            }

            this.displayInventory();
        }
    }

    void displayInventory() {
        for (Item item : this.getInventory()) {
            GameHandler.getGui().display(item.getName(), "black");
        }
    }

    public HashMap<NPCStatus, Boolean> getStatus() {
        return this.npcStatus;
    }

    public void addQuest(Quest quest) {
        this.quests.add(quest);

    }

    public ArrayList<Quest> getQuests() {
        return quests;
    }

    public void help() {
        for (Quest quest : this.getQuests()) {
            GameHandler.getGui().display(this.getName() + " needs help with " + quest.getName() + quest.getDescription(), "black");
        }
    }

    void setStatus(NPCStatus npcStatus, boolean b) {
        this.npcStatus.put(npcStatus, b);
    }

    int getMaturity() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void react(String performance) {
        GameHandler.getGui().display(this.getName() + " is impressed by your performance", "black");
        switch (this.getName()) {  
            case "Dr White" -> {
                switch (performance) {
                    case "puppets" -> {
                        if (this.getRoom().isMusic()) {
                            GameHandler.getGui().display("Dr. White: That's wonderful, " + Player.getName() + "! The way you brought the puppets to life was so creative. I could see how much thought you put into each part.", "black");
                        } else {
                            GameHandler.getGui().display("*Dr. White nods thoughtfully, impressed by your effort*", "black");
                        }
                    }
                    case "dance" -> {
                        if (this.getRoom().isMusic()) {
                            GameHandler.getGui().display("Dr. White: That was fantastic, " + Player.getName() + "! Your coordination is really improving.", "black");
                        } else {
                            GameHandler.getGui().display("Dr. White: Impressive, " + Player.getName() + ". You're getting more confident with every step!", "black");
                        }
                    }
                    case "pirate" -> {
                        if (Player.getSkillLevel(Skill.IMAGINATION) > 2) {
                            GameHandler.getGui().display("Dr. White: Wow, " + Player.getName() + "! You really got into character. Great imagination and social engagement.", "black");
                        } else {
                            GameHandler.getGui().display("Dr. White: You did a great job, " + Player.getName() + "! Keep practicing to unleash more creativity.", "black");
                        }
                    }
                    case "puzzle" -> {
                        if (Player.getSkillLevel(Skill.PROBLEM_SOLVING) > 2) {
                            GameHandler.getGui().display("Dr. White: Excellent work, " + Player.getName() + "! Your problem-solving skills are really coming along.", "black");
                        } else {
                            GameHandler.getGui().display("Dr. White: Good effort, " + Player.getName() + ". Problem-solving is a skill that grows over time.", "black");
                        }
                    }
                }
            }
            case "Ms Sagely" -> {
                switch (performance) {
                    case "puppets" -> {
                        if (this.getRoom().isMusic()) {
                            GameHandler.getGui().display("Ms. Sagely: That was wonderful, " + Player.getName() + "! You really brought the puppets to life with your imagination.", "black");
                        } else {
                            GameHandler.getGui().display("*Ms. Sagely smiles warmly, clearly pleased with your creativity*", "black");
                        }
                    }
                    case "dance" -> {
                        if (this.getRoom().isMusic()) {
                            GameHandler.getGui().display("Ms. Sagely: That was fantastic, " + Player.getName() + "! Your coordination and rhythm are really improving.", "black");
                        } else {
                            GameHandler.getGui().display("Ms. Sagely: Impressive, " + Player.getName() + ". You’re getting better with each move.", "black");
                        }
                    }
                    case "pirate" -> {
                        if (Player.getSkillLevel(Skill.IMAGINATION) > 2) {
                            GameHandler.getGui().display("Ms. Sagely: Wow, " + Player.getName() + "! You really got into the role. Your creativity is shining!", "black");
                        } else {
                            GameHandler.getGui().display("Ms. Sagely: You're doing great, " + Player.getName() + "! Keep working on that imagination.", "black");
                        }
                    }
                    case "puzzle" -> {
                        if (Player.getSkillLevel(Skill.PROBLEM_SOLVING) > 2) {
                            GameHandler.getGui().display("Ms. Sagely: Well done, " + Player.getName() + "! Your problem-solving skills are really improving.", "black");
                        } else {
                            GameHandler.getGui().display("Ms. Sagely: You're on the right track, " + Player.getName() + "! Keep practicing.", "black");
                        }
                    }
                }
            }
            case "Dawn" -> {
                switch (performance) {
                    case "puppets" -> {
                        if (this.getRoom().isMusic()) {
                            GameHandler.getGui().display("Dawn: That's really something, " + Player.getName() + "! I guess all those puppet shows weren’t wasted on me either. You’ve clearly put thought into this.", "black");
                        } else {
                            GameHandler.getGui().display("*Dawn smirks lightly, amused by your efforts*", "black");
                        }
                    }
                    case "dance" -> {
                        if (this.getRoom().isMusic()) {
                            GameHandler.getGui().display("Dawn: Look at you go, " + Player.getName() + "! Moving like you’ve done this before, just like me. Keep it up!", "black");
                        } else {
                            GameHandler.getGui().display("Dawn: Impressive, " + Player.getName() + ". Keep at it—practice makes perfect, believe me.", "black");
                        }
                    }
                    case "pirate" -> {
                        if (Player.getSkillLevel(Skill.IMAGINATION) > 2) {
                            GameHandler.getGui().display("Dawn: Yar, " + Player.getName() + "! You've got some real imagination. You might be the next great pirate... or just the next rejuve to think so!", "black");
                        } else {
                            GameHandler.getGui().display("Dawn: Not bad, " + Player.getName() + ". Creativity takes practice, and you’re getting there.", "black");
                        }
                    }
                    case "puzzle" -> {
                        if (Player.getSkillLevel(Skill.PROBLEM_SOLVING) > 2) {
                            GameHandler.getGui().display("Dawn: Nicely done, " + Player.getName() + "! You're sharpening those problem-solving skills, even if it feels like we’re growing up all over again.", "black");
                        } else {
                            GameHandler.getGui().display("Dawn: Good effort, " + Player.getName() + ". You’re improving, and patience is key.", "black");
                        }
                    }
                }
            }
        }
    }
}