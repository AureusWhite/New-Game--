package Redux2;

import java.util.function.BiConsumer;

public class PawAbility {
    private String name;
    private int level;
    private BiConsumer<Paw, Paw> abilityEffect;

    public PawAbility(String name, int level, BiConsumer<Paw, Paw> abilityEffect) {
        this.name = name;
        this.level = level;
        this.abilityEffect = abilityEffect;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public BiConsumer<Paw, Paw> getAbilityEffect() {
        return abilityEffect;
    }

    public void setAbilityEffect(BiConsumer<Paw, Paw> abilityEffect) {
        this.abilityEffect = abilityEffect;
    }
    
}