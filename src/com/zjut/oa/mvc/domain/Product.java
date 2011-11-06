package com.zjut.oa.mvc.domain;

import java.sql.Timestamp;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zjut.oa.db.Model;

@SuppressWarnings("serial")
public class Product extends Model {
	private static final Log log = LogFactory.getLog(Product.class);

	private String logo;
	private String name;
	private String introduce;
	private String link;
	private Timestamp addtime;

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Timestamp getAddtime() {
		return addtime;
	}

	public void setAddtime(Timestamp addtime) {
		this.addtime = addtime;
	}

	@Override
	public String toString() {
		return super.getId() + "Product [logo=" + logo + ", name=" + name
				+ ", introduce=" + introduce + ", link=" + link + ", addtime="
				+ addtime + "]";
	}

}
