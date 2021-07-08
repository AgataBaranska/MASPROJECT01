package models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "receptionist_training")
public class ReceptionistTraining {

    private int id;
    private Training training;
    private Receptionist receptionist;
    private static List<ReceptionistTraining> entity;

    /**
     * Required by Hibernate.
     */
    private ReceptionistTraining() {
    }

    public ReceptionistTraining(Training training, Receptionist receptionist) {
        this.training = training;
        this.receptionist = receptionist;
    }

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    @ManyToOne
    public Receptionist getReceptionist() {
        return receptionist;
    }

    public void setReceptionist(Receptionist receptionist) {
        this.receptionist = receptionist;
    }
}
