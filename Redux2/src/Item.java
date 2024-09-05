
import java.util.ArrayList;

public class Item {
    private String name;
    private String description;
    private String type;
    private boolean takable;
    private boolean locked;
    private boolean broken;
    private ArrayList<Item> items;
    private boolean droppable;
    public boolean isBroken() {
        return broken;
    }
    public void setBroken(boolean broken) {
        this.broken = broken;
    }
    public boolean isLocked() {
        return locked;
    }
    public void setLocked(boolean locked) {
        this.locked = locked;
    }
    public Item(String name, String description, String type, boolean takable) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.takable = takable;
        this.locked = false;
        this.broken = false;
        this.items = new ArrayList<>();
    }
    public ArrayList<Item> getItems() {
        return items;
    }
    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
    public boolean isTakable() {
        return takable;
    }
    public void setTakable(boolean takable) {
        this.takable = takable;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public Item(String name, String discription) {
        this.name = name;
        this.description = discription;
    }
public void use() {
    switch(this.type) {
        case "Consumable" -> GameHandler.getGui().display("You used the " + this.name, "Black");
        case "Equipment" -> GameHandler.getGui().display("You equipped the " + this.name, "Black");
        case "QuestItem" -> GameHandler.getGui().display("You used the " + this.name, "Black");
        default -> GameHandler.getGui().display("You can't use that item.","red");
    }
}
public void removeItemByName(String itemName) {
    for(Item item : this.items) {
        if(item.getName().equalsIgnoreCase(itemName)) {
            this.items.remove(item);
            break;
        }
    }
}
public void addItem(Item item) {
    this.items.add(item);
}
public void removeItem(Item item) {
    this.items.remove(item);
}
public void removeItemByIndex(int index) {
    this.items.remove(index);
}
public Item getItemByName(String itemName) {
    for(Item item : this.items) {
        if(item.getName().equalsIgnoreCase(itemName)) {
            return item;
        }
    }
    return null;
}
public Item getItemByIndex(int index) {
    return this.items.get(index);
}
public void displayAllItems() {
    for(Item item : this.items) {
        System.out.println(item.getName());
    }
}
public void displayItemsByType(String type) {
    for(Item item : this.items) {
        if(item.getType().equalsIgnoreCase(type)) {
            System.out.println(item.getName());
        }
    }
}
public void displayTakableItemsByType(String type, boolean takable) {
    for(Item item : this.items) {
        if(item.getType().equalsIgnoreCase(type) && item.isTakable() == takable) {
            System.out.println(item.getName());
        }
    }
}
public void displayTakableItems(boolean takable) {
    for(Item item : this.items) {
        if(item.isTakable() == takable) {
            System.out.println(item.getName());
        }
    }
}

public String getName() {
    return this.name;
    }
public String getDescription() {
    return this.description;
    }
public void setName(String name1) {
    this.name = name1;
    }
public void setDescription(String description1) {
    this.description = description1;
    }
    @Override
    public String toString() {
    return getName() + " - " + getDescription();
    }

    public boolean isDroppable() {
        return droppable;
    }
    public void setDroppable(boolean b) {
    this.droppable = b;    
    }

}
