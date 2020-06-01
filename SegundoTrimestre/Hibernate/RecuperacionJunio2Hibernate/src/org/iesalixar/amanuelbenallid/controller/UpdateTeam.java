package org.iesalixar.amanuelbenallid.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.iesalixar.amanuelbenallid.DAO.TeamDAO;

/**
 * Servlet para actualizar los equipos
 * 
 * @author Alberto
 *
 */
@WebServlet("/UpdateTeam")
public class UpdateTeam extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(Login.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateTeam() {
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

		Long equipo_id = Long.parseLong(request.getParameter("equipo_id"));
		String equipo_nombre = request.getParameter("equipo_nombre");
		String equipo_ciudad = request.getParameter("equipo_ciudad");
		String equipo_pais = request.getParameter("equipo_pais");

		if (TeamDAO.updateTeam(equipo_id, equipo_nombre, equipo_ciudad, equipo_pais)) {
			logger.info("Se ha actualizado un equipo");
		} else {
			logger.info("No se ha podido actualizar el equipo");
		}

		getServletContext().getRequestDispatcher("/Read").forward(request, response);
	}

}
