package com.snayab.ahooraelevator.ui.fragments.profile.repository;

import com.snayab.ahooraelevator.module.ApiProvider;
import com.snayab.ahooraelevator.module.ApiService;
import com.snayab.ahooraelevator.ui.fragments.category.model.ElevatorTypesResponse;
import com.snayab.ahooraelevator.ui.fragments.category.repository.CategoryFragmentDataSource;
import com.snayab.ahooraelevator.ui.fragments.profile.model.ProfileResponseResponse;

import io.reactivex.Single;

public class ProfileFragmentApiService implements ProfileFragmentDataSource {
    private ApiService apiService = ApiProvider.apiProvider();



    @Override
    public Single<ProfileResponseResponse> getUserInfo() {
        return apiService.getUserInfo();
    }
}
