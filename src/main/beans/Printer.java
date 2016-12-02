package main.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="printers")
public class Printer {
	
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="title")
	private String title;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="model_id")
	private Model model;
	
	@ManyToOne
	@JoinColumn(name="company_id")
	private Company company;
	
	@Column(name="ip")
	private String ip;
	
	@Column(name="mac")
	private String mac;
	
	@Column(name="location")
	private String location;

	public Printer(String title, Model model, Company company, String ip, String mac, String location) {
		this.title = title;
		this.model = model;
		this.company = company;
		this.ip = ip;
		this.mac = mac;
		this.location = location;
	}

	public Printer() {
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

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
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
	
		
}
