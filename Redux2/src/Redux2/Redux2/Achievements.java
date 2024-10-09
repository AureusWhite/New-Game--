package Redux2;

import java.util.HashMap;
import java.util.HashSet;

public class Achievements {
    private final String name;
    private final String description;
    private boolean unlocked;
    private final int points;
    private final HashMap<Item, Boolean> requiredItems = new HashMap<>();
    private final HashMap<Room, Boolean> requiredPlaces = new HashMap<>();
    private final HashMap<String, Boolean> requiredStatus = new HashMap<>();
    private final HashSet<Achievements> requiredAchievements = new HashSet<>();
    private final HashMap<NPC, Boolean> requiredNPC = new HashMap<>();
    private final HashMap<Equipment, Boolean> requiredEquipment = new HashMap<>();

    // Constructor for setting achievement details
    public Achievements(String name, String description, int points) {
        this.name = name;
        this.description = description;
        this.points = points;
        this.unlocked = false;
    }

    // Getter for the achievement name
    public String getName() {
        return name;
    }

    // Getter for the achievement description
    public String getDescription() {
        return description;
    }

    // Checks if the achievement is unlocked
    public boolean isUnlocked() {
        return unlocked;
    }

    // Set the achievement as unlocked
    public void setUnlocked(boolean unlocked) {
        this.unlocked = unlocked;
    }

    // Getter for the achievement points
    public int getPoints() {
        return points;
    }

    // Method to check if all conditions for the achievement are met
    public boolean checkAchievements() {
        // Check if all required places are visited
        for (Boolean visited : requiredPlaces.values()) {
            if (!visited) {
                return false;
            }
        }
        // Check if all required equipment is equipped
        for (Boolean equipped : requiredEquipment.values()) {
            if (!equipped) {
                return false;
            }
        }
        // Check if all required items are interacted with
        for (Boolean interacted : requiredItems.values()) {
            if (!interacted) {
                return false;
            }
        }
        // Check if all required statuses are met
        for (Boolean status : requiredStatus.values()) {
            if (!status) {
                return false;
            }
        }
        return true;
    }

    // Method to update the achievement when a condition is met
    public void updateAchievement() {
        if (checkAchievements()) {
            this.setUnlocked(true);
            System.out.println("Achievement unlocked: " + this.name);
        }
    }
    // Mark a room as visited
    public void markRoomVisited(Room room) {
        if (requiredPlaces.containsKey(room)) {
            requiredPlaces.put(room, true);
            updateAchievement();
        }
    }
    public  void markEquipmentEquipped(Equipment equipment) {
        if (requiredEquipment.containsKey(equipment)) {
            requiredEquipment.put(equipment, true);
            updateAchievement();
        }
    }

    // Mark an item as interacted with (like opening a PAW figure)
    public void openItem(Item item) {
        if (requiredItems.containsKey(item)) {
            requiredItems.put(item, true);
            updateAchievement();
        }
    }

    // Add required items for an achievement (e.g., a specific collectible like a PAW figure)
    public void addRequiredItem(Item item) {
        requiredItems.put(item, false);  // initially set to false
    }

    // Add required places for an achievement (e.g., visit a specific room)
    public void addRequiredPlace(Room room) {
        requiredPlaces.put(room, false);  // initially set to false
    }

    // Add required status for an achievement (e.g., a specific state like 'spoken to NPC')
    public void addRequiredStatus(String status) {
        requiredStatus.put(status, false);  // initially set to false
    }
    public void addRequiredEquipment(Equipment equipment) {
        requiredEquipment.put(equipment, false);
    }
    public void addRequiredNPC(NPC fuzzy) {
        requiredNPC.put(fuzzy, false);
    }

    public HashMap<NPC, Boolean> getRequiredNPC() {
        return requiredNPC;
    }

    public HashMap<Room,Boolean> getRequiredPlaces() {
        return requiredPlaces;
    }
    public HashMap<Item,Boolean>getRequiredItems(){
        return requiredItems;
    }

    public HashMap<Equipment,Boolean> getRequiredEquipment() {
        return requiredEquipment;
    }

    public HashSet<Achievements> getRequiredAchievements() {
        return requiredAchievements;
    }
}
