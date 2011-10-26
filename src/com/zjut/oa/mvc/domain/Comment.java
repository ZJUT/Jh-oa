package com.zjut.oa.mvc.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.zjut.oa.db.Model;
import com.zjut.oa.mvc.domain.strengthen.CommentTogether;

@SuppressWarnings("serial")
public class Comment extends Model {

	private String content;
	private int userID;
	private Timestamp addtime;
	private int fileID;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public int getFileID() {
		return fileID;
	}

	public void setFileID(int fileID) {
		this.fileID = fileID;
	}

	@Override
	public String toString() {
		return "Comment [content=" + content + ", userID=" + userID
				+ ", addtime=" + addtime + ", fileID=" + fileID + "]";
	}

}
