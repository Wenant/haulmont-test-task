package com.haulmont.test_task.model;


import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.UUID;


@Entity
public class Offer {
    @Id
    @GeneratedValue
    @Column(name="id", insertable = false, updatable = false, nullable = false)
    private UUID id;

    @Min(value = 1000, message = "Некорректное значение")
    @Max(value = 99999999, message = "Некорректное значение")
    private Long creditSum;

    @ManyToOne()
    @JoinColumn(name = "credit_id")
    private Credit credit;

    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL)
    private List<Repayment> repayments;


    public Offer() {
    }

    public Offer(Credit credit) {
        this.credit = credit;
    }

    public Offer(Long creditSum, Credit credit, Customer customer) {
        this.creditSum = creditSum;
        this.credit = credit;
        this.customer = customer;
    }

    public List<Repayment> getRepayment() {
        return repayments;
    }

    public void setRepayment(List<Repayment> repayments) {
        if(repayments != null){
            repayments.forEach(a-> {
                a.setOffer(this);
            });
        }
        this.repayments = repayments;
    }

    public Credit getCredit() {
        return credit;
    }

    public List<Repayment> getRepayments() {
        return repayments;
    }

    public void setRepayments(List<Repayment> repayments) {
        this.repayments = repayments;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Long getCreditSum() {
        return creditSum;
    }

    public void setCreditSum(Long creditSum) {
        this.creditSum = creditSum;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}

