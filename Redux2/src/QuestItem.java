
public class QuestItem extends Item {
    private boolean redeemed;

    public QuestItem(String name, String description) {
        super(name, description);
        this.redeemed = false;
    }

    public boolean isRedeemed() {
        return redeemed;
    }

    public void setRedeemed(boolean redeemed) {
        this.redeemed = redeemed;
    }

}
