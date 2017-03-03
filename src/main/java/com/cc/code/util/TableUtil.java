package com.cc.code.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cc.code.table.Table;
import com.cc.code.table.TableBind;
import com.cc.code.table.TableCarray;
import com.cc.code.table.TableIndex;

@SuppressWarnings("all")
public class TableUtil {
    /**
     * 功能描述：得到指定参数的表信息参数说明： 参数:catalog:目录名称，一般都为空. 参数：schema:数据库名，对于oracle来说就用户名
     * 参数：tablename:表名称 参数：type :表的类型(TABLE |
     * VIEW)注意：在使用过程中，参数名称必须使用大写的。否则得到什么东西。
     *
     * @throws SQLException
     */
    public static final List<String> getTableNanes(Connection conn, String... tableNames) throws SQLException {

        DatabaseMetaData dme = conn.getMetaData();

        List<String> tableNanes = new ArrayList<String>();
        /**
         *
         * 参数： catalog - 类别名称，因为存储在数据库中，所以它必须匹配类别名称。该参数为 "" 则检索没有类别的描述，为 null
         * 则表示该类别名称不应用于缩小搜索范围 schemaPattern - 模式名称的模式，因为存储在数据库中，所以它必须匹配模式名称。该参数为
         * "" 则检索那些没有模式的描述，为 null 则表示该模式名称不应用于缩小搜索范围 tableNamePattern -
         * 表名称模式，因为存储在数据库中，所以它必须匹配表名称 types - 要包括的表类型组成的列表，null 表示返回所有类型 返回：
         *
         * ResultSet - 每一行都是一个表描述 抛出： SQLException - 如果发生数据库访问错误
         */
        List<ResultSet> sets = new ArrayList<ResultSet>();

        if (tableNames != null && tableNames.length != 0) {
            for (String tableName : tableNames) {
                ResultSet rs = dme.getTables("", "", tableName, new String[]{"TABLE"});
                sets.add(rs);
            }
        } else {
            ResultSet rs = dme.getTables("", "", "", new String[]{"TABLE"});
            sets.add(rs);
        }

        List<Map> list = resToList(sets);

        for (Map map : list) {
            String tableName = map.get("TABLE_NAME").toString();
            tableNanes.add(tableName);
        }
        return tableNanes;
    }

    /**
     * 取得表中的字段
     *
     * @throws Exception
     */
    public static final List<Map<String, Object>> getCarrays(Connection conn, String tableName) throws Exception {
        String sql = String.format("SELECT * FROM `%s` LIMIT 1", tableName);

        PreparedStatement stmt = conn.prepareStatement(sql);

        ResultSet rs = stmt.executeQuery();

        List<Map<String, Object>> list = getColumns(rs);

        return list;
    }

    /**
     * 取得表中的字段的注视
     *
     * @throws Exception
     */
    public static final String getRemark(Connection conn, String tableName, String columnName) throws Exception {
        String sql = String.format("SELECT " + "COLUMN_NAME, DATA_TYPE, "
                + "COLUMN_COMMENT FROM information_schema.columns " + "WHERE table_name  =? and COLUMN_NAME =? ");

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, tableName);
        stmt.setString(2, columnName);

        ResultSet rs = stmt.executeQuery();
        String remark = null;

        while (rs.next()) {
            remark = rs.getString("COLUMN_COMMENT");
        }
        return remark;
    }

    /**
     * 取得表中的索引
     *
     * @return
     * @throws SQLException
     */
    public static final List<Map> getIndexs(Connection conn, String tableName, boolean unique) throws SQLException {
        DatabaseMetaData dmd = conn.getMetaData();
        ResultSet rs = dmd.getIndexInfo(null, null, tableName, unique, true);
        return resToList(rs);
    }

    /**
     * 取得主外键关系
     *
     * @param conn
     * @param table
     * @return
     * @throws Exception
     */
    public static final Map getBinds(Connection conn, String tableName) throws Exception {
        DatabaseMetaData dmd = conn.getMetaData();
        Map map = new HashMap();
        ResultSet rs = null;
        rs = dmd.getExportedKeys("", "", tableName);

        map.put("ExportedKeys", resToList(rs));
        rs = dmd.getImportedKeys("", "", tableName);
        map.put("ImportedKeys", resToList(rs));
        return map;
    }

    /**
     * ResultSet转List
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    public static final List<Map> resToList(ResultSet resultSets) throws SQLException {
        List<Map> list = new ArrayList<Map>();
        while (resultSets.next()) {
            list.add(resToMap(resultSets));
        }
        return list;
    }

    /**
     * List<ResultSet>转List
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    public static final List<Map> resToList(List<ResultSet> resultSets) throws SQLException {
        List<Map> list = new ArrayList<Map>();
        for (int i = 0; i < resultSets.size(); i++) {
            while (resultSets.get(i).next()) {
                list.add(resToMap(resultSets.get(i)));
            }
        }
        return list;
    }

    /**
     * ResultSet转Map
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    public static final Map resToMap(ResultSet rs) throws SQLException {
        Map map = new HashMap();
        ResultSetMetaData rsmd = rs.getMetaData();
        int cols = rsmd.getColumnCount();
        for (int i = 1; i <= cols; i++)
            map.put(rsmd.getColumnName(i), rs.getObject(i));

        return map;
    }

    /**
     * 取得字段详情
     *
     * @param rs
     * @return
     * @throws Exception
     */
    public static final List<Map<String, Object>> getColumns(ResultSet rs) throws Exception {
        List<Map<String, Object>> ret = new ArrayList<Map<String, Object>>();

        // ResultSetMetaData 有关 ResultSet 中列的名称和类型的信息。
        ResultSetMetaData rsmd = rs.getMetaData();

        int count = rsmd.getColumnCount();

        for (int i = 1; i <= count; i++) {
            String columnName = rsmd.getColumnName(i);
            int columnType = rsmd.getColumnType(i);
            String columnLabel = rsmd.getColumnLabel(i);
            String columnTypeName = rsmd.getColumnTypeName(i);
            String catalogName = rsmd.getCatalogName(i);
            String columnClassName = rsmd.getColumnClassName(i);
            int precision = rsmd.getPrecision(i);
            int scale = rsmd.getScale(i);
            String schemaName = rsmd.getSchemaName(i);
            String tableName = rsmd.getTableName(i);
            int columnDisplaySize = rsmd.getColumnDisplaySize(i);
            boolean isAutoIncrement = rsmd.isAutoIncrement(i);
            boolean isCaseSensitive = rsmd.isCaseSensitive(i);
            boolean isCurrency = rsmd.isCurrency(i);
            boolean isDefinitelyWritable = rsmd.isDefinitelyWritable(i);
            int isNullable = rsmd.isNullable(i);
            boolean isReadOnly = rsmd.isReadOnly(i);
            boolean isSearchable = rsmd.isSearchable(i);
            boolean isSigned = rsmd.isSigned(i);
            boolean isWritable = rsmd.isWritable(i);

            @SuppressWarnings("rawtypes")
            Map e = new HashMap();
            e.put("i", i);
            e.put("columnName", columnName);
            e.put("columnType", columnType);
            e.put("columnLabel", columnLabel);
            e.put("columnTypeName", columnTypeName);
            e.put("catalogName", catalogName);
            e.put("columnClassName", columnClassName);
            e.put("precision", precision);
            e.put("scale", scale);
            e.put("schemaName", schemaName);
            e.put("tableName", tableName);
            e.put("columnDisplaySize", columnDisplaySize);
            e.put("isAutoIncrement", isAutoIncrement);
            e.put("isCaseSensitive", isCaseSensitive);
            e.put("isCurrency", isCurrency);
            e.put("isDefinitelyWritable", isDefinitelyWritable);
            e.put("isNullable", isNullable);
            e.put("isReadOnly", isReadOnly);
            e.put("isSearchable", isSearchable);
            e.put("isSigned", isSigned);
            e.put("isWritable", isWritable);
            e.put("javaForType", JavaType.getBasicType(JavaType.getType(rsmd, columnLabel)));
            ret.add(e);
        }
        return ret;
    }

    /**
     * @param conn          数据库连接
     * @param sqlMapPackage //生成的文件存放的总位置
     * @param pojoPackage   实体类的位置
     * @param daoPackage    接口的位置
     * @param impPackage    实现类的位置
     * @param extPackage    拓展类的位置
     * @param xmlPackage    mybatis xml的位置
     * @param xmlURL        模板文件相对地址
     * @return
     * @throws Exception
     */
    public static final List<Table> getTables(Connection conn, String packageName, String[] tableNames)
            throws Exception {

        // 存放数据库当中的表
        List<Table> tables = new ArrayList<Table>();

        Table table = null;

        // 取得表名称集合
        List<String> tabelNames = getTableNanes(conn, tableNames);

        // 遍历所有的表名。转换成大写或者小写
        for (String tableName : tabelNames) {

            // 大写表名称
            String className_d = StringUtil.upperFirst(PinYinUtil.getFirstSpell(StringUtil.newTableName(tableName)));

            // 小写表名称
            String className_x = StringUtil.lowerFirst(PinYinUtil.getFirstSpell(StringUtil.newTableName(tableName)));

            // 表索引
            List<TableIndex> tableIndexs = getTableIndexs(conn, tableName);// 表索引

            // 表主外键
            List<TableBind> tableBinds = getTableBinds(conn, tableName);// 表主外键

            // 存放大写的表名
            Set<String> upperTableNames = new HashSet<String>();
            upperTableNames.add(className_d);

            // TableBind: 表的主外键关系
            for (TableBind tableBind : tableBinds) {
                upperTableNames.add(tableBind.getTableName_d());
            }

            String stringCarrayNames1 = "";
            String stringCarrayNames2 = "";
            String stringCarrayNames3 = "";
            String stringCarrayNames4 = "";
            String stringCarrayNames5 = "";

            String stringCarrayNames6 = "";
            String stringCarrayNames7 = "";

            // 表字段
            List<TableCarray> tableCarrays = getTableCarrays(conn, tableName);// 表字段

            // TableCarray 表字段
            for (TableCarray tableCarray : tableCarrays) {

                // 设置这个字段的注释
                String remark = getRemark(conn, tableName, tableCarray.getCarrayName());
                tableCarray.setRemark(remark);

                if (!"".endsWith(stringCarrayNames1)) {
                    stringCarrayNames1 += ", ";
                }
                if (!"".endsWith(stringCarrayNames2)) {
                    stringCarrayNames2 += ", ";
                }
                if (!"".endsWith(stringCarrayNames3)) {
                    stringCarrayNames3 += ", ";
                }
                if (!"".endsWith(stringCarrayNames4)) {
                    stringCarrayNames4 += ", ";
                }
                if (!"".endsWith(stringCarrayNames5)) {
                    stringCarrayNames5 += ", ";
                }
                //
                stringCarrayNames1 += tableCarray.getCarrayName_x();// 首字母小写

                // 字段类型 CarrayType
                stringCarrayNames2 += tableCarray.getCarrayType() + " " + tableCarray.getCarrayName_x();

                // 原表名 CarrayName
                stringCarrayNames3 += tableCarray.getCarrayName();

                stringCarrayNames4 += String.format("#{%s}", tableCarray.getCarrayName_x());

                stringCarrayNames5 += String.format("%s=#{%s}", tableCarray.getCarrayName(),
                        tableCarray.getCarrayName_x());

                if (!tableCarray.getCarrayName().equals("ID") && !tableCarray.getCarrayName().equals("id")) {

                    if (!"".endsWith(stringCarrayNames6)) {
                        stringCarrayNames6 += ", ";
                    }

                    if (!"".endsWith(stringCarrayNames7)) {
                        stringCarrayNames7 += ", ";
                    }
                    stringCarrayNames6 += tableCarray.getCarrayName();

                    stringCarrayNames7 += String.format("#{%s}", tableCarray.getCarrayName_x());
                }
            }

            table = new Table(tableName, className_d, className_x, packageName, tableCarrays, tableIndexs, tableBinds,
                    upperTableNames, stringCarrayNames1, stringCarrayNames2, stringCarrayNames3, stringCarrayNames4,
                    stringCarrayNames5, stringCarrayNames6, stringCarrayNames7);
            String stringCarrayNames8 = "";
            if (table.getKey() == null) {
                ResultSet rs = conn.getMetaData().getPrimaryKeys("", "", tableName);
                while (rs.next()) {
                    String primaryKey = rs.getString("COLUMN_NAME");//获取主键名字

                    table.setKey_x(StringUtil.lowerFirst(StringUtil.newTableName(primaryKey)));
                    table.setKey_d(StringUtil.upperFirst(StringUtil.newTableName(primaryKey)));
                    stringCarrayNames8 += String.format("%s=#{%s}", primaryKey, table.getKey_x());
                }

            }
            if (!stringCarrayNames8.equals("")) {
                table.setStringCarrayNames8(stringCarrayNames8);
            }

            tables.add(table);
        }

        return tables;
    }

    /**
     * @param conn
     * @param tableName
     * @return
     * @throws Exception
     */
    public static final List<TableCarray> getTableCarrays(Connection conn, String tableName) throws Exception {

        List<TableCarray> tableCarrays = new ArrayList<TableCarray>();
        TableCarray tabelCarray = null;

        List<Map<String, Object>> carrays = getCarrays(conn, tableName);

        for (Map<String, Object> map : carrays) {

            String columnLabel = map.get("columnLabel").toString();

            String carrayName_d = StringUtil.upperFirst(PinYinUtil.getFirstSpell(StringUtil.newTableName(columnLabel)));// 首字母大写

            String carrayName_x = StringUtil.lowerFirst(PinYinUtil.getFirstSpell(StringUtil.newTableName(columnLabel)));// 首字母小写

            String carrayType = map.get("javaForType").toString();// 字段类型
            //转为它的包装类
            if (carrayType.equals("int")) {
                carrayType = "Integer";
            } else if (carrayType.equals("short")) {
                carrayType = "Short";
            } else if (carrayType.equals("java.util.Date")) {
                carrayType = "String";
            } else if (carrayType.equals("java.sql.Time")) {
                carrayType = "String";
            } else if (carrayType.equals("boolean")) {
                carrayType = "Integer";
            } else if (carrayType.equals("double")) {
                carrayType = "Double";
            } else if (carrayType.equals("long")) {
                carrayType = "Long";
            }

            tabelCarray = new TableCarray(columnLabel, carrayName_d, carrayName_x, carrayType, "");
            tableCarrays.add(tabelCarray);
        }
        return tableCarrays;
    }

    public static final List<TableIndex> getTableIndexs(Connection conn, String tableName) throws Exception {
        List<Map> indexs = getIndexs(conn, tableName, false);
        Map<String, String> carrayTypes = getTableCarrayTypes(conn, tableName);
        Map<String, List<Map>> _index = new HashMap<String, List<Map>>();
        for (Map map : indexs) {
            String indexName = map.get("INDEX_NAME").toString(); // 索引名称
            List<Map> list = _index.remove(indexName);
            if (list == null) {
                list = new ArrayList<Map>();
            }
            list.add(map);
            _index.put(indexName, list);
        }

        List<TableIndex> tableIndexs = new ArrayList<TableIndex>();
        TableIndex tabelIndex = null;
        Iterator it = _index.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry e = (Map.Entry) it.next();
            String indexName = e.getKey().toString();
            boolean unique = false;
            List<String> carrayNames = new ArrayList<String>();
            List<String> carrayNames_d = new ArrayList<String>();
            List<String> carrayNames_x = new ArrayList<String>();
            List<Map<String, String>> carrayNameMaps = new ArrayList<Map<String, String>>();
            String stringCarrayNames1 = "";
            String stringCarrayNames2 = "";
            String stringCarrayNames3 = "";
            String stringCarrayNames4 = "";
            String stringCarrayNames5 = "";
            List<Map> vals = (List<Map>) e.getValue();
            for (Map map : vals) {
                String carrayName = map.get("COLUMN_NAME").toString();
                unique = "false".equals(map.get("NON_UNIQUE").toString());
                String carrayName_d = StringUtil.upperFirst(PinYinUtil.getFirstSpell(StringUtil
                        .newTableName(carrayName)));
                String carrayName_x = StringUtil.lowerFirst(PinYinUtil.getFirstSpell(StringUtil
                        .newTableName(carrayName)));
                carrayNames.add(carrayName);
                carrayNames_d.add(carrayName_d);
                carrayNames_x.add(carrayName_x);
                Map<String, String> carrayNameMap = new HashMap<String, String>();
                carrayNameMap.put("carrayName", carrayName);
                carrayNameMap.put("carrayName_x", carrayName_x);
                carrayNameMaps.add(carrayNameMap);
                stringCarrayNames1 += carrayName_d;
                if (!"".equals(stringCarrayNames2)) {
                    stringCarrayNames2 += ", ";
                }
                if (!"".equals(stringCarrayNames3)) {
                    stringCarrayNames3 += ", ";
                }
                if (!"".equals(stringCarrayNames4)) {
                    stringCarrayNames4 += ", ";
                }
                if (!"".equals(stringCarrayNames5)) {
                    stringCarrayNames5 += ", ";
                }
                stringCarrayNames2 += carrayName;
                stringCarrayNames3 += carrayTypes.get(carrayName_d) + " " + carrayName_x;
                stringCarrayNames4 += carrayName_x;
                stringCarrayNames5 += String.format("%s=#{%s}", carrayName, carrayName_x);
            }
            tabelIndex = new TableIndex(indexName, carrayNames, carrayNames_d, carrayNames_x, carrayNameMaps,
                    stringCarrayNames1, stringCarrayNames2, stringCarrayNames3, stringCarrayNames4, stringCarrayNames5,
                    unique);
            tableIndexs.add(tabelIndex);
        }
        return tableIndexs;
    }

    public static final Map<String, String> getTableCarrayTypes(Connection conn, String tableName) throws Exception {
        Map<String, String> tableCarrayTypes = new HashMap<String, String>();
        List<Map<String, Object>> carrays = getCarrays(conn, tableName);
        for (Map<String, Object> map : carrays) {
            String columnLabel = map.get("columnLabel").toString();
            String carrayName_d = StringUtil.upperFirst(PinYinUtil.getFirstSpell(columnLabel));// 首字母大写
            String carrayType = map.get("javaForType").toString();// 字段类型
            tableCarrayTypes.put(carrayName_d, carrayType);
        }
        return tableCarrayTypes;
    }

    public static final List<TableBind> getTableBinds(Connection conn, String tableName) throws Exception {
        List<TableBind> tableBinds = new ArrayList<TableBind>();
        TableBind tableBind = null;
        Map map = getBinds(conn, tableName);// 表主外键

        String keyName = "";
        String keyType = "";
        String carrayName = "";
        String table_Name = "";
        String carrayName_d = "";
        String carrayName_x = "";
        String table_Name_d = "";
        String table_Name_x = "";

        List<Map> exportedKeys = (List<Map>) map.get("ExportedKeys");
        for (Map exportedKey : exportedKeys) {
            keyName = exportedKey.get("FK_NAME").toString();
            keyType = "exportedKey";
            carrayName = exportedKey.get("FKCOLUMN_NAME").toString();
            table_Name = exportedKey.get("FKTABLE_NAME").toString();
            carrayName_d = StringUtil.upperFirst(PinYinUtil.getFirstSpell(carrayName));
            carrayName_x = StringUtil.lowerFirst(PinYinUtil.getFirstSpell(carrayName));
            table_Name_d = StringUtil.upperFirst(PinYinUtil.getFirstSpell(table_Name));
            table_Name_x = StringUtil.lowerFirst(PinYinUtil.getFirstSpell(table_Name));
            tableBind = new TableBind(keyName, keyType, table_Name_d, table_Name_x, carrayName_d, carrayName_x);
            tableBinds.add(tableBind);
        }
        List<Map> importedKeys = (List<Map>) map.get("ImportedKeys");
        for (Map importedKey : importedKeys) {
            keyName = importedKey.get("FK_NAME").toString();
            keyType = "importedKey";
            carrayName = importedKey.get("FKCOLUMN_NAME").toString();
            table_Name = importedKey.get("PKTABLE_NAME").toString();
            carrayName_d = StringUtil.upperFirst(PinYinUtil.getFirstSpell(carrayName));
            carrayName_x = StringUtil.lowerFirst(PinYinUtil.getFirstSpell(carrayName));
            table_Name_d = StringUtil.upperFirst(PinYinUtil.getFirstSpell(table_Name));
            table_Name_x = StringUtil.lowerFirst(PinYinUtil.getFirstSpell(table_Name));
            tableBind = new TableBind(keyName, keyType, table_Name_d, table_Name_x, carrayName_d, carrayName_x);
            tableBinds.add(tableBind);
        }
        return tableBinds;
    }

}
