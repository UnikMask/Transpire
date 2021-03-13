package Transpire;

import java.util.*;

public class Helper {
	
	public String printList(List<Object> elements){
		StringBuilder sb = new StringBuilder();
		for(Object obj: elements){
			sb.append(obj.toString());
			sb.append(", ");
		}
		return sb.toString();
	}
}
