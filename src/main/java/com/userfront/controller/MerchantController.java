package com.userfront.controller;

import com.userfront.models.Merchant;
import com.userfront.repositories.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/merchant")
public class MerchantController {

    @Autowired
    private MerchantRepository merchantRepo;

    @RequestMapping("/view")
    public String view(Model model) {
        List<Merchant> merchants = merchantRepo.findAll();
        model.addAttribute("merchants", merchants);
        return "merchants";
    }

    @RequestMapping("/add")
    public String add(Model model) {
        Merchant merchant = new Merchant();
        model.addAttribute("merchant", merchant);
        return "addMerchant";
    }

    @RequestMapping(value = "/save", method = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
    public String merchantPost(@RequestParam("name") final String name, @RequestParam("id") final int id) {

        if (id == 0) {
            System.out.println("add");
            Merchant merchant = new Merchant();
            merchant.setName(name);
            merchantRepo.save(merchant);
        } else {
            System.out.println("edit");
            Merchant merchant = merchantRepo.findById(id).get();
            merchant.setName(name);
            merchantRepo.save(merchant);
        }
        return "redirect:/merchant/view";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String merchantEdit(@RequestParam(value = "merchantId") int merchantId, Model model) {
        Merchant merchant = merchantRepo.findById(merchantId).get();
        model.addAttribute("merchant", merchant);

        return "editMerchant";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @Transactional
    public String merchantDelete(@RequestParam(value = "merchantId") int merchantId, Model model) {
        merchantRepo.deleteById(merchantId);
        List<Merchant> merchants = merchantRepo.findAll();

        Merchant merchant = new Merchant();
        model.addAttribute("merchant", merchant);
        model.addAttribute("merchants", merchants);


        return "merchants";
    }
}
