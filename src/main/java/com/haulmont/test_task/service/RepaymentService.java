package com.haulmont.test_task.service;


import com.haulmont.test_task.model.Offer;
import com.haulmont.test_task.model.Repayment;
import com.haulmont.test_task.repository.RepaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


@Service
public class RepaymentService {

    private final RepaymentRepository repaymentRepository;

    public RepaymentService(RepaymentRepository repaymentRepository) {
        this.repaymentRepository = repaymentRepository;
    }

    public Repayment findById(UUID id){
        return repaymentRepository.getById(id);
    }

    public Repayment saveRepay(Repayment repayments){
        return repaymentRepository.save(repayments);
    }

    public Iterable<Repayment> saveAllRep(Iterable<Repayment> repayments) {
        return repaymentRepository.saveAll(repayments);
    }

    public List<Repayment> findAllByOffer(UUID offer_id) {
        return repaymentRepository.findRepaymentByOffer_id(offer_id);
    }



    /*
    public Iterable<Repayment> saveAllAndFlush(Iterable<Repayment> rep) {
        return repaymentRepository.saveAllAndFlush(rep);
    }

    public void flush(Iterable<Repayment> rep) {
        repaymentRepository.flush();
    }
}*/


    public void paymentSchedule(Offer offer, int n) {
            //List<Repayment> repayments = new ArrayList<>();   //to do
            LocalDate localDate = LocalDate.now();

            double p = offer.getCredit().getCreditPercent() / (100*12);             // доля процентной ставки (в месяц)
            Long creditSum = offer.getCreditSum();                                  //S — сумма займа
            double monthPay = (creditSum * (p + (p / (Math.pow(1 + p, n) - 1))));   // размер аннуитетного ежемесячного платежа

            for (int i = 0; i < n; i++) {
                Repayment repayment = new Repayment();
                localDate = localDate.plusMonths(1);                                 // plus date
                int percentPart = (int) (creditSum * p);                             // плата по процентам
                int creditPart = (int) (monthPay - percentPart);                     // плата по кредиту

                repayment.setDate(localDate);
                repayment.setMonthPay((long) monthPay);
                repayment.setPercentRepayment((long) percentPart);
                repayment.setLoanRepayment((long) creditPart);
                offer.setRepayment(Arrays.asList(repayment));
                repaymentRepository.save(repayment);
                //repayments.add(repayment); //to do

                creditSum = creditSum - creditPart;             //остаток
            }

            //repaymentRepository.saveAll(repayments); //to do (не хочет работать)

        }

}

