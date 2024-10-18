package com.inventoryManagementSystem.ims.controller;

import com.inventoryManagementSystem.ims.model.User;
import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, HttpServletResponse response) throws IOException {
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