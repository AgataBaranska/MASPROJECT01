package models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "contact_lense")
public class ContactLense {

    private static List<ContactLense> entity;
    private int id;
    private String brand;
    private String name;
    private String oxygenTransmission;
    private WearingMode wearingMode;
    private List<LensesCorrectionContactLense> lensesCorrectionContactLenseList;// association cardinality *

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

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    public String getBrand() {
        return brand;
    }


    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "oxygen_transmission")
    public String getOxygenTransmission() {
        return oxygenTransmission;
    }

    public void setOxygenTransmission(String oxygenTransmission) {
        this.oxygenTransmission = oxygenTransmission;
    }

    @Column(name = "wearing_mode")
    @Enumerated
    public WearingMode getWearingMode() {
        return wearingMode;
    }

    public void setWearingMode(WearingMode wearingMode) {
        this.wearingMode = wearingMode;
    }


    public List<LensesCorrection> getLensesCorrectionList() {
        List<LensesCorrection> lensesCorrectionList = new ArrayList<>();

        for (LensesCorrectionContactLense connectingObject:
             lensesCorrectionContactLenseList) {
            lensesCorrectionList.add(connectingObject.getLensesCorrection());
        }
        return lensesCorrectionList;
    }

    @OneToMany(mappedBy = "contactLense", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<LensesCorrectionContactLense> getLensesCorrectionContactLenseList() {
        return lensesCorrectionContactLenseList;
    }

    public void setLensesCorrectionContactLenseList(List<LensesCorrectionContactLense> lensesCorrectionContactLenseList) {
        this.lensesCorrectionContactLenseList = lensesCorrectionContactLenseList;
    }

    private void addToEntity(ContactLense contactLense) {
        if (entity == null) {
            entity = new ArrayList<>();
        }
        entity.add(contactLense);
    }

    public void add(LensesCorrectionContactLense connectingObject)  {
        if (lensesCorrectionContactLenseList == null) {
            lensesCorrectionContactLenseList = new ArrayList<LensesCorrectionContactLense>();
        }
        lensesCorrectionContactLenseList.add(connectingObject);

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
