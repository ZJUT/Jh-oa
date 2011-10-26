package com.zjut.oa.mvc.domain.strengthen;

import java.sql.Timestamp;

import com.zjut.oa.mvc.domain.Ffile;
import com.zjut.oa.mvc.domain.User;

public class CommentTogether {

	private long id;
	private String content;
	private User user;
	private Ffile file;
	private Timestamp addtime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Ffile getFile() {
		return file;
	}

	public void setFile(Ffile file) {
		this.file = file;
	}

	public Timestamp getAddtime() {
		return addtime;
	}

	public void setAddtime(Timestamp addtime) {
		this.addtime = addtime;
	}

	@Override
	public String toString() {
		return "CommentTogether [id=" + id + ", content=" + content + ", user="
				+ user + ", file=" + file + ", addtime=" + addtime + "]";
	}

}
