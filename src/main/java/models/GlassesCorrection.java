package models;

import gui.HibernateUtility;
import org.hibernate.Session;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "glasses_correction")
public class GlassesCorrection {
    private static List<GlassesCorrection> extent = new ArrayList<>();
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "correction_power_right")
    private String correctionPowerRight;
    @Column(name = "correction_power_left")
    private String correctionPowerLeft;
    @Enumerated(EnumType.STRING)
    private CorrectionPurpose purpose;
    @ManyToOne
    private AppointmentCart appointmentCart;//association cardinality 1, GlassesCorrectionObject have  1 Appointment card

    private static final String correctionPowerRegex ="^[+-]?[1-9]\\d*|0$";
    /**
     * Required by Hibernate.
     */
    GlassesCorrection() {
    }

    public GlassesCorrection(String correctionPowerRight, String correctionPowerLeft, CorrectionPurpose purpose) {
        super();
        this.correctionPowerRight = correctionPowerRight;
        this.correctionPowerLeft = correctionPowerLeft;
        this.purpose = purpose;
        addToExtent(this);
    }

    //method check if given text can be parsed to a allowed power of glasses correction
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


    public CorrectionPurpose getPurpose() {
        return purpose;
    }

    public void setPurpose(CorrectionPurpose purpose) {
        this.purpose = purpose;
    }

    public AppointmentCart getAppointmentCart() {
        return appointmentCart;
    }

    public void setAppointmentCart(AppointmentCart appointmentCart) {
        this.appointmentCart = appointmentCart;
    }

    public static List<GlassesCorrection> getExtent() {
        return extent;
    }

    public void matchGlassesCorrectionToAppointmentCart(AppointmentCart appointmentCart) throws Exception {
        if (!(this.appointmentCart == null)) {
            throw new Exception("This object of classes.GlassesCorrection is already matched with a appointmentCart");
        } else {
            this.appointmentCart = appointmentCart;
        }

    }
    private void addToExtent(GlassesCorrection glassesCorrection) {
        extent.add(glassesCorrection);

    }




    public enum CorrectionPurpose {
        FOR_DISTANCE("For distance"),
        FOR_READING("For reading");

        private String label;

        CorrectionPurpose(String label) {
            this.label = label;
        }

        public String toString() {
            return label;
        }
    }

    @Override
    public String toString() {
        return "REye: " + correctionPowerRight +
                ", LEye: " + correctionPowerLeft +
                ", Purpose: " + purpose;
    }
}
