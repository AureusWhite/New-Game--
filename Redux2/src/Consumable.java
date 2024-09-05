
public class Consumable extends Item {

    private int buffValue;

    public Consumable(String name, String description) {
        super(name, description);
    }

    @Override
    public void use() {
        Game.getGui().display("You used the " + this.getName(), "Black");
    }

    public void use(Character character) {
        switch (this.getType()) {
            case "Food" ->
                character.setHunger(character.getHunger() + this.getBuffValue());
            case "Drink" ->
                character.setThirst(character.getThirst() + this.getBuffValue());
            case "Misc" ->
                this.getBuffValue();
            default -> {
            }

        }
    }

    private int getBuffValue() {
        return buffValue;
    }
}
