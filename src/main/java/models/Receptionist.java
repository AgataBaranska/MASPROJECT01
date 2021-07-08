package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "receptionist")
public class Receptionist extends Employee {

    private List<Training> trainingList;

    /**
     * Required by Hibernate.
     */
    private Receptionist() {
    }

    public Receptionist(String name, String surname, String pesel, String telephone, String email, String street,
                        String city, String postalCode, String country, LocalDate hireDate, ContractType contractType,
                        double monthlySalary, List<Training> trainingList) {
        super(name, surname, pesel, telephone, email, street, city, postalCode, country, hireDate, contractType,
                monthlySalary);
        this.trainingList = trainingList;
    }

    @OneToMany(mappedBy = "receptionist", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Training> getTrainingList() {
        return trainingList;
    }

    public void setTrainingList(List<Training> trainingList) {
        this.trainingList = trainingList;
    }
}
