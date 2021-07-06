package models;

import java.util.ArrayList;
import java.util.List;

public class LensesCorrection {
	private String correctionPowerRight;
	private String correctionPowerLeft;
	private List<ContactLense> contactLensesList;// association, cardinality *
	
	private AppointmentCart appointmentCart;//association cardinality 1

	public LensesCorrection(String correctionPowerRight, String correctionPowerLeft, ContactLense... contactLenses) {
		super();
		this.correctionPowerRight = correctionPowerRight;
		this.correctionPowerLeft = correctionPowerLeft;
		if(contactLenses.length<1) {
			throw new IllegalArgumentException("A least one contact lense is required");
		}
		for(ContactLense contactLense: contactLenses) {
			addContactLenses(contactLense);
		}
	}

	private void addContactLenses(ContactLense contactLense) {
		if (contactLensesList == null) {
			contactLensesList = new ArrayList<ContactLense>();
			contactLensesList.add(contactLense);
		} else {
			contactLensesList.add(contactLense);
			contactLense.add(this);// add reverse connection
		}
	}

	public String getCorrectionPowerLeft() {
		return correctionPowerLeft;
	}

	public String getCorrectionPowerRight() {
		return correctionPowerRight;
	}

	public void matchLensesCorrectionToAppointmentCart(AppointmentCart appointmentCart) throws Exception {
		if(!(this.appointmentCart == null)) {
			throw new Exception("This object of classes.LensesCorrection is already matched with a appointmentCart");
		}else {
			this.appointmentCart=appointmentCart;
		}
	}

}
