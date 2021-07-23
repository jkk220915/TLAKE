package dao;

import util.JDBCUtils;

import java.util.Arrays;
import java.util.List;

public class UserDao {

    /**
     * 验证登录身份
     * @param username
     * @param password
     * @return
     */
    public static String loginVerify(String username,String password){
        String sql = "select isAdmin from employee where name=? and password=?";
        String res=JDBCUtils.sqlQueryJSON(sql, Arrays.asList(username,password),1);
        if (res == null){
            return "fail";
        }else if (res.equals("1")){
            return "admin";
        }else {
            return "employee";
        }
    }

    /**
     * 根据员工在职状态查询员工
     * @param type
     * @return
     */
     public static String getEmpInfo(String type){
         String sql;
         if (type.equals("")){
            sql = "select name,employeeId,gender,age,status from employee where isAdmin != true and status != '在职' order by status";
        }else {
            sql = "select name,employeeID,gender,age,status from employee where isAdmin != true and status = '在职' order by status";
        }
         String res = JDBCUtils.sqlQueryJSON(sql, null);
         return res;
     }

    /**
     * 更新指定 ID 的员工状态
     * @param ID
     * @param status
     * @return
     */
     public static boolean changeEmpStatus(String ID,String status){
         boolean res;
         if ("删除".equals(status)){
             String sql = "delete from employee where employeeId=?";
             res = JDBCUtils.sqlUpdate(sql, Arrays.asList(ID));
         }else {
             String sql = "update from employee where employeeId=? and status=?";
             res = JDBCUtils.sqlUpdate(sql,Arrays.asList(ID,status));
         }
         return res;
     }

    /**
     * 新员工注册
     * @param params
     * @return
     */
     public static String userSignIn(List<Object> params){
        String sql = "select name from employee where name=?";
        String name = JDBCUtils.sqlQueryJSON(sql, Arrays.asList(params.get(0)), 1);
         if (name != null){
             return "员工存在";
         }else {
             sql = "insert into employee values(?,?,?,?,?,?,?)";
             boolean res = JDBCUtils.sqlUpdate(sql, params);
             return res?"true":"false";
         }
     }
}
