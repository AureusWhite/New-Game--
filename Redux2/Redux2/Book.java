package Redux2;


public class Book extends Item {
    private final String author;
    private final String title;
    private final String genre;
    private final int pages;
    private final String illistrator;
    private final String ageGroup;

    public Book(String author, String title, String genre, int pages, String illistrator, String ageGroup) {
        super("Book", "A book", "Reading", true);
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.pages = pages;
        this.illistrator = illistrator;
        this.ageGroup = ageGroup;
    }
    
    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getPages() {
        return pages;
    }

    public String getIllistrator() {
        return illistrator;
    }

    public String getAgeGroup() {
        return ageGroup;
    }
    public String Read() {
        String text = GameHandler.readFile(this.getName());
        return text;
    }
    public void Write(String text) {
        GameHandler.writeFile(this.getName(), text);
    }
    
}
