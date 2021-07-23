package dao;


import json.ticketJSON;
import util.JDBCUtils;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class touristDao {

    /**
     * 根据票码获取项目 ID 和项目名称
     * @param ticketID 票码
     * @return
     */
    public String getScenic(String ticketID){
        String sql = "select distinct scenic.scenicID,scenic.scenicName " +
                "from ticketReception as ticket inner join scenicProjects as scenic on " +
                "ticket.scenicProjects=scenic.scenicID and ticket.ticketID=?";
        String res = JDBCUtils.sqlQueryJSON(sql, Arrays.asList(ticketID));
        return res;
    }

    /**
     * 根据票码获取票记录
     * @param ticketID
     * @return
     */
    public String getTicketRecord(String ticketID){
        String sql ="select ticket.ticketID,ticket.timeClock,ticket.remark,scenic.scenicName,employee.name,ticket.employees " +
                "from ticketReception as ticket inner join scenicProjects as scenic inner join employee on " +
                "ticket.scenicProjects=scenic.scenicID and ticket.employees=employee.employeeId and ticket.ticketID=?";
        String res = JDBCUtils.sqlQueryJSON(sql, Arrays.asList(ticketID));
        return res;
    }

    /**
     * 游客评论
     * @param ticketID 票码
     * @param time 评论时间
     * @param remark 打星
     * @param employees 多员工 ID
     * @return
     */
    public String makeRemark(String ticketID,String time,String remark,String employees){
        String sql = "update ticketReception set remark=? where ticketID=? and timeClock=?";
        boolean result = JDBCUtils.sqlUpdate(sql, Arrays.asList(remark, ticketID, time));
        String stars = "";
        if (result){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String date = simpleDateFormat.format(new Date(Long.parseLong(time)));
            switch (remark){
                case "1": stars = "oneStarCount=oneStarCount+1";
                break;
                case "2": stars = "twoStarCount=twoStarCount+1";
                break;
                case "3": stars = "threeStarCount=threeStarCount+1";
                break;
                case "4": stars = "fourStarCount=fourStarCount+1";
                break;
                case "5": stars = "fiveStarCount=fiveStarCount+1";
                break;
            }
            employees = employees.trim().replaceAll("\\s+", "','");
            sql = "update workRecord set" + stars + "where EmployeeId in('"+employees+"') and timeDate=?";
            result = JDBCUtils.sqlUpdate(sql, Arrays.asList(date));
            return ticketJSON.briefJson(result);
        }
        return ticketJSON.briefJson(false);
    }

    /**
     * 获取今日客流量
     * @return
     */
    public String getPassengerFlow() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(new Date());
        String sql = "select COUNT(distinct ticketID) from ticketReception where timeDate=?";
        String res = JDBCUtils.sqlQueryJSON(sql, Arrays.asList(date),1);
        return res;
    }
}
