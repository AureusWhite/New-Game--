package Redux2;

public enum PawsAbility {
    // Abilities
    // Common
    BITE(10), SCRATCH(5), HISS(3), GROWL(4), POUNCE(6), LICK(2), EVASION(7), HEAVY_HIT(8), HEALING_WIND(9), SNEAK(4), CONFUSION(6), DEFENCE(5), STARE(3), SPY(4), TRAIN(7), STUDY(6), SUPPORT(5), EMPOWER(8), STRENGTHEN(7), ENHANCE(6), TRANSFORM(9), FLOW(5), BEAM(8), SPEED(7), CRITICAL(10);

    private final int cost;
    PawsAbility(int cost) {
        this.cost = cost;
    }

    int getCost() {
        return cost;
    }
    
}
