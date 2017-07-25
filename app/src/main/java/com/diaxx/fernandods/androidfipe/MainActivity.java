package com.diaxx.fernandods.androidfipe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewMarcas;
    private MarcasAdapter mAdapter;
    private android.widget.RelativeLayout realtiveLoading;
    private RelativeLayout realtiveErro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        realtiveErro = (RelativeLayout) findViewById(R.id.realtiveErro);
        realtiveLoading = (RelativeLayout) findViewById(R.id.realtiveLoading);
        recyclerViewMarcas = (RecyclerView) findViewById(R.id.recyclerViewMarcas);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewMarcas.setLayoutManager(llm);

        callService();

    }

    private void callService() {

        realtiveLoading.setVisibility(View.VISIBLE);
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url("http://fipeapi.appspot.com/api/1/carros/marcas.json")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                realtiveErro.setVisibility(View.VISIBLE);
                realtiveLoading.setVisibility(View.GONE);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseData = response.body().string();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<Marca>>() {
                        }.getType();
                        List<Marca> marcaList = gson.fromJson(responseData, type);
                        MarcasAdapter adapte = new MarcasAdapter(getApplicationContext(),
                                marcaList, new MarcasAdapter.PositionClickListener() {
                            @Override
                            public void itemClicked(int position) {
                                AppPreferences.newInstace().toggleSaveFavorites(mAdapter.getItem(position));
                            }
                        });
                        Realm.newInstace().saveCache(marcaList);
                        recyclerViewMarcas.setAdapter(adapte);
                        realtiveLoading.setVisibility(View.GONE);
                    }
                });
            }
        });

    }

}
