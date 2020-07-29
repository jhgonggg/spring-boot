package com.funtl.hello.spring.boot.help;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.funtl.hello.spring.boot.entity.Correspondent;
import com.funtl.hello.spring.boot.enums.CorrespondentTypeEnum;
import com.funtl.hello.spring.boot.enums.ErrorEnum;
import com.funtl.hello.spring.boot.excelBean.CorrespondentExcel;
import com.funtl.hello.spring.boot.util.PhoneUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author qy
 * @date 2019/9/27 15:46
 * @description 导入工具类
 */
@Slf4j
public class CorrespondentImportHelper {

    public static List<CorrespondentExcel> getCorrespondentExcelList(List<Correspondent> list) {
        return list.stream().map(correspondent -> {
            String type;
            if (CorrespondentTypeEnum.CORRESPONDENT.getType().equals(correspondent.getType())) {
                type = CorrespondentTypeEnum.CORRESPONDENT.getDesc();
            } else {
                type = CorrespondentTypeEnum.PRESS_SECRETARY.getDesc();
            }
            return CorrespondentExcel.builder().id(correspondent.getId()).name(correspondent.getName())
                    .phone(correspondent.getPhone()).type(type).unit(correspondent.getUnit()).area(correspondent.getArea())
                    .dept(correspondent.getDept()).description(correspondent.getDescription()).avatar(correspondent.getAvatar()).build();
        }).collect(Collectors.toList());
    }

    /**
     * @param importList excel 导入的通讯员
     * @return 通讯员集合
     */
    public static List<Correspondent> getListByExcel(List<CorrespondentExcel> importList) {
        // 设置实体的 type 字段值
        return importList.stream().map(e -> {
            Integer type;
            if (Objects.equals(CorrespondentTypeEnum.CORRESPONDENT.getDesc(), e.getType())) {
                type = CorrespondentTypeEnum.CORRESPONDENT.getType();
            } else {
                type = CorrespondentTypeEnum.PRESS_SECRETARY.getType();
            }
            return new Correspondent().setName(e.getName()).setPhone(e.getPhone()).setType(type).setUnit(e.getUnit())
                    .setArea(e.getArea()).setDept(e.getDept());
        }).collect(Collectors.toList());
    }

    /**
     * 校验
     *
     * @param importList 通讯员集合
     * @return 校验后的结果
     */
    public static String requiredValid(List<CorrespondentExcel> importList) {
        for (CorrespondentExcel correspondentExcel : importList) {
            String type = Convert.toStr(correspondentExcel.getType());
            String phone = Convert.toStr(correspondentExcel.getPhone());
            if (StrUtil.isBlank(Convert.toStr(correspondentExcel.getName()))) {
                return ErrorEnum.NAME_NULL.getMsg();
            }
            if (StrUtil.isNotBlank(phone) && !PhoneUtil.isMobileNumber(phone)) {
                return ErrorEnum.PHONE_NOT_CORRECT.getMsg();
            }
            if (StrUtil.isBlank(type)) {
                return ErrorEnum.TYPE_NULL.getMsg();
            } else if (!Objects.equals(CorrespondentTypeEnum.CORRESPONDENT.getDesc(), type) && !Objects.equals(CorrespondentTypeEnum.PRESS_SECRETARY.getDesc(), type)) {
                return ErrorEnum.TYPE_ERROR.getMsg();
            }
            if (StrUtil.isBlank(Convert.toStr(correspondentExcel.getUnit()))) {
                return ErrorEnum.UINT_NULL.getMsg();
            }
            if (StrUtil.isBlank(Convert.toStr(correspondentExcel.getArea()))) {
                return ErrorEnum.AREA_NULL.getMsg();
            }
        }
        return StringUtils.EMPTY;
    }
}
