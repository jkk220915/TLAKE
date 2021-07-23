package web.servlet;

import dao.scenicDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/scenicServlet")
public class scenicServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("gbk");
        resp.setContentType("application/html;charset=gbk");

        String allScenic = new scenicDao().getAllScenic();
        resp.setHeader("Access-Control-Allow-Origin","*");
        resp.getWriter().write(allScenic);
    }
}
