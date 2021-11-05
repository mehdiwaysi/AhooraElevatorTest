package com.snayab.ahooraelevator.ui.price_request.repository;

import com.snayab.ahooraelevator.ui.price_request.model.RequestPriceRequest;
import com.snayab.ahooraelevator.ui.price_request.model.RequestPriceResponse;

import io.reactivex.Single;

public interface RequestPriceDataSource {

    Single<RequestPriceResponse> requestPrice(RequestPriceRequest requestPriceReques);

}

