package com.funtl.hello.spring.boot.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


@Configuration
public class WebAppConfiguration implements WebMvcConfigurer {

    private static final String[] excludePathPatterns = new String[]{"/", "/error", "/csrf", "/swagger-ui.html/**",
                                    "/webjars/springfox-swagger-ui/**", "/swagger-resources/**"};

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(excludePathPatterns);
    }

    /**
     * 跨域
     */

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        String[] headers = {"token","Origin", "X-Requested-With", "Content-Type", "Accept", "Content-Length", "Authorization","Content-disposition"};
        String[] methods = {"GET", "POST", "PUT","OPTIONS"};
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .exposedHeaders(headers)
                .allowCredentials(Boolean.TRUE)
                .allowedMethods(methods);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.clear();
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        ByteArrayHttpMessageConverter byteArrayHttpMessageConverter = new ByteArrayHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();

        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat, SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteDateUseDateFormat,
                SerializerFeature.DisableCircularReferenceDetect);
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastMediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
        fastMediaTypes.add(new MediaType("application","vnd.spring-boot.actuator.v2+json"));

        fastConverter.setSupportedMediaTypes(fastMediaTypes);
        fastConverter.setFastJsonConfig(fastJsonConfig);

        converters.add(byteArrayHttpMessageConverter);

        converters.add(stringHttpMessageConverter);
        converters.add(fastConverter);
    }
}

