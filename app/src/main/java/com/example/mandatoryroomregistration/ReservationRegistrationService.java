package com.example.mandatoryroomregistration;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface ReservationRegistrationService {
    @GET("Reservations/room/{id}")
    Call<List<Rooms>> getRoomReservations(@Path("time") int time);

    @POST("Reservations")
    Call<Reservation> saveReservationBody(@Body Reservation book);

    @DELETE("Reservations/{id}")
    Call<Reservation> deleteBook(@Path("id") int id);
}
