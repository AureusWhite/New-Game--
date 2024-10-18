package Redux2;

public enum Events {
    // Events
    STORY_TIME("Story Time",GameHandler.getRoomByName("Main_Room"),1);
    private final int importance;
    private final String name;
    private final Room room;
    Events(String name,Room room,int importance) {
        this.name = name;
        this.importance = importance;
        this.room = room;
    }

    public int getImportance() {
            return importance;
    }

    public String getName() {
        return this.name;
    }

    public Room getRoom() {
        return room;
    }
    public Events getSTORY_TIME(){
        return STORY_TIME;
    }
    public void display(){
        System.out.println("Event: " + name);
        System.out.println("Room: " + room.getName());
        System.out.println("Importance: " + importance);
    }
    
    
    
}