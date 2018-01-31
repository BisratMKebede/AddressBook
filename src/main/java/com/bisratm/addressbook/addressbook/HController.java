package com.bisratm.addressbook.addressbook;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import javax.persistence.metamodel.StaticMetamodel;
import javax.validation.Valid;

@Controller
public class HController {
    @Autowired
    AddressRepoistory addressRepoistory;

    @RequestMapping("/")
    public String listAddresses(Model model) {
        model.addAttribute("addresslist", addressRepoistory.findAll());
        return "addresslist";
    }

    @GetMapping("/add")
    public String addressForm(Model model){
        model.addAttribute("singleAddress", new AddressC());
        return "addressform";
    }

    @PostMapping("/process")
    public String processForm(@Valid @ModelAttribute("singleAddress") AddressC address, BindingResult result) {
        if (result.hasErrors()) {
            return "addressform";
        }
        addressRepoistory.save(address);

        return "redirect:/";
    }

    @RequestMapping("/detail/{id}")
    public String showAddress(@PathVariable("id") long id, Model model) {
        model.addAttribute("addresslist", addressRepoistory.findOne(id));
        return "show";
    }

    @RequestMapping("/update/{id}")
    public String modifyAddress(@PathVariable("id") long id, Model model) {
        model.addAttribute("singleAddress", addressRepoistory.findOne(id));
        return "addressform";
    }

    @RequestMapping("/search")
    public String search (@RequestParam("n") String name,Model model1 ){

       model1.addAttribute("addresslist",addressRepoistory.findAllByName(name)) ;
        return "addresslist";

    }

    @RequestMapping("/delete/{id}")
    public String delAddress(@PathVariable("id") long id){
        addressRepoistory.delete(id);
        return "redirect:/";
    }


}
