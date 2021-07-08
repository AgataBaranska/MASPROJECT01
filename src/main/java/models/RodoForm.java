package models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "rodo_form")
public class RodoForm {
    private LocalDate date;
    private String signature;
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

    @Basic
    @Temporal(TemporalType.DATE)
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

    @Basic
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}


