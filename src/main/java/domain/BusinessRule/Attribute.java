package domain.BusinessRule;

public class Attribute {

	private String naam;
	private String value;
	private String entiteit;

	public String getValue() {
		return value;
	}

	public String getEntiteit() {
		return entiteit;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getNaam() {
		return naam;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setEntiteit(String entiteit) {
		this.entiteit = entiteit;
	}
}
