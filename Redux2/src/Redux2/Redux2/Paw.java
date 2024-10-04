package Redux2;

import java.util.Random;

public class Paw {
    private final String name;
    private int attack;
    private int defense;
    private int hp;
    private boolean tapped;
    private final String discription;
    private int speed;
    private PawAbility ability;
    private int health;
    private int stealth;
    private boolean confused;
    private int power;
    private int intellect;

    // Constructor
    public Paw(String name, String discription, int attack, int defense, int hp, int speed) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.hp = hp;
        this.tapped = false;
        this.speed = speed;
        this.discription = discription;
    }

    // Getters and Setters for Paw attributes
    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
        PawsAndProwess.display(name + "'s attack is now " + this.attack + ".");
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
        PawsAndProwess.display(name + "'s defense is now " + this.defense + ".");
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
        PawsAndProwess.display(name + "'s HP is now " + this.hp + ".");
    }

    public String getName() {
        return name;
    }
    public boolean isTapped() {
        return tapped;
    }
    public void setTapped(boolean tapped) {
        this.tapped = tapped;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getDiscription() {
        return discription;
    }
    public void setAbility(PawAbility ability) {
        this.ability = ability;
    }

   public int getPower() {
        return this.power;
    }

    public void setPower(int i) {
        this.power = i;
    }

    public int getStealth() {
        return this.stealth;
    }

    public void setConfused(boolean b) {
        this.confused = b;
    }

    public void setStealth(int i) {
        this.stealth = i;
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int i) {
        this.health = i;
    }

    public PawAbility getAbility() {
        return ability;
    }

    public boolean isConfused() {
        return confused;
    }
    
    public void attack(Paw oPaw) {
        oPaw.defend(this.attack);
    }

    public void defend(int damage) {
        if(!this.isTapped()) {
            int trueDamage = damage - this.defense;
            if(trueDamage > 0) {
                this.hp -= trueDamage;
                PawsAndProwess.display(this.name + " took " + trueDamage + " damage.");
                PawsAndProwess.display(this.name + " now has " + this.hp + " HP.");
            } else {
                PawsAndProwess.display(this.name + " took no damage.");
            }
        } else {
            PawsAndProwess.display(this.name + " is tapped and cannot defend.");
        }
    }

    public int getIntellect() {
        return this.intellect;
    }

    public int getStrategy() {
        Random rand = new Random();
        int strategy = rand.nextInt(3);
        return strategy;
    }

}
