/*
package com.haulmont.test_task.model;


import javax.persistence.*;
import java.util.UUID;

@Entity
public class Bank {
    @Id
    @GeneratedValue
    @Column(name="id", insertable = false, updatable = false, nullable = false)
    private UUID id;

    //@ManyToOne()
    @JoinColumn(name = "credit_id")
    private Credit credit;

    //@ManyToMany()
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Bank() {
    }


}
*/
