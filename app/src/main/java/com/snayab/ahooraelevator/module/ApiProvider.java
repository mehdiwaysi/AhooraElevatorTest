package com.snayab.ahooraelevator.module;

public class ApiProvider {

    public static ApiService apiService;
    public static ApiService apiServiceSnaagrin;

    public static ApiService apiProvider() {
        if (apiService == null) {
            apiService = ApiClient.getClient().create(ApiService.class);
        }
        return apiService;
    }

    public static ApiService apiProviderSnaagrin() {
        if (apiServiceSnaagrin == null) {
            apiServiceSnaagrin = ApiClient.getClientSnaagrin().create(ApiService.class);
        }
        return apiServiceSnaagrin;
    }

}
