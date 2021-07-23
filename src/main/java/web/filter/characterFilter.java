package web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 解决全站乱码问题，处理所有的请求
 */
@WebFilter(filterName = "characterFilter")
public class characterFilter implements Filter {
    String encoding = null;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding="UTF-8";
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        if (encoding != null){
            request.setCharacterEncoding(encoding);
            response.setCharacterEncoding(encoding);
        }
       /* // 将父接口转为子接口
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String method = req.getMethod();
        //解决POST请求中文数据乱码问题
        if (method.equalsIgnoreCase("post")){
            req.setCharacterEncoding("utf-8");
        }
        //处理响应乱码
        resp.setContentType("text/html;charset=utf-8");
        chain.doFilter(req,resp);*/
    }

    @Override
    public void destroy() {

    }
}
