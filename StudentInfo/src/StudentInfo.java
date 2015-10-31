import java.io.IOException;
import java.util.Scanner;

public class StudentInfo {

	public static void main(String args[]) throws IOException {

		int input;
		Scanner sc = new Scanner(System.in);


		do {

			System.out.println("학생정보시스템입니다");
			System.out.println("번호를 입력해 주세요");
			System.out.println("--------------");
			System.out.println("1. Add");
			System.out.println("2. Update");
			System.out.println("3. Delete");
			System.out.println("4. View");
			System.out.println("5. 종료");
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
