package com.inventoryManagementSystem.ims.controller;

import com.inventoryManagementSystem.ims.model.User;
import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;

@Controller
public class DashboardController {

    @GetMapping("/")
    public String landingPage(Model model) {
        model.addAttribute("title", "Landing Page");
        model.addAttribute("pageTitle", "Welcome to Inventory Management System");
        return "landing";
    }

    // @GetMapping("/login")
    // public void rootRedirect(HttpServletResponse response) throws IOException {
    //     response.sendRedirect("/login");
    // }


    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, HttpServletResponse response,Model model) throws IOException {
        model.addAttribute("title", "Dashboard");
        model.addAttribute("pageTitle", "Dashboard");
        // Check if user exists in the session
        User user = (User) session.getAttribute("user");
        
        
        if (user != null) {
            // User exists, return the dashboard view
            return "dashboard";
        } else {
            // User not logged in, redirect to the login page
            response.sendRedirect("/login");
            return null;  // Explicitly return null since response is already handled
        }
    }
}