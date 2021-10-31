package com.haulmont.test_task.repository;


import com.haulmont.test_task.model.Offer;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OfferRepository extends JpaRepository<Offer, UUID> {


    Offer getByCreditSum(Long sum);


    List<Offer> getByCustomer_id(UUID customer_id);
}
