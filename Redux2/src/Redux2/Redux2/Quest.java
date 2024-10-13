package Redux2;
public class Quest {

    private String name;
    public void setName(String name) {
        this.name = name;
    }
    private String description;
    public void setDescription(String description) {
        this.description = description;
    }
    private boolean completed;
    
    public Quest(String name, String description, boolean completed){
        this.name = name;
        this.description = description;
        this.completed = false;
    }
    
    public String getName(){
        return name;

    }
    public String getDescription(){
        return description;
    }
    public boolean isCompleted(){
        return completed;
    }
    public void setCompleted(boolean completed){
        this.completed = completed;
    }
    public void complete(){
        this.completed = true;
    }
    public void incomplete(){
        this.completed = false;
    }
    public void display(){
        System.out.println("Quest: " + name);
        System.out.println("Description: " + description);
        System.out.println("Completed: " + completed);
    }
}