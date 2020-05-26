package org.iesalixar.amanuelbenallid.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * Filtro de autenticación para redirigir al usuario según su rol, y si ha iniciado o no sesión.
 * @author Alberto
 *
 */
public class LoginFilter implements Filter  {
	private ServletContext context;

    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
        this.context.log("AuthenticationFilter initialized");
    }
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(false);
        boolean logeado = (session!= null && session.getAttribute("Authorized")!=null);
        boolean admin= (session != null && session.getAttribute("admin") != null);
        boolean usuario= (session != null && session.getAttribute("user") != null);
        String loginURI = httpRequest.getContextPath() + "/Login";

        boolean loginjsp = httpRequest.getRequestURI().equals(loginURI);
        boolean paginaLogin= httpRequest.getRequestURI().endsWith("login.jsp");
        boolean index= httpRequest.getRequestURI().endsWith("index.jsp");

        if (logeado&& admin) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("../Read");
            dispatcher.forward(request, response);

        } else if (logeado&& usuario) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("../Read");
            dispatcher.forward(request, response);

        } else if (paginaLogin ||loginjsp) {
            chain.doFilter(request, response);

        } else if (!logeado&& index){
            RequestDispatcher dispatcher = request.getRequestDispatcher("../login.jsp");
            dispatcher.forward(request, response);

        }
    }
	
	public void destroy() {
        //close any resources here
    }

}
