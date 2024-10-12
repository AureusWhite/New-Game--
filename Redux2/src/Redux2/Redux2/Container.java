package Redux2;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Container extends Item {

    private final ArrayList<Item> items = new ArrayList<>();

    public Container(String name, String description, String type, boolean takable) {
        super(name, description, type, takable);
    }
    
    public void close() {
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
