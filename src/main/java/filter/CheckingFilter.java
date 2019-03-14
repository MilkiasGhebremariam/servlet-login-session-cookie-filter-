package filter;

import com.sun.net.httpserver.HttpExchange;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.FilterChain.*;

import java.io.IOException;


@WebFilter(filterName = "CheckingFilter",
        urlPatterns = "/*"

)
public class CheckingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String path = ( (HttpServletRequest) servletRequest).getRequestURI().substring(( (HttpServletRequest) servletRequest).getContextPath().length());
        if((( (HttpServletRequest) servletRequest).getSession().getAttribute("CurrentUser")!= null) ||
                path.equalsIgnoreCase("/") || path.equalsIgnoreCase("/login-form.jsp") ||
                path.equalsIgnoreCase("/login")){
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else {
            ((HttpServletRequest)servletRequest).getRequestDispatcher("login-form.jsp").forward(servletRequest, servletResponse);
        }
        // pass the request along the filter chain
        //chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

}
