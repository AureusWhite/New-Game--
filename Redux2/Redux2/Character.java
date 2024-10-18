package Redux2;

import java.util.ArrayList;

public class Character {

    private final String name;
    private final String description;
    public Room room;
    private final ArrayList<Item> inventory;

    public Character(String name, String description, Room room) {
        this.name = name;
        this.description = description;
        this.inventory = new ArrayList<>();
        this.room = room;
    }
    public Room getRoom() {
        return this.room;
    }

    public void addItem(Item item) {
        this.inventory.add(item);
    }

    public void removeItem(Item item) {
        this.inventory.remove(item);
    }

    public ArrayList<Item> getInventory() {
        return this.inventory;
    }

    public String getName() {
        return name.replace("_", " ");
    }

    public String getDescription() {
        return this.description;
    }
    public void setRoom(Room room) {
        if(room == null) {
            room = GameHandler.getRoomByName("Recovery_Room");
        }
        this.room = room;
    }
}
