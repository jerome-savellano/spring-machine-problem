package com.qbryx.util;

public class Validator {

	public static boolean invalidUpcFormat(String string){
		
		if(string.matches("[0-9]+")) return false;
		
		return true;
	}
}
