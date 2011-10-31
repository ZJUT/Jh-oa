package com.zjut.oa.mvc.domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zjut.oa.db.DBHelper;
import com.zjut.oa.db.Model;
import com.zjut.oa.mvc.domain.strengthen.UserTogether;

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

	private String bbs;

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

	public String getBbs() {
		return bbs;
	}

	public void setBbs(String bbs) {
		this.bbs = bbs;
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
				setBbs(rs.getString("bbs"));
				
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

	public List<UserTogether> exportUserListBy(int academyID,
			int departmentID, String location, int islock) {
		// 组合条件
		StringBuilder condition = new StringBuilder();
		if (academyID != -1) {
			condition.append(" and u.academyID =" + academyID);
		}
		if (departmentID != -1) {
			condition.append(" and u.departmentID =" + departmentID);
		}
		if (!StringUtils.equals(location, "-1")) {
			condition.append(" and u.location ='" + location + "'");
		}
		if (islock != -1) {
			condition.append(" and u.islock =" + islock);
		}

		List<UserTogether> utList = new ArrayList<UserTogether>();

		Academy academy = new Academy();
		Department department = new Department();

		StringBuilder sql = new StringBuilder();

		sql.append("select ");

		sql.append(" u.id, ");

		sql.append(" u.uid, ");
		sql.append(" u.username, ");
		sql.append(" d.departmentname, ");
		sql.append(" u.email, ");
		sql.append(" u.cornet, ");
		sql.append(" u.telephone, ");
		sql.append(" u.location, ");
		sql.append(" a.academyname, ");
		sql.append(" u.major, ");
		sql.append(" u.dormitory, ");
		sql.append(" u.islock, ");
		sql.append(" u.academyID, ");
		sql.append(" u.departmentID, ");
		sql.append(" u.addtime, ");
		sql.append(" u.modifytime, ");
		sql.append(" u.bbs ");
		
		sql.append(" from ");
		sql.append(tableName() + " as u, ");
		sql.append(academy.tableName() + " as a, ");
		sql.append(department.tableName() + " as d ");
		sql.append(" where ");
		sql.append(" u.academyID=a.id ");
		sql.append(" and u.departmentID = d.id  ");

		sql.append(condition.toString());

		sql.append(" order by u.addtime asc");
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = DBHelper.getConnection().prepareStatement(sql.toString());
			log.debug("User:getExportUserListBy, sql: " + sql.toString()
					+ ", Values[]");
			rs = ps.executeQuery();

			while (rs.next()) {

				User u = new User();
				u.setId(rs.getLong(1));
				u.setUid(rs.getString(2));
				u.setUsername(rs.getString(3));
				u.setEmail(rs.getString(5));
				u.setCornet(rs.getString(6));
				u.setTelephone(rs.getString(7));
				u.setLocation(rs.getString(8));
				u.setMajor(rs.getString(10));
				u.setDormitory(rs.getString(11));
				u.setIslock(rs.getInt(12));
				u.setAddtime(rs.getTimestamp(15));
				u.setModifytime(rs.getTimestamp(16));
				u.setBbs(rs.getString(17));
				
				Academy a = new Academy();
				a.setId(rs.getLong(13));
				a.setAcademyname(rs.getString(9));

				Department d = new Department();
				d.setId(rs.getLong(14));
				d.setDepartmentname(rs.getString(4));

				UserTogether ut = new UserTogether();
				ut.setId(rs.getLong(1));
				ut.setAcademy(a);
				ut.setDepartment(d);
				ut.setUser(u);

				utList.add(ut);
			}
		} catch (Exception e) {
			log.error(e, e.getCause());
		} finally {
			DbUtils.closeQuietly(rs);
			DbUtils.closeQuietly(ps);
			sql = null;
		}
		return utList;
	}
}
