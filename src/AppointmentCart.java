import java.util.ArrayList;
import java.util.List;

public class AppointmentCart {
	private int idNumber;
	private Optometrist optometrist;
	private List<GlassesCorrection> glassesCorrection;
	private List<LensesCorrection> lensesCorrection;
	private String interview;
	private String recommendations;
	
	public static int idCounter = 0;

//appointment card may have 0 or more GlassesCorrection prescribed
	private List<GlassesCorrection> glassesCorrectionList;// association cardinality [0..1]

//appointment card may have 0 or more LensesCorrection prescribed
	private List<LensesCorrection> lensesCorrectionList;// association cardinality [0..1]

	public AppointmentCart(Optometrist optometrist, Patient patient,String interview, String recommendations) {
		super();
		this.interview = interview;
		this.recommendations = recommendations;
		idNumber = generateIdNumber();
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

	public List<GlassesCorrection> getGlassesCorrectionList() {
		return glassesCorrectionList;
	}

	public int generateIdNumber() {
		return ++idCounter;
	}

}
