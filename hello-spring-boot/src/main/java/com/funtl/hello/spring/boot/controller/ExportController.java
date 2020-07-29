package com.funtl.hello.spring.boot.controller;

import org.springframework.web.bind.annotation.RestController;

/**
 * @author qy
 * @date 2020/7/27 16:36
 * @description
 */
@RestController
public class ExportController {

//    @ApiOperation(value = "效能导出")
//    @GetMapping("/allWorkExport")
//    public void allWorkExport(StaticsDTO staticsDTO, HttpServletResponse response) {
//        StatResult result = statisticsService.allWork(staticsDTO);
//        // 列名
//        List<StatModel> allStatList = result.getStatList();
//        // 数据
//        List<AllStatModel> aStatList = result.getAstatList();
//
//        List<List<String>> head = Lists.newArrayList();
//        List<String> firstHead = Arrays.asList(StringUtils.EMPTY, "部门名称");
//        head.add(firstHead);
//        allStatList.forEach(e -> {
//            List<String> column = Lists.newArrayList();
//            column.add(e.getType());
//            column.add(e.getStatName());
//            head.add(column);
//        });
//        List<List<String>> dataList = aStatList.stream().map(allStatModel -> {
//            List<String> data = allStatModel.getStatList().stream().map(StatModel::getStatNum).collect(Collectors.toList());
//            data.add(SysConst.ZERO, allStatModel.getDeptName());
//            return data;
//        }).collect(Collectors.toList());
//        ExcelUtil.export("效能统计表", head, dataList, response);
//    }

}
