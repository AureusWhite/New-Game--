package Redux2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Equipment extends Item {

    private String slot;
    private int pockets = 1;

    private final ArrayList<Item> items = new ArrayList<>();
    private final HashMap<ItemCondition, Boolean> conditions = new HashMap<>();

    public Equipment(String name, String discription, String slot) {
        super(name, discription);
        this.slot = slot;
    }

    public ArrayList<Item> getItems() {
        return items;
    }
    public String getSlot() {
        return slot;
    }
    public void setSlot(String slot) {
        this.slot = slot;
    }

    public void setEquipped(boolean par) {
        this.conditions.put(ItemCondition.EQUIPPED, par);
    }

    public boolean isEquipped() {
        if (this.conditions.containsKey(ItemCondition.EQUIPPED)) {
            return this.conditions.get(ItemCondition.EQUIPPED);
        }
        return false;
    }

    public int getPockets() {
        return pockets;
    }

    public void setPockets(int pockets) {
        this.pockets = pockets;
    }

    public String getCondition() {
        for(ItemCondition condition1 : ItemCondition.values()){
            if(this.conditions.containsKey(condition1)){
                if(this.conditions.get(condition1)){
                    return condition1.toString();
                }
            }
        }
        return "----";
    }

    public void emptyPockets(NPC aThis) {
        Iterator<Item> it = this.getItems().iterator();
        while (it.hasNext()) {
            Item item = it.next();
            if (item.isContraband()) {
                GameHandler.getGui().display(item.getName() + " was confiscated", "Black");
                aThis.getInventory().add(item);
                it.remove();
            }
        }
    }
    @Override
    public HashMap<ItemCondition, Boolean> getConditions() {
        return conditions;
    }

    void setCondition(ItemCondition condition, boolean b) {
        this.conditions.put(condition, b);
    }
}
