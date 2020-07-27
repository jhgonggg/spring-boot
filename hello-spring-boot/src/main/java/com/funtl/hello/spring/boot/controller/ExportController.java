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

//    @ApiOperation("通讯员导出")
//    @GetMapping(value = "/export")
//    public void export(@Valid CorrespondentQueryDTO dto, HttpServletResponse response) {
//        PageInfo<Correspondent> pageInfo = correspondentService.query(dto);
//        List<CorrespondentExcel> correspondentExcelList = CorrespondentImportHelper.getCorrespondentExcelList(pageInfo.getList());
//        try {
//            String time = DateUtil.format(new Date(), DatePattern.PURE_DATE_FORMAT);
//            String excelName = "通讯员列表-" + time;
//            String fileName = URLEncoder.encode(excelName, SysConst.UTF_8);
//            response.setHeader("Content-disposition", "attachment; filename=" + fileName + SysConst.XLSX);
//            response.setCharacterEncoding(SysConst.UTF_8);
//            response.setContentType("application/vnd.ms-excel");
//            WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
//            // 设置边框
//            contentWriteCellStyle.setBorderBottom(BorderStyle.THIN);
//            contentWriteCellStyle.setBorderLeft(BorderStyle.THIN);
//            contentWriteCellStyle.setBorderRight(BorderStyle.THIN);
//            contentWriteCellStyle.setBorderTop(BorderStyle.THIN);
//            WriteCellStyle headWriteCellStyle = new WriteCellStyle();
//            //设置表头居中对齐
//            headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
//            HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
//            EasyExcel.write(response.getOutputStream(), CorrespondentExcel.class).excelType(ExcelTypeEnum.XLSX).sheet("通讯员").registerWriteHandler(horizontalCellStyleStrategy).doWrite(correspondentExcelList);
//        } catch (Exception e) {
//            log.error("通讯员导出失败", e);
//            throw new SouthcnException(MsgCode.FAIL, "通讯员导出异常");
//        }
//    }

}
