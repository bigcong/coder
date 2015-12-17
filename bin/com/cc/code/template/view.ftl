package dao

import javax.inject.Inject

import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile
import slick.driver.MySQLDriver.api._

import model.Model.${className_d}Model
import model.Type.${className_d}Type
/**
  * Created by bigcong
  */
class ${className_d}Dao @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends HasDatabaseConfigProvider[JdbcProfile] {
  private lazy val ${className_x}Table = TableQuery[${className_d}Table] //活动表
  /**
    * 统筹查询
    * @param ${className_x}Model
    * @return
    */
  def list(${className_x}Model: ${className_d}Model) = {
    var filter = ${className_x}Table.filter(_.${key_x} > 0);
   <#list tableCarrays as tableCarray>
                 <#if tableCarray.carrayType=="String">
    if (!${className_x}Model.${tableCarray.carrayName_x}.equals("")) {
                 <#else>
    if (${className_x}Model.${tableCarray.carrayName_x}!=0) {
                  </#if>
    filter = filter.filter(_.${tableCarray.carrayName_x}=== ${className_x}Model.${tableCarray.carrayName_x})
    }
    </#list>
    val action = filter.map(i => (<#list tableCarrays as tableCarray>i.${tableCarray.carrayName_x},</#list>)).result
    db.run(action)
  }

  /**
    * 插入或者更新
    * @param ${className_x}Type
    * @return
    */
  def insertOrUpdate( ${className_x}Type:  ${className_d}Type) = db.run(
    ${className_x}Table.insertOrUpdate(${className_x}Type)
  )

  /**
    * 根据id 获取一条数据
    * @param ${key_x}
    * @return
    */
  def get(${key_x}: Int) = db.run(
    ${className_x}Table.filter(_.${key_x} === ${key_x}).map (i =>(<#list tableCarrays as tableCarray>i.${tableCarray.carrayName_x},</#list>) ).result
  )


}

private class ${className_d}Table(tag: Tag) extends Table[${className_d}Type](tag, "${className}") {
<#list tableCarrays as tableCarray>
                  <#if tableCarray.carrayName_x== key_x>
  def ${tableCarray.carrayName_x}=column[${tableCarray.carrayType}]("${tableCarray.carrayName}",O.PrimaryKey, O.AutoInc);// ${tableCarray.remark}
                 <#else>
  def ${tableCarray.carrayName_x}=column[${tableCarray.carrayType}]("${tableCarray.carrayName}");// ${tableCarray.remark}
                  </#if>
 </#list>
  def * = (<#list tableCarrays as tableCarray>${tableCarray.carrayName_x},</#list>)
}
type ${className_d}Type = (<#list tableCarrays as tableCarray>${tableCarray.carrayType},</#list>)// ${className}
case class ${className_d}Model(<#list tableCarrays as tableCarray><#if tableCarray.carrayType=="String">var ${tableCarray.carrayName_x}: ${tableCarray.carrayType} = "", <#else>var ${tableCarray.carrayName_x}: ${tableCarray.carrayType} = 0,</#if></#list>)// ${className}

