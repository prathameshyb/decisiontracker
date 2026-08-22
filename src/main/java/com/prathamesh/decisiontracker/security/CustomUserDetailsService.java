package com.prathamesh.decisiontracker.security;

import com.prathamesh.decisiontracker.repository.AdminRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AdminRepository adminRepository;

    public CustomUserDetailsService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {


        return adminRepository.findByAdminName(userName).orElseThrow(() -> new UsernameNotFoundException("Admin not found"));
    }

}
