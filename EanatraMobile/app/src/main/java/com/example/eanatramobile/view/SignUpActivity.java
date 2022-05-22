package com.example.eanatramobile.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.eanatramobile.R;
import com.example.eanatramobile.apiInterface.AuthApi;
import com.example.eanatramobile.apiInterface.Server;
import com.example.eanatramobile.modele.Users;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignUpActivity extends AppCompatActivity {

    Boolean loading = false ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        OnclickLogin();
        OnclickSignUp();

    }

    private void OnclickLogin(){
        Button open = findViewById(R.id.btnlogin);
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLogin();
            }
        });
    }

    public void openLogin(){
        startActivity(new Intent(this,MainActivity.class));
    }

    private void OnclickSignUp(){
        Button btnSignUp = findViewById(R.id.btnsignin);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!loading){
                    loading = true;
                    loadingSignUp(true);
                    signUp();
                }
            }
        });
    }

    private void loadingSignUp(boolean loaderna){
        ProgressBar progressBar = findViewById(R.id.loadingSignUp) ;
        LinearLayout linearLayout = findViewById(R.id.linearboutton);
        if(loaderna){
            progressBar.setVisibility(View.VISIBLE);
            linearLayout.setVisibility(View.GONE);
        }else {
            progressBar.setVisibility(View.GONE);
            linearLayout.setVisibility(View.VISIBLE);
        }
    }


    private Users newUser(){
        Users user = new Users();
        user.setName(((EditText)findViewById(R.id.name)).getText().toString());
        user.setFirstname(((EditText)findViewById(R.id.firstname)).getText().toString());
        user.setAge(Integer.parseInt(((EditText)findViewById(R.id.age)).getText().toString()));
        user.setEmail(((EditText)findViewById(R.id.email)).getText().toString());
        user.setPwd(((EditText)findViewById(R.id.password)).getText().toString());
        return user  ;
    }

    private void signUp(){
        Users user = newUser();
        Retrofit retrofit = Server.getServert();
        AuthApi authApi = retrofit.create(AuthApi.class);
        callApiSignUp(authApi,user);
    }

    private void callApiSignUp(AuthApi authApi,Users user){
        Call<HashMap<String,Object>> call = authApi.createNewUser(user);
        call.enqueue(new Callback<HashMap<String, Object>>() {
            @Override
            public void onResponse(Call<HashMap<String, Object>> call, Response<HashMap<String, Object>> response) {
                if(response.isSuccessful()){
                    HashMap<String,Object> result = response.body();
                    if(new Double(result.get("status")+"").intValue()==200){
                        storeData(result.get("data"));
                        redirectHome();
                    }else {
                        loadingSignUp(false);
                        TextView error_message = findViewById(R.id.errormessage);
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
        startActivity(new Intent(SignUpActivity.this, HomeActivity.class));
    }
}