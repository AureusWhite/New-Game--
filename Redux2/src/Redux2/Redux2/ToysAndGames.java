package Redux2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.naming.RefAddr;

public class ToysAndGames {

    private final static Room mainRoom = GameHandler.getRoomByName("Main_Room");

    static Random rand = new Random();
    private static Timer timer;
    private static boolean gameOver = false;
    private static final ArrayList<NPC> playersIn = new ArrayList<>();
    private static final ArrayList<NPC> playersOut = new ArrayList<>();
    private static final NPC humanPlayer = new NPC("Player", "Human", mainRoom, "Human");
    private final static Item nuclearPotato = GameHandler.getItemByName("Nuclear Potato");

    //Hide and seek game methods
    public static void hideAndSeek() {
        if (Player.getRoom() != mainRoom) {
            GameHandler.getGui().display("You can only start games from the main room", "Black");
            return;
        }
        ArrayList<NPC> playingNpcs = getPlayers();
        ArrayList<NPC> hidingNpcs = new ArrayList<>();
        playersOut.clear();
        playersIn.clear();
        for (NPC npc : playingNpcs) {
            playersIn.add(npc);
        }
        NPC seeker;
        GameHandler.getGui().display("You are playing hide and seek with your friends", "Black");

        // Force the player to be the seeker
        NPC rando = playingNpcs.get(rand.nextInt(playingNpcs.size()));

        if (rando == humanPlayer) {
            GameHandler.getGui().display("You are the seeker", "Black");

            // NPCs will wander and hide
            for (NPC npc : playingNpcs) {
                playersIn.add(npc);
                GameHandler.getGui().display(npc.getName() + " is hiding", "Black");
                hidingNpcs.add(npc);
            }
            for (NPC npc : hidingNpcs) {
                triggerHiderWander(npc);
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
                    playersIn.add(npc);
                    GameHandler.getGui().display(npc.getName() + " is hiding", "Black");
                    triggerHiderWander(npc);
                    GameHandler.getGui().display(npc.getName() + " is wandering to hide!", "Black");  // NPC hides
                }
            }
        }
    }

    public static void triggerSeekerWander(NPC seeker) {
        GameHandler.getGui().display("You have 30 seconds to hide", "Black");

        // Create a new Timer
        Timer timer1 = new Timer();
        timer = timer1;

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
                timer.schedule(wanderTask, 0, 1500);
            }
        };

        // Schedule the seeker to start wandering after 30 seconds (30000 ms)
        timer.schedule(startSeekerTask, 30000);
    }

    public static void endGame() {
        if (gameOver) {
            return;
        }
        gameOver = true;

        if (timer != null) {
            timer.cancel();  // Cancel any remaining timers
        }

        GameHandler.getGui().display("The game has ended. Everyone is back in the main room.", "Black");

        // Move the player to the main room
        Player.setRoom(mainRoom);

        // Keep track of which NPCs have already been moved to avoid duplicates
        ArrayList<NPC> movedNPCs = new ArrayList<>();

        // Remove NPCs from all other rooms and move them to the main room
        for (NPC npc : getPlayers()) {
            if (!movedNPCs.contains(npc)) { // Ensure NPC hasn't already been moved
                // Get the current room of the NPC
                Room currentRoom = npc.getRoom();

                // Remove the NPC from the current room
                if (currentRoom != null) {
                    currentRoom.removeNPC(npc);
                }

                // Move the NPC to the main room and add only once
                npc.setRoom(mainRoom);

                // Add the NPC to the moved list
                movedNPCs.add(npc);
            }
        }

        // Clear the player lists for the next game
        playersIn.clear();
        playersOut.clear();
    }

    protected static void foundYou(NPC npc) {
        if (playersOut.contains(npc)) {
            return;
        }
        GameHandler.getGui().display(npc.getName() + " has been found!", "Black");

        // End game if all NPCs have been found
        if (playersIn.isEmpty()) {
            GameHandler.getGui().display("Everyone has been found!", "Black");
        }
    }

    //methods used by all games
    static ArrayList<NPC> getPlayers() {
        ArrayList<NPC> players = new ArrayList<>();
        for (NPC npc : Player.getRoom().getNPCs()) {
            if (npc.getType().containsKey(NPCType.ADULT)
                    || npc.getType().containsKey(NPCType.ENEMY)
                    || npc.getType().containsKey(NPCType.INFANT)
                    || npc.getStatus().containsKey(NPCStatus.TIRED)
                    || npc.getStatus().containsKey(NPCStatus.SLEEPING)
                    || npc.getStatus().containsKey(NPCStatus.BADMOOD)) {
                GameHandler.getGui().display(npc.getName() + " is not playing", "Black");
            } else {
                players.add(npc);
                GameHandler.getGui().display(npc.getName() + " is playing", "Black");
            }
        }
        players.add(humanPlayer);  // Ensure humanPlayer is added only once
        return players;
    }

    private static void triggerHiderWander(NPC npc) {
        if (playersOut.contains(npc)) {
            return;
        }
        // Create a new Timer
        Timer wanderTimer = new Timer();
        Timer checkTimer = new Timer();
        timer = wanderTimer;

        // Define a TimerTask to start wandering immediately
        TimerTask wanderTask = new TimerTask() {
            @Override
            public void run() {
                if (gameOver) {
                    wanderTimer.cancel();
                    checkTimer.cancel();
                    return;
                }

                // Make the hider wander
                npc.wander(RoomType.GREEN);
            }
        };

        // Start the wandering task every 5 seconds
        wanderTimer.schedule(wanderTask, 0, 5000);

        // Schedule a task to stop wandering after 30 seconds
        TimerTask stopWanderingTask = new TimerTask() {
            @Override
            public void run() {
                wanderTimer.cancel();  // Cancel the wander timer to stop movement
                GameHandler.getGui().display(npc.getName() + " has finished hiding!", "Black");
            }
        };

        // Stop the wandering after 30 seconds (30000 ms)
        wanderTimer.schedule(stopWanderingTask, 15000);

        // Define a TimerTask to check if the player is in the same room as the NPC
        TimerTask checkFoundTask = new TimerTask() {
            @Override
            public void run() {
                if (npc.getRoom() == Player.getRoom()) {
                    foundYou(npc);
                    playersOut.add(npc);
                    playersIn.remove(npc);
                    if (allplayersOut()) {
                        checkTimer.cancel();  // Stop checking if all players are "out"
                    }
                }
            }
        };

        // Start the checking task every second (1000 ms)
        checkTimer.scheduleAtFixedRate(checkFoundTask, 2000, 1000);
    }

    private static boolean allplayersOut() {
        if (playersIn.isEmpty()) {
            endGame();
            return true;

        } else {
            return false;
        }
    }

    public static NPC getHumanPlayer() {
        return humanPlayer;
    }

    public ToysAndGames() {
    }

    //tag game methods
    public void playTag(ArrayList<NPC> playingNpcs) {
        GameHandler.getGui().display("You are playing tag with your friends", "Black");
        int eenyMeenyMinyMoe = rand.nextInt(playingNpcs.size());
        NPC it = playingNpcs.get(eenyMeenyMinyMoe);
        GameHandler.getGui().display(it.getName() + " is it", "Black");
        for (NPC npc : playingNpcs) {
            if (npc == it) {
                triggerItWander(it); // The "it" NPC starts chasing
            } else {
                GameHandler.getGui().display(npc.getName() + " is running", "Black");
            }
        }
    }

    private void triggerItWander(NPC it) {
        GameHandler.getGui().display("You are it! You have 10 seconds to catch someone", "Black");

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
                        if (it.getRoom() == Player.getRoom()) {
                            GameHandler.getGui().display(it.getName() + " saw you, quick run!!", "Black");
                            // Determine if "it" catches the player using skill levels and RNG
                            if (it.getSkillLevel(Skill.GROSS_MOTOR) + rand.nextInt(5) > Player.getSkillLevel(Skill.GROSS_MOTOR) + rand.nextInt(10)) {
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

    public static void nukedPotato() {
        playersIn.clear();
        playersOut.clear();
        playersIn.add(humanPlayer);
        playersIn.addAll(getPlayers());
        if (Player.getRoom() != mainRoom) {
            GameHandler.getGui().display("You can only start games from the main room", "Black");
            return;
        }
        ArrayList<NPC> playingNpcs = getPlayers();
        final ArrayList<NPC> passingNpcs = new ArrayList<>(playingNpcs);
        final ArrayList<NPC> hadTurn = new ArrayList<>();
        GameHandler.getGui().display("You are playing nuked potato with your friends", "Black");
        GameHandler.getGui().display("You have 20 seconds till the potato explodes!", "Black");
        GameHandler.getGui().display("The potato timer gets a little longer each time it is passed", "Black");

        // Give potato to random player
        NPC potato = playingNpcs.get(rand.nextInt(playingNpcs.size()));
        potato.getInventory().add(nuclearPotato);
        GameHandler.getGui().display(potato.getName() + " has the potato", "Black");

        // Define a TimerTask to pass the potato after 3 seconds
        timer = new Timer();
        TimerTask potatoTask = new TimerTask() {
            @Override
            public void run() {
                ArrayList<NPC> toRemoveFromPassing = new ArrayList<>();  // List to collect NPCs to be removed

                // Use an iterator to safely modify the list while iterating over it
                Iterator<NPC> iterator = passingNpcs.iterator();
                while (iterator.hasNext()) {
                    NPC npc = iterator.next();
                    if (npc.getInventory().contains(nuclearPotato)) {
                        npc.getInventory().remove(nuclearPotato);
                        GameHandler.getGui().display(npc.getName() + " passed the potato", "Black");
                        toRemoveFromPassing.add(npc);  // Mark the NPC for removal
                        break;
                    }
                }

                // Remove NPCs after iteration to avoid ConcurrentModificationException
                passingNpcs.removeAll(toRemoveFromPassing);

                // Pass the potato to a random player
                if (!passingNpcs.isEmpty()) {
                    NPC newHolder = passingNpcs.get(rand.nextInt(passingNpcs.size()));
                    newHolder.getInventory().add(nuclearPotato);
                    GameHandler.getGui().display(newHolder.getName() + " has the potato", "Black");
                } else {
                    passingNpcs.clear();  // Reset the list of players
                    passingNpcs.addAll(playersIn);  // Reset the list of players
                }
            }
        };

        // Schedule the potato to be passed every 3 seconds
        timer.schedule(potatoTask, 0, 300);
        
        // Start the timer to explode the potato
            TimerTask explodeTask = new TimerTask() {
                @Override
                public void run() {
                    ArrayList<NPC> toRemoveFromGame = new ArrayList<>();  // List to collect NPCs to be removed

                    // Use an iterator to safely modify the list while iterating over it
                    Iterator<NPC> iterator = playingNpcs.iterator();
                    while (iterator.hasNext()) {
                        NPC npc = iterator.next();
                        if (npc.getInventory().contains(nuclearPotato)) {
                            GameHandler.getGui().display(npc.getName() + " exploded!", "Black");
                            GameHandler.getGui().display(npc.getName() + " has been eliminated", "Black");
                            toRemoveFromGame.add(npc);  // Mark the NPC for removal
                        }

                    }

                    // Remove NPCs after iteration to avoid ConcurrentModificationException
                    playersIn.removeAll(toRemoveFromGame);
                    playersOut.addAll(toRemoveFromGame);

                    if (playersIn.size() == 1) {
                        GameHandler.getGui().display(playersIn.get(0).getName() + " wins!", "Black");
                        timer.cancel();  // Stop the timer once all players are out
                    }

                }
            };

            // Schedule the potato to explode after 20 seconds
            timer.scheduleAtFixedRate(explodeTask, 2000, 2000);
        }
    }
