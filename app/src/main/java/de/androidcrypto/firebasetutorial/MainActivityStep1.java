package de.androidcrypto.firebasetutorial;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivityStep1 extends AppCompatActivity {

    Button loginEmailPassword, signupEmailPassword;
    EditText loginStatus;
    boolean isUserLogedIn;
    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginEmailPassword = findViewById(R.id.btnLoginEmailPassword);
        signupEmailPassword = findViewById(R.id.btnSignupEmailPassword);
        loginStatus = findViewById(R.id.etLoginStatus);

        // get the login status from FirebaseAuth
        currentUser = getCurrentUser();
        isUserLogedIn = getLoginStatus();

        if (isUserLogedIn) {
            loginStatus.setText("user " + getCurrentUserEmail() + " with ID: " + getCurrentUserId());
        }
    }

    private String getCurrentUserId() {
        return currentUser.getUid();
    }

    private String getCurrentUserEmail() {
        String defaultEmail = "not set yet";
        if (currentUser.getEmail() != null) {
            return currentUser.getEmail();
        } else {
            return defaultEmail;
        }

    }

    private FirebaseUser getCurrentUser() {
        return FirebaseAuth.getInstance().getCurrentUser();
    }

    private boolean getLoginStatus() {
        if (currentUser != null) {
            return true;
        } else {
            return false;
        }
    }
}