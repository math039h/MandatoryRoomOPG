package com.example.mandatoryroomregistration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivityKotlin : AppCompatActivity() {
    private var mAuth: FirebaseAuth? = null
    private val tAg = "Authentication"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAuth = FirebaseAuth.getInstance()
    }

    public override fun onStart() {
        super.onStart()
        val currentUser = mAuth!!.currentUser
        updateUI(currentUser)
    }

    private fun defaultLogin() {
        val emailField = findViewById<EditText>(R.id.mainEmailEditText)
        val passwordField = findViewById<EditText>(R.id.mainPasswordEditText)
        emailField.setText("test@test.dk")
        passwordField.setText("testtest")
    }

    fun doLogin(view: View) {
        val emailField = findViewById<EditText>(R.id.mainEmailEditText)
        val passwordField = findViewById<EditText>(R.id.mainPasswordEditText)
        val email = emailField.text.toString()
        val password = passwordField.text.toString()
        if (email.isEmpty() && password.isEmpty()) {
            defaultLogin()
        } else if (email.length > 5 && password.length >= 8) {
            mAuth!!.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val user = mAuth!!.currentUser
                            Log.d(tAg, user!!.email!!)
                            updateUI(user)
                            logInSuccess()
                        } else {
                            Log.w(tAg, "signInWithEmail:failure", task.exception)
                            Toast.makeText(this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show()
                            updateUI(null)
                        }
                    }
        } else {
            Toast.makeText(this, "Please enter a valid email and password", Toast.LENGTH_SHORT).show()
        }
    }

    fun logInSuccess() {
        val emailField = findViewById<EditText>(R.id.mainEmailEditText)
        val passwordField = findViewById<EditText>(R.id.mainPasswordEditText)
        emailField.setText("")
        passwordField.setText("")
        val intent = Intent(this, loggedIn::class.java)
        startActivity(intent)
    }

    private fun updateUI(user: FirebaseUser?) {
        val status = findViewById<TextView>(R.id.mainMessageTextView)
        val firebaseUser = mAuth!!.currentUser
        if (firebaseUser != null) {
            status.text = "Welcome Kotlin " + user!!.email!!
        }
    }
}
