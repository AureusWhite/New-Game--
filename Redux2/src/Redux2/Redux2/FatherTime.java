package Redux2;

import java.util.HashSet;
import java.util.Set;
import javax.swing.JOptionPane;

public class FatherTime {

    private static final Set<String> notifiedEvents = new HashSet<>();
    static Clock clock = new Clock();

    public static Clock getClock() {
        return clock;
    }

    static void nextPhase() {
        if (currentPhase == null) {
            currentPhase = DayPhase.MORNING;
        }
        RoutineManager.isFollowingRoutine(currentPhase);
        currentPhase = switch (currentPhase) {
            case MORNING ->
                DayPhase.BREAKFAST;
            case BREAKFAST ->
                DayPhase.SCHOOL_STRUCTUREDPLAY;
            case SCHOOL_STRUCTUREDPLAY ->
                DayPhase.LUNCH;
            case LUNCH ->
                DayPhase.FREETIME;
            case FREETIME ->
                DayPhase.DINNER;
            case DINNER ->
                DayPhase.TIDYUP;
            case TIDYUP ->
                DayPhase.BATHS_BRUSHES;
            case BATHS_BRUSHES ->
                DayPhase.DORMS;
            case DORMS ->
                DayPhase.MORNING;
            default ->
                DayPhase.MORNING;
        };
    }

    static void setPhase(DayPhase dayPhase) {
        currentPhase = dayPhase;
    }

    public enum DayPhase {
        MORNING, LUNCH, DINNER, GREEN, BLUE, RED, FREETIME, SCHOOL_STRUCTUREDPLAY, TIDYUP, BATHS_BRUSHES, DORMS, BREAKFAST
    }
    private static DayPhase currentPhase;

    public static DayPhase getCurrentPhase() {
        return currentPhase;
    }

    public static String getFormmatedCurrentPhase() {
        return switch (getCurrentPhase()) {
            case LUNCH ->
                "Lunch";
            case DINNER ->
                "Dinner";
            case GREEN ->
                "You may move around freely";
            case BLUE ->
                "Class or Activity";
            case RED ->
                "Return to Dorms";
            case FREETIME ->
                "Free Play";
            case SCHOOL_STRUCTUREDPLAY ->
                "School or Structured Play";
            case TIDYUP ->
                "Tidy Up";
            case BATHS_BRUSHES ->
                "Baths and Brushes";
            case DORMS ->
                "Bed Time";
            case BREAKFAST ->
                "Breakfast";
            default ->
                "Free Play";
        };
    }

    public void smallAction() {
        clock.moveTime(15);
    }

    public static void mediumAction() {
        clock.moveTime(30);
    }

    public void longAction(int time) {
        clock.moveTime(time * 60);
    }

    public void changePhase(DayPhase phase) {
        currentPhase = phase;
    }
    public static void triggerEvent() {
        String currentRoom = Player.getRoom().getName().toLowerCase();
        int currentHour = clock.getCurrentHour();
    
        GameHandler.getGui().display("Current Hour: " + currentHour + ", Current Room: " + currentRoom, "Black");
    
        switch (currentHour) {
            case 6, 7 -> {
                GameHandler.getGui().display("Checking breakfast event", "Black");
                notifyEvent("breakfast", currentRoom.equals("snack_area"), "It's time for breakfast. What would you like to do?",
                        new String[]{"Breakfast", "Sleep in", "Free Play"},
                        () -> Player.setRoom(GameHandler.getRoomByName("Snack_Area")),
                        () -> {
                            mediumAction();
                            Player.setHunger(Player.getHunger() - 40);
                        },
                        () -> {
                            Player.setRoom(GameHandler.getRoomByName("Main_Room"));
                            Player.setHunger(Player.getHunger() - 40);
                        }
                );
            }
            case 8, 9, 10 -> {
                GameHandler.getGui().display("Checking school event", "Black");
                notifyEvent("school", currentRoom.equals("classroom"), "It's time for school. What would you like to do?",
                        new String[]{"School", "Free Play", "Skip School"},
                        () -> Player.setRoom(GameHandler.getRoomByName("Classroom")),
                        () -> Player.setRoom(GameHandler.getRoomByName("Main_Room")),
                        () -> Player.setStatus(PlayerStatus.DISOBEDIENT)
                );
            }
            case 11, 12 -> {
                GameHandler.getGui().display("Checking lunch event", "Black");
                notifyEvent("lunch", currentRoom.equals("snack_area"), "It's time for lunch. What would you like to do?",
                        new String[]{"Lunch", "Free Play", "Skip Lunch"},
                        () -> Player.setRoom(GameHandler.getRoomByName("Snack_Area")),
                        () -> {
                            Player.setRoom(GameHandler.getRoomByName("Main_Room"));
                            Player.setHunger(Player.getHunger() - 40);
                        },
                        () -> Player.setStatus(PlayerStatus.DISOBEDIENT)
                );
            }
            case 13, 14 -> {
                GameHandler.getGui().display("Checking structured play event", "Black");
                notifyEvent("structured play", currentRoom.equals("classroom") || currentRoom.equals("main_room"), "It's free time. What would you like to do?",
                        new String[]{"Free Play", "Structured Play", "Skip PlayGroup"},
                        () -> Player.setRoom(GameHandler.getRoomByName("Main_Room")),
                        () -> Player.setRoom(GameHandler.getRoomByName("Classroom")),
                        () -> Player.setStatus(PlayerStatus.DISOBEDIENT)
                );
            }
            case 15, 16, 17 -> {
                GameHandler.getGui().display("Checking free time event", "Black");
                notifyEvent("free time", currentRoom.equals("main_room"), "You may move around freely", null, null, null, null);
            }
            case 18 -> {
                GameHandler.getGui().display("Checking dinner event", "Black");
                notifyEvent("dinner", currentRoom.equals("snack_area"), "It's time for dinner. What would you like to do?",
                        new String[]{"Dinner", "Free Play", "Skip Dinner"},
                        () -> Player.setRoom(GameHandler.getRoomByName("Snack_Area")),
                        () -> {
                            Player.setRoom(GameHandler.getRoomByName("Main_Room"));
                            Player.setHunger(Player.getHunger() - 40);
                        },
                        () -> Player.setStatus(PlayerStatus.DISOBEDIENT)
                );
            }
            case 20 -> {
                GameHandler.getGui().display("Checking tidy up event", "Black");
                notifyEvent("tidy up", false, "It's time to tidy up. What would you like to do?",
                        new String[]{"Tidy Up", "Free Play", "Skip Tidy Up"},
                        () -> GameHandler.getGui().display("You decide to tidy up", "Black"),
                        () -> Player.setRoom(GameHandler.getRoomByName("Main_Room")),
                        () -> Player.setStatus(PlayerStatus.DISOBEDIENT)
                );
            }
            case 21 -> {
                GameHandler.getGui().display("Checking baths and brushes event", "Black");
                notifyEvent("baths and brushes", currentRoom.equals("bathroom"), "It's time for baths and brushes. What would you like to do?",
                        new String[]{"Bath and Brush", "Skip Bath", "Don't Brush"},
                        () -> Player.setRoom(GameHandler.getRoomByName("Bathroom")),
                        () -> Player.setStatus(PlayerStatus.SMELLY),
                        () -> Player.setStatus(PlayerStatus.SMELLY)
                );
            }
            case 22 -> {
                GameHandler.getGui().display("Checking bed time event", "Black");
                notifyEvent("bed time", currentRoom.equals("dorms"), "It's time for bed. What would you like to do?",
                        new String[]{"Bed Time", "Free Play"},
                        () -> Player.setRoom(GameHandler.getRoomByName("Dorms")),
                        () -> {
                            Player.setRoom(GameHandler.getRoomByName("Main_Room"));
                            Player.setEnergy(Player.getEnergy() - 40);
                        },
                        () -> resetForNewDay() // Reset notifications for a new day
                );
            }
        }
    }
    
    private static void notifyEvent(String event, boolean isInCorrectRoom, String message, String[] options, Runnable action1, Runnable action2, Runnable action3) {
        if (isInCorrectRoom || notifiedEvents.contains(event)) {
            GameHandler.getGui().display("Event already notified or player in correct room: " + event, "Black");
            return; // Don't notify if player is already attending or notified
        }
    
        if (options != null) {
            int choice = JOptionPane.showOptionDialog(null, message, event, JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    
            switch (choice) {
                case 0 -> {
                    if (action1 != null) {
                        action1.run();
                        notifiedEvents.add(event); // Mark event as notified only if attended
                    }
                }
                case 1 -> {
                    if (action2 != null) {
                        action2.run();
                        notifiedEvents.add(event); // Mark event as notified only if attended
                    }
                }
                case 2 -> {
                    if (action3 != null) {
                        action3.run();
                        notifiedEvents.add(event); // Mark event as notified only if attended
                    }
                }
                default -> {
                }
            }
        }
    }
    private static void resetForNewDay() {
        notifiedEvents.clear();
    }
}

