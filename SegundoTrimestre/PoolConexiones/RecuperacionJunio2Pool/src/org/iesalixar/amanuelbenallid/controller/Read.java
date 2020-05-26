package org.iesalixar.amanuelbenallid.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.iesalixar.amanuelbenallid.DAO.TeamDAO;
import org.apache.log4j.Logger;
import org.iesalixar.amanuelbenallid.DAO.PlayerDAO;

/**
 * Servlet para leer equipos y jugadores dependiendo del rol
 * 
 * @author Alberto
 *
 */
@WebServlet("/Read")
public class Read extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(Login.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Read() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		if (checkCookie("user", request.getCookies()) && session.getAttribute("admin") != null
				&& session.getAttribute("Authorized") != null && session.getAttribute("Authorized").equals("yes")) {
			session.setAttribute("jugadores", PlayerDAO.readPlayer());
			session.setAttribute("equipos", TeamDAO.readTeam());
			session.setAttribute("usuario", 0);
			getServletContext().getRequestDispatcher("/private/index.jsp").forward(request, response);
			logger.info("El administrador ha entrado en la página principal");

		} else if (checkCookie("user", request.getCookies()) && session.getAttribute("user") != null
				&& session.getAttribute("Authorized") != null && session.getAttribute("Authorized").equals("yes")) {

			session.setAttribute("jugadores", PlayerDAO.readPlayer());
			session.setAttribute("equipos", TeamDAO.readTeam());
			session.setAttribute("usuario", 1);
			getServletContext().getRequestDispatcher("/private/index.jsp").forward(request, response);
			logger.info("El usuario ha entrado en la página principal");

		}else {
			response.sendRedirect("login.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
