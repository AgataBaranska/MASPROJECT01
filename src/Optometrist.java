import java.time.LocalDate;

public class Optometrist extends Employee {
	private String optometristNumber;

	public Optometrist(String name, String surname, String pesel, String telephone, String email, String street,
			String city, String postalCode, String country, LocalDate hireDate, ContractType contractType,
			double monthlySalary, String optometristNumber) {
		super(name, surname, pesel, telephone, email, street, city, postalCode, country, hireDate, contractType,
				monthlySalary);
		this.optometristNumber = optometristNumber;
	}

	public String getOptometristNumber() {
		return optometristNumber;
	}
		
}
