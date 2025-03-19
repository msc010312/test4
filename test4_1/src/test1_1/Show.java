package test1_1;

import java.util.ArrayList;
import java.util.List;

public class Show implements Calculation {
	private List<String> wordList;

	public Show(List<String> wordList) {
		this.wordList = wordList; // 공통 리스트를 사용하도록 변경
	}
    @Override
    public void show() {
        System.out.println("단어 목록:");
        for (String word : wordList) {
            System.out.println(word);
        }
    }
	@Override
	public void sort(boolean isAsend) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void add(String word) {
		// TODO Auto-generated method stub
		
	}

}
