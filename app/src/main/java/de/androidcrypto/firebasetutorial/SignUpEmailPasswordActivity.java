package de.androidcrypto.firebasetutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseUser;

public class SignUpEmailPasswordActivity extends AppCompatActivity {

    Button signUp, cancel, logIn;
    EditText email, password, status;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_email_password);

        // bindings
        signUp = findViewById(R.id.btnSignupSignUp);
        cancel = findViewById(R.id.btnSignupCancel);
        logIn = findViewById(R.id.btnSignupLogin);
        email = findViewById(R.id.etSignupEmail);
        password = findViewById(R.id.etSignupPassword);
        status = findViewById(R.id.etSignupStatus);

        signUp.setOnClickListener(new View.OnClickListener() {
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
                FirebaseUtils.createUserWithEmailPassword(SignUpEmailPasswordActivity.this, userEmail, userPassword);
                boolean successfulAuthentication = FirebaseUtils.authenticationWasSuccessful();
                if (successfulAuthentication) {
                    firebaseUser = FirebaseUtils.getCurrentUser();
                    if (firebaseUser != null) {
                        String message = "new user loged in with email: " + FirebaseUtils.getCurrentUserEmail(firebaseUser);
                        status.setText(message);
                    } else {
                        String message ="no email address available";
                        status.setText(message);
                    }
                } else {
                    String message ="error while sign in new user";
                    status.setText(message);
                }

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpEmailPasswordActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpEmailPasswordActivity.this, LogInEmailPasswordActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}