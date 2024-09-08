package Redux2;
public class Equipment extends Item {

    private String slot;

    public Equipment(String name, String discription, String slot) {
        super(name, discription);
    }

    public void equip(Character character) {
        character.equipItem(this);
    }

    public String getSlot() {
        return slot;
    }

}
