package com.diaxx.fernandods.androidfipe;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by fernandods on 17/07/17.
 */

public class BrandAdapter extends RecyclerView.Adapter<BrandAdapter.MyViewHolder> {

    private LayoutInflater mInflater;
    private Context mContext;
    private List<Brand> mListMarcas;
    private PositionClickListener mListener;

    public BrandAdapter(Context context, List<Brand> listMarcas, PositionClickListener listener) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
        mListMarcas = listMarcas;
        mListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.textViewMarca.setText(mListMarcas.get(position).getName());
        holder.textViewMarca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.itemClicked(position);
                }
            }
        });


    }

    public Brand getItem(int position) {

        return mListMarcas.get(position);
    }

    @Override
    public int getItemCount() {
        return mListMarcas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewMarca;

        public MyViewHolder(View itemView) {
            super(itemView);
            textViewMarca = (TextView) itemView.findViewById(R.id.textViewMarca);
        }
    }


    public interface PositionClickListener {
        void itemClicked(int position);
    }
}