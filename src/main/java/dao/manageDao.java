package dao;

import util.JDBCUtils;

import java.util.Arrays;

public class manageDao {

    /**
     * 游玩项目接待人数统计
     * @param date
     * @return
     */
    public static String dailyCount(String date){
        String sql = "select scenic.scenicName,COUNT(ticket.timeClock) as count " +
                "from scenicProjects as scenic inner join ticketreception as ticket on " +
                "scenic.scenicID=ticket.scenicProjects and ticket.timeDate=? group by scenic.scenicID";
        String res = JDBCUtils.sqlQueryJSON(sql, Arrays.asList(date));
        return res;
    }

    public static String daysCount(String startDate,String endDate){
        String sql = "select scenic.scenicName,COUNT(ticket.timeClock) as count " +
                "from ticketreception as ticket left join scenicProjects as scenic on " +
                "ticket.scenicProjects=scenic.scenicID and ticket.timeDate between ? and ? " +
                "group by scenic.scenicID";
        String res = JDBCUtils.sqlQueryJSON(sql, Arrays.asList(startDate, endDate));
        return res;
    }

     public static String singleScenicCount(String startDate,String endDate,String scenicName){
        String sql = "select scenic.scenicName,COUNT(ticket.timeClock) as count,timeDate " +
                "from scenicProjects as scenic inner join ticketreception as ticket on " +
                "ticket.scenicProjects=(select scenicID from scenicProjects where scenicName=?) and ticket.timeDate between ? and ? " +
                "group by timeDate";
         String res = JDBCUtils.sqlQueryJSON(sql, Arrays.asList(startDate, endDate, scenicName));
         return res;
     }

    /**
     * 模糊查询项目名字列表
     * @param keyword
     * @return
     */
     public static String getNameList(String keyword){
        String sql = "select scenicName from scenicProjects where scenicName like '%"+keyword+"%'";
         String res = JDBCUtils.sqlQueryJSON(sql, null);
         return res;
     }
}
