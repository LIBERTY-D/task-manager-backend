package com.daniel.app.TaskManager.Profile.controller;


import com.daniel.app.TaskManager.Profile.dto.*;
import com.daniel.app.TaskManager.Profile.mapper.ProfileMapper;
import com.daniel.app.TaskManager.Profile.model.ProfileModel;
import com.daniel.app.TaskManager.Profile.service.ProfileService;
import com.daniel.app.TaskManager.user.dto.GetSingleUserResponseDto;
import com.daniel.app.TaskManager.user.model.UserModel;
import com.daniel.app.TaskManager.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Optional;

@CrossOrigin
@Controller
@RequestMapping("api/v1/profiles")
public class ProfileController {

    private final ProfileService profileService;
    private final ProfileMapper profileMapper;
    private final UserService userService;

    public ProfileController(ProfileService profileService, ProfileMapper profileMapper, UserService userService) {
        this.profileService = profileService;
        this.profileMapper = profileMapper;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<ProfileResponseDtoParent> getProfiles() {
        var profiles = profileService.getProfiles();

        return profileMapper.profileModelsToProfilesResponseDto(profiles);
    }

    @PostMapping("create")
    public ResponseEntity<ProfileResponseDtoParent> createProfile(@RequestBody @Valid CreateProfileDto createDto) {


        Integer id = createDto.user().getId();
        UserModel user = userService.getUser(id);
        ProfileModel profileModel = profileMapper.createProfileDtoToProfileModel(createDto);
        profileModel.setUser(user);
        ProfileModel savedProfileModel = profileService.createProfile(profileModel);
        return profileMapper.profileModelToCreateResponseDto(savedProfileModel);

    }

    @PatchMapping("{profileId}")
    public ResponseEntity<ProfileResponseDtoParent> updateProfile(
            @PathVariable("profileId") Integer id,
            @RequestParam("desc") String desc,
            @RequestParam(value = "profileImg", required = false) MultipartFile profileImg
    ) throws IOException {

        UpdateProfileDto updateProfileDto =null;
        if(profileImg!=null){
            updateProfileDto = new UpdateProfileDto(profileImg.getBytes(),desc);
        }else{
            updateProfileDto =
                    new UpdateProfileDto(this.profileService.getProfile(id).get().getProfileImage(),
                            desc);
        }

        ProfileModel savedProfileModel = profileMapper.updateProfileDtoToProfileModel(updateProfileDto);
        ProfileModel updatedProfile = profileService.updateProfile(id, savedProfileModel);
         return profileMapper.profileModelToUpdateResponseDto(updatedProfile);
    }


    @GetMapping("{profileId}")
    public  ResponseEntity<ProfileResponseDtoParent> getProfile(@PathVariable("profileId") Integer id){

        var profile =  this.profileService.getProfile(id);
        return profileMapper.profileModelsToSingleProfileResponseDto(profile);
    }

    @DeleteMapping("{profileId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public  void deleteProfile(@PathVariable("profileId")  Integer id){
        this.profileService.deleteProfile(id);
    }


    //handle missing body
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleValidationExceptions(HttpMessageNotReadableException e) {

        return new ResponseEntity<>(e.getMessage().split(":")[0],
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException e) {
        HashMap<String,String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error->{
        String field = ((FieldError)error).getField();
        String message =  error.getDefaultMessage();
        errors.put(field,message);
    });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<GetSingleUserResponseDto> notFoundException(NoSuchElementException exp) {


        return new ResponseEntity<GetSingleUserResponseDto>(new GetSingleUserResponseDto(exp.getMessage(), 404, null,null), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> userException(DataIntegrityViolationException exp){


        return  ResponseEntity.badRequest().body("user_id already exists");
    }
}
