package json;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mysql.cj.protocol.Resultset;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ticketJSON {

    /**
     * 查询结果集转换为JSON
     * @param res
     * @return
     */
    public static String resultSetJSONArr(ResultSet res){
        JSONArray jsonArray = new JSONArray();
        try {
            ResultSetMetaData resultSetMetaData = res.getMetaData();
            int columnCount ;
            while (res.next()){
                JSONObject jsonObject = new JSONObject();
                columnCount = resultSetMetaData.getColumnCount();
                for (int i=1;i<=columnCount;i++){
                    String columnName = resultSetMetaData.getColumnLabel(i);
                    jsonObject.put(columnName,res.getString(columnName));
                }
                jsonArray.add(jsonObject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jsonArray.toJSONString();
    }

    /**
     *
     * @param result
     * @return
     */
    public static String briefJson(boolean result){
        JSONObject jsonObject = new JSONObject();
        String res = result ? "success" : "fail";
        jsonObject.put("result",res);
        return jsonObject.toJSONString();
    }
    public static String briefJson(boolean result, String message) {
        JSONObject jsonObject = new JSONObject();
        String res = result ? "success" : "fail";
        jsonObject.put("result",res);
        jsonObject.put("message",message);
        return jsonObject.toJSONString();
    }
}
