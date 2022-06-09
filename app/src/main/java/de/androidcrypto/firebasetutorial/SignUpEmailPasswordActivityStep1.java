package de.androidcrypto.firebasetutorial;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpEmailPasswordActivityStep1 extends AppCompatActivity {

    Button signUp, cancel, logIn;
    EditText email, password, status;

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

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}