import java.util.List;

public class ContactLense {
private String brand;
private String name;
private String oxygenTransmission;
private WearingMode wearingMode;

private enum WearingMode{
	DAILY,
	MONTHLY
}

private List<LensesCorrection> lensesCorrectionList;// association cardinality *

public ContactLense(String brand, String name, String oxygenTransmission, WearingMode wearingMode) {
	super();
	this.brand = brand;
	this.name = name;
	this.oxygenTransmission = oxygenTransmission;
	this.wearingMode = wearingMode;
}

public String getBrand() {
	return brand;
}

public String getName() {
	return name;
}

public String getOxygenTransmission() {
	return oxygenTransmission;
}

public WearingMode getWearingMode() {
	return wearingMode;
}

public void add(LensesCorrection lensesCorrection) {
	if(!lensesCorrectionList.contains(lensesCorrection)) {
		lensesCorrectionList.add(lensesCorrection);
	}
	
}




}
