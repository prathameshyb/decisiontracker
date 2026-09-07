package com.prathamesh.decisiontracker.mvc;


import com.prathamesh.decisiontracker.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyRESTController {

    @Autowired
    private final JwtService jwtService;

    public DummyRESTController(JwtService jwtService) {
        this.jwtService = jwtService;
    }


    @GetMapping("/who-am-i")
    public String returnWhoAmI() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth = context.getAuthentication();
        if (auth != null) {
            return "Username: " + auth.getName() + " Principal: " + auth.getPrincipal() + " Authorities: " + auth.getAuthorities() + " Authorised: " + auth.isAuthenticated();
        }
        return "No user found";
    }

    @GetMapping("/dummy-delete")
    public String performDummyDelete() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth = context.getAuthentication();
        SimpleGrantedAuthority adminAuthority = new SimpleGrantedAuthority("ROLE_ADMIN");
        if (auth != null && auth.getAuthorities().contains(adminAuthority) ) {
            return "Access granted to delete";
        }
        return "Access denied to delete";
    }
}