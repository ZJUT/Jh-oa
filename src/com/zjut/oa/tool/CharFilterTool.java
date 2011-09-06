package com.zjut.oa.tool;

public class CharFilterTool {
	private static char[] backChars = new char[] { '\\', '/', '(', ')', '\'', '\"' };
	
	public static boolean hasBackRegex(String str){
		if(str==null || str.equals(""))
			return false;
		boolean back=false;
		int len=str.length();
		char[] chars=str.toCharArray();
		for(int i=0;i<len;i++){
			char c=chars[i];
			if(inBack(chars[i])){
				back=true;
				break;
			}
		}
		return back;
	}
	
	public static boolean inBack(char c){
		boolean match=false;
		for(char cc : backChars){
			if(c==cc){
				match=true;
				break;
			}
		}
		return match;
	}
	
	public static String getBackRegexChars(){
		StringBuilder s=new StringBuilder();
		int len=backChars.length;
		for(int i=0;i<len;i++){
			if(i!=len-1)
				s.append(backChars[i]+" , ");
			else
				s.append(backChars[i]);
		}
		return s.toString();
	}
}
