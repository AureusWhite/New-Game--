package Redux2;

public enum PlayerStatus {
    WET_DIAPER
    ,WET_CLOTHING
    ,DIRTY_CLOTHING
    ,CRYING
    ,UPSET
    ,HURT
    ,DISOBEDIENT
    ,SLEEPING
    ,HAPPY
    ,BORED
    ,SMELLY
    ,DIRTY_DIAPER
    ,DIRTY_HANDS
    ,DIRTY_FACE
    ,CARRIED
    ,HOLDING_HANDS
    ,CONTENT
    ,TANTRUM
    ,MELTDOWN
    ,JUBILANT
    ,DIRTY
    ,TIRED
    ,HUNGRY
    ,THIRSTY
    ,SICK, HIDDEN, LEADER;
    public void activate() {
        Player.getStatus().add(this);
    }
    public void deactivate() {
        Player.getStatus().remove(this);
    }
    public void toggle() {
        if (isActive()) {
            deactivate();
        } else {
            activate();
        }
    }
    public void display() {
        System.out.println("Status: " + this);
    }
    public void checkMuddy() {
        if (Player.getStatus().contains(WET_CLOTHING)&&Player.getStatus().contains(DIRTY_CLOTHING)) {
            GameHandler.getGui().display("Your clothing is muddy", "Black");
        }   
    }
    public boolean isActive() {
        return Player.getStatus().contains(this);
    }
}