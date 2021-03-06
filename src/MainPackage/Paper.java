package MainPackage;

import java.io.Serializable;
import java.util.ArrayList;

public class Paper implements Serializable {

    private String title;
    private ArrayList<Note> notes = new ArrayList<>();
    private String rating;
    private String author;
    private String entryType;
    private String category;
    private String keywords;
    private Bibtex bibtex;
    private ArrayList<Paper> references = new ArrayList<>();

    public ArrayList<Paper> getReferences() {
        return references;
    }

    public Bibtex getBibtex() {
        return bibtex;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getCategory() {
        return category;
    }

    public void modifyNote(int index, String title, String category, String content, String keywords) {
        Note tmp = notes.get(index);
        tmp.setCategory(category);
        tmp.setContent(content);
        tmp.setTitle(title);
    }

    public void setCategory(String category) {
        this.category = category;
    }
    private String year;
    private String dateAdded;
    private String absolutePath;

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }
//    private HashSet<String> keywords = new HashSet<>();

    public Paper() {
    }

    public Paper(String title, String rating, String author, String dateAdded) {
        this.title = title;
        this.rating = rating;
        this.author = author;
        this.dateAdded = dateAdded;
//        addKeywords(title);
//        addKeywords(author);

    }

    public static void main(String[] args) {
        //  System.out.println("Fuck off");

    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return dateAdded;
    }

    public void setDate(String date) {
        this.dateAdded = date;
    }

//    public HashSet getKeywords() {
//        return keywords;
//    }
//
//    public void removeKeywords(String words) {
//        Scanner sc = new Scanner(words);
//        while (sc.hasNext()) {
//            keywords.remove(sc.next());
//        }
//    }
//
//    public void addKeywords(String words) {
//        Scanner sc = new Scanner(words);
//        while (sc.hasNext()) {
//            keywords.add(sc.next());
//        }
//    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<Note> notes) {
        this.notes = notes;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String[] getTableArray() {
        String[] paperArray = new String[8];
        paperArray[0] = "";
        paperArray[1] = entryType;
        paperArray[2] = author;
        paperArray[3] = title;
        paperArray[4] = rating;
        paperArray[5] = category;
        paperArray[6] = year;
        paperArray[7] = dateAdded;
        return paperArray;
    }

    @Override
    public String toString() {
        if (notes == null) {
            notes = new ArrayList<>();
        }
        return " " + author + " no of notes = " + notes.size();

    }

}
