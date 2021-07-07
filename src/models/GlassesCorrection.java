package models;

public class GlassesCorrection {
	private String correctionPowerRight;
	private String correctionPowerLeft;
	private CorrectionPurpose purpose;
	
	
	public enum CorrectionPurpose {
		FOR_DISTANCE("For distance"),
		FOR_READING("For reading");

		private String label;
		CorrectionPurpose(String label) {
			this.label = label;
		}
		public String toString() {
			return label;
		}
	}
	
	private AppointmentCart appointmentCart;//association cardinality 1, GlassesCorrectionObject have  1 Appointment card
	
	public GlassesCorrection(String correctionPowerRight, String correctionPowerLeft, CorrectionPurpose purpose) {
		super();
		this.correctionPowerRight = correctionPowerRight;
		this.correctionPowerLeft = correctionPowerLeft;
		this.purpose = purpose;
	}

	public String getCorrectionPowerRight() {
		return correctionPowerRight;
	}
	public String getCorrectionPowerLeft() {
		return correctionPowerLeft;
	}

	public CorrectionPurpose getPurpose() {
		return purpose;
	}

	public void matchGlassesCorrectionToAppointmentCart(AppointmentCart appointmentCart) throws Exception {
		if(!(this.appointmentCart == null)) {
			throw new Exception("This object of classes.GlassesCorrection is already matched with a appointmentCart");
		}else {
			this.appointmentCart=appointmentCart;
		}
		
	};

	@Override
	public String toString() {
		return "REye:" + correctionPowerRight  +
				", LEye:" + correctionPowerLeft +
				", Purpose: " + purpose;
	}
}
