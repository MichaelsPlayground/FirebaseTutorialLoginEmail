package de.androidcrypto.firebasetutorial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class SendUserEmailPasswordReset extends AppCompatActivity {

    private static final String TAG = "ResetUserPassword";
    Button send, cancel;
    EditText email, status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_user_email_password_reset);

        // bindings
        send = findViewById(R.id.btnResetSend);
        cancel = findViewById(R.id.btnResetCancel);
        email = findViewById(R.id.etResetEmail);
        status = findViewById(R.id.etResetStatus);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // sanity checks
                String userEmail = email.getText().toString();
                if (userEmail.equals("")) {
                    System.out.println("*** Error: Email is empty ***");
                    return;
                }
                FirebaseAuth mAuth;
                mAuth = FirebaseAuth.getInstance();
                mAuth.sendPasswordResetEmail(userEmail)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) Log.d(TAG, "Email sent.");
                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                                        view.getContext());
                                // set title
                                alertDialogBuilder.setTitle("Reset Password");
                                // set dialog message
                                alertDialogBuilder
                                        .setMessage("A Reset Password Link Is Sent To Your Registered EmailID")
                                        .setCancelable(false)
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                SendUserEmailPasswordReset.this.finish();
                                            }
                                        });
                                AlertDialog alertDialog = alertDialogBuilder.create();
                                alertDialog.show();
                            }
                        });
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SendUserEmailPasswordReset.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}