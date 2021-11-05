package com.snayab.ahooraelevator.module;


import com.snayab.ahooraelevator.ui.about_company.model.AboutCompanyResponse;
import com.snayab.ahooraelevator.ui.about_us.model.AboutUsResponseModel;
import com.snayab.ahooraelevator.ui.code_verification.model.UserAuthVerifyResendResponse;
import com.snayab.ahooraelevator.ui.code_verification.model.UserAuthVerifyResponse;
import com.snayab.ahooraelevator.ui.contact_us.model.ContactUsResponseModel;
import com.snayab.ahooraelevator.ui.contracts.model.ContractLastResponse;
import com.snayab.ahooraelevator.ui.contracts.model.ContractSingleResponse;
import com.snayab.ahooraelevator.ui.enter.model.UserAuthEnterResponseModel;
import com.snayab.ahooraelevator.ui.fragments.category.model.ElevatorTypesResponse;
import com.snayab.ahooraelevator.ui.fragments.main.model.HomePageResponse;
import com.snayab.ahooraelevator.ui.fragments.profile.model.ProfileResponseResponse;
import com.snayab.ahooraelevator.ui.periodic_services.model.PeriodicServicesResponse;
import com.snayab.ahooraelevator.ui.price_request.model.RequestPriceRequest;
import com.snayab.ahooraelevator.ui.price_request.model.RequestPriceResponse;
import com.snayab.ahooraelevator.ui.repair_request.model.ElevatorFailureResponse;
import com.snayab.ahooraelevator.ui.repair_request.model.RepairRequestResponse;
import com.snayab.ahooraelevator.ui.rules.model.RulesResponseModel;
import com.snayab.ahooraelevator.ui.splash.model.ForceUpdateResponse;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @FormUrlEncoded
    @POST("login")
    Single<UserAuthEnterResponseModel> enter(@Field("phone") String phone, @Field("hash_code") String hash_code, @Field("version_id") int version_id);

    @FormUrlEncoded
    @POST("verify")
    Single<UserAuthVerifyResponse> authVerity(@Field("verification_code") int verification_code, @Field("phone") String phone, @Field("name") String name);

    @FormUrlEncoded
    @POST("resend_code")
    Single<UserAuthVerifyResendResponse> resendVerificationCode(@Field("phone") String phone);

    @FormUrlEncoded
    @POST("force_update")
    Single<ForceUpdateResponse> checkForceUpdate(@Field("version_id") int versionId);

    //about_company
    @GET("about_us")
    Single<AboutCompanyResponse> getAboutCompany(@Query("package_name") String packageName, @Query("user_id") int userId);

    //about_company
    @GET("home")
    Single<HomePageResponse> getHomePageData();

    //about_company
    @GET("elevators")
    Single<ElevatorTypesResponse> getElevatorTypes();

    @GET("elevator_failures")
    Single<ElevatorFailureResponse> getElevatorFailures();

    @GET("contracts/last_contract")
    Single<ContractLastResponse> getLastContract(@Query("user_id") int userId);

    @GET("contracts/single")
    Single<ContractSingleResponse> getSingleContract(@Query("contract_id") int contractId);


    @GET("periodic_services/last_contract")
    Single<PeriodicServicesResponse> getPeriodicServices(@Query("user_id") int userId);

    @GET("periodic_services/single")
    Single<PeriodicServicesResponse> getPeriodicServiceSingle(@Query("contract_id") int contractId);

    @POST("price_requests/store")
    Single<RequestPriceResponse> requestPrice(@Body RequestPriceRequest requestPriceRequest);

    @FormUrlEncoded
    @POST("repair_requests/store")
    Single<RepairRequestResponse> repairRequest(@Field("user_id") int user_id, @Field("elevator_failure_id") int elevator_failure_id, @Field("address") String address, @Field("primary_phone") String primary_phone, @Field("Secondary_phone") String Secondary_phone , @Field("description") String description);

    @GET("about_us")
    Single<AboutUsResponseModel> getAboutUs();

    @GET("rules")
    Single<RulesResponseModel> getRules();

    @GET("contact_us")
    Single<ContactUsResponseModel> getContactUs();

    @GET("user/info")
    Single<ProfileResponseResponse> getUserInfo();

}
