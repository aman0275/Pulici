package com.example.pulici;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.RemoteMessage;

import org.jetbrains.annotations.NotNull;

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {


    public static final String Tag = FirebaseMessagingService.class.getSimpleName();

    @Override
    public void onNewToken(@NonNull @NotNull String token) {
        super.onNewToken(token);

        Log.d(Tag, "Token" + token);

    }


    @Override
    public void onMessageReceived(@NonNull @NotNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
    }




}




