package com.snayab.ahooraelevator.ui.fragments.profile.view_model;

import com.snayab.ahooraelevator.ui.fragments.category.model.ElevatorTypesResponse;
import com.snayab.ahooraelevator.ui.fragments.category.repository.CategoryFragmentRepository;
import com.snayab.ahooraelevator.ui.fragments.profile.model.ProfileResponseResponse;
import com.snayab.ahooraelevator.ui.fragments.profile.repository.ProfileFragmentRepository;

import io.reactivex.Single;

public class ProfileFragmentViewModel {

    private ProfileFragmentRepository profileFragmentRepository = new ProfileFragmentRepository();

    public Single<ProfileResponseResponse> getUserInfo() {
        return profileFragmentRepository.getUserInfo();
    }

}
