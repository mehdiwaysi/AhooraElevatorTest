package com.snayab.ahooraelevator.ui.fragments.category.repository;

import com.snayab.ahooraelevator.module.ApiProvider;
import com.snayab.ahooraelevator.module.ApiService;
import com.snayab.ahooraelevator.ui.fragments.category.model.ElevatorTypesResponse;
import com.snayab.ahooraelevator.ui.fragments.main.model.HomePageResponse;
import com.snayab.ahooraelevator.ui.fragments.main.repository.HomeFragmentDataSource;

import io.reactivex.Single;

public class CategoryFragmentApiService implements CategoryFragmentDataSource {
    private ApiService apiService = ApiProvider.apiProvider();


    @Override
    public Single<ElevatorTypesResponse> getElevatorTypes() {
        return apiService.getElevatorTypes();
    }
}
