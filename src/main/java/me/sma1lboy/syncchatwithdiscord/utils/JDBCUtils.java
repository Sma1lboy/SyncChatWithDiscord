package me.sma1lboy.syncchatwithdiscord.utils;
import java.sql.*;
import java.util.Properties;

/**
 * operation DB util.
 * @author jacksonchen
 */
public class JDBCUtils {

    public static String prefixUrl;
    /**
     * get connection from db and get info from config
     * @return connection of the db
     * @throws Exception if connection is null
     */
    public static Connection getConnection() throws Exception {
        //get info from config
        Properties properties = new Properties();
        properties.load(JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties"));
        String prefixUrl = properties.getProperty("prefixUrl");
        String suffixUrl = properties.getProperty("suffixUrl");
        String driver = properties.getProperty("driver");

        //2. loading class
        Class.forName(driver);

        //3. getConnection
        return DriverManager.getConnection(prefixUrl + suffixUrl);
    }
    /**
     * Close connection and statement.
     * @param con connection
     * @param ps statement
     */
    public static void closeResource(Connection con, Statement ps){
        try {
            if (con != null) {
                con.close();
            }
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void closeResource(Connection con, Statement ps, ResultSet rs){
        try {
            if (con != null) {
                con.close();
            }
            if (ps != null) {
                ps.close();
            }
            if(rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
