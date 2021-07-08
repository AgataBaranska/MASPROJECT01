package models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "receptionist")

public class Receptionist extends Employee {
    //    @Id
//  @GeneratedValue(strategy = GenerationType.AUTO)
// @Column(name = "id")
   // private int id;
   @ManyToMany
   @JoinTable(
           name = "receptionist_training",
           joinColumns = @JoinColumn(name = "receptionist_id"),
           inverseJoinColumns = @JoinColumn(name = "training_id"))
    private List<Training> trainingList;


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



//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }


    public List<Training> getTrainingList() {
        return trainingList;
    }

    public void setTrainingList(List<Training> trainingList) {
        this.trainingList = trainingList;
    }
}
