package com.zjut.oa.mvc.domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zjut.oa.db.DBHelper;
import com.zjut.oa.db.Model;

@SuppressWarnings("serial")
public class News extends Model {

	private static final Log log = LogFactory.getLog(News.class);

	private String title;
	private String content;
	private String stext;
	private int userID;
	private Timestamp addtime;
	private Timestamp modifytime;

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

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public Timestamp getAddtime() {
		return addtime;
	}

	public void setAddtime(Timestamp addtime) {
		this.addtime = addtime;
	}

	public Timestamp getModifytime() {
		return modifytime;
	}

	public void setModifytime(Timestamp modifytime) {
		this.modifytime = modifytime;
	}

	@Override
	public String toString() {
		return "News [title=" + title + ", content=" + content + ", stext="
				+ stext + ", userID=" + userID + ", addtime=" + addtime
				+ ", modifytime=" + modifytime + "]";
	}
}
