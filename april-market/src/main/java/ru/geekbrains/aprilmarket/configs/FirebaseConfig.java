package ru.geekbrains.aprilmarket.configs;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

//@Configuration
public class FirebaseConfig {
    @Value("${firebase.refresh-token-path}")
    private String refreshTokenPath;

//    @Bean
    public FirebaseMessaging firebaseMessaging() throws IOException {
        FileInputStream refreshToken = new FileInputStream(refreshTokenPath);

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(refreshToken))
                .setDatabaseUrl("https://your-project-name.firebaseio.com")
                .build();

        FirebaseApp app = FirebaseApp.initializeApp(options);
        return FirebaseMessaging.getInstance(app);
    }
}
