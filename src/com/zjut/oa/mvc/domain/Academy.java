/**
 * 
 */
package com.zjut.oa.mvc.domain;

import com.zjut.oa.db.Model;

/**
 * 
 * 
 * @author qingtian
 * 
 *         2011-9-19 下午05:21:06
 */
public class Academy extends Model {

	private String academyname;

	public String getAcademyname() {
		return academyname;
	}

	public void setAcademyname(String academyname) {
		this.academyname = academyname;
	}

	@Override
	public String toString() {
		return "Academy [academyname=" + academyname + "]";
	}

}
