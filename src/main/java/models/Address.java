package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "address")
public class Address {

    private static List<Address> extent;

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

    private Address() {
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
        return extent;
    }

    public static void setExtent(List<Address> extent) {
        Address.extent = extent;
    }



    private void addToExtent(Address address) {
        if (extent == null) {
            extent = new ArrayList<>();
        }
        extent.add(address);
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
