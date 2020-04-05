package com.example.mandatoryroomregistration;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface RoomRegistrationService {
    @GET("Rooms/free/{time}")
    Call<List<Rooms>> getAllFreeRooms(@Path("time")int time);

    @GET("Rooms")
    Call<List<Rooms>> getAllRooms();
}
