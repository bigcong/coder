package com.jlong.code.build;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jlong.code.connection.ConnectionFactory;
import com.jlong.code.connection.DataSourceConfig;
import com.jlong.code.generator.Generator;

/**
 * 
 * @author jlon
 *
 */
public class Build {

	public static void main(String[] args) {
		try {
			generator();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void generator() throws Exception {
		// 得到数据源
		DataSourceConfig cfg = new DataSourceConfig("com.mysql.jdbc.Driver",
				"jdbc:mysql://127.0.0.1:3306/haolin", "root", "123456789",
				DataSourceConfig.MYSQL);
        
		/**
         * 第一个参数:选择你要生成的表名,此参数可选。不写默认生成全部
         * 第二个参数:生成的文件的作存放的包名，默认是D盘
         * 第三个参数:模板文件的位置
         * 
         *第三个参数:数据源配置
         */
		Generator generator = new Generator(new String[]{"log"},"com.ailink.framework.core.log",
				"com/jlong/code/template/", cfg);
		generator.generate(true, true, true, true, true, true, true, true,true);

}}
