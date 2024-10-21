package Redux2;
import java.util.ArrayList;
import java.util.HashMap;

public class Buffs {

    private final String type;  // Buff type name like "Smarter", "Faster"
    private final String description;  // Buff description (optional)
    private final int duration;  // How long the buff lasts (in game minutes)
    private final boolean isBuff;  // True if buff, false if debuff

    // Store skill buffs in this map: e.g. Cognitive -> +2, Learning -> +1
    private HashMap<Skill, Integer> buffs = new HashMap<>();
    private static Buffs smarterBuff;
    private static Buffs fasterBuff;
    private static Buffs betterBuff;
    private final static ArrayList<Buffs> buffsList = new ArrayList<>();

    public Buffs(String type, HashMap<Skill, Integer> buffs, String description, int duration, boolean isBuff) {
        this.type = type;
        this.buffs = buffs;
        this.description = description;
        this.duration = duration;
        this.isBuff = isBuff;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public int getDuration() {
        return duration;
    }

    public boolean isBuff() {
        return isBuff;
    }

    public HashMap<Skill, Integer> getBuffs() {
        return buffs;
    }

    // Apply buff: increase the relevant skills in the Player
    public void applyBuff() {
        for (Skill skill : buffs.keySet()) {
            Player.addSkillLevel(skill, buffs.get(skill));  // Increase skill by the buff value
        }
    }

    // Remove buff: decrease the relevant skills in the Player
    public void removeBuff() {
        for (Skill skill : buffs.keySet()) {
            Player.addSkillLevel(skill, -buffs.get(skill));  // Revert skill by the buff value
        }
    }

    public static void createBuffs() {

// "Smarter" buff: +1 Learning, +2 Cognitive for 30 minutes
        HashMap<Skill, Integer> smarterBuffs = new HashMap<>();
        smarterBuffs.put(Skill.LEARNING, 1);
        smarterBuffs.put(Skill.COGNITIVE, 2);
        smarterBuff = new Buffs("Smarter", smarterBuffs, "Increases learning and cognitive abilities", 30, true);
        buffsList.add(smarterBuff);
        
// "Faster" buff: +1 Gross Motor, +2 Fine Motor for 20 minutes
        HashMap<Skill, Integer> fasterBuffs = new HashMap<>();
        fasterBuffs.put(Skill.GROSS_MOTOR, 1);
        fasterBuffs.put(Skill.FINE_MOTOR, 2);
         fasterBuff = new Buffs("Faster", fasterBuffs, "Increases motor skills", 20, true);
        buffsList.add(fasterBuff);

// "Better" buff: +1 Everything for 10 minutes
        HashMap<Skill, Integer> betterBuffs = new HashMap<>();
        betterBuffs.put(Skill.LEARNING, 1);
        betterBuffs.put(Skill.COGNITIVE, 1);
        betterBuffs.put(Skill.GROSS_MOTOR, 1);
        betterBuffs.put(Skill.FINE_MOTOR, 1);
        betterBuff = new Buffs("Better", betterBuffs, "Increases all skills slightly", 10, true);
        buffsList.add(betterBuff);

    }
    public static void applyRandomBuff() {
        // Apply a random buff to the player
        Buffs randomBuff = buffsList.get((int) (Math.random() * buffsList.size()));
        randomBuff.applyBuff();
    }

    public ArrayList<Buffs> getBuffsList() {
        return buffsList;
    }
}
