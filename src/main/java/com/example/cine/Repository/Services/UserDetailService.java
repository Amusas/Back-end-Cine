package com.example.cine.Repository.Services;

import com.example.cine.Entity.Users.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private ClientService clientService;
    @Autowired
    private AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = clientService.findByUsername(username);
        if (user == null){
            user = adminService.findByUsername(username);
        }
        return User.withUsername(user.getUsername())
                .roles(user.getRol().toString())
                .build();
    }
}
