package models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "training")
public class Training {
    private int id;
    private String trainingName;
    private String description;
    private String organizer;
    @ManyToMany
    private List<Receptionist> receptionistList;//association *

    /**
     * Required by Hibernate.
     */
    private Training() {
    }

    public Training(String trainingName, String description, String organizer) {
        super();
        this.trainingName = trainingName;
        this.description = description;
        this.organizer = organizer;
    }

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name="id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public List<Receptionist> getReceptionistList() {
        return receptionistList;
    }

    public void setReceptionistList(List<Receptionist> receptionistList) {
        this.receptionistList = receptionistList;
    }

    @Basic
    public String getTrainingName() {
        return trainingName;
    }

    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
    }

    @Basic
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }
}
