package Redux2;
public class Quest {

    private String questName;
    private boolean completed;

    public String getQuestName() {
        return questName;
    }

    public void setQuestName(String questName) {
        this.questName = questName;
    }

    private String questType;

    public String getQuestType() {
        return questType;
    }

    public void setQuestType(String questType) {
        this.questType = questType;
    }

    private final Item requiredItem;

    public Item getRequiredItem() {
        return requiredItem;
    }

    public Quest(String questName, String questType, Item item) {
        this.questName = questName;
        this.questType = questType;
        this.requiredItem = item;
    }

    public String getDescription() {
        return "You need to find " + requiredItem.getName();
    }

    void setCompleted(boolean b) {
        this.completed =b;
        Player.displayQuests();
    }

    Boolean isCompleted() {
        return completed;
    }

}
