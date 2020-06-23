package com.funtl.hello.spring.boot.help;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.funtl.hello.spring.boot.annotation.ContentValidate;
import com.funtl.hello.spring.boot.constant.SysConst;
import com.funtl.hello.spring.boot.enums.WorkFlowTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

@Slf4j
public class ContentValidator implements ConstraintValidator<ContentValidate, Object> {

    /**
     * 工作流web层组合校验，发布的内容长度（每种工作流字段content里面的字段都不一样）需要根据具体工作流类型而定
     * admin api都需要用到
     */

    @Override
    public void initialize(ContentValidate constraintAnnotation) {

    }

    /**
     * pc端、app端发起工作流程公用参数校验，发布的内容长度视工作流类型不同而不同
     * @param value 检验的内容
     * @param context 上下文环境
     * @return  校验结果 成功 true  失败 false
     */
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (Objects.isNull(value)) {
            return true;
        }
        JSONObject json = JSON.parseObject(JSON.toJSONString(value));
        int type = json.getIntValue("type");
        String content = json.getString("content");
        if (StringUtils.isBlank(content)) {
            return false;
        }
        JSONObject jsonContent = JSON.parseObject(content);
        if (Objects.isNull(jsonContent)) {
            return false;
        }
        WorkFlowTypeEnum typeEnum = WorkFlowTypeEnum.getInstance(type);
        int length = SysConst.ZERO;
        switch (typeEnum) {
            case NOTE:
                length = StringUtils.length(jsonContent.getString("noteContent"));
                break;
            case SHARE:
            case CHECKIN:
                length = StringUtils.length(jsonContent.getString("texContent"));
                break;
            case TASK:
            case VISIT:
            case REPORT:
            case MANUSCRIPTS:
            case REVIEWER:
                length = StringUtils.length(jsonContent.getString("visitContent"));
                break;
            case APPROVAL:
                length = StringUtils.length(jsonContent.getString("auditContent"));
                break;
            case CLUE:
                length = StringUtils.length(jsonContent.getString("clueContent"));
                break;
            default:
                break;
        }
        return length <= 16000;
    }
}