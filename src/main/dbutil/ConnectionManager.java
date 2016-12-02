package main.dbutil;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import main.beans.Book;
import main.beans.Printer;

public class ConnectionManager {
	
	private static ConnectionManager ourInstance = new ConnectionManager();
	
	private ConnectionManager(){
		
	}

	public static ConnectionManager getOurInstance() {
		return ourInstance;
	}

	public SessionFactory getFactory(){
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		return factory;
	}
	
	/*public static void main(String[] args) {
		Session session = getOurInstance().getFactory().openSession();
		session.beginTransaction();
		List<Printer> printers = session.createQuery("from Printer").list();
		System.out.println(printers);
		session.getTransaction().commit();
	}*/
}
