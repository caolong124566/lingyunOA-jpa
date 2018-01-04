package com.lingyun.framework.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


<<<<<<< HEAD
@WebFilter(filterName="loginFilter",urlPatterns="/aa")
=======
//@WebFilter(filterName="loginFilter",urlPatterns="/*")
>>>>>>> 3d46f6cd65bd57d1e198eefc4d9f71d5547b43d8
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
<<<<<<< HEAD
        String[] str = {"index.html","person","login"};
=======
        String[] str = {"index.html","employee"};
>>>>>>> 3d46f6cd65bd57d1e198eefc4d9f71d5547b43d8
        HttpServletRequest request1 = (HttpServletRequest) request;
        HttpServletResponse response1 = (HttpServletResponse) response;
        HttpSession session = request1.getSession();
        if(str!=null){
            for(int i = 0;i<str.length;i++) {
                if(str[i]==null || "".equals(str[i])) {
                    continue;
                }
                if(request1.getRequestURI().indexOf(str[i])!=-1 ) {
                    chain.doFilter(request1, response1);
                    return; //必须加这个  不然会继续循环下面的步骤
                }
            }
        }

<<<<<<< HEAD
        if(session.getAttribute("name")!=null && session.getAttribute("password")!=null) {
            chain.doFilter(request1, response1);
        }
        else {
            response1.sendRedirect("forward:index.html");
=======
        if(session.getAttribute("telephone")!=null && session.getAttribute("password")!=null) {
            chain.doFilter(request1, response1);
        }
        else {
            response1.sendRedirect("/angular/dist/index.html");
>>>>>>> 3d46f6cd65bd57d1e198eefc4d9f71d5547b43d8
        }


    }

    @Override
    public void destroy() {

    }
}
