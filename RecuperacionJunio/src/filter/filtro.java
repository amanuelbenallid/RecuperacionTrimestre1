package filter;

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



public class filtro implements Filter  {
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
        boolean paginaLogin= httpRequest.getRequestURI().endsWith("Login.jsp");
        boolean indexAdmin= httpRequest.getRequestURI().endsWith("Index_admin.jsp");
        boolean indexUser= httpRequest.getRequestURI().endsWith("Index_user.jsp");

        if (logeado&& admin) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("../Read");
            dispatcher.forward(request, response);

        } else if (logeado&& usuario) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("../Read");
            dispatcher.forward(request, response);

        } else if ((admin&& indexUser)) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/private/Index_admin.jsp");
            dispatcher.forward(request, response);
        } else if ((usuario&& indexAdmin)) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/private/Index_user.jsp");
            dispatcher.forward(request, response);
        } else if (paginaLogin ||loginjsp) {
            chain.doFilter(request, response);

        } else if (!logeado&& (indexUser|| indexAdmin)){
            RequestDispatcher dispatcher = request.getRequestDispatcher("../Login.jsp");
            dispatcher.forward(request, response);

        }

    }
	
	public void destroy() {
        //close any resources here
    }

}
