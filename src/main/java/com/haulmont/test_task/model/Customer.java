package com.haulmont.test_task.model;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;


@Entity
public class Customer {
    @Id
    @GeneratedValue
    @Column(name="id", insertable = false, updatable = false, nullable = false)
    private UUID id;

    @OneToMany(mappedBy ="customer", cascade = CascadeType.ALL)
    private List<Offer> offers;

    @NotBlank(message = "Не может быть пустым")
    @Size(min = 2, message = "Некорректное значение")
    private String name;

    @NotBlank(message = "Не может быть пустым")
    private String phone;

    @NotEmpty(message = "Не может быть пустым")
    @Email(message = "Некорректное значение")
    private String email;

    @NotBlank(message = "Не может быть пустым")
    private String passport;



    public Customer() {
    }

    public Customer(String name, String phone, String email, String passport) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.passport = passport;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        if(offers != null){
            offers.forEach(a-> {
                a.setCustomer(this);
            });
        }
        this.offers = offers;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }
}
