package com.example.moviestreamingapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.moviestreamingapp.model.MovieModel;
import com.example.moviestreamingapp.model.operations.GetMoviesOp;
import com.example.moviestreamingapp.model.operations.UpdateMoviesOp;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;
import java.util.Collections;


public class Login extends Activity {
    private CallbackManager callbackManager ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Button loginBtn = findViewById(R.id.login_btn);

        LoginButton loginFacebookBtn = findViewById(R.id.login_button);
        loginFacebookBtn.setReadPermissions(Collections.singletonList("email"));

        callbackManager = CallbackManager.Factory.create();
        loginFacebookBtn.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                startActivity(new Intent(Login.this, MainActivity.class));
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }

        });


        loginBtn.setOnClickListener(view -> {
            AccessToken accessToken = AccessToken.getCurrentAccessToken();
            boolean isLoggedIn = accessToken != null && !accessToken.isExpired();

            if (!isLoggedIn) return;
            startActivity(new Intent(view.getContext(), MainActivity.class));
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }



}
