package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "contact_lense")
public class ContactLense {

    private static List<ContactLense> extent = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Basic
    private String brand;
    @Basic
    private String name;
    @Column(name = "oxygen_transmission")
    private String oxygenTransmission;
    @Column(name = "wearing_mode")
    @Enumerated(EnumType.STRING)
    private WearingMode wearingMode;
    @ManyToMany
    //One type of contact lense can be in 0 or more LensesCorrection
    private List<LensesCorrection> lensesCorrectionList;// association cardinality *

    /**
     * Required by Hibernate.
     */
    ContactLense() {
    }

    public ContactLense(String brand, String name, String oxygenTransmission, WearingMode wearingMode) {
        super();
        this.brand = brand;
        this.name = name;
        this.oxygenTransmission = oxygenTransmission;
        this.wearingMode = wearingMode;
        addToExtent(this);
    }

    public static List<ContactLense> getExtent() {
        return extent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOxygenTransmission() {
        return oxygenTransmission;
    }

    public void setOxygenTransmission(String oxygenTransmission) {
        this.oxygenTransmission = oxygenTransmission;
    }

    public WearingMode getWearingMode() {
        return wearingMode;
    }

    public void setWearingMode(WearingMode wearingMode) {
        this.wearingMode = wearingMode;
    }

    public List<LensesCorrection> getLensesCorrectionList() {
        return lensesCorrectionList;
    }

    public void setLensesCorrectionList(List<LensesCorrection> lensesCorrectionList) {
        this.lensesCorrectionList = lensesCorrectionList;
    }

    private void addToExtent(ContactLense contactLense) {
        extent.add(contactLense);
    }

    public void add(LensesCorrection lensesCorrection) {
        if (lensesCorrectionList == null) {
            lensesCorrectionList = new ArrayList<LensesCorrection>();
        }
        if (!lensesCorrectionList.contains(lensesCorrection)) {
            lensesCorrectionList.add(lensesCorrection);
        }
    }

    @Override
    public String toString() {
        return brand + '\'' +
                ", '" + name + '\'';
    }

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
}
