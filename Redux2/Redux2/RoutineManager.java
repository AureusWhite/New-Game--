package Redux2;

import java.util.HashMap;
import java.util.Map;

public class RoutineManager {

    private static final HashMap<FatherTime.DayPhase, Boolean> routine = new HashMap<>();

    public static HashMap<FatherTime.DayPhase, Boolean> getRoutine() {
        return routine;
    }

    public RoutineManager() {
        for (FatherTime.DayPhase phase : FatherTime.DayPhase.values()) {
            routine.put(phase, false);
        }
    }

    public static void isFollowingRoutine(FatherTime.DayPhase phase) {
        switch (phase) {
            case MORNING:
                if (!Player.getRoom().equals(GameHandler.getRoomByName("Dorms"))) {
                    routine.put(FatherTime.DayPhase.MORNING, true);
                    GameHandler.getGui().display("Right Place", "Black");
                }
                break;
            case BREAKFAST:
                if (Player.getRoom().equals(GameHandler.getRoomByName("Snack_Area"))) {
                    routine.put(FatherTime.DayPhase.BREAKFAST, true);
                    GameHandler.getGui().display("Right Place", "Black");
                }
                break;
            case LUNCH:
                if (Player.getRoom().equals(GameHandler.getRoomByName("Snack_Area"))) {
                    routine.put(FatherTime.DayPhase.LUNCH, true);
                    GameHandler.getGui().display("Right Place", "Black");
                }
                break;
            case DINNER:
                if (Player.getRoom().equals(GameHandler.getRoomByName("Snack_Area"))) {
                    routine.put(FatherTime.DayPhase.DINNER, true);
                    GameHandler.getGui().display("Right Place", "Black");
                }
                break;
            case GREEN:

                if (Player.getRoom().equals(GameHandler.getRoomByName("Main_Room"))) {
                    routine.put(FatherTime.DayPhase.GREEN, true);
                    GameHandler.getGui().display("Right Place", "Black");
                }
                break;
            case BLUE:
                if (Player.getRoom().equals(GameHandler.getRoomByName("Classroom"))) {
                    routine.put(FatherTime.DayPhase.BLUE, true);
                    GameHandler.getGui().display("Right Place", "Black");
                }
                break;
            case RED:
                if (Player.getRoom().equals(GameHandler.getRoomByName("Dorms"))) {
                    routine.put(FatherTime.DayPhase.RED, true);
                    GameHandler.getGui().display("Right Place", "Black");
                }
                break;
            case FREETIME:
                if (Player.getRoom().equals(GameHandler.getRoomByName("Main_Room"))) {
                    routine.put(FatherTime.DayPhase.FREETIME, true);
                    GameHandler.getGui().display("Right Place", "Black");
                }
                break;
            case SCHOOL_STRUCTUREDPLAY:
                if (Player.getRoom().equals(GameHandler.getRoomByName("Classroom"))) {
                    routine.put(FatherTime.DayPhase.SCHOOL_STRUCTUREDPLAY, true);
                    GameHandler.getGui().display("Right Place", "Black");
                }
                break;
            case TIDYUP:
                if (!Player.getRoom().containsItem("Trash")) {
                    routine.put(FatherTime.DayPhase.TIDYUP, true);
                    GameHandler.getGui().display("Right Action", "Black");
                    break;
                }
            case BATHS_BRUSHES:
                if (Player.getRoom().equals(GameHandler.getRoomByName("Bathroom"))) {
                    routine.put(FatherTime.DayPhase.BATHS_BRUSHES, true);
                    GameHandler.getGui().display("Right Place", "Black");
                }
                break;
            case DORMS:
                if (Player.getRoom().equals(GameHandler.getRoomByName("Dorms"))) {
                    routine.put(FatherTime.DayPhase.DORMS, true);
                    GameHandler.getGui().display("Right Place", "Black");
                }
                break;
            case NAP:
                if (Player.getRoom().equals(GameHandler.getRoomByName("Dorms"))) {
                    routine.put(FatherTime.DayPhase.NAP, true);
                    GameHandler.getGui().display("Right Place", "Black");
                }
                break;
                case NIGHT:
                    if (Player.getRoom().equals(GameHandler.getRoomByName("Dorms"))) {
                        routine.put(FatherTime.DayPhase.NIGHT, true);
                        GameHandler.getGui().display("Right Place", "Black");
                    }
                    break;
        }
    }
        public static boolean checkRoutine() {
            GameHandler.getGui().display("Checking Routine", "Black");
            
            Map<FatherTime.DayPhase, String> requirements = new HashMap<>();
            requirements.put(FatherTime.DayPhase.MORNING, "be out of bed by 8am");
            requirements.put(FatherTime.DayPhase.BATHS_BRUSHES, "have a bath and brush your teeth between 8:30pm-9pm");
            requirements.put(FatherTime.DayPhase.BREAKFAST, "eat breakfast between 8am-9:30am");
            requirements.put(FatherTime.DayPhase.LUNCH, "eat lunch between 12pm-1pm");
            requirements.put(FatherTime.DayPhase.DINNER, "eat dinner between 5pm-6pm");
            requirements.put(FatherTime.DayPhase.GREEN, "spend time in the main room between 6pm-7pm");
            requirements.put(FatherTime.DayPhase.BLUE, "spend time in the classroom between 10am-3pm");
            requirements.put(FatherTime.DayPhase.SCHOOL_STRUCTUREDPLAY, "participate in structured play between 10am-3pm");
            requirements.put(FatherTime.DayPhase.TIDYUP, "tidy up the main room between 7pm-8pm");
            requirements.put(FatherTime.DayPhase.DORMS, "be in bed by 9pm");
            requirements.put(FatherTime.DayPhase.FREETIME, "play in the main room during green time 3pm-7pm");
        
            // Debug: Print the current state of the routine map
            for (Map.Entry<FatherTime.DayPhase, Boolean> entry : routine.entrySet()) {
                GameHandler.getGui().display(entry.getKey() + ": " + entry.getValue(), "Black");
            }
        
            boolean allCompleted = true;
            StringBuilder incompleteMessages = new StringBuilder();
        
            // Check for incomplete phases
            for (Map.Entry<FatherTime.DayPhase, Boolean> entry : routine.entrySet()) {
                if (!entry.getValue()) {
                    String requirement = requirements.get(entry.getKey());
                    if (requirement != null) {
                        incompleteMessages.append("You need to ").append(requirement).append(" to complete your routine<br>");
                    }
                    allCompleted = false;
                }
            }
        
            if (incompleteMessages.length() > 0) {
                GameHandler.getGui().display(incompleteMessages.toString(), "Black");
            }
        
            return allCompleted;
        }
}
