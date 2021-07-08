package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lenses_correction")
public class LensesCorrection {
    private String correctionPowerRight;
    private String correctionPowerLeft;
    private List<ContactLense> contactLensesList;// association, cardinality *
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

    @OneToMany(mappedBy = "lense_correction", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<ContactLense> getContactLensesList() {
        return contactLensesList;
    }

    public void setContactLensesList(List<ContactLense> contactLensesList) {
        this.contactLensesList = contactLensesList;
    }

    @ManyToOne
    public AppointmentCart getAppointmentCart() {
        return appointmentCart;
    }

    public void setAppointmentCart(AppointmentCart appointmentCart) {
        this.appointmentCart = appointmentCart;
    }

    private void addContactLenses(ContactLense contactLense) {
        if (contactLensesList == null) {
            contactLensesList = new ArrayList<ContactLense>();
            contactLensesList.add(contactLense);
        } else {
            contactLensesList.add(contactLense);
            contactLense.add(this);// add reverse connection
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
