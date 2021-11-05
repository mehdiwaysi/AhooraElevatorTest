package com.snayab.ahooraelevator.ui.fragments.category.view_model;

import com.snayab.ahooraelevator.ui.fragments.category.model.ElevatorTypesResponse;
import com.snayab.ahooraelevator.ui.fragments.category.repository.CategoryFragmentRepository;
import com.snayab.ahooraelevator.ui.fragments.main.model.HomePageResponse;
import com.snayab.ahooraelevator.ui.fragments.main.repository.HomeFragmentRepository;

import io.reactivex.Single;

public class CategoryFragmentViewModel {

    private CategoryFragmentRepository categoryFragmentRepository = new CategoryFragmentRepository();


    public Single<ElevatorTypesResponse> getElevatorTypes() {
        return categoryFragmentRepository.getElevatorTypes();
    }


}
