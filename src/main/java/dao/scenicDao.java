package dao;

import util.JDBCUtils;

import java.util.Arrays;

public class scenicDao {

    /**
     * 获取所有的游玩项目
     * @return
     */
    public String getAllScenic(){
        String sql = "select scenicName,scenicID from scenicProjects where isOnService=true order by convert(scenicName using gbk)";
        String res = JDBCUtils.sqlQueryJSON(sql, null);
        res = res.replace("\"},{\"scenicName\":\"", ";");
        res = res.replace("\",\"scenicID\":\"", "");
        res = res.substring(16, res.length() - 3);
        return "scenic:" + res;
    }

    /**
     * 由项目 ID 获取游玩项目名称
     * @param scenicID
     * @return
     */
    public String getScenicById(String scenicID){
        String sql = "select scenicName from scenicProjects where scenicID=?";
        return JDBCUtils.sqlQueryJSON(sql, Arrays.asList(scenicID),1);
    }
}
