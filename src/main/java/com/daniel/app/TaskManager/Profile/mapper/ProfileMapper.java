package com.daniel.app.TaskManager.Profile.mapper;

import com.daniel.app.TaskManager.Profile.dto.*;
import com.daniel.app.TaskManager.Profile.model.ProfileModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProfileMapper {


    public ProfileModel createProfileDtoToProfileModel(CreateProfileDto createProfileDto){
        ProfileModel profileModel = new ProfileModel();
        profileModel.setDesc(createProfileDto.desc());
        profileModel.setProfileImage(createProfileDto.profileImage());
        profileModel.setUser(createProfileDto.user());
        return profileModel;
    }
    public ProfileModel updateProfileDtoToProfileModel(UpdateProfileDto updateProfileDto){
        ProfileModel profileModel = new ProfileModel();
        profileModel.setDesc(updateProfileDto.desc());
        profileModel.setProfileImage(updateProfileDto.profileImage());
        return profileModel;
    }


    public ResponseEntity<ProfileResponseDtoParent> profileModelToCreateResponseDto(ProfileModel profileModel){

           CreateProfileResponseDto createProfileResponseDto=
                   new CreateProfileResponseDto("Profile created " +
                           "successfully", 201, profileModel);

       return new ResponseEntity<>(createProfileResponseDto, HttpStatus.CREATED);
    }
    public ResponseEntity<ProfileResponseDtoParent> profileModelToUpdateResponseDto(ProfileModel profileModel){

        UpdateProfileResponseDto updateProfileResponseDto=
                new UpdateProfileResponseDto("Profile updated " +
                        "successfully", 200, profileModel);

        return new ResponseEntity<>(updateProfileResponseDto, HttpStatus.OK);
    }

    public ResponseEntity<ProfileResponseDtoParent> profileModelsToProfilesResponseDto(List<ProfileModel> profileModel){

        GetProfilesResponseDto profiles = new GetProfilesResponseDto("Profiles fetched successfully", 200, profileModel.size(), profileModel);
        return new ResponseEntity<>(profiles, HttpStatus.OK);
    }
    public ResponseEntity<ProfileResponseDtoParent> profileModelsToSingleProfileResponseDto(Optional<ProfileModel> profileModel){

        return  new ResponseEntity<>(new GetSingleProfileResponseDto("Profile" +
                " " +
                "fetched successfully",200,profileModel),
                HttpStatus.OK);
    }
}
