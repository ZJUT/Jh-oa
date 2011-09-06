package com.zjut.oa.tool;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 加载属性文件工具类
 * 
 * @author qingtian
 *
 * 2011-4-23 下午04:16:25
 */
public final class PropertiesTool {

	private static final Log log = LogFactory.getLog(PropertiesTool.class);

	public static Properties loadProperties(String properties) {
		Properties prop = new Properties();
		InputStream in = PropertiesTool.class.getClassLoader()
				.getResourceAsStream(properties);
		try {
			prop.load(in);
		} catch (IOException e) {
			log.error(e.getCause());
		}
		return prop;
	}

	public static void main(String args[]) {
		Properties p = PropertiesTool.loadProperties("zookeeper.properties");
		System.out.println(p.get("zookeeper.ip"));
		System.out.println(p.get("zookeeper.port"));
	}

}
