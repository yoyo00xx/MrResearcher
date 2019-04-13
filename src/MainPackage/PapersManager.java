package MainPackage;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PapersManager implements Serializable {

    private static ArrayList<Paper> papers = new ArrayList<>();

    public static void main(String[] args) {

        PapersManager p = new PapersManager();
        p.load();
        Paper paper = new Paper();
        paper.setAuthor("Saud Only");
        paper.getNotes().add(new Note());
        paper.getNotes().add(new Note());
        p.papers.add(paper);
        p.save();
    }

    public Note searchNotes(String keyWords) {
        return null;
    }

    public Paper searchPaper(String keyWords) {
        return null;
    }

    public void giveRating(Paper paper, String rate) {
        paper.setRating(rate);
    }

    public void addPaper(Paper paper) {
        papers.add(paper);
    }

    public void addNote(Paper paper, Note note) {
        paper.getNotes().add(note);
    }

    public void editNote(Paper paper, Note note) {

    }

    public void deleteNote(Paper paper, Note note) {

    }

    public void deletePaper(Paper paper) {
        papers.remove(paper);
    }

    public static ArrayList<Paper> getPapers() {
        return papers;
    }

    public static void save() {
        //   "src/Papers/"
        FileOutputStream fout = null;
        try {
            fout = new FileOutputStream("src/Papers/database.db");
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(papers);
        } catch (Exception e) {
            System.out.println("Couldn't save the papers");
            e.printStackTrace();
        } finally {
            if (fout != null) {
                try {
                    fout.close();
                } catch (IOException ex) {
                    Logger.getLogger(PapersManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        System.out.println("Saved Sucessfuly");
    }

    @SuppressWarnings("unchecked")
    public static void load() {
        ObjectInputStream ois = null;
        try {
            RandomAccessFile raf = new RandomAccessFile("src/Papers/database.db", "r");
            FileInputStream fos = new FileInputStream(raf.getFD());
            ois = new ObjectInputStream(fos);
            papers = new ArrayList<>();
            papers.addAll(((ArrayList<Paper>) ois.readObject()));
            System.out.println(papers.toString());
        } catch (IOException ex) {
            //Logger.getLogger(PapersManager.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("There was no file to load.");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PapersManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException ex) {
                    Logger.getLogger(PapersManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
