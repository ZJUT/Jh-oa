package com.zjut.oa.tool;

import org.json.simple.JSONObject;

public class UploadTool {
	
	
	public static final String SAVE_DIR_NAME="attached";
	public static final String[] ALLOW_FILE_SUFFIX = new String[]{"gif", "jpg", "jpeg", "png", "bmp"};
	public static final long ALLOW_MAX_FILE_SIZE = 1000000;
	
	
	@SuppressWarnings("unchecked")
	public static String getErrorJson(String message){
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", message);
		return obj.toJSONString();
	}
	
}