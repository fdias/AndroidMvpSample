package com.diaxx.fernandods.androidfipe;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by fernandods on 14/08/17.
 */

public class BrandManager {

    private Realm realm;
    private AppPreferences preferences;
    private Context context;
    private OkHttpClient client;

    BrandManager(Context context) {
        this.context = context;
    }

    public void callService(Callback callBack) {

        client = new OkHttpClient();

        Request request = new Request.Builder().url("http://fipeapi.appspot.com/api/1/carros/marcas.json")
                .build();

        client.newCall(request).enqueue(callBack);

    }

    public List<Brand> parseResult(Response response) throws IOException {

        final String responseData = response.body().string();

        Gson gson = new Gson();
        Type type = new TypeToken<List<Brand>>() {
        }.getType();

        return gson.fromJson(responseData, type);

    }

    public void toggleSaveFavorites(Brand marca) {

        preferences = new AppPreferences();

        preferences.toggleSaveFavorites(marca);

    }

    public void saveCache(List<Brand> lista) {
        realm = new Realm(context);

        realm.saveCache(lista);
    }


}
