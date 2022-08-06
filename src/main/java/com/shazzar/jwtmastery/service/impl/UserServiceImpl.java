package com.shazzar.jwtmastery.service.impl;

import com.shazzar.jwtmastery.config.userdetailconfig.AppUser;
import com.shazzar.jwtmastery.entity.User;
import com.shazzar.jwtmastery.model.Mapper;
import com.shazzar.jwtmastery.model.request.UserRequest;
import com.shazzar.jwtmastery.model.response.UserResponse;
import com.shazzar.jwtmastery.repo.UserRepo;
import com.shazzar.jwtmastery.service.UserService;
import com.shazzar.jwtmastery.util.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.shazzar.jwtmastery.entity.Role.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo repo;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    public UserServiceImpl(UserRepo repo, PasswordEncoder encoder, JwtUtil jwtUtil) {
        this.repo = repo;
        this.encoder = encoder;
        this.jwtUtil = jwtUtil;
    }


    @Override
    public UserResponse officialSignup(UserRequest request) {
        User user = Mapper.userRequestToUser(request);
        user.setPassword(encoder.encode(request.getPassword()));
        user.setRole(OFFICIAL);
        repo.save(user);

//        return user with jwt token
        String jwtToken = jwtUtil.generateToken(new AppUser(user));

        return Mapper.userResponseWithJwt(user, jwtToken);
    }

    @Override
    public UserResponse casualSignup(UserRequest request) {
        User user = Mapper.userRequestToUser(request);
        user.setPassword(encoder.encode(request.getPassword()));
        user.setRole(CASUAL);
        repo.save(user);

//        return user with jwt token
        String jwtToken = jwtUtil.generateToken(new AppUser(user));

        return Mapper.userResponseWithJwt(user, jwtToken);
    }
}
