package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "training")
public class Training {
    public static List<Training> extent;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Basic
    private String trainingName;
    @Basic
    private String description;
    @Basic
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
        addToExtent(this);
    }

    public static List<Training> getExtent() {
        return extent;
    }

    public static void setExtent(List<Training> extent) {
        Training.extent = extent;
    }

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

    public String getTrainingName() {
        return trainingName;
    }

    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    private void addToExtent(Training training) {
        if (extent == null) {
            extent = new ArrayList<>();
        }
        extent.add(training);
    }

    @Override
    public String toString() {
        return "Training{" +
                "id=" + id +
                ", trainingName='" + trainingName + '\'' +
                ", description='" + description + '\'' +
                ", organizer='" + organizer + '\'' +
                ", receptionistList=" + receptionistList +
                '}';
    }
}
