package MainPackage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;

public class PaperBuilder {

    private static Paper tmp;
    private static File file;
    final static String PAPERS_DIRECTORY = "src/Papers/";
    private static String newFileName;

    public static void main(String[] args) {

        //testPdfBox();
        // RenameFile();
    }

    public static void testPdfBox() {
        file = new File(PAPERS_DIRECTORY + "Advanced_Lectures_on_Machine_Learning_ML_Summer_Sc.pdf");
        System.out.println(file.exists());
        PDDocument doc = null;

        try {
            doc = PDDocument.load(file);
        } catch (IOException ex) {
            Logger.getLogger(PaperBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }

        PDDocumentInformation info = doc.getDocumentInformation();
        System.out.println("Page Count=" + doc.getNumberOfPages());
        tmp.setTitle(info.getTitle());
        tmp.setAuthor(info.getAuthor());
        tmp.setCategory(info.getSubject());
        tmp.setKeywords(info.getKeywords());
        System.out.println("Creator=" + info.getCreator());
        System.out.println("Producer=" + info.getProducer());
        System.out.println("Creation Date=" + info.getCreationDate());
        tmp.setDate(info.getModificationDate().getCalendarType());
        System.out.println("Trapped=" + info.getTrapped());
        System.out.println(info.getMetadataKeys().toString());
    }

    public static Paper getTmp() {
        return tmp;
    }

    public void setTmp(Paper tmp) {
        this.tmp = tmp;
    }

    public static File getFile() {
        return file;
    }

    public static void setFile(File file) {
        PaperBuilder.file = new File(file.getAbsolutePath());
    }

    public static String getNewFileName() {
        return newFileName;
    }

    public static void setNewFileName(String newFileName) {
        PaperBuilder.newFileName = newFileName;
    }

    public static Paper buildPaper(File exportedFille) {
        tmp = null;
        System.out.println("MainPackage.PaperBuilder.buildPaper(): file:" + file.exists());
        file = exportedFille;
        if (verifyFile(file)) {
            tmp = new Paper();
            RenameFile();
            saveFile();

            tmp.setTitle(newFileName.substring(0, newFileName.lastIndexOf(".")));
            tmp.setAbsolutePath(file.getAbsolutePath());
            return tmp;
        }
        return null;

    }

    private static boolean verifyFile(File file) {
        System.out.println("Verfying File : " + file.getAbsolutePath() + "  " + file.exists());

        String extension = "";

        try {
            if (file != null /*&& file.exists()*/) {
                String name = file.getName();
                extension = name.substring(name.lastIndexOf("."));
            }
        } catch (Exception e) {
            extension = "";
        }

        if (extension.equals(".pdf")) {
            return true;

        }
        return false;
    }

    public static void RenameFile() {

        PDDocument doc = null;

        try {
            doc = PDDocument.load(file);
        } catch (IOException ex) {
            Logger.getLogger(PaperBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }

        PDDocumentInformation info = doc.getDocumentInformation();
        System.out.println("Page Count=" + doc.getNumberOfPages());
        if (info.getTitle() != null) {
            tmp.setTitle(info.getTitle());
        }
        if (info.getAuthor() != null) {
            tmp.setAuthor(info.getAuthor());
        }
        if (info.getSubject() != null) {
            tmp.setCategory(info.getSubject());
        }
        if (info.getKeywords() != null) {
            tmp.setKeywords(info.getKeywords());
        }
        System.out.println("Creator=" + info.getCreator());
        System.out.println("Producer=" + info.getProducer());
        System.out.println("Creation Date=" + info.getCreationDate());
      
        
        System.out.println("Trapped=" + info.getTrapped());
        System.out.println(info.getMetadataKeys().toString());
    }

    public static void RenameFile(File file) {

        PDDocument doc = null;
        tmp = new Paper();
        if (verifyFile(file)) {
            try {
                doc = PDDocument.load(file);
             

            PDDocumentInformation info = doc.getDocumentInformation();
            System.out.println("Page Count=" + doc.getNumberOfPages());
            if (info.getTitle() != null) {
                tmp.setTitle(info.getTitle());
            }
            if (info.getAuthor() != null) {
                tmp.setAuthor(info.getAuthor());
            }
            if (info.getSubject() != null) {
                tmp.setCategory(info.getSubject());
            }
            if (info.getKeywords() != null) {
                tmp.setKeywords(info.getKeywords());
            }
            System.out.println("Creator=" + info.getCreator());
            System.out.println("Producer=" + info.getProducer());
            System.out.println("Creation Date=" + info.getCreationDate());
          if (info.getCreationDate()!= null) {
            if (info.getCreationDate().getCalendarType() != null) {
                
                 DateFormat dateFormat = new SimpleDateFormat("yyyy'/'MM'/'d");  
                String strDate = dateFormat.format(info.getCreationDate().getTime());  
                tmp.setDate(strDate);
                System.out.println("XXXXXXXXXXXXXXXXXXXXX" );
            }
        }   System.out.println("Key Words"+info.getKeywords());
            System.out.println("Trapped=" + info.getTrapped());
            System.out.println(info.getMetadataKeys().toString());
             doc.close();
        }
             
            
        catch (IOException ex) {
                Logger.getLogger(PaperBuilder.class.getName()).log(Level.SEVERE, null, ex);
            }
      

    }
    }

    public static void saveFile() {

        if (!newFileName.substring(newFileName.length() - 4).equalsIgnoreCase(".pdf")) {
            newFileName = newFileName.concat(".pdf");
        }
        Path path = Paths.get(PAPERS_DIRECTORY + newFileName);
        try {
            Files.copy(file.toPath(), path);
        } catch (IOException ex) {
            Logger.getLogger(PaperBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
