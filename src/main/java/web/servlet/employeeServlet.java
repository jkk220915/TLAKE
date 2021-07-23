package web.servlet;

import dao.excitationDao;
import json.ticketJSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/employee")
public class employeeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("gbk");
        resp.setContentType("application/html;charset=gbk");

        String scenicID = req.getParameter("scenicID");
        String employeeId = req.getParameter("EmployeeId");
        excitationDao excDao = new excitationDao();
        String doWhat = req.getParameter("doWhat");
        String back="";
        // 查岗，前端页面由于各种原因显示结果并不可靠，还是需要从数据库查看每个员工的在岗状态
        // 校验员工 ID
        switch (doWhat){
            case "checkin":{
                back = excDao.whoIsHere(scenicID);
                break;
            }
            case "login":{
                String employeeName = excDao.getEmployeeName(employeeId);
                if (employeeName == null){
                    back = ticketJSON.briefJson(false, "不存在的员工ID" + employeeId);
                }else {
                    //员工登录
                    boolean isLogin = excDao.employeePunchIn(employeeId, employeeName, scenicID);
                    if (isLogin){
                        back = ticketJSON.briefJson(true,employeeId+ " "+employeeName+"已登录！");
                    }
                }
                break;
            }
            case "logout":{
                String employeeName = excDao.getEmployeeName(employeeId);
                if (employeeName == null){
                    back = ticketJSON.briefJson(false,"不存在的员工ID" + employeeId);
                }else {
                    //员工登出
                    boolean isLogout = excDao.employeePunchOut(employeeId);
                    if (isLogout){
                        back = ticketJSON.briefJson(true,employeeId+ " " +employeeName+ "已退出！");
                    }else {
                        back = ticketJSON.briefJson(false,employeeId+ " " +employeeName+ "未登录！");
                    }
                }
                break;
            }
        }
        resp.getWriter().write(back);
    }
}
