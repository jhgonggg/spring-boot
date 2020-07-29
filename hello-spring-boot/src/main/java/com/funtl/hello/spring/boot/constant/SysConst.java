package com.funtl.hello.spring.boot.constant;


import com.funtl.hello.spring.boot.enums.WorkFlowTypeEnum;

import java.util.Arrays;
import java.util.List;

public final class SysConst {

    public static final String ALL = "*";
    public static final String SQUARE_BRACKETS = "[]";
    public static final String COMMA = ",";
    public static final String COLON = ";";
    public static final String PERCENT = "%";
    public static final String DOT = ".";
    public static final String HORIZONTAL = "-";
    public static final String VARIABLE_IDENTIFIER = "#";
    public static final String UTF_8 = "UTF-8";
    public static final String SLANTING = "/";
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String XLSX = ".xlsx";
    public static final long EXPIRE_DEFAULT = 60L * 5;

    public static final String DOUBLE_QUOTATION = "::";

    public static final String PIC_ID = "pic-id";

    public static final String PIC_SRC = "src";

    public static final String BODY = "body";

    public static final int ZERO = 0;

    public static final int ONE = 1;

    /**
     * token常量
     */

    public static final String TOKEN = "token";

    /**
     * admin模块排除登录校验
     */
    public static final String[] ADMIN_END_WITHS = new String[]{"currentUserInfo", "index.do", "logout.do", "enter",
            "signOut", "sendCode", "getCode", "getQrcode", "checkQrcode", "checkToken"};

    /**
     * 工作类型常量
     */
    public static final List<Integer> WORK_TYPES = Arrays.asList(
            WorkFlowTypeEnum.SHARE.getType(),
            WorkFlowTypeEnum.NOTE.getType(),
            WorkFlowTypeEnum.VISIT.getType(),
            WorkFlowTypeEnum.APPROVAL.getType(),
            WorkFlowTypeEnum.CLUE.getType(),
            WorkFlowTypeEnum.CHECKIN.getType(),
            WorkFlowTypeEnum.REPORT.getType(),
            WorkFlowTypeEnum.MANUSCRIPTS.getType(),
            WorkFlowTypeEnum.TASK.getType(),
            WorkFlowTypeEnum.REVIEWER.getType(),
            WorkFlowTypeEnum.PROOFREAD.getType());

    /**
     * admin模块aop排除
     */
    public static final List<String> ADMIN_EXCLUDES = Arrays.asList(
            "com.southcn.nfplus.report.conduct.admin.controller.wf.WorkFlowController.initBinder",
            "com.southcn.nfplus.report.conduct.admin.controller.StatisticsController.initBinder",
            "com.southcn.nfplus.report.conduct.admin.controller.StatisticsExportController.initBinder",
            "com.southcn.nfplus.report.conduct.admin.controller.wf.VisitOperateController.initBinder",
            "com.southcn.nfplus.report.conduct.admin.controller.wf.WorkFlowOperateController.initBinder");

}
