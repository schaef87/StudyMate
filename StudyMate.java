/* StudyMate V1.0 */
/* By: John Vande Noord & Justin Schaefer */
/* Copyright 2015 */

import java.io.*;
import java.util.*;
public class StudyMate {
	public static void main(String[] args) throws IOException{
		boolean cont=false;
		PrintStream out;
		String name,sub,chapter,over;
		File folder;
		int menuSelection=0;
		File pa=new File("Quizzes");
		System.out.println("Welcome to StudyMate!\n");
		System.out.println("Created by: John Vande Noord and Justin Schaefer(USNR)\n");
		System.out.println("Please make a selection:\n");
		System.out.println("1. Take a Test\n2. Make/Edit a Test");
		Scanner kybd=new Scanner(System.in);
		do{cont=false;
		try{
			menuSelection=kybd.nextInt();
		}catch (InputMismatchException e){
			System.out.println("Please enter a number");
			kybd.nextLine();
			cont=true;
		}
		}while(cont);
		kybd.nextLine();
		switch(menuSelection){
		case 1:
			if(pa.exists()){
				File[] list=pa.listFiles();
				if(list.length!=0){
					System.out.println();
					for(int i=0;i<list.length;i++)
						System.out.println(i+". "+list[i].getName());
					System.out.println("\nWhich one?");
					do{cont=false;
					try{
						int namenum=kybd.nextInt();
						while(namenum<0||namenum>=list.length){
							System.out.println("Which one? Choose 0-"+(list.length-1));
							namenum=kybd.nextInt();
						}
						name=list[namenum].getName();
						list=new File(pa+"\\"+name).listFiles();
						if(list.length!=0){
							System.out.println();
							for(int i=0;i<list.length;i++)
								System.out.println(i+". "+list[i].getName());
							System.out.println("\nWhich one?");
							do{cont=false;
							try{
								int subnum=kybd.nextInt();
								while(subnum<0||subnum>=list.length){
									System.out.println("Which one? Choose 0-"+(list.length-1));
									subnum=kybd.nextInt();
								}
								sub=list[subnum].getName();
								list=new File(pa+"\\"+name+"\\"+sub).listFiles();
								if(list.length!=0){
									System.out.println();
									for(int i=0;i<list.length;i++)
										System.out.println(i+". "+list[i].getName());
									System.out.println("\nWhat chapter?");
									do{cont=false;
									try{
										int chapnum=kybd.nextInt();
										while(chapnum<0||chapnum>=list.length){
											System.out.println("Which one? Choose 0-"+(list.length-1));
											chapnum=kybd.nextInt();
										}
										takeTest(list[chapnum].getAbsoluteFile(),kybd);
									}
									catch(InputMismatchException e){
										System.out.println("Please enter a number");
										cont=true;
									}
									}while(cont);
								}else{
									System.out.println("no chapters yet");
								}
							}
							catch(InputMismatchException e){
								System.out.println("Please enter a number");
								kybd.nextLine();
								cont=true;
							}
							}while(cont);
						}else{
							System.out.println("no categories yet");
						}
					}
					catch(InputMismatchException e){
						System.out.println("Please enter a number");
						kybd.nextLine();
						cont=true;
					}
					}while(cont);
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
	public static void takeTest(File test, Scanner kybd) throws IOException{
		ArrayList<String> questions=new ArrayList<String>();
		Map<String, String> correct=new HashMap<String, String>();
		Map<String, ArrayList<String>> fakes=new HashMap<String, ArrayList<String>>();
		int numcor=0;
		boolean cont=false;
		Scanner in=new Scanner(test);
		while(in.hasNextInt()){
			int numfans=in.nextInt();
			String ques=in.nextLine().substring(1);
			questions.add(ques);
			correct.put(ques, in.nextLine());
			ArrayList<String> f=new ArrayList<String>();
			for(int i=0;i<numfans;i++)
				f.add(in.nextLine());
			fakes.put(ques, f);
		}
		Collections.shuffle(questions);
		if(!questions.isEmpty())
			for(String i:questions){
				System.out.println(i);
				String right=correct.get(i);
				ArrayList<String> poss=fakes.get(i);
				poss.add(right);
				Collections.shuffle(poss);
				for(int j=1;j<=poss.size();j++)
					System.out.println(j+". "+poss.get(j-1));
				System.out.println("\nPlease enter the number of the correct answer");
				do{cont=false;
				try{
					int ans=kybd.nextInt();
					if(poss.get(ans-1).equals(right)){
						System.out.println("Correct\n");
						numcor++;
						try {
						    Thread.sleep(1000);
						} catch(InterruptedException ex) {
						    Thread.currentThread().interrupt();
						}
					}else
						System.out.println("Incorrect\n");
				}	catch(InputMismatchException e){
					System.out.println("Please enter the NUMBER of the correct answer");
					kybd.nextLine();
					cont=true;
				}
				}while(cont);
			}
		else
			System.out.println("no questions");
		System.out.println("Your score is "+numcor*100.0/questions.size());
		in.close();
	}
}
