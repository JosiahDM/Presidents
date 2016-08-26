package web;

public class President {
	private int termNumber;
	private String firstName, middleName, lastName;
	private int startYear, endYear;
	private String party;
	private String image;

	public President(int termNumber, String firstName, String middleName, String lastName, int startYear, int endYear,
			String party, String image) {
		this.termNumber = termNumber;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.startYear = startYear;
		this.endYear = endYear;
		this.party = party;
		this.image = image;
	}

	public int getTermNumber() {
		return termNumber;
	}

	public void setTermNumber(int termNumber) {
		this.termNumber = termNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getStartYear() {
		return startYear;
	}

	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}

	public int getEndYear() {
		return endYear;
	}

	public void setEndYear(int endYear) {
		this.endYear = endYear;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

	public void display() {
		System.out.printf("%-5d %-15s %-15s %-15s %-5d %-5d %-30s %n", termNumber, firstName, middleName, lastName,
				startYear, endYear, party);
	}

	@Override
	public String toString() {
		return "President [termNumber=" + termNumber + ", firstName=" + firstName + ", middleName=" + middleName
				+ ", lastName=" + lastName + ", startYear=" + startYear + ", endYear=" + endYear + ", party=" + party
				+ "]";
	}

}
