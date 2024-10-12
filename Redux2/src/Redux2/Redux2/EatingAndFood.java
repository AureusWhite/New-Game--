package Redux2;

public class EatingAndFood extends Item {

    private FoodType foodType;
    private final int saturation;
    
    @Override
    public int getSaturation() {
        return saturation;
    }

    public enum FoodType {
        VEGETABLE, FRUIT, JUNK, SNACK, DRINK, MEAL
    }

    public enum MealType {
        BREAKFAST, LUNCH, DINNER, SNACK
    }

    public enum fruitChoices {
        APPLE, ORANGE, RASIN, BANANA, BLUEBERRIES, STRAWBERRIES, GRAPES, PEAR, PEACH, PLUM, MANGO, WATERMELON, PINEAPPLE, HONEYDEW, CHERRY, DRAGONFRUIT, PAWPAW, DURIAN,
    }

    public enum vegetableChoices {
        CARROT, CELERY, BROCCOLI, PEAS, CORN, GREENBEANS, SWEETPOTATO, TOMATO, LETTUCE, PEPPER, CUCUMBER, RADISH, EGGPLANT, MUSHROOM, PUMPKIN, AVOCADO, RUTABAGA, DAIKON,
    }

    public enum junkChoices {
        TATERCHIPS, GOATMEALCOOKIES, GOBBLINCAKE, SKYPIE, ICECREAM, GEEKS, CHOCOLATEPOOPIES, BOOHOOS, GOATMEALBAR, AMIMALCRACKERS, PETZELS, POPCORN, CHOCOLATEBAR, FRUITSNACKS, FRUITLEATHER, NOUGAT, TAFFY, ALMOSTBUTTERCUP, THISANDTHATS,
    }

    public enum proteinChoices {
        BEEF_BURGERS, CHICKEN_NUGGIES, BACON_BITES, FISH_STICKS, HARDBOLD_EGG, TOFU_STIRFRY, BEANS_AND_RICE, EDAMAME_SALAD, QUINOA_BOWL,
    }
    public enum drinkChoices {
        APPLE_JUICE,ORANGE_JUICE,CRANBERRY_JUICE,GRAPE_JUICE,LEMONADE,ICEDTEA,WATER,COCONUT_WATER,FRUITPUNCH,MILK,CHOCOLATE_MILK,STAWBERRY_MILK,WATER_BOTTLE,KID_AID,GEEZERS_LEMONAID
    }
    public EatingAndFood(String name, String description, FoodType foodType, int saturation) {
        super(name, description, description, true);
        this.name = name;
        this.description = description;
        this.foodType = foodType;
        this.saturation = saturation;
    }

    public void eat(FoodType foodType) {

    }

    public void drink(FoodType foodType) {

    }

    public void eat(FoodType foodType, int saturation) {

    }

    public void drink(FoodType foodType, int saturation) {

    }

    public FoodType getFoodType() {
        return foodType;
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }
}
