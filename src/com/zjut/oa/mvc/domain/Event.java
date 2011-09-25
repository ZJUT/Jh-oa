package com.zjut.oa.mvc.domain;

import java.sql.Timestamp;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zjut.oa.db.Model;

public class Event extends Model {

	private static final Log log=LogFactory.getLog(Event.class);
	
	private String title;
	private String content;
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

	public Timestamp getModifytime() {
		return modifytime;
	}

	public void setModifytime(Timestamp modifytime) {
		this.modifytime = modifytime;
	}

	@Override
	public String toString() {
		return "Event [title=" + title + ", content=" + content
				+ ", modifytime=" + modifytime + "]";
	}

}
