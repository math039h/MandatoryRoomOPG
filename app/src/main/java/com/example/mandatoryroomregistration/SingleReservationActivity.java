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

    public static final String ROOM = "BOOKRESERVATION";
    private static final String LOG_TAG = "MYROOMSRESERVATION";
    private Reservation originalReservation;
    private TextView messageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_room);
        messageView = findViewById(R.id.singleReservationMessageTextView);

        Intent intent = getIntent();
        originalReservation = (Reservation) intent.getSerializableExtra(ROOM);

        Log.d(LOG_TAG, originalReservation.toString());
        TextView headingView = findViewById(R.id.singleReservationHeadingTextview);
        headingView.setText("Reservation Id=" + originalReservation.getId());

        EditText fromTimeView = findViewById(R.id.singleReservationFromTimeEditText);
        fromTimeView.setText(Integer.toString(originalReservation.getFromTime()));

        EditText toTimeView = findViewById(R.id.singleReservationToTimeEditText);
        toTimeView.setText(Integer.toString(originalReservation.getToTime()));

        EditText priceView = findViewById(R.id.singleReservationUserIdEditText);
        priceView.setText(originalReservation.getUserId());

        EditText purposeView = findViewById(R.id.singleReservationPurposeEditText);
        purposeView.setText(originalReservation.getPurpose());

        EditText roomIdView = findViewById(R.id.singleReservationRoomIdEditText);
        roomIdView.setText(originalReservation.getRoomId());
    }

    public void backButtonClicked(View view) {
        Log.d(LOG_TAG, "backButtonClicked");
        finish();
    }
/*
    public void deleteReservationButtonClicked(View view) {
        ReservationRegistrationService reservationRegistrationService = ApiUtils.getReservationRegistrationService();
        int roomId = originalRoom.getId();
        Call<Reservation> deleteReservationCall = reservationRegistrationService.deleteReservationButtonClicked(roomId);
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