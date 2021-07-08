package models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lenses_correction")
public class LensesCorrection {
    private int id;
    private String correctionPowerRight;
    private String correctionPowerLeft;
    private List<LensesCorrectionContactLense> lensesCorrectionContactLense;// association, cardinality *
    private AppointmentCart appointmentCart;//association cardinality 1

    /**
     * Required by Hibernate.
     */
    private LensesCorrection() {
    }

    public LensesCorrection(String correctionPowerRight, String correctionPowerLeft, ContactLense... contactLenses) {
        super();
        this.correctionPowerRight = correctionPowerRight;
        this.correctionPowerLeft = correctionPowerLeft;
        if (contactLenses.length < 1) {
            throw new IllegalArgumentException("A least one contact lense is required");
        }
        for (ContactLense contactLense : contactLenses) {
            addContactLenses(contactLense);
        }
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

    @Column(name = "correction_power_right")
    public String getCorrectionPowerRight() {
        return correctionPowerRight;
    }

    public void setCorrectionPowerRight(String correctionPowerRight) {
        this.correctionPowerRight = correctionPowerRight;
    }

    @Column(name = "correction_power_left")
    public String getCorrectionPowerLeft() {
        return correctionPowerLeft;
    }

    public void setCorrectionPowerLeft(String correctionPowerLeft) {
        this.correctionPowerLeft = correctionPowerLeft;
    }

    @OneToMany(mappedBy = "lensesCorrection", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<LensesCorrectionContactLense> getLensesCorrectionContactLense() {
        return lensesCorrectionContactLense;
    }

    public void setLensesCorrectionContactLense(List<LensesCorrectionContactLense> lensesCorrectionContactLense) {
        this.lensesCorrectionContactLense = lensesCorrectionContactLense;
    }


    public List<ContactLense> getContactLensesList() {
        List<ContactLense> contactLensesList = new ArrayList<>();
        for (LensesCorrectionContactLense connectingObject:lensesCorrectionContactLense) {
            contactLensesList.add(connectingObject.getContactLense());
        }
        return contactLensesList;
    }



    @ManyToOne
    public AppointmentCart getAppointmentCart() {
        return appointmentCart;
    }

    public void setAppointmentCart(AppointmentCart appointmentCart) {
        this.appointmentCart = appointmentCart;
    }

    private void addContactLenses(ContactLense contactLense) {

        if (lensesCorrectionContactLense == null) {
            lensesCorrectionContactLense = new ArrayList<LensesCorrectionContactLense>();

            LensesCorrectionContactLense connectingObject =new LensesCorrectionContactLense(this,contactLense);
            lensesCorrectionContactLense.add(connectingObject);
            contactLense.add(connectingObject);

        } else {
            lensesCorrectionContactLense.add(new LensesCorrectionContactLense(this,contactLense));

        }
    }

    public void matchLensesCorrectionToAppointmentCart(AppointmentCart appointmentCart) throws Exception {
        if (!(this.appointmentCart == null)) {
            throw new Exception("This object of classes.LensesCorrection is already matched with a appointmentCart");
        } else {
            this.appointmentCart = appointmentCart;
        }
    }

    @Override
    public String toString() {
        return "LensesCorrection{" +
                "correctionPowerRight='" + correctionPowerRight + '\'' +
                ", correctionPowerLeft='" + correctionPowerLeft + '\'' +
                '}';
    }
}
