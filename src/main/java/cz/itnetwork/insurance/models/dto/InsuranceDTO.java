package cz.itnetwork.insurance.models.dto;

import jakarta.validation.constraints.NotBlank;

public class InsuranceDTO {

    private long id;

    @NotBlank( message = "Vyplňte pole")
    private String name;

    @NotBlank( message = "Vyplňte pole")
    private String description;

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

    public @NotBlank(message = "Vyplňte pole") String getDescription() {
        return description;
    }

    public void setDescription(@NotBlank(message = "Vyplňte pole") String description) {
        this.description = description;
    }
}
