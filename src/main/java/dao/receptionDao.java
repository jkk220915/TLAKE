package dao;

import util.JDBCUtils;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class receptionDao {
    public void creatReceptionRecord(String[] employeeId,String ticketID,String scenicProjects){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
        String date = simpleDateFormat.format(new Date());
        long time = System.currentTimeMillis();
        String sql;
        List<? extends Serializable> sqlparam;
        for (String employee : employeeId){
            sql = "insert into visitorReception (timeDate,timeClock,ticketID,scenicProjects,employees) values(?,?,?,?,?)";
            sqlparam = Arrays.asList(date, time, ticketID, scenicProjects, employee);
            JDBCUtils.sqlUpdate(sql,sqlparam);

            sql = "update workRegister set count=count+1 where employeeId in ()";
            JDBCUtils.sqlUpdate(sql,sqlparam);
        }
        sql = "update workRegister set count=count+1 where employeeId in ()";
        sqlparam = Arrays.asList(employeeId);
        JDBCUtils.sqlUpdate(sql,sqlparam);
    }

    /*public boolean verifyDuplicate(String ticketID,String scenicProjects){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
        String date = simpleDateFormat.format(new Date());
        long time = System.currentTimeMillis();

        String sql = "select max(timeMinute) from visitorReception where timeDate="
                +date+ " and ticketID=" +ticketID+ "and scenicProjects=" + scenicProjects;
        try {
            String res = JDBCUtils.sqlQueryJSON(conn, sql, null);
            while (res.next()){
                if ((time-res.getLong(1))<1000*60){
                    return false;
                }
            }
        }catch (SQLException e){
            throwable.pringStackTrace();
        }
        return true;
    }*/
}
