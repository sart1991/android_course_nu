package com.super_test.thesupertest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Network;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private Context context;
    private ArrayList<String> imagesUrl;
    private ArrayList<String> titles;
    private ArrayList<String> prices;
    private ArrayList<Integer> quantities;
    private DataBaseHandler db;
    private ArrayList<Product> products;

    public RecyclerAdapter(Context context) {
        this.context = context;
        db = new DataBaseHandler(context);
        imagesUrl = new ArrayList<>();
        titles = new ArrayList<>();
        prices = new ArrayList<>();
        quantities = new ArrayList<>();
        products = db.getAllProducts();
        setPropertyList();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public NetworkImageView itemImage;
        public TextView itemTitle;
        public TextView itemPrice;
        public TextView itemQuantity;
        public TextView itemTextButton;

        public ViewHolder(View itemView) {
            super(itemView);
            itemImage = (NetworkImageView)itemView.findViewById(R.id.item_image);
            itemTitle = (TextView)itemView.findViewById(R.id.item_title);
            itemPrice = (TextView)itemView.findViewById(R.id.item_price);
            itemQuantity = (TextView)itemView.findViewById(R.id.item_quantity);
            itemTextButton = (TextView)itemView.findViewById(R.id.item_add);
            itemTextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    int quantity = quantities.get(position) + 1;
                    Product product = products.get(position);
                    quantities.set(position, quantity);
                    itemQuantity.setText(String.format(quantities.get(position).toString()));
                    product.setQuantity(quantity);
                    db.addProduct(product);
                    Log.i("RecyclerAdapter.class", db.getAllProducts().toString());
                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_product_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.itemImage.setDefaultImageResId(R.mipmap.ic_launcher);
        viewHolder.itemImage.setImageUrl(imagesUrl.get(position), new RequestCall().getImageFromUrl(context));
        viewHolder.itemTitle.setText(titles.get(position));
        viewHolder.itemPrice.setText(prices.get(position));
        viewHolder.itemQuantity.setText(String.format(quantities.get(position).toString()));
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    private void setPropertyList() {
        try {
            for (Product product : products) {
                imagesUrl.add(product.getImageURL());
                titles.add(product.getName());
                prices.add(product.getPrice());
                quantities.add(product.getQuantity());
            }
        } catch (Exception e) {
            Log.i("RecyclerAdapter.class", e.getMessage());
        }
    }
}
