package com.funtl.hello.spring.boot.service;

import com.funtl.hello.spring.boot.base.BaseService;
import com.funtl.hello.spring.boot.dto.CorrespondentQueryDTO;
import com.funtl.hello.spring.boot.entity.Correspondent;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author qy
 * @date 2019/9/26 12:32
 * @description
 */
public interface CorrespondentService extends BaseService<Correspondent> {

    void add();

    /**
     * 查询通讯员信息
     *
     * @param dto 通讯员请求实体
     * @return 通讯员分页对象
     */
    PageInfo<Correspondent> query(CorrespondentQueryDTO dto);

    PageInfo<Correspondent> query2(CorrespondentQueryDTO dto);

    /**
     * 批量逻辑删除
     *
     * @param ids 通讯员id集合
     */
    void delete(List<Integer> ids);


    /**
     * 批量保存
     *
     * @param correspondentList 通讯员集合
     * @param userId 添加人用户id
     */
    void add(List<Correspondent> correspondentList, String userId);

    /**
     * 通过姓名查找通讯员
     * @param name 姓名
     * @return 通讯员集合
     */
    List<Correspondent> getByName(String name);
}
