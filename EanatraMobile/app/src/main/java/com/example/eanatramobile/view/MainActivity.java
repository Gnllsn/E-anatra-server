package com.example.eanatramobile.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eanatramobile.R;
import com.example.eanatramobile.apiInterface.AuthApi;
import com.example.eanatramobile.apiInterface.PostsApi;
import com.example.eanatramobile.apiInterface.Server;
import com.example.eanatramobile.fragement.LoginFragment;
import com.example.eanatramobile.fragement.SignUpFragment;
import com.example.eanatramobile.modele.Post;
import com.example.eanatramobile.modele.Test;
import com.example.eanatramobile.modele.Users;
import com.example.eanatramobile.view.SignUpActivity ;

import java.util.HashMap;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    SignUpFragment signUpFragment = new SignUpFragment() ;
    LoginFragment loginFragment = new LoginFragment();
    Boolean loading = false ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = Server.getServert();
        AuthApi authApi = retrofit.create(AuthApi.class);

        onClickLogin();
        onClickSignUp();

//        getSupportFragmentManager().beginTransaction().add(R.id.container,loginFragment).commit();
    }

    private void onClickLogin(){
        Button btnlogin = findViewById(R.id.btnlogin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!loading){
                    loading = true;
                    loadingLogIn(true);
                    LogIn();
                }
            }
        });
    }

    private Users newUser(){
        Users user = new Users();
        user.setEmail(((EditText)findViewById(R.id.email)).getText().toString());
        user.setPwd(((EditText)findViewById(R.id.password)).getText().toString());
        return user  ;
    }

    private void LogIn(){
        Users user = newUser();
        Retrofit retrofit = Server.getServert();
        AuthApi authApi = retrofit.create(AuthApi.class);
        callApiLogIn(authApi,user);

    }

    private void callApiLogIn(AuthApi authApi,Users user){
        Call<HashMap<String,Object>> call = authApi.login(user);
        call.enqueue(new Callback<HashMap<String, Object>>() {
            @Override
            public void onResponse(Call<HashMap<String, Object>> call, Response<HashMap<String, Object>> response) {
                if(response.isSuccessful()){
                    HashMap<String,Object> result = response.body();
                    if(new Double(result.get("status")+"").intValue()==200){
                        storeData(result.get("data"));
                        redirectHome();
                    }else {
                        loadingLogIn(false);
                        TextView error_message = findViewById(R.id.errormessageLogin);
                        error_message.setText(result.get("message").toString());
                        error_message.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Call<HashMap<String, Object>> call, Throwable t) {
                Log.d("ERROR MESSAGE"," \n\n\n "+t.getMessage()+" \n\n\n ");
            }
        });
    }
    private void storeData(Object data){
        SharedPreferences memory = getSharedPreferences("data_user", Context.MODE_PRIVATE);
        SharedPreferences.Editor writer = memory.edit();
        writer.putString("user",data.toString());
        writer.commit();
    }

    private void redirectHome(){
        startActivity(new Intent(MainActivity.this, HomeActivity.class));
    }

    private void loadingLogIn(boolean loaderna){
        ProgressBar progressBar = findViewById(R.id.loadingLogIn) ;
        LinearLayout linearLayout = findViewById(R.id.linearbouttonLogIn);
        if(loaderna){
            progressBar.setVisibility(View.VISIBLE);
            linearLayout.setVisibility(View.GONE);
        }else {
            progressBar.setVisibility(View.GONE);
            linearLayout.setVisibility(View.VISIBLE);
        }
    }

    private void onClickSignUp(){
        Button btnsignup = findViewById(R.id.btnsignin);
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSignup();

            }
        });
    }

    public void openSignup(){
        startActivity(new Intent(this,SignUpActivity.class));
    }
}