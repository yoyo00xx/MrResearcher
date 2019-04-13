/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackage;


import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author st201549390
 */
public class TestClass {
       public static void main(String[] args) 
    {
        
           try {
               String qTittle,qYear;
               Document doc = Jsoup.connect("https://liinwww.ira.uka.de/csbib/?query=Verified+AIG+Algorithms+in+ACL2&field=&year=&since=&before=&results=bibtex&maxnum=40&sort=score").get();
              
               String bibs = doc.body().text();
               System.out.println(bibs);
               
              
               String parsedBib = parsBibs(bibs);
               Scanner scan = new Scanner(parsedBib);
                System.out.println("Parsed Bib is :\n"+parsedBib);
                BebTexFields bibFields = new BebTexFields();
                setBibFields(bibFields,parsedBib);
              //  bibFields.setVisible(true);
                
          // scan.useDelimiter("\"|,");
           
//           while(scan.hasNext()){
//           String entryToDelmiter = scan.next();
//               System.out.println(entryToDelmiter+"\n");
//           }
           
           } catch (IOException ex) {
               Logger.getLogger(TestClass.class.getName()).log(Level.SEVERE, null, ex);
           }
    }
       public static  String parsBibs(String bibs){
       
       bibs.indexOf("is", bibs.indexOf("is") + 1);
      String parsedBib =  bibs.substring( bibs.indexOf("@", bibs.indexOf("@") + 1), bibs.indexOf("}", bibs.indexOf("}") + 1));
           System.out.println(parsedBib);
      
           return parsedBib;
       }
       
       public static void setBibFields(BebTexFields bibfields,String parsedString){
           
           Scanner scan = new Scanner(parsedString);
            scan.useDelimiter("\"|,");
           scan.next();
          
           int i=0;
           while(scan.hasNext()){
               String entryLine = scan.next();
               String entry = entryLine.substring(3, entryLine.indexOf("="));
               String entryValue = scan.next();
               scan.next();
               System.out.println(i+"- Entry:"+entry+" Entry Value: "+entryValue);
             //  String entryLine = scan.next();
             
              // String entry = getFirstWord(entryLine);
                 
               //System.out.println(i+"-"+entryLine);
               //String entryValue = scan.next();
             //  System.out.println("Entry: "+entry+" Value: "+entryValue);
               if(entry.equals("title")){
                   bibfields.getTvTittle().setText(entryValue);
               }
               if(entry.equals("author")){
                   bibfields.getTvAuthor().setText(entryValue);
               }
               if(entry.equals("journal")){
                    bibfields.getTvJournal().setText(entryValue);            
               }
               if(entry.equals("year")){
                    bibfields.getTvYear().setText(bibfields.getTvYear().getText()+" -->"+entryValue);
               }
//               if(entry.equals("month")){
//                    bibfields.getTv().setText(entryValue);
//               }
//               if(entry.equals("abstract")){
//                    bibfields.getTva().setText(entryValue);
//               }
               if(entry.equals("institution")){
                    bibfields.getTvInstit().setText(entryValue);
               }
               if(entry.equals("volume")){
                    bibfields.getTvVolume().setText(entryValue);
               }
               if(entry.equals("bibdate")){
                    bibfields.getTvBibDate().setText(entryValue);
               }
//               if(entry.equals("bibsource")){
//                    bibfields.getTvb().setText(entryValue);
//               }
               if(entry.equals("pages")){
                    bibfields.getTvPages().setText(entryValue);
               }
               if(entry.equals("number")){
                    bibfields.getTvNumber().setText(entryValue);
               }
               if(entry.equals("URL")){
                    bibfields.getTvURL().setText(entryValue);
               }
               
               bibfields.setVisible(true);
               
               
           i++;
           }
           
           
           
       
       
       }
         public static String getFirstWord(String text) {
    int index = text.indexOf(' ');
    if (index > -1) { // Check if there is more than one word.
      return text.substring(0, index); // Extract first word.
    } else {
      return text; // Text is the first word itself.
    }
  }
}
