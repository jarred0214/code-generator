<#assign className = table.tableClassName>
<#assign classNameLower = table.tableClassNameFirstLower>
<#assign primaryKey = table.primaryKey>
<#assign primaryKeyParameters = table.primaryKeyParameters>
<#assign primaryKeyParameterValues = table.primaryKeyParameterValues>
package ${basePackage}.quickapi.service;

import org.xi.common.model.Result;
import ${basePackage}.entity.${className}Entity;
import ${basePackage}.parameter.${className}SelectParameter;
import ${basePackage}.vo.${className}Vo;
import ${basePackage}.quickapi.service.hystric.${className}ServiceHystric;

import com.github.pagehelper.PageInfo;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

<#include "/include/java_copyright.ftl">
@FeignClient(value = "${serviceProvider}", fallback = ${className}ServiceHystric.class)
@RequestMapping("/${className}")
public interface ${className}Service {

    /**
     * 添加
     *
     * @param ${classNameLower}
     * @param sessionId
     * @return
     <#include "/include/author_info1.ftl">
     */
    @RequestMapping("/add${className}")
    Result<${className}Entity> add${className}(${className}Entity ${classNameLower}, String sessionId);

    /**
     * 添加列表
     *
     * @param ${classNameLower}List
     * @param sessionId
     * @return
    <#include "/include/author_info1.ftl">
     */
    @RequestMapping("/add${className}List")
    Result<${className}Entity> add${className}List(List<${className}Entity> ${classNameLower}List, String sessionId);
    <#if table.hasPrimaryKey>

    /**
     * 根据主键物理删除
     *
     <#list primaryKey as column>
     * @param ${column.columnFieldNameFirstLower}
     </#list>
     * @param sessionId
     * @return
     <#include "/include/author_info1.ftl">
     */
    @RequestMapping("/delete${className}ByPk")
    Result<${className}Entity> delete${className}ByPk(${primaryKeyParameters}, String sessionId);
    <#if table.validStatusColumn??>

    /**
     * 根据主键冻结
     *
     <#list primaryKey as column>
     * @param ${column.columnFieldNameFirstLower}
     </#list>
     * @param sessionId
     * @return
     <#include "/include/author_info1.ftl">
     */
    @RequestMapping("/disable${className}ByPk")
    Result<${className}Entity> disable${className}ByPk(${primaryKeyParameters}, String sessionId);

    /**
     * 根据主键激活
     *
     <#list primaryKey as column>
     * @param ${column.columnFieldNameFirstLower}
     </#list>
     * @param sessionId
     * @return
     <#include "/include/author_info1.ftl">
     */
    @RequestMapping("/enable${className}ByPk")
    Result<${className}Entity> enable${className}ByPk(${primaryKeyParameters}, String sessionId);
    </#if>

    /**
     * 根据主键更新
     *
     * @param ${classNameLower}
     * @param sessionId
     * @return
     <#include "/include/author_info1.ftl">
     */
    @RequestMapping("/update${className}ByPk")
    Result<${className}Entity> update${className}ByPk(${className}Entity ${classNameLower}, String sessionId);

    /**
     * 根据主键获取
     *
     <#list primaryKey as column>
     * @param ${column.columnFieldNameFirstLower}
     </#list>
     * @param sessionId
     * @return
     <#include "/include/author_info1.ftl">
     */
    @RequestMapping("/get${className}ByPk")
    Result<${className}Vo> get${className}ByPk(${primaryKeyParameters}, String sessionId);
    </#if>

    /**
     * 分页查询
     *
     * @param parameter
     * @param page
     * @param sessionId
     * @return
     <#include "/include/author_info1.ftl">
     */
    @RequestMapping("/find${className}PageList")
    Result<PageInfo<${className}Vo>> find${className}PageList(${className}SelectParameter parameter, PageInfo page, String sessionId);
}
