package Redux2;

public class Equipment extends Item {

    private String slot;
    private boolean equiped;
    private int pockets;
    private String condition;

    public Equipment(String name, String discription, String slot) {
        super(name, discription);
    }

    @Override
    public String getSlot() {
        return slot;
    }

    @Override
    public void setSlot(String slot) {
        this.slot = slot;
    }

    public void setEquipped(boolean par) {
        this.equiped = par;
    }

    @Override
    public boolean isEquipped() {
        return equiped;
    }

    public int getPockets() {
        return pockets;
    }
    
    public void setPockets(int pockets) {
        this.pockets = pockets;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition1) {
        switch (condition1) {
            case "wet":
                if (this.condition.contains("wet")) {
                    this.condition = "soaked";
                    break;
                }
            case "dirty":
                if (this.condition.contains("dirty")) {
                    this.condition = "filthy";
                    break;
                }
            case "broken":
                if (this.condition.contains("broken")) {
                    this.condition = "destroyed";
                    break;
                }
            case "torn":
                if (this.condition.contains("torn")) {
                    this.condition = "shredded";
                    break;
                }
        }
    }
}
