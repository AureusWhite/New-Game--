package Redux2;

public class FatherTime {

    static void nextPhase() {
        if(currentPhase == null){
            currentPhase = DayPhase.MORNING;
        }
        RoutineManager.isFollowingRoutine(currentPhase);
        switch(currentPhase){
            case MORNING:
                currentPhase = DayPhase.BREAFAST;
                break;
            case BREAFAST:
                currentPhase = DayPhase.LUNCH;
                break;
            case LUNCH:
                currentPhase = DayPhase.DINNER;
                break;
            case DINNER:
                currentPhase = DayPhase.GREEN;
                break;
            case GREEN:
                currentPhase = DayPhase.BLUE;
                break;
            case BLUE:
                currentPhase = DayPhase.RED;
                break;
            case RED:
                currentPhase = DayPhase.FREETIME;
                break;
            case FREETIME:
                currentPhase = DayPhase.SCHOOL_STRUCTUREDPLAY;
                break;
            case SCHOOL_STRUCTUREDPLAY:
                currentPhase = DayPhase.TIDYUP;
                break;
            case TIDYUP:
                currentPhase = DayPhase.BATHS_BRUSHES;
                break;
            case BATHS_BRUSHES:
                currentPhase = DayPhase.DORMS;
                break;
            case DORMS:
                currentPhase = DayPhase.MORNING;
                break;
            default:
                currentPhase = DayPhase.MORNING;
                break;
        }
    }
    public enum DayPhase {
        MORNING, BREAFAST, LUNCH, DINNER, GREEN, BLUE, RED, FREETIME, SCHOOL_STRUCTUREDPLAY, TIDYUP,BATHS_BRUSHES,DORMS
    }
    private static DayPhase currentPhase;
    
    public static DayPhase getCurrentPhase() {
        return currentPhase;
    }
    public void smallAction(){
        // This is a small action
    }
    public void mediumAction(){
        // This is a medium action
    }
    public void longAction(int time){
        // This is a long action
    }
    public void triggerEvent(){
        // This is a trigger event
    }
    public void changePhase(DayPhase phase){
       currentPhase = phase;
    }

}
