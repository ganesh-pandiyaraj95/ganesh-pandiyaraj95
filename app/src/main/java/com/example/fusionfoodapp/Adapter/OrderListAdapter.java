package com.example.fusionfoodapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fusionfoodapp.Model.ItemModel;
import com.example.fusionfoodapp.R;

import java.util.List;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.MyViewHolder> {
    List<ItemModel> menus;
    Context context;

//    public OrderListAdapter(List<ItemModel> itemModels, Context context) {
//        this.itemModels = itemModels;
//        this.context = context;
//    }

    public OrderListAdapter(List<ItemModel> menus,Context context) {
        this.menus = menus;
       this.context = context;
    }

    @NonNull
    @Override
    public OrderListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.order_list_adapter,parent,false);
        return new OrderListAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderListAdapter.MyViewHolder holder, int position) {
        holder.tvItemName.setText(menus.get(position).getName());
        holder.tvPrice.setText("Price: $"+String.format("%.2f", menus.get(position).getTotal() * menus.get(position).getPrice()));
        holder.tvQty.setText("Qty: " + menus.get(position).getTotal());
        Glide.with(holder.IvItem)
                .load(menus.get(position).getUrl())
                .into(holder.IvItem);

    }

    @Override
    public int getItemCount() {
        return menus.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView IvItem;
        TextView tvItemName,tvPrice,tvQty;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            IvItem=itemView.findViewById(R.id.IvItem);
            tvItemName=itemView.findViewById(R.id.tvItemName);
            tvPrice=itemView.findViewById(R.id.tvPrice);
            tvQty=itemView.findViewById(R.id.tvQty);


        }
    }
}
