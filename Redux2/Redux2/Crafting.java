package Redux2;

import java.util.*;

public class Crafting {

    private String name;
    private Map<List<Item>, Item> recipe = new HashMap<>();

    public Crafting(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Add recipe method that takes two items and maps to the result
    public void AddRecipe(Item item1, Item item2, Item result) {
        List<Item> itemPair = Arrays.asList(item1, item2);
        recipe.put(itemPair, result);
    }

    public void RemoveRecipe(Item item1, Item item2) {
        List<Item> itemPair = Arrays.asList(item1, item2);
        recipe.remove(itemPair);
    }

    public Item Craft(Item item1, Item item2) {
        List<Item> itemPair = Arrays.asList(item1, item2);
        List<Item> reversePair = Arrays.asList(item2, item1); // Account for reverse order

        // Check both order combinations of the items
        if (recipe.containsKey(itemPair)) {
            return recipe.get(itemPair);
        } else if (recipe.containsKey(reversePair)) {
            return recipe.get(reversePair);
        }
        Item mess = GameHandler.getItemByName("Mess");
        // Return null or some error if no recipe found
        return mess;
    }

    public void createRecipes() {
        Item stick = GameHandler.getItemByName("Stick");
        Item cheese = GameHandler.getItemByName("Cheese");
        Item cheeseOnAStick = GameHandler.getItemByName("Cheese on a Stick");
        Item diaper = GameHandler.getItemByName("Diaper");
        Item diaperOnAStick = GameHandler.getItemByName("Diaper on a Stick");
        Item rock = GameHandler.getItemByName("Rock");
        Item cheeseCoveredRock = GameHandler.getItemByName("Cheese Covered Rock");
        Item stickOnAStick = GameHandler.getItemByName("Stick on a Stick");
        Item cheezeOnAStick = GameHandler.getItemByName("Cheese on a Stick on a Stick");
        Item diaperWithRock = GameHandler.getItemByName("Diaper with Rock");
        Item diaperWithCheese = GameHandler.getItemByName("Diaper with Cheese");
        Item doll = GameHandler.getItemByName("Doll");
        Item dollWithCheese = GameHandler.getItemByName("Doll with Cheese");
        Item dollWithDiaper = GameHandler.getItemByName("Doll with Diaper");
        Item smashedDoll = GameHandler.getItemByName("Smashed Doll");
        Item smashedDollWithCheese = GameHandler.getItemByName("Smashed Doll with Cheese");
        Item smashedDollOnAStick = GameHandler.getItemByName("Smashed Doll on a Stick");

        // Add recipes
        AddRecipe(stick, cheese, cheeseOnAStick);
        AddRecipe(diaper, stick, diaperOnAStick);
        AddRecipe(rock, cheese, cheeseCoveredRock);
        AddRecipe(stick, stick, stickOnAStick);
        AddRecipe(cheeseOnAStick, stick, cheezeOnAStick);
        AddRecipe(diaper, rock, diaperWithRock);
        AddRecipe(diaper, cheese, diaperWithCheese);
        AddRecipe(doll, cheese, dollWithCheese);
        AddRecipe(doll, diaper, dollWithDiaper);
        AddRecipe(rock, doll, smashedDoll);          
        AddRecipe(smashedDoll, cheese, smashedDollWithCheese);
        AddRecipe(smashedDoll, stick, smashedDollOnAStick);


    }
}
