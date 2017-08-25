package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    MessageRepository messageRepository;

    @RequestMapping("/")
    public String listMessage(Model model){
        model.addAttribute("message", messageRepository.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String messageForm(Model model){
        model.addAttribute("message", new Message());
        return "messageform";
    }

    @PostMapping("/add")
    public String messageForm(@Valid Message message, BindingResult result) {
        if (result.hasErrors()) {
            return "add";
        }
        messageRepository.save(message);
        return ("redirect:/list");
    }



    @PostMapping("/process")
        public String processForm (@Valid Message message, BindingResult result){
            if (result.hasErrors()) {
                return "messageform";

            }


            messageRepository.save(message);
            return ("redirect:/");
        }

        @RequestMapping("/content/{id}")
        public String showMessage (@PathVariable("message") Message message, Model model){
            model.addAttribute("message", messageRepository.findOne(message.getId()));
            return "show";
        }

        @RequestMapping("/posteddate/{id}")
        public String postedMessage ( @PathVariable("id") long id, Model model){
            model.addAttribute("message", messageRepository.findOne(id));
            return "messageform";
        }
     }

