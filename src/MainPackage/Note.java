package MainPackage;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Scanner;

public class Note implements Serializable {

    private String title;
    private String category;
    private String content;
    private String keywords ;

    public Note() {
    }

    public Note(String title, String category, String content) {
        this.title = title;
        this.category = category;
        this.content = content;
    }
       public Note(String title, String category, String content, String keywords) {
        this.title = title;
        this.category = category;
        this.content = content;
        this.keywords = keywords;
    }

    public String getKeywords() {
        return keywords;
    }
    
        public String[] getTableArray() {
        String[] paperArray = new String[5];
        paperArray[0] = "";
        paperArray[1] = title;
        paperArray[2] = category;
        paperArray[3] = content;
        paperArray[4] = keywords;
        
        return paperArray;
    } 

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
