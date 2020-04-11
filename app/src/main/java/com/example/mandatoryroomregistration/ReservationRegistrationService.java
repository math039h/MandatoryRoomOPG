package com.example.mandatoryroomregistration;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface ReservationRegistrationService {
    @GET("Reservations")
    Call<List<Reservation>> ShowAllReservations();

    @POST("Reservations")
    Call<Integer> saveReservationBody(@Body Reservation reservation);

    @DELETE("Reservations/{id}")
    Call<Reservation> deleteBook(@Path("id") int id);
}
