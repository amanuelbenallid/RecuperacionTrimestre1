package org.iesalixar.amanuelbenallid.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.iesalixar.amanuelbenallid.DAO.UserDAO;

/**
 * Servlet para iniciar sesión
 * 
 * @author Alberto
 *
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	final static Logger logger = Logger.getLogger(Login.class);

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rs = request.getRequestDispatcher("login.jsp");
		rs.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		HttpSession session = request.getSession();
		Cookie ck = null;

		session.setAttribute("Authorized", null);
		session.setAttribute("username", username);

		if (UserDAO.checkUser(username, password)) {
			ck = new Cookie("user", username);
			ck.setMaxAge(3 * 60);
			response.addCookie(ck);
			session.setAttribute("Authorized", "yes");
			session.setAttribute("username", username);
			session.setAttribute("password", password);
			if (UserDAO.checkRole(username, password).equals("admin")) {
				session.setAttribute("admin", username);
				response.sendRedirect("./Read");
				logger.info("Se ha iniciado sesión como administrador");

			} else {
				session.setAttribute("user", username);
				response.sendRedirect("./Read");
				logger.info("Se ha iniciado sesión como usuario");

			}

		} else {

			RequestDispatcher rs = request.getRequestDispatcher("login.jsp");
			logger.info("No se ha podido iniciar sesión");
			rs.forward(request, response);
		}

	}

}
