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
    private final HashMap<Equipment, Boolean> requiredEquipment = new HashMap<>();
    private final HashMap<NPC, NPCStatus> requiredNPC = new HashMap<>();

    public enum NPCStatus {
        NOT_SPOKEN_TO, SPOKEN_TO, FRIEND, ENEMY, ADULT, REJUVE, ANDRIOD, COMPANION
    }

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
        // Check if all required items have been collected
        for (Boolean collected : requiredItems.values()) {
            if (!collected) {
                return false;  // Not all items have been collected
            }
        }

        // Check if all required places have been visited
        for (Boolean visited : requiredPlaces.values()) {
            if (!visited) {
                return false;  // Not all places have been visited
            }
        }

        // Check if all required statuses have been met
        for (Boolean status : requiredStatus.values()) {
            if (!status) {
                return false;  // Not all statuses have been met
            }
        }

        // Check if all required achievements have been unlocked
        for (Achievements achievement : requiredAchievements) {
            if (!achievement.isUnlocked()) {
                return false;  // Not all achievements have been unlocked
            }
        }

        // Check if all required equipment has been equipped
        for (Boolean equipped : requiredEquipment.values()) {
            if (!equipped) {
                return false;  // Not all equipment has been equipped
            }
        }

        // Check if all required NPCs have been interacted with
        for (NPCStatus status : requiredNPC.values()) {
            if (status == NPCStatus.NOT_SPOKEN_TO) {
                return false;  // Not all NPC conditions are met
            }
        }

        return true;  // All conditions are met
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

    public void markEquipmentEquipped(Equipment equipment) {
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

    public void markItemCollected(Item item) {
        if (requiredItems.containsKey(item)) {
            requiredItems.put(item, true);
            updateAchievement();
        }
    }

    public void markNPCSpokenTo(NPC npc) {
        if (requiredNPC.containsKey(npc)) {
            requiredNPC.put(npc, NPCStatus.SPOKEN_TO);
            updateAchievement();
        }
    }

// Method to mark that the player has befriended an NPC
    public void markNPCBefriended(NPC npc) {
        if (requiredNPC.containsKey(npc)) {
            requiredNPC.put(npc, NPCStatus.FRIEND);
            updateAchievement();
        }
    }

// Method to mark that the player has become enemies with an NPC
    public void markNPCEnemy(NPC npc) {
        if (requiredNPC.containsKey(npc)) {
            requiredNPC.put(npc, NPCStatus.ENEMY);
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

    public void addRequiredNPC(NPC npc, NPCStatus status) {
        requiredNPC.put(npc, NPCStatus.NOT_SPOKEN_TO);  // Initially not spoken to
        requiredNPC.put(npc,status);  // Initially not spoken to
    } 

    public HashMap<NPC, NPCStatus> getRequiredNPC() {
        return requiredNPC; 
    }

    public HashMap<Room, Boolean> getRequiredPlaces() {
        return requiredPlaces;
    }

    public HashMap<Item, Boolean> getRequiredItems() {
        return requiredItems;
    }

    public HashMap<Equipment, Boolean> getRequiredEquipment() {
        return requiredEquipment;
    }

    public HashSet<Achievements> getRequiredAchievements() {
        return requiredAchievements;
    }

    public HashMap<String, Boolean> getRequiredStatus() {
        return requiredStatus;
    }
    public boolean checkNPCs() {
        for (NPCStatus status : requiredNPC.values()) {
            if (status == NPCStatus.NOT_SPOKEN_TO) {
                return false;  // Not all NPC conditions are met
            }
        }
        return true;  // All required NPCs have been interacted with in some way
    }
    public boolean checkNPCAchievements() {
        // Include other checks like places, items, etc.
        return checkNPCs() && checkAchievements();  // Combine with other checks
    }
    @Override
    public String toString() {
        return "Achievements [name=" + name + ", description=" + description + ", unlocked=" + unlocked + ", points="
                + points + ", requiredItems=" + requiredItems + ", requiredPlaces=" + requiredPlaces
                + ", requiredStatus=" + requiredStatus + ", requiredAchievements=" + requiredAchievements
                + ", requiredNPC=" + requiredNPC + ", requiredEquipment=" + requiredEquipment + "]";
    }
}
