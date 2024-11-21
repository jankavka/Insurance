package cz.itnetwork.insurance.data.entities;

import jakarta.persistence.*;

@Entity
public class PersonEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long personId;

    @Column (nullable = false)
    private String name;

    @Column (nullable = false)
    private String surname;

    @Column (nullable = false)
    private String email;

    @Column (nullable = false)
    private String phoneNumber;

    @Column (nullable = false)
    private String street;

    @Column (nullable = false)
    private String city;

    @Column (nullable = false)
    private String zipCode;

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
