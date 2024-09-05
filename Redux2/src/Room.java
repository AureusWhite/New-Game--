
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Room {

    public ArrayList<Item> inventory;
    private String name;
    private String description;
    public HashMap<String, Room> exits;
    private ArrayList<NPC> npcs;
    private ArrayList<Room> rooms;
    private boolean locked;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        this.npcs = new ArrayList<>();
        this.exits = new HashMap<>();
        this.inventory = new ArrayList<>();
        this.rooms = new ArrayList<>();
        npcs.add(new NPC("Nobody", "No one", this));
        inventory.add(new Item("Nothing", "Nothing"));

    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public ArrayList<Item> getInventory() {
        return this.inventory;
    }

    public void addItem(Item item) {
        this.inventory.add(item);
    }

    public void setExits(HashMap<String, Room> exits) {
        this.exits = exits;
    }

    public void setExit(String direction, Room room) {
        this.exits.put(direction, room);
    }

    public void removeExit(String direction) {
        this.exits.remove(direction);
    }

    public Room getExit(String direction) {
        return this.exits.get(direction);
    }

    public String[] getNPCChoises() {
        String[] npcs1 = new String[this.npcs.size()];
        for (int i = 0; i < this.npcs.size(); i++) {
            npcs1[i] = this.npcs.get(i).getName();
        }
        return npcs1;
    }

    public void addNPC(NPC npc) {
        this.npcs.add(npc);
    }

    public void removeNPC(NPC npc) {
        this.npcs.remove(npc);
    }

    public ArrayList<Item> getArrayInventory() {
        if (this.inventory != null) {
            return this.inventory;
        } else {
            ArrayList<Item> newInventory = new ArrayList<>();
            this.inventory = newInventory;
            return this.inventory;
        }
    }

    public Room getExitByName(String string) {
        for (String exit : exits.keySet()) {
            if (exit.equals(string)) {
                return exits.get(exit);
            }
        }
        return null;
    }

    public String[] getInventoryString() {
        if (this.getArrayInventory().isEmpty()) {
            return new String[]{"empty"};
        }
        String[] items = new String[this.getArrayInventory().size()];
        for (int i = 0; i < this.getArrayInventory().size(); i++) {
            items[i] = this.getArrayInventory().get(i).getName();
        }
        return items;
    }

    public String[] getItemChoises() {
        String[] items = new String[this.getArrayInventory().size()];
        for (int i = 0; i < this.getArrayInventory().size(); i++) {
            items[i] = this.getArrayInventory().get(i).getName();
        }
        return items;
    }

    public Item getItemByName(String string) {
        for (Item item : this.getArrayInventory()) {
            if (item.getName().equalsIgnoreCase(string)) {
                return item;
            }
        }
        return null;
    }

    public String[] getExitString() {
        StringBuilder exitsString = new StringBuilder();
        for (String exit : exits.keySet()) {
            exitsString.append(exit).append(" ");
        }
        return exitsString.toString().split(" ");
    }

    public void removeItem(Item item) {
        this.inventory.remove(item);
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setInventory(ArrayList<Item> inventory1) {
        this.inventory = inventory1;
    }

    public HashMap<String, Room> getExitS() {
        return this.exits;
    }

    public void addExit(Room room) {
        this.exits.put(room.getName(), room);

    }

    public String[] getExitChoises() {
        StringBuilder exitsString = new StringBuilder();
        for (String exit : exits.keySet()) {
            exitsString.append(exit).append(" ");
        }
        return exitsString.toString().split(" ");
    }

    public void removeExit(Room room) {
        this.exits.remove(room.getName());
    }

    public Room getExit(Room room) {
        return this.exits.get(room.getName());
    }

    public void setNPCs(ArrayList<NPC> npcs) {
        this.npcs = npcs;
    }

    public ArrayList<NPC> getNPCs() {
        return this.npcs;
    }

    public void setNPC(NPC npc) {
        this.npcs.add(npc);
    }

    public NPC getNPCByName(String npc) {
        for (NPC npcz : npcs) {
            if (npcz.getName().equals(npc)) {
                return npcz;
            }
        }
        return null;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public ArrayList<Room> getRooms() {
        return this.rooms;
    }

    public String[] getExits() {
        StringBuilder exitsString = new StringBuilder();
        for (String exit : exits.keySet()) {
            exitsString.append(exit).append(" ");
        }
        return exitsString.toString().split(" ");
    }

    public Item[] getContainers() {
        ArrayList<Item> containers = new ArrayList<>();
        if (this.getArrayInventory().isEmpty()) {
            return new Item[]{new Item("Nothing", "Nothing")};
        }
        for (Item item : this.getArrayInventory()) {
            if (item.getType() != null) {
                if (item.getType().equalsIgnoreCase("Container")) {
                    containers.add(item);
                }
            }
        }
        return containers.toArray(Item[]::new);
    }

    void initializeRoomFiles() { //initializes the room files and creates them if they don't exist already.
        this.setDescription(GameHandler.readFile(this.getName().concat("-desc")));
        File mainFile = new File(this.getName().concat(".txt"));
        File descFile = new File(this.getName().concat("-desc.txt"));

        if (!mainFile.exists()) {
            try {
                mainFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (!descFile.exists()) {
                try {
                    descFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
