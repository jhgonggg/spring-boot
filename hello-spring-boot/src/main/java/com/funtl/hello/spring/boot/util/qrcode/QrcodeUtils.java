package com.funtl.hello.spring.boot.util.qrcode;

import cn.hutool.extra.qrcode.BufferedImageLuminanceSource;
import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author qy
 * @date 2020/7/27 11:24
 * @description
 */
public class QrcodeUtils {

    private static Log logger = LogFactory.getLog(QrcodeUtils.class);

    private static final int DEFAULT_LENGTH = 400;// 生成二维码的默认边长，因为是正方形的，所以高度和宽度一致
    public static final String FORMAT = "jpg";// 生成二维码的格式


    /**
     * 根据指定边长创建生成的二维码
     *
     * @param content
     * @param length
     * @param level
     * @return
     */
    public static byte[] createQrcodeByLevel(String content, Integer length, ErrorCorrectionLevel level, Boolean deleteWhite) {
        BitMatrix bitMatrix = createQrcodeMatrix(content, length, level, deleteWhite);
        byte[] bytes = null;
        try {
            bytes = MatrixToImageWriter.writeToBytes(bitMatrix, FORMAT);
        } catch (Exception e) {
            logger.error(e);
        }
        return bytes;
    }

    public static byte[] createQrcodeByLevel(String content, Integer length, ErrorCorrectionLevel level) {
        return createQrcodeByLevel(content, length, level, Boolean.FALSE);
    }

    public static byte[] createQrcode(String content, Integer length) {
        return createQrcodeByLevel(content, length, ErrorCorrectionLevel.L);
    }


    public static byte[] createQrcode(String content, Integer length, Boolean deleteWhite) {
        return createQrcodeByLevel(content, length, ErrorCorrectionLevel.L, deleteWhite);
    }

    /**
     * 创建25%的容错率二维码
     *
     * @param content
     * @param length
     * @param deleteWhite
     * @return
     */
    public static byte[] createMQrcode(String content, Integer length, Boolean deleteWhite) {
        return createQrcodeByLevel(content, length, ErrorCorrectionLevel.M, deleteWhite);
    }

    public static byte[] createQrcodeByLogo(String content, Integer length, byte[] logoFile, ErrorCorrectionLevel level, Boolean deleteWhite) {
        return drawLogo(createQrcodeByLevel(content, length, level, deleteWhite), logoFile);
    }

    public static byte[] createQrcodeByLogo(String content, Integer length, byte[] logoFile, ErrorCorrectionLevel level) {
        return drawLogo(createQrcodeByLevel(content, length, level), logoFile);
    }

    public static byte[] createQrcodeByLogo(String content, Integer length, byte[] logoFile) {
        return createQrcodeByLogo(content, length, logoFile, ErrorCorrectionLevel.M);
    }

    /**
     * 根据指定边长创建生成的二维码
     *
     * @param content  二维码内容
     * @param length   二维码的高度和宽度
     * @param logoFile logo 文件对象，可以为空
     * @return 二维码图片的字节数组
     */
    @Deprecated
    public static byte[] createQrcode(String content, Integer length, byte[] logoFile) {
        return drawLogo(createQrcodeByLevel(content, length, ErrorCorrectionLevel.H), logoFile);
    }

    /**
     * 创建生成默认高度(400)的二维码图片
     * 可以指定是否贷logo
     *
     * @param content  二维码内容
     * @param logoFile logo 文件对象，可以为空
     * @return 二维码图片的字节数组
     */
    @Deprecated
    public static byte[] createQrcode(String content, byte[] logoFile) {
        return createQrcode(content, DEFAULT_LENGTH, logoFile);
    }


    /**
     * 解析二维码
     *
     * @param bytes 二维码文件内容
     * @return 二维码的内容
     */
    public static String decodeQrcode(byte[] bytes) throws Exception {
        BufferedImage image = ImageIO.read(new ByteArrayInputStream(bytes));
        LuminanceSource source = new BufferedImageLuminanceSource(image);
        Binarizer binarizer = new HybridBinarizer(source);
        BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
        Map<DecodeHintType, Object> hints = new HashMap<>();
        hints.put(DecodeHintType.CHARACTER_SET, StandardCharsets.UTF_8);
        return new MultiFormatReader().decode(binaryBitmap, hints).getText();
    }


    /**
     * 将logo添加到二维码中间
     *
     * @param image      生成的二维码图片对象
     * @param logoFile   logo文件对象
     * @param logoConfig
     * @return
     * @throws IOException
     */
    private static byte[] overlapImage(BufferedImage image, byte[] logoFile, MatrixToLogoImageConfig logoConfig) throws IOException {
        try {
            BufferedImage logo = ImageIO.read(new ByteArrayInputStream(logoFile));
            Graphics2D g = image.createGraphics();
            // 考虑到logo图片贴到二维码中，建议大小不要超过二维码的1/5;
            int width = image.getWidth() / logoConfig.getLogoPart();
            int height = image.getHeight() / logoConfig.getLogoPart();
            // logo起始位置，此目的是为logo居中显示
            int x = (image.getWidth() - width) / 2;
            int y = (image.getHeight() - height) / 2;
            // 绘制图
            g.drawImage(logo, x, y, width, height, null);
            // 给logo画边框
            // 构造一个具有指定线条宽度以及 cap 和 join 风格的默认值的实心 BasicStroke
            g.setStroke(new BasicStroke(logoConfig.getBorder()));
            g.setColor(logoConfig.getBorderColor());
            //g.drawRect(x, y, width, height);
            g.dispose();
            // 写入logo图片到二维码
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ImageIO.write(image, FORMAT, out);
            return out.toByteArray();
        } catch (Exception e) {
            throw new IOException("二维码添加logo时发生异常！", e);
        }
    }

    /**
     * 将文件转换为字节数组，
     * 使用MappedByteBuffer，可以在处理大文件时，提升性能
     *
     * @param file 文件
     * @return 二维码图片的字节数组
     */
    private static byte[] toByteArray(File file) {
        try (FileChannel fc = new RandomAccessFile(file, "r").getChannel();) {
            MappedByteBuffer byteBuffer = fc.map(MapMode.READ_ONLY, 0, fc.size()).load();
            byte[] result = new byte[(int) fc.size()];
            if (byteBuffer.remaining() > 0) {
                byteBuffer.get(result, 0, byteBuffer.remaining());
            }
            return result;
        } catch (Exception e) {
            logger.warn("文件转换成byte[]发生异常！", e);
            return null;
        }
    }

    /**
     * 根据内容生成二维码数据
     *
     * @param content 二维码文字内容[为了信息安全性，一般都要先进行数据加密]
     * @param length  二维码图片宽度和高度
     */
    private static BitMatrix createQrcodeMatrix(String content, int length, ErrorCorrectionLevel errorCorrectionLevel, Boolean deleteWhite) {
        Map<EncodeHintType, Object> hints = new HashMap<>();
        // 设置字符编码
        hints.put(EncodeHintType.CHARACTER_SET, StandardCharsets.UTF_8);
        // 指定纠错等级
        hints.put(EncodeHintType.ERROR_CORRECTION, errorCorrectionLevel);
        try {
            return deleteWhite ? deleteWhite(new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, length, length, hints)) : new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, length, length, hints);
        } catch (Exception e) {
            logger.warn("内容为：【" + content + "】的二维码生成失败！", e);
            return null;
        }
    }

    private static BitMatrix deleteWhite(BitMatrix matrix) {
        int[] rec = matrix.getEnclosingRectangle();
        int resWidth = rec[2] + 1;
        int resHeight = rec[3] + 1;
        BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);
        resMatrix.clear();
        for (int i = 0; i < resWidth; i++) {
            for (int j = 0; j < resHeight; j++) {
                if (matrix.get(i + rec[0], j + rec[1])) {
                    resMatrix.set(i, j);
                }
            }
        }
        return resMatrix;
    }

    /**
     * 画logo
     *
     * @param bytes
     * @param logoFile
     * @return
     */
    private static byte[] drawLogo(byte[] bytes, byte[] logoFile) {
        try {
            if (logoFile != null) {
                // 添加logo图片, 此处一定需要重新进行读取，而不能直接使用二维码的BufferedImage 对象
                BufferedImage img = ImageIO.read(new ByteArrayInputStream(bytes));
                bytes = overlapImage(img, logoFile, new MatrixToLogoImageConfig());
            }
            return bytes;
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }
}
