package Redux2;
import java.util.HashMap;


public class NPC extends Character {
    private boolean faction;
    private boolean confederate;
    private String type;
    private int suspicion;
    private String playerRep = "Good";
    private boolean hasNewQuest;
    private String reason="";
    private String alignment;
    private HashMap<String, Quest> quests = new HashMap<>();
    private String dialog = "Hello, I am " + this.getName().replace("_", " ") + ".";

    public void reciveItem(Item item) {
        if(item.getType().equalsIgnoreCase("QuestItem")){
            GameHandler.getGui().display(this.getName() + " is happy to recive the " + item.getName(), "black");
            this.addItem(item);
            Player.removeQuest(GameHandler.getQuests().get("TidyUp"));
            this.setNewQuest(true);


        } else {
            GameHandler.getGui().display(this.getName() + " is not interested in the " + item.getName(), "black");
        }
}
    public void giveItemToPlayer(Item item) {
        Player.reciveItem(this, item);
        this.removeItem(item);
    }

    public NPC(String name, String description, Room room, String type) {
        super(name, description, room);
        this.type = type;
          
    }

    public String getDialog() {
        return dialog;
    }

    public void getPranked() {
        if(this.getType().equals("adult")){
            GameHandler.getGui().display(this.getName() + " is not amused by your prank", "black");
            Player.getPunished("pranking", 1);
        } else {
            GameHandler.getGui().display(this.getName() + " is not amused by your prank", "black");
        }   
    }

    public void caughtPlayer(String act) {
        String type1=this.getType();
        switch(type1){
            case "child" ->  {
                if(this.faction==confederate){
                    this.tattle(act);
                } else {
                    GameHandler.getGui().display(""+this.getName() +" notices you" + act + ", but does not seem to care", "black");
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

    private void tattle(String act) {
        for (NPC npc : this.getRoom().getNPCs()) {
            if(npc.getType().equals("adult")){
                GameHandler.getGui().display("You are caught by " + this.getName() + " while " + act, "black");
                this.disipline(act);
            }
        }
    }

    private void disipline(String act) {
        switch(act.toLowerCase()){
            case "stealing" -> {
            switch (this.playerRep) {
                case "Good" -> {GameHandler.getGui().display(this.getName()+": I will give you a second chance, but if you are caught again, you lose privilages", "black");
                setRep("Mischievous");
                }
            
                case "Mischievous" -> {
                    GameHandler.getGui().display(this.getName()+": You misbehaved before and I let it slide, but I will not tolerate it again", "black");
                    Player.getPunished(act,1);
                }
                case "Bad" -> {
                    GameHandler.getGui().display(this.getName()+": You again? I am getting tired of seeing your name on the discipline list", "black");
                    Player.getPunished(act,2);
                }
                default -> {
                }
            }
        }
            case "vandalism" -> {
            switch (playerRep) {
                case "Good" -> GameHandler.getGui().display(this.getName()+": I will give you a second chance, but if you are caught again, you lose privilages", "black");
                case "Mischievous" -> {
                    GameHandler.getGui().display(this.getName()+": You misbehaved before and I let it slide, but I will not tolerate it again", "black");
                    Player.getPunished(act,1);
                }
                case "Bad" -> {
                    GameHandler.getGui().display(this.getName()+": You again? I am getting tired of seeing your name on the discipline list", "black");
                    Player.getPunished(act,2);
                }
                default -> {
                }
            }
        }
            case "bullying" -> {
            switch (playerRep) {
                case "Good" -> GameHandler.getGui().display(this.getName()+": I will give you a second chance, but if you are caught again, you lose privilages", "black");
                case "Mischievous" -> {
                    GameHandler.getGui().display(this.getName()+": You misbehaved before and I let it slide, but I will not tolerate it again", "black");
                    Player.getPunished(act,1);
                }
                case "Bad" -> {
                    GameHandler.getGui().display(this.getName()+": You again? I am getting tired of seeing your name on the discipline list", "black");
                    Player.getPunished(act,2);
                }
                default -> {
                }
            }
        }
            case "cheating" -> {
            switch (playerRep) {
                case "Good" -> GameHandler.getGui().display(this.getName()+": I will give you a second chance, but if you are caught again, you lose privilages", "black");
                case "Mischievous" -> {
                    GameHandler.getGui().display(this.getName()+": You misbehaved before and I let it slide, but I will not tolerate it again", "black");
                    Player.getPunished(act,1);
                }
                case "Bad" -> {
                    GameHandler.getGui().display(this.getName()+": You again? I am getting tired of seeing your name on the discipline list", "black");
                    Player.getPunished(act,2);
                }
                default -> {
                }
            }
        }
            case "lying" -> {
            switch (playerRep) {
                case "Good" -> GameHandler.getGui().display(this.getName()+": I will give you a second chance, but if you are caught again, you lose privilages", "black");
                case "Mischievous" -> {
                    GameHandler.getGui().display(this.getName()+": You misbehaved before and I let it slide, but I will not tolerate it again", "black");
                    Player.getPunished(act,1);
                }
                case "Bad" -> {
                    GameHandler.getGui().display(this.getName()+": You again? I am getting tired of seeing your name on the discipline list", "black");
                    Player.getPunished(act,2);
                }
                default -> {
                }
            }
        }
            case "skipping" -> {
            switch (playerRep) {
                case "Good" -> GameHandler.getGui().display(this.getName()+": I will give you a second chance, but if you are caught again, you lose privilages", "black");
                case "Mischievous" -> {
                    GameHandler.getGui().display(this.getName()+": You misbehaved before and I let it slide, but I will not tolerate it again", "black");
                    Player.getPunished(act,1);
                }
                case "Bad" -> {
                    GameHandler.getGui().display(this.getName()+": You again? I am getting tired of seeing your name on the discipline list", "black");
                    Player.getPunished(act,2);
                }
                default -> {
                }
            }
        }
            case "fighting" -> {
            switch (playerRep) {
                case "Good" -> GameHandler.getGui().display(this.getName()+": I will give you a second chance, but if you are caught again, you lose privilages", "black");
                case "Mischievous" -> {
                    GameHandler.getGui().display(this.getName()+": You misbehaved before and I let it slide, but I will not tolerate it again", "black");
                    Player.getPunished(act,1);
                }
                case "Bad" -> {
                    GameHandler.getGui().display(this.getName()+": You again? I am getting tired of seeing your name on the discipline list", "black");
                    Player.getPunished(act,2);
                }
                default -> {
                }
            }
        }
            case "tresspassing" -> {
            switch (playerRep) {
                case "Good" -> GameHandler.getGui().display(this.getName()+": I will give you a second chance, but if you are caught again, you lose privilages", "black");
                case "Mischievous" -> {
                    GameHandler.getGui().display(this.getName()+": You misbehaved before and I let it slide, but I will not tolerate it again", "black");
                    Player.getPunished(act,1);
                }
                case "Bad" -> {
                    GameHandler.getGui().display(this.getName()+": You again? I am getting tired of seeing your name on the discipline list", "black");
                    Player.getPunished(act,2);
                }
                default -> {
                }
            }
        }
        default ->{
            break;
        }
    }
} 

    private String getType() {
return this.type;
    }

    public void setSuspicion() {
        if(this.getType().equals("adult")){
            GameHandler.getGui().display(this.getName() + " is suspicious of you", "black");
            this.suspicion += 1;
        } else {
            GameHandler.getGui().display(this.getName() + " is suspicious of you", "black");
        }
    }

    private void setRep(String reputation) {
        this.playerRep = reputation;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean hasNewQuest() {
        return hasNewQuest;
    }

public void giveQuest(Quest quest) {
    Player.addQuest(quest);
}
    public void setNewQuest(boolean hasNewQuest) {
        this.hasNewQuest = hasNewQuest;
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

    public void setHasNewQuest(boolean hasNewQuest) {
        this.hasNewQuest = hasNewQuest;
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

    public boolean getHasNewQuest() {
        return hasNewQuest;
    }

    public String getReason() {
        return reason;
    }

    public void setAlignment(String string) {
        this.alignment = string;
    }

    HashMap<String,Quest> getQuests() {
        return this.quests; 
    }

    public void setQuests(HashMap<String, Quest> quests) {
        this.quests = quests;
    }

    public String getAlignment() {
        return alignment;
    }

    public void addQuest(Quest tidyUp) {
        this.quests.put(tidyUp.getQuestName(), tidyUp);
    }
    public void giveQuestToPlayer(Quest quest){
        Player.addQuest(quest);
    }
    public void update() {
        cleanUpInventory();
        cleanUpQuests();
        String blargh=getPlayerRep();
        switch (blargh) {
            case "Bad" -> this.setDialog("I am not talking to you");
            case "Mischievous" -> this.setDialog("I am watching you");
            default -> this.setDialog("Hello, I am " + this.getName().replace("_", " ") + ".");
        }
    }

    private void setDialog(String string) {
        this.dialog = string;
    }

    private void cleanUpInventory() {
        for (Item item : this.getInventory()) {
            if(item.getType().equalsIgnoreCase("QuestItem")){
                this.removeItem(item);
            }
        }
    }

    private void cleanUpQuests() {
        for (Quest quest : this.quests.values()) {
            if(quest.isCompleted()){
                this.quests.remove(quest.getQuestName());
            }
        }
    }
}
