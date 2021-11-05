package com.snayab.ahooraelevator.ui.fragments.category.repository;

import com.snayab.ahooraelevator.ui.fragments.category.model.ElevatorTypesResponse;
import com.snayab.ahooraelevator.ui.fragments.main.model.HomePageResponse;

import io.reactivex.Single;

public interface CategoryFragmentDataSource {

    Single<ElevatorTypesResponse> getElevatorTypes();
}