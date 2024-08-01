package com.daniel.app.TaskManager.Profile.repository;

import com.daniel.app.TaskManager.Profile.model.ProfileModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepo  extends JpaRepository<ProfileModel,Integer> {

}
