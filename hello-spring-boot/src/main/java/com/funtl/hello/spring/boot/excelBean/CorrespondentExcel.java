package com.funtl.hello.spring.boot.excelBean;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author qy
 * @date 2020/2/26 17:17
 * @description
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CorrespondentExcel implements Serializable {

    @ExcelProperty(value = "编号")
    private Integer id ;

    @ExcelProperty(value = "姓名")
    private String name;

    @ExcelProperty(value = "手机号")
    private String phone;

    @ExcelProperty(value = "类型")
    private String type;

    @ExcelProperty(value = "单位")
    private String unit;

    @ExcelProperty(value = "地区")
    private String area;

    @ExcelProperty(value = "部门")
    private String dept;

    @ExcelProperty(value = "简介")
    private String description;

    @ExcelProperty(value = "头像")
    private String avatar;

}
