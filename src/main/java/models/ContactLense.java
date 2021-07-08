package models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "contact_lense")
public class ContactLense {

    private static List<ContactLense> entity;
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
    @Enumerated
    private WearingMode wearingMode;

    @ManyToMany
    private List<LensesCorrection> lensesCorrectionList;// association cardinality *

    /**
     * Required by Hibernate.
     */
    private ContactLense() {
    }

    public ContactLense(String brand, String name, String oxygenTransmission, WearingMode wearingMode) {
        super();
        this.brand = brand;
        this.name = name;
        this.oxygenTransmission = oxygenTransmission;
        this.wearingMode = wearingMode;
        addToEntity(this);
    }

    public static List<ContactLense> getEntity() {
        return entity;
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

    private void addToEntity(ContactLense contactLense) {
        if (entity == null) {
            entity = new ArrayList<>();
        }
        entity.add(contactLense);
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
