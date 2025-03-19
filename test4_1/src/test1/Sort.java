package test1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sort implements Calculation {
	List <String> li = new ArrayList<>();
	
	Sort(List li){
		this.li = li;
	}
	
	@Override
	public void sort(boolean isAsend) {
		// TODO Auto-generated method stub
		if (isAsend == true) {
			Collections.sort(li);
		}else {
			li.sort(Collections.reverseOrder());
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
