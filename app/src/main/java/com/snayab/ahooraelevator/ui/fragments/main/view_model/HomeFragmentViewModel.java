package com.snayab.ahooraelevator.ui.fragments.main.view_model;

import com.snayab.ahooraelevator.ui.fragments.main.model.HomePageResponse;
import com.snayab.ahooraelevator.ui.fragments.main.repository.HomeFragmentRepository;

import io.reactivex.Single;

public class HomeFragmentViewModel {

    private HomeFragmentRepository homeFragmentRepository = new HomeFragmentRepository();


    public Single<HomePageResponse> getHomePageData() {
        return homeFragmentRepository.getHomePageData();
    }
}
