package main.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cartridges")
public class Cartridge {

	@Id
	@Column(name="cartridge_id")
	private int cartridge_id;
	
	@Column(name="name")
	private String name;

	public Cartridge(String name) {
		this.name = name;
	}

	public Cartridge() {
	}

	public int getCartridge_id() {
		return cartridge_id;
	}

	public void setCartridge_id(int cartridge_id) {
		this.cartridge_id = cartridge_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
		
}
