package test1;

import java.util.ArrayList;
import java.util.List;

public class Add implements Calculation{	//추가 기능 담당 클래스 구현

	List<String> wordList;	//메인 클래스에서 전달받은 단어목록을 저장할 리스트
	Add(List<String> wordList){		
		this.wordList = wordList;	//입력받은값을 저장하도록 함.
	}
	
	@Override
	public void sort(boolean isAsend) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add(String word) {
		wordList.add(word);
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
	

}
