package Redux2;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Container extends Item {

    private final ArrayList<Item> items = new ArrayList<>();

    public Container(String name, String description, String type, boolean takable) {
        super(name, description, type, takable);
    }

    public void open() { // open the container
        boolean hasKey = Player.hasItemByName(name + "_key");
        if(isLocked()||!hasKey){
            GameHandler.getGui().display("The " + this.getName() + " is locked.", "Black");
        } else {
            GameHandler.getGui().display("You open the " + this.getName() + ".", "Black");
            String[] itemChoices = new String[items.size() + 1];
            for (int i = 0; i < items.size(); i++) {
                itemChoices[i] = items.get(i).getName();
            }
            itemChoices[items.size()] = "Close";
            int choice = JOptionPane.showOptionDialog(null, "Choose an item to take", "Container", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, itemChoices, itemChoices[0]);
            if (choice == items.size()) {
                close();
            } else {
                Player.addItem(items.get(choice));
            }
        }
    }
    private void close() {
        boolean hasKey = Player.hasItemByName(name + "_key");
        GameHandler.getGui().display("You close the " + this.getName() + ".", "Black");
        String[] choices = {"Lock", "Leave"};
        int choice = JOptionPane.showOptionDialog(null, "What would you like to do?", "Container", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
        if (choice == 0&&!isLocked()&&hasKey) {
            lock();
        }

    }
    public void lock() {
        GameHandler.getGui().display("You lock the " + this.getName() + ".", "Black");
        this.setLocked(true);
    }
    public ArrayList<Item> getItems() {
        return items;
    }

}
