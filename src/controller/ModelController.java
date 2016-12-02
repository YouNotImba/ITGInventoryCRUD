package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import main.beans.Cartridge;
import main.beans.Model;
import main.dbutil.ConnectionManager;

@ManagedBean
@RequestScoped
public class ModelController {
	
	private int model_id;
	private String name;
	private int cartridge_id;

	public ModelController(int model_id, String name, int cartridge_id) {
		this.model_id = model_id;
		this.name = name;
		this.cartridge_id = cartridge_id;
	}

	public ModelController() {
		super();
	}

	public int getModel_id() {
		return model_id;
	}

	public void setModel_id(int model_id) {
		this.model_id = model_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCartridge_id() {
		return cartridge_id;
	}

	public void setCartridge_id(int cartridge_id) {
		this.cartridge_id = cartridge_id;
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Model> allModels(){
		List<Model> models = new ArrayList<>();
		SessionFactory factory = ConnectionManager.getOurInstance().getFactory();
		try {
			Session session = factory.openSession();
			session.beginTransaction();
			models = session.createQuery("from Model").list();
			session.getTransaction().commit();
		} finally {
			factory.close();
		}
		return models;
	}	
	
	public String add() {
		SessionFactory factory = ConnectionManager.getOurInstance().getFactory();
		try {
			Session session = factory.openSession();
			session.beginTransaction();
			Model model = new Model();
			Cartridge temp = (Cartridge) session.createQuery("from Cartridge where id = " + cartridge_id).list().get(0);
			model.setName(getName());
			model.setCartridge(temp);
			session.save(model);
			session.getTransaction().commit();
		} finally {
			factory.close();
		}
		return "allprinters";
	}
}
