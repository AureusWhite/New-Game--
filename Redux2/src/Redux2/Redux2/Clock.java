package Redux2;

public class Clock {

    private int time;

    public Clock() {
        this.time = (5 * 60); // Starting at 5:00 AM
    }

    public int moveTime(int minutes) {
        time = (time + minutes) % 1440; // Time goes to 0 after 23:59
        int currentHour = getCurrentHour();
        GameHandler.getGui().display("Current Time: " + time + " minutes, Current Hour: " + currentHour, "Black");
        switch (currentHour) {
            case 0 -> FatherTime.setPhase(FatherTime.DayPhase.DORMS);
            case 6 -> FatherTime.setPhase(FatherTime.DayPhase.MORNING);
            case 7, 8 -> {
                FatherTime.setPhase(FatherTime.DayPhase.BREAKFAST);
                if (currentHour == 7) {
                    GameHandler.getGui().display("Triggering breakfast event", "Black");
                    FatherTime.triggerEvent();
                }
            }
            case 9, 10 -> {
                FatherTime.setPhase(FatherTime.DayPhase.SCHOOL_STRUCTUREDPLAY);
                if (currentHour == 9) {
                    GameHandler.getGui().display("Triggering school event", "Black");
                    FatherTime.triggerEvent();
                }
            }
            case 11 -> {
                FatherTime.setPhase(FatherTime.DayPhase.LUNCH);
                GameHandler.getGui().display("Triggering lunch event", "Black");
                FatherTime.triggerEvent();
            }
            case 12 -> {
                FatherTime.setPhase(FatherTime.DayPhase.SCHOOL_STRUCTUREDPLAY);
                GameHandler.getGui().display("Triggering school event", "Black");
                FatherTime.triggerEvent();
            }
            case 15 -> FatherTime.setPhase(FatherTime.DayPhase.FREETIME);
            case 18 -> {
                FatherTime.setPhase(FatherTime.DayPhase.DINNER);
                GameHandler.getGui().display("Triggering dinner event", "Black");
                FatherTime.triggerEvent();
            }
            case 20 -> {
                FatherTime.setPhase(FatherTime.DayPhase.TIDYUP);
                GameHandler.getGui().display("Triggering tidy up event", "Black");
                FatherTime.triggerEvent();
            }
            case 21 -> {
                FatherTime.setPhase(FatherTime.DayPhase.BATHS_BRUSHES);
                GameHandler.getGui().display("Triggering baths and brushes event", "Black");
                FatherTime.triggerEvent();
            }
            case 22 -> {
                FatherTime.setPhase(FatherTime.DayPhase.DORMS);
                GameHandler.getGui().display("Triggering bed time event", "Black");
                FatherTime.triggerEvent();
            }
        }
        return time;
    }
    public String getFormattedTime() {
        int hours = (time / 60) % 12;
        if (hours == 0) {
            hours = 12;
        }
        int minutes = time % 60;
        String period = time < 720 ? "AM" : "PM";
        return String.format("%d:%02d %s", hours, minutes, period); // displays the time in 12-hour format and adds the name for the period of the day.
    }

    public int getCurrentHour() {
        return time / 60;
    }

    public String formatByHourMinute(int minutes) {
        int hours = minutes / 60;
        int remainingMinutes = minutes % 60;
        StringBuilder formattedTime = new StringBuilder();
        if (hours > 0) {
            formattedTime.append(hours).append(" hour");
            if (hours > 1) {
                formattedTime.append("s");
            }
            if (remainingMinutes > 0) {
                formattedTime.append(" ");
            }
        }
        if (remainingMinutes > 0) {
            formattedTime.append(remainingMinutes).append(" minute");
            if (remainingMinutes > 1) {
                formattedTime.append("s");
            }
        }
        return formattedTime.toString();
    }

    public String getTimeOfDay() {
        int hour = getCurrentHour();
        if (hour >= 5 && hour < 12) {
            return "Morning" + " - " + FatherTime.getFormmatedCurrentPhase();
        } else if (hour >= 12 && hour < 17) {
            return "Afternoon" + " - " + FatherTime.getFormmatedCurrentPhase();
        } else if (hour >= 17 && hour < 20) {
            return "Evening" + " - " + FatherTime.getFormmatedCurrentPhase();
        } else {
            return "Night" + " - " + FatherTime.getFormmatedCurrentPhase();
        }
    }

    public void setTime(int time) {
        this.time = time;
    }
}
