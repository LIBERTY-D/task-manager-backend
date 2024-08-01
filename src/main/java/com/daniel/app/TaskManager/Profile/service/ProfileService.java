package com.daniel.app.TaskManager.Profile.service;


import com.daniel.app.TaskManager.Profile.model.ProfileModel;
import com.daniel.app.TaskManager.Profile.repository.ProfileRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProfileService {

    private  final ProfileRepo profileRepo;

    public ProfileService(ProfileRepo profileRepo) {
        this.profileRepo = profileRepo;
    }


    public List<ProfileModel> getProfiles(){
         return  profileRepo.findAll();
    }
    public  ProfileModel  createProfile(ProfileModel profileModel) {
        return  profileRepo.save(profileModel);
    }

    public ProfileModel updateProfile(Integer id, ProfileModel profileModel){

        Optional<ProfileModel> profile = this.getProfile(id);
        if(profile.isEmpty()){
            throw new NoSuchElementException("No such profile with id "+id);
        }

        if(this.getProfile(id).isPresent()){
            profileModel.setUser(this.getProfile(id).get().getUser());
        }

        profileModel.setId(id);

        return this.profileRepo.save(profileModel);

    }

    public Optional<ProfileModel> getProfile(Integer id){

        return  this.profileRepo.findById(id);
    }

    public  void deleteProfile(Integer id){
        this.profileRepo.deleteById(id);
    }
}
