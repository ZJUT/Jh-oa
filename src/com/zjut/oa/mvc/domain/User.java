package com.zjut.oa.mvc.domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zjut.oa.db.DBHelper;
import com.zjut.oa.db.Model;

@SuppressWarnings("serial")
public class User extends Model {

	private static final Log log = LogFactory.getLog(User.class);

	private String uid;
	private String username;
	private String password;

	private String email;
	private String cornet;
	private String telephone;
	private int academyID; // 学院ID
	private String major; // 专业
	private String location; // 校区
	private String dormitory; // 宿舍

	private int islock; // 状态

	private Timestamp addtime;
	private Timestamp modifytime;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 
	 * 
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 
	 * 
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 
	 * 
	 * @return the cornet
	 */
	public String getCornet() {
		return cornet;
	}

	/**
	 * 
	 * 
	 * @param cornet
	 *            the cornet to set
	 */
	public void setCornet(String cornet) {
		this.cornet = cornet;
	}

	/**
	 * 
	 * 
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * 
	 * 
	 * @param telephone
	 *            the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * 
	 * 
	 * @return the academyID
	 */
	public int getAcademyID() {
		return academyID;
	}

	/**
	 * 
	 * 
	 * @param academyID
	 *            the academyID to set
	 */
	public void setAcademyID(int academyID) {
		this.academyID = academyID;
	}

	/**
	 * 
	 * 
	 * @return the major
	 */
	public String getMajor() {
		return major;
	}

	/**
	 * 
	 * 
	 * @param major
	 *            the major to set
	 */
	public void setMajor(String major) {
		this.major = major;
	}

	/**
	 * 
	 * 
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * 
	 * 
	 * @param location
	 *            the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * 
	 * 
	 * @return the dormitory
	 */
	public String getDormitory() {
		return dormitory;
	}

	/**
	 * 
	 * 
	 * @param dormitory
	 *            the dormitory to set
	 */
	public void setDormitory(String dormitory) {
		this.dormitory = dormitory;
	}

	/**
	 * 
	 * 
	 * @return the islock
	 */
	public int getIslock() {
		return islock;
	}

	/**
	 * 
	 * 
	 * @param islock
	 *            the islock to set
	 */
	public void setIslock(int islock) {
		this.islock = islock;
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
		return "User [uid=" + uid + ", username=" + username + ", password="
				+ password + ", addtime=" + addtime + ", modifytime="
				+ modifytime + "]";
	}

	public boolean exist(String uid, String password) {
		boolean flag = false;
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ");
		sql.append(tableName());
		sql.append(" where uid=? and password=?");
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = DBHelper.getConnection().prepareStatement(sql.toString());
			ps.setObject(1, uid);
			ps.setObject(2, password);
			log.debug("User:exist, sql: " + sql.toString() + ", Values[" + uid
					+ "," + password + "]");
			rs = ps.executeQuery();
			if (rs.next()) {
				setId(rs.getInt("id"));
				setUsername(rs.getString("username"));
				setAddtime(rs.getTimestamp("addtime"));
				setModifytime(rs.getTimestamp("modifytime"));
				flag = true;
			}
		} catch (Exception e) {
			log.error(e, e.getCause());
		} finally {
			DbUtils.closeQuietly(rs);
			DbUtils.closeQuietly(ps);
			sql = null;
		}
		return flag;
	}
}
