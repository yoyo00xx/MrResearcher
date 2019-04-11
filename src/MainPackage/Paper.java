package MainPackage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Paper implements Serializable {

    private String title;
    private ArrayList<Note> notes = new ArrayList<>();
    private double rating;
    private String author;
    private String date;
    private HashSet<String> keywords = new HashSet<>();

    public Paper() {
    }

    public Paper(String title, double rating, String author, String date) {
        this.title = title;
        this.rating = rating;
        this.author = author;
        this.date = date;
        addKeywords(title);
        addKeywords(author);

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
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public HashSet getKeywords() {
        return keywords;
    }

    public void removeKeywords(String words) {
        Scanner sc = new Scanner(words);
        while (sc.hasNext()) {
            keywords.remove(sc.next());
        }
    }

    public void addKeywords(String words) {
        Scanner sc = new Scanner(words);
        while (sc.hasNext()) {
            keywords.add(sc.next());
        }
    }

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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        if (notes == null) {
            notes = new ArrayList<>();
        }
        return " " + author + " no of notes = " + notes.size();

    }

}
