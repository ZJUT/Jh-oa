package com.zjut.oa.tool;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONObject;

public class UploadTool {
	
	private static final Log log=LogFactory.getLog(UploadTool.class);
	
	public static final String SAVE_DIR_NAME="attached";
	public static final String[] ALLOW_FILE_SUFFIX = new String[]{"gif", "jpg", "jpeg", "png", "bmp"};
	public static final long ALLOW_MAX_FILE_SIZE = 1000000; //1M

	public static final String FILE_SAVE_DIR_NAME="file";
	public static final String[] FILE_ALLOW_FILE_SUFFIX = new String[]{
											"gif", "jpg", "jpeg", "png", "bmp",
											"doc","ppt","xls",
											"pdf","chm",
											"7z","zip","rar"};
	public static final long FILE_ALLOW_MAX_FILE_SIZE = 10000000; //10M
	
	public static final String PRODUCT_SAVE_DIR_NAME="product";
	public static final String[] PRODUCT_ALLOW_FILE_SUFFIX = new String[]{
											"gif", "jpg", "jpeg", "png", "bmp",
											};
	public static final long PRODUCT_ALLOW_MAX_FILE_SIZE = 10000000; //10M
	
	@SuppressWarnings("unchecked")
	public static String getErrorJson(String message){
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", message);
		log.info("Error Json: "+obj.toJSONString());
		return obj.toJSONString();
	}
	
}