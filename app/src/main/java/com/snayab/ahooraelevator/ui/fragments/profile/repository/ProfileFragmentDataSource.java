package com.snayab.ahooraelevator.ui.fragments.profile.repository;

import com.snayab.ahooraelevator.ui.fragments.category.model.ElevatorTypesResponse;
import com.snayab.ahooraelevator.ui.fragments.profile.model.ProfileResponseResponse;

import io.reactivex.Single;

public interface ProfileFragmentDataSource {

    Single<ProfileResponseResponse> getUserInfo();
}