package dbpool.demo;

import com.cangoframework.dbpool.DBHelper;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by cango on 2018/1/5.
 */
public class MainTest {

    @Test
    public void testConn() {
        try {
            Connection connection = DBHelper.getConnection("local");
            System.out.println(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSelect() {
        try {
            List<Map<String, Object>> listArray = DBHelper.getListMap("select * from user");
            System.out.println(listArray);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}