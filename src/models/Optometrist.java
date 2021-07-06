package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Optometrist extends Employee {
	private String optometristNumber;
	private List<Appointment> appointmentList;//association cardinality *

	public Optometrist(String name, String surname, String pesel, String telephone, String email, String street,
			String city, String postalCode, String country, LocalDate hireDate, ContractType contractType,
			double monthlySalary, String optometristNumber) {
		super(name, surname, pesel, telephone, email, street, city, postalCode, country, hireDate, contractType,
				monthlySalary);
		this.optometristNumber = optometristNumber;
	}

	public void addAppointmentToList(Appointment appointment){
		if(appointmentList== null){
			appointmentList = new ArrayList<>();
		}
		appointmentList.add(appointment);
	}

	public String getOptometristNumber() {
		return optometristNumber;
	}
		
}
