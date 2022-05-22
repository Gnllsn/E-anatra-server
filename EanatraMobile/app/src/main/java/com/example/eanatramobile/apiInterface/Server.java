package com.example.eanatramobile.apiInterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class Server { // final : pas d'heritage

    private static Retrofit server = null ;
    private static final String base_url = "http://localhost:3000/" ;
    private static final String ip_url = "http://192.168.1.110:3000/" ;
    private static final String server_url = "https://api-e-anatra.herokuapp.com/" ;

    private Server() {
        super();
    }

    public static final Retrofit getServert(){
        if(server==null){
            server = new Retrofit.Builder()
                    .baseUrl(ip_url)
//                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build() ;
        }
        return server ;
    }


}
