package com.funtl.hello.spring.boot.util;

import com.funtl.hello.spring.boot.constant.SysConst;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class ReportUtil {

    //报题类别 ID与名称对应 缓存
    public static final Map<String, String> reportTypeIdToNameCache = new ConcurrentHashMap<>();
    //部门 deptCode 与名称对应 缓存
    public static final Map<String, String> deptCodeToNameCache = new ConcurrentHashMap<>();

    public static String getReportTypeNameById(Integer id) {
        if (reportTypeIdToNameCache.size() == 0) {//等于0 则 初始化
            doCacheReportType();
        } else {//缓存过期，则初始化
            String time = reportTypeIdToNameCache.get("time");
            try {
                Date old = DateUtils.parseDate(time, SysConst.DATE_FORMAT);
                Date now = new Date();
                long l = now.getTime() - old.getTime();
                if ((now.getTime() - old.getTime()) > 600000) {//超过十分钟，刷新缓存。
                    doCacheReportType();
                }
            } catch (ParseException e) {
                log.error("日期转换异常", e);
            }
        }
        return Objects.nonNull(id) ? reportTypeIdToNameCache.get(String.valueOf(id)) : StringUtils.EMPTY;
    }

    public static String getDeptNameByDeptCode(String deptCode) {
        if (MapUtils.isEmpty(deptCodeToNameCache)) {//等于0 则 初始化
            doCacheDept();
        } else {//缓存过期，则初始化
            String time = deptCodeToNameCache.get("time");
            try {
                Date old = DateUtils.parseDate(time, SysConst.DATE_FORMAT);
                Date now = new Date();
                if ((now.getTime() - old.getTime()) > 600000) {//超过十分钟，刷新缓存。
                    doCacheDept();
                }
            } catch (ParseException e) {
                log.error("日期转换异常", e);
            }
        }
        return StringUtils.isBlank(deptCode) || MapUtils.isEmpty(deptCodeToNameCache) ? StringUtils.EMPTY : deptCodeToNameCache.get(deptCode);
    }

    private static void doCacheReportType() {
//        WorkReportTypeMapper typeMapper = (WorkReportTypeMapper) SpringUtil.getBean("workReportTypeMapper");
//        List<WorkReportType> types = typeMapper.selectAll();
//        types.forEach(e->{
//            reportTypeIdToNameCache.put(String.valueOf(e.getId()),e.getReportTypeName());
//        });
//        reportTypeIdToNameCache.put("time",DateUtil.getCurrentDateString(SysConst.DATE_FORMAT));
    }


    private static void doCacheDept() {
//        DeptMapper deptMapper = (DeptMapper) SpringUtil.getBean("deptMapper");
//        List<Dept> depts = deptMapper.selectAll();
//        depts.forEach(e->{
//            deptCodeToNameCache.put(e.getDeptCode(),e.getDeptName());
//        });
//        deptCodeToNameCache.put("time",DateUtil.getCurrentDateString(SysConst.DATE_FORMAT));
    }

}
