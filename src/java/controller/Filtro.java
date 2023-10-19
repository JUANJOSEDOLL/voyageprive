/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */
package controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author JUAN JOSE DOLL
 */
@WebFilter("/FiltroAcceso")
public class Filtro implements Filter {
    
   // FilterConfig filterConfig;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

     HttpSession sesion = ((HttpServletRequest) request).getSession();
        if (sesion.getAttribute("email_usuario") != null) {
            
            chain.doFilter(request, response);
            
        } else {
            ((HttpServletResponse)response).sendRedirect("/VoyagePrive/login.html");
        }
        
    }
    
    @Override
    public void destroy() {


    }
    
}
