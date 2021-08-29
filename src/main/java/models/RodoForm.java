package models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rodo_form")
public class RodoForm {

    private static List<RodoForm> extent = new ArrayList<>();
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Basic
    // @Temporal(TemporalType.DATE)
    private LocalDate date;
    @Basic
    private String signature;

    /**
     * Required by Hibernate.
     */
    RodoForm() {
    }

    public RodoForm(String signature) {
        super();
        this.date = LocalDate.now();
        this.signature = signature;
        addToExtent(this);
    }

    public static List<RodoForm> getExtent() {
        return extent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private void addToExtent(RodoForm rodoForm) {
        extent.add(rodoForm);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Override
    public String toString() {
        return "RodoForm{" +
                "id=" + id +
                ", date=" + date +
                ", signature='" + signature + '\'' +
                '}';
    }
}


