<#macro mapperEl value="">${r"#{"}${value}}</#macro>
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" 
	"http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="${packageName}.mapper.${className_d}Mapper">
	     <!-- 结果对应-->
	<resultMap id="${className_d}Map" type="${packageName}.entity.${className_d}">
		<#list tableCarrays as tableCarray>
		   <result property="${tableCarray.carrayName_x}" column="${tableCarray.carrayName}" /> <!-- ${tableCarray.remark}-->
		</#list>
	</resultMap>
	
	
	<sql id="${className_d}Columns">
		  ${stringCarrayNames3}
    </sql>
	
    <!-- 分页条件查询-->
	<select id="listPage${className_d}" resultMap="${className_d}Map" 
	parameterType="${packageName}.entity.${className_d}">
		select
		<include refid="${className_d}Columns" />
		from ${className} 
		 <where>
		  <#list tableCarrays as tableCarray>
		      <#if tableCarray.carrayType=="String"> 
                  <if test="${tableCarray.carrayName_x} !=null and ${tableCarray.carrayName_x}!=''">
              <#else> 
                  <if test="${tableCarray.carrayName_x} !=null">
              </#if>   
			           and ${tableCarray.carrayName}=<@mapperEl tableCarray.carrayName_x/>
			      </if>
		 </#list>
		</where>
	</select>
	
	<!-- 查询${className}的数量-->
	<select id="get${className_d}Count" resultType="Integer">
		select count(*)
		from ${className}
	</select>
	
	<!-- 插入 -->
	<insert id="insert" parameterType="${className_d}" 
	  useGeneratedKeys="true" keyProperty="id">
		INSERT INTO ${className} (
			${stringCarrayNames6}
		) VALUES (
			${stringCarrayNames7}
		)
	</insert>
	<!-- 有条件的插入 -->
	<insert id="insertSelective" parameterType="${packageName}.entity.${className_d}" useGeneratedKeys="true" keyProperty="id">
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
	<select id="get${className_d}ById" parameterType="Integer" resultMap="${className_d}Map">
		select 
		<include refid="${className_d}Columns" /> 
		from ${className} where 
		   ${key_x}=${key_x}
	</select>
	
	<!-- 根据条件查询 ${className}  -->
	<select id="list${className_d}" resultMap="${className_d}Map" parameterType="${packageName}.entity.${className_d}">
		select
		<include refid="${className_d}Columns" />
		from ${className} 
		<where>
		 <#list tableCarrays as tableCarray>
			    <#if tableCarray.carrayType=="String"> 
                  <if test="${tableCarray.carrayName_x} !=null and ${tableCarray.carrayName_x}!=''">
              <#else> 
                  <if test="${tableCarray.carrayName_x} !=null">
              </#if>   
				and ${tableCarray.carrayName}=<@mapperEl tableCarray.carrayName_x/>
			</if>
		 </#list>
		</where>
	</select>
	
	<!--更新  -->
	<update id="update${className_d}" parameterType="${packageName}.entity.${className_d}">
		UPDATE ${className} 
		SET
		${stringCarrayNames5}
		WHERE
		<#list tableIndexs as tableIndex>
		${tableIndex.stringCarrayNames5}
	    </#list>
	</update>
		<!--根据条件update  -->
    <update id="updateByPrimaryKeySelective" parameterType="${packageName}.entity.${className_d}" >
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
		<#list tableIndexs as tableIndex>
		   ${tableIndex.stringCarrayNames5}
	    </#list>
	</update>
	
	
	<!--根据条件删除  -->
	<delete id="delete${className_d}" parameterType="${packageName}.entity.${className_d}">
		delete from ${className} 
	<where>
		 <#list tableCarrays as tableCarray>
			    <#if tableCarray.carrayType=="String"> 
                  <if test="${tableCarray.carrayName_x} !=null and ${tableCarray.carrayName_x}!=''">
              <#else> 
                  <if test="${tableCarray.carrayName_x} !=null">
              </#if>   
				and ${tableCarray.carrayName} =  <@mapperEl tableCarray.carrayName_x/>
			</if>
		 </#list>
		</where> 
		 
	</delete>
</mapper>