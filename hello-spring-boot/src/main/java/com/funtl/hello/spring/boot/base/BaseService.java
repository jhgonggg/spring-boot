package com.funtl.hello.spring.boot.base;

import java.util.List;

/**
 * @author qy
 * @date 2020/7/29 16:28
 * @description
 */
public interface BaseService<T extends BaseEntity>{

    public T selectByKey(Object id);

    public T save(T entity);

    public T saveNotNull(T entity);

    public List<T> select(T entity);

    public T oneSelect(T entity);

    public int delete(Integer id);

    public int delete(Integer... id);

    public int delete(T entity);

    public int updateAll(T entity);

    public int selectCount(T entity);

    public int updateNotNull(T entity);

    public List<T> selectAll();

    public List<T> selectByExample(Object example);
}
