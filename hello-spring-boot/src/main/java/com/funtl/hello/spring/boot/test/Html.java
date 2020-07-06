package com.funtl.hello.spring.boot.test;

import org.apache.commons.collections4.CollectionUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author qy
 * @date 2020/7/6 14:34
 * @description
 */
public class Html {

    public static void main(String[] args) {
        String content = "<p>7月3日，记者获悉，近期，《佛山市公安机关警务辅助人员管理办法》经市政府审议通过，将从8月1日起施行。这是佛山首部专门针对辅警的管理办法，共七章四十七条，明确了辅警的职责权利和义务、招聘、管理与监督、待遇保障、法律责任等内容。</p>\n" +
                "<p class=\" summary_style\"><span style=\"color: rgb(0, 32, 96);\"><strong>辅警可以做什么？</strong></span></p>\n" +
                "<p><span style=\"color: rgb(0, 32, 96);\"><strong>14项工作可在民警带领下开展</strong></span></p>\n" +
                "<p>哪些事情辅警可以做？哪些不能做？《办法》一一做了规定。</p>\n" +
                "<p>根据《办法》，辅警分为文职辅警和勤务辅警。</p>\n" +
                "<p>市民日常接触较多的勤务辅警，从事协助民警执法执勤等警务工作，包括预防、制止违法犯罪活动；接受群众求助，依法调解民事纠纷；治安巡逻、值守；在车站、机场、码头等人员聚集场所开展安全巡查；维护案（事）件现场秩序，保护案（事）件现场，救助受伤受困人员；疏导交通，劝阻交通安全违法行为，采集交通违法信息；开展社会治安、交通安全、禁毒等宣传教育等。</p>\n" +
                "<p><img title=\"疫情期间，警辅人员参与疫情防控工作\" contenteditable=\"false\" imgtitleid=\"1595227906288\" data_ue_src=\"../../xy/image.do?path=空&amp;mediaId=6b75204ec18c4f808fe8fa3160c9a65d_cut_batchwm.jpg\" data-width=\"748\" data-height=\"485\" data-original=\"https://pic.nfapp.southcn.com/nfplus/ossfs/pic/xy/202007/03/6b75204ec18c4f808fe8fa3160c9a65d_cut_batchwm.jpg?x-oss-process=style/w640\" src=\"https://static.nfapp.southcn.com/apptpl/img/poster.png\" class=\"lazyload\" onerror=\"https://static.nfapp.southcn.com/apptpl/img/poster.png\" style=\"height: auto;\"><span class=\"spanPicTitle\">疫情期间，警辅人员参与疫情防控工作</span></p>\n" +
                "\n" +
                "<p>根据公安机关的安排，勤务辅警可以在民警的带领下从事以下执法相关辅助工作：包括接报警控制现场、了解情况、疏导劝阻、平息事态、调节矛盾、现行堵控、现场救助、查找现场视频监控等现场处置；案事件现场秩序维护、人员隔离、犯罪制止、伤员救治等现场处置；对行为举止失控的醉酒人员、实施暴力行为的精神病人采取临时保护性约束措施。</p>\n" +
                "<p>此外勤务辅警还可以从事涉案财物管理工作；驯养、使用警犬；盘查、堵控、监控、看管违法犯罪嫌疑人；社会治安信息采集和人员身份信息核录；记录讯问、询问笔录；看守所、拘留所、强制隔离戒毒所、执法办案区等场所的管理；维护大型公共活动秩序；社区管理、特种行业管理、养犬管理等公安行政管理活动；开展戒毒人员日常管理、检查易制毒化学品企业、公开查缉毒品；治安检查；驾驶警用车辆、船艇、航空器等警用交通工具。</p>\n" +
                "<p class=\" summary_style\"><span style=\"color: rgb(0, 32, 96);\"><strong>如何成为辅警？</strong></span></p>\n" +
                "<p><span style=\"color: rgb(0, 32, 96);\"><strong>年满18周岁，至少具有高中学历</strong></span></p>\n" +
                "<p>《办法》指出，辅警的招聘由市、区公安机关组织实施，向社会公示，按照报名、资格审查、笔试、面试、体能测评、体检、政治考察、公示、签订劳动合同等程序实施。体能测评、体检参照民警体能测评、体检标准。</p>\n" +
                "<p>成为一名辅警，需要年满18周岁，勤务辅警应当具有高中以上文化程度，文职辅警应当具有大专以上文化程度。</p>\n" +
                "<p>受过刑事处罚或者涉嫌违法犯罪尚未查清的；曾因吸毒、嫖娼、赌博受到处罚的；曾被行政拘留、司法拘留或者收容教育的；曾被吊销律师、公证员执业证书的；曾被国家机关、事业单位开除公职或者因违纪违规被辞退解聘的；曾被开除军籍的；曾因违反公安机关辅警管理规定被解聘的；有较为严重个人不良信用记录的，不得从事辅警工作。</p>\n" +
                "<p><img title=\"疫情期间，警辅人员参与疫情防控工作\" contenteditable=\"false\" imgtitleid=\"1602693628186\" data_ue_src=\"../../xy/image.do?path=空&amp;mediaId=365786553e9d471c8fb95adef1673a1e_cut_batchwm.jpg\" data-width=\"732\" data-height=\"484\" data-original=\"https://pic.nfapp.southcn.com/nfplus/ossfs/pic/xy/202007/03/365786553e9d471c8fb95adef1673a1e_cut_batchwm.jpg?x-oss-process=style/w640\" src=\"https://static.nfapp.southcn.com/apptpl/img/poster.png\" class=\"lazyload\" onerror=\"https://static.nfapp.southcn.com/apptpl/img/poster.png\" style=\"height: auto;\"><span class=\"spanPicTitle\">疫情期间，警辅人员参与疫情防控工作</span></p>\n" +
                "\n" +
                "<p class=\" summary_style\"><span style=\"color: rgb(0, 32, 96);\"><strong>待遇保障如何？</strong></span></p>\n" +
                "<p><span style=\"color: rgb(0, 32, 96);\"><strong>优秀辅警报考民警，给予适当照顾</strong></span></p>\n" +
                "<p>在辅警的待遇方面，《办法》也做出了详细的规定。</p>\n" +
                "<p>据介绍，辅警薪酬待遇标准由市、区公安机关会同同级财政部门、承担工资管理职能的部门，参照当地上年度在岗职工平均工资，结合当地经济社会发展水平和财力状况自行核定，并建立动态调整机制。</p>\n" +
                "<p>根据《办法》，辅警所属单位应当依法为辅警办理基本养老、基本医疗、工伤、失业、生育等社会保险，缴存住房公积金。</p>\n" +
                "<p>辅警所属单位还应当根据工作岗位的危险性或者特殊性，为辅警购买包含附加意外伤害医疗险、意外伤害住院保障险的人身意外伤害保险，并每年组织辅警参加健康检查，建立辅警健康档案。</p>\n" +
                "<p>经同级财政部门同意，市、区公安机关可以设立抚恤金专项经费，对因训练、执勤以及抢险救灾等受伤、致残、死亡的辅警或者其直系亲属予以适当补助。</p>\n" +
                "<p><img title=\"疫情期间，警辅人员参与疫情防控工作\" contenteditable=\"false\" imgtitleid=\"1602730332331\" data_ue_src=\"../../xy/image.do?path=空&amp;mediaId=a20b582c817843c4a37cd17cc4ff3796_cut_batchwm.jpg\" data-width=\"748\" data-height=\"485\" data-original=\"https://pic.nfapp.southcn.com/nfplus/ossfs/pic/xy/202007/03/a20b582c817843c4a37cd17cc4ff3796_cut_batchwm.jpg?x-oss-process=style/w640\" src=\"https://static.nfapp.southcn.com/apptpl/img/poster.png\" class=\"lazyload\" onerror=\"https://static.nfapp.southcn.com/apptpl/img/poster.png\" style=\"height: auto;\"><span class=\"spanPicTitle\">疫情期间，警辅人员参与疫情防控工作</span></p>\n" +
                "\n" +
                "<p>辅警因工受伤、致残的，依法享受工伤待遇。</p>\n" +
                "<p>辅警因公牺牲、因工死亡的，其近亲属依照规定享受相关的抚恤和待遇。被评定为革命烈士的，其遗属依照革命烈士褒扬有关规定享受相关抚恤待遇。</p>\n" +
                "<p>市、区公安机关和其他有关部门对在保护公共财产和人民群众生命财产安全、预防和制止违法犯罪等工作中表现突出，有显著成绩或者特殊贡献的辅警，应当按照国家有关规定给予表彰奖励。</p>\n" +
                "<p>此外，《办法》还专门提到，特别优秀的辅警报考公安机关民警职位的，应当给予适当照顾。</p>\n" +
                "<p>【南方日报记者】夏小荔</p>\n" +
                "<p>【实习生】胡一玮</p>\n" +
                "<p>【通讯员】佛公选</p>\n" +
                "<p>佛山市公安局供图</p>\n";

        List<ParagraphDTO> paragraphContent = getParagraphContent(content);
        System.out.println(paragraphContent);

    }

    private static List<ParagraphDTO> getParagraphContent(String content){
        Document doc = Jsoup.parse(content);
        Elements elements = doc.getElementsByTag("p");
        elements.add(doc.createElement("p").attr("class","summary_style"));
        boolean flag = false;
        List<Elements> lists = new ArrayList<>();
        Elements newElement = null;
        for (Element element : elements) {
            Elements strong = element.select("strong");
            boolean hasClass = element.hasClass("summary_style");
            if (strong.size() == 0 && !hasClass) {
                if (flag) {
                    newElement.add(element);
                }
            } else {
                flag = true;
                if (CollectionUtils.isNotEmpty(newElement)) {
                    lists.add(newElement);
                }
                newElement = new Elements();
            }
        }
        Iterator iterator = lists.iterator();
        List<ParagraphDTO> paragraphDTOList = new ArrayList<>();
        while (iterator.hasNext()) {
            Elements elementList = (Elements) iterator.next();
            Elements imgElements = elementList.select("img");
            if (imgElements.size() > 0) {
                ParagraphDTO paragraphDTO = new ParagraphDTO();
                String text = elementList.text();
                String src = imgElements.attr("src");
                paragraphDTO.setParagraphContent(text);
                paragraphDTO.setImgUrl(src);
                paragraphDTOList.add(paragraphDTO);
            }
        }
        return paragraphDTOList;
    }
}
