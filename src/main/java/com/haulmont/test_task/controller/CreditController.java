package com.haulmont.test_task.controller;


import com.haulmont.test_task.model.Credit;
import com.haulmont.test_task.service.CreditService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/credit")
public class CreditController {

    private final CreditService creditService;

    public CreditController(CreditService creditService) {
        this.creditService = creditService;
    }

    @GetMapping
    public String findAll(Model model){
        List<Credit> credit = creditService.findAll();
        model.addAttribute("credit", credit);
        return "credit/index";
    }


    @GetMapping("/{id}")
    public String getById(@PathVariable("id") UUID id, Model model){
        model.addAttribute("credit", creditService.findById(id));

        return "credit/show";
    }

    @GetMapping("/new")
    public String newCredit(@ModelAttribute("credit") Credit credit) {
        return "credit/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("credit") @Valid Credit credit, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return "credit/new";

        creditService.saveCredit(credit);
        return "redirect:/credit";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("credit", creditService.findById(id));
        return "/credit/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("credit") @Valid Credit credit, BindingResult bindingResult,
                         @PathVariable("id") UUID id) {
        if(bindingResult.hasErrors())
            return "/credit/edit";

        creditService.saveCredit(credit);
        return "redirect:/credit";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") UUID id) {
        creditService.deleteById(id);
        return "redirect:/credit";
    }


}
