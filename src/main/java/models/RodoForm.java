package models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rodo_form")
public class RodoForm {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Basic
    // @Temporal(TemporalType.DATE)
    private LocalDate date;
    @Basic
    private String signature;
    private static List<RodoForm> extent;

    /**
     * Required by Hibernate.
     */
    private RodoForm() {
    }

    public RodoForm(String signature) {
        super();
        this.date = LocalDate.now();
        this.signature = signature;
        addToExtent(this);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private void addToExtent(RodoForm rodoForm) {
        if(extent==null){
            extent = new ArrayList<>();
        }
        extent.add(rodoForm);
    }

    public static List<RodoForm> getExtent() {
        return extent;
    }

    public static void setExtent(List<RodoForm> extent) {
        RodoForm.extent = extent;
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



}


