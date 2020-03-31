package com.funtl.hello.spring.boot.test;

import cn.hutool.http.HtmlUtil;
import com.funtl.hello.spring.boot.constant.SysConst;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.Optional;

/**
 * @author qy
 * @date 2020/3/31 17:45
 * @description
 */
public class HuToolHtmlTest {


    public static void main(String[] args) {
        String content = "看合适的开发<img src=\"https://tpc.googlesyndication.com/simgad/15093394718881671873?sqp=4sqPyQQ7QjkqNxABHQAAtEIgASgBMAk4A0DwkwlYAWBfcAKAAQGIAQGdAQAAgD-oAQGwAYCt4gS4AV_FAS2ynT4&amp;rs=AOga4qnpq-ECRR85tC0pweVo1PZFW-4mkg\" border=\"0\" width=\"300\" alt=\"\" class=\"img_ad\">哈萨克地方三房空间的粉红色积分兑换扣三分们宣传部是明细账本V型名称vif大使馆已U盾市<img src=\"https://tpc.googlesyndication.com/simgad/15093394718881671873?sqp=4sqPyQQ7QjkqNxABHQAAtEIgASgBMAk4A0DwkwlYAWBfcAKAAQGIAQGdAQAAgD-oAQGwAYCt4gS4AV_FAS2ynT4&amp;rs=AOga4qnpq-ECRR85tC0pweVo1PZFW-4mkg\" border=\"0\" width=\"300\" alt=\"\" class=\"img_ad\" pic-id = \"1231\">分公司的";

        Document doc = Jsoup.parse(content);
        doc.outputSettings(new Document.OutputSettings().prettyPrint(false));
        System.out.println(doc);
        Elements elements = doc.getElementsByTag("img");
        System.out.println(elements);
        System.out.println(elements.size());
        Optional.of(elements).ifPresent(e->{
            e.forEach(element -> {
                String picId = element.attr(SysConst.PIC_ID);
                System.out.println(picId);
                element.attr(SysConst.PIC_SRC, "222222222");
                element.removeAttr(SysConst.PIC_ID);
                System.out.println(element);
                element.remove();
                System.out.println(element);
            });
        });
        System.out.println(elements);

        String unescape = HtmlUtil.unescape(doc.getElementsByTag(SysConst.BODY).html());

        System.out.println(unescape);
    }

    public void assembleHtml(String htmlContent) {
        Document doc = Jsoup.parse(htmlContent);
        // 禁止格式化文档元素
        doc.outputSettings(new Document.OutputSettings().prettyPrint(false));
        Elements img = doc.getElementsByTag("img");

    }
}
