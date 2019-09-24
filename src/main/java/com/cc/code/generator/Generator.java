package com.cc.code.generator;

import com.cc.code.connection.ConnectionFactory;
import com.cc.code.connection.DataSourceConfig;
import com.cc.code.fileWriter.FileWriterFactory;
import com.cc.code.table.Table;
import com.cc.code.util.FileEnum;
import com.cc.code.util.TableUtil;
import freemarker.template.Configuration;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author jlon
 */
public class Generator {

    private String packageName;

    private String[] tableNames;
    private String url;

    private DataSourceConfig cfg;

    /**
     * @throws Exception
     */
    public Generator(String packageName, DataSourceConfig cfg, String url) {
        super();
        this.packageName = packageName;
        this.cfg = cfg;
        this.url = url;
    }

    /**
     * @throws Exception
     */
    public Generator(String[] tableNames, String packageName, DataSourceConfig cfg, String url) {
        super();
        this.packageName = packageName;
        this.tableNames = tableNames;
        this.cfg = cfg;
        this.url = url;
    }

    /**
     * {方法功能中文描述}
     *
     * @throws Exception
     * @author: cuicong
     * @datetime:2015年9月15日下午3:25:45
     */
    public void generate(String author, List<FileEnum> fileEnum)

        throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //获得数据库连接
        Connection conn = ConnectionFactory.getConnection(cfg);

        //获取所有的表名
        List<Table> tables = TableUtil.getTables(conn, packageName, tableNames);

        //获取模板
        Configuration configuration = FileWriterFactory.getConfiguration("");

        for (Table table : tables) {
            table.setAuthor(author);
            table.setDateTime(dateFormat.format(new Date()));
            fileEnum.forEach(t -> FileWriterFactory.dataSourceOut(configuration, table, t, url));

        }
        System.err.println("祝贺你,生成成功！");
    }

}
