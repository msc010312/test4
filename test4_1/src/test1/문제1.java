package test1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 문제1 {
	
	private static Scanner sc = new Scanner(System.in);
	
	// String단어를 저장할 리스트 , 공통으로 사용
	private static List<String> wordList = new ArrayList<String>();

	// 인터페이스인 Calculation 을 구현할 객체 생성 공통리스트인 wordList로 전달.
	static Calculation add = new Add(wordList);		//단어 추가기능
	static Calculation sort = new Sort(wordList);	//단어 정렬 기능
	static Calculation show = new Show(wordList);	//단어 출력 기능
	public static void main(String[] args) {
		int n = 0;	//입력받을 정수변수.
		

		while (true) {	//while문을 사용하여 무한 반복할 수 있도록 함.
			System.out.println("----------------M E N U----------------");
			System.out.println("1 추가");	//단어추가 안내
			System.out.println("2 정렬");	//단어정렬 안내
			System.out.println("3 확인");	//저장된 단어 확인 안내
			System.out.println("4 종료");	//프로그램 종료 안내
			System.out.println("----------------M E N U----------------");
			System.out.print("번호 : ");
			n = sc.nextInt();	//1,2,3,4중 입력 받은 값에따라 기능실행할 수 있도록 case용.
			switch (n) {
			case 1:	//추가 기능
				System.out.print("단어 입력 : ");
				String word = sc.next();
				add.add(word);
				break;
			case 2:	//정렬 기능

				System.out.print("오름차순 여부(1:오름차순,0:내림차순) : ");
				int no = sc.nextInt();
				if(no==1) sort.sort(true); else sort.sort(false);				
				break;
			case 3:	//확인 기능

				show.show();
				break;
			case 4:	//종료
				System.out.print("종료합니다");
				System.exit(-1);
				break;
			default:
				System.out.println("다시입력 하세요");
			}
		}

	}

}
