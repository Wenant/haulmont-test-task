package com.haulmont.test_task.repository;

import com.haulmont.test_task.model.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface    CreditRepository extends JpaRepository<Credit, UUID> {
}
