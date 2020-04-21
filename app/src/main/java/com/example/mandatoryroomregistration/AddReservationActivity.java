package com.example.mandatoryroomregistration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.TimeFormatException;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddReservationActivity extends AppCompatActivity {
    private static final String LOG_TAG = "ADDRESERVATION123";
    private Reservation originalReservation;
    private TextView messageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG, "Test AddReservationAtivity");
        setContentView(R.layout.activity_add_reservation);
        Log.d(LOG_TAG, "Test AddR 2");
        messageView = findViewById(R.id.AddReservationMessageTextView);
        getAllReservations();

        SwipeRefreshLayout refreshLayout = findViewById(R.id.AddReservationSwipeRefresh);
        refreshLayout.setOnRefreshListener(() -> {
            refreshLayout.setRefreshing(true);
            getAllReservations();
            refreshLayout.setRefreshing(false);
        });
    }

    public void addReservationButtonClicked(View view) {
        EditText formTimeField = findViewById(R.id.addReservationFromTimeEditText);
        EditText toTimeField = findViewById(R.id.addReservationToTimeEditText);
        EditText userIdField = findViewById(R.id.addReservationUserIdEditText);
        EditText purposeField = findViewById(R.id.addReservationPurposeEditText);
        EditText roomIdField = findViewById(R.id.addReservationRoomIdEditText);

        String fromTimeString = formTimeField.getText().toString().trim();
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

        ReservationRegistrationService roomRegistrationService = ApiUtils.getReservationRegistrationService();

        //Call<Reservation> saveReservationCall = ReservationRegistrationService.saveReservation(fromTime, toTime, userId, price);
        Reservation reservation = new Reservation(fromTime, toTime, userId, purpose, roomId);

        Call<Integer> saveReservationCall = roomRegistrationService.saveReservationBody(reservation);
        saveReservationCall.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (roomId > 5 || roomId < 1) {
                    Toast.makeText(AddReservationActivity.this, "This room id does not exist", Toast.LENGTH_SHORT).show();
                }
                else if (response.isSuccessful()) {

                    Integer theNewResevation = response.body();
                    Log.d("RESERVATION", theNewResevation.toString());
                    Toast.makeText(AddReservationActivity.this, "Reservation added, id: " + theNewResevation, Toast.LENGTH_SHORT).show();

                } else {
                    String problem = "Problem: " + response.code() + " " + response.message();
                    Log.e("RESERVATION", problem);
                    if (fromTime > toTime) {
                        Toast.makeText(AddReservationActivity.this, "From time can't be higher than to time", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AddReservationActivity.this, problem, Toast.LENGTH_SHORT).show();
                    }
                }
            }


            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.e("RESERVATION", t.getMessage());
            }
        });
    }



    public void deleteReservationButtonClicked(View view) {
        ReservationRegistrationService reservationRegistrationService = ApiUtils.getReservationRegistrationService();
        int reservationId = originalReservation.getId();
        Call<Reservation> deleteReservationCall = reservationRegistrationService.deleteReservation(reservationId);
        messageView.setText("");

        deleteReservationCall.enqueue(new Callback<Reservation>() {
            @Override
            public void onResponse(Call<Reservation> call, Response<Reservation> response) {
                if (response.isSuccessful()) {
                    String message = "Book deleted, id: " + originalReservation.getId();
                    Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();
                    Log.d(LOG_TAG, message);
                } else {
                    String problem = call.request().url() + "\n" + response.code() + " " + response.message();
                    messageView.setText(problem);
                    Toast.makeText(getBaseContext(), problem, Toast.LENGTH_SHORT).show();
                    Log.e(LOG_TAG, problem);
                }
            }

            @Override
            public void onFailure(Call<Reservation> call, Throwable t) {
                Toast.makeText(AddReservationActivity.this, "Problem: " + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e(LOG_TAG, "Problem: " + t.getMessage());
            }
        });

    }

    public void getAllReservations() {
        ReservationRegistrationService reservationRegistrationService = ApiUtils.getReservationRegistrationService();
        Call<List<Reservation>> getAllReservationsCall = reservationRegistrationService.ShowAllReservations();
        TextView messageView = findViewById(R.id.AddReservationMessageTextView);

        messageView.setText("");
        getAllReservationsCall.enqueue(new Callback<List<Reservation>>() {
            @Override
            public void onResponse(Call<List<Reservation>> call, Response<List<Reservation>> response) {
                if (response.isSuccessful()) {
                    List<Reservation> allReservations = response.body();
                    Log.d(LOG_TAG, allReservations.toString());
                    populateRecyclerView(allReservations);
                } else {
                    String message = "Problem " + response.code() + " " + response.message();
                    Log.d(LOG_TAG, message);
                    messageView.setText(message);
                }
            }

            @Override
            public void onFailure(Call<List<Reservation>> call, Throwable t) {
                Log.e(LOG_TAG, t.getMessage());
                messageView.setText(t.getMessage());
            }
        });
    }

    private void populateRecyclerView(List<Reservation> allReservations) {
        RecyclerView recyclerView = findViewById(R.id.AddReservationRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewSimpleAdapter adapter = new RecyclerViewSimpleAdapter<>(allReservations);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((view, position, item) -> {
            Reservation reservation = (Reservation) item;
            Log.d(LOG_TAG, item.toString());
            Intent intent = new Intent(this, SingleReservationActivity.class);
            intent.putExtra(SingleReservationActivity.RESERVATION, reservation);
            Log.d(LOG_TAG, "putExtra " + reservation.toString());
            startActivity(intent);
        });
    }
}

