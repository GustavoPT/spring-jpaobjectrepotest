package com.userfront.controller;

import com.userfront.models.Merchant;
import com.userfront.models.Product;
import com.userfront.repositories.MerchantRepository;
import com.userfront.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MerchantRepository merchantRepo;

    @RequestMapping("/view")
    public String view(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "products";
    }

    @RequestMapping("/add")
    public String add(Model model) {
        Product product = new Product();
        Merchant merchant = new Merchant();
        List<Merchant> merchants = merchantRepo.findAll();
        model.addAttribute("product", product);
        model.addAttribute("merchant", merchant);
        model.addAttribute("merchants", merchants);
        return "addProduct";
    }

    @RequestMapping(value = "/save", method = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
    public String productPost(@RequestParam("name") final String name, @RequestParam("id") final int id, @RequestParam("merchant") final String merchantName) {

        if (id == 0) {
            System.out.println("add");
            Product product = new Product();
            Merchant merchant = merchantRepo.findMerchantByName(merchantName);
            product.setName(name);
            product.setMerchant(merchant);
            productRepository.save(product);
        } else {
            System.out.println("edit");
            Product product = productRepository.findById(id).get();
            Merchant merchant = merchantRepo.findMerchantByName(merchantName);
            product.setName(name);
            product.setMerchant(merchant);
            productRepository.save(product);
        }
        return "redirect:/product/view";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String productEdit(@RequestParam(value = "productId") int productId, Model model) {

        Product product = productRepository.findById(productId).get();
        Merchant merchant = product.getMerchant();
        List<Product> products = productRepository.findAll();
        List<Merchant> merchants = merchantRepo.findAll();
        model.addAttribute("products", products);
        model.addAttribute("product", product);
        model.addAttribute("merchant", merchant);
        model.addAttribute("merchants", merchants);
        return "editProduct";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @Transactional
    public String productDelete(@RequestParam(value = "productId") int productId, Model model) {

        productRepository.deleteById(productId);

        List<Product> products = productRepository.findAll();

        Product product = new Product();
        model.addAttribute("product", product);
        model.addAttribute("products", products);


        return "products";
    }

}
