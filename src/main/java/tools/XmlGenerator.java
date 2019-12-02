package tools;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.FileWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: M˚Haonan
 * @Date: 2019-09-09 11:15
 * @Description:
 * xml文件生成工具，为了方便eshopadmin中实体类生成xml文件
 */
public class XmlGenerator {
    /**
     * 要操作的对象类
     */
    private Class<?> obj;

    /**
     * 要操作的类名
     */
    private String className;

    /**
     * xml文件输出路径
     */
    private String outPath;

    /**
     * java类型和数据库映射关系
     */
    private Map<String, String> javaToJdbc;


    public void init(Class<?> obj, String outPath) {
        this.obj = obj;
        this.className = obj.getSimpleName();
        this.javaToJdbc = new HashMap<>();
        this.outPath = outPath;
        initMap();
    }

    private void initMap() {
        javaToJdbc.put("Integer", "TINYINT");
        javaToJdbc.put("String", "VARCHAR");
        javaToJdbc.put("Date", "DATETIME");
        javaToJdbc.put("Long", "INT");
    }

    public void out() {
        Field[] fields = obj.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getType().getSimpleName());
        }
        generateXml(fields);
    }

    private void generateXml(Field[] fields) {
        String lowClassName = StringUtils.firstLetterToLowCase(className);
        //创建一个文档对象
        Document doc = DocumentHelper.createDocument();
        //创建根节点
        Element root = doc.addElement("sqlMap");
        //设置根节点属性
        root.addAttribute("namespace", className);
        Element typeAlias1 = root.addElement("typeAlias");
        Element typeAlias2 = root.addElement("typeAlias");
        typeAlias1.addAttribute("alias", lowClassName);
        typeAlias1.addAttribute("type", "");
        typeAlias2.addAttribute("alias", lowClassName + "Criteria");
        typeAlias2.addAttribute("type", "");
        Element resultMap = root.addElement("resultMap");
        resultMap.addAttribute("id", lowClassName + "Result");
        resultMap.addAttribute("class", lowClassName);
        for (Field field : fields) {
            Element result = resultMap.addElement("result");
            result.addAttribute("column", StringUtils.camelToUnderline(field.getName()));
            result.addAttribute("property", field.getName());
            result.addAttribute("jdbcType", javaToJdbc.get(field.getType().getSimpleName()));
        }
        Element sql1 = root.addElement("sql");
        sql1.addAttribute("id", "criteriaWhereClause");
        Element dynamic = sql1.addElement("dynamic");
        dynamic.addAttribute("prepend", "where");
        for (Field field : fields) {
            Element isNotNull = dynamic.addElement("isNotNull");
            isNotNull.addAttribute("prepend", "and");
            isNotNull.addAttribute("property", field.getName());
            isNotNull.addAttribute("removeFirstPrepend", "true");
            isNotNull.addText(StringUtils.camelToUnderline(field.getName()) + " = #" + field.getName() + "#");
        }
        Element sql3 = root.addElement("sql");
        sql3.addAttribute("id", "selectSQL");
        String selectSql = generateSelectSql(fields);
        sql3.addCDATA(selectSql);
        Element insert = root.addElement("insert");
        insert.addAttribute("id", "insert");
        insert.addAttribute("parameterClass", lowClassName);
        String insertSql = generateInsertSql(fields);
        insert.addCDATA(insertSql);
        Element selectKey = insert.addElement("selectKey");
        selectKey.addAttribute("resultClass", "Long");
        selectKey.addAttribute("keyProperty", "id");
        selectKey.addText("select last_insert_id();");
        Element update = root.addElement("update");
        update.addAttribute("id", "update");
        update.addAttribute("parameterClass", lowClassName);
        update.addText("update " + StringUtils.camelToUnderline(StringUtils.firstLetterToLowCase(className)) + " ");
        Element dynamic2 = update.addElement("dynamic");
        dynamic2.addAttribute("prepend","set");
        for (Field field : fields) {
            Element isNotNull = dynamic2.addElement("isNotNull");
            isNotNull.addAttribute("prepend", ",");
            isNotNull.addAttribute("property", field.getName());
            isNotNull.addAttribute("removeFirstPrepend", "true");
            isNotNull.addText(StringUtils.camelToUnderline(field.getName()) + " = #" + field.getName() + "#");
        }
        OutputFormat format = OutputFormat.createPrettyPrint();
        //设置xml文档的编码为utf-8
        format.setEncoding("utf-8");
        Writer out;
        try {
            out = new FileWriter(outPath);
            XMLWriter writer = new XMLWriter(out, format);
            writer.write(doc);
            writer.close();
        }catch (Exception e) {
            System.out.println("生成失败");
        }

    }

    private String generateInsertSql(Field[] fields) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ").append(StringUtils.camelToUnderline(StringUtils.firstLetterToLowCase(className))).append(" (");
        for (Field field : fields) {
            sb.append(StringUtils.camelToUnderline(field.getName()) + ",");
        }
        sb.append(") VALUES (");
        for (Field field : fields) {
            sb.append("#");
            sb.append(field.getName());
            sb.append("#,");
        }
        sb.append(")");
        return sb.toString();
    }

    private String generateSelectSql(Field[] fields) {
        StringBuilder sb = new StringBuilder();
        sb.append("select \n");
        for (Field field : fields) {
            sb.append(StringUtils.camelToUnderline(field.getName()) + ",\n");
        }
        String substring = sb.substring(0, sb.lastIndexOf(","));
        return substring + " from " + StringUtils.camelToUnderline(StringUtils.firstLetterToLowCase(className));
    }


    public static void main(String[] args) {
        XmlGenerator xml = new XmlGenerator();
        xml.init(ShopOrderItem.class, "1.xml");
        xml.out();
    }
}
