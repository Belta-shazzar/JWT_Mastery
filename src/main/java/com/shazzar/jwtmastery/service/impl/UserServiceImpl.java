package com.shazzar.jwtmastery.service.impl;

import com.shazzar.jwtmastery.config.userdetailconfig.AppUser;
import com.shazzar.jwtmastery.entity.User;
import com.shazzar.jwtmastery.exception.ResourceNotFoundException;
import com.shazzar.jwtmastery.model.Mapper;
import com.shazzar.jwtmastery.model.request.UserRequest;
import com.shazzar.jwtmastery.model.response.NonRegistrationUserResponse;
import com.shazzar.jwtmastery.model.response.UserRegistrationResponse;
import com.shazzar.jwtmastery.repo.UserRepo;
import com.shazzar.jwtmastery.service.UserService;
import com.shazzar.jwtmastery.util.JwtUtil;
import lombok.SneakyThrows;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.shazzar.jwtmastery.entity.Role.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo repo;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;
    private static final String NOT_FOUND_ERROR_MSG = "%s with %s %s, not found";

    public UserServiceImpl(UserRepo repo, PasswordEncoder encoder, JwtUtil jwtUtil) {
        this.repo = repo;
        this.encoder = encoder;
        this.jwtUtil = jwtUtil;
    }


    @Override
    public UserRegistrationResponse officialSignup(UserRequest request) {
        User officialUser = getUser(request);
        officialUser.setPassword(encoder.encode(request.getPassword()));
        officialUser.setRole(OFFICIAL);
        repo.save(officialUser);

//        return user with jwt token
        String jwtToken = jwtUtil.generateToken(new AppUser(officialUser));

        return Mapper.userResponseWithJwt(officialUser, jwtToken);
    }

    @Override
    public UserRegistrationResponse casualSignup(UserRequest request) {
        User casualUser = getUser(request);
        casualUser.setPassword(encoder.encode(request.getPassword()));
        casualUser.setRole(CASUAL);
        repo.save(casualUser);

//        return user with jwt token
        String jwtToken = jwtUtil.generateToken(new AppUser(casualUser));

        return Mapper.userResponseWithJwt(casualUser, jwtToken);
    }

    private User getUser(UserRequest request) {
        return Mapper.userRequestToUser(request);
    }

    @SneakyThrows
    @Override
    public NonRegistrationUserResponse getbyId(Long id) {
        User user = repo.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(String.format(NOT_FOUND_ERROR_MSG, "User", "id", id)));

        return Mapper.userTouserResponse(user);
    }

    @Override
    public List<NonRegistrationUserResponse> getAllUser() {
        List<User> users = repo.findAll();
        return Mapper.UsersToUserLIstResponse(users);
    }

    @SneakyThrows
    @Override
    public void deleteById(Long id) {
        User user = repo.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(String.format(NOT_FOUND_ERROR_MSG, "User", "id", id)));
        repo.delete(user);
    }
}
