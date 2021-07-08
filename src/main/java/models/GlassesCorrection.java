package models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "glasses_correction")
public class GlassesCorrection {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "correction_power_right")
    private String correctionPowerRight;
    @Column(name = "correction_power_left")
    private String correctionPowerLeft;
    @Enumerated
    private CorrectionPurpose purpose;
    @ManyToOne
    private AppointmentCart appointmentCart;//association cardinality 1, GlassesCorrectionObject have  1 Appointment card

    /**
     * Required by Hibernate.
     */
    private GlassesCorrection() {
    }

    public GlassesCorrection(String correctionPowerRight, String correctionPowerLeft, CorrectionPurpose purpose) {
        super();
        this.correctionPowerRight = correctionPowerRight;
        this.correctionPowerLeft = correctionPowerLeft;
        this.purpose = purpose;
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

    public void matchGlassesCorrectionToAppointmentCart(AppointmentCart appointmentCart) throws Exception {
        if (!(this.appointmentCart == null)) {
            throw new Exception("This object of classes.GlassesCorrection is already matched with a appointmentCart");
        } else {
            this.appointmentCart = appointmentCart;
        }

    }

    @Override
    public String toString() {
        return "REye:" + correctionPowerRight +
                ", LEye:" + correctionPowerLeft +
                ", Purpose: " + purpose;
    }

    ;

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
}
