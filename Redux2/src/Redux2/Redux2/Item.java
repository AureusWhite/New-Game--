package Redux2;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

public class Item {
    private int saturation;
    protected String name;
    protected String description;
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
        this.conditions.put(ItemCondition.TAKEABLE, takable);
    }

    public boolean isBroken() {
        if (this.conditions.containsKey(ItemCondition.BROKEN)) {
            return this.conditions.get(ItemCondition.BROKEN);
        }
        return false;
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
        if (this.conditions.containsKey(ItemCondition.TAKEABLE)) {
            return this.conditions.get(ItemCondition.TAKEABLE);
        }
        return false;
    }
    public int getSaturation() {
        return this.saturation;
    }

    public void setSaturation(int saturation) {
        this.saturation = saturation;
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
        if (this.getClass().equals(Container.class)) {
            String[] choices = this.getItemChoices();
            String choice = JOptionPane.showInputDialog(null, "Choose an item to take", "Dispencer", JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]).toString();
            if (choice.equals("Close")) {
                return;
            }
            for (Item item : this.inventory) {
                if (item.getName().equals(choice)) {
                    GameHandler.getGui().display("You take the " + item.getName(), "Black");
                    Player.addItem(item);
                    this.removeItem(item);
                    return;
                }
            }
        }
        else if (this.conditions.containsKey(ItemCondition.FOOD)||this.conditions.containsKey(ItemCondition.DRINK)) {
            if(this.conditions.containsKey(ItemCondition.FOOD)){
                Player.setHunger(Player.getHunger() + this.getSaturation());
            GameHandler.getGui().display("Saturation: " + this.getSaturation(), "Black");
            GameHandler.getGui().display("You eat the " + this.getName(), "Black");
            GameHandler.getGui().display("You are now " + Player.getHunger() + "% full", "Black");
            }
            else if(this.conditions.containsKey(ItemCondition.DRINK)){
                Player.setThirst(Player.getThirst() + this.getSaturation());
            GameHandler.getGui().display("Saturation: " + this.getSaturation(), "Black");
            GameHandler.getGui().display("You drink the " + this.getName(), "Black");
            GameHandler.getGui().display("You are now " + Player.getThirst() + "% quenched", "Black");
                Player.setBlatter(Player.getBlatter() + this.getSaturation());
        }
        } else if (this.conditions.containsKey(ItemCondition.DISPENCER)) {
            String[] choices = getItemChoices();
            String choice = JOptionPane.showInputDialog(null, "Choose an item to take", "Dispencer", JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]).toString();
            if (choice.equals("Close")) {
                return;
            }
            for (Item item : this.inventory) {
                if (item.getName().equals(choice)) {
                    GameHandler.getGui().display("You take the " + item.getName(), "Black");
                    Player.addItem(item);
                    this.removeItem(item);
                    return;
                }
            }
        } else if (this.conditions.containsKey(ItemCondition.TOY)) {
            GameHandler.getGui().display("You use the " + this.getName(), "Black");
            Player.removeItem(this);
        } else if (this.conditions.containsKey(ItemCondition.TEST)) {
            GameHandler.getGui().display("You use the " + this.getName(), "Black");
            Player.removeItem(this);
        } else if (this.conditions.containsKey(ItemCondition.KEY)) {
            String[] choices = Player.getRoom().getExitChoises();
            String choice = JOptionPane.showInputDialog(null, "Choose a door to unlock", "Unlock", JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]).toString();
            if (choice.equals("Close")) {
                return;
            }
            if (!Player.getRoom().getExit(choice).isLocked()) {
                GameHandler.getGui().display("The " + choice + " door is already unlocked", "Black");
            } else if (Player.getRoom().getExit(choice).isLocked()) {
                if (this.getName().equals(Player.getRoom().getExit(choice).getKey())) {
                    Player.getRoom().getExit(choice).setLocked(false);
                    GameHandler.getGui().display("You unlock the " + choice + " door", "Black");
                    Player.removeItem(this);
                } else {
                    GameHandler.getGui().display("The " + choice + " door is locked, and you do not have the key.", "Black");
                }
            } else {
                GameHandler.getGui().display("The " + choice + " door is already unlocked", "Black");
            }

        } else if (this.conditions.containsKey(ItemCondition.CLOTHING)) {
            GameHandler.getGui().display("Use equip..", "Black");

        } else if (this.conditions.containsKey(ItemCondition.SEAT)) {
            GameHandler.getGui().display("You sit on the " + this.getName(), "Black");
        } else if (this.conditions.containsKey(ItemCondition.CONTRABAND)) {
            GameHandler.getGui().display("You use the " + this.getName(), "Black");
            Player.removeItem(this);
        } else if (this.conditions.containsKey(ItemCondition.DROPPABLE)) {
            GameHandler.getGui().display("You use the " + this.getName(), "Black");
            Player.removeItem(this);
        } else if (this.conditions.containsKey(ItemCondition.VANDELIZED)) {
            GameHandler.getGui().display("You use the " + this.getName(), "Black");
            Player.removeItem(this);
        } else if (this.conditions.containsKey(ItemCondition.INTERACTABLE)) {
            GameHandler.getGui().display("You use the " + this.getName(), "Black");
            Player.removeItem(this);
        } else if (this.conditions.containsKey(ItemCondition.BROKEN)) {
            GameHandler.getGui().display("You use the " + this.getName(), "Black");
            Player.removeItem(this);
        } else if (this.conditions.containsKey(ItemCondition.LOCKED)) {
            GameHandler.getGui().display("You use the " + this.getName(), "Black");
            Player.removeItem(this);
        } else if (this.conditions.containsKey(ItemCondition.TAKEABLE)) {
            GameHandler.getGui().display("You use the " + this.getName(), "Black");
        } else if (this.conditions.containsKey(ItemCondition.DRINK)) {
        } else {
            GameHandler.getGui().display("You use the " + this.getName(), "Black");
        }

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
        if (this.conditions.containsKey(ItemCondition.INTERACTABLE)) {
            if (this.conditions.get(ItemCondition.DISPENCER)) {
                switch (this.getName()) {
                    case "Food Tray Dispenser" -> {
                        GameHandler.makeFoodTray();
                        String[] choices = getItemChoices();
                        String choice = JOptionPane.showInputDialog(null, "Choose an item to take", "Dispencer", JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]).toString();
                        if (choice.equals("Close")) {
                            return;
                        }
                        for (Item item : this.inventory) {
                            if (item.getName().equals(choice)) {
                                GameHandler.getGui().display("You take the " + item.getName(), "Black");
                                Player.addItem(item);
                                this.removeItem(item);
                                return;
                            }
                        }
                    }
                    default -> {
                    }
                }

            }
        }
    }
    public void removeItem(Item item) {
        if (!item.isConditionMet(ItemCondition.UNREMOVABLE)) {
            this.inventory.remove(item);
        }
    }
    private boolean isConditionMet(ItemCondition condition) {
        if (this.conditions.containsKey(condition)) {
            return this.conditions.get(condition);
        }
        return false;
    }

    public ArrayList<Item> getInventory() {
        return this.inventory;
    }

    public void displayInventory() {
        for (Item item : this.inventory) {
            GameHandler.getGui().display(item.getName(), "Black");
        }
    }

    private String[] getItemChoices() {
        ArrayList<String> choices = new ArrayList<>();
        for (Item item : this.inventory) {
            choices.add(item.getName());
        }
        choices.add("Close");
        return choices.toArray(String[]::new);
    }
}
