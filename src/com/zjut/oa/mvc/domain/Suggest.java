package com.zjut.oa.mvc.domain;

import java.sql.Timestamp;

import com.zjut.oa.db.Model;

public class Suggest extends Model {

	private String content;
	private Timestamp addtime;
	private String reply;
	private Timestamp replytime;
	private int userID;
	private int replyUserID;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getAddtime() {
		return addtime;
	}

	public void setAddtime(Timestamp addtime) {
		this.addtime = addtime;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public Timestamp getReplytime() {
		return replytime;
	}

	public void setReplytime(Timestamp replytime) {
		this.replytime = replytime;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getReplyUserID() {
		return replyUserID;
	}

	public void setReplyUserID(int replyUserID) {
		this.replyUserID = replyUserID;
	}

	@Override
	public String toString() {
		return "Suggest [content=" + content + ", addtime=" + addtime
				+ ", reply=" + reply + ", replytime=" + replytime + ", userID="
				+ userID + ", replyUserID=" + replyUserID + "]";
	}

}
