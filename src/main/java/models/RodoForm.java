package models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "rodo_form")
public class RodoForm {
    private int id;
    private LocalDate date;
    private String signature;

    @OneToOne(mappedBy = "rodo")
    private Patient patient;//association cardinality 1

    /**
     * Required by Hibernate.
     */
    private RodoForm() {
    }

    public RodoForm(String signature, Patient patient) {
        super();
        this.patient = patient;
        this.date = LocalDate.now();
        this.signature = signature;
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

    @Basic
   // @Temporal(TemporalType.DATE)
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Basic
    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }


    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}


