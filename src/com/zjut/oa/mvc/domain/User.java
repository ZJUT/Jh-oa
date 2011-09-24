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
	private int departmentID; // 部门ID

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCornet() {
		return cornet;
	}

	public void setCornet(String cornet) {
		this.cornet = cornet;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public int getAcademyID() {
		return academyID;
	}

	public void setAcademyID(int academyID) {
		this.academyID = academyID;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDormitory() {
		return dormitory;
	}

	public void setDormitory(String dormitory) {
		this.dormitory = dormitory;
	}

	public int getDepartmentID() {
		return departmentID;
	}

	public void setDepartmentID(int departmentID) {
		this.departmentID = departmentID;
	}

	public int getIslock() {
		return islock;
	}

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

	public boolean login(String uid, String password) {
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
				setUid(rs.getString("uid"));
				setUsername(rs.getString("username"));
				setEmail(rs.getString("email"));
				setCornet(rs.getString("cornet"));
				setTelephone(rs.getString("telephone"));
				setAcademyID(rs.getInt("academyID"));
				setLocation(rs.getString("location"));
				setDepartmentID(rs.getInt("departmentID"));
				setDormitory(rs.getString("dormitory"));
				setMajor(rs.getString("major"));
				setIslock(rs.getInt("islock"));
				
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
