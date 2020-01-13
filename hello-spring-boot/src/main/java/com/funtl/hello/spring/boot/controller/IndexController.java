package com.funtl.hello.spring.boot.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Controller
public class IndexController {

    @GetMapping(value = {"","index","/"})
//    @AccessLimit(seconds = 5, maxCount = 20, needLogin = true)
    public String index(Model model){
        model.addAttribute("message",null);
        return "login" ;
    }

    @GetMapping(value = {"people"})
//    @AccessLimit(seconds = 5, maxCount = 20, needLogin = true)
    public String vue(Model model){
        return "people";
    }

    @GetMapping(value = {"main"})
    public String main(Model model){
        return "main";
    }

    @RequestMapping("/upload/download")
    @ApiOperation(value = "/upload/download",tags = "下载")
    public ResponseEntity<byte[]> download(@RequestParam(value="type")@ApiParam(value = "type=0单张图片下载，type=1打包下载")int type
            , @RequestParam(value="pubUrl")@ApiParam(value = "公网地址，type=1时逗号隔开") String pubUrl){
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            String filename = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))
                    + (new Random()).nextInt(1000) + ".zip";
            if(type == 0){
                filename = filename.replace("zip", FilenameUtils.getExtension(pubUrl));
                headers.set("Content-Disposition","attachment;fileName="+ URLEncoder.encode(filename, "utf-8"));
                if(!StringUtils.startsWith(pubUrl, "可能的下载链接")&&!StringUtils.startsWith(pubUrl, "可能的下载链接")){
                    throw  new RuntimeException("不支持该图片下载");
                }
                URL url = new URL(pubUrl);
                return new ResponseEntity<>(IOUtils.toByteArray(url), headers, HttpStatus.CREATED);
            }else {
                headers.set("Content-Disposition","attachment;fileName="+ URLEncoder.encode(filename, "utf-8"));
                try(ByteArrayOutputStream byteStream = new ByteArrayOutputStream()){
                    try (ZipOutputStream zos = new ZipOutputStream(byteStream)){
                        String[] files = pubUrl.split(",");
                        for (String file : files) {
                            if(!StringUtils.startsWith(pubUrl, "可能的下载链接")&&!StringUtils.startsWith(pubUrl, "可能的下载链接")){
                                throw new RuntimeException("内含不支持下载的图片");
                            }
                            URL url = new URL(file);
                            zos.putNextEntry(new ZipEntry(file.substring(file.lastIndexOf("/"))));
                            try (InputStream fis = url.openConnection().getInputStream()) {
                                IOUtils.copy(fis, zos);
                            }
                        }
                    }
                    return new ResponseEntity<>(byteStream.toByteArray(), headers, HttpStatus.CREATED);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("下载资源失败");
        }
    }

}
