package Redux2;

public class Clock {

    private int time;

    public Clock(Game game) {
        this.time = (5 * 60); // Starting at 5:00 AM
    }

    public int moveTime(int minutes) {
        time = (time + minutes) % 1440; // Time goes to 0 after 23:59
        return time;
    }

    public int getTime() {
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
            return "Morning";
        } else if (hour >= 12 && hour < 17) {
            return "Afternoon";
        } else if (hour >= 17 && hour < 20) {
            return "Evening";
        } else {
            return "Night";
        }
    }

    public void setTime(int time) {
        this.time = time;
    }
}
