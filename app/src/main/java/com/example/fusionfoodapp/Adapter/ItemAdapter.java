package com.example.fusionfoodapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fusionfoodapp.MainActivity;
import com.example.fusionfoodapp.Model.ItemModel;
import com.example.fusionfoodapp.R;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.viewholder> {
    List<ItemModel>itemModels;
    Context context;
    private FoodListClickListner clickListner;
   // int count=0;


    public ItemAdapter(List<ItemModel> itemModels, Context context, FoodListClickListner clickListner) {
        this.itemModels = itemModels;
        this.context = context;
        this.clickListner = clickListner;
    }
    public void updateData(List<ItemModel> itemModels){
        this.itemModels=itemModels;
        notifyDataSetChanged();

    }



    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.food_list_adapter,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, @SuppressLint("RecyclerView") int position) {
        //final ItemModel itemModel=itemModels.get(position);
        //holder.IvItem.setImageResource(itemModels.get(position).getImg());
        holder.tvItemName.setText(itemModels.get(position).getName());
        holder.tvPrice.setText(" $ "+String.valueOf(itemModels.get(position).getPrice()) );
        holder.tvDescription.setText(itemModels.get(position).getDesc());

        Glide.with(holder.IvItem)
                .load(itemModels.get(position).getUrl())
                .into(holder.IvItem);

        holder.LlCartTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ItemModel itemModel=itemModels.get(position);
                itemModel.setTotal(1);
                clickListner.onAddToCart(itemModel);
                holder.LlCartTag.setVisibility(View.GONE);
                holder.LlCart.setVisibility(View.VISIBLE);
                holder.TvNumber.setText(String.valueOf(itemModel.getTotal()));

            }
        });

        holder.CVmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 ItemModel itemModel=itemModels.get(position);
               // itemModel.setTotal(1);
                int total=itemModel.getTotal();;
                total++;
                if(total <=10){
                    itemModel.setTotal(total);
                    clickListner.onUpdateToCart(itemModel);
                    holder.TvNumber.setText(String.valueOf(itemModel.getTotal()));
                }
//                else{
//                   clickListner.onRemoveFromCart(itemModel);
//                    holder.TvNumber.setText(String.valueOf(itemModel.getTotal()));
//                }




            }
        });
        holder.Cvless.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ItemModel itemModel=itemModels.get(position);
                int total=itemModel.getTotal();
                total--;
                if(total >0){
                    itemModel.setTotal(total);
                    clickListner.onUpdateToCart(itemModel);
                    holder.TvNumber.setText(String.valueOf(itemModel.getTotal()));
                   // holder.Cvless.setClickable(true);
                }else {
                    itemModel.setTotal(total);
                    clickListner.onRemoveFromCart(itemModel);
                    holder.LlCartTag.setVisibility(View.VISIBLE);
                    holder.LlCart.setVisibility(View.GONE);


                    //.TvNumber.setText(itemModel.getTotal());
                }

            }
        });

//        if(count>0){
//            holder.Cvless.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    count=count-1;
//                    holder.TvNumber.setText(String.valueOf(count));
//                }
//            });
//        }

    }

    @Override
    public int getItemCount() {
        return itemModels.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        CardView Cvless,CVmore;
        TextView tvItemName,tvPrice,tvDescription,TvNumber;
        ImageView IvItem;
        LinearLayout LlCartTag,LlCart;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            tvItemName=itemView.findViewById(R.id.tvItemName);
            tvPrice=itemView.findViewById(R.id.tvPrice);
            tvDescription=itemView.findViewById(R.id.tvDescription);
            IvItem=itemView.findViewById(R.id.IvItem);
            CVmore=itemView.findViewById(R.id.CVmore);
            Cvless=itemView.findViewById(R.id.Cvless);
            TvNumber=itemView.findViewById(R.id.TvNumber);
            LlCartTag=itemView.findViewById(R.id.LlCartTag);
            LlCart=itemView.findViewById(R.id.LlCart);
        }
    }

    public interface FoodListClickListner{
        public void onAddToCart(ItemModel itemModel);
        public void onUpdateToCart(ItemModel itemModel);
        public void onRemoveFromCart(ItemModel itemModel);
    }
}
