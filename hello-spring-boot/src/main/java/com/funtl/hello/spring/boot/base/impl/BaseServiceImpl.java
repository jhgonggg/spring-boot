package com.funtl.hello.spring.boot.base.impl;

import com.funtl.hello.spring.boot.base.BaseEntity;
import com.funtl.hello.spring.boot.base.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author qy
 * @date 2020/7/29 16:29
 * @description
 */
@Slf4j
public abstract class BaseServiceImpl<T extends BaseEntity> implements BaseService<T>, InitializingBean {


    @Autowired
    protected Mapper<T> mapper;




    /*public Mapper<T> getMapper() {
        return mapper;
    }*/

    public <M extends Mapper> M getMapper() {
        return (M) mapper;
    }

    /*public <T> T getMapperExt(){
    	return (T)mapper;
	}*/

    /**
     * 获取泛型 class 对象
     *
     * @return
     */
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
    public T selectByKey(Object id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public T save(T entity) {
        mapper.insert(entity);
        return entity;
    }

    @Override
    public T saveNotNull(T entity) {
        mapper.insertSelective(entity);
        return entity;
    }

    @Override
    public List<T> select(T entity) {
        // TODO Auto-generated method stub
        return mapper.select(entity);
    }

    @Override
    public int delete(Integer... id) {
        if (id != null) {
            int c = 0;
            for (int i = 0; i < id.length; i++) {
                c = c + mapper.deleteByPrimaryKey(id[i]);
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
    public int updateNotNull(T entity) {
        return mapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public int selectCount(T entity) {
        return mapper.selectCount(entity);
    }

    @Override
    public List<T> selectAll() {
        return this.mapper.selectAll();
    }


    @Override
    public List<T> selectByExample(Object example) {
        return mapper.selectByExample(example);
    }


    @Override
    public int delete(Integer id) {
        return this.mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int delete(T entity) {
        return this.mapper.delete(entity);
    }

    @Override
    public T oneSelect(T entity) {
        return this.mapper.selectOne(entity);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info(this.getClass() + "初始化完毕...");
    }


}
