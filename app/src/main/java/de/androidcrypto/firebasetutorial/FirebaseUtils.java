package de.androidcrypto.firebasetutorial;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseUtils {

    private boolean isUserLogedIn;
    private FirebaseUser currentUser;

    public String getCurrentUserId(FirebaseUser user) {
        return user.getUid();
    }

    public String getCurrentUserEmail(FirebaseUser user) {
        String defaultEmail = "not set yet";
        if (user.getEmail() != null) {
            return user.getEmail();
        } else {
            return defaultEmail;
        }
    }

    public FirebaseUser getCurrentUser() {
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        return currentUser;
    }

    public boolean getLoginStatus() {
        if (currentUser != null) {
            return true;
        } else {
            return false;
        }
    }


}
