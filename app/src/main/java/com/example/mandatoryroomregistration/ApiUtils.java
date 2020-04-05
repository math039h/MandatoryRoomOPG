package com.example.mandatoryroomregistration;

class ApiUtils {
    private ApiUtils() {
    }

    private static final String BASE_URL = "http://anbo-roomreservationv3.azurewebsites.net/api/";

    public static RoomRegistrationService getRoomRegistrationService() {

        return RetrofitClient.getClient(BASE_URL).create(RoomRegistrationService.class);
    }
    public static ReservationRegistrationService getReservationRegistrationService() {

        return RetrofitClient.getClient(BASE_URL).create(ReservationRegistrationService.class);
    }
}
