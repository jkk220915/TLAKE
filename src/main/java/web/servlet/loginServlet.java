package web.servlet;

import dao.UserDao;
import json.ticketJSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/loginVerify")
public class loginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    /**
     * 登录验证
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String userType = UserDao.loginVerify(username, password);
        String back = "";
        if (userType.equals("fail")){
            back = ticketJSON.briefJson(false);
        }else {
            HttpSession session = req.getSession();
            session.setAttribute("user",userType);
            back = ticketJSON.briefJson(true, userType);
        }
        resp.getWriter().write(back);
    }
}
