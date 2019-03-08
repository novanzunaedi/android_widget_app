package id.co.noz.eth.price.widget.api;

import android.content.res.Resources;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import id.co.noz.eth.price.widget.R;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    @Provides @Singleton
    public Gson provideGson() {return new Gson();}

    @Provides @Singleton
    public GsonConverterFactory provideGsonConverter(Gson gson){
        return GsonConverterFactory.create(gson);
    }

    @Provides @Singleton
    public EndPoint provideAPI(GsonConverterFactory converterFactory){
        return new Retrofit.Builder()
                .baseUrl(EndPoint.BASE_URL)
                .addConverterFactory(converterFactory)
                .build().create(EndPoint.class);
    }

    @Provides @Singleton
    public ApiService provideCMCService(Resources res, EndPoint endPoint){
        String apiKey = res.getString(R.string.coin_market_api_key);
        return new ApiService(apiKey, endPoint);
    }
}































