package com.haulmont.test_task.model;


import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.UUID;


@Entity
public class Credit {
    @Id
    @GeneratedValue
    @Column(name="id", insertable = false, updatable = false, nullable = false)
    private UUID id;

    @Min(value = 1000, message = "Некорректное значение")
    @Max(value = 99999999, message = "Некорректное значение")
    private Long creditLimit;
    @DecimalMin("0.01")
    @DecimalMax("356.0")
    private double creditPercent;

    @OneToMany(mappedBy ="credit", cascade = CascadeType.ALL)
    private List<Offer> offers;

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        if(offers != null){
            offers.forEach(a-> {
                a.setCredit(this);
            });
        }
        this.offers = offers;
    }

    public Credit() {
    }

    public Credit(Long creditLimit, double creditPercent) {
        this.creditLimit = creditLimit;
        this.creditPercent = creditPercent;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Long getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Long creditLimit) {
        this.creditLimit = creditLimit;
    }

    public double getCreditPercent() {
        return creditPercent;
    }

    public void setCreditPercent(double creditPercent) {
        this.creditPercent = creditPercent;
    }
}
