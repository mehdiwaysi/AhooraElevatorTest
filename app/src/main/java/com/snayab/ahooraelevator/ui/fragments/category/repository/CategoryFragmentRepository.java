package com.snayab.ahooraelevator.ui.fragments.category.repository;


import com.snayab.ahooraelevator.ui.fragments.category.model.ElevatorTypesResponse;
import com.snayab.ahooraelevator.ui.fragments.main.model.HomePageResponse;
import com.snayab.ahooraelevator.ui.fragments.main.repository.ApiHomeFragmentDataSource;
import com.snayab.ahooraelevator.ui.fragments.main.repository.HomeFragmentDataSource;

import io.reactivex.Single;

public class CategoryFragmentRepository implements CategoryFragmentDataSource {

    private ApiCategoryFragmentDataSource categoryFragmentDataSource = new ApiCategoryFragmentDataSource();


    @Override
    public Single<ElevatorTypesResponse> getElevatorTypes() {
        return categoryFragmentDataSource.getElevatorTypes();
    }
}
