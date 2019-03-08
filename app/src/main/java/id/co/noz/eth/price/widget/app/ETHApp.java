package id.co.noz.eth.price.widget.app;

import android.app.Application;

import id.co.noz.eth.price.widget.api.ApiComponent;
import id.co.noz.eth.price.widget.api.ApiModule;
import id.co.noz.eth.price.widget.api.DaggerApiComponent;


public class ETHApp extends Application {

    public static ETHApp app;
    private AppModule appModule;
    private ApiModule apiModule;
    private ApiComponent apiComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;

        appModule = new AppModule(this);
        apiModule = new ApiModule();

        apiComponent = DaggerApiComponent.builder()
                .appModule(appModule)
                .apiModule(apiModule)
                .build();
    }

    public AppModule getAppModule() {return appModule;}

    public ApiModule getApiModule() {return apiModule;}

    public ApiComponent getApiComponent() {return apiComponent;}
}

























