package Redux2;

import java.util.ArrayList;
import java.util.HashMap;

public class Item {

    protected String name;
    private String description;
    private String type;
    private int price;
    private final HashMap<ItemCondition, Boolean> conditions = new HashMap<>();
    private final ArrayList<Item> inventory = new ArrayList<>();

    public HashMap<ItemCondition, Boolean> getConditions() {
        return this.conditions;
    }


    public Item(String name, String description, String type, boolean takable) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.price = 0;
    }

    public Item(String name, String discription) {
        this.name = name;
        this.description = discription;
    }

    public boolean isBroken() {
        if (this.conditions.containsKey(ItemCondition.BROKEN)) {
            return this.conditions.get(ItemCondition.BROKEN);
        }
        return false;
    }
    public void removeItem(Item item) {
        this.inventory.remove(item);
    }
    public void setBroken(boolean b) {
        this.conditions.put(ItemCondition.BROKEN, b);
    }

    public boolean isLocked() {
        if (this.conditions.containsKey(ItemCondition.LOCKED)) {
            return this.conditions.get(ItemCondition.LOCKED);
        }
        return false;
    }

    public void setLocked(boolean locked) {
        this.conditions.put(ItemCondition.LOCKED, locked);
    }

    public boolean isTakable() {
        if(this.conditions.containsKey(ItemCondition.TAKEABLE)){
            return this.conditions.get(ItemCondition.TAKEABLE);
        }
        return false;
    }

    public void setTakable(boolean takable) {
        this.conditions.put(ItemCondition.TAKEABLE, takable);
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void use() {
        GameHandler.getGui().display("You used the " + this.name, "black");
    }

    public void setContraband(boolean b) {
        this.conditions.put(ItemCondition.CONTRABAND, b);
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
        if (this.conditions.containsKey(ItemCondition.DROPPABLE)) {
            return this.conditions.get(ItemCondition.DROPPABLE);
        }
        return true;
    }

    public void setDroppable(boolean b) {
        this.conditions.put(ItemCondition.DROPPABLE, b);
    }

    public boolean isContraband() {
        if (this.conditions.containsKey(ItemCondition.CONTRABAND)) {
            return this.conditions.get(ItemCondition.CONTRABAND);
        }
        return false;
    }

    public void setVandalized(boolean b) {
        this.conditions.put(ItemCondition.VANDELIZED, b);
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int i) {
        this.price = i;
    }

    void addItem(Item item) {
        this.inventory.add(item);
    }

    public void interact() {
    if(this.conditions.containsKey(ItemCondition.INTERACTABLE)){
        GameHandler.getGui().display("You interacted with the " + this.name, "black");
    }
    }

    public ArrayList<Item> getInventory() {
        return this.inventory;
    }
}
