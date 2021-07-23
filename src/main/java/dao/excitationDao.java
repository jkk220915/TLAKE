package dao;

import util.JDBCUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class excitationDao {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 通过游玩项目 ID 给在该处在职员工加业绩
     * @param scenicID  游玩项目 ID
     */
    public static void achievementPlus(String scenicID){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(new Date());

        String sql = "update workRecord set receptionCount=receptionCount+1 where scenicID=? and timeDate=? and isOnline=true";
        JDBCUtils.sqlUpdate(sql,Arrays.asList(scenicID,date));
    }
    public String getEmployeeName(String EmployeeId){
        String sql = "select name from employee where EmployeeId=? and status=?";
        String employeeName = JDBCUtils.sqlQueryJSON(sql, Arrays.asList(EmployeeId, "在职"));
        return employeeName;
    }

    /**
     * 员工签到
     * @param employeeId 员工 ID
     * @param employeeName 员工名字
     * @param scenicID 在职项目 ID
     * @return
     */
    public boolean employeePunchIn(String employeeId, String employeeName, String scenicID) {
        boolean res = false;
        String date = sdf.format(new Date());
        String time = sdf2.format(new Date());

        String sql = "select scenicID from workRecord where auxiliaryIndex=?";
        String haveBeanHere = JDBCUtils.sqlQueryJSON(sql, Arrays.asList(date + "-" + employeeId));
        if (haveBeanHere==null){
            sql = "insert into workRecord(timeDate,EmployeeId,auxiliaryIndex,name,punchIn,isOnline,scenicID) where(?,?,?,?,?,true,?)";
            res = JDBCUtils.sqlUpdate(sql, Arrays.asList(date, employeeId, date + "-" + employeeId, employeeName, time, scenicID));
        }else {
            if (haveBeanHere.equals(scenicID)){
                return true;
            }else {
                sql = "update workRecord set scenicID=? and isOnline=true where auxiliaryIndex=?";
                res = JDBCUtils.sqlUpdate(sql, Arrays.asList(scenicID, date + "-" + employeeId));
            }
        }
        return res;
    }

    public boolean employeePunchOut(String employeeId) {
        String date = sdf.format(new Date());
        String time = sdf2.format(new Date());

        String sql = "select scenicID from wordRecord where auxiliaryIndex=?";
        String haveBeanHere = JDBCUtils.sqlQueryJSON(sql, Arrays.asList(date + "-" + employeeId));
        if (haveBeanHere != null){
            sql = "update workRecord set isOnline=false,punchOff=? where auxiliaryIndex=?";
            boolean res = JDBCUtils.sqlUpdate(sql, Arrays.asList(time, date + "-" + employeeId));
            return res;
        }else {
            return false;
        }
    }

    public String whoIsHere(String scenicID) {
        String date = sdf.format(new Date());

        String sql = "select EmployeeId,name from workRecord where scenicID=? and timeDate=? and isOnline=true";
        String res = JDBCUtils.sqlQueryJSON(sql, Arrays.asList(scenicID, date));
        return res;
    }

    /**
     * 员工接待人数统计
     * @param date
     * @return
     */
    public String dailyCount(String date){
        String sql = "select work.name,scenic.scenicName,work.receptionCount,work.fiveStarCount,work.fourStarCount,work.threeStarCount,work.twoStarCount,work.oneStarCount " +
                "from workRecord as work inner join scenicProjects as scenic on " +
                "work.scenicID=scenic.scenicID and work.timeDate=?";
        String res = JDBCUtils.sqlQueryJSON(sql, Arrays.asList(date));
        return res;
    }
    public String daysCount(String startDate,String endDate){
        String sql = "select name,sum(receptionCount) as receptionCount,sum(fiveStarCount) as fiveStarCount,sum(fourStarCount) as fourStarCount,sum(threeStarCount) as threeStarCount, " +
                "sum(twoStarCount) as twoStarCount,sum(oneStarCount) as oneStarCount " +
                "from workRecord where timeDate>=? and timeDate<=? group by name";
        String res = JDBCUtils.sqlQueryJSON(sql, Arrays.asList(startDate, endDate));
        return res;
    }
    public String singlePersonCount(String employeeId,String startDate,String endDate){
        String sql ="select work.name,work.timeDate,scenic.scenicName,work.receptionCount,work.fiveStarCount,work.fourStarCount,work.threeStarCount,work.twoStarCount,work.oneStarCount " +
                "from workRecord as work inner join scenicProjects as scenic on " +
                "work.scenicID=scenic.scenicID and work.timeDate between ? and ? and employeeId=?";
        String res = JDBCUtils.sqlQueryJSON(sql, Arrays.asList(startDate, endDate, employeeId));
        return res;
    }

    /**
     * 模糊查询
     * @param keyword
     * @return
     */
    public String getNameList(String keyword){
        String sql = "select name,EmployeeId from employee where name like '%"+keyword+"%'";
        String names = JDBCUtils.sqlQueryJSON(sql, null);
        return names;
    }
}
