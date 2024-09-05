
public class Quest {

    private String questName;

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

    private QuestItem requiredItem;

    public QuestItem getRequiredItem() {
        return requiredItem;
    }

    public void setRequiredItem(QuestItem requiredItem) {
        this.requiredItem = requiredItem;
    }

    public Quest(String questName, String questType, QuestItem requiredItem) {
        this.questName = questName;
        this.questType = questType;
        this.requiredItem = requiredItem;
    }

    public boolean isComplete() {
        for (QuestItem item : Player.questItems) {
            if (item.getName().equals(requiredItem.getName())) {
                return true;
            }
        }
        return false;
    }

}
