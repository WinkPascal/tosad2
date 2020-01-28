package domain.BusinessRule;

import java.util.List;

public class Attribute {
	private int id;
	private String naam;
	private String entiteit;
	private List<String> values;

	public String getEntiteit() {
		return entiteit;
	}

	public Attribute(int id, String naam, String entiteit, List<String> values) {
		this.id = id;
		this.naam = naam;
		this.entiteit = entiteit;
		this.values = values;
	}

	public List<String> getValues(){
		return this.values;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getNaam() {
		return naam;
	}

	public void setEntiteit(String entiteit) {
		this.entiteit = entiteit;
	}
}
