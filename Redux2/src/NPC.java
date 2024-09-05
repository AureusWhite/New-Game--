
public class NPC extends Character {

    public void reciveItem(Item item) {
        switch (item) {
            case QuestItem questItem ->
                questItem.setRedeemed(true);
            case Consumable consumable ->
                this.useItem(consumable);
            case Equipment equipment ->
                this.equipItem(equipment);
            default ->
                Player.addItem(item);
        }
        addItem(item);
        Player.removeItem(item);
    }

    public void giveItemToPlayer(Item item) {
        Player.reciveItem(this, item);
        this.removeItem(item);
    }

    public NPC(String name, String description, Room room) {
        super(name, description, room);
    }

    public String getDialog() {
        return "Hello, I am " + this.getName().replace("_", " ") + ".";
    }
}
