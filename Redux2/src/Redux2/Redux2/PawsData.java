package Redux2;
import java.io.Serializable;
import java.util.ArrayList;

public class PawsData implements Serializable {
    private static final long serialVersionUID = 1L;
    private final ArrayList<Paw> playerPaws;
    private final ArrayList<Paw> gamePaws;

    public PawsData(ArrayList<Paw> playerPaws, ArrayList<Paw> gamePaws) {
        this.playerPaws = playerPaws;
        this.gamePaws = gamePaws;
    }

    public ArrayList<Paw> getPlayerPaws() {
        return playerPaws;
    }

    public ArrayList<Paw> getGamePaws() {
        return gamePaws;
    }
}