package de.androidcrypto.firebasetutorial;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseUtils {

    private static String TAG = "FirebaseUtils";
    private static boolean isUserLogedIn;
    private static boolean authenticationSuccessful;
    private static FirebaseUser currentUser;
    private static FirebaseAuth mAuth;

    public String getCurrentUserId(FirebaseUser user) {
        return user.getUid();
    }

    public static String getCurrentUserEmail(FirebaseUser user) {
        String defaultEmail = "not set yet";
        if (user.getEmail() != null) {
            return user.getEmail();
        } else {
            return defaultEmail;
        }
    }

    public static FirebaseUser getCurrentUser() {
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        return currentUser;
    }

    public static boolean getLoginStatus() {
        if (currentUser != null) {
            return true;
        } else {
            return false;
        }
    }

    public static void loginUserWithEmailPassword(Activity activity, String email, String password) {
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "logInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            currentUser = user;
                            authenticationSuccessful = true;
                            Intent intent = new Intent(activity,MainActivity.class);
                            activity.startActivity(intent);
                            activity.finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "logInWithEmail:failure", task.getException());
                            currentUser = null;
                            authenticationSuccessful = false;
                        }
                    }
                });

    }

    public static void changeUserPasswordWithEmailPassword(Activity activity, String passwordOld, String passwordNew) {
        FirebaseUser user;
        user = FirebaseAuth.getInstance().getCurrentUser();
        final String email = user.getEmail();
        System.out.println("*** email: " + email);
        AuthCredential credential = null;
        if (email != null) {
            credential = EmailAuthProvider.getCredential(email, passwordOld);
            FirebaseUser finalUser = user;
            user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        finalUser.updatePassword(passwordNew).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (!task.isSuccessful()) {
                                    Log.d(TAG, "changePasswordWithEmail:success");
                                    Intent intent = new Intent(activity, MainActivity.class);
                                    activity.startActivity(intent);
                                    activity.finish();
                                } else {
                                    Log.d(TAG, "changePasswordWithEmail:failure");
                                    //passwordChangeSuccessful = false;
                                }
                            }
                        });
                    } else {
                        Log.d(TAG, "changePasswordWithEmail:authentication failure");
                    }
                }
            });
        }
    }
    public static void createUserWithEmailPassword(Activity activity, String email, String password) {
        authenticationSuccessful = false;
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            currentUser = user;
                            authenticationSuccessful = true;
                            Intent intent = new Intent(activity,MainActivity.class);
                            activity.startActivity(intent);
                            activity.finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            currentUser = null;
                            authenticationSuccessful = false;
                        }
                    }
                });
    }

    public static boolean authenticationWasSuccessful() {
        return authenticationSuccessful;
    }

    public static void logoutUser() {
        FirebaseAuth.getInstance().signOut();
        currentUser = null;
        authenticationSuccessful = false;
        isUserLogedIn = false;
    }
}
