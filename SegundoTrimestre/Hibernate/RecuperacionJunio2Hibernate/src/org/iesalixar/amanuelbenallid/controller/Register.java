package org.iesalixar.amanuelbenallid.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.iesalixar.amanuelbenallid.DAO.UserDAO;
import org.iesalixar.amanuelbenallid.helper.Helper;

/**
 * Servlet para registrar nuevos usuarios
 * 
 * @author Alberto
 *
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(Login.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Register() {
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
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String fullname = request.getParameter("fullname");
		String fechanac = request.getParameter("fechanac");
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaDate = null;
        try {
            fechaDate = formato.parse(fechanac);
        } 
        catch (ParseException ex) 
        {
            System.out.println(ex);
        }

		if (!Helper.checkFullname(fullname) || !Helper.checkPassword(password) || !Helper.checkUsername(username)) {
			if (!Helper.checkFullname(fullname)) {
				request.setAttribute("errorFullname", "El nombre completo debe estar formado por entre 10 y 50 caracteres alfabéticos");
				logger.info("Error de nombre completo");
			}
			if (!Helper.checkPassword(password)) {
				request.setAttribute("errorPassword",
						"La contraseña debe tener al menos una mayúscula, una minúscula, un número, al menos seis caracteres y alguno de los siguientes caracteres especiales @#$%^&+=]");
				logger.info("Error de contraseña");
			}
			if (!Helper.checkUsername(username)) {
				request.setAttribute("errorUsername", "El nombre de usuario debe tener entre 4 y 15 caracteres alfanumericos");
				logger.info("Error de nombre de usuario");
			}

			getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
		} else {
			if (UserDAO.createUser(username, password, fullname, fechaDate, "user")) {
				logger.info("Se ha registrado un nuevo usuario");
			} else {
				logger.info("El registro no ha sido completado");
			}
			getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
		}

	}

}
