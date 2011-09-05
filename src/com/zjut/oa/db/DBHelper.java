package com.zjut.oa.db;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <b>数据库操作工具</b><br />
 * 底层使用Apache开源软件包DBUtils：{@link DBUtils http://commons.apache.org/dbutils/ }
 * 
 * @author qingtian
 * 
 *         2011-1-29 下午10:01:25
 */
public final class DBHelper {

	private final static Log log = LogFactory.getLog(DBHelper.class);

	private final static QueryRunner qRunner = new QueryRunner();
	private final static ColumnListHandler columnListHandler = new ColumnListHandler() {
		protected Object handleRow(ResultSet rs) throws SQLException {
			Object obj = super.handleRow(rs);
			if (obj instanceof BigInteger)
				return ((BigInteger) obj).longValue();
			return obj;
		}
	};
	private final static ScalarHandler scaleHandler = new ScalarHandler() {
		public Object handle(ResultSet rs) throws SQLException {
			Object obj = super.handle(rs);
			if (obj instanceof BigInteger)
				return ((BigInteger) obj).longValue();
			return obj;
		}
	};
	@SuppressWarnings("serial")
	private final static List<Class<?>> primitiveClasses = new ArrayList<Class<?>>() {
		{
			add(Long.class);
			add(Integer.class);
			add(String.class);
			add(java.util.Date.class);
			add(java.sql.Date.class);
			add(java.sql.Timestamp.class);
		}
	};

	/**
	 * 从数据库连接池管理器{@link DBManager}中取数据库连接
	 * 
	 * @return Connection
	 */
	public static Connection getConnection() {
		try {
			return DBManager.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException("Can't get a connection in DBHelper!"
					+ e);
		}
	}

	/**
	 * 读取对象
	 * 
	 * @param <T>
	 *            对象类型
	 * @param beanClass
	 * @param sql
	 *            语句
	 * @param params
	 *            参数
	 * @return 指定类型对象
	 */
	@SuppressWarnings("unchecked")
	public static <T> T read(Class<T> beanClass, String sql, Object... params) {
		try {
			log.debug("read: " + sql);
			return (T) qRunner.query(getConnection(), sql,
					isPrimitive(beanClass) ? scaleHandler : new BeanHandler<T>(
							beanClass), params);
		} catch (SQLException e) {
			throw new RuntimeException(
					"Can't invoke read(Class<T> beanClass,String sql,Object ...params) in DBHelper!");
		}
	}

	/**
	 * 查询数据
	 * 
	 * @param <T>
	 *            对象类型
	 * @param beanClass
	 * @param sql
	 *            语句
	 * @param params
	 *            参数
	 * @return 对象列表
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> List<T> query(Class<T> beanClass, String sql,
			Object... params) {
		try {
			log.debug("query: " + sql );
			return (List<T>) qRunner.query(getConnection(), sql,
					isPrimitive(beanClass) ? columnListHandler
							: new BeanListHandler(beanClass), params);
		} catch (SQLException e) {
			throw new RuntimeException(
					"Can't invoke query(Class<T> beanClass,String sql,Object ...params)"
							+ e);
		}
	}

	/**
	 * 分页查询数据
	 * 
	 * @param <T>
	 *            数据类型
	 * @param beanClass
	 * @param sql
	 *            语句
	 * @param page
	 *            页码
	 * @param count
	 *            数量
	 * @param params
	 *            参数
	 * @return
	 */
	public static <T> List<T> querySlice(Class<T> beanClass, String sql,
			int page, int count, Object... params) {
		if (page < 0 || count < 0)
			throw new IllegalArgumentException(
					"Illegal parameter of 'page' or 'count'");
		int from = (page - 1) * count;
		count = (count > 0) ? count : Integer.MAX_VALUE;
		log.debug("querySlice: " + sql + ",page: " + page + ",from: " + from
				+ ",count: " + count);
		return query(beanClass, sql + " limit ?,?",
				ArrayUtils.addAll(params, new Integer[] { from, count }));
	}

	/**
	 * 数据统计
	 * 
	 * @param sql
	 *            语句
	 * @param params
	 *            参数
	 * @return 统计结果
	 */
	public static long stat(String sql, Object... params) {
		try {
			log.debug("stat: " + sql);
			Number num = (Number) qRunner.query(getConnection(), sql,
					scaleHandler, params);
			return (num != null) ? num.longValue() : -1;
		} catch (SQLException e) {
			throw new RuntimeException(
					"Can't invoke stat(String sql,Object ...params)" + e);
		}
	}

	/**
	 * 数据更新：insert/update/delete
	 * 
	 * @param sql
	 *            语句
	 * @param params
	 *            参数
	 * @return 影响记录数
	 */
	public static int update(String sql, Object... params) {
		try {
			log.debug("update: " + sql);
			return qRunner.update(getConnection(), sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 批量执行语句
	 * 
	 * @param sql
	 *            语句
	 * @param params
	 *            二维数组的参数
	 * @return 影响结果集的数组
	 */
	public static int[] batch(String sql, Object[][] params) {
		try {
			log.debug("batch: " + sql);
			return qRunner.batch(getConnection(), sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private final static boolean isPrimitive(Class<?> cls) {
		return cls.isPrimitive() || primitiveClasses.contains(cls);
	}
}
