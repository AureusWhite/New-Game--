
public class Equipment extends Item{

    public Equipment(String name, String discription) {
        super(name, discription);
    }

    public void equip(Character character) {
        character.equipItem(this);
    }

}
