package com.anzuza.anzuza.controller;

import com.anzuza.anzuza.model.User;
import com.anzuza.anzuza.model.Visitor;
import com.anzuza.anzuza.repository.VisitorRepository;
import com.anzuza.anzuza.service.UserManagerService;
import com.anzuza.anzuza.service.impl.VisitorServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value="/visitor")
public class VisitorController {

    @Autowired
    VisitorServiceImpl studentService;

    @Autowired
    UserManagerService userManagerService;
    private VisitorRepository visitorRepository;

    @GetMapping(value = "/home")
    public String homePage() {
        return "home-page";
    }

    @GetMapping(value = "/registration")
    public String registerVisitor(Model model) {
        Visitor visitor = new Visitor();
        model.addAttribute("visitor", visitor);
        return "registration-page";

    }

    @GetMapping(value = "/view")
    public String home(Model model, HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        if(userId == null){
            return "home-page";
        }
        List<Visitor> visitors = studentService.getAll(Long.valueOf(userId));
        model.addAttribute("visitor", visitors);
        System.out.println("attr "+session.getAttribute("userId"));
        return "home-page";
    }
    @PostMapping(value = "/register")
    public String createVisitor(@ModelAttribute("visitor") Visitor theVisitor,HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        if(userId == null){
            return "error-page";
        }
        Optional<User> user = userManagerService.getUserById(Long.valueOf(userId));
        theVisitor.setUser(user.get());
        Visitor savedVisitor = studentService.createVisitor(theVisitor);
        if (savedVisitor != null) {
            return "redirect:/visitor/home";
        }
        return "error-page";

    }
    @GetMapping(value = "/updateForm")
    public String showFormForUpdate(@RequestParam("id") int id, Model theModel) {
        Optional<Visitor> theStudent = studentService.findVisitorById(id);
        theModel.addAttribute("student", theStudent);
        return "edit";
    }
    @GetMapping(value = "/deleteForm")
    public String showFormForDelete(@RequestParam("id") int id, Model theModel) {
        Optional<Visitor> visitor = studentService.findVisitorById(id);
        theModel.addAttribute("visitor", visitor);
        return "delete";
    }


    @PostMapping(value = "/deleteVisitor")
    public String deleteStudent(@RequestParam("id") int id) {
        studentService.deleteVisitor(id);
        return "redirect:/visitor/home";
    }

}
