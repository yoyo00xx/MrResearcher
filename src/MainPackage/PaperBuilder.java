package MainPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PaperBuilder {
	private static Paper tmp;
	private static  File file;
	final static String PAPERS_DIRECTORY="src/Papers/";
        private static String newFileName;
	
	public static void main(String[] args) {
		
         file = new File(PAPERS_DIRECTORY+"gogo.pdf");
           
    
            
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
           tmp  = null;
            System.out.println("MainPackage.PaperBuilder.buildPaper(): file:"+file.exists());
           file =exportedFille;
            if(verifyFile(file)){
            RenameFile();
            saveFile();
            }
            // TODO  COMPLETE GENERATION OF PAPER OBJECT
		return tmp;
	}

    private static boolean verifyFile(File file) {
        System.out.println("Verfying File : " + file.getAbsolutePath() + "  " + file.exists());

        String extension = "";

        try {
            if (file != null /*&& file.exists()*/) {
                String name = file.getName();
                extension = name.substring(name.lastIndexOf("."));
                System.out.println("HERE");
            }
        } catch (Exception e) {
            extension = "";
        }

        System.out.println("Extension is : " + extension);
        if (extension.equals(".pdf")) {
            return true;

        }
        return false;
    }

	private static void RenameFile() {
                    
	}

	public static void saveFile() {
            
            if(newFileName.lastIndexOf(".") ==-1){
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
                    Path path = Paths.get(PAPERS_DIRECTORY+newFileName);
            try {
                Files.copy(file.toPath(), path);
            } catch (IOException ex) {
                Logger.getLogger(PaperBuilder.class.getName()).log(Level.SEVERE, null, ex);
            }
                
	}

    }

}
