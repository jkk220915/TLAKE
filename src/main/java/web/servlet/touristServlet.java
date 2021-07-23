package web.servlet;

import dao.touristDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/touristServlet")
public class touristServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/html;charset=utf-8");

        String doWhat = req.getParameter("doWhat");
        String ticketID = req.getParameter("ticketID");
        touristDao touristdao = new touristDao();
        switch (doWhat){
            case "getBrief":
                String scenic = touristdao.getScenic(ticketID);
                resp.getWriter().write(scenic);
                break;
            case "getSpecific":
                String ticketRecord = touristdao.getTicketRecord(ticketID);
                resp.getWriter().write(ticketRecord);
                break;
            case "remark":
                String time = req.getParameter("time");
                String remark = req.getParameter("rremark");
                String employees = req.getParameter("employees");
                String makeRemark = touristdao.makeRemark(ticketID, time, remark, employees);
                resp.getWriter().write(makeRemark);
                break;
        }
    }
}
