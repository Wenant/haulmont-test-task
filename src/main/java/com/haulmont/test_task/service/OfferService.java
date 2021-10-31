package com.haulmont.test_task.service;

import com.haulmont.test_task.model.Offer;
import com.haulmont.test_task.repository.OfferRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class OfferService {

    private final OfferRepository offerRepository;

    public OfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    public Offer findById(UUID id){
        return offerRepository.getById(id);
    }

    public List<Offer> findAll(){
        return offerRepository.findAll();
    }

    public Offer saveOffer(Offer offer){
        return offerRepository.save(offer);
    }





    public List<Offer> findAllByCustomer(UUID customer_id) {
        return offerRepository.getByCustomer_id(customer_id);
    }


}
