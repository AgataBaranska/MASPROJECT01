package models;

import javax.persistence.Basic;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Person {
    private String name;
    private String surname;
    private String pesel;
    private String telephone;
    private String email;
    private Address address;

    /**
     * Required by Hibernate.
     */
    protected Person() {
    }

    public Person(String name, String surname, String pesel, String telephone, String email, String street, String city, String postalCode, String country) {
        super();
        this.name = name;
        this.surname = surname;
        this.pesel = pesel;
        this.telephone = telephone;
        this.email = email;
        this.address = new Address(street, city, postalCode, country);
    }

    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    @Basic
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Basic
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Basic
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
