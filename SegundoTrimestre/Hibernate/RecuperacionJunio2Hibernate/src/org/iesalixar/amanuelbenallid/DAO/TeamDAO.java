package org.iesalixar.amanuelbenallid.DAO;

import java.util.HashSet;
import java.util.Set;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.iesalixar.amanuelbenallid.controller.Login;
import org.iesalixar.amanuelbenallid.helper.HibernateUtil;
import org.iesalixar.amanuelbenallid.model.Player;
import org.iesalixar.amanuelbenallid.model.Team;
/**
 * Clase que tiene todos los métodos para el CRUD de los equipos
 * @author Alberto
 *
 */
public class TeamDAO {
	final static Logger logger = Logger.getLogger(Login.class);

	/**
	 * Este método lee todos los equipos que hay en la base de datos
	 * 
	 * @return List<Team> Devuelve una lista con todos los Equipos.
	 */
	@SuppressWarnings("unchecked")
	public static Set<Team> readTeam() {

		Set<Team> equipos = new HashSet<Team>();
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			equipos.addAll(session.createQuery("FROM Team t").list());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return equipos;
	}

	/**
	 * Este método borra el equipo que se le pasa por parametros la ID, y pone como
	 * nulo el equipo de los jugadores que tenían el quipo eliminado.
	 * 
	 * @param idequipo recibe la ID del equipo que se va a borrar
	 * @return devuelve true si lo consigue borrar y false si no
	 */
	@SuppressWarnings("unchecked")
	public static boolean deleteTeam(Long idequipo) {
		boolean delete = false;
		HibernateUtil.buildSessionFactory();
		Session session = null;
		Set<Player> jugadores = new HashSet<Player>();
		Team t = null;
		
		try {
			HibernateUtil.openSessionAndBindToThread();
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			t = (Team)session.createQuery("from Team t where t.id =:id" , Team.class).setParameter("id", idequipo ).uniqueResult();	
			
			jugadores.addAll(session.createQuery("FROM Player p where equipo=?0").setParameter(0, t).list());
			
			for (Player p : jugadores) {
				PlayerDAO.updatePlayer(p.getId(), null);
			}
			HibernateUtil.openSessionAndBindToThread();
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.delete(t);
			delete=true;
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return delete;

	}

	/**
	 * Este método actualiza cualquier campo de los equipos, menos la ID
	 * 
	 * @param id Recibe la ID del equipo que se va a actualizar
	 * @param nombre recibe el nombre nuevo o el mismo que tenía en caso de que no se haya cambiado
	 * @param ciudad recibe la ciudad nueva o la misma en caso de que no se haya cambiado
	 * @param pais recibe el país nuevo o el mismo en caso de que no haya cambiado
	 * @return devuelve true si lo consigue actualizar y false si no
	 */
	public static boolean updateTeam(Long id, String nombre, String ciudad, String pais) {
		boolean update = false;
		Session session= null;
		Team t = null;
		HibernateUtil.buildSessionFactory();
		try {
			HibernateUtil.openSessionAndBindToThread();
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			t = (Team)session.createQuery("from Team t where t.id =:id" , Team.class).setParameter("id", id ).uniqueResult();	
            
            
            t.setCiudad(ciudad);
            t.setNombre(nombre);
            t.setPais(pais);
            
            session.beginTransaction();
			session.saveOrUpdate(t);
			session.getTransaction().commit();
			update=true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return update;
	}

	/**
	 * Este método sirve para crear un nuevo equipo
	 * @param nombre recibe el nombre del equipo a crear 
	 * @param ciudad recibe la ciudad del equipo que va a crear
	 * @param pais recibe el pais del equipo que va a crear
	 * @return devuelve true si lo consigue crear y false si no
	 */
	public static boolean createTeam( String ciudad, String nombre,String pais) {
		boolean insert = false;
		Session session = null;
		HibernateUtil.buildSessionFactory();
		Team t = new Team();
		t.setCiudad(ciudad);
		t.setNombre(nombre);
		t.setPais(pais);

		try {
			HibernateUtil.openSessionAndBindToThread();
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.save(t);
			insert=true;
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return insert;
	}
}
