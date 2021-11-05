package com.snayab.ahooraelevator.ui.fragments.category.repository;

import com.snayab.ahooraelevator.ui.fragments.category.model.ElevatorTypesResponse;
import com.snayab.ahooraelevator.ui.fragments.main.model.HomePageResponse;
import com.snayab.ahooraelevator.ui.fragments.main.repository.HomeFragmentApiService;
import com.snayab.ahooraelevator.ui.fragments.main.repository.HomeFragmentDataSource;

import io.reactivex.Single;

public class ApiCategoryFragmentDataSource implements CategoryFragmentDataSource {

    private CategoryFragmentApiService categoryFragmentApiService = new CategoryFragmentApiService();


    @Override
    public Single<ElevatorTypesResponse> getElevatorTypes() {
        return categoryFragmentApiService.getElevatorTypes();
    }
}
