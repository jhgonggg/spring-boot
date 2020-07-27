package com.funtl.hello.spring.boot.controller;

import com.funtl.hello.spring.boot.config.SouthcnException;
import com.funtl.hello.spring.boot.response.MsgCode;
import com.funtl.hello.spring.boot.response.Response;
import com.funtl.hello.spring.boot.response.ResponseBuilder;
import com.funtl.hello.spring.boot.util.qrcode.QrcodeUtils;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Objects;

/**
 * @author qy
 * @date 2020/7/27 11:34
 * @description
 */
@RestController
@Slf4j
public class QrCodeController {

    @ApiOperation(value = "生成二维码")
    @PostMapping("/createQrCode")
    /*
     *  ErrorCorrectionLevel
     *   L  7%  容错率
     *   M  15% 容错率
     *   Q  25% 容错率
     *   H  30% 容错率
     */

    public Mono<Response> createQrCode(@ApiParam(value = "url地址") String url, @ApiParam(value = "logo文件") MultipartFile logoFile, ErrorCorrectionLevel level) {
        try {
            System.out.println(level);
            Integer length = 280;
            byte[] data;
            if (Objects.nonNull(logoFile)) {
                //生成带logo的二维码
                data = QrcodeUtils.createQrcodeByLogo(url, length, logoFile.getBytes(), level, Boolean.TRUE);
            } else {
                //生成不带logo的二维码
                data = QrcodeUtils.createQrcodeByLevel(url, length, level, Boolean.TRUE);
            }
//            String pubUrl = MediaCenterServiceImpl.mediaCenterApi.uploadByByteArray(data, MediaType.Image, "jpg");
            String pubUrl = "二维码地址";
            return Mono.fromCallable(() -> ResponseBuilder.buildSuccess(pubUrl));
        } catch (IOException e) {
            log.error("生成二维码出错", e);
            throw new SouthcnException(MsgCode.FAIL, "生成二维码出错");
        }
    }
}
