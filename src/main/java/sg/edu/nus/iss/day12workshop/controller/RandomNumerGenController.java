package sg.edu.nus.iss.day12workshop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import sg.edu.nus.iss.day12workshop.model.Image;
import sg.edu.nus.iss.day12workshop.service.RandomNumService;

@Controller
public class RandomNumerGenController {
    
    @Autowired
    RandomNumService service;

    @GetMapping("/home")
    public String landingPage() {
        return "home";
    }

    @GetMapping("/get")
    public String generateRandomNums(Model model, HttpServletRequest request) {

        int number = Integer.parseInt(request.getParameter("number"));
        System.out.println("input no is: " + number);

        if (number < 1 || number > 30) {
            String errorMsg = "Invalid number: " + number;
            model.addAttribute("errorMsg", errorMsg);
            return "home";
        }

        List<Integer> randomNumbers = service.generateRandomNums(number);
        List<Image> imageList = new ArrayList<>();

        for (int randomNumber : randomNumbers) {
            imageList.add(new Image(Integer.toString(randomNumber), "/images/" + Integer.toString(randomNumber) + ".png"));
        }

        model.addAttribute("number", number);
        model.addAttribute(imageList);

        return "display";
    }   

}
