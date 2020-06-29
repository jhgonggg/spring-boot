package com.funtl.hello.spring.boot.controller;

import com.funtl.hello.spring.boot.response.ResponseBuilder;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Controller
@Slf4j
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

    @GetMapping("/upload/downloadVideo")
    @ApiOperation(value = "工作流视频下载")
    public ResponseEntity<byte[]> downloadVideo(@RequestParam @ApiParam(value = "下载文件名") String filename,
                                                @RequestParam(value="mediaId") String mediaId){
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.set("Content-Disposition","attachment;fileName="+ URLEncoder.encode(filename, "utf-8"));
            URL url = new URL("用 mediaId 获取的视频地址");
            return new ResponseEntity<>(IOUtils.toByteArray(url), headers, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException("下载视频失败");
        }
    }

    /**
     *  OkHttpClient 上传文件 、测试
     * @param file
     * @throws IOException
     */
//    @GetMapping(value = "upload")
//    @ResponseBody
//    public void testUpload(MultipartFile file) throws IOException {
//        OkHttpClient client = new OkHttpClient();
//        String api = "/down";
//        String url = String.format("%s%s", "http://127.0.0.1:9090", api);
//        System.out.println(file.getName());  // file
//        System.out.println(file.getContentType()); // vedio/mp4 、 image/jpn
//        RequestBody requestBody = null;
//        try {
//            requestBody = new MultipartBody.Builder()
//                    .setType(MultipartBody.FORM)
//                    .addFormDataPart(
//                            file.getName(),
//                            file.getOriginalFilename(),
//                            RequestBody.create(file.getBytes(),
//                                    okhttp3.MediaType.parse(org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE)))
//                    .build();
//        } catch (IOException e) {
//            log.error("文件转换 byte 错误,e->{}", e.getMessage(), e);
//        }
//        Request request = new Request.Builder()
//                .url(url)
//                //默认为 GET 请求，可以不写
//                .post(Objects.requireNonNull(requestBody))
//                .build();
//        final Call call = client.newCall(request);
//        Response response = call.execute();
//        System.out.println(Objects.requireNonNull(response.body()).string());
//    }

    /**
     *  接收图片
     * @param file
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/down")
    public Mono<com.funtl.hello.spring.boot.response.Response> downLoad(MultipartFile file){
        return Mono.fromCallable(() -> {
            String name = file.getOriginalFilename();
            System.out.println(name);
            return ResponseBuilder.buildSuccess();
        });
    }

}
