
public class GlassesCorrection {
	private String correctionPowerRight;
	private String correctionPowerLeft;
	private CorrectionPurpose purpose;
	
	
	private enum CorrectionPurpose {
		FOR_DISTANCE, FOR_READING
	}
	
	private AppointmentCart appointmentCart;//association cardinality 1, GlassesCorrectionObject have  1 Appointment card
	
	public GlassesCorrection(String correctionPowerRight, String correctionPowerLeft, CorrectionPurpose purpose) {
		super();
		this.correctionPowerRight = correctionPowerRight;
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
			throw new Exception("This object of GlassesCorrection is already matched with a appointmentCart");
		}else {
			this.appointmentCart=appointmentCart;
		}
		
	};

	
	
}
