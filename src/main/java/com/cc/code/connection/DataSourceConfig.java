package com.cc.code.connection;
/**
 * 
 * @author jlon
 *
 */
public class DataSourceConfig {
	public String driver;
	public String url;
	public String user;
	public String pass;
	public int dataSourceType;

	public static final int MYSQL = 1;
	public static final int SQLSERVER = 2;

	public DataSourceConfig(String driver, String url, String user,
			String pass, int dataSourceType) {
		super();
		this.driver = driver;
		this.url = url;
		this.user = user;
		this.pass = pass;
		this.dataSourceType = dataSourceType;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public int getDataSourceType() {
		return dataSourceType;
	}

	public void setDataSourceType(int dataSourceType) {
		this.dataSourceType = dataSourceType;
	}

}
