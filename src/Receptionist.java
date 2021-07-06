import java.time.LocalDate;
import java.util.List;

public class Receptionist extends Employee {

	private List<Training> trainingList;

	public Receptionist(String name, String surname, String pesel, String telephone, String email, String street,
			String city, String postalCode, String country, LocalDate hireDate, ContractType contractType,
			double monthlySalary, List<Training> trainingList) {
		super(name, surname, pesel, telephone, email, street, city, postalCode, country, hireDate, contractType,
				monthlySalary);
		this.trainingList = trainingList;
	}
	
	public List<Training> getTrainingList(){
		return trainingList;
	}
	
}
