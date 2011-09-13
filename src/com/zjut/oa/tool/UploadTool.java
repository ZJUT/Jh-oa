package com.zjut.oa.tool;

import org.json.simple.JSONObject;

public class UploadTool {
	
	@SuppressWarnings("unchecked")
	public static String getErrorJson(String message){
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", message);
		return obj.toJSONString();
	}
	
//	public static String upload(HttpServletRequest req){
//		return "";
//	}
}
