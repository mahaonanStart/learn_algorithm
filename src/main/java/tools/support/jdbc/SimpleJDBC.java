package tools.support.jdbc;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: M˚Haonan
 * @Date: 2020-06-18 17:29
 * @Description: 使用原生jdbc快速连接数据库
 */
@Slf4j
public class SimpleJDBC {

    @Setter
    private String driver = "com.mysql.jdbc.Driver";

    @Setter
    private String url;

    @Setter
    private String username;

    @Setter
    private String password;

    private Connection connection;

    public SimpleJDBC(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public void connection() {
        try {
            Class.forName(driver);
            this.connection =  DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            log.info("driver: {} 未找到", this.driver);
        } catch (SQLException e) {
            log.info("数据库连接失败");
        }
    }

    public void insert(String tableName, Class<?> cls, List dataList) {
        String sql = "insert into " + tableName + " values (null, ";
        Field[] fields = cls.getFields();

    }
}
