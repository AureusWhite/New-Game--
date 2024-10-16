package Redux2;

import java.util.ArrayList;
import java.util.HashMap;

public class Quest {

    private final String name;
    private final String description;
    private ArrayList<Item> reward;
    private final int difficulty;
    private ArrayList<Item> requiredItems;
    public void setRequiredItems(ArrayList<Item> requiredItems) {
        this.requiredItems = requiredItems;
    }

    private ArrayList<Room> requiredRooms;
    public void setRequiredRooms(ArrayList<Room> requiredRooms) {
        this.requiredRooms = requiredRooms;
    }

    private ArrayList<NPC> requiredNPCs;
    public ArrayList<NPC> getRequiredNPCs() {
        return requiredNPCs;
    }

    public void setRequiredNPCs(ArrayList<NPC> requiredNPCs) {
        this.requiredNPCs = requiredNPCs;
    }

    private HashMap<String,Boolean> requiredConditions;


    public Quest(String name, String description,int difficulty) {
        this.name = name;
        this.description = description;
        this.difficulty = difficulty;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<Item> getReward() {
        return reward;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public ArrayList<Item> getRequiredItems() {
        return requiredItems;
    }

    public ArrayList<Room> getRequiredRooms() {
        return requiredRooms;
    }

    public boolean checkCompleted() {
        return requiredItems.isEmpty() || requiredRooms.isEmpty();
    }

    public void turnInQuest() {
        if (Player.getHands().contains(requiredItem())) {
            removeRequiredItem(requiredItem());
        } else {
            System.out.println("You do not have the required item to turn in this quest.");
        }
    }

    Item requiredItem() {
        Item item = null;
        for (int i = 0; i < requiredItems.size(); i++) {
            item = requiredItems.get(i);
        }
        return item;
    }

    public void removeRequiredItem(Item item) {
        requiredItems.remove(item);
        if (requiredItems.isEmpty()) {
            checkCompleted();
        }
    }

    public void removeRequiredRoom(Room room) {
        requiredRooms.remove(room);
        if (requiredRooms.isEmpty()) {
            checkCompleted();
        }
    }

    public void addRequiredItem(Item item) {
        requiredItems.add(item);
    }

    public void addRequiredRoom(Room room) {
        requiredRooms.add(room);
    }

    public void addReward(Item item) {
        reward.add(item);
    }

    public void removeReward(Item item) {
        reward.remove(item);
    }

    public void completeQuest() {
        if (checkCompleted()) {
            for (int i = 0; i < reward.size(); i++) {
                Player.addItem(reward.get(i));
            }
        }
    }

    public void removeQuest() {
        if (checkCompleted()) {
            for (int i = 0; i < reward.size(); i++) {
                Player.removeItem(reward.get(i));
            }
        }
    }

    public void addQuest() {
        if (checkCompleted()) {
            for (int i = 0; i < reward.size(); i++) {
                Player.addItem(reward.get(i));
            }
        }
    }

    public void checkQuest() {
        if (checkCompleted()) {
            completeQuest();
        }
    }

    public void checkQuests() {
        for (int i = 0; i < Player.getQuests().size(); i++) {
            Player.getQuests().get(i).checkQuest();
        }
    }

    public void removeQuests() {
        for (int i = 0; i < Player.getQuests().size(); i++) {
            Player.getQuests().get(i).removeQuest();
        }
    }

    public void addQuests() {
        for (int i = 0; i < Player.getQuests().size(); i++) {
            Player.getQuests().get(i).addQuest();
        }
    }

    public void addRequiredItems() {
        for (int i = 0; i < Player.getQuests().size(); i++) {
            Player.getQuests().get(i).addRequiredItem(requiredItem());
        }
    }

    public void removeRequiredItems() {
        for (int i = 0; i < Player.getQuests().size(); i++) {
            Player.getQuests().get(i).removeRequiredItem(requiredItem());
        }
    }

    public void addRequiredRooms() {
        for (int i = 0; i < Player.getQuests().size(); i++) {
            Player.getQuests().get(i).addRequiredRoom(requiredRooms.get(i));
        }
    }

    public void removeRequiredRooms() {
        for (int i = 0; i < Player.getQuests().size(); i++) {
            Player.getQuests().get(i).removeRequiredRoom(requiredRooms.get(i));
        }
    }

    public void addRewards() {
        for (int i = 0; i < Player.getQuests().size(); i++) {
            Player.getQuests().get(i).addReward(reward.get(i));
        }
    }

    public void removeRewards() {
        for (int i = 0; i < Player.getQuests().size(); i++) {
            Player.getQuests().get(i).removeReward(reward.get(i));
        }
    }

    public void completeQuests() {
        for (int i = 0; i < Player.getQuests().size(); i++) {
            Player.getQuests().get(i).completeQuest();
        }
    }

    public void checkQuestsCompleted() {
        for (int i = 0; i < Player.getQuests().size(); i++) {
            Player.getQuests().get(i).checkCompleted();
        }
    }

    public void removeQuestsCompleted() {
        for (int i = 0; i < Player.getQuests().size(); i++) {
            Player.getQuests().get(i).removeQuest();
        }
    }

    public void addQuestsCompleted() {
        for (int i = 0; i < Player.getQuests().size(); i++) {
            Player.getQuests().get(i).addQuest();
        }
    }

    public void addRequiredNPC(NPC requiredNPC) {
        this.requiredNPCs.add(requiredNPC);
    }

    public void addRequiredConditon(String condition, boolean b) {
        this.requiredConditions.put(condition, b);
    }

    public HashMap<String,Boolean> getRequiredConditions() {
        return requiredConditions;
    }
    public void toggleCondition(String condition) {
        if (requiredConditions.containsKey(condition)) {
            requiredConditions.put(condition, !requiredConditions.get(condition));
        }
    }
    public void checkConditions() {
        for (String condition : requiredConditions.keySet()) {
            if (!requiredConditions.get(condition)) {
                return;
            }
        }
        checkCompleted();
    }
    public void removeCondition(String condition) {
        requiredConditions.remove(condition);
    }
    public void addCondition(String condition) {
        requiredConditions.put(condition, false);
    }
}
