
package Redux2;


public class Quest {
    private final String name, description;
    private final int difficulty;
    private Room requiredRoom;


    public Quest(String name, String description, int difficulty) {
        this.name = name;
        this.description = description;
        this.difficulty = difficulty;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public int getDifficulty() {
        return difficulty;
    }
    public boolean checkCompleted() {
        Item trash = GameHandler.getItemByName("trash");
        if (this.requiredRoom.getInventory().contains(trash)){
            System.out.println("The room is not clean");
            return false;
        } else {
            System.out.println("The room is clean");
            return true;
        }
    }


}
