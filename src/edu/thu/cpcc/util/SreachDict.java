package edu.thu.cpcc.util;

import java.util.ArrayList;

public class SreachDict {
	private String key;

	public String SearchDict(String target, ArrayList<String[]> dict){
		for(int i = 0; i < dict.size(); i++){
			String value = dict.get(i)[0];			
			if(value.equals(target))
				setKey(dict.get(i)[1]);
		}
		return key;		
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
