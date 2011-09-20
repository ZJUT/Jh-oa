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
import com.zjut.oa.mvc.domain.strengthen.PermissionTogether;
import com.zjut.oa.mvc.domain.strengthen.RolePermissionTogether;

@SuppressWarnings("serial")
public class Userrole extends Model {

	private static final Log log = LogFactory.getLog(Userrole.class);

	private int roleID;
	private int userID;

	public int getRoleID() {
		return roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	@Override
	public String toString() {
		return "Userrole [roleID=" + roleID + ", userID=" + userID + "]";
	}

	/**
	 * 根据角色ID来加载相应的角色权限[完全对象映射]
	 */
	public List<RolePermissionTogether> getRolePermissionTogetherByRoleID(
			String roleID) {
		List<RolePermissionTogether> rptList = new ArrayList<RolePermissionTogether>();

		Rolepermission rolepermission = new Rolepermission();
		Permission permission = new Permission();
		Menu menu = new Menu();
		Resource resource = new Resource();
		Operator operator = new Operator();

		StringBuilder sql = new StringBuilder();
		sql.append("select ");

		sql.append(" rp.id, ");

		sql.append(" rp.permissionID, ");
		sql.append(" p.menuID, ");
		sql.append(" p.resourceID, ");
		sql.append(" p.optID, ");
		sql.append(" p.description, ");

		sql.append(" m.menuname, ");

		sql.append(" r.resourcename, ");
		sql.append(" r.resourcevalue, ");

		sql.append(" o.optname, ");
		sql.append(" o.optvalue ");

		sql.append(" from ");
		sql.append(tableName() + " as ur,");
		sql.append(rolepermission.tableName() + " as rp,");
		sql.append(permission.tableName() + " as p,");
		sql.append(menu.tableName() + " as m,");
		sql.append(resource.tableName() + " as r,");
		sql.append(operator.tableName() + " as o");
		sql.append(" where ");
		sql.append(" ur.roleID=rp.roleID ");
		sql.append(" and rp.permissionID = p.id  ");
		sql.append(" and p.menuID = m.id  ");
		sql.append(" and p.resourceID = r.id  ");
		sql.append(" and p.optID = o.id ");
		sql.append(" and ur.roleID = ? ");
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = DBHelper.getConnection().prepareStatement(sql.toString());
			ps.setObject(1, roleID);
			log.debug("Userrole:getRolePermissionTogetherByRoleID, sql: "
					+ sql.toString() + ", Values[" + roleID + "]");
			rs = ps.executeQuery();

			Role role = new Role();
			role = role.get(Long.parseLong(roleID));

			while (rs.next()) {

				Menu m = new Menu();
				m.setId(rs.getLong(3));
				m.setMenuname(rs.getString(7));

				Resource r = new Resource();
				r.setId(rs.getLong(4));
				r.setResourcename(rs.getString(8));
				r.setResourcevalue(rs.getString(9));

				Operator o = new Operator();
				o.setId(rs.getLong(5));
				o.setOptname(rs.getString(10));
				o.setOptvalue(rs.getString(11));

				PermissionTogether pt = new PermissionTogether();
				pt.setId(rs.getLong(2));
				pt.setMenu(m);
				pt.setResource(r);
				pt.setOperator(o);
				pt.setDescription(rs.getString(6));

				RolePermissionTogether rpt = new RolePermissionTogether();
				rpt.setId(rs.getLong(1));
				rpt.setRole(role);
				rpt.setPermissiontogether(pt);

				rptList.add(rpt);
			}
		} catch (Exception e) {
			log.error(e, e.getCause());
		} finally {
			DbUtils.closeQuietly(rs);
			DbUtils.closeQuietly(ps);
			sql = null;
		}
		return rptList;
	}
}
