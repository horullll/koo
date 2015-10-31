import java.util.Scanner;
import java.io.*;


public class StudentInfoSys {
    
    public static void main(String args[]) throws IOException {
        
        int input;
        Scanner sc = new Scanner(System.in);
        
        
        do {
            
            System.out.println("학생 정보 시스템입니다");
            System.out.println("번호를 입력해주세요");
            System.out.println("--------------");
            System.out.println("1. Add");
            System.out.println("2. Delete");
            System.out.println("3. update");
            System.out.println("4. View");
            System.out.println("5. exit");
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
    
    public void goSystem(int input) throws IOException {
        
        switch (input) {
                
            case 1: {
                StudentAdd sa = new StudentAdd();
                sa.addStudent();
                break;
            }
            case 2: {
                StudentDelete sd = new StudentDelete();
                Scanner s = new Scanner(System.in);
                System.out.println("삭제할 학생의 학번입력 : ");
                int id = s.nextInt();
                sd.delStudent(id);
                break;
            }
            case 3: {
                StudentUpdate su = new StudentUpdate();
                su.update();
                break;
            }
            case 4: {
                StudentView sv = new StudentView();
                sv.ViewStudent();
                break;
            }
            default: {
                break;
                
            }
                
        }
    }
    
    
    
    public class StudentAdd {
        
        
        
        public void addStudent() throws IOException{
            
            Scanner scan = new Scanner(System.in);
            String stu=null;
            String stuID=null;
            String stuName=null;
            String stuDept=null;
            String stuPN=null;
            System.out.println("ID: ");
            stuID = scan.nextLine();
            System.out.println("Name: ");
            stuName = scan.nextLine();
            System.out.println("Department: ");
            stuDept = scan.nextLine();
            System.out.println("Phone Number: ");
            stuPN = scan.nextLine();
            stu = stuID + "\t" + stuName + "\t" + stuDept + "\t" + stuPN;
            
            int lastLine =0;
            
            FileWriter f_writer = new FileWriter("studentinfomation.txt",true);
            BufferedWriter b_writer = new BufferedWriter(f_writer,1024);
            b_writer.write(stu);
            b_writer.write("\n");
            b_writer.close();
            f_writer.close();
            
            
            System.out.println("학생정보가 파일에 저장되었습니다.");
            
            System.out.println("파일 읽기 결과");
            
            FileReader f_reader2 = new FileReader("studentinfomation.txt");
            BufferedReader b_reader2 = new BufferedReader(f_reader2,1024);
            System.out.println("ID"+'\t'+"이름"+'\t'+"학과"+'\t'+"phoneNumber");
            while(true){
                stu= b_reader2.readLine();
                if(stu==null)
                    break;
                System.out.println(String.valueOf(stu));
                
            }
            
            
        }
        
    }
    
    class StudentDelete {
        
        public void delStudent(int id){
            
            
            try {
                
                FileReader fr = new FileReader("studentinfomation.txt");
                String temp = "";
                
                BufferedReader br = new BufferedReader(fr);
                
                String line;
                int linenumber;
                Position pos = new Position();
                linenumber = pos.position(id);
                
                for(int i=1; i<linenumber; i++) {
                    line = br.readLine();
                    temp += (line + "\r\n");
                    
                }
                
                String delData = br.readLine();
                
                while((line = br.readLine()) != null) {
                    temp += (line + "\r\n");
                }
                
                FileWriter fw = new FileWriter("studentinfomation.txt");
                fw.write(temp);
                
                fw.close();
                br.close();
                
            } catch (Exception e) {
                
                e.printStackTrace();
                
            }
        }
        
    }
    
    public class StudentUpdate {
        
        
        public int s_pos;
        
        public void update() throws IOException {
            
            
            Scanner sc = new Scanner(System.in);
            Position posi = new Position();
            
            System.out.println("수정할 학생의 학번을 입력해주세요");
            int id = sc.nextInt();
            
            s_pos = posi.position(id);
            
            
            long pos_before;
            RandomAccessFile ra;
            
            try {
                
                ra = new RandomAccessFile("studentinfomation.txt","rw");
                String temp=null;
                int i;
                for (i = 1; i < s_pos; i++) {
                    ra.readLine(); // 읽으며 이동
                }
                pos_before = ra.getFilePointer();
                
                String phone=null;
                String line=null;
                
                line = ra.readLine();
                int len = line.length();
                phone = line.split("\t")[3];
                
                System.out.println("현재번호는 :" + phone);
                System.out.println("정말 번호를 바꾸시겠습니까?(Y/N)");
                String ans = sc.next();
                if (ans.equals("y")) {
                    System.out.println("번호를 입력하세요");
                    phone = sc.next();
                    ra.seek(pos_before+len-11);
                    ra.writeBytes(phone);
                    
                    
                }
                
                
                ra.close();
                
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            FileReader f_reader2 = new FileReader("studentinfomation.txt");
            BufferedReader b_reader2 = new BufferedReader(f_reader2,1024);
            String stu;
            System.out.println("ID"+'\t'+"이름"+'\t'+"학과"+'\t'+"phoneNumber");
            while(true){
                
                stu= b_reader2.readLine();
                if(stu==null)
                    break;
                System.out.println(String.valueOf(stu));
                
            }
            
            
        }
        
    }
    
    public class StudentView {
        
        
        Scanner scan = new Scanner(System.in);
        
        
        public void ViewStudent(){
            boolean find = false;
            System.out.println("ID를 입력하세요!");
            int ID = scan.nextInt();
            Position posi = new Position();
            int loc = posi.position(ID);
            
            String stu = null;
            
            FileReader f_reader = null;
            try {
                f_reader= new FileReader("studentinfomation.txt");
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            BufferedReader b_reader = new BufferedReader(f_reader,1024);
            int i=1;
            
            while(true){
                if(loc == 0)
                    break;
                try {
                    stu = b_reader.readLine();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
                if(i == loc){
                    find = true;
                    System.out.println(String.valueOf(stu));
                    break;
                }
                i++;
            }
            if(!find)System.out.println("그런 ID의 학생은 존재하지 않습니다.");
            
            
        }
    }
    
    
    
    class Position{
        
        public int position(int id){
            
            int linenumber=0;
            
            try {	
                
                String s;
                
                FileReader fr = new FileReader("studentinfomation.txt"); //학생정보파일 경로 추가해줘 
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
}



