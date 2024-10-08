package Redux2;

import java.util.HashMap;

public class RoutineManager {
    private static HashMap<FatherTime.DayPhase,Boolean> routine = new HashMap<>();

    public RoutineManager(){
        for(FatherTime.DayPhase phase : FatherTime.DayPhase.values()){
            routine.put(phase, false);
        }
    }
    public static void isFollowingRoutine(FatherTime.DayPhase phase){
        switch(phase){
            case MORNING:
            if(!Player.getRoom().equals(GameHandler.getRoomByName("Dorms"))){
                routine.put(FatherTime.DayPhase.MORNING, true);
                GameHandler.getGui().display("Right Place","Black");
            }
            break;
            case BREAFAST:
            if(Player.getRoom().equals(GameHandler.getRoomByName("Snack_Area"))){
                routine.put(FatherTime.DayPhase.BREAFAST, true);
                GameHandler.getGui().display("Right Place","Black");
            }
            break;
            case LUNCH:
            if(Player.getRoom().equals(GameHandler.getRoomByName("Snack_Area"))){
                routine.put(FatherTime.DayPhase.LUNCH, true);
                GameHandler.getGui().display("Right Place","Black");
            }
            break;
            case DINNER:
            if(Player.getRoom().equals(GameHandler.getRoomByName("Snack_Area"))){
                routine.put(FatherTime.DayPhase.DINNER, true);
                GameHandler.getGui().display("Right Place","Black");
            }
            break;
            case GREEN:

            if(Player.getRoom().equals(GameHandler.getRoomByName("Main_Room"))){
                routine.put(FatherTime.DayPhase.GREEN, true);
                GameHandler.getGui().display("Right Place","Black");
            }
            break;
            case BLUE:
            if(Player.getRoom().equals(GameHandler.getRoomByName("Classroom"))){
                routine.put(FatherTime.DayPhase.BLUE, true);
                GameHandler.getGui().display("Right Place","Black");
            }
            break;
            case RED:
            if(Player.getRoom().equals(GameHandler.getRoomByName("Dorms"))){
                routine.put(FatherTime.DayPhase.RED, true);
                GameHandler.getGui().display("Right Place","Black");
            }
            break;
            case FREETIME:
            if(Player.getRoom().equals(GameHandler.getRoomByName("Main_Room"))){
                routine.put(FatherTime.DayPhase.FREETIME, true);
                GameHandler.getGui().display("Right Place","Black");
            }
            break;
            case SCHOOL_STRUCTUREDPLAY:
            if(Player.getRoom().equals(GameHandler.getRoomByName("Classroom"))){
                routine.put(FatherTime.DayPhase.SCHOOL_STRUCTUREDPLAY, true);
                GameHandler.getGui().display("Right Place","Black");
            }
            break;
            case TIDYUP:
            if(!Player.getRoom().containsItem("Trash")){
                routine.put(FatherTime.DayPhase.TIDYUP, true);
                GameHandler.getGui().display("Right Action","Black");
                break;
            }
            case BATHS_BRUSHES:
            if(Player.getRoom().equals(GameHandler.getRoomByName("Bathroom"))){
                routine.put(FatherTime.DayPhase.BATHS_BRUSHES, true);
                GameHandler.getGui().display("Right Place","Black");
            }
            break;
            case DORMS:
            if(Player.getRoom().equals(GameHandler.getRoomByName("Dorms"))){
                routine.put(FatherTime.DayPhase.DORMS, true);
                GameHandler.getGui().display("Right Place","Black");
            }
            break;
        }
    }
}
