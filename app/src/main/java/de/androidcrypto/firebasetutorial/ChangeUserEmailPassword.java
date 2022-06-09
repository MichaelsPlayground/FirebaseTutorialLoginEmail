package de.androidcrypto.firebasetutorial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseUser;

public class ChangeUserEmailPassword extends AppCompatActivity {

    Button change, cancel;
    EditText email, passwordOld, passwordNew, status;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_user_email_password);

        // bindings
        change = findViewById(R.id.btnChangePasswordChange);
        cancel = findViewById(R.id.btnChangePasswordCancel);
        email = findViewById(R.id.etChangeEmail);
        passwordOld = findViewById(R.id.etChangePasswordOld);
        passwordNew = findViewById(R.id.etChangePasswordNew);
        status = findViewById(R.id.etChangeStatus);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser user = null;
                user = FirebaseUtils.getCurrentUser();
                String userEmailFromUser = FirebaseUtils.getCurrentUserEmail(user);
                email.setText(userEmailFromUser);
                // sanity checks
                String userEmail = email.getText().toString();
                String userPasswordOld = passwordOld.getText().toString();
                String userPasswordNew = passwordNew.getText().toString();
                if (userEmail.equals("")) {
                    System.out.println("*** Error: Email is empty ***");
                    return;
                }
                if (userPasswordOld.equals("")) {
                    System.out.println("*** Error: old Password is empty ***");
                    return;
                }
                if (userPasswordNew.equals("")) {
                    System.out.println("*** Error: new Password is empty ***");
                    return;
                }

                // todo FirebaseUtils routine funktioniert nicht !!, die unten genannte schon ??

                // todo change of email address disabling

                FirebaseUtils.changeUserPasswordWithEmailPassword(ChangeUserEmailPassword.this, userPasswordOld, userPasswordNew);
/*
                // start the auth process
                final String email = user.getEmail();
                AuthCredential credential = EmailAuthProvider.getCredential(email,userPasswordOld);
                FirebaseUser finalUser = user;
                user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            finalUser.updatePassword(userPasswordNew).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(!task.isSuccessful()){
                                        status.setText("Error while changing the password");
                                    }else {
                                        status.setText("Password change was successful");
                                    }
                                }
                            });
                        }else {
                            status.setText("Authentication Failed");
                        }
                    }
                });*/
            }

        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChangeUserEmailPassword.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}