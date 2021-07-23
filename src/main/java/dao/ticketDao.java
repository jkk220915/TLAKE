package dao;

import util.JDBCUtils;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class ticketDao {

    /**
     * 添加扫票记录
     * @param scenicID
     * @param ticketID
     * @param employeeId
     * @return
     */
    public static boolean creatTicketRecord(String scenicID,String ticketID,String employeeId){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(new Date());

        //每张票只能扫一次
        String sql = "select max(timeClock) from ticketReception where scenicProjects=? and timeDate=? and ticketID=?";
        String res = JDBCUtils.sqlQueryJSON(sql, Arrays.asList(scenicID, date, ticketID), 1);
        if (res != null){
            return false;
        }
        long timeClock = System.currentTimeMillis();
        sql ="insert ticketReception(timeDate,timeClock,ticketID,scenicProjects,employees) value(?,?,?,?,?)";
        return  JDBCUtils.sqlUpdate(sql,Arrays.asList(date,timeClock,ticketID,scenicID,employeeId));
    }

    /**
     * 获取某一项目的客流量（扫票数）
     * @param scenicID
     * @return
     */
    public static int getPassengerTraffic(String scenicID){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(new Date());

        String sql = "select count(ticketID) from ticketReception where timeDate=? and scenicID=?";
        String res = JDBCUtils.sqlQueryJSON(sql, Arrays.asList(date, scenicID));
        if (res != null){
            return Integer.parseInt(res);
        }
        return 0;
    }

    /**
     * 获取详细票务信息
     * @param ticketID
     * @return
     */
    public static String getTicketDetail(String ticketID){
        String sql = "select ticket.ticketID,ticket.timeClock,ticket.remark,scenic.scenicName,employee.name " +
                "from ticketReception as ticket inner join scenicProjects as scenic inner join employee on " +
                "ticket.scenicProjects=scenic.scenicID and ticket.employees=employee.EmployeeId and ticket.ticketID=?";
        String res = JDBCUtils.sqlQueryJSON(sql, Arrays.asList(ticketID));
        return res;
    }
}
