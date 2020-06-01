package org.iesalixar.amanuelbenallid.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.iesalixar.amanuelbenallid.DAO.UserDAO;

/**
 * Servlet para cambiar el rol, de admin a user y al revés
 * 
 * @author Alberto
 *
 */
@WebServlet("/UpdateUserRole")
public class UpdateUserRole extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(Login.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateUserRole() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long usuario_id = Long.parseLong(request.getParameter("userId"));
		String roleantiguo = request.getParameter("oldRole");

		if (UserDAO.updateRole(usuario_id, roleantiguo).equals("user")) {
			logger.info("El administrador ha pasado a ser usuario");
		} else if (UserDAO.updateRole(usuario_id, roleantiguo).equals("admin")) {
			logger.info("El usuario ha pasado a ser administrador");
		} else {
			logger.info("No se ha podido actualizar");
		}

		getServletContext().getRequestDispatcher("/ReadUser").forward(request, response);
	}

}
