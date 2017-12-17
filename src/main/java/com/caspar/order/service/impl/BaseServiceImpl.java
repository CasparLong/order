package com.caspar.order.service.impl;

import com.caspar.order.entity.base.BaseDomain;
import com.caspar.order.service.base.BaseService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Description:
 *
 * @author Caspar
 * @Date 2017/12/13
 */
public abstract class BaseServiceImpl<T extends BaseDomain> implements BaseService<T>, InitializingBean {

    protected Log log = LogFactory.getLog(this.getClass());

    @Autowired
    protected Mapper<T> mapper;

    public Mapper<T> getMapper() {
        return mapper;
    }

    @SuppressWarnings("unchecked")
    protected Class<T> getActualTypeClass() {
        Class<T> entityClass = null;
        Type t = getClass().getGenericSuperclass();
        if (t instanceof ParameterizedType) {
            Type[] p = ((ParameterizedType) t).getActualTypeArguments();
            entityClass = (Class<T>) p[0];
        }
        return entityClass;
    }

    @Override
    public T selectByKey(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public T save(T entity) {
        Integer result = mapper.insert(entity);
        if (result > 0) {
            this.log.info("save:" + entity.getClass() + ",result:" + result);
        }
        return entity;
    }

    @Override
    public List<T> select(T entity) {
        return mapper.select(entity);
    }

    @Override
    public T oneSelect(T entity) {
        return mapper.selectOne(entity);
    }

    @Override
    public int delete(Integer id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int delete(Integer... id) {
        if (null != id) {
            int c = 0;
            for (Integer oneId : id) {
                c += mapper.deleteByPrimaryKey(oneId);
            }
            return c;
        } else {
            throw new RuntimeException("id is not null");
        }
    }

    @Override
    public int updateAll(T entity) {
        return mapper.updateByPrimaryKey(entity);
    }

    @Override
    public int selectCount(T entity) {
        return mapper.selectCount(entity);
    }

    @Override
    public int updateNotNull(T entity) {
        return mapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public List<T> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public List<T> selectByExample(Object example) {
        return mapper.selectByExample(example);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.log.info(this.getClass() + " initialized...");
    }
}