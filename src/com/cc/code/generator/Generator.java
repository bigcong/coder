package com.cc.code.generator;

import java.sql.Connection;
import java.util.List;

import com.cc.code.connection.ConnectionFactory;
import com.cc.code.connection.DataSourceConfig;
import com.cc.code.fileWriter.FileWriterFactory;
import com.cc.code.table.Table;
import com.cc.code.util.TableUtil;

import freemarker.template.Configuration;

/**
 * 
 * @author jlon
 *
 */
public class Generator {

    private String packageName;

    private String templateUrl;

    private String[] tableNames;

    private DataSourceConfig cfg;

    /**
     * @throws Exception
     */
    public Generator(String packageName, String templateUrl, DataSourceConfig cfg) {
        super();
        this.packageName = packageName;
        this.templateUrl = templateUrl;
        this.cfg = cfg;
    }

    /**
     * @throws Exception
     */
    public Generator(String[] tableNames, String packageName, String templateUrl, DataSourceConfig cfg) {
        super();
        this.packageName = packageName;
        this.tableNames = tableNames;
        this.templateUrl = templateUrl;
        this.cfg = cfg;
    }

    /**
     * 
     * {方法功能中文描述}
     * 
     * @param pojo  
     * @param controller
     * @param mapper
     * @param service
     * @param serviceImpl
     * @param sqlXml
     * @param listJsp
     * @param infoJsp
     * @param viewJsp
     * @throws Exception
     * @author: cuicong
     * @datetime:2015年9月15日下午3:25:45
     */
    public void generate(boolean pojo, boolean controller, boolean mapper, boolean service, boolean serviceImpl,
            boolean sqlXml, boolean listJsp, boolean infoJsp, boolean viewJsp)

    throws Exception {

        //获得数据库连接
        Connection conn = ConnectionFactory.getConnection(cfg);

        //获取所有的表名
        List<Table> tables = TableUtil.getTables(conn, packageName, tableNames);

        //获取模板
        Configuration configuration = FileWriterFactory.getConfiguration(templateUrl);

        for (Table table : tables) {
            if (pojo) {
                buildFactory(table, configuration, FileWriterFactory.POJO);
            }
            if (controller) {
                buildFactory(table, configuration, FileWriterFactory.CONTROLLER);
            }
            if (mapper) {
                buildFactory(table, configuration, FileWriterFactory.MAPPER);
            }
            if (sqlXml) {
                buildFactory(table, configuration, FileWriterFactory.SQLXML);
            }
            if (service) {
                buildFactory(table, configuration, FileWriterFactory.SERVICE);
            }
            if (serviceImpl) {
                buildFactory(table, configuration, FileWriterFactory.SERVICE_IMPL);
            }
            if (listJsp) {
                buildFactory(table, configuration, FileWriterFactory.LISTJSP);
            }
            if (infoJsp) {
                buildFactory(table, configuration, FileWriterFactory.INFOJSP);
            }
            if (viewJsp) {
                buildFactory(table, configuration, FileWriterFactory.VIEWJSP);
            }
        }
        System.err.println("祝贺你,生成成功！");
    }

    /**
     * 
     */
    public void buildFactory(Table table, Configuration configuration, int type) {
        String templateUrl = "";
        switch (type) {
        case FileWriterFactory.POJO:
            templateUrl = "pojo.ftl";
            break;
        case FileWriterFactory.CONTROLLER:
            templateUrl = "controller.ftl";
            break;
        case FileWriterFactory.MAPPER:
            templateUrl = "mapper.ftl";
            break;
        case FileWriterFactory.SERVICE:
            templateUrl = "service.ftl";
            break;
        case FileWriterFactory.SERVICE_IMPL:
            templateUrl = "serviceImp.ftl";
            break;
        case FileWriterFactory.SQLXML:
            templateUrl = "sqlXml.ftl";
            break;
        case FileWriterFactory.LISTJSP:
            templateUrl = "list.flt";
            break;
        case FileWriterFactory.INFOJSP:
            templateUrl = "info.flt";
            break;
        case FileWriterFactory.VIEWJSP:
            templateUrl = "view.ftl";
            break;
        }
        build(table, configuration, templateUrl, type);
    }

    /**
    * 
    * @param table
    * @param configuration
    */
    public void build(Table table, Configuration configuration, String templateUrl, int type) {
        FileWriterFactory.dataSourceOut(configuration, // 解析对象
                templateUrl, //模板名称
                table, //数据对象
                type);
    }

}
