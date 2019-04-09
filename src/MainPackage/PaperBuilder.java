import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;


public class PaperBuilder {
	private Paper tmp;
	private static  File file;
	final static String PAPERS_DIRECTORY="src/Papers/";
        private static String newFileName = "gogogaga2";
	
	public static void main(String[] args) {
		
         file = new File(PAPERS_DIRECTORY+"gogo.pdf");
         saveFile();
    
            
    }
	
	

	public Paper buildPaper(File file) {
		return tmp;
	}

	private static boolean verifyFile(File file) {
		System.out.println("Verfying File : "+file.getAbsolutePath()+"  "+file.exists());

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
        
        System.out.println("Extension is : "+extension);
        if(extension.equals(".pdf")) {
        	return true;
		
        }
		return false;
	}

	private void RenameFile() {

	}

	private static void saveFile() {
            
            
                try {
            FileInputStream fs = new FileInputStream(file.getAbsolutePath());
           
            FileOutputStream os = new FileOutputStream(PAPERS_DIRECTORY+newFileName+".pdf");
            int b;
            while ((b = fs.read()) != -1) {
            os.write(b);
            }
            os.close();
            fs.close();
        } catch (Exception E) {
            E.printStackTrace();
        }
                
	}

}
