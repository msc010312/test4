package test1_1;

import java.util.ArrayList;
import java.util.List;

public class Add implements Calculation{
	private List<String> wordList;

	public Add(List<String> wordList) {
		this.wordList = wordList; // 공통 리스트를 사용하도록 변경
	}
    @Override
    public void add(String word) {
        wordList.add(word);
        System.out.println("추가 완료!");
    }
	@Override
	public void sort(boolean isAsend) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}


}
