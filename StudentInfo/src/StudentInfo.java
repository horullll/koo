import java.util.Scanner;
import java.io.*;


public class StudentInfoSys {
    
    public static String path = "" //DB 파일 경로를 입력해 주세요
    
    
    
    public static void main(String args[]) throws IOException {
    
    int input;
    Scanner scanner = new Scanner(System.in);
    
    
    do {
        
        System.out.println("학생 정보 시스템입니다. 번호를 입력하여 사용해주세요.");
        System.out.println("--------------");
        System.out.println("1. Add");
        System.out.println("2. Update");
        System.out.println("3. Delete");
        System.out.println("4. View");
        System.out.println("5. exit");
        System.out.println("--------------");
        System.out.print("번호입력하세요: ");
        input = scanner.nextInt();
        
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
                
            case 1:
                StudentAdd addObject = new StudentAdd();
                addObject.addStudent();
                break;
                
            case 2:
                StudentUpdate updateObject = new StudentUpdate();
                updateObject.update();
                break;
                
            case 3:
                StudentDelete deleteObject = new StudentDelete();
                Scanner s = new Scanner(System.in);
                System.out.println("삭제할 학생의 학번입력 : ");
                int studentID = s.nextInt();
                deleteObject.delStudent(studentID);
                break;
                
            case 4:
                StudentView viewObject = new StudentView();
                viewObject.ViewStudent();
                break;
                
            default:
                break;
                
        }
        
    }
}



class StudentAdd {
    
    String aStudentInformation=null;
    String studentID=null;
    String studentName=null;
    String studentDept=null;
    String studentPhoneNum=null;
    
    
    
    public void addStudent() throws IOException{
        
        Scanner scan = new Scanner(System.in);
        
        
        System.out.println("ID: ");
        studentID = scan.nextLine();
        System.out.println("Name: ");
        studentName = scan.nextLine();
        System.out.println("Department: ");
        studentDept = scan.nextLine();
        System.out.println("Phone Number: ");
        studentPhoneNum = scan.nextLine();
        aStudentInformation = studentID + "\t" + studentName + "\t" + studentDept + "\t" + studentPhoneNum;
        
        
        FileWriter file_writer = new FileWriter(path,true);
        BufferedWriter buffer_writer = new BufferedWriter(file_writer,1024);
        buffer_writer.write(aStudentInformation);
        buffer_writer.write("\n");
        
        buffer_writer.close();
        file_writer.close();
        
        
        System.out.println("학생정보가 파일에 저장되었습니다.");
        System.out.println("학생정보 추가결과");
        
        FileReader file_reader = new FileReader(path);
        BufferedReader buffer_reader = new BufferedReader(file_reader,1024);
        System.out.println("ID"+'\t'+"이름"+'\t'+"학과"+'\t'+"전화번호");
        while(true){
            aStudentInformation = buffer_reader.readLine();
            if(aStudentInformation==null)
                break;
            System.out.println(String.valueOf(aStudentInformation));
            
        }
        file_reader.close();
        buffer_reader.close();
        
    }
    
}

class StudentDelete {
    
    public void delStudent(int id){
        
        
        try {
            
            FileReader file_reader = new FileReader(path);
            BufferedReader buffer_reader = new BufferedReader(file_reader);
            
            String line;
            String temp = "";
            int linenumber;
            
            FindStudentLine pos = new FindStudentLine();
            linenumber = pos.findStudentLine(id);
            
            if(linenumber==0){
                System.out.println("그런 ID를 가진학생은 존재하지 않습니다.");
                return;
            }
            
            for(int i=1; i<linenumber; i++) {
                line = buffer_reader.readLine();
                temp += (line + "\r\n");
                
            }
            
            String delData = buffer_reader.readLine();
            
            while((line = buffer_reader.readLine()) != null) {
                temp += (line + "\r\n");
            }
            
            FileWriter file_writer = new FileWriter(path);
            file_writer.write(temp);
            
            file_writer.close();
            buffer_reader.close();
            
        } catch (Exception e) {
            
            e.printStackTrace();
            
        }
        
    }
    
}

class StudentUpdate {
    
    int s_pos;
    
    public void update() throws IOException {
        
        
        Scanner scan = new Scanner(System.in);
        FindStudentLine posi = new FindStudentLine();
        
        System.out.println("수정할 학생의 학번을 입력해주세요");
        
        int idOfUpdateStudent = scan.nextInt();
        s_pos = posi.findStudentLine( idOfUpdateStudent);
        
        
        long pos_before;
        int i;
        String phone=null;
        String line=null;
        RandomAccessFile ra;
        
        try {
            
            ra = new RandomAccessFile(path,"rw");
            for (i = 1; i < s_pos; i++) {
                ra.readLine(); // 읽으며 이동
            }
            pos_before = ra.getFilePointer();
            
            
            line = ra.readLine();
            int len = line.length();
            phone = line.split("\t")[3];
            
            System.out.println("현재번호는 :" + phone);
            System.out.println("정말 번호를 바꾸시겠습니까?(y/n)");
            String ans = scan.next();
            if (ans.equals("y")) {
                System.out.println("번호를 입력하세요('-',공백없이)");
                phone = scan.next();
                ra.seek(pos_before+len-11);
                ra.writeBytes(phone);
                
                
            }
            ra.close();
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        FileReader f_reader = new FileReader(path);
        BufferedReader b_reader = new BufferedReader(f_reader,1024);
        String stu;
        System.out.println("ID"+'\t'+"이름"+'\t'+"학과"+'\t'+"전화번호");
        while(true){
            
            stu= b_reader.readLine();
            if(stu==null)
                break;
            System.out.println(String.valueOf(stu));
            
        }
        f_reader.close();
        b_reader.close();
    }
    
}

public class StudentView {
    
    Scanner scanner = new Scanner(System.in);
    
    public void ViewStudent(){
        
        System.out.println("ID를 입력하세요!");
        
        int idOfViewStudent = scanner.nextInt();
        FindStudentLine findStudentLineObject= new FindStudentLine();
        int findedStudentLine = findStudentLineObject.findStudentLine(idOfViewStudent);
        int i=1;
        
        String findedStudentInformation = null;
        FileReader file_reader = null;
        
        if(findedStudentLine==0){
            System.out.println("그런 ID를 가진학생은 존재하지 않습니다.");
            return;
        }
        
        try {
            file_reader= new FileReader(path);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        BufferedReader buffer_reader = new BufferedReader(file_reader,1024);
        
        while(true){
            if(findedStudentLine == 0)
                break;
            try {
                findedStudentInformation= buffer_reader.readLine();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            if(i == findedStudentLine){
                System.out.println(String.valueOf(findedStudentInformation));
                break;
            }
            i++;
        }
        
    }
}



class FindStudentLine{
    
    public int findStudentLine(int id){
        
        int linenumber=0;
        
        try {
            
            String s;
            
            FileReader fr = new FileReader(path); //학생정보파일 경로 추가해줘
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