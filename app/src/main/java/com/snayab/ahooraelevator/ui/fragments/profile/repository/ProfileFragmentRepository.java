package com.snayab.ahooraelevator.ui.fragments.profile.repository;


import com.snayab.ahooraelevator.ui.fragments.category.model.ElevatorTypesResponse;
import com.snayab.ahooraelevator.ui.fragments.category.repository.ApiCategoryFragmentDataSource;
import com.snayab.ahooraelevator.ui.fragments.category.repository.CategoryFragmentDataSource;
import com.snayab.ahooraelevator.ui.fragments.profile.model.ProfileResponseResponse;

import io.reactivex.Single;

public class ProfileFragmentRepository implements ProfileFragmentDataSource {

    private ApiProfileFragmentDataSource apiProfileFragmentDataSource = new ApiProfileFragmentDataSource();

    @Override
    public Single<ProfileResponseResponse> getUserInfo() {
        return apiProfileFragmentDataSource.getUserInfo();
    }
}
