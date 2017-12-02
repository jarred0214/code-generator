package ${basePackage}.service;

import ${paginationFullClass};
import ${basePackage}.condition.order.OrderCondition;

import java.io.Serializable;
import java.util.List;

public interface BaseService<T extends Serializable, C extends Serializable> {

    /**
     * 添加
     *
     * @param model
     * @return
     */
    int insert(T model);

    /**
     * 添加
     *
     * @param models
     * @return
     */
    int insertList(List<T> models);

    /**
     * 根据条件删除
     *
     * @param condition
     * @return
     */
    int deleteByCondition(C condition);

    /**
     * 根据条件更新
     *
     * @param model
     * @param condition
     * @return
     */
    int updateByCondition(T model, C condition);

    /**
     * 查询
     *
     * @param condition
     * @param order
     * @param pagination
     * @return
     */
    ${paginationClass}<T> findByCondition(C condition, OrderCondition order, ${paginationClass} pagination);

}
