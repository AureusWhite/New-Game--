
package Redux2;



public class CleaningQuest extends Quest {
    Item mess, trash, spill, puddle;
    Room requiredRoom;

    public CleaningQuest(String name, String description, int difficulty, Room requiredRoom) {
        super(name, description, difficulty);
        this.requiredRoom = requiredRoom;

        mess = GameHandler.getItemByName("Mess");
        trash = GameHandler.getItemByName("Trash");
        spill = GameHandler.getItemByName("Spill");
        puddle = GameHandler.getItemByName("Puddle");
    }
@Override
    public boolean checkCompleted() {
        for(Quest quest : Player.getQuests()){
            if(quest instanceof CleaningQuest){
                if (requiredRoom.getInventory().contains(mess) || requiredRoom.getInventory().contains(trash) || requiredRoom.getInventory().contains(spill) || requiredRoom.getInventory().contains(puddle)) {
                    System.out.println("The room is not clean");
                    return false;
                } else {
                    System.out.println("The room is clean");
                    return true;
                }
            }
        }
        return false;
    }    
}
