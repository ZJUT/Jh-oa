package com.zjut.oa.mvc.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zjut.oa.mvc.core.ActionAdapter;
import com.zjut.oa.mvc.core.annotation.None;
import com.zjut.oa.mvc.core.annotation.Result;

public class GlobalAction extends ActionAdapter {

	@Result("/WEB-INF/pages/anonymous/index.jsp")
	public String index(HttpServletRequest req, HttpServletResponse resp){
		
		return INPUT;
	}
	
	@Result("/WEB-INF/pages/freeze/index.jsp")
	public String manager(HttpServletRequest req, HttpServletResponse resp){
		
		return INPUT;
	}
	
	@None
	public String uploadImg(HttpServletRequest req, HttpServletResponse resp){
		try {
			PrintWriter out=resp.getWriter();
			StringBuilder output_json=new StringBuilder();
			output_json.append("{");
			output_json.append("\'error\':");
			output_json.append("0");
			output_json.append(",");
			output_json.append("\'url\':");
			output_json.append("\'zhanwei\'");
			output_json.append("}");
			
			out.print(output_json.toString());
			out.flush();
			out.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			log.error("上传图像出错:"+e,e.getCause());
		}
		
		return NONE;
	}
}
