package com.example.fusionfoodapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fusionfoodapp.Adapter.ItemAdapter;
import com.example.fusionfoodapp.Adapter.OrderListAdapter;
import com.example.fusionfoodapp.Database.DataBaseHelper;
import com.example.fusionfoodapp.MainActivity;
import com.example.fusionfoodapp.Model.FoodModel;
import com.example.fusionfoodapp.Model.ItemModel;
import com.example.fusionfoodapp.Model.OrderListModel;
import com.example.fusionfoodapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CheckoutActivity extends AppCompatActivity {
    TextView tvvalue,TvPlaceOdr;
    OrderListAdapter orderListAdapter;
    RecyclerView RvOrderList;
    EditText EdName,EdPhone,EdEmail;
    public List<ItemModel> itemModels=null;
    OrderListModel orderListModel;
    String totalAmt;

    public String filterOdrNmae,str;

    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        dataBaseHelper=new DataBaseHelper(CheckoutActivity.this);
        tvvalue=findViewById(R.id.tvvalue);
        RvOrderList=findViewById(R.id.RvOrderList);
        TvPlaceOdr=findViewById(R.id.TvPlaceOdr);
        EdName=findViewById(R.id.EdName);
        EdPhone=findViewById(R.id.EdPhone);
        EdEmail=findViewById(R.id.EdEmail);
       // dataBaseHelper=new DataBaseHelper(CheckoutActivity.this);

        FoodModel foodModel=getIntent().getParcelableExtra("FoodModel");
        calculateTotalAmount(foodModel);

        RvOrderList.setLayoutManager(new LinearLayoutManager(this));
        orderListAdapter = new OrderListAdapter(foodModel.getMenus(),this);
        RvOrderList.setAdapter(orderListAdapter);

        itemModels=foodModel.getMenus();


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            Set<String> filterOdrNmae = itemModels.stream()
                    .map(p -> p.getName())
                    .collect(Collectors.toSet());

            String[] stringArray = filterOdrNmae.toArray(new String[0]);
            StringBuffer sb = new StringBuffer();
            for(int i = 0; i < stringArray.length; i++) {
                sb.append(stringArray[i]);
            }
             str = Arrays.toString(stringArray);

            System.out.println(str);

        }


        TvPlaceOdr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               boolean checkFields= validation(EdName.getText().toString(),EdPhone.getText().toString(),EdEmail.getText().toString());
                if(checkFields==true){
                    try{
                        orderListModel =new OrderListModel(-1,EdName.getText().toString(),EdPhone.getText().toString(),EdEmail.getText().toString(),str,totalAmt);

                    }catch (Exception e){
                        Toast.makeText(CheckoutActivity.this,e.toString(),Toast.LENGTH_LONG).show();

                    }
                    dataBaseHelper=new DataBaseHelper(CheckoutActivity.this);
                    boolean success = dataBaseHelper.addOne(orderListModel);
                    //Toast.makeText(CheckoutActivity.this,"Success"+success,Toast.LENGTH_LONG).show();

                    if(success==true){
                        callSuccessDialogue();
                    }
                }
            }
        });

    }

    private void callSuccessDialogue() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.success_dialogue);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView TvOkay=dialog.findViewById(R.id.TvOkay);

        TvOkay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CheckoutActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });
        dialog.show();



    }

    private Boolean validation(String Name, String Phone, String Email) {
        if(Name.length()==0){
            EdName.requestFocus();
            EdName.setError("Please Enter name");
            return false;
        }else if(!Name.matches("[a-zA-Z]+")){
            EdName.requestFocus();
            EdName.setError("Enter Only name");
            return false;
        } else if(!Phone.matches("^[6-9][0-9]{9}$+")){
            EdPhone.requestFocus();
            EdPhone.setError("Please Enter Valid Mobile Number");
            return false;
        }else if(Phone.length()==0){
            EdPhone.requestFocus();
            EdPhone.setError("Please Enter Mobile number");
            return false;
        }else if(Email.length()==0){
            EdEmail.requestFocus();
            EdEmail.setError("Please Enter Email");
            return false;
        }else if(!Email.matches("[a-zA-Z0-9_.]+@[a-zA-Z0-9]+.[a-zA-Z]{2,3}[.] {0,1}[a-zA-Z]+")){
            EdEmail.requestFocus();
            EdEmail.setError("Please Enter Valid email");
            return false;
        }
        else{
            return  true;
        }

    }



    private void calculateTotalAmount(FoodModel foodModel) {
        float subTotalAmount = 0f;

        for(ItemModel i : foodModel.getMenus()) {
            subTotalAmount += i.getTotal() * i.getPrice();
        }


        totalAmt=String.valueOf(subTotalAmount);

        tvvalue.setText("$"+String.format("%.2f", subTotalAmount));



    }
}