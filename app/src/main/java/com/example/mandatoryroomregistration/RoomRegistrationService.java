package com.example.mandatoryroomregistration;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface RoomRegistrationService {
    @GET("Rooms/free/{time}")
    Call<List<Rooms>> getAllFreeRooms(@Path("time")int time);

    @GET("Rooms")
    Call<List<Rooms>> getAllRooms();

    @GET("Reservations/room/{id}")
    Call<List<Rooms>> getRoomReservations(@Path("time") int time);
}
