package Redux2;

import java.util.Random;
import javax.swing.JOptionPane;

public class PawFigure extends Item {
    static Random random = new Random();   
    PawsColor color;
    PawsAbility ability;
    PawsRarity rarity;

    // Constructor
    public PawFigure() {
        super("Paw Figure", "Open for a new Paw with random attributes!", "Paw", true);
    }

    // Method to generate a random Paw
    public void generateRandomPaw() {
        // Randomly pick a color
        this.color = PawsColor.values()[random.nextInt(PawsColor.values().length)];

        // Randomly pick an ability
        this.ability = PawsAbility.values()[random.nextInt(PawsAbility.values().length)];

        // Base stats for the Paw (you can adjust these numbers)
        int baseAttack = random.nextInt(5) + 7;
        int baseDefense = random.nextInt(5) + 7;
        int baseHP = 25;
        int speed = random.nextInt(5) + 7;
        int totalStats = baseAttack + baseDefense + speed;
        switch(totalStats){
            case 16,17,18:
                this.rarity = PawsRarity.TRASH;
                break;
            case 19,20,21:
                this.rarity = PawsRarity.COMMON;
                break;
            case 22,23,24:
                this.rarity = PawsRarity.UNCOMMON;
                break;
            case 25,26,27:
                this.rarity = PawsRarity.RARE;
                break;
            case 28,29,30:
                this.rarity = PawsRarity.EPIC;
                break;
            case 31,32,33:
                this.rarity = PawsRarity.LEGENDARY;
                break;
            default:
                this.rarity = PawsRarity.TRASH;
                break;
        }

        // Ask the player for the name and description of the Paw
        this.name = JOptionPane.showInputDialog("Name the Paw");
        this.description = JOptionPane.showInputDialog("Describe the Paw");

        // Display the newly generated Paw's details on the GUI
        String pawDetails = "Name: " + this.name + "<br>"
                          + "Description: " + this.description + "<br>"
                          + "Color: " + this.color + "<br>"
                          + "Ability: " + this.ability + "<br>"
                          + "Rarity: " + this.rarity + "<br>"
                          + "Attack: " + baseAttack + "<br>"
                          + "Defense: " + baseDefense + "<br>"
                          + "HP: " + baseHP + "<br>"
                          + "Speed: " + speed;

        GameHandler.getGui().display(pawDetails, "Black");

        // Generate the Paw and add it to the collection
        Player.getPaws().add(new Paw(this.name, this.description, baseAttack, baseDefense, baseHP, speed, this.color, this.ability, this.rarity));
        PawsAndProwess.getPaws().addAll(Player.getPaws());
        Player.removeItem(this);
    }
}
