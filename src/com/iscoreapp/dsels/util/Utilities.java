package com.iscoreapp.dsels.util;

import java.util.List;

public class Utilities {
	
	public static String[] listAsStringArray(List<?> list) {
		if (list == null) {
			return null;
		}
		if (list.isEmpty()) {
			return new String[0];
		}
		String[] array = new String[list.size()];
		for (int i=0; i<list.size(); i++) {
			array[i] = list.get(i).toString();
		}
		return array;
	}

}
