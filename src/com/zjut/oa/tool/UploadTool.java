package com.zjut.oa.tool;

import org.json.simple.JSONObject;

public class UploadTool {
	
	
	public static final String SAVE_DIR_NAME="attached";
	public static final String[] ALLOW_FILE_SUFFIX = new String[]{"gif", "jpg", "jpeg", "png", "bmp"};
	public static final long ALLOW_MAX_FILE_SIZE = 1000000; //1M

	public static final String FILE_SAVE_DIR_NAME="file";
	public static final String[] FILE_ALLOW_FILE_SUFFIX = new String[]{"gif", "jpg", "jpeg", "png", "bmp",
											"doc","ppt","xls"};
	public static final long FILE_ALLOW_MAX_FILE_SIZE = 10000000; //10M
	
	
	@SuppressWarnings("unchecked")
	public static String getErrorJson(String message){
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", message);
		return obj.toJSONString();
	}
	
}