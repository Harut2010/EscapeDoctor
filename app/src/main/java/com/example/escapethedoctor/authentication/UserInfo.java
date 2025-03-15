package com.example.escapethedoctor.authentication;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


public class UserInfo {

    private static String username = null;

    public static void getUserName(final UserNameCallback callback) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            String userId = user.getUid();
            DocumentReference userRef = db.collection("users").document(userId);

            userRef.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    // Extract the username from the Firestorm document
                    String username = task.getResult().getString("username");
                    if (username != null) {
                        callback.onUserNameReceived(username);
                    } else {
                        callback.onError("Username not found.");
                    }
                } else {
                    callback.onError("Failed to retrieve username.");
                }
            });
        } else {
            callback.onError("User is not logged in.");
        }
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        UserInfo.username = username;
    }

    public interface UserNameCallback {
        void onUserNameReceived(String username);
        void onError(String error);
    }
}

