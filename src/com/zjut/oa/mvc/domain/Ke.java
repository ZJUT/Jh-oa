package com.zjut.oa.mvc.domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zjut.oa.db.DBHelper;
import com.zjut.oa.db.Model;
import com.zjut.oa.mvc.domain.strengthen.FreeKeTogether;
import com.zjut.oa.mvc.domain.strengthen.KeTogether;
import com.zjut.oa.mvc.domain.strengthen.UserTogether;

@SuppressWarnings("serial")
public class Ke extends Model {

	private static final Log log = LogFactory.getLog(Ke.class);

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

	public List<KeTogether> getKeTogetherByUserID(String userID, String orderby) {
		List<KeTogether> ktList = new ArrayList<KeTogether>();

		User user = new User();

		StringBuilder sql = new StringBuilder();
		sql.append("select ");

		sql.append(" k.id, ");
		sql.append(" k.userID, ");
		sql.append(" k.kevalue, ");

		sql.append(" from ");
		sql.append(tableName() + " as k, ");
		sql.append(user.tableName() + " as u ");
		sql.append(" where ");
		sql.append(" k.userID = u.id ");
		sql.append(" and k.userID = ?");
		if (orderby != null)
			sql.append(orderby);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = DBHelper.getConnection().prepareStatement(sql.toString());
			ps.setObject(1, userID);
			log.debug("Ke:getKeTogetherByUserID, sql: " + sql.toString()
					+ ", Values[" + userID + "]");
			rs = ps.executeQuery();

			while (rs.next()) {

				User u = new User();
				u = u.get(rs.getLong(2));

				Ke k = new Ke();
				k.setId(rs.getLong(1));
				k.setUserID(rs.getInt(2));
				k.setKevalue(rs.getString(3));

				KeTogether kt = new KeTogether();
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

	public List<FreeKeTogether> findFreeKe(HttpServletRequest req) {
		List<FreeKeTogether> fktList = new ArrayList<FreeKeTogether>();
		StringBuilder filter = new StringBuilder();

		// 接收所有值
		int[][] kevalue_arr = new int[11][7];
		System.out.println("打印接受的课表参数 Start");
		for (int i = 1; i <= 11; i++) {
			for (int j = 1; j <= 7; j++) {
				String param = "kevalue_" + i + "_" + j;
				int tmp_kevalue = req.getParameter(param) == null ? 0 : Integer
						.parseInt((String) req.getParameter(param));
				kevalue_arr[i - 1][j - 1] = tmp_kevalue;
				System.out.print(kevalue_arr[i - 1][j - 1] + ",");
				int startIndex = (i - 1) * 7 + j;
				if (tmp_kevalue == 1) {
					filter.append(" and substring(k.kevalue," + startIndex
							+ ",1)" + " ='0'");
				}
			}
			System.out.println();
		}
		System.out.println("打印接受的课表参数 End");
		log.info("空课查询条件:" + filter.toString());

		StringBuilder sql = new StringBuilder();
		sql.append("select ");
		sql.append(" k.id, ");// 1
		sql.append(" k.userID, ");// 2
		sql.append(" k.kevalue, ");// 3
		sql.append(" count(*), ");// 4
		sql.append(" d.departmentname, ");// 5
		sql.append(" u.id, ");// 6
		sql.append(" u.uid, ");// 7
		sql.append(" u.username, ");// 8
		sql.append(" u.addtime, ");// 9
		sql.append(" u.modifytime, ");// 10
		sql.append(" u.email, ");// 11
		sql.append(" u.cornet, ");// 12
		sql.append(" u.telephone, ");// 13
		sql.append(" u.academyID, ");// 14
		sql.append(" a.academyname, ");// 15
		sql.append(" u.departmentID, ");// 16
		sql.append(" u.jobID, ");// 17
		sql.append(" j.jobname, ");// 18
		sql.append(" u.major, ");// 19
		sql.append(" u.location, ");// 20
		sql.append(" u.dormitory, ");// 21
		sql.append(" u.islock, ");// 22
		sql.append(" u.bbs, ");// 23
		sql.append(" u.introduce, ");// 24
		sql.append(" u.simpleinfo ");// 25
		sql.append(" from ");

		User user = new User();
		Academy academy = new Academy();
		Department department = new Department();
		Job job = new Job();

		sql.append(" " + tableName() + " as k, ");
		sql.append(user.tableName() + " as u, ");
		sql.append(academy.tableName() + " as a, ");
		sql.append(department.tableName() + " as d, ");
		sql.append(job.tableName() + " as j");

		sql.append(" where ");
		sql.append(" k.userID = u.id and ");
		sql.append(" u.academyID = a.id and ");
		sql.append(" u.departmentID = d.id and ");
		sql.append(" u.jobID = j.id  ");

		sql.append(filter.toString());
		
		sql.append(" group by u.departmentID ");
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = DBHelper.getConnection().prepareStatement(sql.toString());
			log.debug("Ke:findFreeKe, sql: " + sql.toString() + ", Values[]");
			rs = ps.executeQuery();

			while (rs.next()) {
				FreeKeTogether fkt = new FreeKeTogether();

				Ke k = new Ke();
				k.setId(rs.getLong(1));
				k.setUserID(rs.getInt(2));
				k.setKevalue(rs.getString(3));

				UserTogether ut = new UserTogether();
				User u = new User();
				u.setId(rs.getInt(2));
				u.setUid(rs.getString(7));
				u.setUsername(rs.getString(8));
				u.setAddtime(rs.getTimestamp(9));
				u.setModifytime(rs.getTimestamp(10));
				u.setEmail(rs.getString(11));
				u.setCornet(rs.getString(12));
				u.setTelephone(rs.getString(13));
				u.setAcademyID(rs.getInt(14));
				u.setDepartmentID(rs.getInt(16));
				u.setJobID(rs.getInt(17));
				u.setMajor(rs.getString(19));
				u.setLocation(rs.getString(20));
				u.setDormitory(rs.getString(21));
				u.setIslock(rs.getInt(22));
				u.setBbs(rs.getString(23));
				u.setIntroduce(rs.getString(24));
				u.setSimpleinfo(rs.getString(25));

				Academy a = new Academy();
				a.setId(rs.getInt(14));
				a.setAcademyname(rs.getString(15));

				Department d = new Department();
				d.setId(rs.getInt(16));
				d.setDepartmentname(rs.getString(5));

				Job j = new Job();
				j.setId(rs.getInt(17));
				j.setJobname(rs.getString(18));

				ut.setId(rs.getInt(2));
				ut.setUser(u);
				ut.setAcademy(a);
				ut.setDepartment(d);
				ut.setJob(j);

				fkt.setKeId(rs.getLong(1));
				fkt.setKe(k);
				fkt.setTotal(rs.getInt(4));
				fkt.setUsertogether(ut);

				fktList.add(fkt);
			}
		} catch (Exception e) {
			log.error(e, e.getCause());
		} finally {
			DbUtils.closeQuietly(rs);
			DbUtils.closeQuietly(ps);
			sql = null;
		}

		return fktList;
	}
}
