// StudyMate V1.0
// By: John VandeNoord & Justin Schaefer
// Copyright 2015

import java.util.*;

public class StudyMate {
   public static void main(String[] args){
   
   //SETUP
   Scanner reader = new Scanner(System.in);
   String menuSelection = "5";
   
   //CONTROL
   while(!menuSelection.equals("3")){
   //MENU
      System.out.println("Welcome to StudyMate!\n");
      System.out.println("Created by: John VandeNoord and Justin Schaefer(USNR)\n");
      System.out.println("Please make a selection:\n");
      System.out.println("1. Take a Test\n" + "2. Make/Edit a Test\n" + "3. Exit");
      
      //INPUT
     menuSelection = reader.next();
      
      //BODY
      switch(menuSelection){
         case "1":
         System.out.println("TEST: Case 1");
         break;
     
      }//End of Cases       
     }//End of WhileLoop
   } //End of Main
} //End