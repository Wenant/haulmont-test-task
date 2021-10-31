package com.haulmont.test_task.controller;

import com.haulmont.test_task.model.Credit;
import com.haulmont.test_task.model.Customer;
import com.haulmont.test_task.model.Offer;
import com.haulmont.test_task.service.CreditService;
import com.haulmont.test_task.service.CustomerService;
import com.haulmont.test_task.service.OfferService;
import com.haulmont.test_task.service.RepaymentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/offer")
public class OfferController {


    private final OfferService offerService;
    private final CustomerService customerService;
    private final CreditService creditService;
    private final RepaymentService repaymentService;

    public OfferController(CustomerService customerService,
                           OfferService offerService,
                           CreditService creditService, RepaymentService repaymentService) {
        this.offerService = offerService;
        this.customerService = customerService;
        this.creditService = creditService;
        this.repaymentService = repaymentService;
    }


    @GetMapping
    public String findAll(Model model) {
        List<Offer> offer = offerService.findAll();
        model.addAttribute("offer", offer);
        return "offer/index";
    }



    @GetMapping("/new")
    public String newOffer(@ModelAttribute("offer") Offer offer, Model model) {
        List<Customer> customers = customerService.findAll();
        List<Credit> credits = creditService.findAll();

        model.addAttribute("customer", customers);
        model.addAttribute("credit", credits);
        return "offer/new";
    }


    @Validated
    @PostMapping()
    public String newOneOffer(@ModelAttribute("offer") @RequestBody @Valid Offer offer, BindingResult bindingResult,
                              @RequestParam(name = "x", defaultValue = "12") int x,
                              RedirectAttributes redirectAttrs) {
        if(bindingResult.hasErrors())
            return "offer/new";

        offerService.saveOffer(offer);
        repaymentService.paymentSchedule(offer, x);
        redirectAttrs.addAttribute("id", offer.getId());
        return "redirect:/pay/{id}";
    }
}