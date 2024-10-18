package Redux2;

public class FetchQuest extends Quest {

    public FetchQuest(String name, String description, int difficulty) {
        super(name, description, difficulty);
    }

    public void fetch() {
        System.out.println("Fetching quest");
    }
    
}
