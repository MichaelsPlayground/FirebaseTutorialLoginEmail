package de.androidcrypto.firebasetutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class SignUpEmailPasswordActivity extends AppCompatActivity {

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


    }
}