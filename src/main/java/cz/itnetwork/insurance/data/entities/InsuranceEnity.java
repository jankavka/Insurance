package cz.itnetwork.insurance.data.entities;

import jakarta.persistence.*;

@Entity
public class InsuranceEnity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long Id;

    @Column (nullable = false)
    private String name;

    @Column (nullable = false)
    private String description;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
