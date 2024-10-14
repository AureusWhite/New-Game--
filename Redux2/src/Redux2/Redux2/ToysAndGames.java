package Redux2;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class ToysAndGames {
    Random rand = new Random();
    ArrayList<NPC> npcs = new ArrayList<NPC>();
    private Timer timer;
    private boolean gameOver = false;
    private ArrayList<NPC> PlayersIn = new ArrayList<NPC>();
    private ArrayList<NPC> PlayersOut = new ArrayList<NPC>();

    public ToysAndGames() {}

    public void hideAndSeek(ArrayList<NPC> playingNpcs) {
        NPC seeker;
        GameHandler.getGui().display("You are playing hide and seek with your friends", "Black");
        
        // Force the player to be the seeker
        int eenyMeenyMinyMoe = 3;  // This ensures the player is always the seeker
    
        if (eenyMeenyMinyMoe == 3) {
            GameHandler.getGui().display("You are the seeker", "Black");
            seeker = null;  // Player is the seeker
    
            // NPCs will wander and hide
            for (NPC npc : playingNpcs) {
                PlayersIn.add(npc);
                GameHandler.getGui().display(npc.getName() + " is hiding", "Black");
                triggerHiderWander(npc);  // Make the NPCs wander and hide
            }
    
        } else {
            // Handle the case where an NPC is the seeker
            GameHandler.getGui().display("You are the hider", "Black");
            seeker = playingNpcs.get(rand.nextInt(playingNpcs.size()));
            for (NPC npc : playingNpcs) {
                if (npc == seeker) {
                    GameHandler.getGui().display(npc.getName() + " is the seeker", "Black");
                    triggerSeekerWander(seeker);  // Make the NPC seek
                } else {
                    PlayersIn.add(npc);
                    GameHandler.getGui().display(npc.getName() + " is hiding", "Black");
                    triggerHiderWander(npc);  // NPC hides
                }
            }
        }
    }
    
    private void triggerHiderWander(NPC npc) {
        // Create a new Timer
        Timer timer = new Timer();
        this.timer = timer;
    
        // Define a TimerTask to start wandering immediately
        TimerTask wanderTask = new TimerTask() {
            @Override
            public void run() {
                if (gameOver) {
                    npc.setRoom(GameHandler.getRoomByName("Main_Room"));
                    timer.cancel();
                    return;
                }
    
                // Make the hider wander
                npc.wander(RoomType.GREEN);
                GameHandler.getGui().display(npc.getName() + " is wandering to hide!", "Black");
            }
        };
    
        // Start the wandering task every 5 seconds
        timer.schedule(wanderTask, 0, 5000);
    
        // Schedule a task to stop wandering after 30 seconds
        TimerTask stopWanderingTask = new TimerTask() {
            @Override
            public void run() {
                timer.cancel();  // Cancel the wander timer to stop movement
                GameHandler.getGui().display(npc.getName() + " has finished hiding!", "Black");
                if (npc.getRoom() == Player.getRoom()) {
                    foundYou(npc);  // Handle the logic when the NPC is found
                }
            }
        };
    
        // Stop the wandering after 30 seconds (30000 ms)
        timer.schedule(stopWanderingTask, 30000);
    }
    
    protected void foundYou(NPC npc) {
        this.PlayersOut.add(npc);
        this.PlayersIn.remove(npc);
        GameHandler.getGui().display(npc.getName() + " has been found!", "Black");
        
        // End game if all NPCs have been found
        if (PlayersIn.size() == 0) {
            GameHandler.getGui().display("Everyone has been found!", "Black");
            endGame();
        }
    }

    public void triggerSeekerWander(NPC seeker) {
        GameHandler.getGui().display("You have 30 seconds to hide", "Black");
        
        // Create a new Timer
        Timer timer = new Timer();
        this.timer = timer;
        
        // Define a TimerTask to start wandering after 30 seconds
        TimerTask startSeekerTask = new TimerTask() {
            @Override
            public void run() {
                GameHandler.getGui().display(seeker.getName() + " is now seeking!", "Black");
                
                // Define a task for the seeker to wander every 5 seconds
                TimerTask wanderTask = new TimerTask() {
                    @Override
                    public void run() {
                        if (gameOver) {
                            seeker.setRoom(GameHandler.getRoomByName("Main_Room"));
                            timer.cancel();
                            return;
                        }
                        
                        // Seeker wanders within the allowed room type
                        if (!Player.getRoom().getType().equals(RoomType.GREEN)) {
                            GameHandler.getGui().display("RULE BREAKER!!! " + seeker.getName() + " is not allowed to be in this room", "Black");
                            endGame();
                        } else {
                            seeker.wander(RoomType.GREEN);
                        }

                        if (seeker.getRoom() == Player.getRoom()) {
                            GameHandler.getGui().display(seeker.getName() + " found you!", "Black");
                            timer.cancel();  // Stop the timer once the player is found
                        } else {
                            GameHandler.getGui().display(seeker.getName() + " is still searching...", "Black");
                        }
                    }
                };
    
                // Start the wandering task every 5 seconds
                timer.schedule(wanderTask, 0, 5000);
            }
        };
        
        // Schedule the seeker to start wandering after 30 seconds (30000 ms)
        timer.schedule(startSeekerTask, 30000);
    }
    
    ArrayList<NPC> getPlayers() {
        ArrayList<NPC> players = Player.getRoom().getNPCs();
        for (NPC npc : players) {
            if (npc.getType().containsKey(NPCType.ADULT) || 
                npc.getType().containsKey(NPCType.ENEMY) || 
                npc.getType().containsKey(NPCType.INFANT) ||
                npc.getStatus().containsKey(NPCType.TIRED) ||
                npc.getStatus().containsKey(NPCType.SLEEPING) ||
                npc.getStatus().containsKey(NPCType.BADMOOD)) {
                GameHandler.getGui().display(npc.getName() + " is not playing", "Black");
            } else {
                npcs.add(npc);
                GameHandler.getGui().display(npc.getName() + " is playing", "Black");
            }
        }
        return players;
    }

    public void endGame() {
        gameOver = true;
        if (timer != null) {
            timer.cancel();  // Cancel any remaining timers
        }
        GameHandler.getGui().display("The game has ended. Everyone is back in the main room.", "Black");
        
        // Move all players and NPCs to the main room
        Room mainRoom = GameHandler.getRoomByName("Main_Room");
        Player.setRoom(mainRoom);
        
        for (NPC npc : npcs) {
            npc.setRoom(mainRoom);
        }
    }
    public void playTag(ArrayList<NPC> playingNpcs) {
        GameHandler.getGui().display("You are playing tag with your friends","Black");
        int eenyMeenyMinyMoe = rand.nextInt(playingNpcs.size());
        NPC it = playingNpcs.get(eenyMeenyMinyMoe);
        GameHandler.getGui().display(it.getName() + " is it","Black");
        for(NPC npc : playingNpcs){
            if(npc == it){
                triggerItWander(it); // The "it" NPC starts chasing
            } else {
                GameHandler.getGui().display(npc.getName() + " is running","Black");
            }
        }
    }
    
    private void triggerItWander(NPC it) {
        GameHandler.getGui().display("You are it! You have 10 seconds to catch someone", "Black");
        
        // Create a new Timer
        Timer timer = new Timer();
        this.timer = timer;
        
        // Define a TimerTask to start wandering after 10 seconds
        TimerTask startItTask = new TimerTask() {
            @Override
            public void run() {
                GameHandler.getGui().display(it.getName() + " is now chasing!", "Black");
                
                // Define a task for the "it" NPC to wander every 2 seconds
                TimerTask wanderTask = new TimerTask() {
                    @Override
                    public void run() {
                        // Make "it" wander
                        it.wander(RoomType.GREEN); // Chasing only in "GREEN" rooms
                        if(gameOver == true){
                            it.setRoom(GameHandler.getRoomByName("Main_Room"));
                            return;
                        }
                        if (it.getRoom() == Player.getRoom()) {
                            GameHandler.getGui().display(it.getName() + " saw you, quick run!!", "Black");
                            // Determine if "it" catches the player using skill levels and RNG
                            if(it.getSkillLevel(Skill.GROSS_MOTOR) + rand.nextInt(5) > Player.getSkillLevel(Skill.GROSS_MOTOR) + rand.nextInt(10)){
                                GameHandler.getGui().display(it.getName() + " caught you!", "Black");
                                timer.cancel();  // Stop chasing once caught
                            } else {
                                GameHandler.getGui().display(it.getName() + " missed you!", "Black");
                            }
                        } else {
                            GameHandler.getGui().display(it.getName() + " is still chasing...", "Black");
                        }
                    }
                };
    
                // Start the wandering task every 2 seconds
                timer.schedule(wanderTask, 0, 2000);
            }
        };
        
        // Schedule "it" to start wandering after 10 seconds
        timer.schedule(startItTask, 10000);
    }    
}
