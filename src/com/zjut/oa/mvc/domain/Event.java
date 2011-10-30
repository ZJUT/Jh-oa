package com.zjut.oa.mvc.domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zjut.oa.db.DBHelper;
import com.zjut.oa.db.Model;
import com.zjut.oa.mvc.domain.strengthen.EventTogether;

public class Event extends Model {

	private static final Log log = LogFactory.getLog(Event.class);

	private String title;
	private String content;
	private Timestamp modifytime;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getModifytime() {
		return modifytime;
	}

	public void setModifytime(Timestamp modifytime) {
		this.modifytime = modifytime;
	}

	@Override
	public String toString() {
		return "Event [title=" + title + ", content=" + content
				+ ", modifytime=" + modifytime + "]";
	}

	public List<EventTogether> listEventByYear(int year) {
		List<EventTogether> etList = new ArrayList<EventTogether>();
		StringBuilder sql = new StringBuilder();

		sql.append(" select id, ");
		sql.append(" title,");
		sql.append(" content, ");
		sql.append(" modifytime, ");
		sql.append(" year(modifytime), ");
		sql.append(" month(modifytime) ");
		sql.append(" from ");
		sql.append(tableName() + " ");

		sql.append(" where year(modifytime)=? ");
		sql.append(" order by month(modifytime) desc");

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = DBHelper.getConnection().prepareStatement(sql.toString());
			ps.setObject(1, year);
			log.debug("Event:listEventByYear, sql: " + sql.toString()
					+ ", Values[" + year + "]");
			rs = ps.executeQuery();
			while (rs.next()) {
				EventTogether et = new EventTogether();

				Event e = new Event();
				e.setId(rs.getLong(1));
				e.setTitle(rs.getString(2));
				e.setContent(rs.getString(3));
				e.setModifytime(rs.getTimestamp(4));

				et.setId(rs.getLong(1));
				et.setEvent(e);
				et.setYear(rs.getInt(5));
				et.setMonth(rs.getInt(6));

				etList.add(et);
			}
		} catch (Exception e) {
			log.error(e, e.getCause());
		} finally {
			DbUtils.closeQuietly(rs);
			DbUtils.closeQuietly(ps);
			sql = null;
		}
		return etList;
	}

	public List<Integer> getAllYear() {
		List<Integer> yearList = new ArrayList<Integer>();
		StringBuilder sql = new StringBuilder();

		sql.append(" select distinct year(modifytime) ");
		sql.append(" from ");
		sql.append(tableName() + " ");

		sql.append(" order by year(modifytime) desc");

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = DBHelper.getConnection().prepareStatement(sql.toString());
			log.debug("Event:getAllYear, sql: " + sql.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				Integer year=rs.getInt(1);
				yearList.add(year);
			}
		} catch (Exception e) {
			log.error(e, e.getCause());
		} finally {
			DbUtils.closeQuietly(rs);
			DbUtils.closeQuietly(ps);
			sql = null;
		}
		return yearList;
	}
}
