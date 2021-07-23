package web.servlet;

import dao.UserDao;
import json.ticketJSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet("/signInServlet")
public class signInServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    /**
     * 新员工申请入职
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/html;charset=utf-8");

        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String age = req.getParameter("age");
        String gender = req.getParameter("gender");
        String employeeid = "tldj" + System.currentTimeMillis()/1000;
        String res = UserDao.userSignIn(Arrays.<Object>asList(name,employeeid,password,gender,age,false,"申请入职"));
        resp.getWriter().write(ticketJSON.briefJson(res.equals("true"),res));
    }
}
