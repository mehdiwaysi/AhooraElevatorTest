package com.snayab.ahooraelevator.ui.price_request.repository;

import com.snayab.ahooraelevator.ui.price_request.model.RequestPriceRequest;
import com.snayab.ahooraelevator.ui.price_request.model.RequestPriceResponse;

import io.reactivex.Single;

public class ApiRequestPriceDataSource implements RequestPriceDataSource {

    RequestPriceApiService requestPriceApiService = new RequestPriceApiService();


    @Override
    public Single<RequestPriceResponse> requestPrice(RequestPriceRequest requestPriceReques) {
        return requestPriceApiService.requestPrice(requestPriceReques);
    }
}
