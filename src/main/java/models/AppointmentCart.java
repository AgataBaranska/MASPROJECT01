package models;

import gui.HibernateUtility;
import org.hibernate.Session;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "appointment_cart")
public class AppointmentCart {
    private static List<AppointmentCart> extent = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Basic
    private String interview;
    @Basic
    private String recommendations;

    @OneToMany(mappedBy = "appointmentCart", cascade = CascadeType.ALL, orphanRemoval = true)
    //appointment card may have 0 or more classes.GlassesCorrection prescribed
    private List<GlassesCorrection> glassesCorrectionList;

    @OneToMany(mappedBy = "appointmentCart", cascade = CascadeType.ALL, orphanRemoval = true)
    //appointment card may have 0 or more classes.LensesCorrection prescribed
    private List<LensesCorrection> lensesCorrectionList;

    @OneToOne(mappedBy = "appointmentCart")
    private Appointment appointment;

    /**
     * Required by Hibernate.
     */

   AppointmentCart() {
        super();
        lensesCorrectionList = new ArrayList<LensesCorrection>();
        glassesCorrectionList = new ArrayList<GlassesCorrection>();

        addToExtent(this);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public Patient getPatient() {
        return appointment.getPatient();
    }

    public String getInterview() {
        return interview;
    }

    public void setInterview(String interview) {
        this.interview = interview;
    }

    public String getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(String recommendations) {
        this.recommendations = recommendations;
    }

    public List<GlassesCorrection> getGlassesCorrectionList() {
        return glassesCorrectionList;
    }

    public void setGlassesCorrectionList(List<GlassesCorrection> glassesCorrectionList) {
        this.glassesCorrectionList = glassesCorrectionList;
    }

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

    public static List<AppointmentCart> getExtent() {
        return extent;
    }

    public void addLensesCorrection(LensesCorrection lensesCorrection) {

        lensesCorrectionList.add(lensesCorrection);
        try {
            lensesCorrection.matchLensesCorrectionToAppointmentCart(this);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addGlassesCorrection(GlassesCorrection glassesCorrection) {
        glassesCorrectionList.add(glassesCorrection);
        try {
            glassesCorrection.matchGlassesCorrectionToAppointmentCart(this);//add reverse connection
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    private  void addToExtent(AppointmentCart appointmentCart) {
        extent.add(appointmentCart);

    }

    @Override
    public String toString() {
        return "AppointmentCart{" +
                "id=" + id +
                ", interview='" + interview + '\'' +
                ", recommendations='" + recommendations + '\'' +
//                ", glassesCorrectionList=" + glassesCorrectionList +
//                ", lensesCorrectionList=" + lensesCorrectionList +
                ", appointment=" + appointment +
                '}';
    }
}
