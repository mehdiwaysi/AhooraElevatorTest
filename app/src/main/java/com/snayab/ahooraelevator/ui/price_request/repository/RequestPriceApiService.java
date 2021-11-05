package com.snayab.ahooraelevator.ui.price_request.repository;

import com.snayab.ahooraelevator.module.ApiProvider;
import com.snayab.ahooraelevator.module.ApiService;
import com.snayab.ahooraelevator.ui.price_request.model.RequestPriceRequest;
import com.snayab.ahooraelevator.ui.price_request.model.RequestPriceResponse;

import io.reactivex.Single;

public class RequestPriceApiService implements RequestPriceDataSource {
    private ApiService apiService = ApiProvider.apiProvider();


    @Override
    public Single<RequestPriceResponse> requestPrice(RequestPriceRequest requestPriceRequest) {
        return apiService.requestPrice(requestPriceRequest);
    }


}
