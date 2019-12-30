package com.funtl.hello.spring.boot.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Objects;

/**
 * url转码、解码
 *
 */
@Slf4j
public class UrlUtil {

	private final static String ENCODE = "GBK";

	/**
	 * URL 解码
	 */
	public static String getURLDecoderString(String str) {
		String result = StringUtils.EMPTY;
		if (Objects.isNull(str)) {
			return StringUtils.EMPTY;
		}
		try {
			result = URLDecoder.decode(str, ENCODE);
		} catch (UnsupportedEncodingException e) {
			log.error("URL解码失败 ex={}", e.getMessage(), e);
		}
		return result;
	}

	/**
	 * URL 转码
	 */
	public static String getURLEncoderString(String str) {
		String result = StringUtils.EMPTY;
		if (Objects.isNull(str)) {
			return StringUtils.EMPTY;
		}
		try {
			result = URLEncoder.encode(str, ENCODE);
		} catch (UnsupportedEncodingException e) {
			log.error("URL转码失败 ex={}", e.getMessage(), e);
		}
		return result;
	}
}