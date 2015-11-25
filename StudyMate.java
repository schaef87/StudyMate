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
		System.out.println("1. Take a Test\n2. Make/Edit a Test");
		Scanner kybd=new Scanner(System.in);
		int menuSelection=kybd.nextInt();
		kybd.nextLine();
		PrintStream out;
		String name,sub,chapter,over;
		File folder;
		File pa=new File("Quizzes");
		switch(menuSelection){
		case 1:
			if(pa.exists()){
				File[] list=pa.listFiles();
				if(list.length!=0){
					for(int i=0;i<list.length;i++)
						System.out.println(i+". "+list[i].getName());
					System.out.println("\nWhich one?");
					int namenum=kybd.nextInt();
					while(namenum<0||namenum>=list.length){
						System.out.println("Which one? Choose 0-"+(list.length-1));
						namenum=kybd.nextInt();
					}
					name=list[namenum].getName();
					list=new File(pa+"\\"+name).listFiles();
					if(list.length!=0){
						for(int i=0;i<list.length;i++)
							System.out.println(i+". "+list[i].getName());
						System.out.println("\nWhich one?");
						int subnum=kybd.nextInt();
						while(subnum<0||subnum>=list.length){
							System.out.println("Which one? Choose 0-"+(list.length-1));
							subnum=kybd.nextInt();
						}
						sub=list[subnum].getName();
						list=new File(pa+"\\"+name+"\\"+sub).listFiles();
						if(list.length!=0){
							for(int i=0;i<list.length;i++)
								System.out.println(i+". "+list[i].getName());
							System.out.println("\nWhat chapter?");
							int chapnum=kybd.nextInt();
							while(chapnum<0||chapnum>=list.length){
								System.out.println("Which one? Choose 0-"+(list.length-1));
								chapnum=kybd.nextInt();
							}
							takeTest(list[chapnum].getAbsoluteFile());
						}else{
							System.out.println("no chapters yet");
						}
					}else{
						System.out.println("no categories yet");
					}
				}else{
					System.out.println("no topics yet");
				}
			}else{
				System.out.println("no tests yet");
			}
			break;
		case 2:
			listAll(pa,0);
			do{
				System.out.println("What is the name?");
				name=kybd.nextLine();
				System.out.println("What is the subcategory?");
				sub=kybd.nextLine();
				folder=new File("Quizzes\\"+name+"\\"+sub);
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
				out.println(fake.size()+" "+ques);
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
	public static void listAll(File tree, int space){
		if(tree.exists()){
			File[] names=tree.listFiles();
			if(names.length!=0)
				for(File j:names){
					for(int i=0;i<space;i++)
						System.out.print("\t");
					System.out.println(j.getName());
					if(j.isDirectory()){
						listAll(j,space+1);
					}
				}
			else
				System.out.println("no tests yet");
		}else{
			System.out.println("no tests yet");
		}
	}
	public static void takeTest(File test) throws IOException{
		ArrayList<String> questions=new ArrayList<String>();
		Map<String, String> correct=new HashMap<String, String>();
		Map<String, ArrayList<String>> fakes=new HashMap<String, ArrayList<String>>();
		Scanner in=new Scanner(test);
		do{
			int numfans=in.nextInt();
			String ques=in.nextLine().substring(1);
			questions.add(ques);
			correct.put(ques, in.nextLine());
			ArrayList<String> f=new ArrayList<String>();
			for(int i=0;i<numfans;i++)
				f.add(in.nextLine());
			fakes.put(ques, f);
		}while(in.hasNextInt());
		
//		for(String i:questions){
//			
//		}

	}
}
