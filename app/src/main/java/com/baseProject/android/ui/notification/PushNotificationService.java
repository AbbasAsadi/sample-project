package com.baseProject.android.ui.notification;

import com.baseProject.android.R;
//import com.google.firebase.messaging.FirebaseMessagingService;
//import com.google.firebase.messaging.RemoteMessage;

public class PushNotificationService /*extends FirebaseMessagingService*/ {
    public static final String TAG = "FireBaseService";
    public static final int NOTIFICATION_ID = 123;
    private final int icon = R.mipmap.ic_launcher;

    public PushNotificationService() {
    }

    /**
     * There are two scenarios when onNewToken is called:
     * 1) When a new token is generated on initial app startup
     * 2) Whenever an existing token is changed
     * Under #2, there are three scenarios when the existing token is changed:
     * A) App is restored to a new device
     * B) User uninstalls/reInstalls the app
     * C) User clears app data
     */
  /*  @Override
    public void onNewToken(String token) {
        Log.d("TAG4", "Refreshed token: " + token);
//        Toast.makeText(this, token, Toast.LENGTH_SHORT).show();
//        sendRegistrationToServer(token);
    }


    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if (remoteMessage == null) {
            return;
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            handleNotification(remoteMessage);
        }
    }

    private void handleNotification(RemoteMessage message) {
        if (ApplicationUtils.isAppIsInBackground(getApplicationContext())) {
            // app is in background, broadcast the push message
            super.onMessageReceived(message);
        } else {
            // app is in foreground, broadcast the push message
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            PendingIntent pendingIntent = PendingIntent.getActivity(this, NOTIFICATION_ID, intent,
                    PendingIntent.FLAG_ONE_SHOT);

            Uri notificationUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Notification notification = new NotificationCompat.Builder(this, PushNotificationConfig.PUSH_NOTIFICATION_CHANNEL_ID)
                    .setContentTitle(message.getNotification().getTitle())
                    .setContentText(message.getNotification().getBody())
                    .setSmallIcon(icon)
                    .setSound(notificationUri, AudioManager.STREAM_NOTIFICATION)
                    .setContentIntent(pendingIntent)
                    .build();
            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(PushNotificationConfig.PUSH_NOTIFICATION_CHANNEL_ID, "Default channel", NotificationManager.IMPORTANCE_DEFAULT);
                manager.createNotificationChannel(channel);
            }

//            NotificationManagerCompat manager = NotificationManagerCompat.from(getApplicationContext());
            manager.notify(NOTIFICATION_ID, notification);
            makeVibrate();

            ContextCompat.getMainExecutor(this).execute(() -> {
                Toast.makeText(this, "اعلان جدیدی دارید", Toast.LENGTH_LONG).show();
            });
        }
    }

    private void makeVibrate() {
        Vibrator vibrator;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

            // Vibrate for 750 milliseconds
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                vibrator.vibrate(1000);
            }
        }
    }*/
}
