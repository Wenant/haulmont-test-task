package com.haulmont.test_task.model;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Repayment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", insertable = false, updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "offer_id")
    private Offer offer;

    private LocalDate date;
    private Long monthPay;
    private Long loanRepayment;
    private Long percentRepayment;

    public Repayment() {
    }

    public Repayment(Long monthPay, Long loanRepayment, Long percentRepayment) {
        this.monthPay = monthPay;
        this.loanRepayment = loanRepayment;
        this.percentRepayment = percentRepayment;
    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public Long getMonthPay() {
        return monthPay;
    }

    public void setMonthPay(Long monthPay) {
        this.monthPay = monthPay;
    }

    public Long getLoanRepayment() {
        return loanRepayment;
    }

    public void setLoanRepayment(Long loanRepayment) {
        this.loanRepayment = loanRepayment;
    }

    public Long getPercentRepayment() {
        return percentRepayment;
    }

    public void setPercentRepayment(Long percentRepayment) {
        this.percentRepayment = percentRepayment;
    }
}
