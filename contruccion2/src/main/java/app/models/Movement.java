package app.models;

import java.sql.Date;

public class Movement {
	private int id;
	private long personId;
	private int materialId;
	private Date movenmentDate;
	private String type;
	
	public Movement() {
		this.movenmentDate=new Date(System.currentTimeMillis());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getPersonId() {
		return personId;
	}

	public void setPersonId(long personId) {
		this.personId = personId;
	}

	public int getMaterialId() {
		return materialId;
	}

	public void setMaterialId(int materialId) {
		this.materialId = materialId;
	}

	public Date getMovenmentDate() {
		return movenmentDate;
	}

	public void setMovenmentDate(Date movenmentDate) {
		this.movenmentDate = movenmentDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
