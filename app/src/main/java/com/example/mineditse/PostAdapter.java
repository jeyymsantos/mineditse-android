package com.example.mineditse;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class PostAdapter extends  RecyclerView.Adapter<PostAdapter.ViewHolder> {

    List<PostModel> postList;

    public PostAdapter(Context context, List<PostModel> postList) {
        this.postList = postList;
    }

    @NonNull
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolder holder, int position) {
        holder.product_name.setText(postList.get(position).getProdName());
        holder.product_status.setText(postList.get(position).getProdStatus());
        holder.product_price.setText("Php " + postList.get(position).getProdPrice());
        holder.bale_id.setText("Bale ID: " + Integer.toString(postList.get(position).getBaleId()));

//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);
//
//        //image
//        URL url = null;
//        try {
//            url = new URL("https://mineditse.store" + postList.get(position).getProdImgPath());
//            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//            holder.product_image.setImageBitmap(bmp);
//        } catch (Exception e) {
//            e.printStackTrace();
//            holder.product_name.setText(e.toString());
//        }

        if (postList.get(position).getProdPrice().equalsIgnoreCase("Sold")) {
            holder.product_price.setTextColor(Color.BLUE);
        }
        if (postList.get(position).getProdPrice().equalsIgnoreCase("Pending")) {
            holder.product_price.setTextColor(Color.rgb(255, 191, 0));
        }
        if (postList.get(position).getProdPrice().equalsIgnoreCase("Available")) {
            holder.product_price.setTextColor(Color.GREEN);
        }
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView product_name, bale_id, product_price, product_status;
        ImageView product_image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            product_image = itemView.findViewById(R.id.product_image);
            product_name = itemView.findViewById(R.id.product_name);
            bale_id = itemView.findViewById(R.id.bale_id);
            product_price = itemView.findViewById(R.id.product_status);
            product_status = itemView.findViewById(R.id.product_price);
        }
    }
}
