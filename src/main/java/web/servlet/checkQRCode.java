package web.servlet;

import dao.excitationDao;
import dao.ticketDao;
import json.ticketJSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/checkQR")
public class checkQRCode extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("gbk");
        resp.setContentType("application/html;charset=gbk");

        String ticketID = req.getParameter("ticketID");
        String scenicID = req.getParameter("scenicID");
        String employeeId = req.getParameter("employeeId");
        boolean ticketRecord = ticketDao.creatTicketRecord(scenicID, ticketID, employeeId);
        String back;
        if (ticketRecord){
            //excitationDao excDao = new excitationDao();
            excitationDao.achievementPlus(scenicID);
            int passengerTraffic = ticketDao.getPassengerTraffic(scenicID);
            back = ticketJSON.briefJson(true, String.valueOf(passengerTraffic));
        }else {
            back = ticketJSON.briefJson(false, "");
        }
        resp.getWriter().write(back);
    }
}
