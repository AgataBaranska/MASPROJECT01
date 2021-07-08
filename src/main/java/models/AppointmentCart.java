package models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "appointment_cart")
public class AppointmentCart {
    public static int idCounter = 0;
    private int idNumber;
    private Patient patient;
    private String interview;
    private String recommendations;
    //appointment card may have 0 or more classes.GlassesCorrection prescribed
    private List<GlassesCorrection> glassesCorrectionList;// association cardinality [0..1]

    //appointment card may have 0 or more classes.LensesCorrection prescribed
    private List<LensesCorrection> lensesCorrectionList;// association cardinality [0..1]

    @OneToMany(mappedBy="appointmentCart")
    private Appointment appointment;
    /**
     * Required by Hibernate.
     */
    private AppointmentCart() {
    }

    public AppointmentCart(Patient patient) {
        super();
        this.patient = patient;
        idNumber = generateIdNumber();
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    @Basic
    public static int getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(int idCounter) {
        AppointmentCart.idCounter = idCounter;
    }

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    @Basic
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Basic
    public String getInterview() {
        return interview;
    }

    public void setInterview(String interview) {
        this.interview = interview;
    }

    @Basic
    public String getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(String recommendations) {
        this.recommendations = recommendations;
    }

    @OneToMany(mappedBy = "appointmentCart", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<GlassesCorrection> getGlassesCorrectionList() {
        return glassesCorrectionList;
    }

    public void setGlassesCorrectionList(List<GlassesCorrection> glassesCorrectionList) {
        this.glassesCorrectionList = glassesCorrectionList;
    }

    @OneToMany(mappedBy = "appointmentCart", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<LensesCorrection> getLensesCorrectionList() {
        return lensesCorrectionList;
    }

    public void setLensesCorrectionList(List<LensesCorrection> lensesCorrectionList) {
        this.lensesCorrectionList = lensesCorrectionList;
    }

    public void addInterview(String interview) {
        this.interview = interview;
    }

    public void addRecommendations(String recommendations) {
        this.recommendations = recommendations;
    }

    public void addLensesCorrection(LensesCorrection lensesCorrection) {
        if (lensesCorrectionList == null) {
            lensesCorrectionList = new ArrayList<LensesCorrection>();
        }
        lensesCorrectionList.add(lensesCorrection);
        try {
            lensesCorrection.matchLensesCorrectionToAppointmentCart(this);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addGlassesCorrection(GlassesCorrection glassesCorrection) {
        if (glassesCorrectionList == null) {
            glassesCorrectionList = new ArrayList<GlassesCorrection>();
        }

        glassesCorrectionList.add(glassesCorrection);
        try {
            glassesCorrection.matchGlassesCorrectionToAppointmentCart(this);//add reverse connection
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }


    public int generateIdNumber() {
        return ++idCounter;
    }

}
