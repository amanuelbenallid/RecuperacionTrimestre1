package org.iesalixar.amanuelbenallid.DAO;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import org.hibernate.Session;
import org.iesalixar.amanuelbenallid.helper.HibernateUtil;
import org.iesalixar.amanuelbenallid.model.User;
/**
 * Clase que tiene todos los métodos para el CRUD de los usuario
 * @author Alberto
 *
 */
public class UserDAO {
	/**
	 * Este método comprueba los datos de acceso para el login
	 * @param username recibe el nombre de usuario
	 * @param password recibe la contraseña
	 * @return devuelve true en caso de que el usuario se encuentre en la base de datos, y false si no
	 */
	public static boolean checkUser(String username, String password) {
		boolean found = true;
		HibernateUtil.buildSessionFactory();
		Session session = null;
		try {
			HibernateUtil.openSessionAndBindToThread();
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            Query query = session.createQuery("from User u where u.username = ?0 and u.password = ?1");
            query.setParameter(0, username);
            query.setParameter(1, password);
            @SuppressWarnings("unchecked")
            List<User> result = query.getResultList();

            if (result.isEmpty()) {
                found =false;
            }	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return found;
	}

	/**
	 * Este método comprueba si el usuario es administrador o usuario normal
	 * @param username recibe el nombre de usuario
	 * @param password recibe la contraseña del usuario
	 * @return devuelve una cadena de texto con el role del usuario
	 */
	public static String checkRole(String username, String password) {
		String role = null;
		HibernateUtil.buildSessionFactory();
		Session session = null;
		try {
			HibernateUtil.openSessionAndBindToThread();
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            Query query = session.createQuery("from User u where u.username = ?0 and u.password = ?1");
            query.setParameter(0, username);
            query.setParameter(1, password);
            @SuppressWarnings("unchecked")
            List<User> result = query.getResultList();

            for (User u : result) {
            	role= u.getRole();
            }
		} catch (Exception e) {
			e.printStackTrace();
		}

		return role;
	}

	/**
	 * Este método sirve para registrar nuevos usuarios, por defecto como usuarios normales
	 * @param username recibe el nombre de usuario
	 * @param password recibe la contraseña
	 * @param fullname recibe el nombre completo del usuario
	 * @param fechanac recibe la fecha de nacimiento del usuario
	 * @param role recibe el role que se le va a dar al usuario aunque por defecto es usuario normal
	 * @return devuelve true si lo consigue crear y false si no
	 */
	public static boolean createUser(String username, String password, String fullname, Date fechanac, String role) {
		boolean insert = false;
		Session session = null;
		User u = new User();
		HibernateUtil.buildSessionFactory();
		u.setUsername(username);
		u.setFullname(fullname);
		u.setFechanac(fechanac);
		u.setPassword(password);
		u.setRole(role);
		
		try {
			HibernateUtil.openSessionAndBindToThread();
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.save(u);
			insert=true;
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return insert;
	}

	/**
	 * Este método borra un usuario de la base de datos
	 * @param idusuario recibe la ID del usuario que se va a borrar
	 * @return devuelve true si lo consigue borrar y false si no
	 */
	public static boolean deleteUser(Long idusuario) {
		boolean delete = false;
		HibernateUtil.buildSessionFactory();
		Session session = null;
		User u = null;
		try {
			HibernateUtil.openSessionAndBindToThread();
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			u = (User)session.createQuery("FROM User u where user_id=?0", User.class).setParameter(0, idusuario).uniqueResult();
			session.beginTransaction();
			session.delete(u);
			delete=true;
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return delete;

	}

	/**
	 * Este método lee todos los usuarios de la base de datos
	 * @return List<User> Devuelve una lista con todos los usuarios
	 */
	@SuppressWarnings("unchecked")
	public static Set<User> readUser() {

		Set<User> usuarios = new HashSet<User>();
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			usuarios.addAll(session.createQuery("FROM User u").list());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return usuarios;
	}

	/**
	 * Este método cambia el rol de los usuarios, puede cambiar de admin a user y de user a admin
	 * @param user_id recibe el ID del usuario al que se le va a cambiar el rol
	 * @param roleantiguo recibe el antiguo rol que tenía el usuario
	 * @return devuelve una cadena con el valor del nuevo rol que tiene el usuario
	 */
	public static String updateRole(Long user_id, String roleantiguo) {
		String newRole = null;
		Session session = null;
		User u = null;
		HibernateUtil.buildSessionFactory();
	    try {
	    	HibernateUtil.openSessionAndBindToThread();
			session = HibernateUtil.getSessionFactory().getCurrentSession();
	    	session.beginTransaction();
			u = (User)session.createQuery("FROM User u where user_id=?0", User.class).setParameter(0, user_id).uniqueResult();
	        if(roleantiguo.equals("user")){
	            u.setRole("admin");
	            session.saveOrUpdate(u);
	            newRole = "admin";
	        }else {
	        	u.setRole("user");
	            session.saveOrUpdate(u);
	            newRole = "user";
	        }
	        
	        session.getTransaction().commit();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return newRole;
	}
	
}
