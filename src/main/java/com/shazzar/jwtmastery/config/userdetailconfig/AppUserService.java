package com.shazzar.jwtmastery.config.userdetailconfig;

import com.shazzar.jwtmastery.entity.User;
import com.shazzar.jwtmastery.model.Mapper;
import com.shazzar.jwtmastery.model.request.JwtRequest;
import com.shazzar.jwtmastery.model.response.UserResponse;
import com.shazzar.jwtmastery.repo.UserRepo;
import com.shazzar.jwtmastery.util.JwtUtil;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserService implements UserDetailsService {

    private final UserRepo userRepo;
    private final JwtUtil jwtUtil;

    public AppUserService(UserRepo userRepo, JwtUtil jwtUtil) {
        this.userRepo = userRepo;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException(String.format("user with %s %s not found", "email", email)));

        return new AppUser(user);
    }

    public UserResponse signInAuthentication(JwtRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();
        jwtUtil.authenticate(username, password);

        UserDetails userDetails = loadUserByUsername(username);
        String jwt = jwtUtil.generateToken(userDetails);
        User user = userRepo.findByEmail(username).orElseThrow(() ->
                new UsernameNotFoundException(String.format("user with %s %s not found", "email", username)));

        return Mapper.userResponseWithJwt(user, jwt);
    }


}
