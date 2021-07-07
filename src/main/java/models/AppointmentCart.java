package models;

import java.util.ArrayList;
import java.util.List;

public class AppointmentCart {
	private int idNumber;
	private Patient patient;
	private List<GlassesCorrection> glassesCorrection;
	private List<LensesCorrection> lensesCorrection;
	private String interview;
	private String recommendations;
	
	public static int idCounter = 0;

//appointment card may have 0 or more classes.GlassesCorrection prescribed
	private List<GlassesCorrection> glassesCorrectionList;// association cardinality [0..1]

//appointment card may have 0 or more classes.LensesCorrection prescribed
	private List<LensesCorrection> lensesCorrectionList;// association cardinality [0..1]

	public AppointmentCart(Patient patient) {
		super();
		this.patient = patient;

		idNumber = generateIdNumber();
	}


	public void addInterview(String interview){
		this.interview = interview;
	}
	public void addRecommendations(String recommendations){
		this.recommendations = recommendations;
	}

	public void addLensesCorrection(LensesCorrection lensesCorrection) {
		if (lensesCorrectionList == null) {
			lensesCorrectionList = new ArrayList<LensesCorrection>();
		}
		lensesCorrectionList.add(lensesCorrection);
		try {
			lensesCorrection.matchLensesCorrectionToAppointmentCart(this);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void addGlassesCorrection(GlassesCorrection glassesCorrection) {
		if (glassesCorrectionList == null) {
			glassesCorrectionList = new ArrayList<GlassesCorrection>();
		}
		
		glassesCorrectionList.add(glassesCorrection);
		try {
			glassesCorrection.matchGlassesCorrectionToAppointmentCart(this);//add reverse connection
		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
	}

	public Patient getPatient(){
		return patient;
	}

	public List<GlassesCorrection> getGlassesCorrectionList() {
		return glassesCorrectionList;
	}

	public int generateIdNumber() {
		return ++idCounter;
	}

}
