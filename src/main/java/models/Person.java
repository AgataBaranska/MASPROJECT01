package models;

import javax.persistence.*;

@MappedSuperclass
@Table(name="person")
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;
    @Basic
    private String name;
    @Basic
    private String surname;
    @Basic
    private String pesel;
    @Basic
    private String telephone;
    @Basic
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address", referencedColumnName = "id")
    private Address address;//klucz obcy

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


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
