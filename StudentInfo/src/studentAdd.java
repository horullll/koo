package studentInfor;
import java.util.Scanner;
import java.io.*;


public class studentAdd {
	
	
	
	
	
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
