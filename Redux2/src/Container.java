
public class Container extends Item {

    public Container(String name, String description, String type, boolean takable) {
        super(name, description, type, takable);

    }

    public void open() { // open the container
        if (this.isLocked()) {
            GameHandler.getGui().display("The container is locked.", "red");
        } else {
            GameHandler.getGui().display("You open the " + this.getName() + ".", "black");
            StringBuilder itemList = new StringBuilder("The " + this.getName() + " contains: <br>");
            for (Item item : this.getItems()) {
                itemList.append(item.getName()).append(".<br>");
            }
            GameHandler.getGui().display(itemList.toString(), "black");
        }
    }

}
