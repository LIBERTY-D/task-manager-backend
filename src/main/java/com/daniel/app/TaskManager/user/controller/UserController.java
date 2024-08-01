package com.daniel.app.TaskManager.user.controller;


import com.daniel.app.TaskManager.user.dto.*;
import com.daniel.app.TaskManager.user.exception.UserException;
import com.daniel.app.TaskManager.user.mapper.UserModelMapper;
import com.daniel.app.TaskManager.user.model.UserModel;
import com.daniel.app.TaskManager.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin
@RestController()
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    private  final UserModelMapper userModelMapper;
    public UserController(UserService userService, UserModelMapper userModelMapper) {
        this.userService = userService;
        this.userModelMapper = userModelMapper;
    }

    //signup
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> signUp(@Valid @RequestBody CreateUserDto createUserDto){
        System.out.println("in");
        UserModel user = userModelMapper.createUserDtoToUserModel(createUserDto);
        return  ResponseEntity.ok(userService.signUp(user));
    }

    //login
    @PostMapping("/login")
    public  ResponseEntity<AuthResponse> signIn(@Valid  @RequestBody LoginDto loginDto){

        UserModel user = userModelMapper.loginUserDtoToUserModel(loginDto);
        return  ResponseEntity.ok(userService.signIn(user));
    }

   // admin only
    @GetMapping("/admin")
    public  ResponseEntity<List<UserModel>> getUsers(){



        return  ResponseEntity.ok(this.userService.getUsers());
    }


    @GetMapping("/{userId}")
    public ResponseEntity<GetSingleUserResponseDto> getUser(@PathVariable("userId")  Integer userID){

        UserModel user =  this.userService.getUser(userID);
        var profile = user.getProfile();
        return  new ResponseEntity<>(new GetSingleUserResponseDto("fetched " +
                "user",200,user,profile),
                HttpStatus.OK);
    }


    //update

   @PatchMapping("{userId}")
    public ResponseEntity<UserModel> updateUser(@PathVariable Integer userId,
                        @RequestBody UpdateDto updateDto){


        var mappedUser =  userModelMapper.updateUserDtoToUserModel(updateDto);
        mappedUser.setId(userId);
        var userDb =  this.userService.updateUser(userId,mappedUser);
        return  new ResponseEntity<UserModel>(userDb,HttpStatus.OK);

    }



    //delete
    @DeleteMapping("{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Integer userId){
        this.userService.deleteUser(userId);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public  ResponseEntity<UserException> methodArgumentNotValidException(MethodArgumentNotValidException exp){
        HashMap<String,Object> errors = new HashMap<>();
        exp.getBindingResult().getAllErrors().forEach(objectError -> {
            String field =  ((FieldError)objectError).getField();
            String msg = objectError.getDefaultMessage();
            errors.put(field,msg);
        });


        return  new ResponseEntity<>(new UserException(errors,
                HttpServletResponse.SC_BAD_REQUEST),
                HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<UserException> userException(DataIntegrityViolationException exp){
        HashMap<String,Object> errors = new HashMap<>();
            String msg = "Email or Username already exists";
            errors.put("email-password",msg);

        return  new ResponseEntity<>(new UserException(errors,
                HttpServletResponse.SC_BAD_REQUEST),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<GetSingleUserResponseDto> notFoundException(NoSuchElementException exp){


        return  new ResponseEntity<GetSingleUserResponseDto>(new GetSingleUserResponseDto(exp.getMessage(), 404,null,null),HttpStatus.NOT_FOUND);
    }


}
