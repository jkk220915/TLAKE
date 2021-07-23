package web.servlet;

import dao.touristDao;
import json.ticketJSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/passengetFlowServlet")
public class passengerFlowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/html;charset=utf-8");

        touristDao touristdao = new touristDao();
        String count = touristdao.getPassengerFlow();
        String res = ticketJSON.briefJson(true, count);
        resp.getWriter().write(res);
    }
}
