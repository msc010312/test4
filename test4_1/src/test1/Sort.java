package test1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sort implements Calculation {// 단어 목록을 정렬하는 클래스 구현

	List <String> li = new ArrayList<>();
	
	Sort(List li){
		this.li = li;
	}
	
	@Override
	public void sort(boolean isAsend) {
		// TODO Auto-generated method stub
		if (isAsend == true) {
			Collections.sort(li);	// 오름차순 정렬
		}else {
			li.sort(Collections.reverseOrder());//내림차순 정렬
		}
		
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
