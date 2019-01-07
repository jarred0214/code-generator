<#include "/include/table/properties.ftl">
package ${basePackage}.admin.service.hystric;

import ${baseCommonPackage}.constant.OperationConstants;
import ${baseCommonPackage}.model.ResultVo;
import ${basePackage}.entity.${className}Entity;
import ${basePackage}.condition.extension.${className}ConditionExtension;
import ${basePackage}.entity.extension.${className}EntityExtension;
import ${basePackage}.admin.service.${className}Service;

import com.github.pagehelper.PageInfo;

import org.springframework.stereotype.Service;

import java.util.List;

<#include "/include/java_copyright.ftl">
@Service
public class ${className}ServiceHystric implements ${className}Service {

    /**
     * 添加
     *
     * @param entity
     * @param sessionId
     * @return
     <#include "/include/author_info1.ftl">
     */
    @Override
    public ResultVo<${className}Entity> add(${className}Entity entity, String sessionId) {
        return new ResultVo<>(OperationConstants.SERVICE_NOT_AVAILABLE);
    }

    /**
     * 添加列表
     *
     * @param list
     * @param sessionId
     * @return
    <#include "/include/author_info1.ftl">
     */
    @Override
    public ResultVo<Integer> addList(List<${className}Entity> list, String sessionId) {
        return new ResultVo<>(OperationConstants.SERVICE_NOT_AVAILABLE);
    }
    <#if table.hasPrimaryKey>

    /**
     * 根据主键物理删除
     *
     <#list pks as column>
     * @param ${fieldName}
     </#list>
     * @param sessionId
     * @return
     <#include "/include/author_info1.ftl">
     */
    @Override
    public ResultVo<Integer> delete(<#list pks as column><#if (column_index > 0)>, </#if>${column.targetDataType} ${fieldName}</#list>, String sessionId) {
        return new ResultVo<>(OperationConstants.SERVICE_NOT_AVAILABLE);
    }
    <#if (table.uniquePrimaryKey??)>

    /**
     * 根据主键列表物理删除
     *
     * @param ${table.uniquePrimaryKey.targetColumnName?uncap_first}List
     * @param sessionId
     * @return
     <#include "/include/author_info1.ftl">
     */
    @Override
    public ResultVo<Integer> delete(List<${table.uniquePrimaryKey.targetDataType}> ${table.uniquePrimaryKey.targetColumnName?uncap_first}List, String sessionId) {
        return new ResultVo<>(OperationConstants.SERVICE_NOT_AVAILABLE);
    }
    </#if>
    <#if table.validStatusColumn??>

    /**
     * 根据主键禁用
     *
     <#list pks as column>
     * @param ${fieldName}
     </#list>
     * @param sessionId
     * @return
     <#include "/include/author_info1.ftl">
     */
    @Override
    public ResultVo<Integer> disable(<#list pks as column><#if (column_index > 0)>, </#if>${column.targetDataType} ${fieldName}</#list>, String sessionId) {
        return new ResultVo<>(OperationConstants.SERVICE_NOT_AVAILABLE);
    }

    /**
     * 根据主键启用
     *
     <#list pks as column>
     * @param ${fieldName}
     </#list>
     * @param sessionId
     * @return
     <#include "/include/author_info1.ftl">
     */
    @Override
    public ResultVo<Integer> enable(<#list pks as column><#if (column_index > 0)>, </#if>${column.targetDataType} ${fieldName}</#list>, String sessionId) {
        return new ResultVo<>(OperationConstants.SERVICE_NOT_AVAILABLE);
    }
    <#if (table.uniquePrimaryKey??)>

    /**
     * 根据主键列表禁用
     *
     * @param ${table.uniquePrimaryKey.targetColumnName?uncap_first}List
     * @param sessionId
     * @return
     <#include "/include/author_info1.ftl">
     */
    @Override
    public ResultVo<Integer> disable(List<${table.uniquePrimaryKey.targetDataType}> ${table.uniquePrimaryKey.targetColumnName?uncap_first}List, String sessionId) {
        return new ResultVo<>(OperationConstants.SERVICE_NOT_AVAILABLE);
    }

    /**
     * 根据主键列表启用
     *
     * @param ${table.uniquePrimaryKey.targetColumnName?uncap_first}List
     * @param sessionId
     * @return
     <#include "/include/author_info1.ftl">
     */
    @Override
    public ResultVo<Integer> enable(List<${table.uniquePrimaryKey.targetDataType}> ${table.uniquePrimaryKey.targetColumnName?uncap_first}List, String sessionId) {
        return new ResultVo<>(OperationConstants.SERVICE_NOT_AVAILABLE);
    }
    </#if>
    </#if>

    /**
     * 根据主键更新
     *
     * @param entity
     * @param sessionId
     * @return
     <#include "/include/author_info1.ftl">
     */
    @Override
    public ResultVo<Integer> update(${className}Entity entity<#if !table.hasAutoIncrementUniquePrimaryKey><#list pks as column>, ${column.targetDataType} old${propertyName}</#list></#if>, String sessionId) {
        return new ResultVo<>(OperationConstants.SERVICE_NOT_AVAILABLE);
    }

    /**
     * 根据主键获取
     *
     <#list pks as column>
     * @param ${fieldName}
     </#list>
     * @param sessionId
     * @return
     <#include "/include/author_info1.ftl">
     */
    @Override
    public ResultVo<${className}EntityExtension> get(<#list pks as column><#if (column_index > 0)>, </#if>${column.targetDataType} ${fieldName}</#list>, String sessionId) {
        return new ResultVo<>(OperationConstants.SERVICE_NOT_AVAILABLE);
    }
    </#if>

    /**
     * 分页查询
     *
     * @param parameter
     * @param sessionId
     * @return
     <#include "/include/author_info1.ftl">
     */
    @Override
    public ResultVo<PageInfo<${className}EntityExtension>> getPageInfo(${className}ConditionExtension parameter, String sessionId) {
        return new ResultVo<>(OperationConstants.SERVICE_NOT_AVAILABLE);
    }

}
