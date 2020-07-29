package com.funtl.hello.spring.boot.controller;


import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.fastjson.JSONObject;
import com.funtl.hello.spring.boot.config.SouthcnException;
import com.funtl.hello.spring.boot.constant.SysConst;
import com.funtl.hello.spring.boot.dto.CorrespondentQueryDTO;
import com.funtl.hello.spring.boot.entity.Correspondent;
import com.funtl.hello.spring.boot.enums.CorrespondentTypeEnum;
import com.funtl.hello.spring.boot.enums.ErrorEnum;
import com.funtl.hello.spring.boot.excelBean.CorrespondentExcel;
import com.funtl.hello.spring.boot.help.CorrespondentImportHelper;
import com.funtl.hello.spring.boot.help.TokenThreadLocal;
import com.funtl.hello.spring.boot.response.MsgCode;
import com.funtl.hello.spring.boot.response.Response;
import com.funtl.hello.spring.boot.response.ResponseBuilder;
import com.funtl.hello.spring.boot.service.CorrespondentService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author qy
 * @date 2019/9/26 12:28
 * @description 通讯员接口
 */
@Api(value = "/correspondent", tags = "通讯员接口")
@Slf4j
@RestController
@RequestMapping(value = "/correspondent")
public class CorrespondentController {

    @Autowired
    private CorrespondentService correspondentService;

    @GetMapping(value = "/add")
    public Mono<Response> add() {
        correspondentService.add();
        return Mono.fromCallable(ResponseBuilder::buildSuccess);
    }

// token: ffee77cb26bbe18890cd607656ca03bb28923a14310af9a2ac1d5f7105afd715bb8734ed87c4830f79e4f0a317a7a072bc930d6c48f91fd8eac53efe60e298d9
    @ApiOperation("通讯员信息查询")
    @GetMapping(value = "/query")
    public Mono<Response> query(@Valid CorrespondentQueryDTO dto) {
        dto.setUserId(TokenThreadLocal.getUserId());
        return Mono.fromCallable(() -> ResponseBuilder.buildSuccess(correspondentService.query(dto)));
    }

    @ApiOperation("通过姓名查询")
    @GetMapping(value = "/getByName")
    public Mono<Response> getByName(@RequestParam("name") @ApiParam("姓名") String name) {
        return Mono.fromCallable(() -> {
            List<Correspondent> list = correspondentService.getByName(name);
            List<JSONObject> jsons = list.stream().map(e -> {
                JSONObject json = new JSONObject();
                json.put("id", e.getId());
                json.put("name", e.getName());
                json.put("unit", e.getUnit());
                return json;
            }).collect(Collectors.toList());
            return ResponseBuilder.buildSuccess(jsons);
        });
    }


    @ApiOperation("通讯员删除")
    @PostMapping(value = "/delete")
    public Mono<Response> delete(@RequestParam("ids") @ApiParam("通讯员主键id") List<Integer> ids) {
        return Mono.fromCallable(() -> {
            correspondentService.delete(ids);
            return ResponseBuilder.buildSuccess();
        });
    }

    @ApiOperation("通讯员导出")
    @GetMapping(value = "/export")
    public void export(@Valid CorrespondentQueryDTO dto, HttpServletResponse response) {
        PageInfo<Correspondent> pageInfo = correspondentService.query(dto);
        List<CorrespondentExcel> correspondentExcelList = CorrespondentImportHelper.getCorrespondentExcelList(pageInfo.getList());
        try {
            String time = DateUtil.format(new Date(), DatePattern.PURE_DATE_FORMAT);
            String excelName = "通讯员列表-" + time;
            String fileName = URLEncoder.encode(excelName, SysConst.UTF_8);
            response.setHeader("Content-disposition", "attachment; filename=" + fileName + SysConst.XLSX);
            response.setCharacterEncoding(SysConst.UTF_8);
            response.setContentType("application/vnd.ms-excel");
            WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
            // 设置边框
            contentWriteCellStyle.setBorderBottom(BorderStyle.THIN);
            contentWriteCellStyle.setBorderLeft(BorderStyle.THIN);
            contentWriteCellStyle.setBorderRight(BorderStyle.THIN);
            contentWriteCellStyle.setBorderTop(BorderStyle.THIN);
            WriteCellStyle headWriteCellStyle = new WriteCellStyle();
            //设置表头居中对齐
            headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
            HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
            EasyExcel.write(response.getOutputStream(), CorrespondentExcel.class).excelType(ExcelTypeEnum.XLSX).sheet("通讯员").registerWriteHandler(horizontalCellStyleStrategy).doWrite(correspondentExcelList);
        } catch (Exception e) {
            log.error("通讯员导出失败", e);
            throw new SouthcnException(MsgCode.FAIL, "通讯员导出异常");
        }
    }

    @ApiOperation("通讯员导入模板下载")
    @GetMapping("/downloadModel")
    public void downloadModel(HttpServletResponse response) {
        ServletOutputStream out = null;
        InputStream fis = null;
        HSSFWorkbook workbook = null;
        try {
            fis = Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream("template/correspondent/通讯员批量导入模板.xls");
            workbook = new HSSFWorkbook(Objects.requireNonNull(fis));
            response.setContentType("application/binary;charset=UTF-8");
            String fileName = URLEncoder.encode("通讯员批量导入模板", SysConst.UTF_8);
            response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");
            response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
            out = response.getOutputStream();
            workbook.write(out);
            out.flush();
        } catch (IOException e) {
            log.error("通讯员批量导入模板下载失败", e);
        } finally {
            IoUtil.close(out);
            IoUtil.close(fis);
            IoUtil.close(workbook);
        }
    }

    @ApiOperation("通讯员批量导入")
    @PostMapping("/importCorrespondent")
    public Mono<Response> importCorrespondent(@RequestParam("file") @ApiParam(value = "通讯员 excel") MultipartFile file) {
        return Mono.fromCallable(() -> {
            //根据文件名获取拓展名
            String fileName = file.getOriginalFilename();
            String extName = FileUtil.extName(fileName);
            // 文件格式不正确
            if (!StrUtil.equalsAnyIgnoreCase(extName, CorrespondentTypeEnum.XLS.getDesc())) {
                return ResponseBuilder.buildFail(ErrorEnum.FILE_FORMAT_NOT_CORRECT.getMsg());
            }
            List<CorrespondentExcel> importList = EasyExcel.read(new BufferedInputStream(file.getInputStream())).head(CorrespondentExcel.class).sheet().doReadSync();
            // 检验
            String valid = CorrespondentImportHelper.requiredValid(importList);
            if (StringUtils.isNotBlank(valid)) {
                return ResponseBuilder.buildFail(valid);
            }
            // 转换成对象集合
            List<Correspondent> correspondentList = CorrespondentImportHelper.getListByExcel(importList);
            correspondentService.add(correspondentList, TokenThreadLocal.getUserId());
            return ResponseBuilder.buildSuccess(correspondentList.size());
        });
    }

}
