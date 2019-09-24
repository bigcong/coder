<#macro mapperEl value="">${r"#{"}${value}}</#macro>
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="${packageName}.dao.${className_d}DAO">
    <!-- 结果对应-->
    <resultMap id="${className_d}Map" type="${packageName}.entity.${className_d}Entity">
    <#list tableCarrays as tableCarray>
        <result property="${tableCarray.carrayName_x}" column="${tableCarray.carrayName}" />
        <!-- ${tableCarray.remark}-->
    </#list>
    </resultMap>


    <sql id="${className_d}Columns">
    ${stringCarrayNames3}
    </sql>

    <!-- 条件查询-->
    <select id="queryByCondition" resultMap="${className_d}Map"
            parameterType="${packageName}.entity.${className_d}Entity">
        select
        <include refid="${className_d}Columns"/>
        from ${className}
        <where>
        <#list tableCarraySet as tableCarray>
            <#if tableCarray.carrayType=="String">
            <if test="${tableCarray.carrayName_x} !=null and ${tableCarray.carrayName_x}!=''">
            <#else>
            <if test="${tableCarray.carrayName_x} !=null">
            </#if>
            and ${tableCarray.carrayName}=<@mapperEl tableCarray.carrayName_x/>
        </if>
        </#list>
        </where>
        order by  ${key} desc
    </select>




    <!-- 有条件的插入 -->
    <insert id="insertSelective" parameterType="${packageName}.entity.${className_d}Entity" useGeneratedKeys="true"
            keyProperty="${key_x}">
        insert into ${className}
        <trim prefix="(" suffix=")" suffixOverrides=",">
        <#list tableCarrays as tableCarray>
            <#if tableCarray.carrayType=="String">
            <if test="${tableCarray.carrayName_x} !=null and ${tableCarray.carrayName_x}!=''">
            <#else>
            <if test="${tableCarray.carrayName_x} !=null">
            </#if>
        ${tableCarray.carrayName},
        </if>
        </#list>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides=",">
        <#list tableCarrays as tableCarray>
            <#if tableCarray.carrayType=="String">
            <if test="${tableCarray.carrayName_x} !=null and ${tableCarray.carrayName_x}!=''">
            <#else>
            <if test="${tableCarray.carrayName_x} !=null">
            </#if>
            <@mapperEl tableCarray.carrayName_x/>,
        </if>
        </#list>
        </trim>
    </insert>

    <!-- 根据主键查询(唯一)-->
    <select id="getByPrimaryKey" parameterType="java.lang.${keyCarrayType}" resultMap="${className_d}Map">
        select
        <include refid="${className_d}Columns"/>
        from ${className} where
    ${stringCarrayNames8}
    </select>

    <!--根据条件update  -->
    <update id="updateByPrimaryKeySelective" parameterType="${packageName}.entity.${className_d}Entity">
        UPDATE ${className}
        <set>
        <#list tableCarrays as tableCarray>
            <#if tableCarray.carrayType=="String">
            <if test="${tableCarray.carrayName_x} !=null and ${tableCarray.carrayName_x}!=''">
            <#else>
            <if test="${tableCarray.carrayName_x} !=null">
            </#if>
        ${tableCarray.carrayName} =  <@mapperEl tableCarray.carrayName_x/>,
        </if>
        </#list>
        </set>
        WHERE
    ${stringCarrayNames8}
    </update>


</mapper>