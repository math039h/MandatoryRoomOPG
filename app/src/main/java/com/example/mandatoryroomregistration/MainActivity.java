package com.example.mandatoryroomregistration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private static final String TAG = "Authentication";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    public void createAccount(View view) {

        EditText EmailField = findViewById(R.id.mainEmailEditText);
        EditText PasswordField = findViewById(R.id.mainPasswordEditText);
        String email = EmailField.getText().toString();
        String password = PasswordField.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Log.d(TAG, user.getEmail());
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });

        // ...
    }

    public void defaultLogin(View view) {
        EditText EmailField = findViewById(R.id.mainEmailEditText);
        EditText PasswordField = findViewById(R.id.mainPasswordEditText);
        EmailField.setText("test@test.dk");
        PasswordField.setText("testtest");
    }

    public void doLogin(View view) {

        EditText EmailField = findViewById(R.id.mainEmailEditText);
        EditText PasswordField = findViewById(R.id.mainPasswordEditText);
        TextView ErrorMesseageField = findViewById(R.id.mainMessageTextView);
        //defaultLogin(view);
        String email = EmailField.getText().toString();
        String password = PasswordField.getText().toString();
        String problem = "Problem: ";
        String errorMesseage = ErrorMesseageField.getText().toString();
        if (email.length() > 5 && password.length() >= 8) {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                Log.d(TAG, user.getEmail());
                                updateUI(user);
                                LogInSuccess(view);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(MainActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                //ErrorMesseageField.setText("Authentication failed");
                                updateUI(null);
                            }

                            // ...
                        }
                    });
        } else {
            Toast.makeText(MainActivity.this, "Please enter a valid email and password", Toast.LENGTH_SHORT).show();
        }
    }

    public void LogInSuccess(View view) {
        EditText EmailField = findViewById(R.id.mainEmailEditText);
        EditText PasswordField = findViewById(R.id.mainPasswordEditText);
        EmailField.setText("");
        PasswordField.setText("");
        Intent intent = new Intent(this, loggedIn.class);
        startActivity(intent);
    }

    /*public void SendToLoggedInIfVerified(View view) {
        FirebaseUser user = mAuth.getInstance().getCurrentUser();
        //FirebaseUser user = null;
        if (user != null) {
            Log.d(TAG, "Test");
            Intent intent = new Intent(this, loggedIn.class);
            Toast.makeText(this, "Sending To Logged In", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }
    }*/

    /*private void getCurrentUserData(View view) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();

            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            String uid = user.getUid();
        }
    }*/

    private void updateUI(FirebaseUser user) {
        TextView status = findViewById(R.id.mainMessageTextView);
        status.setText("Welcome " );
    }

    /*public void onDestroy(FirebaseUser currentUser) {
        currentUser = null;
        super.onDestroy();
    }*/
}


