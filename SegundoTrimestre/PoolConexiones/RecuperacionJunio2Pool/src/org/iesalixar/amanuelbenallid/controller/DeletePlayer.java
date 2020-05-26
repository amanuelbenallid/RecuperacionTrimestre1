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
 * Servlet para borrar jugadores
 * 
 * @author Alberto
 *
 */
@WebServlet("/DeletePlayer")
public class DeletePlayer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(Login.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeletePlayer() {
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
		logger.info("Se ha pedido borrar un jugador");
		if (PlayerDAO.deletePlayer(jugador_id)) {
			logger.info("Se ha eliminado un jugador de la base de datos");
		} else {
			logger.info("No se ha eliminado el jugador de la base de datos");
		}

		getServletContext().getRequestDispatcher("/Read").forward(request, response);
	}

}
