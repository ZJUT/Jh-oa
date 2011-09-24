package com.zjut.oa.mvc.domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zjut.oa.db.DBHelper;
import com.zjut.oa.db.Model;
import com.zjut.oa.mvc.domain.strengthen.KeTogether;
import com.zjut.oa.mvc.domain.strengthen.PermissionTogether;
import com.zjut.oa.mvc.domain.strengthen.RolePermissionTogether;

@SuppressWarnings("serial")
public class Ke extends Model {

	private static final Log log=LogFactory.getLog(Ke.class);
	
	private int userID;
	private String kevalue;
	
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getKevalue() {
		return kevalue;
	}
	public void setKevalue(String kevalue) {
		this.kevalue = kevalue;
	}
	@Override
	public String toString() {
		return "Ke [userID=" + userID + ", kevalue=" + kevalue + "]";
	}
	
	public List<KeTogether> getKeTogetherByUserID( String userID, String orderby) {
		List<KeTogether> ktList = new ArrayList<KeTogether>();

		User user=new User();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select ");
		
		sql.append(" k.id, ");
		sql.append(" k.userID, ");
		sql.append(" k.kevalue, ");

		sql.append(" from ");
		sql.append(tableName()+" as k, ");
		sql.append(user.tableName()+" as u ");
		sql.append(" where ");
		sql.append(" k.userID = u.id ");
		sql.append(" and k.userID = ?");
		if(orderby!=null)
			sql.append(orderby);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = DBHelper.getConnection().prepareStatement(sql.toString());
			ps.setObject(1, userID);
			log.debug("Ke:getKeTogetherByUserID, sql: "
					+ sql.toString() + ", Values["+userID+"]");
			rs = ps.executeQuery();

			while (rs.next()) {
				
				User u=new User();
				u=u.get(rs.getLong(2));
				
				Ke k=new Ke();
				k.setId(rs.getLong(1));
				k.setUserID(rs.getInt(2));
				k.setKevalue(rs.getString(3));
				
				KeTogether kt=new KeTogether();
				kt.setId(rs.getLong(1));
				kt.setUser(u);
				kt.setKe(k);
				
				ktList.add(kt);
				
			}
		} catch (Exception e) {
			log.error(e, e.getCause());
		} finally {
			DbUtils.closeQuietly(rs);
			DbUtils.closeQuietly(ps);
			sql = null;
		}
		return ktList;
	}
}
