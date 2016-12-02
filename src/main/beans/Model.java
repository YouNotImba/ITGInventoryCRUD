package main.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="model")
public class Model {
	
	@Id
	@Column(name="model_id")
	private int model_id;
	
	@Column(name="name")
	private String name;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="cartridge_id")
	private Cartridge cartridge;

	public Model(String name, Cartridge cartridge) {
		this.name = name;
		this.cartridge = cartridge;
	}

	public Model() {
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

	public Cartridge getCartridge() {
		return cartridge;
	}

	public void setCartridge(Cartridge cartridge) {
		this.cartridge = cartridge;
	}
	
	
}
