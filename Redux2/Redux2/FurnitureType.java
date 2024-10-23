package Redux2;

public enum FurnitureType {
    //Furniture Types
    BOOSTERSEAT, TABLE, BED, CUPBOARD, SHELF, CLOSET, DRAWER, CABINET, DESK, CHAIR, BENCH, STOOL, COUCH, SOFA, LOVESEAT, OTTOMAN, FUTON, DAYBED, CRIB, BUNK, TRUNDLE, MATTRESS, BOXSPRING, HEADBOARD, FOOTBOARD, NIGHTSTAND, DRESSER, MIRROR, WARDROBE, ARMOIRE, CHEST, HUTCH, BUFFET, SIDEBOARD, CREDENZA, ENTERTAINMENT, TV, STAND, COFFEE, END, CONSOLE, VANITY, HIGHCHAIR, ROCKER, RECLINER, GLIDER, SWING, HAMMOCK, WALKER, CRADLE, PLAYPEN, PLAYARD, PLAYMAT, PLAYGYM, PLAYHOUSE, PLAYTENT, PLAYCUBE, PLAYTUBE, PLAYTUNNEL, PLAYSLIDE, PLAYSWING, CHANGINGTABLE, DIAPERWIZARD, TOYCHEST, STEPSTOOL, POTTY, BATHTUB, BATHSEAT;

    boolean locking;
    boolean locked;
    boolean occupied;

    FurnitureType() {
        this.locking = false;
        this.locked = false;
        this.occupied = false;
    }

    public boolean getLocking() {
        return this.locking;
    }

    public boolean getLocked() {
        return this.locked;
    }

    public boolean getOccupied() {
        return this.occupied;
    }

    public void setLocking(boolean locking) {
        this.locking = locking;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public void lock() {
        if (this.locking) {
            this.locked = true;
        }
    }

    public void unlock() {
        if (this.locking) {
            this.locked = false;
        }
    }

    public void occupy() {
        if (this.occupied) {
            this.occupied = true;
        }
    }

    public void vacate() {
        if (this.occupied) {
            this.occupied = false;
        }
    }

    public void toggleLock() {
        if (this.locking) {
            this.locked = !this.locked;
        }
    }

    public void toggleOccupied() {
        if (this.occupied) {
            this.occupied = !this.occupied;
        }
    }

    public void toggleLocking() {
        this.locking = !this.locking;
    }

    public void toggleLocking(boolean locking) {
        this.locking = locking;
    }

    public void toggleLocked() {
        this.locked = !this.locked;
    }

    public void toggleOccupied(boolean occupied) {
        this.occupied = occupied;
    }

}
