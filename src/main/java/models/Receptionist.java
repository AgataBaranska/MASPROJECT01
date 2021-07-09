package models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "receptionist")
public class Receptionist extends Employee {

    public static List<Receptionist> extent;
    @ManyToMany
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

        addToExtent(this);
    }

    public static List<Receptionist> getExtent() {
        return extent;
    }

    public static void setExtent(List<Receptionist> extent) {
        Receptionist.extent = extent;
    }

    private void addToExtent(Receptionist receptionist) {
        if (extent == null) {
            extent = new ArrayList<>();
        }
        extent.add(receptionist);
    }

    public List<Training> getTrainingList() {
        return trainingList;
    }

    public void setTrainingList(List<Training> trainingList) {
        this.trainingList = trainingList;
    }

    @Override
    public String toString() {
        return "Receptionist{" +
                "trainingList=" + trainingList +
                '}';
    }
}
