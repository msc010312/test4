package test1;

import java.util.ArrayList;
import java.util.List;

public class Add implements Calculation{
	List<String> wordList;
	Add(List<String> wordList){
		this.wordList = wordList;
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
