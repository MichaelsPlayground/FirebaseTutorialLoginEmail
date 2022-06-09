package de.androidcrypto.firebasetutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Button loginEmailPassword, signupEmailPassword, logoutUser, changeUserPassword;
    EditText loginStatus;
    Intent signupIntent, loginIntent, changeUserPasswordIntent;

    boolean isUserLogedIn;
    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // bindings
        loginEmailPassword = findViewById(R.id.btnLoginEmailPassword);
        signupEmailPassword = findViewById(R.id.btnSignupEmailPassword);
        logoutUser = findViewById(R.id.btnLogoutUser);
        changeUserPassword = findViewById(R.id.btnChangeUserPassword);
        loginStatus = findViewById(R.id.etLoginStatus);
        signupIntent = new Intent(MainActivity.this, SignUpEmailPasswordActivity.class);
        loginIntent = new Intent(MainActivity.this, LogInEmailPasswordActivity.class);
        changeUserPasswordIntent = new Intent(MainActivity.this, ChangeUserEmailPassword.class);

        // todo delete account
        // todo send password reset link
        // todo email signup with account verification

        // get the login status from FirebaseAuth
        currentUser = getCurrentUser();
        isUserLogedIn = getLoginStatus();

        if (isUserLogedIn) {
            loginStatus.setText("user " + getCurrentUserEmail() + " with ID: " + getCurrentUserId());
        }

        loginEmailPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(loginIntent);
            }
        });

        changeUserPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(changeUserPasswordIntent);
            }
        });

        logoutUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUtils.logoutUser();
                loginStatus.setText("no user loged in");
            }
        });

        signupEmailPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(signupIntent);
            }
        });
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