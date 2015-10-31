import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.io.FileWriter;

public class StudentInfo {

	public static void main(String args[]) throws IOException {

		int input;
		Scanner sc = new Scanner(System.in);


		do {

			System.out.println("ÇÐ»ýÁ¤º¸½Ã½ºÅÛÀÔ´Ï´Ù");
			System.out.println("¹øÈ£¸¦ ÀÔ·ÂÇØ ÁÖ¼¼¿ä");
			System.out.println("--------------");
			System.out.println("1. Add");
			System.out.println("2. Update");
			System.out.println("3. Delete");
			System.out.println("4. View");
			System.out.println("5. Á¾·á");
			System.out.println("--------------");

			input = sc.nextInt();

			if (input != 5) {

				WhatIsInput ip = new WhatIsInput();
				ip.goSystem(input);
			}

		} while (input != 5);

	}


public class StudentAdd {
    
    
    
    
    
    public void addStudent() throws IOException{
        
        Scanner scan = new Scanner(System.in);
        String stu;
        System.out.println("학생 정보 입력");
        stu = scan.nextLine();
        int lastLine =0;
        
        FileWriter f_writer = new FileWriter("test.txt",true);
        BufferedWriter b_writer = new BufferedWriter(f_writer,1024);
        b_writer.write(stu);
        b_writer.write("\n");
        b_writer.close();
        f_writer.close();
        
        
        System.out.println("학생정보가 파일에 저장되었습니다.");
        
        System.out.println("파일 읽기 결과");
        
        FileReader f_reader2 = new FileReader("test.txt");
        BufferedReader b_reader2 = new BufferedReader(f_reader2,1024);
        for(int j =0 ; j < 10 ; j++){
            stu= b_reader2.readLine();
            System.out.println(String.valueOf(stu));
            
        }
        
        
    }
    
}

public class StudentInfo {
    
    public static void main(String args[]) throws IOException {
        
        int input;
        Scanner sc = new Scanner(System.in);
        
        
        do {
            
            System.out.println("«–ª˝¡§∫∏Ω√Ω∫≈€¿‘¥œ¥Ÿ");
            System.out.println("π¯»£∏¶ ¿‘∑¬«ÿ ¡÷ººø‰");
            System.out.println("--------------");
            System.out.println("1. Add");
            System.out.println("2. Update");
            System.out.println("3. Delete");
            System.out.println("4. View");
            System.out.println("5. ¡æ∑·");
            System.out.println("--------------");
            
            input = sc.nextInt();
            
            if (input != 5) {
                
                WhatIsInput ip = new WhatIsInput();
                ip.goSystem(input);
            }
            
        } while (input != 5);
        
    }
    
}

class WhatIsInput {
    
    public void goSystem(int input) {
        
        switch (input) {
                
            case 1: {
                StudentAdd sa = new StudentAdd();
                sa.addStudent();
                break;
            }
            case 2: {
                StudentDelete sd = new StudentDelete();
			break;

		}
		case 3: {
			StudentUpdate su = new StudentUpdate();
			su.update();
			break;
		}
		case 4: {
			StudentView sv = new StudentView();
			break;
		}
		default: {
			break;

		}

		}

	}
}

class Position{

	public int position(int id){

		int linenumber=0;

		try {	

			String s;

			FileReader fr = new FileReader("filepath"); //학생정보파일 경로 추가해줘 
			BufferedReader br = new BufferedReader(fr);
			LineNumberReader line = new LineNumberReader(br);


			while ((s = line.readLine()) != null) {

				String search_id = s.split("\t")[0];
				if(search_id.equals(Integer.toString(id))) {

					if(s.indexOf(Integer.toString(id)) != -1) 
						linenumber = line.getLineNumber();	
				
				}
			}
		
		} catch (IOException e) {

			System.out.println(e.getMessage());
		
		}

		return linenumber;
	}
}




