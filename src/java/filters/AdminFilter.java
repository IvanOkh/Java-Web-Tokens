/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import services.UserService;

/**
 *
 * @author 797462
 */
public class AdminFilter implements Filter 
{
    
    public void doFilter(ServletRequest request, ServletResponse response,
                        FilterChain chain)throws IOException, ServletException 
    {
        // code that is executed before the servlet        
        HttpServletRequest hsr = (HttpServletRequest)request;
        HttpSession session = hsr.getSession();
        
        UserService us = new UserService();

        String email = (String)session.getAttribute("email");
        try
        {
            User usr = us.get(email);
            if (usr.getRole().getRoleName().equalsIgnoreCase("system admin") || 
                    usr.getRole().getRoleName().equalsIgnoreCase("company admin"))
            {
                 chain.doFilter(request, response);
            }
            else 
            {
                HttpServletResponse hsre = (HttpServletResponse)response;
                hsre.sendRedirect("home");
            }
              
        }
        catch (Exception o)
        {
                    
        }
         
         // code that is executed after the servlet
    }

    //@Override
    public void init(FilterConfig filterConfig) throws ServletException 
    {
       
    }

    //@Override
    public void destroy() 
    {
        
    }

}
