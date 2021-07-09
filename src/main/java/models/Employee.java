package models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@MappedSuperclass
public abstract class Employee extends Person {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id", updatable = false, nullable = false)
//    private int id;

    @Basic
    // @Temporal(TemporalType.DATE)
    private LocalDate hireDate;
    //protected Set<Day> workingDays;

    @Column(name = "monthly_salary")
    private double monthlySalary;
    @Column(name = "contract_type")
    @Enumerated
    private ContractType contractType;


    /**
     * Required by Hibernate.
     */
    protected Employee() {
    }

    public Employee(String name, String surname, String pesel, String telephone, String email, String street,
                    String city, String postalCode, String country, LocalDate hireDate, ContractType contractType, double monthlySalary) {
        super(name, surname, pesel, telephone, email, street, city, postalCode, country);
        this.hireDate = hireDate;
        this.monthlySalary = monthlySalary;
        this.contractType = contractType;
        // workingDays = new HashSet<Day>();
    }


//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }


    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

//    @Basic
//    public Set<Day> getWorkingDays() {
//        return workingDays;
//    }
//
//    public void setWorkingDays(Set<Day> workingDays) {
//        this.workingDays = workingDays;
//    }

    public ContractType getContractType() {
        return contractType;
    }

    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
    }

    public double getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(double monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

//    public void addWorkingDay(Day day) {
//        workingDays.add(day);
//    }

//    public void removeWorkingDay(Day day) {
//        if (workingDays.contains(day)) {
//            workingDays.remove(day);
//        }
//    }

    public enum Day {
        MO, TU, WE, TH, FR, SA, SUN
    }

    public enum ContractType {
        FULL_TIME, HALF_TIME
    }


}
