package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import main.beans.Company;
import main.beans.Model;
import main.beans.Printer;
import main.dbutil.ConnectionManager;
import main.util.SearchUtil;

@ManagedBean
@SessionScoped
public class PrinterController {

	private int id;
	private String title;
	private int model_id;
	private int company_id;
	private String ip;
	private String mac;
	private String location;
	private String searchString;

	public PrinterController(String title, int model, int company, String ip, String mac, String location) {
		this.title = title;
		this.model_id = model;
		this.company_id = company;
		this.ip = ip;
		this.mac = mac;
		this.location = location;
	}

	public PrinterController() {
		super();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getModel() {
		return model_id;
	}

	public void setModel(int model) {
		this.model_id = model;
	}

	public int getCompany() {
		return company_id;
	}

	public void setCompany(int company) {
		this.company_id = company;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	public List<Printer> getAllPrinters() {
		List<Printer> result = new ArrayList<>();
		SessionFactory factory = ConnectionManager.getOurInstance().getFactory();
		try {
			Session session = factory.openSession();
			session.beginTransaction();
			result = session.createQuery("from Printer").list();
			session.getTransaction().commit();
		} finally {
			factory.close();
		}
		return result;
	}

	public String add() {
		SessionFactory factory = ConnectionManager.getOurInstance().getFactory();
		try {
			Session session = factory.openSession();
			session.beginTransaction();
			Printer printer = new Printer();
			Model tempMod = (Model) session.createQuery("from Model where model_id = " + model_id).list().get(0);
			Company tempComp = (Company) session.createQuery("from Company where company_id = " + company_id).list()
					.get(0);
			printer.setTitle(getTitle());
			printer.setModel(tempMod);
			printer.setCompany(tempComp);
			printer.setIp(getIp());
			printer.setMac(getMac());
			printer.setLocation(getLocation());
			session.save(printer);
			session.getTransaction().commit();
		} finally {
			factory.close();
		}
		return "allprinters";
	}

	public List<Printer> search() {
		List<Printer> result = new ArrayList<>();
		SessionFactory factory = ConnectionManager.getOurInstance().getFactory();
		try {
			Session session = factory.openSession();
			session.beginTransaction();
			List<Printer> tempList = new ArrayList<>();
			tempList = session.createQuery("from Printer").list();
			session.getTransaction().commit();
//			for (Printer printer : tempList) {
//				if (
//						printer.getTitle().toLowerCase().contains(searchString.toLowerCase())
//						|| printer.getIp().toLowerCase().contains(searchString.toLowerCase())
//						|| printer.getMac().toLowerCase().contains(searchString.toLowerCase())
//						|| printer.getLocation().toLowerCase().contains(searchString.toLowerCase())
//						|| printer.getModel().getName().toLowerCase().contains(searchString.toLowerCase())
//						|| printer.getCompany().getName().toLowerCase().contains(searchString.toLowerCase())) {
//
//					result.add(printer);
//
//				}
//			}
			result = SearchUtil.searchPrinter(searchString, tempList);
		} finally {
			factory.close();
		}
		return result;
	}

	public String delete() {
		SessionFactory factory = ConnectionManager.getOurInstance().getFactory();
		Map<String, String> param = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		int idToDelete = Integer.parseInt(param.get("id"));
		System.out.println("id to delete " + idToDelete);
		try {
			Session session = factory.openSession();
			session.beginTransaction();
			Printer printer = session.get(Printer.class, idToDelete);
			session.delete(printer);
			session.getTransaction().commit();
		} finally {
			factory.close();
		}
		return "allprinters";
	}
	
	public String setEdit(){
		SessionFactory factory = ConnectionManager.getOurInstance().getFactory();
		Map<String, String> param = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		int idToEdit = Integer.parseInt(param.get("editid"));
		System.out.println("id to edit " + idToEdit);
		try {
			Session session = factory.openSession();
			session.beginTransaction();
			Printer printer = session.get(Printer.class, idToEdit);
			this.setId(idToEdit);
			this.setTitle(printer.getTitle());
			this.setModel(printer.getModel().getModel_id());
			this.setCompany(printer.getCompany().getCompany_id());
			this.setIp(printer.getIp());
			this.setMac(printer.getMac());
			this.setLocation(printer.getLocation());
			session.getTransaction().commit();
		} finally {
			factory.close();
		}
		return "edit";
	}
	
	public String save(){
		SessionFactory factory = ConnectionManager.getOurInstance().getFactory();
			
		try {
			Session session = factory.openSession();
			session.beginTransaction();
			Model tempMod = (Model) session.createQuery("from Model where model_id = " + model_id).list().get(0);
			Company tempComp = (Company) session.createQuery("from Company where company_id = " + company_id).list()
					.get(0);
			Printer printer = session.get(Printer.class, id);
			printer.setTitle(getTitle());
			printer.setModel(tempMod);
			printer.setCompany(tempComp);
			printer.setIp(getIp());
			printer.setMac(getMac());
			printer.setLocation(getLocation());
			session.getTransaction().commit();
		} finally {
			factory.close();
		}
		
		return "allprinters";
	}
}
