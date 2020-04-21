package com.example.mandatoryroomregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SingleRoomActivity extends AppCompatActivity {

    public static final String ROOM = "BOOK";
    private static final String LOG_TAG = "MYROOMS";
    private Rooms originalRoom;
    private TextView messageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_room);
        messageView = findViewById(R.id.singleRoomMessageTextView);

        Intent intent = getIntent();
        originalRoom = (Rooms) intent.getSerializableExtra(ROOM);

        Log.d(LOG_TAG, originalRoom.toString());
        TextView headingView = findViewById(R.id.singleRoomHeadingTextview);
        headingView.setText("Room Id=" + originalRoom.getId());

        EditText NameView = findViewById(R.id.singleRoomNameEditText);
        NameView.setText(originalRoom.getName());

        EditText discriptionView = findViewById(R.id.singleRoomDescriptionEditText);
        discriptionView.setText(originalRoom.getDescription());

        EditText capacityView = findViewById(R.id.singleRoomCapacityEditText);
        capacityView.setText(Double.toString(originalRoom.getCapacity()));

        EditText remarksView = findViewById(R.id.singleRoomRemarksEditText);
        remarksView.setText(originalRoom.getRemarks());
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
