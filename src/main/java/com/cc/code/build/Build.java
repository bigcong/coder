package com.cc.code.build;

import com.cc.code.connection.DataSourceConfig;
import com.cc.code.generator.Generator;
import com.cc.code.util.FileEnum;

import java.util.Arrays;
import java.util.List;

/**
 * @author jlon
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
        DataSourceConfig cfg =
            new DataSourceConfig("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/atour_user", "root", "",
                DataSourceConfig.MYSQL);
        /**
         * 第一个参数:选择你要生成的表名,此参数可选。不写默认生成全部
         * 第二个参数:生成的文件的作存放的包名
         * 第三个参数:数据源配置
         * 第四个参数:生成目标文件的位置
         */
        Generator generator =
            new Generator(new String[] {"meb_coupons_template"}, "com.atour.user.persistent.atouruser", cfg,
                "/Users/cong/Desktop/atour/user-center/user-center-web/src/main/java");

        List<FileEnum> fileEnums =
            Arrays.asList(FileEnum.DTO, FileEnum.POJO, FileEnum.MAPPER, FileEnum.SQLXML, FileEnum.PARAM,
                FileEnum.SERVICE, FileEnum.WRAPPER, FileEnum.CONDITION);
        generator.generate("雷欧", fileEnums);

    }
}
