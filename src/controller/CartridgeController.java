package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import main.beans.Cartridge;
import main.dbutil.ConnectionManager;

@ManagedBean
@RequestScoped
public class CartridgeController {

	private int id;
	private String name;

	public CartridgeController(String name) {
		this.name = name;
	}

	public CartridgeController() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Cartridge> allModels(){
		List<Cartridge> cartridges = new ArrayList<>();
		SessionFactory factory = ConnectionManager.getOurInstance().getFactory();
		try {
			Session session = factory.openSession();
			session.beginTransaction();
			cartridges = session.createQuery("from Cartridge").list();
			session.getTransaction().commit();
		} finally {
			factory.close();
		}
		return cartridges;
	}	
	
	public String add() {
		SessionFactory factory = ConnectionManager.getOurInstance().getFactory();
		try {
			Session session = factory.openSession();
			session.beginTransaction();
			Cartridge cartridge = new Cartridge();
			cartridge.setName(getName());
			session.save(cartridge);
			session.getTransaction().commit();
		} finally {
			factory.close();
		}
		return "allprinters";
	}
	
}
