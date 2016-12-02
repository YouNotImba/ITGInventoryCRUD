package main.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="company")
public class Company {
	
	@Id
	@Column(name="company_id")
	private int company_id;
	
	@Column(name="name")
	private String name;
	
	
	public Company() {
	}

	public Company(String name) {
		this.name = name;
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
	
		

}
