package Redux2;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Shops extends Item {

    ArrayList<Item> inventory;

    public Shops(String name, String description) {
        super(name, description, "Shop/Container/Furniture", false);
        this.takable = false;
        this.inventory = new ArrayList<>();
    }

    @Override
    public void use() {
        GameHandler.getGui().display("You walk up to the shop and ask to see what's for same", "Black");
    }
    @Override
    public void interact() {
        GameHandler.getGui().display("You walk up to the shop and ask to see what's for sale", "Black");
        String[] forSale = this.getForSale();
        String selectedItem = (String) JOptionPane.showInputDialog(
                            null,
                            "What would you like to buy?",
                            "Shop",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            forSale,
                            forSale[0]);
        if (selectedItem != null) {
            String itemName = selectedItem.split(" - ")[0];

            Item item = GameHandler.getItemByName(itemName);
            if (item != null) {
                if(Player.giveMoney(item.getPrice())==0){
                    return;
                }
                GameHandler.getGui().display("You buy the " + item.getName(), "Black");
                Player.addItem(item);
            } else {
                GameHandler.getGui().display("The selected item is not available", "Black");
                GameHandler.getGui().display(itemName, "Black");
            }
        } else {
            GameHandler.getGui().display("You didn't buy anything", "Black");
        }
    }
    

    private ArrayList<Item> getInventory() {
        return this.inventory;
    }

    public String[] getForSale() {
        String[] items = new String[this.getInventory().size()];
        for (Item item : this.inventory) {
            items[this.inventory.indexOf(item)] = item.getName()+" - "+item.getPrice();
        }
        return items;
    }
    @Override
    public void addItem(Item item) {
        this.inventory.add(item);
    }
}
