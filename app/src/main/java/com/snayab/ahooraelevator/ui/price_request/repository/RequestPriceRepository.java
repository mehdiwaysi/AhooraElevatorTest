package com.snayab.ahooraelevator.ui.price_request.repository;

import com.snayab.ahooraelevator.ui.price_request.model.RequestPriceRequest;
import com.snayab.ahooraelevator.ui.price_request.model.RequestPriceResponse;

import io.reactivex.Single;

public class RequestPriceRepository implements RequestPriceDataSource {

    private ApiRequestPriceDataSource apiRequestPriceDataSource = new ApiRequestPriceDataSource();

    @Override
    public Single<RequestPriceResponse> requestPrice(RequestPriceRequest requestPriceReques) {
        return apiRequestPriceDataSource.requestPrice(requestPriceReques);
    }


}
