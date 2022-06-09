package de.androidcrypto.firebasetutorial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseUser;

public class LogInEmailPasswordActivity extends AppCompatActivity {

    Button signUp, cancel, logIn;
    EditText email, password, status;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_email_password);

        // bindings
        signUp = findViewById(R.id.btnLoginSignUp);
        cancel = findViewById(R.id.btnLoginCancel);
        logIn = findViewById(R.id.btnLoginLogin);
        email = findViewById(R.id.etLoginEmail);
        password = findViewById(R.id.etLoginPassword);
        status = findViewById(R.id.etLoginStatus);

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // sanity checks
                String userEmail = email.getText().toString();
                String userPassword = password.getText().toString();
                if (userEmail.equals("")) {
                    System.out.println("*** Error: Email is empty ***");
                    return;
                }
                if (userPassword.equals("")) {
                    System.out.println("*** Error: Password is empty ***");
                    return;
                }
                // start the auth process
                FirebaseUtils.loginUserWithEmailPassword(LogInEmailPasswordActivity.this, userEmail, userPassword);
                boolean successfulAuthentication = FirebaseUtils.authenticationWasSuccessful();
                if (successfulAuthentication) {
                    firebaseUser = FirebaseUtils.getCurrentUser();
                    if (firebaseUser != null) {
                        String message = "user loged in with email: " + FirebaseUtils.getCurrentUserEmail(firebaseUser);
                        status.setText(message);
                    } else {
                        String message ="no email address available";
                        status.setText(message);
                    }
                } else {
                    String message ="error while log in user";
                    status.setText(message);
                }

            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogInEmailPasswordActivity.this, LogInEmailPasswordActivity.class);
                startActivity(intent);
                finish();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogInEmailPasswordActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}