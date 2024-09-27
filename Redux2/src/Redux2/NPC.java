package Redux2;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class NPC extends Character {

    public static void followPlayer(NPC npc) {
        Player.setAge(11);
        if (Player.getAge() > npc.getAge() + 3) {
            Player.setLeader(true);
            npc.follower = true;
            GameHandler.getGui().display(npc.getName() + " is following you", "black");
            npc.setRoom(Player.getRoom());
        } else {
            GameHandler.getGui().display(npc.getName() + " is not interested in following you", "black");
        }
    }
    private int npcAge;
    private boolean faction;
    private final Map<Integer, Double> pRep = new HashMap<>();
    private boolean confederate;
    private String type;
    private int suspicion;
    private String playerRep = "Good";
    private String alignment;
    boolean follower;
    private Quest quest;

    public NPC(String name, String description, Room room, String type) {
        super(name, description, room);
        this.type = type;
        this.pRep.put(0, 1.33);
        this.pRep.put(1, 1.33);
        this.pRep.put(2, 1.33);
        this.pRep.put(3, 1.33);
    }

    public int getNpcAge() {
        return npcAge;
    }

    public void setNpcAge(int npcAge) {
        this.npcAge = npcAge;
    }

    public void reciveItem(Item item) {
        this.addItem(item);
        Player.removeItem(item);
        if(item.equals(this.getQuest().getRequiredItem())){
            this.getQuest().setCompleted(true);
            GameHandler.getGui().display("You have completed the quest", "black");
            this.setQuest(null);
            this.listQuests();
        }
    }

    public void giveItemToPlayer(Item item) {
        Player.addItem(item);
        this.removeItem(item);
    }

    public String getDialog() {
        GameHandler.fileSection2 = "Hello, I am " + this.getName().replace("_", " ");
        GameHandler.fileSection3 = "Good afternoon, I am " + this.getName().replace("_", " ");
        GameHandler.fileSection4 = "Good evening, I am " + this.getName().replace("_", " ");
        GameHandler.readFile(this.getName());
        switch (GameHandler.getClock().getTimeOfDay()) {
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
        if (this.getType().equals("adult")) {
            Random random = new Random();
            int num = random.nextInt(2);
            switch (num) {
                case 0 -> {
                    GameHandler.getGui().display("You walk up and place a pickle into " + this.getName() + "'s pocket.", "black");
                    Player.getPunished("pranking", 1, this, "Time out");
                    break;
                }
                case 1 -> {
                    GameHandler.getGui().display("You put a plastic spider into " + this.getName() + "'s hair.", "black");
                    Player.getPunished("pranking", 1, this, "Time out");
                    break;
                }
            }
            GameHandler.playerTimeOut(10, "Pranking", this);
            GameHandler.getGui().display(this.getName() + " is not amused by your prank", "black");
        } else {
            GameHandler.getGui().display(this.getName() + " is not amused by your prank", "black");
        }
    }

    public void caughtPlayer(String act) {
        String type1 = this.getType();
        switch (type1) {
            case "child" -> {
                if (this.faction == confederate) {
                    this.tattle(act);
                } else {
                    GameHandler.getGui().display("" + this.getName() + " notices you" + act + ", but does not seem to care", "black");
                }
            }
            case "adult" -> {
                GameHandler.getGui().display("You are caught by " + this.getName() + " while " + act, "black");
                this.disipline(act, this, 10);
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

    public void setSuspicion(int suspicion, String reason) {
        if (this.getType().equals("adult")) {
            GameHandler.getGui().display(this.getName() + " is suspicious of you", "black");
            this.suspicion += 1;
        } else {
            GameHandler.getGui().display(this.getName() + " is suspicious of you", "black");
        }
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setFaction(boolean faction) {
        this.faction = faction;
    }

    public void setConfederate(boolean confederate) {
        this.confederate = confederate;
    }

    public void setSuspicion(int suspicion) {
        this.suspicion = suspicion;
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

    public Quest getQuest() {
        return quest;
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

    int getAge() {
        if (this.npcAge == 0) {
            this.npcAge = 0;
        }
        return this.npcAge;
    }

    void setQuest(Quest quest1) {
        this.quest = quest1;
    }

    private void tattle(String act) {
        for (NPC npc : this.getRoom().getNPCs()) {
            if (npc.getType().equals("adult")) {
                GameHandler.getGui().display("You are caught by " + this.getName() + " while " + act, "black");
                this.disipline(act,npc,10);
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
            case "fighting" -> {
                GameHandler.getGui().display(this.getName() + " takes the item from you and returns it to its owner", "black");
                GameHandler.playerTimeOut(i, act, npc);
            }
            case "lying" -> {
                GameHandler.getGui().display(this.getName() + " takes the item from you and returns it to its owner", "black");
                GameHandler.playerTimeOut(i, act, npc);
            }
            case "cheating" -> {
                GameHandler.getGui().display(this.getName() + " takes the item from you and returns it to its owner", "black");
                GameHandler.playerTimeOut(i, act, npc);
            }
        }
    }

    private String getType() {
        return this.type;
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

    void persuade(Ability ability) {
        switch (ability) {
            case CRY -> {
                if (this.pRep.get(1) > 0) {
                    GameHandler.getGui().display(this.getName() + ": Has heard your cries and is comming to help you.", "black");
                } else {
                    GameHandler.getGui().display(this.getName() + ": is not moved by your tears", "black");
                }
            }
            case POINT -> {
                if (this.pRep.get(0) > 0) {
                    GameHandler.getGui().display(this.getName() + ": is willing to give you the object you pointed at.", "black");
                } else {
                    GameHandler.getGui().display(this.getName() + ": is not willing to give you the object you pointed at.", "black");
                }
            }
            case NAME -> {
                if (this.pRep.get(0) > 0) {
                    GameHandler.getGui().display(this.getName() + ": is willing to give you the object you named.", "black");
                } else {
                    GameHandler.getGui().display(this.getName() + ": is not willing to give you the object you named.", "black");
                }
            }
            case ASK -> {
                if (this.pRep.get(0) > 0) {
                    GameHandler.getGui().display("You ask nicely and " + this.getName() + " is willing to do as you ask", "black");
                } else {
                    GameHandler.getGui().display("You ask nicely and " + this.getName() + " is not willing to do as you ask", "black");
                }
            }
            case NEGOTIATE -> {
                if (this.pRep.get(2) > 0) {
                    GameHandler.getGui().display("You try to negotiate with " + this.getName() + " and they are willing to do as you ask", "black");
                } else {
                    GameHandler.getGui().display("You try to negotiate with " + this.getName() + " and they are not willing to do as you ask", "black");
                }
            }
            case MEDIATE -> {
                if (this.pRep.get(2) > 0) {
                    GameHandler.getGui().display("You attempt to mediate the situation and " + this.getName() + " is willing to do as you ask", "black");
                } else {
                    GameHandler.getGui().display("You attempt to mediate the situation and " + this.getName() + " is not willing to do as you ask", "black");
                }
            }

            default -> {
            }
        }

    }

    void askForItem(Item item) {
        if (pRep.get(0) > 0) {
            GameHandler.getGui().display(this.getName() + " is willing to give you " + item.getName(), "black");
        }
        this.takeItem(item);
        giveItemToPlayer(item);
    }

    private void takeItem(Item item) {
        this.addItem(item);
        GameHandler.removeItemFromRoom(item);

    }

    private void listQuests() {
        if (this.quest != null) {
            GameHandler.getGui().display(this.getName() + " has a quest for you", "black");
            GameHandler.getGui().display(this.quest.getName(), "black");
        } else {
            GameHandler.getGui().display(this.getName() + " has no quests for you", "black");
        }
    }

    void playedWith(Item toy) {
        if (!this.getType().equals("child")) {
            GameHandler.getGui().display(this.getName() + " is playing with " + toy.getName()+" with you.", "black");
        } else {
            GameHandler.getGui().display(this.getName() + " is not interested in playing with you", "black");
        }
    }
}
