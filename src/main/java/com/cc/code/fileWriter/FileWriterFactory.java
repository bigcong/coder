package com.cc.code.fileWriter;

import com.cc.code.table.Table;
import com.cc.code.util.DirMaker;
import com.cc.code.util.FileEnum;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;

/**
 * @author jlon
 */
public class FileWriterFactory {

    private static Configuration cfg;
    /**
     *
     */
    public static final int POJO = 1;
    /**
     *
     */
    public static final int MAPPER = 2;
    /**
     *
     */
    public static final int SQLXML = 3;
    /**
     *
     */
    public static final int SERVICE = 4;
    /**
     *
     */
    public static final int SERVICE_IMPL = 5;
    /**
     *
     */
    public static final int LISTJSP = 6;

    public static final int INFOJSP = 7;

    public static final int VIEWJSP = 8;

    public static final int CONTROLLER = 9;
    public static final int CONDITION = 10;

    public static final int DTO = 11;

    public static final int PARAM = 12;

    /**
     * @param url
     * @return
     */
    public static Configuration getConfiguration(String url) {
        if (cfg == null) {
            cfg = new Configuration();

            ClassLoader classLoader = FileWriterFactory.class.getClassLoader();

            File file = new File(classLoader.getResource("template")
                .getFile());
            try {
                cfg.setDirectoryForTemplateLoading(file);
                cfg.setObjectWrapper(new DefaultObjectWrapper());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return cfg;
    }

    /**
     * @param cfg
     * @param table
     * @param fileEnum
     * @param resultURL
     */
    public static void dataSourceOut(Configuration cfg, Table table, FileEnum fileEnum, String resultURL) {
        String fileName = null;
        Template temp = null;
        try {
            temp = cfg.getTemplate(fileEnum.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Writer out = null;

        try {
            String packageName = "";
            fileName = fileEnum.getFileName();
            packageName = table.getPackageName() + "." + fileEnum.getPackageName();

            packageName = packageName.replace(".", "/");

            String url = resultURL + "/" + packageName + "/" + table.getClassName_d() + fileName;
            System.out.println(url);

            File file = new File(url);

            DirMaker.createFile(file);

            out = new FileWriter(file);

            temp.process(table, out);

            temp.process(table, new OutputStreamWriter(System.out));

            out.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param cfg
     * @param templateName
     * @param root
     * @param fileName
     */
    public static void dataSourceOut(Configuration cfg, String templateName, Object root, String fileName) {

        Template temp = null;
        try {
            temp = cfg.getTemplate(templateName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Writer out = null;
        try {
            if (fileName != null && !"".equals(fileName)) {
                String packageName = "";
                packageName = packageName.replace(".", "/");
                String url = "src/" + packageName + "/" + fileName;
                File file = new File(url);
                out = new FileWriter(file);
                temp.process(root, out);
            }
            temp.process(root, new OutputStreamWriter(System.out));
            out.flush();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
