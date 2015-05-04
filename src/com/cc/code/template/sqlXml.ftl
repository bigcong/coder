<#macro mapperEl value="">${r"#{"}${value}}</#macro>
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" 
	"http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="${packageName}.mapper.${className_d}Mapper">
	
	<resultMap id="${className_d}Map" type="${className_d}">
		  <id property="id" column="ID" />
		<#list tableCarrays as tableCarray>
		  <#if tableCarray.carrayName !='id' && tableCarray.carrayName !='ID'>
		   <result property="${tableCarray.carrayName_x}" column="${tableCarray.carrayName}" />
		  </#if>
		</#list>
	</resultMap>
	
	
	<sql id="${className_d}Columns">
		  ${stringCarrayNames3}
    </sql>
	
    <!-- 很据条件查询店铺的全部 -->
	<select id="listPage${className_d}" resultMap="${className_d}Map" 
	parameterType="${className_d}">
		select
		<include refid="${className_d}Columns" />
		from ${className} where 1=1 
		 <#list tableCarrays as tableCarray>
			 <if test="${tableCarray.carrayName_x} !=null and ${tableCarray.carrayName_x}!=''">
				and ${tableCarray.carrayName}=<@mapperEl tableCarray.carrayName_x/>
			</if>
		 </#list>
	</select>
	
	<!-- 查询${className}的数量-->
	<select id="get${className_d}Count" resultType="Integer">
		select count(*)
		from ${className}
	</select>
	
	<!-- 插入 -->
	<insert id="insert${className_d}" parameterType="${className_d}" 
	  useGeneratedKeys="true" keyProperty="id">
		INSERT INTO ${className} (
			${stringCarrayNames6}
		) VALUES (
			${stringCarrayNames7}
		)
	</insert>
	
	<!-- 根据主键查询(唯一)-->
	<select id="get${className_d}ById" parameterType="Integer" resultType="${className_d}">
		select 
		<include refid="${className_d}Columns" /> 
		from ${className} where id=<@mapperEl value="id" />
	</select>
	
	<!-- 根据条件查询 ${className}  -->
	<select id="get${className_d}" resultMap="${className_d}Map" parameterType="${className_d}">
		select
		<include refid="${className_d}Columns" />
		from ${className} where 1=1 
		 <#list tableCarrays as tableCarray>
			 <if test="${tableCarray.carrayName_x} !=null and ${tableCarray.carrayName_x}!=''">
				and ${tableCarray.carrayName}=<@mapperEl tableCarray.carrayName_x/>
			</if>
		 </#list>
	</select>
	
	<!--更新  -->
	<update id="update${className_d}" parameterType="${className_d}">
		<#list tableIndexs as tableIndex>
		UPDATE ${className} 
		SET
		${stringCarrayNames5}
		WHERE
		${tableIndex.stringCarrayNames5}
	    </#list>
	</update>
	
	
	<!--根据条件删除  -->
	<delete id="delete${className_d}" parameterType="${className_d}">
		delete from ${className} 
		where 1=1
		 <#list tableCarrays as tableCarray>
			 <if test="${tableCarray.carrayName_x} !=null and ${tableCarray.carrayName_x}!=''">
				and ${tableCarray.carrayName} =  <@mapperEl tableCarray.carrayName_x/>
			</if>
		 </#list>
	</delete>
	
	<!--根据主键删除  -->
	<delete id="delete${className_d}ByIds">
		delete from ${className} where id in
		<foreach item="item" index="index" collection="array" open="(" separator="," close=")">  
          #{item}  
        </foreach>  
	</delete>
</mapper>