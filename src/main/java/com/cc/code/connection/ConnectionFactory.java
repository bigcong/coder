package com.cc.code.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
		private static Connection conn;

		private ConnectionFactory() {
			super();
		}

		public static Connection getConnection(DataSourceConfig cfg) {
			if (conn == null) {
				try {
					Class.forName(cfg.driver);
					try {
						conn = DriverManager.getConnection(cfg.url, cfg.user,
								cfg.pass);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
			return conn;
		}
}
