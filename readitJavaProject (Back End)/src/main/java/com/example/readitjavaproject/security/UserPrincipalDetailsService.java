package com.example.readitjavaproject.security;

import com.example.readitjavaproject.domain.User;
import com.example.readitjavaproject.repository.IUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {

    private final IUserRepository userRepository;

    public UserPrincipalDetailsService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        final User user = this.userRepository.findByUsername(s).orElseThrow(() -> new UsernameNotFoundException("User login not found"));
        return new UserPrincipal(user);
    }
}
