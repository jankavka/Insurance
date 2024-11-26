package cz.itnetwork.insurance.models.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class InsuranceDTO {

    private long id;

    @NotBlank( message = "Vyplňte pole")
    private String name;

    @NotNull( message = "Vyplňte pole")
    @Min(value = 1, message = "Částka musí být větší než nula")
    private int amount;

    @NotBlank( message = "Vyplňte pole")
    private String subjectOfInsurance;

    @NotNull(message = "Vyplňte pole")
    private LocalDate validFrom;

    @NotNull( message = "Vyplňte pole")
    private LocalDate validUntil;

    private long personId;

    private PersonDTO personDTO;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public @NotBlank(message = "Vyplňte pole") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Vyplňte pole") String name) {
        this.name = name;
    }

    @NotNull(message = "Vyplňte pole")
    @Min(value = 1, message = "Částka musí být větší než nula")
    public int getAmount() {
        return amount;
    }

    public void setAmount(@NotNull(message = "Vyplňte pole") @Min(value = 1, message = "Částka musí být větší než nula") int amount) {
        this.amount = amount;
    }

    public @NotBlank(message = "Vyplňte pole") String getSubjectOfInsurance() {
        return subjectOfInsurance;
    }

    public void setSubjectOfInsurance(@NotBlank(message = "Vyplňte pole") String subjectOfInsurance) {
        this.subjectOfInsurance = subjectOfInsurance;
    }

    public @NotNull(message = "Vyplňte pole") LocalDate getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(@NotNull(message = "Vyplňte pole") LocalDate validFrom) {
        this.validFrom = validFrom;
    }

    public @NotNull(message = "Vyplňte pole") LocalDate getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(@NotNull(message = "Vyplňte pole") LocalDate validUntil) {
        this.validUntil = validUntil;
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public PersonDTO getPersonDTO() {
        return personDTO;
    }

    public void setPersonDTO(PersonDTO personDTO) {
        this.personDTO = personDTO;
    }
}
