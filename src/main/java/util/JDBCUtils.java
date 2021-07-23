package util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.mysql.cj.protocol.Resultset;
import json.ticketJSON;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class JDBCUtils {

    //声明静态数据源成员变量datasource
    private static DataSource dataSource;

    //创建连接池对象
    static {
        try {
            //创建properties对象
            Properties properties = new Properties();
            //将配置文件转换成字节输入流
            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            //使用properties对象加载is
            properties.load(is);
            //druid底层是使用的工厂设计模式，去加载配置文件，创建DruidDataSource对象
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 定义公有的得到数据源的方法
     * @return
     */
    public static DataSource dataSource(){
        return dataSource;
    }

    /**
     * 定义得到连接对象的方法
     * @return
     */
    public static Connection getconn(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 执行更新语句
     * @param sql 更新语句
     * @param params 语句参数
     * @return 执行结果：false--> 执行失败；true--> 执行成功
     */
    public static boolean sqlUpdate(String sql, List<?> params){
        int result = 0;
        PreparedStatement pstmt = null;
        Connection conn = null;
        try {
            conn = JDBCUtils.getconn();
            pstmt = conn.prepareStatement(sql);
            int index = 1;
            result = -1;
            if (params != null && !params.isEmpty()){
                for (Object param : params){
                    pstmt.setObject(index++,param);
                }
            }
            result=pstmt.executeUpdate();
        } catch (SQLException e) {
                e.printStackTrace();
        }finally {
            JDBCUtils.close(pstmt,conn);
        }
        return result > -1;
    }

    /**
     * 执行查询语句
     * @param sql 查询语句
     * @param params 语句参数
     * @return 执行结果集转换为JSON格式
     */
    public static String sqlQueryJSON(String sql,List<String> params){
        ResultSet res = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = JDBCUtils.getconn();
            pstmt = conn.prepareStatement(sql);
            int index= 1;
            if (params != null && !params.isEmpty()){
                for (Object param :params){
                    pstmt.setObject(index++,param);
                }
            }
            res = pstmt.executeQuery();
            return ticketJSON.resultSetJSONArr(res);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(res,pstmt,conn);
        }
        return null;
    }

    /**
     * 执行查询语句
     * @param sql 查询语句
     * @param params 语句参数
     * @param x
     * @return 返回单结果
     */
    public static String sqlQueryJSON(String sql,List<?> params,int x){
        ResultSet res = null;
        PreparedStatement pstmt = null;
        Connection conn = null;
        try {
            conn = JDBCUtils.getconn();
            pstmt = conn.prepareStatement(sql);
            int index = 1;
            if(params != null && !params.isEmpty()){
                for (Object param : params){
                    pstmt.setObject(index++,param);
                }
            }
            res = pstmt.executeQuery();
            if (res.next()){
                return res.getString(x);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(res,pstmt,conn);
        }
        return null;
    }

    /**
     * 资源释放
     * @param pstmt
     * @param conn
     */
    private static void close(PreparedStatement pstmt, Connection conn) {
        if (pstmt != null){
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 资源释放
     * @param res
     * @param pstmt
     * @param conn
     */
    private static void close(ResultSet res, PreparedStatement pstmt, Connection conn) {
        if (res != null){
            try {
                res.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (pstmt != null){
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
