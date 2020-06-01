package org.iesalixar.amanuelbenallid.main;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.iesalixar.amanuelbenallid.helper.HibernateUtil;
import org.iesalixar.amanuelbenallid.model.Player;
import org.iesalixar.amanuelbenallid.model.Team;
import org.iesalixar.amanuelbenallid.model.User;

public class Main {

	public static void main(String[] args) {

		HibernateUtil.buildSessionFactory();
		HibernateUtil.openSessionAndBindToThread();
		
		Session session = null;
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		session.beginTransaction();
		Date fechanac = new Date();
		User u1 = new User();
		u1.setFullname("pepe");
		u1.setFechanac(fechanac);
		u1.setPassword("user111");
		u1.setRole("user");
		u1.setUsername("user1");
		
		
		User u2 = new User();
		u2.setFullname("juan");
		u2.setFechanac(fechanac);
		u2.setPassword("admin111");
		u2.setRole("admin");
		u2.setUsername("admin1");
		
		Team t1 = new Team();
		t1.setCiudad("Madrid");
		t1.setPais("España");
		t1.setNombre("Real Madrid");
		
		Team t2 = new Team();
		t2.setCiudad("Sevilla");
		t2.setPais("España");
		t2.setNombre("Real Betis");
		
		Player p1 = new Player();
		p1.setApellidos("perez perez");
		p1.setNombre("Juanito");
		p1.setDireccion("calle rosa");
		p1.setDni("12345678P");
		p1.setEquipo(t1);
		
		
		Set<Player> jugadores = new HashSet<Player>();
		t1.setJugadores(jugadores);
		
		
		session.save(t1);
		session.save(t2);
		session.save(p1);
		session.save(u1);
		session.save(u2);
		
		session.getTransaction().commit();
		
		
	}

}
