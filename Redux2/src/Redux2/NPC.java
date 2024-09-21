package Redux2;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

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
    private int npcAge = 1;
    private boolean faction;
    private final Map<Integer, Double> pRep = new HashMap<>();
    private boolean confederate;
    private String type;
    private int suspicion;
    private String playerRep = "Good";
    private String reason = "";
    private String alignment;
    private String dialog = "Hello, I am " + this.getName().replace("_", " ") + ".";
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
    }

    public void giveItemToPlayer(Item item) {
        Player.reciveItem(this, item);
        this.removeItem(item);
    }

    public String getDialog() {
        return dialog;
    }

    public void getPranked() {
        if (this.getType().equals("adult")) {
            GameHandler.getGui().display(this.getName() + " is not amused by your prank", "black");
            Player.getPunished("pranking", 1);
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
                this.disipline(act);
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

    public void setSuspicion() {
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

    public void setReason(String reason) {
        this.reason = reason;
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

    public String getReason() {
        return reason;
    }

    public void setAlignment(String string) {
        this.alignment = string;
    }

    public String getAlignment() {
        return alignment;
    }

    public void update() {
        String blargh = getPlayerRep();
        switch (blargh) {
            case "Bad" ->
                this.setDialog("I am not talking to you");
            case "Mischievous" ->
                this.setDialog("I am watching you");
            default ->
                this.setDialog("Hello, I am " + this.getName().replace("_", " ") + ".");
        }
    }

    public boolean isFollower() {
        return follower;
    }

    public Quest getQuest() {
        return quest;
    }

    public String getResponse(String responce) {
        switch (responce) {
            case "Sarcastic" -> {
                switch (this.getName()) {
                    case "Ms Sagely" -> {
                        this.adjustPlayerRep(1.1, 1.1, 1.1, 1.1);
                        return "Ms Sagely: Young one, please do not be sarcastic with me, I have seen this behaviour before and it never ends well";
                    }
                    case "Dawn" -> {
                        this.adjustPlayerRep(-1, 1, 2, -2);
                        return "Dawn: Haha, very funny. I invented the bratty child act, you can't fool me";
                    }
                    case "Dr_White" -> {
                        this.adjustPlayerRep(1, -1, 1, -1);
                        return "Dr White: 'humm' I see *Writes in notebook*";
                    }
                    case "Susy" -> {
                        this.adjustPlayerRep(2, -1, 1, -2);
                        return "Susy: Why would you say that?";
                    }
                    case "Aang" -> {
                        this.adjustPlayerRep(1, -1, 1, -1);
                        return "Aang: *Adjust his spectacles* I am very familiar with the rhetorical technique of sarcasm. Your attempt is lackluster at best";
                    }
                    case "Taliber" -> {
                        this.adjustPlayerRep(-1, 1, 2, -2);
                        return "Taliber: *Rolls eyes*";
                    }
                    case "Aureus" -> {
                        this.adjustPlayerRep(2, -1, 1, -2);
                        return "Aureus: *laughs loudly and claps* \"Nice one, I like you\"";
                    }
                    case "Reaserch_Student M" -> {
                        this.adjustPlayerRep(-1, 1, 2, -2);
                        return "*Writes in notebook*";
                    }
                    case "Reaserch_Student F" -> {
                        this.adjustPlayerRep(1, -1, -2, 2);
                        return "*Writes in notebook*";
                    }
                    case "Casidy" -> {
                        return "Casidy: Whoa, I don't want any trouble from you. I am just trying to get by";
                    }
                    case "Farah" -> {
                        return "Farah: You what? Listen that attitude is not going to get you anywhere";
                    }
                    case "Danelle" -> {
                        return "Danelle: *Rolls eyes* seen it before kid, no one likes a smart ass";
                    }
                    case "Timmy" -> {
                        return "*Cries* You are a meanie";
                    }
                    case "Mrs_White" -> {
                        return "Mrs White: Have you spoken to my husband about that, I am sure he would love to hear all about it from you, I am sure you won't mind doing a few tests while you're in his office filing a complaint?";
                    }
                    default ->
                        throw new AssertionError();
                }
            }
            case "Nice" -> {
                return "Thank you";

            }
            case "Mean" -> {
                return "You are a jerk";
            }
            case "Neutral" -> {
                return "I am not sure how to respond to that";
            }
        }
        return "I am not sure how to respond to that";
    }

    public boolean isFaction() {
        return faction;
    }

    public Map<Integer, Double> getpRep() {
        return pRep;
    }

    public void setFollower(boolean follower) {
        this.follower = follower;
    }

    int getAge() {
        return this.npcAge;
    }

    void setQuest(Quest quest1) {
        this.quest = quest1;
    }

    private void tattle(String act) {
        for (NPC npc : this.getRoom().getNPCs()) {
            if (npc.getType().equals("adult")) {
                GameHandler.getGui().display("You are caught by " + this.getName() + " while " + act, "black");
                this.disipline(act);
            }
        }
    }

    private void disipline(String act) {
        switch (act.toLowerCase()) {
            case "stealing" -> {
                switch (this.playerRep) {
                    case "Good" -> {
                        GameHandler.getGui().display(this.getName() + ": I will give you a second chance, but if you are caught again, you lose privilages", "black");
                        setRep("Mischievous");
                    }

                    case "Mischievous" -> {
                        GameHandler.getGui().display(this.getName() + ": You misbehaved before and I let it slide, but I will not tolerate it again", "black");
                        Player.getPunished(act, 1);
                        setRep("Bad");
                    }
                    case "Bad" -> {
                        GameHandler.getGui().display(this.getName() + ": You again? I am getting tired of seeing your name on the discipline list", "black");
                        Player.getPunished(act, 2);
                    }
                    default -> {
                    }
                }
            }
            case "vandalism" -> {
                switch (playerRep) {
                    case "Good" -> {
                        GameHandler.getGui().display(this.getName() + ": I will give you a second chance, but if you are caught again, you lose privilages", "black");
                        setRep("Mischievous");
                    }
                    case "Mischievous" -> {
                        GameHandler.getGui().display(this.getName() + ": You misbehaved before and I let it slide, but I will not tolerate it again", "black");
                        Player.getPunished(act, 1);
                    }
                    case "Bad" -> {
                        GameHandler.getGui().display(this.getName() + ": You again? I am getting tired of seeing your name on the discipline list", "black");
                        Player.getPunished(act, 2);
                    }
                    default -> {
                    }
                }
            }
            case "sabotage" -> {
                switch (playerRep) {
                    case "Good" -> {
                        GameHandler.getGui().display(this.getName() + ": I will give you a second chance, but if you are caught again, you lose privilages", "black");
                        setRep("Mischievous");
                    }
                    case "Mischievous" -> {
                        GameHandler.getGui().display(this.getName() + ": You misbehaved before and I let it slide, but I will not tolerate it again", "black");
                        Player.getPunished(act, 1);
                        setRep("Bad");
                    }
                    case "Bad" -> {
                        GameHandler.getGui().display(this.getName() + ": You again? I am getting tired of seeing your name on the discipline list", "black");
                        Player.getPunished(act, 2);
                    }
                    default -> {
                    }
                }
            }
            case "bullying" -> {
                switch (playerRep) {
                    case "Good" -> {
                        GameHandler.getGui().display(this.getName() + ": I will give you a second chance, but if you are caught again, you lose privilages", "black");
                        setRep("Mischievous");
                    }
                    case "Mischievous" -> {
                        GameHandler.getGui().display(this.getName() + ": You misbehaved before and I let it slide, but I will not tolerate it again", "black");
                        Player.getPunished(act, 1);
                    }
                    case "Bad" -> {
                        GameHandler.getGui().display(this.getName() + ": You again? I am getting tired of seeing your name on the discipline list", "black");
                        Player.getPunished(act, 2);
                    }
                    default -> {
                    }
                }
            }
            case "cheating" -> {
                switch (playerRep) {
                    case "Good" -> {
                        GameHandler.getGui().display(this.getName() + ": I will give you a second chance, but if you are caught again, you lose privilages", "black");
                        setRep("Mischievous");
                    }
                    case "Mischievous" -> {
                        GameHandler.getGui().display(this.getName() + ": You misbehaved before and I let it slide, but I will not tolerate it again", "black");
                        Player.getPunished(act, 1);
                    }
                    case "Bad" -> {
                        GameHandler.getGui().display(this.getName() + ": You again? I am getting tired of seeing your name on the discipline list", "black");
                        Player.getPunished(act, 2);
                    }
                    default -> {
                    }
                }
            }
            case "lying" -> {
                switch (playerRep) {
                    case "Good" -> {
                        GameHandler.getGui().display(this.getName() + ": I will give you a second chance, but if you are caught again, you lose privilages", "black");
                        setRep("Mischievous");
                    }
                    case "Mischievous" -> {
                        GameHandler.getGui().display(this.getName() + ": You misbehaved before and I let it slide, but I will not tolerate it again", "black");
                        Player.getPunished(act, 1);
                    }
                    case "Bad" -> {
                        GameHandler.getGui().display(this.getName() + ": You again? I am getting tired of seeing your name on the discipline list", "black");
                        Player.getPunished(act, 2);
                    }
                    default -> {
                    }
                }
            }
            case "skipping" -> {
                switch (playerRep) {
                    case "Good" -> {
                        GameHandler.getGui().display(this.getName() + ": I will give you a second chance, but if you are caught again, you lose privilages", "black");
                        setRep("Mischievous");
                    }
                    case "Mischievous" -> {
                        GameHandler.getGui().display(this.getName() + ": You misbehaved before and I let it slide, but I will not tolerate it again", "black");
                        Player.getPunished(act, 1);
                    }
                    case "Bad" -> {
                        GameHandler.getGui().display(this.getName() + ": You again? I am getting tired of seeing your name on the discipline list", "black");
                        Player.getPunished(act, 2);
                    }
                    default -> {
                    }
                }
            }
            case "fighting" -> {
                switch (playerRep) {
                    case "Good" -> {
                        GameHandler.getGui().display(this.getName() + ": I will give you a second chance, but if you are caught again, you lose privilages", "black");
                        setRep("Mischievous");
                    }
                    case "Mischievous" -> {
                        GameHandler.getGui().display(this.getName() + ": You misbehaved before and I let it slide, but I will not tolerate it again", "black");
                        Player.getPunished(act, 1);
                    }
                    case "Bad" -> {
                        GameHandler.getGui().display(this.getName() + ": You again? I am getting tired of seeing your name on the discipline list", "black");
                        Player.getPunished(act, 2);
                    }
                    default -> {
                    }
                }
            }
            case "tresspassing" -> {
                switch (playerRep) {
                    case "Good" -> {
                        GameHandler.getGui().display(this.getName() + ": I will give you a second chance, but if you are caught again, you lose privilages", "black");
                        setRep("Mischievous");
                    }
                    case "Mischievous" -> {
                        GameHandler.getGui().display(this.getName() + ": You misbehaved before and I let it slide, but I will not tolerate it again", "black");
                        Player.getPunished(act, 1);
                        setRep("Bad");
                    }
                    case "Bad" -> {
                        GameHandler.getGui().display(this.getName() + ": You again? I am getting tired of seeing your name on the discipline list", "black");
                        Player.getPunished(act, 2);
                    }
                    default -> {
                    }
                }
            }
            default -> {
                break;
            }
        }
    }

    private String getType() {
        return this.type;
    }

    private void setRep(String reputation) {
        this.playerRep = reputation;
    }

    private void setDialog(String string) {
        this.dialog = string;
    }

    private void adjustPlayerRep(double i, double i0, double i1, double i2) {
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
}
