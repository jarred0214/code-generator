<#assign className = table.tableClassName>
<#assign classNameLower = table.tableClassNameFirstLower>
package ${basePackage}.quickadmin.vm.out;

import ${basePackage}.entity.${className}Entity;
import ${basePackage}.vo.${className}Vo;

import java.math.BigDecimal;
import java.util.Date;
import java.io.Serializable;

public class ${className}ListModel implements Serializable {

    public ${className}ListModel() {
    }

    public ${className}ListModel(${className}Vo vo) {

        <#list table.columns as column>
        ${column.columnFieldNameFirstLower} = vo.get${column.columnFieldName}();
        </#list>
    }

    public ${className}ListModel(${className}Entity entity) {

        <#list table.columns as column>
        ${column.columnFieldNameFirstLower} = entity.get${column.columnFieldName}();
        </#list>
    }

    <#list table.columns as column>
    /**
     * ${column.columnComment}
     */
    private ${column.columnFieldType} ${column.columnFieldNameFirstLower};

    </#list>

    <#list table.columns as column>
    /**
    * 设置${column.columnComment}
    */
    public void set${column.columnFieldName}(${column.columnFieldType} ${column.columnFieldNameFirstLower}) {
        this.${column.columnFieldNameFirstLower} = ${column.columnFieldNameFirstLower};
    }

    /**
    * 获取${column.columnComment}
    */
    public ${column.columnFieldType} get${column.columnFieldName}() {
        return ${column.columnFieldNameFirstLower};
    }

    </#list>
}
