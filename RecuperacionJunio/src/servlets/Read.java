package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import clases.Equipo;
import clases.Jugadores;

/**
 * Servlet implementation class Read
 */
@WebServlet("/Read")
public class Read extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Read() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

        if (checkCookie("user", request.getCookies()) && session.getAttribute("admin") != null
                && session.getAttribute("Authorized") != null && session.getAttribute("Authorized").equals("yes")) {

        	session.setAttribute("equipos", Equipo.readEquipo());
            getServletContext().getRequestDispatcher("/private/Index_admin.jsp").forward(request, response);

        } else if(checkCookie("user", request.getCookies()) && session.getAttribute("user") != null
                && session.getAttribute("Authorized") != null && session.getAttribute("Authorized").equals("yes")) {
           
            
            session.setAttribute("jugadores", Jugadores.readJugadores());
            getServletContext().getRequestDispatcher("/private/Index_user.jsp").forward(request, response);

        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	private boolean checkCookie(String value, Cookie cookies[]) {

        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals(value))
                return true;
        }

        return false;

    }

}
