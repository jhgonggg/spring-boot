package com.funtl.hello.spring.boot.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.funtl.hello.spring.boot.base.impl.BaseServiceImpl;
import com.funtl.hello.spring.boot.config.SouthcnException;
import com.funtl.hello.spring.boot.constant.SysConst;
import com.funtl.hello.spring.boot.dto.MonitorPushDTO;
import com.funtl.hello.spring.boot.entity.MonitorConfig;
import com.funtl.hello.spring.boot.entity.MonitorPush;
import com.funtl.hello.spring.boot.entity.MonitorPushRecord;
import com.funtl.hello.spring.boot.entity.MonitorSite;
import com.funtl.hello.spring.boot.help.TokenThreadLocal;
import com.funtl.hello.spring.boot.plusRedis.redis.annotation.GetCache;
import com.funtl.hello.spring.boot.plusRedis.redis.enums.CacheParamEnum;
import com.funtl.hello.spring.boot.response.MsgCode;
import com.funtl.hello.spring.boot.service.MonitorConfigService;
import com.funtl.hello.spring.boot.service.MonitorPushRecordService;
import com.funtl.hello.spring.boot.service.MonitorPushService;
import com.funtl.hello.spring.boot.service.MonitorSiteService;
import com.funtl.hello.spring.boot.util.BeanCopyUtils;
import com.funtl.hello.spring.boot.util.reflections.FnConverter;
import com.funtl.hello.spring.boot.vo.MonitorConfigVO;
import com.funtl.hello.spring.boot.vo.MonitorPushVO;
import com.funtl.hello.spring.boot.vo.MonitorSiteVO;
import com.funtl.hello.spring.boot.vo.SiteVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.Weekend;
import tk.mybatis.mapper.weekend.WeekendCriteria;
import tk.mybatis.mapper.weekend.WeekendSqls;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MonitorSiteServiceImpl extends BaseServiceImpl<MonitorSite> implements MonitorSiteService {

    @Autowired
    private MonitorConfigService monitorConfigService;
    @Autowired
    private MonitorPushService monitorPushService;
    @Autowired
    private MonitorPushRecordService monitorPushRecordService;

    @GetCache(param = CacheParamEnum.MONITOR_LIST)
    @Override
    public List<MonitorSiteVO> sites() {
        List<MonitorSite> list = this.selectAll();
        Map<String, List<MonitorSite>> map = list.stream().collect(Collectors.groupingBy(MonitorSite::getGroupId));
        List<MonitorSiteVO> vo = Lists.newArrayList();
        map.forEach((k, v)-> list.stream().filter(f -> StringUtils.equalsIgnoreCase(k, f.getGroupId())).findFirst()
                .ifPresent(value -> {
                    MonitorSiteVO monitorSiteVO = value.convertExt(MonitorSiteVO.class);
                    monitorSiteVO.setSites(v.stream().map(m -> SiteVO.builder().code(m.getCode()).name(m.getName())
                                .state(m.getState()).build()).collect(Collectors.toList()));
                    vo.add(monitorSiteVO);
        }));
        return vo;
    }

    @Override
    public void save(MonitorConfigVO vo) {
        MonitorConfig save = new MonitorConfig();
        BeanCopyUtils.copyProperties(vo, save);
        save.setSites(String.join(SysConst.COMMA, vo.getSites()));
        if (Objects.isNull(vo.getId())) {
            int count = monitorConfigService.selectCount(new MonitorConfig().setUserId(TokenThreadLocal.getUserId()).setStatus(true));
            if (count >= 3) {
                throw new SouthcnException(MsgCode.FAIL, "只能添加3个任务监控");
            }
            save.setUserId(TokenThreadLocal.getUserId()).setCreateTime(new Date()).setUpdateTime(new Date());
            monitorConfigService.saveNotNull(save);
        } else {
            save.setUpdateTime(new Date());
            monitorConfigService.updateNotNull(save);
        }
    }

    @Override
    public List<MonitorConfigVO> list(String userId) {
        FnConverter<MonitorConfig> fn = FnConverter.of(MonitorConfig.class);
        Example example = new Example.Builder(MonitorConfig.class)
                .where(WeekendSqls.<MonitorConfig>custom().andEqualTo(MonitorConfig::getUserId, userId)
                                              .andEqualTo(MonitorConfig::getStatus, true))
                .orderByDesc(fn.fnToFieldName(MonitorConfig::getId)).build();
        List<MonitorConfig> list = monitorConfigService.selectByExample(example);
        return list.stream().map(m -> {
                    MonitorConfigVO vo = m.convertExt(MonitorConfigVO.class);
                    vo.setSites(Splitter.on(SysConst.COMMA).trimResults().splitToList(m.getSites()));
                    vo.setPushNum(monitorPushRecordService.selectCount(
                            new MonitorPushRecord().setUserId(userId).setIsPush(true).setConfigId(m.getId())));
                    return vo;
                }).collect(Collectors.toList());
    }

    @Override
    public void receives(List<MonitorPushDTO> list) {
        list = list.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() ->
                new TreeSet<>(Comparator.comparing(MonitorPushDTO::getTitle))), ArrayList::new));
        list = list.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() ->
                new TreeSet<>(Comparator.comparing(MonitorPushDTO::getUrl))), ArrayList::new));
        list.forEach(m -> {
            MonitorPush save = new MonitorPush();
            BeanCopyUtils.copyProperties(m, save);
            save.setDocId(m.getDoc_id()).setSendIp(m.getSend_ip()).setSendTime(m.getSend_time()).setMediaName(m.getMedia_name())
                .setSystem(SysConst.ND).setPvCount(m.getPv_count()).setMediaId(m.getMedia_id()).setReplyCount(m.getReply_count())
                .setPushTime(m.getPtime()).setCreateTime(new Date()).setUpdateTime(new Date());
            monitorPushService.saveNotNull(save);
            this.savePushRecord(save);
        });
    }

    @Override
    public List<MonitorPushVO> pushList(Integer configId) {
        FnConverter<MonitorPushRecord> fn = FnConverter.of(MonitorPushRecord.class);
        WeekendSqls<MonitorPushRecord> where =
                WeekendSqls.<MonitorPushRecord>custom().andEqualTo(MonitorPushRecord::getUserId, TokenThreadLocal.getUserId())
                        .andEqualTo(MonitorPushRecord::getIsPush, true).andEqualTo(MonitorPushRecord::getConfigId, configId);
        Example example = new Example.Builder(MonitorPushRecord.class).where(where)
                                     .orderByDesc(fn.fnToFieldName(MonitorPushRecord::getId)).build();

        PageInfo<MonitorPushRecord> pageInfo = PageHelper.startPage(SysConst.ZERO, 50).doSelectPageInfo(() ->
                monitorPushRecordService.selectByExample(example));
        return pageInfo.getList().stream().map(m -> monitorPushService.selectByKey(m.getPushId())).filter(Objects::nonNull)
                .map(m -> m.convertExt(MonitorPushVO.class)).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void siteList(String data) {

        // 删除
        List<MonitorSite> sites = this.selectAll();
        sites.forEach(f -> this.delete(f.getId()));

        JSONArray arr = JSON.parseArray(data);
        arr.forEach(f -> {
            JSONObject j = JSON.parseObject(JSON.toJSONString(f));
            JSONArray array = j.getJSONArray("sites");
            array.forEach(s -> {
                JSONObject site = JSON.parseObject(JSON.toJSONString(s));
                MonitorSite save = new MonitorSite().setCode(site.getString("id")).setGroupId(j.getString("groupid"))
                        .setGroupName(j.getString("groupname")).setName(site.getString("name"))
                        .setState(site.getIntValue("site")).setType(j.getString("type")).setStatus(SysConst.ONE)
                        .setCreateTime(new Date()).setUpdateTime(new Date());
                this.saveNotNull(save);
            });
        });
    }

    private void savePushRecord(MonitorPush save) {
        String all = StringUtils.substringBeforeLast(save.getMediaId(), SysConst.DOT).concat(SysConst.DOT).concat(SysConst.ALL);
        Weekend<MonitorConfig> condition = Weekend.of(MonitorConfig.class);
        WeekendCriteria<MonitorConfig, Object> criteria = condition.weekendCriteria();
        criteria.andEqualTo(MonitorConfig::getStatus, true).andEqualTo(MonitorConfig::getState, true);

        WeekendCriteria<MonitorConfig, Object> sitesCriteria = condition.weekendCriteria();
        sitesCriteria.orLike(MonitorConfig::getSites, SysConst.PERCENT.concat(save.getMediaId()).concat(SysConst.PERCENT));
        sitesCriteria.orLike(MonitorConfig::getSites, SysConst.PERCENT.concat(all).concat(SysConst.PERCENT));
        condition.and(sitesCriteria);

        List<MonitorConfig> configs = monitorConfigService.selectByExample(condition);
        log.info("找到用户配置-->{}", JSON.toJSONString(configs));
        configs.stream().filter(f -> this.matchSearch(f, save)).forEach(f -> {
            MonitorSite site = this.oneSelect(new MonitorSite().setCode(save.getMediaId()));
            String msg = String.format(SysConst.MONITOR_MSG, save.getTitle(), save.getUrl(),
                    Objects.nonNull(site) ? site.getName() : StringUtils.EMPTY, f.getName());
            MonitorPushRecord record =
                    new MonitorPushRecord().setPushId(save.getId()).setUserId(f.getUserId()).setMsg(msg).setConfigId(f.getId())
                    .setPushTime(save.getPushTime()).setUpdateTime(new Date());
            monitorPushRecordService.saveNotNull(record);
            log.info("保存推送记录-->{}, pushId-->{}", f.getUserId(), save.getId());
        });
    }

    private boolean matchSearch(MonitorConfig f, MonitorPush save) {
        String matchStr = StringUtils.defaultString(save.getTitle()) + StringUtils.defaultString(save.getSummary())
                + StringUtils.defaultString(save.getContent());

        String[] searchs = StringUtils.split(
                StringUtils.replace(f.getKeywordSearch(), SysConst.CHINESE_COMMA, SysConst.COMMA), SysConst.COMMA);
        String[] keywordMust = StringUtils.split(
                StringUtils.replace(f.getKeywordMust(), SysConst.CHINESE_COMMA, SysConst.COMMA), SysConst.COMMA);
        String[] excludes = StringUtils.split(
                StringUtils.replace(f.getKeywordExclude(), SysConst.CHINESE_COMMA, SysConst.COMMA), SysConst.COMMA);
        if (StringUtils.containsAny(matchStr, excludes)) {
            return false;
        }
        if (ArrayUtils.getLength(searchs) == SysConst.ONE
                && StringUtils.equals(searchs[SysConst.ZERO], SysConst.MONITOR_SEARCH_ALL)) {
            if (ArrayUtils.isEmpty(keywordMust)) {
                return true;
            }
            if (ArrayUtils.isNotEmpty(keywordMust) && StringUtils.containsAny(matchStr, keywordMust)) {
                return true;
            }
        }
        return StringUtils.containsAny(matchStr, searchs) && ArrayUtils.isEmpty(keywordMust) ||
                StringUtils.containsAny(matchStr, searchs) && (ArrayUtils.isNotEmpty(keywordMust)
                        && StringUtils.containsAny(matchStr, keywordMust));
    }
}
