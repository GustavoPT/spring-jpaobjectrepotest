package com.userfront.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import com.userfront.repositories.CardRepository;
import com.userfront.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.userfront.models.Card;
import com.userfront.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/card")
public class CardController {
	
	private static Logger log = LoggerFactory.getLogger(CardController.class);
	public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/images";
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private CardRepository cardRepository;

	@RequestMapping("/view")
	public String view(Model model) {
		User user = userRepository.findByUsername((String) model.getAttribute("userName"));
		List<Card> cards=user.getCards();
        model.addAttribute("cards", cards);
		return "cards";
	}
	
	@RequestMapping("/add/view")
	public String add(Model model) {
		Card card=new Card();
        model.addAttribute("card", card);
		return "addCard";
	}
	
	@RequestMapping(value = "/save", method = {RequestMethod.GET, RequestMethod.PUT,RequestMethod.POST})
    public String cardPost(
			@RequestParam("number") final int number,
			@RequestParam("year") final int year,
			@RequestParam("code") final int code,
			@RequestParam("month") final int month,
			@RequestParam("image") final MultipartFile file,
			@RequestParam("id") final int id,
			@RequestParam("name") final String name,
			HttpSession session
	) {

		String fileName="";
		// getting the model
		// id here
		User user = userRepository.findByUsername((String) session.getAttribute("userName"));
		System.out.println("id "+ id);
		
		if (id==0) {
			System.out.println("add");
			Card card=new Card();
			try {
				fileName =file.getOriginalFilename(); 
				String filePath = Paths.get(uploadDirectory,fileName).toString();
				
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
				
				stream.write(file.getBytes());
				stream.close();
			
			
			card.setCode(code);
			card.setImage(fileName);
			card.setMonth(month);
			card.setNumber(number);
			card.setYear(year);
			card.setUser(user);
			card.setName(name);
			cardRepository.save(card);
		} catch (IOException e) {
			e.printStackTrace();
		}
		}else {
			System.out.println("edit");
			Card card=cardRepository.findByCode(code);
			if (file.getOriginalFilename()=="") {
				System.out.println("file is not edited");
				fileName=card.getImage();
			}else {
				System.out.println("file is edited");
				fileName =file.getOriginalFilename(); 
			}
			try {
				String filePath = Paths.get(uploadDirectory,fileName).toString();
				
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
				
				stream.write(file.getBytes());
				stream.close();
			
			
			card.setCode(code);
			card.setImage(fileName);
			card.setMonth(month);
			card.setNumber(number);
			card.setYear(year);
			card.setUser(user);
			card.setName(name);
			
			cardRepository.save(card);
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
			
		
		
		
		
		
        return "redirect:/card/view";
    }
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String cardEdit(@RequestParam(value = "cardCode") int cardCode, Model model){

        Card card = cardRepository.findByCode(cardCode);
        List<Card> cards = cardRepository.findAll();

        model.addAttribute("cards", cards);
        model.addAttribute("card", card);

        return "editCard";
    }
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
    @Transactional
    public String cardDelete(@RequestParam(value = "cardCode") int cardCode, Model model){

        cardRepository.deleteByCode(cardCode);

        List<Card> cards = cardRepository.findAll();

        Card card = new Card();
        model.addAttribute("card", card);
        model.addAttribute("cards", cards);


        return "cards";
    }
}
