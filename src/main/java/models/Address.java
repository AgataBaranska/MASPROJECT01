package models;

import gui.HibernateUtility;
import org.hibernate.Session;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "address")
public class Address {

    private static List<Address> extent = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Basic
    private String street;
    @Basic
    private String city;
    @Basic
    @Column(name = "postal_code")
    private String postalCode;
    @Basic
    private String country;

    /**
     * Required by Hibernate.
     */

    Address() {
    }

    public Address(String street, String city, String postalCode, String country) {
        super();
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
        addToExtent(this);
    }

    public static List<Address> getExtent() {
        Session session = HibernateUtility.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        extent = session.createQuery("FROM Address", Address.class).list();
        session.getTransaction().commit();
        return extent;
    }

    private void addToExtent(Address address) {
        extent.add(address);
        Session session = HibernateUtility.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(address);
        session.getTransaction().commit();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
