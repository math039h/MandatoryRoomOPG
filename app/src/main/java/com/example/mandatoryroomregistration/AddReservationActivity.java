/*package com.example.mandatoryroomregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.util.TimeFormatException;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Time;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class AddReservationActivity extends AppCompatActivity {
    private static final String LOG_TAG = "RESERVATION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reservation);
    }

    public void addReservationButtonClicked(View view) {
        EditText formTimeField = findViewById(R.id.addReservationFromTimeEditText);
        EditText toTimeField = findViewById(R.id.addReservationToTimeEditText);
        EditText userIdField = findViewById(R.id.addReservationUserIdEditText);
        EditText purposeField = findViewById(R.id.addReservationPurposeEditText);
        EditText roomIdField = findViewById(R.id.addReservationRoomIdEditText);

        String fromTimeString = formTimeField.getText().toString().trim();
        // TODO check if fromTimeString is empty string?
        String toTimeString = toTimeField.getText().toString().trim();
        String userId = userIdField.getText().toString().trim();
        String purpose = purposeField.getText().toString().trim();
        String roomIdString = roomIdField.getText().toString().trim();

        Integer fromTime;
        try {
            fromTime = Integer.parseInt(fromTimeString);
        } catch (TimeFormatException ex) {
            formTimeField.setError("Not a valid Time Format");
            return;
        }

        Integer toTime;
        try {
            toTime = Integer.parseInt(toTimeString);
        } catch (TimeFormatException ex) {
            toTimeField.setError("Not a valid Time Format");
            return;
        }

        Integer roomId;
        try {
            roomId = Integer.parseInt(roomIdString);
        } catch (TimeFormatException ex) {
            roomIdField.setError("Not a valid Number");
            return;
        }
/*
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://anbo-restserviceproviderbooks.azurewebsites.net/Service1.svc/")
                // https://futurestud.io/tutorials/retrofit-2-adding-customizing-the-gson-converter
                // Gson is no longer the default converter
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BookStoreService bookStoreService = retrofit.create(BookStoreService.class);

        ReservationRegistrationService roomRegistrationService = ApiUtils.getReservationRegistrationService();

        // Call<Book> saveBookCall = bookStoreService.saveBook(fromTimeString, toTime, userId, price);
        Reservation reservation = new Reservation(fromTime, toTime, userId, purpose, roomId);

        Call<Reservation> saveReservationCall = roomRegistrationService.saveReservationBody(reservation);
        saveReservationCall.enqueue(new Callback<Reservation>() {
            @Override
            public void onResponse(Call<Reservation> call, Response<Reservation> response) {
                if (response.isSuccessful()) {
                    Reservation theNewResevation = response.body();
                    Log.d("RESERVATION", theNewResevation.toString());
                    Toast.makeText(AddReservationActivity.this, "Reservation added, id: " + theNewResevation.getId(), Toast.LENGTH_SHORT).show();
//                    Snackbar.make(view, "Book added, id: " + theNewBook.getId(), Snackbar.LENGTH_LONG).show();
                } else {
                    String problem = "Problem: " + response.code() + " " + response.message();
                    Log.e("RESERVATION", problem);
                    Toast.makeText(AddReservationActivity.this, problem, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Reservation> call, Throwable t) {
                Log.e("RESERVATION", t.getMessage());
            }
        });
    }
}

        */