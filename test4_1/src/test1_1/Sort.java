package test1_1;

import java.util.Collections;
import java.util.List;

public class Sort implements Calculation {
	private List<String> wordList;

	public Sort(List<String> wordList) {
		this.wordList = wordList; // 공통 리스트를 사용하도록 변경
	}
    @Override
    public void sort(boolean isAscend) {
        if (isAscend) {
        	Collections.sort(wordList);
        } else {
            wordList.sort(Collections.reverseOrder());
        }
        System.out.println("정렬 완료!");
    }
	@Override
	public void add(String word) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

}
