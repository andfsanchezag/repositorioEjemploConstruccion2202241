package app.dto;

import java.sql.Date;

import app.models.Material;

public class MaterialDto {
	private int id;
	private String tittle;
	private Date registerDate;
	private int quantity;
	private int actQuantity;
	
	
	public MaterialDto(String tittle, int quantity) {
		this.tittle = tittle;
		this.quantity = quantity;
		this.actQuantity = quantity;
		this.registerDate=new Date(System.currentTimeMillis());	
	}

	

	public MaterialDto(int id) {
		super();
		this.id=id;
	}



	public MaterialDto(Material material) {
		this.id=material.getId();
		this.actQuantity=material.getActQuantity();
		this.quantity=material.getQuantity();
		this.tittle=material.getTittle();
	}



	public MaterialDto() {
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
