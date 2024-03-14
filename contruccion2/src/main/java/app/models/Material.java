package app.models;

import java.sql.Date;

public class Material {
	private int id;
	private String tittle;
	private Date registerDate;
	private int quantity;
	private int actQuantity;
	
	public Material() {
		this.registerDate=new Date(System.currentTimeMillis());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTittle() {
		return tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getActQuantity() {
		return actQuantity;
	}

	public void setActQuantity(int actQuantity) {
		this.actQuantity = actQuantity;
	}
}
