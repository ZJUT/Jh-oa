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

@SuppressWarnings("serial")
public class Permission extends Model {
	
	private static final Log log=LogFactory.getLog(Permission.class);
	
	private int menuID;
	private int resourceID;
	private int optID;
	private String description;

	public int getMenuID() {
		return menuID;
	}

	public void setMenuID(int menuID) {
		this.menuID = menuID;
	}

	public int getResourceID() {
		return resourceID;
	}

	public void setResourceID(int resourceID) {
		this.resourceID = resourceID;
	}

	public int getOptID() {
		return optID;
	}

	public void setOptID(int optID) {
		this.optID = optID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Permission [menuID=" + menuID + ", resourceID=" + resourceID
				+ ", optID=" + optID + ", description=" + description + "]";
	}

	/**
	 * 加载所有权限[完全对象映射]
	 */
	public List<PermissionTogether> getPermissionTogether( String orderby) {
		List<PermissionTogether> ptList = new ArrayList<PermissionTogether>();

		Permission permission = new Permission();
		Menu menu = new Menu();
		Resource resource = new Resource();
		Operator operator = new Operator();

		StringBuilder sql = new StringBuilder();
		sql.append("select ");

		sql.append(" p.id, ");
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
		sql.append(tableName() + " as p,");
		sql.append(menu.tableName() + " as m,");
		sql.append(resource.tableName() + " as r,");
		sql.append(operator.tableName() + " as o");
		sql.append(" where ");
		sql.append(" p.menuID = m.id  ");
		sql.append(" and p.resourceID = r.id  ");
		sql.append(" and p.optID = o.id ");
		if(orderby!=null)
			sql.append(orderby);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = DBHelper.getConnection().prepareStatement(sql.toString());
			log.debug("Permission:getPermissionTogether, sql: "
					+ sql.toString() + ", Values[]");
			rs = ps.executeQuery();

			while (rs.next()) {

				Menu m = new Menu();
				m.setId(rs.getLong(2));
				m.setMenuname(rs.getString(6));

				Resource r = new Resource();
				r.setId(rs.getLong(3));
				r.setResourcename(rs.getString(7));
				r.setResourcevalue(rs.getString(8));

				Operator o = new Operator();
				o.setId(rs.getLong(4));
				o.setOptname(rs.getString(9));
				o.setOptvalue(rs.getString(10));

				PermissionTogether pt = new PermissionTogether();
				pt.setId(rs.getLong(1));
				pt.setMenu(m);
				pt.setResource(r);
				pt.setOperator(o);
				pt.setDescription(rs.getString(5));

				ptList.add(pt);
			}
		} catch (Exception e) {
			log.error(e, e.getCause());
		} finally {
			DbUtils.closeQuietly(rs);
			DbUtils.closeQuietly(ps);
			sql = null;
		}
		return ptList;
	}
}
