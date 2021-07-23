package web.servlet;

import dao.UserDao;
import json.ticketJSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/adminServlet")
public class adminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    /**
     * 管理员工，状态
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/html;charset=utf-8");

        String doWhat = req.getParameter("doWhat");
        String back = "";
        boolean res = false;
        switch (doWhat){
            case "getEmp":
                String type = req.getParameter("type");
                back = UserDao.getEmpInfo(type);
                break;
            case "writeOff":
                String ID = req.getParameter("empID");
                res = UserDao.changeEmpStatus(ID,"注销");
                back = ticketJSON.briefJson(res);
                break;
            case "pass":
                res = UserDao.changeEmpStatus(req.getParameter("empID"), "在职");
                back = ticketJSON.briefJson(res);
                break;
            case "delete":
                res = UserDao.changeEmpStatus(req.getParameter("empID"),"删除");
                back = ticketJSON.briefJson(res);
                break;
        }
        resp.getWriter().write(back);
    }
}
