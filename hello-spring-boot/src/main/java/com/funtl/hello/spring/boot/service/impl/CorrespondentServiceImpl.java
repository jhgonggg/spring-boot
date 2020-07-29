package com.funtl.hello.spring.boot.service.impl;

import com.funtl.hello.spring.boot.base.impl.BaseServiceImpl;
import com.funtl.hello.spring.boot.constant.SysConst;
import com.funtl.hello.spring.boot.dto.CorrespondentQueryDTO;
import com.funtl.hello.spring.boot.entity.Correspondent;
import com.funtl.hello.spring.boot.mapper.CorrespondentMapper;
import com.funtl.hello.spring.boot.service.CorrespondentService;
import com.funtl.hello.spring.boot.util.PhoneUtil;
import com.funtl.hello.spring.boot.util.reflections.FnConverter;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.Weekend;
import tk.mybatis.mapper.weekend.WeekendCriteria;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author qy
 * @date 2019/9/26 12:34
 * description
 */
@Service
@Slf4j
public class CorrespondentServiceImpl extends BaseServiceImpl<Correspondent> implements CorrespondentService {

    @Autowired
    private CorrespondentMapper correspondentMapper;

    @Override
    public void add() {
        List<Correspondent> list = Lists.newArrayList();
        list.add(new Correspondent().setName("123").setPhone("13825694572").setType(1).setUnit("单位1").setArea("广州")
                .setCreateTime(new Date()).setDept("1").setDescription("描述").setStatus(0).setUserId("test"));
        list.add(new Correspondent().setName("124").setPhone("13825694573").setType(2).setUnit("单位2").setArea("广州1")
                .setCreateTime(new Date()).setDept("3").setDescription("描述2").setStatus(0).setUserId("test"));
        list.add(new Correspondent().setName("125").setPhone("13825694577").setType(1).setUnit("单位3").setArea("广州2")
                .setCreateTime(new Date()).setDept("5").setDescription("描述3").setStatus(0).setUserId("test"));
        // 必须全部字段....
        correspondentMapper.insertList(list);
    }

    @Override
    public PageInfo<Correspondent> query(CorrespondentQueryDTO dto) {
        String search = dto.getSearch();
        Page<Correspondent> page = PageHelper.startPage(dto.getPageNumber(), dto.getPageSize());
        Weekend<Correspondent> weekend = Weekend.of(Correspondent.class);
        WeekendCriteria<Correspondent, Object> weekendCriteria = weekend.weekendCriteria();
        if (StringUtils.isNotBlank(search)) {
            if (PhoneUtil.isMobileNumber(search)) {
                weekendCriteria.andEqualTo(Correspondent::getPhone, search);
            } else {
                String like = SysConst.PERCENT.concat(StringUtils.trim(search)).concat(SysConst.PERCENT);
                weekendCriteria.orLike(Correspondent::getName, like).orLike(Correspondent::getUnit, like);
            }
        }
        Example example = new Example(Correspondent.class);
        Example.Criteria criteria = example.createCriteria();
        FnConverter<Correspondent> fn = FnConverter.of(Correspondent.class);
        criteria.andEqualTo(fn.fnToFieldName(Correspondent::getStatus), SysConst.ZERO);
        if (StringUtils.isNotBlank(dto.getArea())) {
            criteria.andEqualTo(fn.fnToFieldName(Correspondent::getArea), dto.getArea());
        }
        if (Objects.nonNull(dto.getType())) {
            criteria.andEqualTo(fn.fnToFieldName(Correspondent::getType), dto.getType());
        }
        weekend.orderBy(fn.fnToFieldName(Correspondent::getCreateTime)).desc();
        weekend.and(criteria);
        this.mapper.selectByExample(weekend);
        return page.toPageInfo();
    }

    @Override
    public PageInfo<Correspondent> query2(CorrespondentQueryDTO dto) {
        String search = dto.getSearch();
        Page<Correspondent> page = PageHelper.startPage(dto.getPageNumber(), dto.getPageSize());
        Weekend<Correspondent> weekend = Weekend.of(Correspondent.class);
        WeekendCriteria<Correspondent, Object> weekendCriteria = weekend.weekendCriteria();
        if (StringUtils.isNotBlank(search)) {
            if (PhoneUtil.isMobileNumber(search)) {
                weekendCriteria.andEqualTo(Correspondent::getPhone, search);
            } else {
                String like = SysConst.PERCENT.concat(StringUtils.trim(search)).concat(SysConst.PERCENT);
                weekendCriteria.orLike(Correspondent::getName, like).orLike(Correspondent::getUnit, like);
            }
        }
        WeekendCriteria<Correspondent, Object> criteria = weekend.weekendCriteria();
        criteria.andEqualTo(Correspondent::getStatus, SysConst.ZERO);
        if (StringUtils.isNotBlank(dto.getArea())) {
            criteria.andEqualTo(Correspondent::getArea, dto.getArea());
        }
        if (Objects.nonNull(dto.getType())) {
            criteria.andEqualTo(Correspondent::getType, dto.getType());
        }
        FnConverter<Correspondent> fn = FnConverter.of(Correspondent.class);
        weekend.orderBy(fn.fnToFieldName(Correspondent::getCreateTime)).desc();
        weekend.and(criteria);
        this.mapper.selectByExample(weekend);
        return page.toPageInfo();
    }


    @Override
    public void delete(List<Integer> ids) {
        ids.forEach(id -> this.mapper.updateByPrimaryKeySelective(new Correspondent().setStatus(SysConst.ONE).setId(id)));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(List<Correspondent> correspondentList, String userId) {
        Date date = new Date();
        correspondentList.forEach(correspondent -> {
            correspondent.setUserId(userId);
            if (StringUtils.isNotBlank(correspondent.getPhone())) {
                correspondentExist(correspondent, date);
            } else {
                correspondent.setCreateTime(date);
                this.mapper.insertSelective(correspondent);
            }
        });
    }

    private void correspondentExist(Correspondent correspondent, Date date) {
        Correspondent one = queryByNameAndPhone(correspondent.getName(), correspondent.getPhone());
        if (Objects.nonNull(one)) {
            this.mapper.updateByPrimaryKeySelective(correspondent.setStatus(SysConst.ZERO).setId(one.getId()).setCreateTime(date));
        } else {
            correspondent.setCreateTime(date);
            this.mapper.insertSelective(correspondent);
        }
    }

    @Override
    public List<Correspondent> getByName(String name) {
        Weekend<Correspondent> weekend = Weekend.of(Correspondent.class);
        WeekendCriteria<Correspondent, Object> weekendCriteria = weekend.weekendCriteria();
        weekendCriteria.andEqualTo(Correspondent::getStatus, SysConst.ZERO)
                       .andLike(Correspondent::getName, SysConst.PERCENT.concat(StringUtils.trim(name)).concat(SysConst.PERCENT));
        return this.mapper.selectByExample(weekend);
    }

    /**
     * 根据手机号和姓名查询对象
     * @param name 姓名
     * @param phone 手机号
     * @return 通讯员对象
     */
    private Correspondent queryByNameAndPhone(String name, String phone) {
        // 根据手机号和姓名查询
        return this.mapper.selectOne(new Correspondent().setName(name).setPhone(phone));
    }
}
