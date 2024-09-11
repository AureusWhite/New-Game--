package Redux2;


public class Quest {

    private String name;
    private String description;
    private boolean completed;
    private Item[] itemReward;
    private int moneyReward;
    private int expReward;
    private String type;
    private Item requiredItem;
    private NPC requiredEscort;
    private Room requiredRoom;

    public Quest(String name, String description, Item[] itemReward, int moneyReward, int expReward, String type, Item requiredItem, NPC requiredEscort,Room requiredRoom) {
        this.name = name;
        this.description = description;
        this.itemReward = itemReward;
        this.moneyReward = moneyReward;
        this.expReward = expReward;
        this.completed = false;
        this.type = type;
        this.requiredItem = requiredItem;
        this.requiredEscort = requiredEscort;
        this.requiredRoom = requiredRoom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Item[] getItemReward() {
        return itemReward;
    }

    public void setItemReward(Item[] itemReward) {
        this.itemReward = itemReward;
    }

    public int getMoneyReward() {
        return moneyReward;
    }

    public void setMoneyReward(int moneyReward) {
        this.moneyReward = moneyReward;
    }

    public int getExpReward() {
        return expReward;
    }

    public void setExpReward(int expReward) {
        this.expReward = expReward;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Item getRequiredItem() {
        return requiredItem;
    }

    public void setRequiredItem(Item requiredItem) {
        this.requiredItem = requiredItem;
    }

    public NPC getRequiredEscort() {
        return requiredEscort;
    }

    public void setRequiredEscort(NPC requiredEscort) {
        this.requiredEscort = requiredEscort;
    }

    public Room getRequiredRoom() {
        return requiredRoom;
    }

    public void setRequiredRoom(Room requiredRoom) {
        this.requiredRoom = requiredRoom;
    }
    public boolean checkCompletion(){
        GameHandler.getGui().display("Checking completion", this.getName());
        switch(this.type){
            case "fetch" -> {
                if(Player.hasItem(this.requiredItem)){
                    this.completed = true;
                    GameHandler.getGui().display("You did the thing", this.getName());
                    Player.removeItem(requiredItem);
                } else {
                    GameHandler.getGui().display("You did not do the thing", this.getName());
                }
                break;
            }
            case "escort" -> {
                if(requiredEscort.getRoom() == requiredRoom){
                    GameHandler.getGui().display(requiredEscort.getName()+" is in "+requiredRoom.getName(), "black");
                    this.completed = true;
                    GameHandler.getGui().display("You did the thing", this.getName());
                } else {
                    GameHandler.getGui().display("You did not do the thing", this.getName());
                }
                break;
            }
            case "tidyUp" -> {
                GameHandler.getGui().display("Checking for"+this.requiredItem.getName(),"black");
                if(!requiredRoom.getInventory().contains(this.requiredItem)){
                    this.completed = true;
                    GameHandler.getGui().display("You did the thing", this.getName());
                    GameHandler.getGui().display("You found a "+this.requiredItem, "black");
                } else {
                    GameHandler.getGui().display("You did not do the thing", "black");
                  
                }
                break;

            }
        }
        return this.completed;

    }

}
