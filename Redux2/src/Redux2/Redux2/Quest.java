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

    public Quest(String name, String description, Item[] itemReward, int moneyReward, int expReward, String type, Item requiredItem, NPC requiredEscort, Room requiredRoom) {
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

}
