package models;

import java.util.ArrayList;
import java.util.List;

public class ContactLense {
    private String brand;
    private String name;
    private String oxygenTransmission;
    private WearingMode wearingMode;
    private List<LensesCorrection> lensesCorrectionList;// association cardinality *

    private static List<ContactLense> entity;
    public enum WearingMode {
        DAILY("Daily"),
        MONTHLY("Monthly");
        private String label;

        WearingMode(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }
    public ContactLense(String brand, String name, String oxygenTransmission, WearingMode wearingMode) {
        super();
        this.brand = brand;
        this.name = name;
        this.oxygenTransmission = oxygenTransmission;
        this.wearingMode = wearingMode;
        addToEntity(this);
    }

    private void addToEntity(ContactLense contactLense) {
        if(entity == null){
            entity = new ArrayList<>();
        }
        entity.add(contactLense);
    }
    public static List<ContactLense> getEntity(){
        return entity;
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
        if(lensesCorrectionList ==null){
            lensesCorrectionList = new ArrayList<LensesCorrection>();
        }
        if (!lensesCorrectionList.contains(lensesCorrection)) {
            lensesCorrectionList.add(lensesCorrection);
        }

    }

    @Override
    public String toString() {
        return  brand + '\'' +
                ", '" + name + '\'';
    }
}
