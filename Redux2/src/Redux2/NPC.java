package Redux2;

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
    int getAge() {
        return this.npcAge;
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

    public Quest getQuest() {
        return quest;
    }

    void setQuest(Quest quest1) {
        this.quest = quest1;
    }
}
