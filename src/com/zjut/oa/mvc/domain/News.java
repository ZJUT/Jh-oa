package com.zjut.oa.mvc.domain;

import java.sql.Timestamp;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zjut.oa.db.Model;

@SuppressWarnings("serial")
public class News extends Model {
	
	private static final Log log=LogFactory.getLog(News.class);
	
	private String title;
	private String content;
	private String stext;
	private Timestamp addtime;
	private int userID;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getStext() {
		return stext;
	}
	public void setStext(String stext) {
		this.stext = stext;
	}
	public Timestamp getAddtime() {
		return addtime;
	}
	public void setAddtime(Timestamp addtime) {
		this.addtime = addtime;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	@Override
	public String toString() {
		return "News [title=" + title + ", content=" + content + ", stext="
				+ stext + ", addtime=" + addtime + ", userID=" + userID + "]";
	}
	
}
