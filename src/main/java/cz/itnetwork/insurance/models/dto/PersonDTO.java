package cz.itnetwork.insurance.models.dto;

import jakarta.validation.constraints.NotBlank;

public class PersonDTO {

    private long personId;

    @NotBlank (message = "Vyplňte pole")
    private String name;

    @NotBlank (message = "Vyplňte pole")
    private String surname;

    @NotBlank (message = "Vyplňte pole")
    private String email;

    @NotBlank (message = "Vyplňte pole")
    private String phoneNumber;

    @NotBlank (message = "Vyplňte pole")
    private String street;

    @NotBlank (message = "Vyplňte pole")
    private String city;

    @NotBlank (message = "Vyplňte pole")
    private String zipCode;

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public @NotBlank(message = "Vyplňte pole") String getSurname() {
        return surname;
    }

    public void setSurname(@NotBlank(message = "Vyplňte pole") String surname) {
        this.surname = surname;
    }

    public @NotBlank(message = "Vyplňte pole") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Vyplňte pole") String name) {
        this.name = name;
    }

    public @NotBlank(message = "Vyplňte pole") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Vyplňte pole") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Vyplňte pole") String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@NotBlank(message = "Vyplňte pole") String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public @NotBlank(message = "Vyplňte pole") String getStreet() {
        return street;
    }

    public void setStreet(@NotBlank(message = "Vyplňte pole") String street) {
        this.street = street;
    }

    public @NotBlank(message = "Vyplňte pole") String getCity() {
        return city;
    }

    public void setCity(@NotBlank(message = "Vyplňte pole") String city) {
        this.city = city;
    }

    public @NotBlank(message = "Vyplňte pole") String getZipCode() {
        return zipCode;
    }

    public void setZipCode(@NotBlank(message = "Vyplňte pole") String zipCode) {
        this.zipCode = zipCode;
    }
}
