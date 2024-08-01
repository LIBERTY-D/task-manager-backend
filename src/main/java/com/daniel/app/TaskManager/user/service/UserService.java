package com.daniel.app.TaskManager.user.service;


import com.daniel.app.TaskManager.user.dto.AuthResponse;
import com.daniel.app.TaskManager.user.model.UserModel;
import com.daniel.app.TaskManager.user.repository.UserRepository;

import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
public class UserService {

    private  final UserRepository userRepository;
    private  final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private  final AuthenticationManager authenticationManager;


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;

        this.authenticationManager = authenticationManager;

    }

    //signup
    public AuthResponse signUp(UserModel request){

         request.setPassword(passwordEncoder.encode(request.getPassword()));
         //encode password

        request= userRepository.save(request);
         String token =  jwtService.generateToken(request);
        return    new AuthResponse("token generated",token,
                request.getId(),
                request.getFirstName(),request.getLastName(),
                request.getEmail(),request.getProfile(),
                request.getPassword(), request.getUsername());
    }

    //login
    public  AuthResponse signIn(UserModel request){

        UserModel userModel =
                userRepository.findByUsername(request.getUsername());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));

        String token  =  jwtService.generateToken(userModel);
        return  new AuthResponse("token generated",token,userModel.getId(),
                userModel.getFirstName(),userModel.getLastName(),
                userModel.getEmail(),userModel.getProfile(),
                userModel.getPassword(), userModel.getUsername());

    }

   public List<UserModel> getUsers(){

        return this.userRepository.findAll();
   }


    //update
    public UserModel updateUser(Integer userId,UserModel user){
        var getUser =  this.userRepository.findById(userId).get();
        if(user.getPassword() != null){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }else{
            user.setPassword(getUser.getPassword());
        }


        user.setUsername(getUser.getUsername());
        return this.userRepository.save(user);

    }
    // get user by id

    public UserModel getUser(Integer userID){
        if(this.userRepository.findById(userID).isPresent()){
            return this.userRepository.findById(userID).get();

        }
      throw  new NoSuchElementException("No such user with Id "+userID);

    }

    //delete
    public void deleteUser(Integer userId){

        this.userRepository.deleteById(userId);
    }
}
