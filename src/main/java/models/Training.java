package models;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "training")
public class Training {
    private String trainingName;
    private String description;
    private String organizer;

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
