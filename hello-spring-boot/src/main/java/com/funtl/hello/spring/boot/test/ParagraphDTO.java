package com.funtl.hello.spring.boot.test;

import lombok.Data;
import lombok.ToString;

/**
 * 图文转视频 文章段落类
 */
@Data
@ToString
public class ParagraphDTO {

    private String imgUrl;

    private String paragraphContent;

}
