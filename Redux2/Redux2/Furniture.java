package Redux2;

import java.util.HashMap;

public class Furniture extends Item {
    HashMap<FurnitureType, Boolean> furniture = new HashMap<>();
    private final FurnitureType type;
    private boolean isOccupied;
    private boolean isLocked;
    private boolean isLocking;
    private final String useDescription;

    public Furniture(FurnitureType type, String name, String description, String useDescription) {
        super(name, description, useDescription, false);
        this.type = type;
        this.isOccupied = false;
        this.isLocked = false;
        this.isLocking = false;
        furniture.put(type, true);
        this.name = name;
        this.description = description;
        this.useDescription = useDescription;
    }
    public FurnitureType getType() {
        return this.type;
    }
    public String getUseDescription() {
        return this.useDescription;
    }
    public boolean getOccupied() {
        return this.isOccupied;
    }
    public boolean getLocked() {
        return this.isLocked;
    }
    public boolean getLocking() {
        return this.isLocking;
    }
    public void setOccupied(boolean occupied) {
        this.isOccupied = occupied;
    }
    public void setLocking(boolean locking) {
        this.isLocking = locking;
    }
    public void lock() {
        if (this.isLocking) {
            this.isLocked = true;
        }
    }
    public void unlock() {
        if (this.isLocking) {
            this.isLocked = false;
        }
    }
    public void occupy() {
        if (this.isOccupied) {
            this.isOccupied = true;
        }
    }
    public void vacate() {
        if (this.isOccupied) {
            this.isOccupied = false;
        }
    }
    public void toggleLock() {
        if (this.isLocking) {
            this.isLocked = !this.isLocked;
        }
    }
    public void toggleOccupied() {
        if (this.isOccupied) {
            this.isOccupied = !this.isOccupied;
        }
    }
    public void toggleLocking() {
        this.isLocking = !this.isLocking;
    }
    @Override
    public void use() {
        System.out.println(this.useDescription);
    }
    public void examine() {
        System.out.println(this.description);
    }
    public void toggle() {
        if (this.isOccupied) {
            this.isOccupied = !this.isOccupied;
        }
        if (this.isLocked) {
            this.isLocked = !this.isLocked;
        }
    }
    public void toggleLocking(boolean locking) {
        this.isLocking = locking;
    }
    public void toggleLocked() {
        this.isLocked = !this.isLocked;
    }
    public void toggleOccupied(boolean occupied) {
        this.isOccupied = occupied;
    }
    public void sitOn(){
        System.out.println("You sit on the " + this.name);
    }
    public void standUp(){
        System.out.println("You stand up from the " + this.name);
    }
    public void climbOut(){
        System.out.println("You climb out of the " + this.name);
    }
    public void climbOn(){
        System.out.println("You climb on the " + this.name);
    }
    public void climbIn(){
        System.out.println("You climb in the " + this.name);
    }
    public void beSatIn(NPC npc){
        if(npc==null){
            Player.beSat(this);
                this.isOccupied = true;
                this.isLocked = true;
                this.isLocking = true;       
        }

    }


    
}
