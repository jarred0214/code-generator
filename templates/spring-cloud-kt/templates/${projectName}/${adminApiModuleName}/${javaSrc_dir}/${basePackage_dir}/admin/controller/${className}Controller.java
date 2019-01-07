<#include "/include/table/properties.ftl">
package ${basePackage}.admin.controller;

import ${baseCommonPackage}.model.ResponseVo;
import ${baseCommonPackage}.model.ResultVo;
import ${baseCommonPackage}.utils.LogUtils;
import ${baseCommonPackage}.validation.*;
import ${basePackage}.admin.service.${className}Service;
import ${basePackage}.admin.vm.addoredit.${className}AddOrEditVm;
import ${basePackage}.admin.vm.detail.${className}DetailVm;
import ${basePackage}.admin.vm.search.${className}SearchVm;
import ${basePackage}.entity.${className}Entity;
import ${basePackage}.condition.extension.${className}ConditionExtension;
import ${basePackage}.entity.extension.${className}EntityExtension;

import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

<#include "/include/java_copyright.ftl">
@RestController
@RequestMapping("/${table.tableClassName?lower_case}")
@Validated
public class ${className}Controller {

    private static LogUtils logger = LogUtils.build(${className}Controller.class);

    @Value("${r'${spring.application.name}'}")
    private String applicationName;

    @Autowired
    private ${className}Service ${classNameLower}Service;

    /**
     * 添加
     *
     * @param vm
     * @return
     <#include "/include/author_info1.ftl">
     */
    @RequestMapping(value = { "/add" }, method = RequestMethod.POST)
    public ResponseVo<${className}AddOrEditVm> add(@Validated({DataAdd.class}) ${className}AddOrEditVm vm) {

        ResponseVo<${className}AddOrEditVm> responseVo;
        ${className}Entity entity = vm.get${className}Entity();
        ResultVo<${className}Entity> apiResult = ${classNameLower}Service.add(entity, getSessionId());
        if (apiResult.isSuccess()) {
            vm.set${className}Entity(apiResult.getResult());
            responseVo = new ResponseVo<>(vm);
        } else {
            responseVo = new ResponseVo<>(apiResult.getMessage());
        }

        return responseVo;
    }

    /**
     * 编辑
     *
     * @param vm
     * @return
     <#include "/include/author_info1.ftl">
     */
    @RequestMapping(value = { "/edit" }, method = RequestMethod.POST)
    public ResponseVo<Integer> edit(@Validated({DataEdit.class}) ${className}AddOrEditVm vm<#if !table.hasAutoIncrementUniquePrimaryKey><#list pks as column>, ${column.targetDataType} old${propertyName}</#list></#if>) {

        ResponseVo<Integer> responseVo;
        ResultVo<Integer> apiResult = ${classNameLower}Service.update(vm.get${className}Entity()<#if !table.hasAutoIncrementUniquePrimaryKey><#list pks as column>, old${propertyName}</#list></#if>, getSessionId());
        if (apiResult.isSuccess()) {
            responseVo = new ResponseVo<>(apiResult.getResult());
        } else {
            responseVo = new ResponseVo<>(apiResult.getMessage());
        }

        return responseVo;
    }

    /**
     * 添加列表
     *
     * @param list
     * @return
    <#include "/include/author_info1.ftl">
     */
    @RequestMapping(value = { "/addList" }, method = RequestMethod.POST)
    public ResponseVo<Integer> addList(@RequestBody List<${className}AddOrEditVm> list) {

        ResponseVo<Integer> responseVo;
        List<${className}Entity> entityList = list.stream().map(o->o.get${className}Entity()).collect(Collectors.toList());
        ResultVo<Integer> apiResult = ${classNameLower}Service.addList(entityList, getSessionId());
        if (apiResult.isSuccess()) {
            responseVo = new ResponseVo<>(apiResult.getResult());
        } else {
            responseVo = new ResponseVo<>(apiResult.getMessage());
        }

        return responseVo;
    }
    <#if table.hasPrimaryKey>

    /**
     * 根据主键物理删除
     *
     <#list pks as column>
     * @param ${fieldName}
     </#list>
     * @return
     <#include "/include/author_info1.ftl">
     */
     @RequestMapping(value = { "/delete" }, method = RequestMethod.GET)
     public ResponseVo<Integer> delete(<#include "/include/table/validate_primary_parameters.ftl">) {

         ResponseVo<Integer> responseVo;
         ResultVo<Integer> apiResult = ${classNameLower}Service.delete(<#include "/include/table/primary_values.ftl">, getSessionId());
         if (apiResult.isSuccess()) {
             responseVo = new ResponseVo<>(apiResult.getResult());
         } else {
             responseVo = new ResponseVo<>(apiResult.getMessage());
         }

         return responseVo;
    }
    <#if (table.uniquePrimaryKey??)>

    /**
     * 根据主键列表物理删除
     *
     * @param ${table.uniquePrimaryKey.targetColumnName?uncap_first}List
     * @return
     <#include "/include/author_info1.ftl">
     */
     @RequestMapping(value = { "/deleteList" }, method = RequestMethod.POST)
     public ResponseVo<Integer> deleteList(@RequestBody List<${table.uniquePrimaryKey.targetDataType}> ${table.uniquePrimaryKey.targetColumnName?uncap_first}List) {

        ResponseVo<Integer> responseVo;
        ResultVo<Integer> apiResult = ${classNameLower}Service.delete(${table.uniquePrimaryKey.targetColumnName?uncap_first}List, getSessionId());
        if (apiResult.isSuccess()) {
            responseVo = new ResponseVo<>(apiResult.getResult());
        } else {
            responseVo = new ResponseVo<>(apiResult.getMessage());
        }

        return responseVo;
    }
    </#if>
    <#if table.validStatusColumn??>

    /**
     * 根据主键禁用
     *
     <#list pks as column>
     * @param ${fieldName}
     </#list>
     * @return
     <#include "/include/author_info1.ftl">
     */
    @RequestMapping(value = "/disable", method = RequestMethod.GET)
    public ResponseVo<Integer> disable(<#include "/include/table/validate_primary_parameters.ftl">) {

        ResponseVo<Integer> responseVo;
        ResultVo<Integer> apiResult = ${classNameLower}Service.disable(<#include "/include/table/primary_values.ftl">, getSessionId());
        if (apiResult.isSuccess()) {
            responseVo = new ResponseVo<>(apiResult.getResult());
        } else {
            responseVo = new ResponseVo<>(apiResult.getMessage());
        }

        return responseVo;
    }

    /**
     * 根据主键启用
     *
     <#list pks as column>
     * @param ${fieldName}
     </#list>
     * @return
     <#include "/include/author_info1.ftl">
     */
    @RequestMapping(value = "/enable", method = RequestMethod.GET)
    public ResponseVo<Integer> enable(<#include "/include/table/validate_primary_parameters.ftl">) {

        ResponseVo<Integer> responseVo;
        ResultVo<Integer> apiResult = ${classNameLower}Service.enable(<#include "/include/table/primary_values.ftl">, getSessionId());
        if (apiResult.isSuccess()) {
            responseVo = new ResponseVo<>(apiResult.getResult());
        } else {
            responseVo = new ResponseVo<>(apiResult.getMessage());
        }

        return responseVo;
    }
    <#if (table.uniquePrimaryKey??)>

    /**
     * 根据主键列表禁用
     *
     * @param ${table.uniquePrimaryKey.targetColumnName?uncap_first}List
     * @return
     <#include "/include/author_info1.ftl">
     */
    @RequestMapping(value = "/disableList", method = RequestMethod.POST)
    public ResponseVo<Integer> disableList(@RequestBody List<${table.uniquePrimaryKey.targetDataType}> ${table.uniquePrimaryKey.targetColumnName?uncap_first}List) {

        ResponseVo<Integer> responseVo;
        ResultVo<Integer> apiResult = ${classNameLower}Service.disable(${table.uniquePrimaryKey.targetColumnName?uncap_first}List, getSessionId());
        if (apiResult.isSuccess()) {
            responseVo = new ResponseVo<>(apiResult.getResult());
        } else {
            responseVo = new ResponseVo<>(apiResult.getMessage());
        }

        return responseVo;
    }

    /**
     * 根据主键列表启用
     *
     * @param ${table.uniquePrimaryKey.targetColumnName?uncap_first}List
     * @return
     <#include "/include/author_info1.ftl">
     */
    @RequestMapping(value = "/enableList", method = RequestMethod.POST)
    public ResponseVo<Integer> enableList(@RequestBody List<${table.uniquePrimaryKey.targetDataType}> ${table.uniquePrimaryKey.targetColumnName?uncap_first}List) {

        ResponseVo<Integer> responseVo;
        ResultVo<Integer> apiResult = ${classNameLower}Service.enable(${table.uniquePrimaryKey.targetColumnName?uncap_first}List, getSessionId());
        if (apiResult.isSuccess()) {
            responseVo = new ResponseVo<>(apiResult.getResult());
        } else {
            responseVo = new ResponseVo<>(apiResult.getMessage());
        }

        return responseVo;
    }
    </#if>
    </#if>

    /**
     * 根据主键获取
     *
     <#list pks as column>
     * @param ${fieldName}
     </#list>
     * @return
     <#include "/include/author_info1.ftl">
     */
    @RequestMapping(value = { "/detail" }, method = RequestMethod.GET)
    public ResponseVo<${className}DetailVm> detail(<#include "/include/table/validate_primary_parameters.ftl">) {

        ResponseVo<${className}DetailVm> responseVo;
        ResultVo<${className}EntityExtension> apiResult = ${classNameLower}Service.get(<#include "/include/table/primary_values.ftl">, getSessionId());
        if (apiResult.isSuccess()) {
            responseVo = new ResponseVo<>();
            responseVo.setSuccess(true);
            ${className}EntityExtension vo;
            if ((vo = apiResult.getResult()) != null) {
                ${className}DetailVm vm = new ${className}DetailVm(vo);
                responseVo.setResult(vm);
            }
        } else {
            responseVo = new ResponseVo<>(apiResult.getMessage());
        }

        return responseVo;
    }
    </#if>

    /**
     * 分页查询
     *
     * @param searchVm
     * @return
     <#include "/include/author_info1.ftl">
     */
    @RequestMapping(value = { "/search" }, method = RequestMethod.POST)
    public ResponseVo<PageInfo<${className}EntityExtension>> search(@RequestBody ${className}SearchVm searchVm) {

        ResponseVo<PageInfo<${className}EntityExtension>> responseVo;
        ${className}ConditionExtension parameter = searchVm.get${className}ConditionExtension();
        ResultVo<PageInfo<${className}EntityExtension>> apiResult = ${classNameLower}Service.getPageInfo(parameter, getSessionId());
        if (apiResult.isSuccess()) {
            PageInfo<${className}EntityExtension> pageInfo = apiResult.getResult();
            responseVo = new ResponseVo<>(pageInfo);
        } else {
            responseVo = new ResponseVo<>(apiResult.getMessage());
        }

        return responseVo;
    }

    private String getSessionId() {
        return applicationName + "-" + UUID.randomUUID();
    }

}
