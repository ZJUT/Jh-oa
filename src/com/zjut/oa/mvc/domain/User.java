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

	private static final Log log=LogFactory.getLog(User.class);
	
	private String uid;
	private String username;
	private String password;

	private int roleID;
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

	public int getRoleID() {
		return roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;
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
				+ password + ", roleID=" + roleID + ", addtime=" + addtime
				+ ", modifytime=" + modifytime + "]";
	}

	public boolean exist(String uid,String password){
		boolean flag=false;
		StringBuilder sql=new StringBuilder();
		sql.append("select * from ");
		sql.append(tableName());
		sql.append(" where uid=? and password=?");
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			ps = DBHelper.getConnection().prepareStatement(sql.toString());
			ps.setObject(1, uid);
			ps.setObject(2, password);
			rs=ps.executeQuery();
			if(rs.next()){
				setUsername(rs.getString("username"));
				setRoleID(rs.getInt("roleID"));
				setAddtime(rs.getTimestamp("addtime"));
				setModifytime(rs.getTimestamp("modifytime"));
				flag=true;
			}
		}catch(Exception e){
			log.error(e,e.getCause());
		}finally{
			DbUtils.closeQuietly(rs);
			DbUtils.closeQuietly(ps);
			sql=null;
		}
		return flag;
	}
//	public static void main(String args[]){
//		User user=new User();
//		user.setUid("200826490109");
//		user.setUsername("李斌斌");
//		user.setPassword("123456");
//		Timestamp timestamp=CalendarTool.now();
//		user.setAddtime(timestamp);
//		user.setModifytime(timestamp);
//		System.out.println("生成的ID:"+user.save());
//	}
}
