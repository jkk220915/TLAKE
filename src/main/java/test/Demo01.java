package test;

import dao.excitationDao;
import dao.manageDao;
import dao.scenicDao;

public class Demo01 {

    public static void main(String[] args) {
        scenicDao scenicDao = new scenicDao();
        String allScenic = scenicDao.getAllScenic();
        System.out.println(allScenic);
        excitationDao excDao = new excitationDao();
        String dailyCount = excDao.dailyCount("2021-6-16");
       // System.out.println(dailyCount);
        String daysCount = excDao.daysCount("2021-6-9", "2021-6-15");
       // System.out.println(daysCount);
        String tldj001 = excDao.singlePersonCount("tldj001", "2021-6-9", "2021-6-12");
       // System.out.println(tldj001);
        String dailyCount1 = manageDao.dailyCount("2021-06-16");
        System.out.println(dailyCount1);
    }
}
