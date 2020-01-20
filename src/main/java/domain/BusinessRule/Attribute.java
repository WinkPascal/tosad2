package domain.BusinessRule;

public class Attribute {

	private String naam;
	private String entiteit;

	public String getEntiteit() {
		return entiteit;
	}

	public Attribute(String naam, String entiteit) {
		this.naam = naam;
		this.entiteit = entiteit;
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
