package com.diaxx.fernandods.androidfipe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private android.widget.RelativeLayout realtiveLoading;
    private RelativeLayout realtiveErro;
    private BrandManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        realtiveErro = (RelativeLayout) findViewById(R.id.realtiveErro);
        realtiveLoading = (RelativeLayout) findViewById(R.id.realtiveLoading);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewBrand);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        manager = new BrandManager(getApplicationContext());

        callApi();

    }

    private void callApi() {

        showLoading();

        manager.callService(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                showError();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {

                final List<Brand> brandList;
                brandList = manager.parseResult(response);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        manager.saveCache(brandList);
                        showResult(brandList);

                    }
                });
            }
        });

    }

    private void showResult(final List<Brand> brandList) {
        BrandAdapter adapte = new BrandAdapter(getApplicationContext(),
                brandList, new BrandAdapter.PositionClickListener() {
            @Override
            public void itemClicked(int position) {
                manager.toggleSaveFavorites(brandList.get(position));
            }
        });

        recyclerView.setAdapter(adapte);
        hideLoading();
    }

    private void hideLoading() {
        realtiveLoading.setVisibility(View.GONE);
    }

    private void showLoading() {
        realtiveLoading.setVisibility(View.VISIBLE);
    }

    private void showError() {
        realtiveErro.setVisibility(View.VISIBLE);
        hideLoading();
    }


}
