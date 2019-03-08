package id.co.noz.eth.price.widget.api;

import javax.inject.Singleton;

import dagger.Component;
import id.co.noz.eth.price.widget.app.AppModule;
import id.co.noz.eth.price.widget.view.CryptoWidget;

@Singleton
@Component(modules = {AppModule.class, ApiModule.class})
public interface ApiComponent {
    void inject(CryptoWidget widget);
}
