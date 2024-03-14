package app.dto;

public class LoanDto {
	private int id;
	private PersonDto person;
	private MaterialDto material;

	
	
	public LoanDto() {
	}

	public LoanDto(long person, int material) {
		this.person = new PersonDto();
		this.person.setId(person);
		this.material = new MaterialDto();
		this.material.setId(material);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PersonDto getPerson() {
		return person;
	}

	public void setPerson(PersonDto person) {
		this.person = person;
	}

	public MaterialDto getMaterial() {
		return material;
	}

	public void setMaterial(MaterialDto material) {
		this.material = material;
	}
}
