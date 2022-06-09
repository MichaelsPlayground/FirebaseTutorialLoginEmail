package de.androidcrypto.firebasetutorial;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Button loginEmailPassword, signupEmailPassword, logoutUser, changeUserPassword,
            deleteUserPassword, sendUserPasswordReset;
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
        deleteUserPassword = findViewById(R.id.btnDeleteUserPassword);
        sendUserPasswordReset = findViewById(R.id.btnSendUserPasswordReset);
        loginStatus = findViewById(R.id.etLoginStatus);
        signupIntent = new Intent(MainActivity.this, SignUpEmailPasswordActivity.class);
        loginIntent = new Intent(MainActivity.this, LogInEmailPasswordActivity.class);
        changeUserPasswordIntent = new Intent(MainActivity.this, ChangeUserEmailPassword.class);

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

        sendUserPasswordReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SendUserEmailPasswordReset.class);
                startActivity(intent);
            }
        });

        deleteUserPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                alert.setTitle("Delete the loged in user ?");
                alert.setMessage("Are you sure you want to delete ?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // todo surround the deletion with try/catch for
                        // FirebaseAuthInvalidUserException and
                        // FirebaseAuthRecentLoginRequiredException
/*
FirebaseAuthInvalidUserException thrown if the current user's account has been disabled, deleted,
 or its credentials are no longer valid
FirebaseAuthRecentLoginRequiredException thrown if the user's last sign-in time does not meet the
 security threshold. Use reauthenticate(AuthCredential) to resolve. This does not apply if the
 user is anonymous.
*/
                        FirebaseAuth.getInstance().getCurrentUser().delete();

                        dialog.dismiss();
                    }
                });

                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                alert.show();
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