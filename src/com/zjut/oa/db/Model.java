package com.zjut.oa.db;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <b>数据库表对象的基类</b><br />
 * 具体表对象继承此类并添加对应表中的其他字段作为新类属性。每个表必须定义id，且id属性已在此类提供,子类无需重复定义<br />
 * <br/>
 * 
 * <b>本类提供如下功能：</b><br/>
 * 一、基本数据查询和统计功能：<br />
 * <ol>
 * <li>查询所有记录(不分页)，对应方法：{@link#listAll()}</li>
 * <li>按指定排序规则查询所有记录(不分页)，对应方法：{@link#listAll(String order)}</li>
 * <li>按指定过滤条件筛选记录(不分页)，对应方法{@link#filter(String filter)}</li>
 * <li>查询所有记录(分页)，对应方法：{@link#listAllByPage(int page, int size)}</li>
 * <li>按指定排序规则查询所有记录(分页)，对应方法：{@link#listAllByPage(String order, int page, int
 * size)}</li>
 * <li>按指定过滤条件筛选记录(分页)，对应方法{@link#filterByPage(String filter, int page, int
 * size)}</li>
 * <li>统计记录总数，对应方法：{@link#totalCount()}</li>
 * <li>按指定过滤条件统计记录总数，对应方法：{@link#totalCount(String filter)}</li>
 * </ol>
 * 二、对象常用功能<br />
 * <ol>
 * <li>保存(更新)对象，对应方法：{@link#save()}</li>
 * <li>删除对象，对应方法：{@link#delete()}</li>
 * <li>根据主键(id)获取对象，对应方法：{@link#get(java.math.BigInteger id)}</li>
 * <li>子类通用功能：根据主键(id)获取泛型对象，对应方法：{@link#get(long id)}</li>
 * <li>批量获取对象，对应方法：{@link#batchGet(List<Long> ids)}</li>
 * <li>获取对应表名（强制转换为小写），对应方法：{@link#tableName()}</li>
 * </ol>
 * 
 * @author qingtian
 * 
 *         2011-1-30 下午03:29:55
 */
@SuppressWarnings("serial")
public class Model implements Serializable {

	private static final Log log = LogFactory.getLog(Model.class);
	private String thisTableName = null;
	/** 数据库表名默认前缀 ，值为 "qt_" */
	private final static String TABLE_PREFIX = "qt_";
	/** 表名前缀，初始化为 {@link#TABLE_PREFIX} */
	private String tableNamePrefix = TABLE_PREFIX;

	public String getTableNamePrefix() {
		return tableNamePrefix;
	}

	public void setTableNamePrefix(String tableNamePrefix) {
		this.tableNamePrefix = tableNamePrefix;
	}

	/** 主键 */
	private long id = -1;

	public long getId() {
		return id;
	}

	public void setId(long thisId) {
		this.id = thisId;
	}

	/**
	 * 查询全部对象(默认排序)不分页。如需分页请查看{@link#list(int page, int size)}
	 * 
	 * @return 对象列表
	 */
	public List<? extends Model> listAll() {
		String sql = "select * from " + tableName();
		return DBHelper.query(getClass(), sql);
	}

	/**
	 * 根据排序规则来查询对象，不分页。如需分页请查看{@link#list(int page, int size)}
	 * 
	 * @param order
	 *            排序规则
	 * @return 对象列表
	 */
	public List<? extends Model> listAll(String order) {
		String sql = "select * from " + tableName() + " " + order;
		return DBHelper.query(getClass(), sql);
	}

	/**
	 * 不分页筛选对象
	 * 
	 * @param filter
	 *            带 where 的筛选条件
	 * @return
	 */
	public List<? extends Model> filter(String filter) {
		String sql = "select * from " + tableName() + " " + filter;
		return DBHelper.query(getClass(), sql);
	}

	/**
	 * 分页列出所有对象
	 * 
	 * @param page
	 *            页码
	 * @param size
	 *            待查询记录数
	 * @return
	 */
	public List<? extends Model> listAllByPage(int page, int size) {
		String sql = "select * from " + tableName();
		return DBHelper.querySlice(getClass(), sql, page, size);
	}

	/**
	 * 根据排序规则分页列出所有对象
	 * 
	 * @param order
	 *            排序规则
	 * @param page
	 *            页码
	 * @param size
	 *            待查询记录数
	 * @return
	 */
	public List<? extends Model> listAllByPage(String order, int page, int size) {
		String sql = "select * from " + tableName() + " " + order;
		return DBHelper.querySlice(getClass(), sql, page, size);
	}

	/**
	 * 分页列出条件过滤出的所有对象
	 * 
	 * @param filter
	 *            带 where 子句后的条件
	 * @param page
	 *            页码
	 * @param size
	 *            待查询记录数
	 * @return
	 */
	public List<? extends Model> filterByPage(String filter, int page, int size) {
		String sql = "select * from " + tableName() + filter;
		return DBHelper.querySlice(getClass(), sql, page, size);
	}

	/**
	 * 统计对象总数
	 * 
	 * @return
	 */
	public int totalCount() {
		String sql = "select count(*) from " + tableName();
		return (int) DBHelper.stat(sql);
	}

	/**
	 * 统计条件过滤后对象的总数<br />
	 * 
	 * @param filter
	 *            带 where 子句后的条件字符串
	 * @return
	 */
	public int totalCount(String filter) {
		String sql = "select count(*) from " + tableName() + filter;
		return (int) DBHelper.stat(sql);
	}

	/**
	 * 保存对象到数据库表中
	 * 
	 * @return 刚插入记录自动生成的主键值
	 */
	public long save() {
		if (getId() > 0)
			_updateObject(this);
		else
			setId(_insertObject(this));
		return getId();
	}

	/**
	 * 根据ID主键删除对象
	 * 
	 * @return 删除操作结果影响记录数
	 */
	public boolean delete() {
		String sql = "delete from " + tableName() + " where id=?";
		return DBHelper.update(sql, getId()) == 1;
	}

	public Model get(java.math.BigInteger id) {
		if (id == null)
			return null;
		return get(id.longValue());
	}

	/**
	 * 根据主键读取对象详细资料
	 * 
	 * @param <T>
	 * @param id
	 * @return 目标对象
	 */
	@SuppressWarnings("unchecked")
	public <T extends Model> T get(long id) {
		if (id < 0)
			return null;
		String sql = "select * from " + tableName() + " where id=?";
		return (T) DBHelper.read(getClass(), sql, id);

	}

	/**
	 * 根据主键列表批量读取对象
	 * 
	 * @param ids
	 * @return 目标对象列表
	 */
	public List<? extends Model> batchGet(List<Long> ids) {
		if (ids == null || ids.size() == 0)
			return null;
		StringBuilder sql = new StringBuilder("select * from ");
		sql.append(tableName());
		sql.append(" where id in (");
		int size = ids.size();
		for (int i = 1; i <= size; i++) {
			sql.append('?');
			if (i < size)
				sql.append(',');
		}
		sql.append(')');
		List<? extends Model> beans = DBHelper.query(getClass(),
				sql.toString(), ids.toArray(new Object[size]));
		return beans;
	}

	/**
	 * 批量删除
	 * 
	 * @param ids
	 *            ID数组
	 * @return 影响记录数
	 */
	public int[] batchDelete(String[] ids) {
		StringBuilder sql = new StringBuilder("delete from ");
		sql.append(tableName());
		sql.append(" where id in(");
		int length = ids.length;
		for (int i = 1; i <= length; i++) {
			sql.append('?');
			if (i < length)
				sql.append(',');
		}
		sql.append(')');
		String[][] param = { ids };
		return DBHelper.batch(sql.toString(), param);
	}

	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (!getClass().equals(obj.getClass()))
			return false;
		Model other = (Model) obj;
		return other.getId() == getId();
	}

	/**
	 * 对象对应数据库中的表名<br />
	 * 表名=前缀 {@link #tableNamePrefix } + 类简单名<br/>
	 * <b>注意：</b>类名---映射--->表名后 <b>全为小写</b>
	 * 
	 * @return 数据库表名
	 */
	protected String tableName() {
		String fullName = getClass().getName();
		int startDocPos = fullName.lastIndexOf('.');
		// 得到不带包路径的类名
		String className = fullName.substring(startDocPos + 1);
		// 转换类名映射到表名为全小写
		className = className.toLowerCase();
		if (thisTableName == null)
			thisTableName = tableNamePrefix + className;
		return thisTableName;
	}

	/**
	 * 列出要插入到数据库的域集合，子类可以覆盖此方法
	 * 
	 * @return 可插入域的Map
	 */
	protected Map<String, Object> listInsertableFields() {
		try {
			@SuppressWarnings("unchecked")
			Map<String, Object> props = BeanUtils.describe(this);
			if (getId() < 0)
				props.remove("id");
			props.remove("class");
			props.remove("tableNamePrefix");
			log.debug("listInsertableFields: " + props);
			return props;
		} catch (Exception e) {
			throw new RuntimeException("Exception where fetching fields of "
					+ this);
		}
	}

	/**
	 * 根据主键更新对象
	 * 
	 * @param model
	 *            待更新对象
	 */
	private void _updateObject(Model model) {
		Map<String, Object> bean = model.listInsertableFields();
		// copy fields without property 'id'
		Map<String, Object> cp_bean = new HashMap<String, Object>();
		for (String key : bean.keySet()) {
			if (!key.equals("id")) {
				Object value = bean.get(key);
				cp_bean.put(key, value);
			}
		}

		String[] fields = cp_bean.keySet().toArray(new String[cp_bean.size()]);
		StringBuilder sql = new StringBuilder("update ");
		sql.append(model.tableName());
		sql.append(" set ");
		int fieldsLength = fields.length;
		for (int i = 0; i < fieldsLength; i++) {
			if (i > 0)
				sql.append(',');
			sql.append(fields[i] + "=? ");
		}
		sql.append(" where id=?");
		log.debug("_upateObject sql: " + sql);
		// 更新对象
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = DBHelper.getConnection().prepareStatement(sql.toString());
			for (int i = 0; i < fieldsLength; i++) {
				ps.setObject(i + 1, cp_bean.get(fields[i]));
			}
			ps.setObject(fieldsLength + 1, getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(" _upateObject : " + e);
		} finally {
			DbUtils.closeQuietly(rs);
			DbUtils.closeQuietly(ps);
			sql = null;
			fields = null;
			bean = null;
			cp_bean = null;
		}
	}

	/**
	 * 插入对象
	 * 
	 * @param model
	 * @return 生成的主键
	 */
	private static long _insertObject(Model model) {
		Map<String, Object> bean = model.listInsertableFields();
		String[] fields = bean.keySet().toArray(new String[bean.size()]);
		StringBuilder sql = new StringBuilder("insert into ");
		sql.append(model.tableName());
		sql.append('(');
		int fieldsLength = fields.length;
		for (int i = 0; i < fieldsLength; i++) {
			if (i > 0)
				sql.append(',');
			sql.append(fields[i]);
		}
		sql.append(") values(");
		for (int i = 0; i < fieldsLength; i++) {
			if (i > 0)
				sql.append(',');
			sql.append('?');
		}
		sql.append(')');
		log.debug("_inerstObject sql: " + sql);
		// 插入对象
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = DBHelper.getConnection().prepareStatement(sql.toString(),
					PreparedStatement.RETURN_GENERATED_KEYS);
			for (int i = 0; i < fieldsLength; i++) {
				ps.setObject(i + 1, bean.get(fields[i]));
			}
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			return rs.next() ? rs.getLong(1) : -1;
		} catch (SQLException e) {
			throw new RuntimeException(" _insertObject : " + e);
		} finally {
			DbUtils.closeQuietly(rs);
			DbUtils.closeQuietly(ps);
			sql = null;
			fields = null;
			bean = null;
		}
	}
}
