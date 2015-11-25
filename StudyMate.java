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
		int menuSelection = kybd.nextInt();
		PrintStream out;
		String name,sub,chapter,over;
		File folder;
		File pa=new File("Quizes");
		switch(menuSelection){
		case 1:
			if(pa.exists()){
				File[] list=pa.listFiles();
				if(list.length!=0){
					for(int i=0;i<list.length;i++)
						System.out.println(i+". "+list[i].getName());
					System.out.println("Which one?");
					int namenum=kybd.nextInt();
					name=list[namenum].getName();
					list=new File(pa+"\\"+name).listFiles();
					if(list.length!=0){
						for(int i=0;i<list.length;i++)
							System.out.println(i+". "+list[i].getName());
						System.out.println("Which one?");
						int subnum=kybd.nextInt();
						sub=list[subnum].getName();
						list=new File(pa+"\\"+name+"\\"+sub).listFiles();
						if(list.length!=0){
							for(int i=0;i<list.length;i++)
								System.out.println(i+". "+list[i].getName());
							System.out.println("What chapter?");
							int chapnum=kybd.nextInt();
							System.out.println(pa+"\\"+name+"\\"+sub+"\\"+list[chapnum]);
						}
					}
				}
			}else{
				System.out.println("no tests yet");
			}
			break;
		case 2:
			do{
				System.out.println("What is the name?");
				name=kybd.nextLine();
				System.out.println("What is the subcategory?");
				sub=kybd.nextLine();
				folder=new File("Quizes\\"+name+"\\"+sub);
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
			break;
		}
	}
}
