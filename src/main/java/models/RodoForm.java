package models;

import java.time.LocalDate;

public class RodoForm {
	private LocalDate date;
	private String signature;
	private Patient patient;//association cardinality 1
	
	public RodoForm( String signature, Patient patient) {
		super();
		this.patient = patient;
		this.date = LocalDate.now();
		this.signature = signature;
	}
	
	
}


