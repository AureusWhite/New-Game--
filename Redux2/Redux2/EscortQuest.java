
package Redux2;


public class EscortQuest extends Quest {

    private NPC requiredNPC;
    private Room requiredRoom;



    public EscortQuest(String name, String description, int difficulty, Room requiredRoom1, NPC requiredNPC1) {
        super(name, description, difficulty);
        this.requiredNPC = requiredNPC1;
        this.requiredRoom = requiredRoom1;     
    }
    
    @Override
    public boolean checkCompleted() {
        Room npcRoom = this.requiredNPC.getRoom();
        if (npcRoom == null) {
            return false;  // NPC is not in any room, so the quest is not completed
        }
        return npcRoom.equals(requiredRoom);
    }


    public NPC getRequiredNPC() {
        return requiredNPC;
    }

    public void setRequiredNPC(NPC requiredNPC) {
        this.requiredNPC = requiredNPC;
    }

    public Room getRequiredRoom() {
        return requiredRoom;
    }

    public void setRequiredRoom(Room requiredRoom) {
        this.requiredRoom = requiredRoom;
    }
    
}
