package spider.test;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import spider.parse.ParseHtml;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: M˚Haonan
 * @Date: 2020-06-19 18:57
 * @Description: 解析csdn后缀名 https://blog.csdn.net/fengdijiang/article/details/101348901
 */
public class ParseSuffix {


    public void parse(File file) throws Exception{
        ParseHtml parseHtml = new ParseHtml();
        Document document = parseHtml.parseFile(file);
        String test = "SUFFIX_MAP.put(\"image/tiff\", \".tif\");";
        List<String> resut = new ArrayList<>();
        Elements elements = parseHtml.selectList(document, "//table/tbody/tr");
        for (Element element : elements) {
            String res = "SUFFIX_MAP.put(\"" +  parseHtml.selectText(element, "td[2]").replace("<td>","").replace("</td>","") + "\", \"" + parseHtml.selectText(element, "td[1]").replace("<td>","").replace("</td>","") + "\");";
            resut.add(res);
            res = "SUFFIX_MAP.put(\"" +  parseHtml.selectText(element, "td[4]").replace("<td>","").replace("</td>","") + "\", \"" + parseHtml.selectText(element, "td[3]").replace("<td>","").replace("</td>","") + "\");";
            resut.add(res);
        }
        resut.forEach(l -> {
            System.out.println(l);
        });
    }

    public static void main(String[] args) throws Exception{
        File file = new File("/Users/mahaonan/Downloads/神兵阁.html");
        ParseSuffix suffix = new ParseSuffix();
        suffix.parse(file);
    }
}
