package com.haulmont.test_task.controller;

import com.haulmont.test_task.model.Customer;
import com.haulmont.test_task.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping
    public String findAll(Model model){
        List<Customer> customer = customerService.findAll();
        model.addAttribute("customer", customer);
        return "customer/index";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable("id") UUID id, Model model){
        model.addAttribute("customer", customerService.findById(id));
        return "customer/show";
    }

    @GetMapping("/new")
    public String newCustomer(@ModelAttribute("customer") Customer customer) {
        return "customer/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("customer") @Valid Customer customer,
                         BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "customer/new";
        }
        customerService.saveCustomer(customer);
        return "redirect:/customer";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("customer", customerService.findById(id));

        return "/customer/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("customer") @Valid Customer customer, BindingResult bindingResult,
                         @PathVariable("id") UUID id) {
        if(bindingResult.hasErrors())
            return "/customer/edit";

        customerService.saveCustomer(customer);
        return "redirect:/customer";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") UUID id) {
        customerService.deleteById(id);
        return "redirect:/customer";
    }


}
