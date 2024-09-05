import java.util.ArrayList;

public class Character {
private  String name;
private String description;
Room room;
ArrayList<Item> inventory;
private int hunger;
    private int thirst;
    public Character(String name, String description, Room room) {
        this.name = name;
        this.description = description;
        this.inventory = new ArrayList<>();
        this.room = room;
    }
    public void setRoom(Room room) {
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
    public  String getName() {
        return name;
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
public void reciveItem(Character character, Item item) {
    this.addItem(item);
    character.removeItem(item);
}
public void useItem(Consumable consumable) {
    consumable.use(this);
}
public void equipItem(Equipment equipment) {
    equipment.equip(this);
}
public int getHunger() {
    return this.hunger;
}
public void setHunger(int i) {
    this.hunger = i;
}
public int getThirst() {
    return this.thirst;
}
public void setThirst(int i) {
    this.thirst = i;
}
}