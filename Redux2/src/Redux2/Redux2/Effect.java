package Redux2;

public class Effect {

    @FunctionalInterface
    public interface EffectAction {
        void run(String argument);
    }

    private String description; // e.g., "Successfully sneak", "Soothes themselves"
    private EffectAction action; // Effect can have an action that changes the player's state

    public Effect(String description, EffectAction action) {
        this.description = description;
        this.action = action;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EffectAction getAction() {
        return action;
    }

    public void setAction(EffectAction action) {
        this.action = action;
    }

    public void applyEffect(String argument) {
        // Apply the effect (invoke the action)
        if (action != null) {
            action.run(argument);
        }
    }

    public String getDescription() {
        return description;
    }
}
