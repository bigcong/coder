package com.cc.code.build;

import com.cc.code.connection.DataSourceConfig;
import com.cc.code.generator.Generator;

/**
 * 
 * @author jlon
 *
 */
public class Build {

    public static void main(String[] args) {
        try {
            generator();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generator() throws Exception {
        // 得到数据源
        DataSourceConfig cfg = new DataSourceConfig("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/haolinapp",
                "root", "root", DataSourceConfig.MYSQL);

        /**
         * 第一个参数:选择你要生成的表名,此参数可选。不写默认生成全部 第二个参数:生成的文件的作存放的包名，默认是D盘 第三个参数:模板文件的位置
         * 
         * 第三个参数:数据源配置
         */
        Generator generator = new Generator(new String[] { "yl_residential_expand" }, "com.haolin",
                "com/cc/code/template/", cfg);
        generator.generate(true, true, true, true, true, true, true, true, true);

    }
}
