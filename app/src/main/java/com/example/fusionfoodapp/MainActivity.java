package com.example.fusionfoodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.RecoverySystem;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fusionfoodapp.Activity.CheckoutActivity;
import com.example.fusionfoodapp.Adapter.ItemAdapter;
import com.example.fusionfoodapp.Model.FoodModel;
import com.example.fusionfoodapp.Model.ItemModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemAdapter.FoodListClickListner {
    RecyclerView rvItemList;
    ItemAdapter itemAdapter;
    FoodModel foodModel=new FoodModel();
    public List<ItemModel>itemModels=null;
    public List<ItemModel>itemCartList;
    private int totalItemInCart=0;
    TextView TvItemCount;
    RelativeLayout RlCheckout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvItemList=findViewById(R.id.rvItemList);
        TvItemCount=findViewById(R.id.TvItemCount);
        RlCheckout=findViewById(R.id.RlCheckout);
        List<FoodModel> foodModels =  getFoodData();
        //String s= getIntent().getStringExtra("Hai");

        //itemModels = bundle.getParcelable("data");

        // itemModels = new Gson().fromJson("data", new TypeToken<List<ItemModel>>(){}.getType());

        itemModels = foodModels.get(0).getMenus();

        itemAdapter=new ItemAdapter(itemModels,this,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rvItemList.setLayoutManager(linearLayoutManager);
        rvItemList.setAdapter(itemAdapter);
//        itemModels= foodModel.getItemModel();

        RlCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(totalItemInCart==0){
                    Toast.makeText(MainActivity.this, "Please add some items in cart.", Toast.LENGTH_SHORT).show();
                    return;

                }
                foodModel.setMenus(itemCartList);
                Intent intent = new Intent(MainActivity.this, CheckoutActivity.class);
                intent.putExtra("FoodModel",foodModel);
                startActivity(intent);
            }
        });


    }

    private List<FoodModel> getFoodData() {
        InputStream is = getResources().openRawResource(R.raw.food);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try{
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while(( n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0,n);
            }
        }catch (Exception e) {

        }

        String jsonStr = writer.toString();
        Gson gson = new Gson();
        FoodModel[] foodModels =  gson.fromJson(jsonStr, FoodModel[].class);
        List<FoodModel> restList = Arrays.asList(foodModels);

        return  restList;
    }


    @Override
    public void onAddToCart(ItemModel itemModel) {

        try{
            if(itemCartList == null) {
                itemCartList = new ArrayList<>();
            }
            itemCartList.add(itemModel);
            totalItemInCart = 0;

            for(ItemModel i : itemCartList) {
                totalItemInCart = totalItemInCart + i.getTotal();
            }
            TvItemCount.setText("Checkout (" +totalItemInCart +") items");
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public void onUpdateToCart(ItemModel itemModel) {
        if(itemCartList.contains(itemModel)){
            int index=itemCartList.indexOf(itemModel);
            itemCartList.remove(index);
            itemCartList.add(index,itemModel);
            totalItemInCart=0;
            for(ItemModel i:itemCartList){
                totalItemInCart=totalItemInCart+i.getTotal();
            }
            TvItemCount.setText("Checkout (" +String.valueOf(totalItemInCart) +") items");


            Log.d("Added------------------",String.valueOf(totalItemInCart));
        }

    }

    @Override
    public void onRemoveFromCart(ItemModel itemModel) {

        if(itemCartList.contains(itemModel)){
            itemCartList.remove(itemModel);
            totalItemInCart=0;
            for(ItemModel i:itemCartList){
                totalItemInCart=totalItemInCart+i.getTotal();
            }
            TvItemCount.setText("Checkout (" +String.valueOf(totalItemInCart) +") items");
            Log.d("---removed--",String.valueOf(totalItemInCart));
        }

    }
}