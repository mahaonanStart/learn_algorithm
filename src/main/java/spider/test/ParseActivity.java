package spider.test;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import spider.parse.ParseHtml;
import tools.Encodes;
import us.codecraft.xsoup.XElements;
import us.codecraft.xsoup.Xsoup;

import java.io.File;
import java.util.*;

/**
 * @Author: M˚Haonan
 * @Date: 2020-06-18 14:01
 * @Description: 解析剑灵神器阁网站
 */
public class ParseActivity {

    private Map<Integer, String> stringMap = new LinkedHashMap<>();

    public ParseActivity() {
        stringMap.put(0, "无战力值要求");
        stringMap.put(1, "战力值达3万");
        stringMap.put(2, "战力值达5万");
        stringMap.put(3, "战力值达7万");
    }

    public void parse() throws Exception{
        Map<String, List<Item>> map = new LinkedHashMap<>();
        File file = new File("/Users/mahaonan/Downloads/神兵阁.html");
        ParseHtml parse = new ParseHtml();
        Document document = parse.parseFile(file);
        Elements elements = parse.selectList(document, "//div[@class='part3_cont_item']");
        int i = 0;
        for (Element element : elements) {
            List<Item> list = new ArrayList<>();
            map.put(stringMap.get(i ++) , list);
            Elements e = parse.selectList(element, "/ul/li");
            for (Element d : e) {
                Item item = new Item();
                item.setName(parse.selectText(d, "/div[@class='part3_prop_pic']/p/text()"));
                item.setStar(parse.selectText(d, "/div[@class='part3_prop_btns']/a[1]/text()"));
                item.setNumber(parse.selectText(d, "/div[@class='part3_prop_btns']/a[2]/text()"));
                item.setTimes(parse.selectText(d, "/div[@class='page_spr part3_prop_tips']/p/text()"));
                list.add(item);
            }
        }
        parse.saveAsExcel("/Users/mahaonan/mhn/test", "神兵阁1", Item.class, map);
    }

    public static void main(String[] args) throws Exception{
        ParseActivity activity = new ParseActivity();
        activity.parse();
    }


    @Data
    public static class Item {
        @ExcelProperty("名称")
        private String name;

        @ExcelProperty("微光数")
        private String star;

        @ExcelProperty("战力值")
        private String number;

        @ExcelProperty("兑换次数")
        private String times;

    }
}
