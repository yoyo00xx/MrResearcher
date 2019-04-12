package MainPackage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

        file = new File(PAPERS_DIRECTORY + "p.pdf");
        System.out.println(file.exists());
        PDDocument doc = null;
        try {
            doc = PDDocument.load(file);
        } catch (IOException ex) {
            Logger.getLogger(PaperBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }

        PDDocumentInformation info = doc.getDocumentInformation();
        System.out.println("Page Count=" + doc.getNumberOfPages());
        System.out.println("Title=" + info.getTitle());
        System.out.println("Author=" + info.getAuthor());
        System.out.println("Subject=" + info.getSubject());
        System.out.println("Keywords=" + info.getKeywords());
        System.out.println("Creator=" + info.getCreator());
        System.out.println("Producer=" + info.getProducer());
        System.out.println("Creation Date=" + info.getCreationDate());
        System.out.println("Modification Date=" + info.getModificationDate());
        System.out.println("Trapped=" + info.getTrapped());
        System.out.println(info.getMetadataKeys().toString());

    }

    public Paper getTmp() {
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
            RenameFile();
            saveFile();
        }
        tmp = new Paper();
        tmp.setTitle(newFileName.substring(0, newFileName.lastIndexOf(".")));
        tmp.setAbsolutePath(file.getAbsolutePath());
        return tmp;
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

    private static void RenameFile() {

    }

    public static void saveFile() {

        if (!newFileName.substring(newFileName.length() - 4).equalsIgnoreCase(".pdf")) {
            newFileName = newFileName.concat(".pdf");
        }

//                try {
//            FileInputStream fs = new FileInputStream(file.getAbsolutePath());
//
//            FileOutputStream os = new FileOutputStream(PAPERS_DIRECTORY+newFileName);
//            int b;
//            while ((b = fs.read()) != -1) {
//            os.write(b);
//            }
//            os.close();
//            fs.close();
//        } catch (Exception E) {
//            E.printStackTrace();
//        }
        Path path = Paths.get(PAPERS_DIRECTORY + newFileName);
        try {
            Files.copy(file.toPath(), path);
        } catch (IOException ex) {
            Logger.getLogger(PaperBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
