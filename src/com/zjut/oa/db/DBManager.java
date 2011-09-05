package com.zjut.oa.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <b>数据库连接池及连接管理,方法均为静态的</b><br />
 * <ol>
 * 		<li>关闭连接池，对应方法：{@link#closeDataSource()}</li>
 * 		<li>从池中取得连接，对应方法：{@link#getConnection()}</li>
 * 		<li>关闭连接，对应方法：{@link#closeConnection()}</li>
 * </ol>
 * 
 * @author qingtian
 * 
 *         2011-1-29 下午03:57:50
 */
public final class DBManager {

	private final static Log log = LogFactory.getLog(DBManager.class);

	private static final String DB_PROPERTIES_FILENAME = "db.properties";
	/** 每个DBManager所在线程保存一个连接 */
	private final static ThreadLocal<Connection> con = new ThreadLocal<Connection>();
	/** 数据源 */
	private static DataSource dataSource;

	static {
		initDataSource(null);
	}

	/**
	 * 关闭数据库连接池
	 * 
	 */
	public final static void closeDataSource() {
		try {
			dataSource.getClass().getMethod("close").invoke(dataSource);
		} catch (Exception e) {
			log.error("Unabled to destroy DataSource!", e);
		}
	}

	/**
	 * 从池中取连接并将其设置为所在线程拥有
	 * 
	 * @return 数据库连接
	 * @throws SQLException
	 */
	public final static Connection getConnection() throws SQLException {
		Connection conn = con.get();
		if (conn == null || conn.isClosed()) {
			conn = dataSource.getConnection();
			con.set(conn);
		}
		return conn;
	}

	/**
	 * 关闭打开的连接
	 * 
	 */
	public final static void closeConnection() {
		Connection conn = con.get();
		try {
			if (conn != null && !conn.isClosed()) {
				conn.setAutoCommit(true);
				conn.close();
			}
		} catch (SQLException e) {
			log.error("Unabled to close connection!", e);
		}
		con.set(null);
	}

	/**
	 * 初始化连接池
	 * 
	 * @param dbProp
	 *            属性文件路径，默认加载名为 db.properties文件。
	 *            键名必须以jdbc.开头,且jdbc.datasource指定数据库连接池完全名， 其后为连接池配置
	 */
	private final static void initDataSource(Properties dbProp) {
		try {
			if (dbProp == null) {
				dbProp = new Properties();
				dbProp.load(DBManager.class.getClassLoader()
						.getResourceAsStream(DB_PROPERTIES_FILENAME));
			}
			// key-value delete : jdbc.
			Properties cp_props = new Properties();
			for (Object key : dbProp.keySet()) {
				String skey = (String) key;
				if (skey.startsWith("jdbc.")) {
					String name = skey.substring(5);
					cp_props.put(name, dbProp.getProperty(skey));
				}
			}
			dataSource = (DataSource) Class.forName(
					cp_props.getProperty("datasource")).newInstance();
			log.info("Using DataSource : " + dataSource.getClass().getName());
			// copy properties into datasource
			BeanUtils.populate(dataSource, cp_props);
	
			Connection conn = getConnection();
			DatabaseMetaData dmd = conn.getMetaData();
			log.info("Connected to " + dmd.getDatabaseProductName() + " "
					+ dmd.getDatabaseProductVersion());
	
			closeConnection();
	
		} catch (Exception e) {
			log.error("InitDataSource failed : " + e);
		}
	}

}
