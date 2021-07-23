package web.servlet;

import dao.excitationDao;
import dao.manageDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/excitation")
public class excitationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/html;charset=utf-8");

        String doWhat = req.getParameter("doWhat");
        excitationDao excitation = new excitationDao();

        String date;
        String startDate;
        String endDate;
        String employeeId;
        String dailyCount;
        String daysCount;
        String singlePersonCount;
        String keyword;
        String nameList;

        switch (doWhat){
            case "dailyCount":
                date = req.getParameter("dateTime");
                dailyCount = excitation.dailyCount(date);
                resp.getWriter().write(dailyCount);
                break;
            case "dasCount":
                startDate = req.getParameter("startDate");
                endDate = req.getParameter("endDate");
                daysCount = excitation.daysCount(startDate, endDate);
                resp.getWriter().write(daysCount);
                break;
            case "singlePersonCount":
                startDate = req.getParameter("startDate");
                endDate = req.getParameter("endDate");
                employeeId = req.getParameter("employeeId");
                singlePersonCount = excitation.singlePersonCount(employeeId, startDate, endDate);
                resp.getWriter().write(singlePersonCount);
            case "dailyScenicCount":
                date = req.getParameter("dateTime");
                dailyCount = manageDao.dailyCount(date);
                resp.getWriter().write(dailyCount);
                break;
            case "daysScenicCount":
                startDate = req.getParameter("startDate");
                endDate = req.getParameter("endDate");
                daysCount = manageDao.daysCount(startDate,endDate);
                resp.getWriter().write(daysCount);
                break;
            case "singleScenicCount":
                startDate = req.getParameter("startDate");
                endDate = req.getParameter("endDate");
                String scenicName = req.getParameter("scenicName");
                String singleScenicCount = manageDao.singleScenicCount(startDate, endDate, scenicName);
                resp.getWriter().write(singleScenicCount);
                break;
            case "refreshName":
                keyword = req.getParameter("keyword");
                nameList = excitation.getNameList(keyword);
                resp.getWriter().write(nameList);
                break;
            case "refreshScenic":
                keyword = req.getParameter("keyword");
                nameList = manageDao.getNameList(keyword);
                resp.getWriter().write(nameList);
                break;
        }
    }
}
