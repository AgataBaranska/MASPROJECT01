package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Entity
@Table(name = "lenses_correction")
public class LensesCorrection {
    private static List<LensesCorrection> extent = new ArrayList<>();
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "correction_power_right")
    private String correctionPowerRight;
    @Column(name = "correction_power_left")
    private String correctionPowerLeft;
    //correction power must be an plus or minus signed integer
    private static final String correctionPowerRegex ="^[+-]?[1-9]\\d*|0$";

    @ManyToMany
    @JoinTable(
            name = "correction_lenses",
            joinColumns = @JoinColumn(name = "lenses_correction_id"),
            inverseJoinColumns = @JoinColumn(name = "contact_lense_id"))
    private List<ContactLense> contactLenseList;// LensesCorrection may have many ContactLenses
    @ManyToOne
    //LensesCorrection object may be only in one appointmentCart
    private AppointmentCart appointmentCart;

    /**
     * Required by Hibernate.
     */
    LensesCorrection() {
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
        addToExtent(this);
    }
//method checkc if given text can be paarsed to a allowed power of lenses correction
    public static boolean checkIfValueCorrect(String stringToCheck) {
        String s = stringToCheck.trim();
        return s.matches(correctionPowerRegex);
    }



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

    public String getCorrectionPowerRight() {
        return correctionPowerRight;
    }

    public void setCorrectionPowerRight(String correctionPowerRight) {
        this.correctionPowerRight = correctionPowerRight;
    }

    public String getCorrectionPowerLeft() {
        return correctionPowerLeft;
    }

    public void setCorrectionPowerLeft(String correctionPowerLeft) {
        this.correctionPowerLeft = correctionPowerLeft;
    }

    public List<ContactLense> getContactLensesList() {
        return contactLenseList;
    }

    public AppointmentCart getAppointmentCart() {
        return appointmentCart;
    }

    public void setAppointmentCart(AppointmentCart appointmentCart) {
        this.appointmentCart = appointmentCart;
    }

    public static List<LensesCorrection> getExtent() {
        return extent;
    }
    private void addToExtent(LensesCorrection lensesCorrection) {
        extent.add(lensesCorrection);
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

        return "REye: " + correctionPowerRight +
                ", LEye: " + correctionPowerLeft +
                ", Lenses: " + getContactLensesList();


    }


}
