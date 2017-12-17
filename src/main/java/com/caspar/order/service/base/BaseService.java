package com.caspar.order.service.base;

import com.caspar.order.entity.base.BaseDomain;

import java.util.List;

/**
 * Description:
 *
 * @author Caspar
 * @Date 2017/12/13
 */
public interface BaseService<T extends BaseDomain> {

    T selectByKey(Integer id);

    T save(T entity);

    List<T> select(T entity);

    T oneSelect(T entity);

    int delete(Integer id);

    int delete(Integer... id);

    int updateAll(T entity);

    int selectCount(T entity);

    int updateNotNull(T entity);

    List<T> selectAll();

    List<T> selectByExample(Object example);

}