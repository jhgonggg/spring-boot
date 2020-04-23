package com.funtl.hello.spring.boot.util;

import cn.hutool.core.util.RandomUtil;
import com.funtl.hello.spring.boot.constant.SysConst;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;


public final class CommonHelper {

    /**
     * 不能返回 Collections.emptyList()
     */
    private static final List<String> EMPTYLIST = Lists.newArrayList();
    private static final Set<String> EMPTYSET = Sets.newHashSet();

    /**
     * copy原有指挥报道系统， 用hutool稍加美化
     * @return 返回一串随机串
     */
    public static String getJmsId() {

        return String.valueOf(System.currentTimeMillis()) + RandomUtil.randomLong(100000, 999999);
    }

    public static Set<String> getSetBySplitter(String source) {
        return getSetBySplitter(source, SysConst.COMMA);
    }

    private static Set<String> getSetBySplitter(String source, String splitter) {
        return StringUtils.isBlank(source) ?
                EMPTYSET :
                new HashSet<>(Arrays.asList(StringUtils.indexOf(source, splitter) != -1
                        ? source.split(splitter) : new String[]{source}));
    }

    /**
     * 处理 ; 的情况, 前面为部门编码，后面为用户id, 处理前后空格
     * <pre>
     *     ;测试28  --> ["", "测试28"]
     *     1407;  --> ["1407", ""]
     *     1407;测试28  --> ["1407", "测试28"]
     *     1407;测试28,测试77  --> ["1407", "测试28,测试77"]
     * </pre>
     * @param source 输入字符串
     * @return 返回一个两个元素的列表，第一个为部门，第二个为具体某一个或者某些人
     */
    public static String[] getArrayBySplitter(String source) {
        String[] array = new String[2];
        if (StringUtils.isBlank(source) || StringUtils.equals(StringUtils.trim(source), SysConst.COLON)) {
            array[SysConst.ZERO] = StringUtils.EMPTY;
            array[SysConst.ONE] = StringUtils.EMPTY;
        } else if (StringUtils.startsWith(source, SysConst.COLON)) {
            array[SysConst.ZERO] = StringUtils.EMPTY;
            array[SysConst.ONE] = StringUtils.split(source, SysConst.COLON)[SysConst.ZERO];
        } else if (StringUtils.endsWith(source, SysConst.COLON)) {
            array[SysConst.ZERO] = StringUtils.split(source, SysConst.COLON)[SysConst.ZERO];
            array[SysConst.ONE] =  StringUtils.EMPTY;
        } else {
            array = StringUtils.split(StringUtils.trim(source), SysConst.COLON);
        }
        return array;
    }

    public static List<String> getListBySplitter(String source) {
        return getListBySplitter(source, SysConst.COMMA);
    }

    public static List<String> getListBySplitter(String source, String splitter) {
        return StringUtils.isBlank(source) ?
                EMPTYLIST :
                new ArrayList<>(
                        Arrays.asList(StringUtils.indexOf(source, splitter) != -1 ?
                                source.split(splitter) : new String[]{source})
                );
    }

    public static void main(String[] args) {
        System.out.println("--------------------------");
        String enName = "a;a;xinyu";
        List<String> strings = CommonHelper.getListBySplitter(enName,SysConst.COLON);
        System.out.println(strings);

        String member = "1,2,4";

        List<String> sss = CommonHelper.getListBySplitter(member,SysConst.COMMA);

        System.out.println(sss);

        System.out.println(StringUtils.join(sss, SysConst.COMMA));

        String newMan = sss.stream().filter(old -> !"2".equals(old)).map(old -> String.format("%s%s", SysConst.COMMA, old)).collect(Collectors.joining("", "111", ""));

        System.out.println(newMan);

        String newMan2 = sss.stream().filter(old -> !"2".equals(old)).map(old -> SysConst.COMMA + old).collect(Collectors.joining("", "111", ""));

        System.out.println(newMan2);
    }
}
