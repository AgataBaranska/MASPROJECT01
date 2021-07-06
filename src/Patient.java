import java.util.ArrayList;
import java.util.List;

public class Patient extends Person {

	private int idNumber;
	private RodoForm rodoForm;//association, cardinality 1
	private static List<Patient> extent;
	private static int idCounter = 0;
	
	public Patient(String name, String surname, String pesel, String telephone, String email, String street,
			String city, String postalCode, String country) {
		super(name, surname, pesel, telephone, email, street, city, postalCode, country);
		this.idNumber = generateIdNumber();
		this.rodoForm = generateRodoForm();
		addPatient(this);
	}

	private static void addPatient(Patient pacjent) {
		if(extent == null) {
			extent = new ArrayList<Patient>();
		}else {
			extent.add(pacjent);
		}
	}
	private static void removePatient(Patient patient) {
		extent.remove(patient);
	}
	
	private static List<Patient> getExtent(){
		return extent;
	}

	private RodoForm generateRodoForm() {
		return new RodoForm("SignatureTemplate", this);
	}

	private int generateIdNumber() {
		return ++idCounter;
	}


	

}
