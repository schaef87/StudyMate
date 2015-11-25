/* StudyMate V1.0 */
/* By: John Vande Noord & Justin Schaefer */
/* Copyright 2015 */

import java.io.*;
import java.util.*;
public class StudyMate {
   public static void main(String[] args) throws IOException{
      System.out.println("Welcome to StudyMate!\n");
      System.out.println("Created by: John Vande Noord and Justin Schaefer(USNR)\n");
      System.out.println("Please make a selection:\n");
      System.out.println("1. Take a Test\n2. Make/Edit a Test");//no use at this time
      Scanner kybd=new Scanner(System.in);
      PrintStream out;
      String name,sub,chapter,over;
      File folder;
      do{
         System.out.println("What is the name?");
         name=kybd.nextLine();
         System.out.println("What is the subcategory?");
         sub=kybd.nextLine();
         folder=new File(name+"\\"+sub);
         if(!folder.mkdirs()){
            System.out.println("something went wrong");
            System.exit(0);
         }
         System.out.println("What chapter?");
         chapter=kybd.nextLine();
         over="yes";
         if((new File(folder, chapter+".txt").exists())){
            System.out.println("This chapter already exsists. Do you wish to overwrite?");
            over=kybd.nextLine();
         }
      }while(!over.equalsIgnoreCase("yes"));
      out=new PrintStream(new File(folder, chapter+".txt"));
      do{
         System.out.println("Please enter the question");
         String ques=kybd.nextLine();
         System.out.println("Please enter the correct answer");
         String answer=kybd.nextLine();
         ArrayList<String> fake=new ArrayList<String>();
         boolean con=true;
         do{
            System.out.println("Please enter an incorrect answer");
            String fkan=kybd.nextLine();
            fake.add(fkan);
            System.out.println("Another fake? (y/n)");
            if(kybd.nextLine().equalsIgnoreCase("n"))
               con=false;
         }while(con);
         out.println((fake.size()+1)+" "+ques);
         out.println(answer);
         for(String i:fake)
            out.println(i);
         out.println();
         System.out.println("Another question? (y/n)");
      }while(kybd.nextLine().equalsIgnoreCase("y"));
      kybd.close();out.close();
   }//END MAIN
}//END PROGRAM
