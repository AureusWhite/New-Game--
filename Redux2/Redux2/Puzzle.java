package Redux2;

public class Puzzle extends Item {

    private final int difficulty;
    private final String type;

    Puzzle(String name, String description, int difficulty, String type) {
        super(name, description, "Puzzle", true);
        this.difficulty = difficulty;
        this.type = type;

    }

    public int getDifficulty() {
        return difficulty;
    }

    public String getType() {
        return type;
    }

    public String solve() {
        int gross = Player.getSkillLevel(Skill.GROSS_MOTOR);
        int fine = Player.getSkillLevel(Skill.FINE_MOTOR);
        int learning = Player.getSkillLevel(Skill.LEARNING);
        int problemSolving = Player.getSkillLevel(Skill.PROBLEM_SOLVING);
        int cognitive = Player.getSkillLevel(Skill.COGNITIVE);
        boolean attention = Player.getProficiencies().contains(Proficiencies.PAYATTENTION);
    
        switch (this.getType()) {
            case "Jigsaw" -> {
                if (problemSolving >= this.getDifficulty()) {
                    GameHandler.getGui().display("Your sharp mind tackles each piece with precision, and the puzzle falls into place!", "Green");
                    return "Completed Jigsaw Puzzle";
                } else if (fine >= this.getDifficulty() && attention) {
                    GameHandler.getGui().display("Careful hands and focus guide each piece until, finally, the picture comes together.", "Green");
                    this.setName("Completed Jigsaw Puzzle");
                } else {
                    GameHandler.getGui().display("You fumble with the pieces, growing frustrated as the image never quite fits. Maybe next time...", "Red");
                    this.setName("Pile Of Pieces");
                }
                return "Jigsaw";
            }
            case "Crossword" -> {
                if (problemSolving >= this.getDifficulty()) {
                    GameHandler.getGui().display("Every clue clicks into place as you breeze through the crossword with ease!", "Green");
                    return "Completed Crossword";
                } else if (learning >= this.getDifficulty() && attention) {
                    GameHandler.getGui().display("With some study and focus, the words start making sense and the puzzle is solved.", "Green");
                    this.setName("Completed Crossword");
                } else {
                    GameHandler.getGui().display("You scratch your head as the answers elude you, leaving the puzzle half-filled and messy.", "Red");
                    this.setName("Scribbled on Crossword Puzzle");
                }
                return "Crossword";
            }
            case "Shapes" -> {
                if (problemSolving >= this.getDifficulty()) {
                    GameHandler.getGui().display("Your logical thinking guides you as every shape finds its place perfectly!", "Green");
                    return "Completed Shapes Puzzle";
                } else if (gross >= this.getDifficulty() && attention) {
                    GameHandler.getGui().display("With patience and a steady hand, you fit each shape in place, completing the puzzle.", "Green");
                    this.setName("Completed Shapes Puzzle");
                } else {
                    GameHandler.getGui().display("You force the pieces together, but they just won't fit. Maybe with better coordination next time...", "Red");
                    this.setName("Square Peg in Round Hole");
                }
                return "Shapes";
            }
            case "Colors" -> {
                if (problemSolving >= this.getDifficulty()) {
                    GameHandler.getGui().display("Your understanding of patterns and colors makes the puzzle a breeze!", "Green");
                    return "Completed Colors Puzzle";
                } else if (cognitive >= this.getDifficulty() && attention) {
                    GameHandler.getGui().display("Focusing on the task, you match the colors and complete the puzzle.", "Green");
                } else {
                    GameHandler.getGui().display("Despite your efforts, the colors just don't seem to align. A bit more practice and focus may help.", "Red");
                }
                return "Colors";
            }
        }
        return "Puzzle";
    }
    
}
