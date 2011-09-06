package com.zjut.oa.tool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PatternTool {

	private static final Log log=LogFactory.getLog(PatternTool.class);
	
	public static boolean match(String pattern, String value){
		boolean isMatched=false;
		Pattern p=Pattern.compile(pattern);
		Matcher matcher=p.matcher(value);
		isMatched=matcher.matches();
		log.debug("Value : "+value+", Pattern : "+pattern +", isMatched : "+isMatched);
		return isMatched;
	}
	
	public static void main(String[] args) {
		String [] values=new String[]{"org.qingtian","1org.qingtian.1","org.qingtian.1","org.qingtian..1","org.qingtian.autodata","org.qingtian.*"};
		String pattern="^org.qingtian.[a-z0-9]*";
		for(String value :values){
			System.out.println(value+"匹配"+pattern+"＝"+PatternTool.match(pattern, value));
		}
	}

}
