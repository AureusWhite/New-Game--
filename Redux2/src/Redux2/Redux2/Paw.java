package Redux2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Paw implements Serializable{
    private static final long serialVersionUID = 1L;

    private String name;
    private PawsColor color;
    private PawsRarity rarity;
    Random rand = new Random();
    private PawsAbility pawability;
    private int attack = 1;

    private int defense = 1;
    private boolean tapped = false;
    private final String discription;
    private int speed = 1;
    private PawAbility ability;
    private int hp = 25;
    private int stealth = 1;
    private boolean confused = false;
    private int power = 1;
    private int intellect = 1;
    private int energy = 10;
    private ArrayList<Paw> paws;

    // Constructor
    public Paw(String name, String discription, int attack, int defense, int hp, int speed, PawsColor color, PawsAbility ability, PawsRarity rarity) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.tapped = false;
        this.speed = speed;
        this.discription = discription;
    }

    public void setColor(PawsColor color) {
        this.color = color;
    }

    public PawsRarity getRarity() {
        return rarity;
    }
    public PawsAbility getPawability() {
        return pawability;
    }

    public void setPawability(PawsAbility pawability) {
        this.pawability = pawability;
    }

    public void setRarity(PawsRarity rarity) {
        this.rarity = rarity;
    }

    public void setIntellect(int i) {
        this.intellect = i;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PawsColor getColor() {
        return color;
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
        int randP = this.rand.nextInt(i);
        this.setAttack(this.getAttack() + randP);
    }

    public int getStealth() {
        return this.stealth;
    }
    public void setStealth(int i) {
        this.stealth = i;
    }
    public PawAbility getAbility() {
        return ability;
    }
    public boolean isConfused() {
        return confused;
    }
    public void setConfused(boolean b) {
        this.confused = b;
    }
    public void attack(Paw defending) {
        if(this.isConfused()) {
            PawsAndProwess.display(this.getName() + " is confused and does not obey.");
            int snapoutofit = rand.nextInt(2);
            if(snapoutofit == 1) {
                PawsAndProwess.display(this.getName() + " snapped out of it.");
                this.setConfused(false);
                return;
            } else {
            PawsAndProwess.display(this.getName() + " is still confused.");
            this.setConfused(true);
            return;
        }
    }
        this.setEnergy(this.getEnergy() - 2);
        if (this.isTapped()) {
            PawsAndProwess.display(this.getName() + " is tapped and cannot attack.");
            this.setTapped(false);
        } else {
            if(defending.isTapped()) {
                PawsAndProwess.display(defending.getName() + " is tapped and cannot defend.");
                defending.setHp(defending.getHp() - this.getAttack());
                defending.setTapped(false);
                return;
            }
            PawsAndProwess.display(this.getName() + " attacked " + defending.getName() + ".");
            PawsAndProwess.display(this.getName() + " lost 2 energy. " + this.getName() + " now has " + this.getEnergy() + " energy.");
            int damage = this.getAttack() - defending.getDefense();
            if (damage > 0) {
                defending.setHp(defending.getHp() - damage);
                PawsAndProwess.display(defending.getName() + " took " + damage + " damage.\n");
            } else {
                PawsAndProwess.display(defending.getName() + " took no damage.\n");
            }
        }
    }
    public void defend(Paw attacking) {
        this.setEnergy(this.getEnergy() + 2);
        int hide = rand.nextInt(this.getStealth()+1);
        if(hide > 5) {
            PawsAndProwess.display(this.getName() + " is stealthed and cannot be attacked.");
            return;
        }
        if (this.isTapped()) {
            PawsAndProwess.display(this.getName() + " is tapped and cannot defend.");
            this.setTapped(false);
            this.setHp(this.hp - attacking.getAttack());
        } else {
            PawsAndProwess.display(this.getName() + " defended against " + attacking.getName() + ".");
            PawsAndProwess.display(this.getName() + " gained 2 energy. " + this.getName() + " now has " + this.getEnergy() + " energy.");
            int damage = attacking.getAttack() - this.getDefense();
            if (damage > 0) {
                this.setHp(this.hp - damage);
                PawsAndProwess.display(this.getName() + " took " + damage + " damage.\n");
            } else {
                PawsAndProwess.display(this.getName() + " took no damage.\n");
            }
        }
    }
    public int getIntellect() {
        return this.intellect;
    }
    public int getStrategy() {
        int strategy = rand.nextInt(3);
        return strategy;
    }
    public void takeTurn(Paw playerPaw) {
        int action = rand.nextInt(2);
        switch (action) {
            case 0 -> {
                if (this.getEnergy() >= 2) {
                    this.attack(playerPaw);
                } else {
                    this.defend(playerPaw);
                }
            }
            case 1 -> {
                if (this.getEnergy() >= 7) {
                    this.attack(playerPaw);
                } else {
                    this.defend(playerPaw);
                }
            }
        }
        PawsAndProwess.setPriority();
    }
    public int getEnergy() {
        return this.energy;
    }
    public void setEnergy(int i) {
        this.energy = i;
    }
    public void setPaws(ArrayList<Paw> paws) {
        this.paws = paws;
    }
    public ArrayList<Paw> getPaws() {
        return this.paws;
    }
    public void addPaw(Paw paw) {
        this.paws.add(paw);
    }
    public void removePaw(Paw paw) {
        this.paws.remove(paw);
    }
    public void displayPaws() {
        for (Paw paw : this.paws) {
            PawsAndProwess.display(paw.getName());
        }
    }
}