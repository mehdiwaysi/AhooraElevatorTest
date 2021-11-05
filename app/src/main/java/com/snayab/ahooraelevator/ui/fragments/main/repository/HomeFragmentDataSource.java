package com.snayab.ahooraelevator.ui.fragments.main.repository;

import com.snayab.ahooraelevator.ui.fragments.main.model.HomePageResponse;
import io.reactivex.Single;

public interface HomeFragmentDataSource {

    Single<HomePageResponse> getHomePageData();
}