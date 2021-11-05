package com.snayab.ahooraelevator.ui.price_request.view_model;


import com.snayab.ahooraelevator.ui.price_request.model.RequestPriceRequest;
import com.snayab.ahooraelevator.ui.price_request.model.RequestPriceResponse;
import com.snayab.ahooraelevator.ui.price_request.repository.RequestPriceRepository;

import io.reactivex.Single;

public class RequestPriceViewModel {

    RequestPriceRepository requestPriceRepository = new RequestPriceRepository();

    public Single<RequestPriceResponse> requestPrice(RequestPriceRequest requestPriceReques) {
        return requestPriceRepository.requestPrice(requestPriceReques);
    }

}
