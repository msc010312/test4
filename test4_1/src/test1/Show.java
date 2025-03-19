package test1;

import java.util.ArrayList;
import java.util.List;

public class Show implements Calculation {
	List<String> li = new ArrayList<>();
	Show(List li){
		this.li = li;
	}
	@Override
	public void sort(boolean isAsend) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add(String word) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		for (String item : li) {
			System.out.println(item);
		}
	}


}
