package org.techtown.narcissism_android.net;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Server {
    private static Server instance;
    private ServerApi api;

    private Server(){
        String url="http://10.80.161.48:3000";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(ServerApi.class);
    }
    public static Server getInstance(){
        if(instance == null) instance= new Server();
        return instance;
    }
    public ServerApi getApi(){return api;}
}
