package org.iesalixar.amanuelbenallid.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.iesalixar.amanuelbenallid.DAO.PlayerDAO;

/**
 * Servlet para actualizar el equipo de los jugadores
 * 
 * @author Alberto
 *
 */
@WebServlet("/UpdatePlayerTeam")
public class UpdatePlayerTeam extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(Login.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdatePlayerTeam() {
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
		Integer jugador_id = Integer.parseInt(request.getParameter("jugador_id"));
		Integer equipo = Integer.parseInt(request.getParameter("team"));

		if (PlayerDAO.updatePlayer(jugador_id, equipo)) {
			logger.info("Se ha cambiado el equipo del jugador");
		} else {
			logger.info("No se ha podido cambiar el equipo del jugador");
		}

		getServletContext().getRequestDispatcher("/Read").forward(request, response);
	}

}
