package Redux2;

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
    private boolean updated;
    private String type;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        this.npcs = new ArrayList<>();
        this.exits = new HashMap<>();
        this.inventory = new ArrayList<>();
        this.rooms = new ArrayList<>();
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
            return new String[]{};
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
            return new Item[]{};
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

    public void dance() {
        GameHandler.getGui().display("You danced", "Black");
    }

    public void pretend() {
        GameHandler.getGui().display("You pretended", "Black");
    }

    public void lead() {
        GameHandler.getGui().display("You led", "Black");
    }

    public void play(String selectedNPC, String selectedToy2) {
        NPC npc = this.getNPCByName(selectedNPC);
        Item toy = this.getItemByName(selectedToy2);
        if (npc == null) {
            GameHandler.getGui().display("That NPC does not exist", "Black");
        } else if (toy == null) {
            GameHandler.getGui().display("That toy does not exist", "Black");
        } else {
            GameHandler.getGui().display("You played with " + npc.getName() + " using " + toy.getName(), "Black");
            npc.playedWith(toy);
        }
        
    }

    public void prank() {
        String[] npcs1 = this.getNPCChoises();
        if (npcs1.length == 0) {
            GameHandler.getGui().display("There are no NPCs to prank", "Black");
            return;
        }
        GameHandler.getGui().display("Who do you want to prank?", "Black");
        String npc = GameHandler.getGui().getInput();
        NPC npc1 = this.getNPCByName(npc);
        if (npc1 == null) {
            GameHandler.getGui().display("That NPC does not exist", "Black");
        } else {
            GameHandler.getGui().display("You pranked " + npc1.getName(), "Black");
            npc1.getPranked();
        }

    }

    public void steal(String itemName) {
        Item takings = this.getItemByName(itemName);
        if (takings == null) {
            GameHandler.getGui().display("That item does not exist", "Black");
        } else {
            GameHandler.getGui().display("You attempt to steal " + takings.getName(), "Black");
            int outcome = (int) (Math.random() * 100);
            if (outcome < 1) {
                GameHandler.getGui().display("You successfully stole " + takings.getName(), "Black");
            } else {
                GameHandler.getGui().display("You failed to steal " + takings.getName(), "Black");
                this.getFirstNPC().caughtPlayer("Stealing");

            }
        }
    }

    public void sabotage(Item item) {
        GameHandler.getGui().display("What do you want to sabotage?", "Black");
        if (item == null) {
            GameHandler.getGui().display("That item does not exist", "Black");
        } else {
            GameHandler.getGui().display("You attempt to sabotage " + item.getName(), "Black");
            int outcome = (int) (Math.random() * 100);
            if (outcome < 50) {
                GameHandler.getGui().display("You successfully sabotaged " + item.getName(), "Black");
                breakItem(item);
            } else {
                GameHandler.getGui().display("You failed to sabotage " + item.getName(), "Black");
                this.getFirstNPC().caughtPlayer("sabotage");
            }
        }

    }

    public void vandalize(Item item) {
        if (item == null) {
            GameHandler.getGui().display("That item does not exist", "Black");
        } else {
            GameHandler.getGui().display("You attempt to vandalize " + item.getName(), "Black");
            int outcome = (int) (Math.random() * 100);
            if (outcome < 99) {
                GameHandler.getGui().display("You successfully vandalized " + item.getName(), "Black");
                vandalizeItem(item);
            } else {
                GameHandler.getGui().display("You failed to vandalize " + item.getName(), "Black");
                this.getFirstNPC().caughtPlayer("Vandalism");
            }
        }
    }

    public String[] getItemsByType(String string) {
        for (Item item : this.getArrayInventory()) {
            if (item.getType().equalsIgnoreCase(string)) {
                return new String[]{item.getName()};
            }
        }
        return null;
    }

    public NPC getFirstNPC() {
        return this.npcs.get(0);

    }

    public String[] getFurniture() {
        ArrayList<Item> furniture = new ArrayList<>();
        for (Item item : this.getArrayInventory()) {
            if (item.getType().equalsIgnoreCase("Furniture")) {
                furniture.add(item);
            }
        }
        String[] furniture1 = new String[furniture.size()];
        for (int i = 0; i < furniture.size(); i++) {
            furniture1[i] = furniture.get(i).getName();
        }
        return furniture1;
    }

    public String[] getToyChoices() {
        String[] items = this.getItemChoises();
        String[] toys = new String[items.length];
        int i = 0;
        for (String itemName : items) {
            Item item = this.getItemByName(itemName);
            if (item != null && item.getType() != null && item.getType().equalsIgnoreCase("Toy")) {
                toys[i] = itemName;
            } else {
                toys[i] = "";
            }
            i++;
        }
        return toys;
    }

    public void update() {
    }

    public ArrayList<NPC> getNpcs() {
        return npcs;
    }

    public void setNpcs(ArrayList<NPC> npcs) {
        this.npcs = npcs;
    }

    public boolean isUpdated() {
        return updated;
    }

    void initializeRoomFiles() { //initializes the room files and creates them if they don't exist already.
        File mainFile = new File(this.getName().concat(".txt"));
        File descFile = new File(this.getName().concat("-desc.txt"));

        if (!mainFile.exists()) {
            try {
                mainFile.createNewFile();
            } catch (IOException e) {
                GameHandler.getGui().display("Error creating file main file", "Black");
            }
            if (!descFile.exists()) {
                try {
                    descFile.createNewFile();
                } catch (IOException e) {
                    GameHandler.getGui().display("Error creating description file", "Black");
                }
            }
        }
        this.setDescription(GameHandler.readFile(this.getName().concat("-desc")));
    }

    String[] getContraband() {
        ArrayList<Item> contraband = new ArrayList<>();
        for (Item item : this.getArrayInventory()) {
            if (item.isContraband()) {
                contraband.add(item);
            }
        }
        String[] contraband1 = new String[contraband.size()];
        for (int i = 0; i < contraband.size(); i++) {
            contraband1[i] = contraband.get(i).getName();
        }
        return contraband1;
    }
    public String getType() {
        return this.type;
    }

    private void breakItem(Item item) {
        item.setBroken(true);
        item.setName(name.concat(" (Broken)"));
    }

    private void vandalizeItem(Item item) {
        item.setVandalized(true);
        item.setName(item.getName().concat(" (Vandalized)"));
        item.setDescription("This item is painted in the colors of "+Player.alignment+"<br> The logo scribbled onto it supports "+Player.alignment+"<br>"+item.getDescription());
    }
}
