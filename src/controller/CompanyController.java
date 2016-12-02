package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import main.beans.Cartridge;
import main.beans.Company;
import main.dbutil.ConnectionManager;

@ManagedBean
@RequestScoped
public class CompanyController {

	private int company_id;
	private String name;
	
	public CompanyController(int company_id, String name) {
		this.company_id = company_id;
		this.name = name;
	}

	public CompanyController() {
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Company> allCompanys(){
		List<Company> companys = new ArrayList<>();
		SessionFactory factory = ConnectionManager.getOurInstance().getFactory();
		try {
			Session session = factory.openSession();
			session.beginTransaction();
			companys = session.createQuery("from Company").list();
			session.getTransaction().commit();
		} finally {
			factory.close();
		}
		return companys;
	}
	
	public String add() {
		SessionFactory factory = ConnectionManager.getOurInstance().getFactory();
		try {
			Session session = factory.openSession();
			session.beginTransaction();
			Company company = new Company();
			company.setName(getName());
			session.save(company);
			session.getTransaction().commit();
		} finally {
			factory.close();
		}
		return "allprinters";
	}
	
}
