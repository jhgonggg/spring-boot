package com.funtl.hello.spring.boot.util;

import com.funtl.hello.spring.boot.enums.BootEnum;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class EnumsUtils {

    private static class EnumsMap {

        private Integer code;
        private String name;

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static List<EnumsMap> convert(String className) {
        String classString = BootEnum.class.getPackage().getName() + "." + className;
        List<EnumsMap> list = new ArrayList<>();
        try {
            Class<?> aClass = ClassUtils.getClass(classString);
            if (aClass.isEnum()) {
                Class<Enum> enumClass = (Class<Enum>) aClass;
                EnumSet enums = EnumSet.allOf(enumClass);
                enums.forEach(e -> {
                    try {
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
