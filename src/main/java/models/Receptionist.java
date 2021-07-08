package models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "receptionist")
public class Receptionist extends Employee {

    private int id;
    private List<ReceptionistTraining> receptionistTrainingList;


    /**
     * Required by Hibernate.
     */
    private Receptionist() {
    }

    public Receptionist(String name, String surname, String pesel, String telephone, String email, String street,
                        String city, String postalCode, String country, LocalDate hireDate, ContractType contractType,
                        double monthlySalary) {
        super(name, surname, pesel, telephone, email, street, city, postalCode, country, hireDate, contractType,
                monthlySalary);

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

    @OneToMany(mappedBy = "receptionist", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<ReceptionistTraining> getReceptionistTrainingList() {
        return receptionistTrainingList;
    }

    public void setReceptionistTrainingList(List<ReceptionistTraining> receptionistTrainingList) {
        this.receptionistTrainingList = receptionistTrainingList;
    }
}
