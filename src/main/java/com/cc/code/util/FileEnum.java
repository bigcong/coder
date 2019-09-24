package com.cc.code.util;

/**
 * @author 雷欧
 * @date 2019-08-27 16:00
 * @description
 */
public enum FileEnum {

    POJO(1, "pojo.ftl", "Entity.java", "entity"),
    MAPPER(2, "mapper.ftl", "DAO.java", "dao"),

    SQLXML(3, "sqlXml.ftl", "Mapper.xml", "mybatis"),

    SERVICE(4, "service.ftl", "Service.java", "service"),

    DTO(5, "dto.ftl", "DTO.java", "dto"),
    PARAM(6, "param.ftl", "Param.java", "param"),
    CONDITION(8, "condition.ftl", "Condition.java", "condition"),

    WRAPPER(7, "wrapper.ftl", "Wrapper.java", "wrapper");

    private int value;
    private String name;

    private String fileName;
    private String packageName;

    private FileEnum(int value, String name, String fileName, String packageName) {
        this.value = value;
        this.name = name;
        this.fileName = fileName;
        this.packageName = packageName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static FileEnum getInstance(int value) {

        for (FileEnum obj : FileEnum.values()) {
            if (obj.getValue() == value) {
                return obj;
            }
        }
        return null;
    }}
