<#macro mapperEl pre="" end="">
  ${r"${"}${pre}${end}}
</#macro>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HLRMS-好邻后台管理系统</title>
<link type="text/css" rel="stylesheet" href="../css/main.css"/>
<style type="text/css">
body{width:100%;height:100%;background-color: #FFFFFF;text-align: center;}
.input_txt{width:200px;height:20px;line-height:20px;}
.info{height:40px;line-height:40px;}
.info th{text-align: right;width:65px;color: #4f4f4f;padding-right:5px;font-size: 13px;}
.info td{text-align:left;}
</style>
</head>
<body>
	<table border="0" cellpadding="0" cellspacing="0">
	     <#list tableCarrays as tableCarray>
		   <tr class="info">
				<th>${tableCarray.remark}:</th>
				<td>
				 <#if tableCarray.carrayType=="java.util.Date">
			        <input type="text" 
					value="<fmt:formatDate value="<@mapperEl pre="${className}." end="${tableCarray.carrayName_x}"/>" pattern="yyyy-MM-dd"/>"
					 style="width: 200px;" disabled="disabled"/>
			         <#else>
			         <input type="text" name="${tableCarray.carrayName_x}" id="${tableCarray.carrayName_x}" class="input_txt"
					value="<@mapperEl pre="${className}." end="${tableCarray.carrayName_x}"/>" disabled="disabled"/>
			      </#if>
				</td>
			</tr>
		 </#list>
		</table>
</body>
</html>