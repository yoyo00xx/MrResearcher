package MainPackage;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PapersManager implements Serializable {

    private static ArrayList<Paper> papers = new ArrayList<>();

    public static void main(String[] args) {

//       PapersManager p = new PapersManager();
//        p.load();
//        Paper paper = new Paper();
//        paper.setAuthor("Saud Only");
//        paper.getNotes().add(new Note());
//        paper.getNotes().add(new Note());
//        p.papers.add(paper);
//        p.save();
    }

    public static Note searchNotes(String keyWords) {
        return null;
    }

    public static Paper searchPaper(String keyWords) {
        return null;
    }

    public static void giveRating(Paper paper, double rate) {
        paper.setRating(rate);
    }

    public static void addPaper(Paper paper) {
        papers.add(paper);
    }

    public static void addNote(Paper paper, Note note) {
        paper.getNotes().add(note);
    }

    public static void editNote(Paper paper, Note note) {

    }

    public static void deleteNote(Paper paper, Note note) {

    }

    public static void deletePaper(Paper paper) {
        papers.remove(paper);
    }

    public static ArrayList getPapers() {
        return papers;
    }

    public static void save() {
        //   "src/Papers/"
        FileOutputStream fout = null;
        try {
            fout = new FileOutputStream("src/Papers/database.db");
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(papers);
            System.out.println("save was successful");
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

    }

    @SuppressWarnings("unchecked")
    public static void load() {
        ObjectInputStream ois = null;
        try {
            RandomAccessFile raf = new RandomAccessFile("src/Papers/database.db", "r");
            FileInputStream fos = new FileInputStream(raf.getFD());
            ois = new ObjectInputStream(fos);

            papers.addAll(((ArrayList<Paper>) ois.readObject()));
            System.out.println(papers.toString());
            System.out.println("load was successful");
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
