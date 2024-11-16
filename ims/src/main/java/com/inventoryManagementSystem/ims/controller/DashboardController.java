package com.inventoryManagementSystem.ims.controller;

import com.inventoryManagementSystem.ims.model.User;
import java.io.IOException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import jakarta.servlet.http.HttpServletRequest;
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

    @PostMapping("/query")
    @ResponseBody
    @CrossOrigin(origins = "*")  // Allow CORS requests during development; specify origins in production
    public ResponseEntity<?> query(@RequestBody Map<String, String> requestBody) {
        try {
            // Extract the question from the request body
            String question = requestBody.get("question");

            // External API URL
            String externalApiUrl = "https://imschatbotapi.onrender.com/query";

            // Create a RestTemplate instance to make the HTTP request
            RestTemplate restTemplate = new RestTemplate();

            // Prepare the JSON body to send to the external API
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Map<String, String>> request = new HttpEntity<>(requestBody, headers);

            // Make the POST request to the external API
            ResponseEntity<Map> response = restTemplate.exchange(
                externalApiUrl,
                HttpMethod.POST,
                request,
                Map.class
            );

            // Return the response from the external API
            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            // Handle exceptions and return an error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(Map.of("error", "Something went wrong: " + e.getMessage()));
        }
    }

}