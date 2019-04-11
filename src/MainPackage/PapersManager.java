package MainPackage;

import java.io.*;
import java.util.*;

public class PapersManager implements Serializable {

    private ArrayList<Paper> papers = new ArrayList<>();

    public static void main(String[] args) {

        PapersManager p = new PapersManager();
        p.load();
        Paper paper = new Paper();
        paper.setAuthor("hahahahahaha");

        p.papers.add(paper);
        p.save();
    }

    public Note searchNotes(String keyWords) {
        return null;
    }

    public Paper searchPaper(String keyWords) {
        return null;
    }

    public void giveRating(double rate) {

    }

    public void addPaper(Paper paper) {

    }

    public void addNote(Note note) {

    }

    public void editNote(Note note) {

    }

    public void deleteNote(Note note) {

    }

    public void deletePaper(Paper paper) {

    }

    public void save() {
        //   "src/Papers/"
        try (FileOutputStream fout = new FileOutputStream("src/Papers/database.db");) {
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(this);
        } catch (Exception e) {
            System.out.println("Couldn't save the class");
            e.printStackTrace();
        }
    }

    public void load() {
        try {
            FileInputStream streamIn = new FileInputStream("src/Papers/database.db");
            ObjectInputStream objectinputstream = new ObjectInputStream(streamIn);
            PapersManager tmp = (PapersManager) objectinputstream.readObject();
            papers.addAll(tmp.papers);
            System.out.println(papers.toString());
        } catch (Exception e) {
            System.out.println("Couldn't load the class");

            // e.printStackTrace();
        }
    }
}
