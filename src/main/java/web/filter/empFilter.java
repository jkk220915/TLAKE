package web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/empFilter")
public class empFilter implements Filter {
    private String encode = "utf-8";
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        HttpServletResponse resp = (HttpServletResponse) response;

        req.setCharacterEncoding(encode);
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding(encode);
        String user = (String) session.getAttribute("user");
        if (user == null && (!"admin".equals(user) && !"employee".equals(user))){
            resp.sendRedirect("../login.html");
            return;
        }
        chain.doFilter(req,resp);
    }

    @Override
    public void destroy() {

    }
}
