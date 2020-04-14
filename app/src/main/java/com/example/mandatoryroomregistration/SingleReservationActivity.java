package com.example.mandatoryroomregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SingleReservationActivity extends AppCompatActivity {

    //public static final String ROOM = "ROOM";
    public static final String RESERVATION = "RESERVATION";
    private static final String LOG_TAG = "MYROOMSRESERVATION";
    //private Rooms originalRoom;
    private Reservation originalReservation;
    private TextView messageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_room);
        messageView = findViewById(R.id.singleReservationMessageTextView);

        Intent intent = getIntent();
        originalReservation = (Reservation) intent.getSerializableExtra(RESERVATION);
        Log.d(LOG_TAG, "originalReservation " + originalReservation);
        //Intent intentRoom = getIntent();
        //originalRoom = (Rooms) intentRoom.getSerializableExtra(ROOM);

        Log.d(LOG_TAG, originalReservation.toString());
        //TextView headingView = findViewById(R.id.singleReservationHeadingTextview);
        Log.d(LOG_TAG, "efter textview headingview");
        //headingView.setText("Reservation Id=" + originalReservation.getId());
        Log.d(LOG_TAG, "efter headingview set text");
        EditText fromTimeView = findViewById(R.id.singleReservationFromTimeEditText);
        fromTimeView.setText(Integer.toString(originalReservation.getFromTime()));
        Log.d(LOG_TAG, "44");
        EditText toTimeView = findViewById(R.id.singleReservationToTimeEditText);
        toTimeView.setText(Integer.toString(originalReservation.getToTime()));
        Log.d(LOG_TAG, "After time view");
        EditText priceView = findViewById(R.id.singleReservationUserIdEditText);
        priceView.setText(originalReservation.getUserId());
        Log.d(LOG_TAG, "50 linje");
        EditText purposeView = findViewById(R.id.singleReservationPurposeEditText);
        purposeView.setText(originalReservation.getPurpose());
        Log.d(LOG_TAG, "53");
        EditText roomIdView = findViewById(R.id.singleReservationRoomIdEditText);
        roomIdView.setText(Integer.toString(originalReservation.getRoomId()));
    }

    public void backButtonClicked(View view) {
        Log.d(LOG_TAG, "backButtonClicked");
        finish();
    }
/*
    public void deleteReservationButtonClicked(View view) {
        ReservationRegistrationService reservationRegistrationService = ApiUtils.getReservationRegistrationService();
        int reservationId = originalReservation.getId();
        Call<Reservation> deleteReservationCall = reservationRegistrationService.deleteReservation(reservationId);
        messageView.setText("");

        deleteBookCall.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                if (response.isSuccessful()) {
                    //Snackbar.make(view, "Book deleted, id: " + originalBook.getId(), Snackbar.LENGTH_LONG).show();
                    String message = "Book deleted, id: " + originalBook.getId();
                    Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();
                    Log.d(LOG_TAG, message);
                } else {
                    //Snackbar.make(view, "Problem: " + response.code() + " " + response.message(), Snackbar.LENGTH_LONG).show();
                    String problem = call.request().url() + "\n" + response.code() + " " + response.message();
                    messageView.setText(problem);
                    //Toast.makeText(getBaseContext(), problem, Toast.LENGTH_SHORT).show();
                    Log.e(LOG_TAG, problem);
                }
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                //Snackbar.make(view, "Problem: " + t.getMessage(), Snackbar.LENGTH_LONG).show();
                Log.e(LOG_TAG, "Problem: " + t.getMessage());
            }
        });
    }
/*
    public void updateButtonClicked(View view) {
        Log.d(LOG_TAG, "anotherButtonClicked");
        Toast.makeText(this, "anotherButtonClicked", Toast.LENGTH_SHORT).show();

        EditText authorField = findViewById(R.id.singleRoomDescriptionEditText);
        EditText titleField = findViewById(R.id.singleRoomNameEditText);
        EditText publisherField = findViewById(R.id.singleRoomRemarksEditText);
        EditText priceField = findViewById(R.id.singleRoomCapacityEditText);
        // REST bug: price cannot be updated!

        String author = authorField.getText().toString().trim();
        // TODO check if author is empty string?
        String title = titleField.getText().toString().trim();
        String publisher = publisherField.getText().toString().trim();
        String priceString = priceField.getText().toString().trim();

        double price;
        try {
            price = Double.parseDouble(priceString);
        } catch (NumberFormatException ex) {
            priceField.setError("Not a valid price");
            return;
        }
        Book bookToUpdate = new Book(author, title, publisher, price);
        Log.d(LOG_TAG, "Update " + bookToUpdate);

        BookStoreService bookStoreService = ApiUtils.getBookStoreService();
        Call<Book> callUpdate = bookStoreService.updateBook(originalBook.getId(), bookToUpdate);
        callUpdate.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                if (response.isSuccessful()) {
                    Log.d(LOG_TAG, response.body().toString());
                    messageView.setText("Updated " + response.body().toString());
                } else {
                    messageView.setText("Problem: " + response.code() + " " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                messageView.setText("Problem: " + t.getMessage());
            }
        });
    }*/
}