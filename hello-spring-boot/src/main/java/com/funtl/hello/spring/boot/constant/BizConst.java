package com.funtl.hello.spring.boot.constant;


public final class BizConst {

    /**
     * 发出日志通知模板
     */
    public static final String NOTE_WARN = "%s发出日志:%s";
    /**
     * 发出日志通知模板
     */
    public static final String APPROVAL_WARN = "%s发出审批:%s";
    /**
     * 请你回执模板
     */
    public static final String RECEIPT_WARN = "%s请你回执:%s";
    /**
     * 提到你消息模板
     */
    public static final String ASSIGN_YOU = "%s提到你";
    /**
     * 提到你的部门模板
     */
    public static final String ASSIGN_YOUR_DEPT = "%s提到你的部门";

    /**
     * 发了一个模板
     */
    public static final String SEND_PREFIX = "%s发了一个";
    /**
     * 发了一条线索提到你
     */
    public static final String CLUE_MENTION_YOU = "%s发了一条报料提到你";
    /**
     * 发了一个签到提到你
     */
    public static final String CHECKIN_MENTION_YOU = "%s发了一个签到提到你";
    /**
     * 发了一个报题提到你
     */
    public static final String TOPIC_MENTION_YOU = "%s发了一个报题提到你";
    /**
     * 发了一个审稿,请审核！
     */
    public static final String RECEIVER_MENTION_YOU = "%s发了一个审稿,请审核！";
    /**
     * 发了一条采访提到你
     */
    public static final String VISIT_MENTION_YOU = "%s发了一条采访提到你";
    /**
     * 发了一个任务提到你
     */
    public static final String TASK_MENTION_YOU = "%s发了一个任务提到你";

    /**
     * 退出采访或者退出任务
     */
    public static final String QUIT = "%s退出了团队,退出原因:%s";

    /**
     * 结束采访
     */
    public static final String FINISH_VISIT = "%s结束采访";

    /**
     * 点赞
     */
    public static final String GOOD = "%s赞了我的%s";

    /**
     * 该通知模板用于新增校对人、部门审核
     * String mes = usermodel.getUser_name() + warnName + wf.getWf_content();
     */
    public static final String ADD_AUDIT_MANS = "%s发了一个审稿：%s";

    /**
     * 该通知模板用于新增终审人
     * String mes = usermodel.getUser_name() + warnName + wf.getWf_content();
     */
    public static final String ADD_AUDIT_EDITOR_MANS = "%s请审稿：%s";

    /**
     * String mes = usermodel.getUser_name() + "回执工作：" + wf.getWf_content();
     */
    public static final String RECEIPT_WORK = "%s回执工作：%s";


    /**
     * new StringBuilder(user.getUserName()).append("发").append(content).toString();
     */
    public static final String AUDIT_TEMPLATE = "%s发%s";

    /**
     * 校对链接模板
     */
    public static final String PROOFREAD_TEMPLATE = "&proofreader=%s&sender=%s&assignee=%s#/";



    public static final String USED = "已采用";
    public static final String SIGN_ISSUE = "已签发";
    public static final String CHECK_MAN_EDIT_PASS = "审校编辑后通过";
    public static final String LEADER_MAN_EDIT_PASS = "部门编辑后通过";
    public static final String CHECK_MAN_PASS = "审校通过";
    public static final String LEADER_MAN_PASS = "部门通过";

    public static final String MANUSCRIPT_UNAUDIT_NAME = "交稿-待审稿";
    public static final String REVIEWER_UNAUDIT_NAME = "编审-待审稿";

    /**
     * 前端提交表单对字段进行url解码用到
     */
    public static final String MATCH = "%(?![0-9a-fA-F]{2})";
    /**
     * 前端提交表单对字段进行url解码用到
     */
    public static final String REPLACE_MATCH = "%25";
    /**
     * 前端提交表单对字段进行url解码用到
     */
    public static final String ADD = "\\+";
    /**
     * 前端提交表单对字段进行url解码用到
     */
    public static final String REPLACE_2B = "%2B";

    public static final Integer EDIT_PASS = 5;

    /**
     * 修改审稿模式消息模板
     */
    public static final String MODIFY_VISIT_TYPE = "%s修改审稿模式";
}
