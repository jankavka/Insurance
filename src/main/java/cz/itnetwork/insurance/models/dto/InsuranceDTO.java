package cz.itnetwork.insurance.models.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public class InsuranceDTO {

    private long Id;

    @NotBlank( message = "Vyplňte pole")
    private String name;

    @NotBlank( message = "Vyplňte pole")
    private String description;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public @NotBlank(message = "Vyplňte pole") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Vyplňte pole") String name) {
        this.name = name;
    }

    public @NotBlank(message = "Vyplňte pole") String getDescription() {
        return description;
    }

    public void setDescription(@NotBlank(message = "Vyplňte pole") String description) {
        this.description = description;
    }
}
