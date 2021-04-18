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
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;


public class Login extends Activity {
    private CallbackManager callbackManager ;
    Button connectBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        connectBtn = findViewById(R.id.connect_btn);

        LoginButton loginFacebookBtn = findViewById(R.id.login_button);
        callbackManager = CallbackManager.Factory.create();

        loginFacebookBtn.setReadPermissions(Arrays.asList("email","public_profile"));
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
                connectBtn.setText(getString(R.string.loggedOut));
            }

        });


        connectBtn.setOnClickListener(view -> {
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

    AccessTokenTracker tokenTracker =  new AccessTokenTracker(){
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            if(currentAccessToken == null){
                connectBtn.setText(getString(R.string.loggedOut));
            }
            else{
                GraphRequest request = GraphRequest.newMeRequest(currentAccessToken, (object, response) -> {
                    try{
                        String userName = object.getString("first_name");
                        String lastName = object.getString("last_name");
                        connectBtn.setText("Continue as "+ userName + " " + lastName);
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "first_name,last_name");
                request.setParameters(parameters);
                request.executeAsync();
            }
        }
    };

}
