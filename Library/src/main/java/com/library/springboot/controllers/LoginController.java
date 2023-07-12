package com.library.springboot.controllers;

import com.library.springboot.library_classes.Worker;
import com.library.springboot.services.WorkerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private WorkerService workerService;
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        request.logout();
        return "logout";
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("worker", new Worker());
        return "register";
    }
        @PostMapping("/register")
        public String registerWorker(@ModelAttribute("worker") Worker worker){
            workerService.registerUser(worker.getUsername(), worker.getPassword());
            return "redirect:/login";
        }
}