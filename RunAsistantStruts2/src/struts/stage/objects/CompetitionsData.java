package struts.stage.objects;

public class CompetitionsData {
	private String firstName;
	private String lastName;
	int km;

	public CompetitionsData(String firstName, String lastName, int km){
		this.firstName = firstName;
		this.lastName = lastName;
		this.km = km;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getKm() {
		return km;
	}

	public void setKm(int km) {
		this.km = km;
	}
}
