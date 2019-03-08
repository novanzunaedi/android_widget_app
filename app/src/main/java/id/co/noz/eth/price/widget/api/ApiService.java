package id.co.noz.eth.price.widget.api;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import id.co.noz.eth.price.widget.entities.Crypto;
import id.co.noz.eth.price.widget.entities.Currency;
import id.co.noz.eth.price.widget.utils.OnFailureListener;
import id.co.noz.eth.price.widget.utils.OnSuccessListener;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiService {
    private EndPoint endPoint;
    private String apiKey;

    public ApiService(String apiKey, EndPoint endPoint){
        this.endPoint = endPoint;
        this.apiKey = apiKey;
    }

    public void getEthereum(Currency currency,
                            OnSuccessListener<Crypto> onSuccessListener,
                            OnFailureListener<String> onFailureListener){
        getCrypto(1027, currency, onSuccessListener, onFailureListener);
    }

    public void getBitcoin(Currency currency,
                           OnSuccessListener<Crypto> onSuccessListener,
                           OnFailureListener<String> onFailureListener){
        getCrypto(1, currency, onSuccessListener, onFailureListener);
    }


    private void getCrypto(final int cryptoId, final Currency currency,
                           final OnSuccessListener<Crypto> onSuccessListener,
                           final OnFailureListener<String> onFailureListener){

        endPoint.getCryptoData(apiKey, cryptoId, currency.getValue()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.body() != null) {
                        JSONObject object = new JSONObject(response.body().string());
                        JSONObject eth = object.getJSONObject("data").getJSONObject(String.valueOf(cryptoId));
                        JSONObject quote = eth.getJSONObject("quote").getJSONObject(currency.getValue());
                        Crypto crypto = new Crypto.Builder()
                                .setName(eth.getString("name"))
                                .setSymbol(eth.getString("symbol"))
                                .setCurrency(currency.getValue())
                                .setPrice(quote.getDouble("price"))
                                .setPercentChangeDay(quote.getDouble("percent_change_24h"))
                                .setPercentChangeWeek(quote.getDouble("percent_change_7d"))
                                .build();

                        System.out.println("CRYPTO NYA " +  crypto.getName());
                        onSuccessListener.onSuccess(crypto);
                    }
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                onFailureListener.onFailure(t.getLocalizedMessage());
            }
        });
    }
}

























