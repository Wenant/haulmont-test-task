package com.haulmont.test_task.controller;

import com.haulmont.test_task.model.Offer;
import com.haulmont.test_task.model.Repayment;
import com.haulmont.test_task.service.OfferService;
import com.haulmont.test_task.service.RepaymentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping
public class RepaymentController {


    private final RepaymentService repaymentService;
    private final OfferService offerService;

    public RepaymentController(RepaymentService repaymentService
            , OfferService offerService) {
        this.repaymentService = repaymentService;
        this.offerService = offerService;
    }

    @GetMapping
    public String mainPage(){
        return "repayment/hi";
    }

    @GetMapping("pay/{id}")
    public String schedule(@PathVariable("id") UUID id,@PathVariable("id") UUID offer_id, Model model) {
        Offer offer = offerService.findById(offer_id);
        List<Repayment> repayments = repaymentService.findAllByOffer(id);

        Long percent = 0l;
        Long monthPay = 0l;
        for(Repayment repayment : repayments) {
            Long getPercent = repayment.getPercentRepayment();
            Long getMonthPay = repayment.getMonthPay();
            percent = percent+getPercent;
            monthPay = monthPay+getMonthPay;
        }

        model.addAttribute("repayments", repayments);
        model.addAttribute("offer", offer);
        model.addAttribute("percent", percent);
        model.addAttribute("monthPay", monthPay);
        return "repayment/index";
    }
}





