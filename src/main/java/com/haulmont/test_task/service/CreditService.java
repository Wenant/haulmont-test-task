package com.haulmont.test_task.service;


import com.haulmont.test_task.model.Credit;
import com.haulmont.test_task.repository.CreditRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class CreditService {

    private final CreditRepository creditRepository;

    public CreditService(CreditRepository creditRepository) {
        this.creditRepository = creditRepository;
    }

    public Credit findById(UUID id){
        return creditRepository.getById(id);
    }

    public List<Credit> findAll(){
        return creditRepository.findAll();
    }

    public Credit saveCredit(Credit credit){
        return creditRepository.save(credit);
    }

    public void deleteById(UUID id){
        creditRepository.deleteById(id);
    }


}
