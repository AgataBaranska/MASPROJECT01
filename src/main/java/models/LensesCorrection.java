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

    @ManyToMany
    @JoinTable(
            name="correction_lenses",
            joinColumns = @JoinColumn(name ="lenses_correction_id"),
            inverseJoinColumns = @JoinColumn(name = "contact_lense_id"))
    private List<ContactLense> contactLenseList;// association, cardinality *

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ContactLense> getContactLenseList() {
        return contactLenseList;
    }

    public void setContactLenseList(List<ContactLense> contactLenseList) {
        this.contactLenseList = contactLenseList;
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

    public List<ContactLense> getContactLensesList() {
       return contactLenseList;
    }


    @ManyToOne
    public AppointmentCart getAppointmentCart() {
        return appointmentCart;
    }

    public void setAppointmentCart(AppointmentCart appointmentCart) {
        this.appointmentCart = appointmentCart;
    }

    private void addContactLenses(ContactLense contactLense) {

        if (contactLenseList == null) {
            contactLenseList = new ArrayList<ContactLense>();

        }
        contactLenseList.add(contactLense);

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
