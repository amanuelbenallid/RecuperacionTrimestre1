package org.iesalixar.amanuelbenallid.DAO;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.iesalixar.amanuelbenallid.helper.HibernateUtil;
import org.iesalixar.amanuelbenallid.model.Player;
import org.iesalixar.amanuelbenallid.model.Team;
/**
 * Clase que tiene todos los métodos para el CRUD de los jugadores
 * @author Alberto
 *
 */
public class PlayerDAO {
	/**
	 * Este método lee todos los jugadores de la base de datos
	 * @return List<Player> Devuelve una lista que contiene los jugadores
	 */
	@SuppressWarnings("unchecked")
	public static Set<Player> readPlayer() {

		Set<Player> jugadores = new HashSet<Player>();
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			jugadores.addAll(session.createQuery("FROM Player p").list());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jugadores;
	}

	/**
	 * Este método sirve para crear un nuevo jugador
	 * @param nombre recibe el nombre del jugador
	 * @param apellidos recibe los apellidos del jugador
	 * @param dni recibe el DNI del jugador
	 * @param direccion recibe la dirección del jugador
	 * @param equipo recibe la ID del equipo al que pertenece el jugador
	 * @return devuelve true si lo consigue crear y false si no
	 */
	public static boolean createPlayer( String nombre, String apellidos, String dni, String direccion, Long idEquipo) {
		boolean insert = false;
		Session session = null;
		Player p = new Player();
		p.setApellidos(apellidos);
		p.setDireccion(direccion);
		p.setDni(dni);
		p.setNombre(nombre);
		HibernateUtil.buildSessionFactory();
		Team t = null;
		try {
			HibernateUtil.openSessionAndBindToThread();
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			t = (Team)session.createQuery("from Team t where t.id =:idE" , Team.class).setParameter("idE", idEquipo ).uniqueResult();
			p.setEquipo(t);
			
			session.beginTransaction();
			session.save(p);
			session.getTransaction().commit();
			insert=true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return insert;
	}


	/**
	 * Este método cambia el equipo en el que juega un jugador
	 * @param idjugador recibe la ID del jugador
	 * @param equiponuevo recibe la ID del equipo que se le quiere poner al jugador
	 * @return devuelve true si lo consigue actualizar y false si no
	 */
	public static boolean updatePlayer(Long idjugador, Long equiponuevo) {
		boolean update = false;
		Session session= null;
		HibernateUtil.buildSessionFactory();
		Team t =null;
		Player p = null;
		try {
			HibernateUtil.openSessionAndBindToThread();
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			t = (Team)session.createQuery("from Team t where t.id =:id" , Team.class).setParameter("id", equiponuevo ).uniqueResult();	
            p = (Player) session.createQuery("from Player p where p.id=:idP", Player.class).setParameter("idP", idjugador).uniqueResult(); 
            p.setEquipo(t);

            session.beginTransaction();
			session.saveOrUpdate(p);
			session.getTransaction().commit();
			update=true;
			
            
           
		} catch (Exception e) {
			e.printStackTrace();
		}
		return update;

	}

	/**
	 * Este método borra un jugador que se le pasa por parámetros la ID
	 * @param idjugador recibe la ID del jugador que se va a borrar
	 * @return devuelve true si lo consigue borrar y false si no
	 */
	public static boolean deletePlayer(Long idjugador) {
		boolean delete = false;
		HibernateUtil.buildSessionFactory();
		Session session = null;
		Player p = null;
		try {
			HibernateUtil.openSessionAndBindToThread();
			session = HibernateUtil.getSessionFactory().getCurrentSession();
            p = (Player) session.createQuery("from Player p where p.id=:idP", Player.class).setParameter("idP", idjugador).uniqueResult(); 
			session.beginTransaction();
			session.delete(p);
			delete=true;
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return delete;

	}
}
