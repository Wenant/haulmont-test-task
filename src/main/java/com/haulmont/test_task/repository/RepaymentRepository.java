package com.haulmont.test_task.repository;

import com.haulmont.test_task.model.Repayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface RepaymentRepository extends JpaRepository<Repayment, UUID> {


    @Query(value = "SELECT * FROM repayment WHERE offer_id = :id", nativeQuery = true)
    List<Repayment> findRepaymentByOffer_id(@Param("id") UUID offer_id);


    Repayment getById(UUID id);
}
