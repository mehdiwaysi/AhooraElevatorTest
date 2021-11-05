package com.snayab.ahooraelevator.ui.fragments.profile.repository;

import com.snayab.ahooraelevator.ui.fragments.category.model.ElevatorTypesResponse;
import com.snayab.ahooraelevator.ui.fragments.category.repository.CategoryFragmentApiService;
import com.snayab.ahooraelevator.ui.fragments.category.repository.CategoryFragmentDataSource;
import com.snayab.ahooraelevator.ui.fragments.profile.model.ProfileResponseResponse;

import io.reactivex.Single;

public class ApiProfileFragmentDataSource implements ProfileFragmentDataSource {

    private ProfileFragmentApiService profileFragmentApiService = new ProfileFragmentApiService();




    @Override
    public Single<ProfileResponseResponse> getUserInfo() {
        return profileFragmentApiService.getUserInfo();
    }
}
