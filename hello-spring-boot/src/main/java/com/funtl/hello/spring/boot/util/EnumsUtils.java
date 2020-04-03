package com.funtl.hello.spring.boot.util;

import com.funtl.hello.spring.boot.enums.BootEnum;
import lombok.Data;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
@Data
public class EnumsUtils {

    @Data
    private static class EnumsMap {

        private Integer code;
        private String name;

    }

    public static List<EnumsMap> convert(String className) {
        String classString = BootEnum.class.getPackage().getName() + "." + className;
        List<EnumsMap> list = new ArrayList<>();
        try {
            Class<?> aClass = ClassUtils.getClass(classString);
            if (aClass.isEnum()) {
                EnumSet enums = EnumSet.allOf((Class<Enum>) aClass);
                enums.forEach(e -> {
                    try {
                        // fieldName 对应枚举类字段
                        Object code = FieldUtils.readDeclaredField(e, "code", true);
                        Object name = FieldUtils.readDeclaredField(e, "name", true);
                        EnumsMap emap = new EnumsMap();
                        emap.setCode(NumberUtils.toInt(code.toString()));
                        emap.setName(name.toString());
                        list.add(emap);
                    } catch (IllegalAccessException e1) {
                        e1.printStackTrace();
                    }
                });
            }
        } catch (Exception e) {

        }
        return list;
    }

}
